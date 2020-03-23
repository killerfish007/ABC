package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P199_BinaryTreeRightSideView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> rv = new ArrayList<>();
		if (root == null)
			return rv;
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
				int r = cur.get(cur.size() - 1);
				rv.add(r);
				cur.clear();
				if (!q.isEmpty()) {
					q.offer(null);
				}
			}

		}
		return rv;
	}

}
