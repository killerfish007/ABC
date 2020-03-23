package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P102_BinaryTreeLevelOrderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> results = new ArrayList<>();
		if(root ==null) {
			return results;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		List<Integer> curr = new ArrayList<>();
		while(!q.isEmpty()) {
			TreeNode tmp = q.poll();
			if(tmp!=null) {
				curr.add(tmp.val);
				if(tmp.left!=null) {
					q.add(tmp.left);
				}
				if(tmp.right!=null) {
					q.add(tmp.right);
				}
			}else {
				results.add(new ArrayList<>(curr));
				curr.clear();
				if(!q.isEmpty()) {
					q.add(null);
				}
			}
		}
		return results;
	}

}
