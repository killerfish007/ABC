package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P107_BinaryTreeLevelOrderTraversalII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		List<Integer> cur = new ArrayList<>();
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
				res.add(0, new ArrayList<>(cur));
				cur.clear();
				if (!q.isEmpty()) {
					q.offer(null);
				}
			}

		}
		return res;
	}

}
