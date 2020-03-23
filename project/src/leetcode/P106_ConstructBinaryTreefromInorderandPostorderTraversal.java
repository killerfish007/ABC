package leetcode;

public class P106_ConstructBinaryTreefromInorderandPostorderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return helper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
	}

	public TreeNode helper(int[] inorder, int[] postorder, int postIndex, int inStart, int inEnd) {
		if (postIndex < 0 || inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[postIndex]);
		int inIndex = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}
		root.left = helper(inorder, postorder, (postIndex - 1) - (inEnd - inIndex), inStart, inIndex - 1);
		root.right = helper(inorder, postorder, postIndex - 1, inIndex + 1, inEnd);

		return root;
	}
}
