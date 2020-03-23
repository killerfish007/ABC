package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class P236_LowestCommonAncestorofaBinaryTree {

	public static void main(String[] args) {

	}

	TreeNode lca = null;

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		helper(root, p, q);
		return lca;
	}

	public boolean helper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return false;
		int mid = (root == p || root == q) ? 1 : 0;
		int left = helper(root.left, p, q) ? 1 : 0;
		int right = helper(root.right, p, q) ? 1 : 0;
		if (mid + left + right >= 2)
			lca = root;
		return mid + left + right > 0;
	}

	public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
		Stack<TreeNode> s = new Stack<>();
		Map<TreeNode, TreeNode> childParentMap = new HashMap<>();
		s.push(root);
		childParentMap.put(root, null);
		// Iterate until we find both the nodes p and q
		while (!childParentMap.containsKey(p) || !childParentMap.containsKey(q)) {
			TreeNode node = s.pop();
			// While traversing the tree, keep saving the parent pointers.
			if (node.left != null) {
				s.add(node.left);
				childParentMap.put(node.left, node);
			}
			if (node.right != null) {
				s.add(node.right);
				childParentMap.put(node.right, node);
			}
		}

		// Ancestors set() for node p.
		Set<TreeNode> ancestors = new HashSet<>();
		// Process all ancestors for node p using parent pointers.
		while (p != null) {
			ancestors.add(p);
			p = childParentMap.get(p);
		}

		// The first ancestor of q which appears in
		// p's ancestor set() is their lowest common ancestor.
		while (!ancestors.contains(q))
			q = childParentMap.get(q);

		return q;
	}
}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}