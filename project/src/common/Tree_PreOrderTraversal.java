package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_PreOrderTraversal {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		System.out.println(preOrderTraversal(root));
		System.out.println(preOrderTraversalIterative(root));
	}

	public static List<Integer> preOrderTraversal(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if (root == null)
			return results;
		results.add(root.val);
		results.addAll(preOrderTraversal(root.left));
		results.addAll(preOrderTraversal(root.right));
		return results;
	}

	public static List<Integer> preOrderTraversalIterative(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if (root == null)
			return results;
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		while (!s.isEmpty()) {
			TreeNode curr = s.pop();
			results.add(curr.val);
			if (curr.right != null) {
				s.push(curr.right);
			}
			if (curr.left != null) {
				s.push(curr.left);
			}
		}
		return results;
	}

}
