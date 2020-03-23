package leetcode;

public class P109_ConvertSortedListtoBinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}
		return helper(head, null);
	}

	public TreeNode helper(ListNode head, ListNode tail) {
		if (head == tail) {
			return null;
		}
		ListNode slowPtr = head;
		ListNode fastPtr = head;

		while (fastPtr != tail && fastPtr.next != tail) {
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;
		}

		TreeNode root = new TreeNode(slowPtr.val);
		root.left = helper(head, slowPtr);
		root.right = helper(slowPtr.next, tail);

		return root;
	}

}
