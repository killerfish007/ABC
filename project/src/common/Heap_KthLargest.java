package common;

import java.util.Arrays;

public class Heap_KthLargest {

	public static void main(String[] args) {
		int[] nums = { 6, 5, 1, 2, 3, 4 };
		int[] nums2 = { 6, 5, 1, 2, 3, 4 };
		System.out.println(findKthLargest(nums, 2));
		System.out.println(kthSmallest(nums2, 3));
	}

	public static int findKthLargest(int[] nums, int k) {
		int n = nums.length;
		buildMaxHeap(nums, n - 1);
		for (int i = 0; i < k - 1; i++) {
			nums[0] = nums[n - 1];
			nums = Arrays.copyOf(nums, n - 1);
			n = nums.length;
			maxheapify(nums, n - 1, 0);
		}
		return nums[0];
	}

	public static void buildMaxHeap(int[] nums, int n) {
		int lastNonLeafIndex = (n - 1) / 2;
		for (int i = lastNonLeafIndex; i >= 0; i--) {
			maxheapify(nums, n, i);
		}
	}

	public static void maxheapify(int[] nums, int n, int i) {
		int largest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if (left <= n && nums[left] > nums[largest]) {
			largest = left;
		}
		if (right <= n && nums[right] > nums[largest]) {
			largest = right;
		}
		if (largest != i) {
			int t = nums[i];
			nums[i] = nums[largest];
			nums[largest] = t;
			maxheapify(nums, n, largest);
		}
	}

	public static int kthSmallest(int[] arr, int k) {
		int n = arr.length;
		buildMinHeap(arr, n);
		for (int i = 0; i < k - 1; i++) {
			arr[0] = arr[arr.length - 1];
			arr = Arrays.copyOf(arr, arr.length - 1);
			n = arr.length;
			heapify(arr, n, 0);
		}
		return arr[0];
	}

	public static void buildMinHeap(int[] arr, int n) {
		// last non leaf node index
		int lastNonLeafIndex = (n - 1) / 2;
		for (int i = lastNonLeafIndex; i >= 0; i--) {
			heapify(arr, n, i);
		}
	}

	public static void heapify(int[] arr, int n, int i) {
		int smallest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left < n && arr[left] < arr[smallest]) {
			smallest = left;
		}

		if (right < n && arr[right] < arr[smallest]) {
			smallest = right;
		}

		if (smallest != i) {
			int temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;

			heapify(arr, n, smallest);
		}
	}
}
