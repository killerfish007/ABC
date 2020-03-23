package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_InOrderTraversal {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		System.out.println(inOrderTraversal(root));
		System.out.println(inOrderTraversalIterative(root));
	}

	public static List<Integer> inOrderTraversal(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if (root == null)
			return results;
		results.addAll(inOrderTraversal(root.left));
		results.add(root.val);
		results.addAll(inOrderTraversal(root.right));
		return results;
	}

	public static List<Integer> inOrderTraversalIterative(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if (root == null)
			return results;
		Stack<TreeNode> s = new Stack<>();
		TreeNode curr = root;
		while (!s.isEmpty() || curr != null) {
			while (curr != null) {
				s.add(curr);
				curr = curr.left;
			}

			curr = s.pop();
			results.add(curr.val);
			curr = curr.right;
		}
		return results;
	}

}
