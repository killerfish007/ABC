package leetcode;

import java.util.HashSet;
import java.util.Set;

public class P671_SecondMinimumNodeInaBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void dfs(TreeNode root, Set<Integer> uniques) {
		if (root != null) {
			uniques.add(root.val);
			dfs(root.left, uniques);
			dfs(root.right, uniques);
		}
	}

	public int findSecondMinimumValue(TreeNode root) {
		Set<Integer> uniques = new HashSet<Integer>();
		dfs(root, uniques);

		int min1 = root.val;
		long ans = Long.MAX_VALUE;
		for (int v : uniques) {
			if (min1 < v && v < ans)
				ans = v;
		}
		return ans < Long.MAX_VALUE ? (int) ans : -1;
	}
}
