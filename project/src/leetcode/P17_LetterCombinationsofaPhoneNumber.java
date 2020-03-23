package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17_LetterCombinationsofaPhoneNumber {

	public static void main(String[] args) {
		List<String> results = letterCombinations("23");
		results.forEach(a -> System.out.println(a));

	}

	public static List<String> letterCombinations(String digits) {
		Map<String, String> phone = new HashMap<String, String>();
		phone.put("2", "abc");
		phone.put("3", "def");
		phone.put("4", "ghi");
		phone.put("5", "jkl");
		phone.put("6", "mno");
		phone.put("7", "pqrs");
		phone.put("8", "tuv");
		phone.put("9", "wxyz");

		List<String> output = new ArrayList<String>();

		if (digits.length() != 0) {
			backtrack("", digits, phone, output);
		}

		return output;
	}

	private static void backtrack(String combination, String digits, Map<String, String> phone, List<String> output) {
		if (digits.length() == 0) {
			output.add(combination);
		} else {
			String digit = digits.substring(0, 1);
			String letters = phone.get(digit);
			for (int i = 0; i < letters.length(); i++) {
				backtrack(combination + letters.charAt(i), digits.substring(1), phone, output);
			}
		}
	}
}
