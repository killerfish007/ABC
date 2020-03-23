package leetcode;

public class P98_ValidateBinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isValidBST(TreeNode root) {
		return isBSTHelper(root, null, null);
	}

	public boolean isBSTHelper(TreeNode node, Integer lowerLimit, Integer upperLimit) {
		if (node == null)
			return true;
		if ((lowerLimit != null && node.val <= lowerLimit) || (upperLimit != null && upperLimit <= node.val))
			return false;
		return isBSTHelper(node.left, lowerLimit, node.val) && isBSTHelper(node.right, node.val, upperLimit);
	}
}
