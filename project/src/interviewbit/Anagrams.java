package interviewbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Anagrams {

	public static void main(String[] args) {
		String[] ar = { "cat", "dog", "god", "tca" };
		System.out.println(anagrams(Arrays.asList(ar)));
	}

	public static ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		HashMap<String, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < A.size(); i++) {
			char[] c = A.get(i).toCharArray();
			Arrays.sort(c);
			String token = new String(c);
			ArrayList<Integer> temp = map.getOrDefault(token, new ArrayList<Integer>());
			temp.add(i + 1);
			map.put(token, temp);
		}
		for (ArrayList<Integer> v : map.values()) {
			res.add(v);
		}
		return res;
	}
}
