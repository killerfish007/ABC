package leetcode;

import java.util.Stack;

public class P100_SameTree {

	public static void main(String[] args) {
		TreeNode p = new TreeNode(0);
		p.left = new TreeNode(-5);

		TreeNode q = new TreeNode(0);
		q.left = new TreeNode(-8);
		System.out.println(isSameTreeIterative(p, q));

	}

	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (p == null || q == null) {
			return false;
		} else if (p.val != q.val) {
			return false;
		}
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	public static boolean isSameTreeIterative(TreeNode p, TreeNode q) {
		Stack<TreeNode> s = new Stack<>();
		s.add(p);
		s.add(q);

		while (!s.isEmpty()) {
			TreeNode t1 = s.pop();
			TreeNode t2 = s.pop();
			if (t1 == null && t2 == null)
				continue;
			if (t1 == null || t2 == null)
				return false;
			if (t1.val != t2.val)
				return false;
			s.push(t1.left);
			s.push(t2.left);
			s.push(t1.right);
			s.push(t2.right);
		}
		return true;
	}

}
