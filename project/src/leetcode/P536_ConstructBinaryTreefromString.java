package leetcode;

import java.util.Stack;

public class P536_ConstructBinaryTreefromString {

	public static void main(String[] args) {
		String s = "4(2(3)(1))(6(5))";
		TreeNode root = str2Tree(s);
		printPreOrder(root);

	}

	public static void printPreOrder(TreeNode root) {
		if (root != null) {
			System.out.println(root.val);
			printPreOrder(root.left);
			printPreOrder(root.right);
		}
	}

	public static TreeNode str2Tree(String s) {
		return buildTree(s, 0, s.length() - 1);
	}

	public static TreeNode buildTree(String s, int si, int ei) {
		if (si > ei)
			return null;
		TreeNode root = new TreeNode(s.charAt(si) - '0');
		int index = -1; // Matching right ')' index
		if (si + 1 <= ei && s.charAt(si + 1) == '(') {
			index = findIndex(s, si + 1, ei);
		}
		if (index != -1) {
			root.left = buildTree(s, si + 2, index - 1);
			root.right = buildTree(s, index + 2, ei - 1);
		}
		return root;
	}

	public static int findIndex(String str, int si, int ei) {
		if (si > ei)
			return -1;
		Stack<Character> s = new Stack<>();
		for (int i = si; i <= ei; i++) {
			if (str.charAt(i) == '(') {
				s.push('(');
			} else if (str.charAt(i) == ')') {
				if (s.peek() == '(') {
					s.pop();
					if (s.isEmpty()) {
						return i;
					}
				}
			}

		}

		return -1;
	}

}
