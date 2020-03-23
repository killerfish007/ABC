package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P117_PopulatingNextRightPointersinEachNodeII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Node connect(Node root) {
		if (root == null) {
			return root;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		int len = 1;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			len--;
			if (cur.left != null) {
				q.offer(cur.left);
			}
			if (cur.right != null) {
				q.offer(cur.right);
			}

			if (len == 0) {
				len = q.size();
				cur.next = null;
			} else {
				cur.next = q.peek();
			}
		}
		return root;
	}

}
