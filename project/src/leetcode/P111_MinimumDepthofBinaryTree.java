package leetcode;

public class P111_MinimumDepthofBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int minDepth(TreeNode root) {
		if(root == null) {
			return 0;
		}
		if(root.left!=null && root.right!=null) {
			return Math.min(minDepth(root.left), minDepth(root.right))+1;
		}
		if(root.left==null) {
			return minDepth(root.right)+1;
		} else {
			return minDepth(root.left)+1;
		}
	}
}
