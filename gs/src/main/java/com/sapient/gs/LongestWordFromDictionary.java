package com.sapient.gs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestWordFromDictionary {
	static HashSet<String> result = new HashSet<String>();
	static int maxlen = 0;

	public static void main(String[] args) {
		if (doTestPass())
			System.out.println("Test Passed");
		else
			System.out.println("Test Failded");
	}

	static Set<String> longestWord(String letters, Dictionary dict) {
		// find all possible permutation of string "dogs"
		permute(letters.toCharArray(), 0, letters.length(), dict);
		System.out.println(result.toString());
		return result;
	}
	

	static void permute(char[] arr, int left, int right, Dictionary dict) {
		if (left == right) {
			String s1 = "";
			for (int i = 0; i < arr.length; i++) {
				s1 = s1 + arr[i];
			}
			combination(s1.toCharArray(), dict);
		} else {
			// swapping first and adjacent character
			// ABC-> BAC-> BCA
			// ACB-> CAB-> CBA			
			for (int i = left; i < right; i++) {
				arr = swap(arr, left, i);
				permute(arr, left + 1, right, dict);
				arr = swap(arr, left, i);
			}
		}
	}

	private static void combination(char[] charArray, Dictionary dict) {
		String str = "";
		for (int i = 0; i < charArray.length; i++) {
			str = str + charArray[i];
			if (dict.contains(str)) {
				if (str.length() == maxlen) {
					result.add(str);
				} else if (str.length() > maxlen) {
					maxlen = str.length();
					result.clear();
					result.add(str);
				}
			}
		}
	}

	private static char[] swap(char[] arr, int len, int i) {
		char temp;
		temp = arr[len];
		arr[len] = arr[i];
		arr[i] = temp;
		return arr;
	}

	public static boolean doTestPass() {
		Dictionary dict = new Dictionary(
				new String[] { "to", "toe", "too", "toes", "doe", "dogs", "god", "dog", "book", "banana" });

		boolean result = new HashSet<String>(Arrays.asList("toe", "doe", "dog", "god", "too"))
				.equals(longestWord("oegdot", dict));
		result = result && new HashSet<String>(Arrays.asList("toes", "dogs")).equals(longestWord("oegdots", dict));
		return result;
	}
}

class Dictionary {
	private String[] entries;

	public Dictionary(String[] entries) {
		this.entries = entries;
	}

	public boolean contains(String word) {
		return Arrays.asList(entries).contains(word);
	}
}