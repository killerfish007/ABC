package leetcode;

public class P4_MedianofTwoSortedArrays {

	public static void main(String[] args) {
		int[] nums1 = { 1, 3 };
		int[] nums2 = { 2 };
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int sum = nums1.length + nums2.length;
		int index = sum / 2;
		int[] ar = new int[index + 1];
		boolean isEven = (sum % 2 == 0) ? true : false;
		int a = 0, b = 0, c = 0;
		while (c <= index) {
			int p1 = a < nums1.length ? nums1[a] : Integer.MAX_VALUE;
			int p2 = b < nums2.length ? nums2[b] : Integer.MAX_VALUE;
			if (p1 <= p2) {
				ar[c] = nums1[a++];
			} else {
				ar[c] = nums2[b++];
			}
			c++;
		}
		if (isEven) {
			return (ar[ar.length - 1] + ar[ar.length - 2] + 0d) / 2;
		} else {
			return 0d + ar[ar.length - 1];
		}
	}

}
