package interviewbit;

import java.util.HashMap;

public class LRUCache {
	int capacity;
	DLLNode start;
	DLLNode end;
	HashMap<Integer, DLLNode> map;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
		start = new DLLNode(0, 0);
		end = new DLLNode(0, 0);
		start.next = end;
		end.prev = start;
	}

	public void addNode(DLLNode node) {
		DLLNode tmp = start.next;
		node.next = tmp;
		tmp.prev = node;
		start.next = node;
		node.prev = start;
	}

	public void removeNode(DLLNode node) {
		DLLNode next = node.next;
		DLLNode prev = node.prev;
		prev.next = next;
		next.prev = prev;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			DLLNode node = map.get(key);
			removeNode(node);
			addNode(node);
			return node.val;
		}
		return -1;
	}

	public void set(int key, int value) {
		DLLNode node = new DLLNode(key, value);
		if (map.containsKey(key)) {
			DLLNode tmp = map.get(key);
			removeNode(tmp);
			addNode(node);
		} else {
			if (capacity == map.size()) {
				DLLNode tmp = end.prev;
				removeNode(tmp);
				map.remove(tmp.key);
			}
			addNode(node);
		}
		map.put(key, node);
	}

}

class DLLNode {
	DLLNode prev;
	DLLNode next;
	int key;
	int val;

	public DLLNode(int key, int val) {
		this.key = key;
		this.val = val;
		prev = null;
		next = null;
	}
}