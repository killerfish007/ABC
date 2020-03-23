package leetcode;

public class P270_ClosestBinarySearchTreeValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int closestValue(TreeNode root, double target) {
		int result = 0;
		double min = Double.MAX_VALUE;
		while (root != null) {
			if (target > root.val) {
				double diff = Math.abs(target - root.val);
				if (diff < min) {
					min = diff;
					result = root.val;
				}
				root = root.right;
			} else if (target < root.val) {
				double diff = Math.abs(target - root.val);
				if (diff < min) {
					min = diff;
					result = root.val;
				}
				root = root.left;
			} else {
				return root.val;
			}
		}
		return result;
	}

}
