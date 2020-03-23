package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P94_BinaryTreeInorderTraversal {

	public static void main(String[] args) {

	}

	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;
		result.addAll(inorderTraversal(root.left));
		result.add(root.val);
		result.addAll(inorderTraversal(root.right));
		return result;
	}

	public static List<Integer> inorderTraversalRecursive(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;
		TreeNode curr = root;
		Stack<TreeNode> s = new Stack<>();
		while (curr != null || !s.isEmpty()) {
			while (curr != null) {
				s.push(curr);
				curr = curr.left;
			}
			curr = s.pop();
			result.add(curr.val);
			curr = curr.right;
		}
		return result;
	}

}
