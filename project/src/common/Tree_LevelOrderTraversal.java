package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree_LevelOrderTraversal {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		System.out.println(levelOrderTraversalIterative(root));
	}

	public static List<List<Integer>> levelOrderTraversalIterative(TreeNode root) {

		List<List<Integer>> results = new ArrayList<>();
		if (root == null) {
			return results;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		List<Integer> curr = new ArrayList<>();
		while (!q.isEmpty()) {
			TreeNode tmp = q.poll();
			if (tmp != null) {
				curr.add(tmp.val);
				if (tmp.left != null) {
					q.add(tmp.left);
				}
				if (tmp.right != null) {
					q.add(tmp.right);
				}
			} else {
				results.add(new ArrayList<>(curr));
				curr.clear();
				if (!q.isEmpty()) {
					q.add(null);
				}
			}
		}
		return results;

	}

}
