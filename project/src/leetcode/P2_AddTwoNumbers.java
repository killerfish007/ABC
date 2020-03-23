package leetcode;

public class P2_AddTwoNumbers {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(5);
		// l1.next = new ListNode(4);
		// l1.next.next = new ListNode(5);

		ListNode l2 = new ListNode(5);
		// l2.next = new ListNode(6);
		// l2.next.next = new ListNode(4);

		ListNode re = addTwoNumbers(l1, l2);
		while (re != null) {
			System.out.println(re.val);
			re = re.next;
		}

	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode re = new ListNode(0);
		ListNode curr = re;
		int carry = 0;
		while (l1 != null || l2 != null) {
			int d1 = l1 != null ? l1.val : 0;
			int d2 = l2 != null ? l2.val : 0;
			int sum = d1 + d2 + carry;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			l1 = l1 != null ? l1.next : null;
			l2 = l2 != null ? l2.next : null;
			curr = curr.next;
		}

		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return re.next;
	}
}
