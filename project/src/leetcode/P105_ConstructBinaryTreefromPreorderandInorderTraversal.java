package leetcode;

public class P105_ConstructBinaryTreefromPreorderandInorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(preorder, inorder, 0, 0, inorder.length - 1);
	}

	public TreeNode helper(int[] preorder, int[] inorder, int preIndex, int inStart, int inEnd) {
		if (preIndex > preorder.length - 1 || inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preIndex]);
		int inIndex = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}
		root.left = helper(preorder, inorder, preIndex + 1, inStart, inIndex - 1);
		root.right = helper(preorder, inorder, preIndex + 1 + inIndex - inStart, inIndex + 1, inEnd);
		return root;
	}

}
