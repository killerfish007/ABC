package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Trie_HotelReviews {

	public static void main(String[] args) {
		String goodWords = "cool_ice_wifi";
		ArrayList<String> reviews = new ArrayList<>();
		reviews.add("water_is_cool");
		reviews.add("cold_ice_drink");
		reviews.add("cool_wifi_speed");
		System.out.println(solve(goodWords, reviews));
		System.out.println(solve1(goodWords, reviews));
	}

	// Non Trie Approach
	public static ArrayList<Integer> solve1(String s, ArrayList<String> arr) {
		ArrayList<Integer> ans = new ArrayList<>();
		if (arr.size() == 0)
			return ans;

		String[] str = s.split("_");
		HashSet<String> set = new HashSet<>(Arrays.asList(str));
		int count = 0;
		ArrayList<Pair> pairs = new ArrayList<>();

		for (int i = 0; i < arr.size(); i++) {
			count = 0;
			String[] temp = arr.get(i).split("_");
			for (String s1 : temp) {
				count += set.contains(s1) ? 1 : 0;
			}
			pairs.add(new Pair(count, i));
		}

		Collections.sort(pairs, new PairComparator());

		for (Pair p : pairs) {
			ans.add(p.index);
		}
		return ans;
	}

	// Trie Approach
	static TrieNode root;

	public static ArrayList<Integer> solve(String A, ArrayList<String> B) {

		root = new TrieNode();
		String[] goodWords = A.split("_");
		for (String s : goodWords) {
			insert(s);
		}
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.count == o2.count) {
					return o1.index - o2.index;
				}
				return o2.count - o1.count;
			}

		});
		for (int i = 0; i < B.size(); i++) {
			String[] words = B.get(i).split("_");
			int count = 0;
			for (String w : words) {
				if (search(w))
					count++;
			}
			pq.add(new Pair(count, i));
		}
		ArrayList<Integer> res = new ArrayList<>();
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			res.add(p.index);
		}
		return res;

	}

	public static void insert(String s) {
		int level;
		int length = s.length();
		int index;
		TrieNode pCrawl = root;
		for (level = 0; level < length; level++) {
			index = s.charAt(level) - 'a';
			if (pCrawl.ar[index] == null)
				pCrawl.ar[index] = new TrieNode();
			pCrawl = pCrawl.ar[index];
		}
		pCrawl.isEndOfWord = true;
	}

	public static boolean search(String s) {
		int level;
		int length = s.length();
		int index;
		TrieNode pCrawl = root;
		for (level = 0; level < length; level++) {
			index = s.charAt(level) - 'a';
			if (pCrawl.ar[index] == null)
				return false;
			pCrawl = pCrawl.ar[index];
		}
		return (pCrawl != null && pCrawl.isEndOfWord);
	}
}

class TrieNode {
	TrieNode[] ar;
	boolean isEndOfWord;

	TrieNode() {
		ar = new TrieNode[26];
		isEndOfWord = false;
	}
}

class Pair {
	int count;
	int index;

	Pair(int count, int index) {
		this.count = count;
		this.index = index;
	}
}

class PairComparator implements Comparator<Pair> {
	public int compare(Pair p1, Pair p2) {
		if (p1.count == p2.count) {
			return p1.index - p2.index;
		}
		return p2.count - p1.count;
	}
}