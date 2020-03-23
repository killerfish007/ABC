package common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class KMostFrequentWordsFromFile {

	public static void main(String[] args) {
		int k = 5;
		String fileName = "C:\\Users\\mamidika\\Desktop\\MyBase\\Work\\ABC\\project\\src\\common\\file.txt";
		StringBuilder sb = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(a -> sb.append(a));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] words = sb.toString().split(" ");
		printKMostFreq(words, k);
	}

	private static void printKMostFreq(String[] words, int k) {

		MinHeap minHeap = new MinHeap(k);
		Trie root = new Trie();
		for (String word : words)
			insertTrieAndHeap(word, root, minHeap);

		displayMinHeap(minHeap);

	}

	private static void displayMinHeap(MinHeap minHeap) {
		for (int i = 0; i < minHeap.currentSize; i++) {
			System.out.format("%s : %d\n", minHeap.array[i].word, minHeap.array[i].frequency);
		}
	}

	private static void insertTrieAndHeap(String word, Trie root, MinHeap minHeap) {

		Trie pCrawl = root;
		for (int i = 0; i < word.length(); i++) {
			int index = Character.toLowerCase(word.charAt(i)) - 'a';
			if (pCrawl.array[index] == null) {
				pCrawl.array[index] = new Trie();
			}
			pCrawl = pCrawl.array[index];
		}
		if (pCrawl.isEndOfWord) {
			pCrawl.frequency++;
		} else {
			pCrawl.isEndOfWord = true;
			pCrawl.frequency = 1;
		}

		insertMinHeap(word, pCrawl, minHeap);
	}

	private static void insertMinHeap(String word, Trie trieNode, MinHeap minHeap) {

		// Case1 : word is already present in minHeap
		if (trieNode.indexInMinHeap != -1) {
			minHeap.array[trieNode.indexInMinHeap].frequency++;
			minHeapify(minHeap, trieNode.indexInMinHeap);
		}

		// Case2 : word is not present and heap is not full
		else if (minHeap.currentSize < minHeap.capacity) {
			int index = minHeap.currentSize;
			trieNode.indexInMinHeap = index;
			minHeap.array[index] = new MinHeapNode(trieNode, trieNode.frequency, word);
			minHeap.currentSize++;

			buildMinHeap(minHeap);
		}

		// Case3 : word is not present and heap is full and frequency of word is more
		// than root of heap
		else if (trieNode.frequency > minHeap.array[0].frequency) {
			minHeap.array[0].trieNode = trieNode;
			minHeap.array[0].trieNode.indexInMinHeap = 0;

			minHeap.array[0].frequency = trieNode.frequency;
			minHeap.array[0].word = word;

			minHeapify(minHeap, 0);
		}
	}

	private static void buildMinHeap(MinHeap minHeap) {
		int n = minHeap.currentSize - 1;
		for (int i = (n - 1) / 2; i >= 0; i--) {
			minHeapify(minHeap, i);
		}
	}

	private static void minHeapify(MinHeap minHeap, int i) {
		int smallest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (right < minHeap.currentSize && minHeap.array[right].frequency < minHeap.array[smallest].frequency) {
			smallest = right;
		} else if (left < minHeap.currentSize && minHeap.array[left].frequency < minHeap.array[smallest].frequency) {
			smallest = left;
		}

		if (smallest != i) {

			minHeap.array[smallest].trieNode.indexInMinHeap = i;
			minHeap.array[i].trieNode.indexInMinHeap = smallest;

			MinHeapNode temp = minHeap.array[i];
			minHeap.array[i] = minHeap.array[smallest];
			minHeap.array[smallest] = temp;

			minHeapify(minHeap, smallest);
		}

	}
}

class MinHeapNode {
	Trie trieNode;
	int frequency;
	String word;

	MinHeapNode(Trie trieNode, int frequency, String word) {
		this.trieNode = trieNode;
		this.frequency = frequency;
		this.word = word;
	}

}

class MinHeap {
	int capacity;
	int currentSize;
	MinHeapNode[] array;

	MinHeap(int capacity) {
		this.capacity = capacity;
		this.currentSize = 0;
		this.array = new MinHeapNode[capacity];
	}

}

class Trie {
	Trie[] array;
	boolean isEndOfWord;
	int frequency;
	int indexInMinHeap;

	Trie() {
		array = new Trie[26];
		isEndOfWord = false;
		frequency = 0;
		indexInMinHeap = -1;
	}
}