package leetcode;

public class P979_DistributeCoinsinBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int moves = 0;

	public int distributeCoins(TreeNode root) {
		dfs(root);
		return moves;
	}

	public int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int excessOfLeftSubTree = dfs(root.left);
		int excessOfRightSubTree = dfs(root.right);
		moves = moves + Math.abs(excessOfLeftSubTree) + Math.abs(excessOfRightSubTree);
		return root.val - 1 + excessOfLeftSubTree + excessOfRightSubTree;
	}

}
