package leetcode;

import java.util.Stack;

public class P226_InvertBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public TreeNode invertTree(TreeNode root) {
        if(root==null)
            return null;
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
    
    public TreeNode invertTreeIterative(TreeNode root) {
        if(root==null)
            return null;
        Stack<TreeNode> s = new Stack<>();
        s.add(root);
        while(!s.isEmpty()){
            TreeNode curr = s.pop();
            TreeNode tmp = curr.left;
            curr.left = curr.right;
            curr.right = tmp;
            if(curr.left!=null) s.add(curr.left);
            if(curr.right!=null) s.add(curr.right);
        }
        return root;
    }

}
