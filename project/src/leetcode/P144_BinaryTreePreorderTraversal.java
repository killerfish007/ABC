package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P144_BinaryTreePreorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if(root == null)
			return results;
		results.add(root.val);
		results.addAll(preorderTraversal(root.left));
		results.addAll(preorderTraversal(root.right));
		return results;
	}
	
	public List<Integer> preorderTraversalIterative(TreeNode root) {
		List<Integer> results = new ArrayList<>();
		if(root == null)
			return results;
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		while(!s.isEmpty()) {
			TreeNode curr = s.pop();
			results.add(curr.val);
			if(curr.right!=null) {
				s.push(curr.right);
			}
			if(curr.left!=null) {
				s.push(curr.left);
			}
		}
		return results;
	}
}
