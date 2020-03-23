package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P145_BinaryTreePostorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if (root == null)
			return results;
		results.addAll(postorderTraversal(root.left));
		results.addAll(postorderTraversal(root.right));
		results.add(root.val);
		return results;
	}

	public List<Integer> postOrderTraversalInterative(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if (root == null)
			return results;
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		TreeNode prev = null;
		while(!s.isEmpty()) {
			TreeNode curr = s.peek();
			if(prev==null || prev.left==curr || prev.right==curr) {
				if(curr.left!=null) {
					s.push(curr.left);
				} else if(curr.right!=null) {
					s.push(curr.right);
				}
			} else if(curr.left==prev) {
				if(curr.right!=null) {
					s.push(curr.right);
				}
			}else {
				results.add(curr.val);
				s.pop();
			}
			prev = curr;
		}
		return results;
	}
}
