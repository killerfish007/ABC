package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class P103_BinaryTreeZigzagLevelOrderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		List<Integer> cur = new ArrayList<>();
		boolean leftToRight = true;
		while (!q.isEmpty()) {
			TreeNode tmp = q.poll();
			if (tmp != null) {
				cur.add(tmp.val);
				if (tmp.left != null) {
					q.offer(tmp.left);
				}
				if (tmp.right != null) {
					q.offer(tmp.right);
				}

			} else {
				if (leftToRight) {
					res.add(new ArrayList<>(cur));
					cur.clear();
				} else {
					Stack<Integer> s = new Stack<>();
					s.addAll(cur);
					cur.clear();
					while (!s.isEmpty()) {
						cur.add(s.pop());
					}
					res.add(new ArrayList<>(cur));
					cur.clear();
				}

				if (!q.isEmpty()) {
					q.offer(null);
					leftToRight = !leftToRight;
				}
			}
		}
		return res;
	}
}
