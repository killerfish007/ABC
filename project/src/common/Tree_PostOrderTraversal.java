package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_PostOrderTraversal {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		System.out.println(postOrderTraversal(root));
		System.out.println(postOrderTraversalIterative(root));
	}

	public static List<Integer> postOrderTraversal(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if (root == null)
			return results;
		results.addAll(postOrderTraversal(root.left));
		results.addAll(postOrderTraversal(root.right));
		results.add(root.val);
		return results;
	}

	public static List<Integer> postOrderTraversalIterative(TreeNode root) {

		List<Integer> results = new ArrayList<>();
		if (root == null)
			return results;
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		TreeNode prev = null;
		while (!s.isEmpty()) {
			TreeNode curr = s.peek();
			if (prev == null || prev.left == curr || prev.right == curr) {
				if (curr.left != null) {
					s.push(curr.left);
				} else if (curr.right != null) {
					s.push(curr.right);
				}
			} else if (curr.left == prev) {
				if (curr.right != null) {
					s.push(curr.right);
				}
			} else {
				results.add(curr.val);
				s.pop();
			}
			prev = curr;
		}
		return results;

	}

}
