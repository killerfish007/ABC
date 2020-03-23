package leetcode;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class TestClass {
	public static void main(String[] args) {
		int[] nums = {10, 20, 30, 50, 10, 70, 30};
		printMaximumOfMinsOfSubarraySizeK(nums, 7);
	}
	
	
	
	
	public static void printMaximumOfMinsOfSubarraySizeK(int[] nums, int k) {
 
	    int max = Integer.MIN_VALUE;
	 
	    LinkedList<Integer> deque = new LinkedList<Integer>();
	    for(int i=0; i<nums.length; i++){
	        if(!deque.isEmpty()&&deque.peekFirst()==i-k) 
	            deque.poll();
	 
	        while(!deque.isEmpty()&&nums[deque.peekLast()]>nums[i]){
	            deque.removeLast();
	        }    
	 
	        deque.offer(i);
	 
	        if(i+1>=k) {
	        	max = Math.max(max, nums[deque.peek()]);
	        }
	    }
	 
	    System.out.println(max);
	}

	static void LargestTripletMultiplication(int arr[], int n) {
		// call a priority queue
		PriorityQueue<Integer> q = new PriorityQueue(Collections.reverseOrder());

		// traversing the array
		for (int i = 0; i < n; i++) {
			// pushing arr[i] in array
			q.add(arr[i]);

			// if less than three elements are present
			// in array print -1
			if (q.size() < 3)
				System.out.println("-1");
			else {
				// pop three largest elements
				int x = q.poll();
				int y = q.poll();
				int z = q.poll();

				// Reinsert x, y, z in priority_queue
				int ans = x * y * z;
				System.out.println(ans);
				q.add(x);
				q.add(y);
				q.add(z);
			}
		}
	}

	public static int maxProduct(final int[] A) {
		int minV = A[0];
		int maxV = A[0];
		int maxP = A[0];
		for (int i = 1; i < A.length; i++) {
			if (A[i] < 0) {
				int t = minV;
				minV = maxV;
				maxV = t;
			}
			maxV = Math.max(A[i], A[i] * maxV);
			minV = Math.min(A[i], A[i] * minV);

			maxP = Math.max(maxP, maxV);
		}
		return maxP;
	}

	public static ArrayList<Integer> getMode2(ArrayList<Integer> a, ArrayList<ArrayList<Integer>> b) {
		Map<Integer, Integer> map = new TreeMap<>(Comparator.comparingInt(o -> o));
		Map<Integer, List<Integer>> maxMap = new HashMap<>();
		Map<Integer, Integer> array = new HashMap<>();
		int maxCount = Integer.MIN_VALUE;
		for (int i = 0; i < a.size(); i++) {
			array.put(i, a.get(i));
		}
		for (int i = 0; i < a.size(); i++) {
			Integer count = map.get(a.get(i));
			if (count == null)
				count = 0;
			map.put(a.get(i), ++count);
			List<Integer> list = maxMap.get(count);
			if (list == null)
				list = new LinkedList<>();
			list.add(a.get(i));
			maxMap.put(count, list);
			maxCount = Math.max(count, maxCount);
		}
		List prev = Arrays.asList(new Integer[] { 0, 0 });
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < b.size(); i++) {
			ArrayList<Integer> updateList = b.get(i);
			if (prev.get(0) != updateList.get(0) || prev.get(1) != updateList.get(1)) {
				Integer firstIndex = array.get(updateList.get(0) - 1);
				Integer count = map.get(firstIndex);
				if (count == null)
					count = 0;
				map.put(firstIndex, --count);
				count = map.get(updateList.get(1));
				if (count == null)
					count = 0;
				map.put(updateList.get(1), ++count);
				array.put(updateList.get(0) - 1, updateList.get(1));
				maxCount = Math.max(maxCount, count);
			}
			result.add(getMax(map, maxCount));
			prev = updateList;
		}

		return result;
	}

	public static Integer getMax(Map<Integer, Integer> map, int maxCount) {
		int res = 0;
		for (Integer i : map.values()) {
			if (i == maxCount) {
				res++;
			}
		}

		return res;
	}

	public static ArrayList<Integer> getMode(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i : A) {
			if (map.containsKey(i))
				map.put(i, map.get(i) + 1);
			else
				map.put(i, 1);
		}

		for (ArrayList<Integer> update : B) {
			int index = update.get(0);
			int num = update.get(1);

			int toUpdate = A.get(index - 1);

			if (map.get(toUpdate) != null)
				map.put(toUpdate, map.get(toUpdate) - 1);
			A.set(index - 1, num);
			if (map.containsKey(num))
				map.put(num, map.get(num) + 1);
			else
				map.put(num, 1);

			int modeindex = 0;
			int mode = -1;
			for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
				if (modeindex == (int) pair.getValue() && mode > (int) pair.getKey())
					mode = (int) pair.getKey();
				if (modeindex < (int) pair.getValue()) {
					modeindex = (int) pair.getValue();
					mode = (int) pair.getKey();
				}
			}

			result.add(mode);
		}
		return result;

	}

	public int mode(ArrayList<Integer> A, HashMap<Integer, Integer> map) {
		int modeindex = 0;
		int mode = -1;
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (modeindex == (int) pair.getValue() && mode > (int) pair.getKey())
				mode = (int) pair.getKey();
			if (modeindex < (int) pair.getValue()) {
				modeindex = (int) pair.getValue();
				mode = (int) pair.getKey();
			}
			it.remove();
		}
		return mode;
	}

	public static int arrange(String A, int B) {
		int n = A.length();
		if (B > n)
			return -1;
		int[][] dp = new int[B + 1][n + 1];
		horse[] horses = getNum(A);
		for (int i = 1; i <= n; i++) {
			dp[1][i] = horses[i].b * horses[i].w;
		}
		for (int i = 1; i <= B; i++) {
			dp[i][1] = 0;
		}

		for (int i = 2; i <= B; i++) {
			for (int j = 2; j <= n; j++) {
				int best = Integer.MAX_VALUE;
				for (int p = 1; p < j; p++) {
					int black = horses[j].b - horses[p].b;
					int white = horses[j].w - horses[p].w;
					best = Math.min(best, dp[i - 1][p] + black * white);
				}
				dp[i][j] = best;
			}
		}
		return dp[B][n];
	}

	public static horse[] getNum(String A) {
		int n = A.length();
		int b = 0, w = 0;
		horse[] num = new horse[n + 1];
		for (int i = 0; i < n; i++) {
			if (A.charAt(i) == 'W')
				w++;
			if (A.charAt(i) == 'B')
				b++;
			num[i + 1] = new horse(b, w);
		}
		return num;
	}

	public static int[][] avgset(int[] A) {
		if (A.length == 0) {
			return new int[0][0];
		}
		int n = A.length;
		int s = 0;
		for (int i : A) {
			s += i;
		}
		for (int n1 = 1; n1 < n; n1++) {
			int s1 = (s / n) * n1;
			if (isSubSetPossible(A, 0, s1, n1)) {
				System.out.println(n1 + " " + s1);
				break;
			}
		}
		return null;
	}

	private static boolean isSubSetPossible(int[] a, int i, int s1, int n1) {
		if (n1 == 0 && s1 == 0) {
			return true;
		} else if ((n1 == 0 && s1 != 0) || i >= a.length) {
			return false;
		}
		return isSubSetPossible(a, i + 1, s1 - a[i], n1 - 1) || isSubSetPossible(a, i + 1, s1, n1);
	}

	private static ArrayList<Integer> array;
	private static boolean[][][] dp;
	private static ArrayList<Integer> indexSetA;

	public static ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> a) {
		if (a == null || a.isEmpty()) {
			return new ArrayList<>();
		}

		array = a;
		Collections.sort(array);

		int sum = 0;
		for (int element : array) {
			sum += element;
		}

		int n = array.size();

		// memoization table by three states : (index, sum of setA, size of setA)
		dp = new boolean[n][1 + sum][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 1 + sum; j++) {
				for (int k = 0; k < n; k++) {
					dp[i][j][k] = true;
				}
			}
		}

		indexSetA = new ArrayList<>();

		// iterate for third state : size of setA which varies from 1 to n-1
		for (int sizeA = 1; sizeA < n; sizeA++) {
			if ((sum * sizeA) % n != 0) {
				continue;
			}

			int sumA = (sum * sizeA) / n;

			if (isPartitionPossible(0, sumA, sizeA) == true) {
				break;
			}
		}

		return generatePartitions();
	}

	private static boolean isPartitionPossible(final int index, final int sumA, final int sizeA) {
		if (sizeA == 0) {
			return sumA == 0;
		}

		int n = array.size();
		if (index >= n) {
			return false;
		}

		if (dp[index][sumA][sizeA] == false) {
			return false;
		}

		if (sumA >= array.get(index)) {
			// include the current index i.e. include the current element in setA
			indexSetA.add(index);
			if (isPartitionPossible(index + 1, sumA - array.get(index), sizeA - 1) == true) {
				return true;
			}
			indexSetA.remove(indexSetA.size() - 1);
		}

		if (isPartitionPossible(index + 1, sumA, sizeA)) {
			// skip the current index i.e. don't include the current element in setA
			return true;
		}

		dp[index][sumA][sizeA] = false;
		return dp[index][sumA][sizeA];
	}

	private static ArrayList<ArrayList<Integer>> generatePartitions() {
		int i = 0, j = 0;
		int sizeA = indexSetA.size();
		int n = array.size();

		if (sizeA == n || sizeA == 0) { // no solution exists
			return new ArrayList<>();
		}

		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		result.add(new ArrayList<>());

		// index i is used to iterate over all elements and index j is used to iterate
		// over indexSetA
		while (i < n && j < sizeA) {
			if (i == indexSetA.get(j)) {
				result.get(0).add(array.get(i));
				j++;
			} else {
				result.get(1).add(array.get(i));
			}
			i++;
		}

		while (i < n) {
			result.get(1).add(array.get(i));
			i++;
		}

		return result;
	}

	public static int majorityElement(int[] nums) {
		int count = 1;
		int num = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (count == 0) {
				count = 1;
				num = nums[i];
			} else if (num == nums[i]) {
				count++;
			} else {
				count--;
			}
		}
		return num;
	}

	public static ArrayList<TreeNode> generateTrees(int a) {
		return construct(1, a);
	}

	public static ArrayList<TreeNode> construct(int s, int e) {
		ArrayList<TreeNode> list = new ArrayList<>();
		if (s > e) {
			list.add(null);
			return list;
		}
		for (int i = s; i <= e; i++) {
			ArrayList<TreeNode> leftNodes = construct(s, i - 1);
			ArrayList<TreeNode> rightNodes = construct(i + 1, e);
			for (int j = 0; j < leftNodes.size(); j++) {
				TreeNode left = leftNodes.get(j);
				for (int k = 0; k < rightNodes.size(); k++) {
					TreeNode right = rightNodes.get(k);
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					list.add(root);
				}
			}
		}
		return list;
	}

	public static int maximumGap(final int[] A) {
		int maxD = -1;
		int[] maxA = new int[A.length];
		maxA[A.length - 1] = A[A.length - 1];
		for (int i = A.length - 2; i >= 0; i--) {
			maxA[i] = Math.max(A[i], maxA[i + 1]);
		}
		int i = 0, j = 0;
		while (i < A.length && j < A.length) {
			if (A[i] < maxA[j]) {
				maxD = Math.max(maxD, j - i);
				j++;
			} else {
				i++;
			}
		}
		return maxD;
	}

	public int repeatedNumber(final List<Integer> a) {
		for (int i = 0; i < a.size(); i++) {
			int index = Math.abs(a.get(i));
			int element = a.get(index - 1);
			if (element < 0) {
				return index;
			} else {
				a.set(index - 1, -element);
			}
		}
		return -1;
	}

	public static int firstMissingPositive(int[] arr) {
		int size = arr.length;
		int shift = segregate(arr, size);
		int arr2[] = new int[size - shift];
		int j = 0;
		for (int i = shift; i < size; i++) {
			arr2[j] = arr[i];
			j++;
		}
		return findMissingPositive(arr2, j);

	}

	public static int findMissingPositive(int arr[], int size) {
		int i;

		for (i = 0; i < size; i++) {
			int x = Math.abs(arr[i]);
			if (x - 1 < size && arr[x - 1] > 0)
				arr[x - 1] = -arr[x - 1];
		}

		for (i = 0; i < size; i++)
			if (arr[i] > 0)
				return i + 1;

		return size + 1;
	}

	public static int segregate(int arr[], int size) {
		int j = 0, i;
		for (i = 0; i < size; i++) {
			if (arr[i] <= 0) {
				int temp;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
		}

		return j;
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

	public static int numIslands(char[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1' && !visited[i][j]) {
					count++;
					visited[i][j] = true;
					dfs(grid, visited, i, j);
				}
			}
		}
		return count;
	}

	public static void dfs(char[][] grid, boolean[][] visited, int row, int col) {
		int[] rowAdj = { -1, 0, 1, 0 };
		int[] colAdj = { 0, 1, 0, -1 };
		for (int i = 0; i < 4; i++) {
			int nRow = row + rowAdj[i];
			int nCol = col + colAdj[i];
			if (isValid(nRow, nCol, grid) && grid[nRow][nCol] == '1' && !visited[nRow][nCol]) {
				visited[nRow][nCol] = true;
				dfs(grid, visited, nRow, nCol);
			}
		}
	}

	public static boolean isValid(int row, int col, char[][] grid) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
			return false;
		}
		return true;
	}

	public static String reverseWords(String a) {
		a = a.trim();
		char[] ar = a.toCharArray();
		int i = 0;
		for (int j = 0; j < ar.length; j++) {
			if (ar[j] == ' ') {
				reverse(ar, i, j - 1);
				i = j + 1;
			}
		}
		reverse(ar, i, ar.length - 1);
		reverse(ar, 0, ar.length - 1);
		return new String(ar);
	}

	public static void reverse(char[] s, int i, int j) {
		while (i < j) {
			char t = s[i];
			s[i] = s[j];
			s[j] = t;
			i++;
			j--;
		}
	}

	public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<>();
		List<String> temp = new ArrayList<>();
		boolean[] visited = new boolean[wordList.size()];
		backtrack(beginWord, endWord, wordList, temp, res, visited);
		return res;
	}

	public static void backtrack(String beginWord, String endWord, List<String> wordList, List<String> temp,
			List<List<String>> res, boolean[] visited) {
		temp.add(beginWord);
		if (beginWord.equals(endWord)) {
			if (temp.size() < minSize) {
				res.clear();
				minSize = temp.size();
			}
			if (temp.size() == minSize && !res.contains(temp))
				res.add(new ArrayList<>(temp));
		} else {
			for (int i = 0; i < wordList.size(); i++) {
				if (!visited[i] && isAdj(wordList.get(i), beginWord)) {
					visited[i] = true;
					backtrack(wordList.get(i), endWord, wordList, temp, res, visited);
					visited[i] = false;
				}
			}
		}
		temp.remove(temp.size() - 1);
	}

	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		boolean[] visited = new boolean[wordList.size()];
		return dfs(beginWord, endWord, wordList, visited, 0);
	}

	private static int dfs(String beginWord, String endWord, List<String> wordList, boolean[] visited, int count) {
		if (beginWord.equals(endWord)) {
			return count;
		} else {
			for (int i = 0; i < wordList.size(); i++) {
				if (!visited[i] && isAdjacent(beginWord, wordList.get(i))) {
					visited[i] = true;
					return dfs(wordList.get(i), endWord, wordList, visited, count + 1);
				}
			}
			return 0;
		}

	}

	private static boolean isAdjacent(String s1, String s2) {
		int count = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				count++;
				if (count > 1) {
					return false;
				}
			}
		}
		return count == 1 ? true : false;
	}

	public static int maxProfit(int[] prices) {
		int[][] dp = new int[3][prices.length];
		for (int i = 1; i <= 2; i++) {
			int maxD = Integer.MIN_VALUE;
			for (int j = 1; j < prices.length; j++) {
				maxD = Math.max(maxD, dp[i - 1][j - 1] - prices[j - 1]);
				dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxD);
			}
		}
		return dp[2][prices.length - 1];
	}

	public static List<Integer> getRow(int rowIndex) {
		List<Integer> res = new ArrayList<>();
		for (int c = 0; c <= rowIndex; c++) {
			if (c == 0 || c == rowIndex) {
				res.add(1);
			} else {
				res.add(find(rowIndex, c));
			}
		}
		return res;
	}

	public static int find(int r, int c) {
		if (c == 1) {
			return r;
		} else if (c == r) {
			return 1;
		}
		return find(r - 1, c - 1) + find(r - 1, c);
	}

	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		List<Integer> tmp = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		while (!q.isEmpty()) {
			TreeNode node = q.poll();
			if (node != null) {
				tmp.add(node.val);
				if (node.left != null) {
					q.add(node.left);
				}
				if (node.right != null) {
					q.add(node.right);
				}
			} else {
				if (!q.isEmpty()) {
					q.add(null);
				}
				res.add(new ArrayList<>(tmp));
				tmp.clear();
			}
		}
		return res;
	}

	public static boolean isValidBST(TreeNode root) {
		return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static boolean helper(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		} else if (root.val <= min || root.val >= max) {
			return false;
		} else {
			return helper(root.left, min, root.val) && helper(root.right, root.val, max);
		}
	}

	public static List<Integer> grayCode(int n) {
		List<Integer> res = new ArrayList<>();
		int totalGrayCodes = (int) Math.pow(2, n); // 1>>n
		for (int i = 0; i < totalGrayCodes; i++) {
			res.add(i ^ (i / 2));
		}
		return res;
	}

	public void setZeroes(ArrayList<ArrayList<Integer>> a) {
		boolean isRow = false;
		boolean isCol = false;
		int r = a.size();
		int c = a.get(0).size();
		if (a.get(0).get(0) == 0) {
			isRow = true;
			isCol = true;
		}
		if (!isRow) {
			for (int i = 1; i < c; i++) {
				if (a.get(0).get(i) == 0) {
					isRow = true;
					break;
				}
			}
		}
		if (!isCol) {
			for (int i = 1; i < r; i++) {
				if (a.get(i).get(0) == 0) {
					isCol = true;
					break;
				}
			}
		}
		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				if (a.get(i).get(i) == 0) {
					a.get(0).set(j, 0);
					a.get(i).set(0, 0);
				}
			}
		}

		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				if (a.get(i).get(0) == 0 || a.get(0).get(j) == 0) {
					a.get(i).set(j, 0);
				}
			}
		}
		if (isRow) {
			for (int i = 0; i < c; i++) {
				a.get(0).set(i, 0);
			}
		}
		if (isCol) {
			for (int i = 0; i < r; i++) {
				a.get(i).set(0, 0);
			}
		}
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		if (strs.length == 0) {
			return new ArrayList<>();
		}
		Map<String, List<String>> map = new HashMap<>();
		for (String s : strs) {
			char[] c = s.toCharArray();
			Arrays.sort(c);
			String key = String.valueOf(c);
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(s);
		}

		return new ArrayList<>(map.values());
	}

	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - 1 - i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = temp;
			}
		}
	}

	public static int trap(int[] height) {
		int total = 0;
		int l = 0;
		int r = height.length - 1;
		int lm = height[l];
		int rm = height[r];
		while (l < r) {
			if (height[l] < height[r]) {
				if (lm > height[l]) {
					total += lm - height[l];
				} else {
					lm = height[l];
				}
				l++;
			} else {
				if (rm > height[r]) {
					total += rm - height[r];
				} else {
					rm = height[r];
				}
				r--;
			}
		}
		return total;
	}

	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		if (lists.length == 1) {
			return lists[0];
		}
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
		for (int i = 0; i < lists.length; i++) {
			pq.add(lists[i]);
		}
		while (!pq.isEmpty()) {
			ListNode t = pq.poll();
			if (t.next != null) {
				pq.add(t.next);
			}
			cur.next = t;
			cur = cur.next;
		}
		return dummy.next;
	}

	public static boolean isValid(String s) {
		Stack<Character> st = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				st.push(c);
			} else if (c == ')') {
				if (!st.isEmpty() && st.peek() == '(') {
					st.pop();
				} else {
					return false;
				}
			} else if (c == ']') {
				if (!st.isEmpty() && st.peek() == '[') {
					st.pop();
				} else {
					return false;
				}
			} else if (c == '}') {
				if (!st.isEmpty() && st.peek() == '{') {
					st.pop();
				} else {
					return false;
				}
			}
		}
		if (st.isEmpty()) {
			return true;
		}
		return false;
	}

	public static List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		if (n < 0) {
			return res;
		}
		backtrack(n, "", res, 0, 0);
		return res;
	}

	public static void backtrack(int n, String s, List<String> res, int l, int r) {
		if (l == n && r == n) {
			res.add(s);
		} else {
			if (l < n) {
				backtrack(n, s + "(", res, l + 1, r);
			}
			if (l > r) {
				backtrack(n, s + ")", res, l, r + 1);
			}
		}
	}

	public static List<String> letterCombinations(String digits) {
		Map<Character, String> map = new HashMap<>();
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");

		List<String> res = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return res;
		}
		backtrack(digits, "", 0, map, res);
		return res;
	}

	public static void backtrack(String digits, String sb, int index, Map<Character, String> map, List<String> res) {
		if (sb.length() == digits.length()) {
			res.add(sb);
		} else {
			char c = digits.charAt(index);
			String t = map.get(c);
			for (int i = 0; i < t.length(); i++) {
				backtrack(digits, sb + t.charAt(i), index + 1, map, res);
			}
		}
	}

	public static int myAtoi(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int i = 0;
		while (i < s.length() && s.charAt(i) == ' ') {
			i++;
		}
		boolean flag = false;
		if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
			if (s.charAt(i) == '-') {
				flag = true;
			}
			i++;
		}
		double res = 0;
		while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
			res = res * 10 + (s.charAt(i) - '0');
			i++;
		}
		if (flag) {
			res = -res;
		}
		if (res >= Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (res <= Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return (int) res;
	}

	public static int minPathSum(int[][] A) {
		int m = A.length;
		int n = A[0].length;
		int[][] dp = new int[m][n];
		int sum = 0;
		for (int i = 0; i < m; i++) {
			dp[i][0] = sum + A[i][0];
			sum = dp[i][0];
		}
		sum = 0;
		for (int j = 0; j < n; j++) {
			dp[0][j] = sum + A[0][j];
			sum = dp[0][j];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = A[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
			}
		}
		return dp[m - 1][n - 1];
	}

	public static int minDistance(String A, String B) {
		int[][] dp = new int[B.length() + 1][A.length() + 1];
		for (int j = 0; j <= A.length(); j++) {
			dp[0][j] = j;
		}
		for (int i = 0; i <= B.length(); i++) {
			dp[i][0] = i;
		}
		for (int i = 1; i <= B.length(); i++) {
			for (int j = 1; j <= A.length(); j++) {
				if (A.charAt(j - 1) == B.charAt(i - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
				}
			}
		}
		return dp[B.length()][A.length()];
	}

	public static int cnttrue(String A) {
		return True(0, A.length() - 1, A);
	}

	static Map<int[], Integer> trueMap = new HashMap<>();
	static Map<int[], Integer> falseMap = new HashMap<>();
	static int mod = 1003;

	private static int True(int i, int j, String exp) {
		if (i > j)
			return 0;
		if (i == j)
			return exp.charAt(i) == 'T' ? 1 : 0;
		int[] a = { i, j };
		if (trueMap.containsKey(a)) {
			return trueMap.get(a);
		}
		int ans = 0;
		for (int k = i; k < j; k++) {
			char c = exp.charAt(k);
			if (c == '&') {
				ans += (True(i, k - 1, exp) * True(k + 1, j, exp));
			}
			if (c == '|') {
				ans += (True(i, k - 1, exp) * True(k + 1, j, exp) + True(i, k - 1, exp) * False(k + 1, j, exp)
						+ False(i, k - 1, exp) * True(k + 1, j, exp));
			}
			if (c == '^') {
				ans += (True(i, k - 1, exp) * False(k + 1, j, exp) + False(i, k - 1, exp) * True(k + 1, j, exp));
			}
			ans = ans % mod;
		}
		trueMap.put(a, ans);
		return ans;
	}

	private static int False(int i, int j, String exp) {

		if (i > j)
			return 0;
		if (i == j)
			return exp.charAt(i) == 'F' ? 1 : 0;
		int[] a = { i, j };
		if (falseMap.containsKey(a)) {
			return falseMap.get(a);
		}
		int ans = 0;
		for (int k = i; k < j; k++) {
			char c = exp.charAt(k);
			if (c == '&') {
				ans += (False(i, k - 1, exp) * False(k + 1, j, exp) + True(i, k - 1, exp) * False(k + 1, j, exp)
						+ False(i, k - 1, exp) * True(k + 1, j, exp));
			}
			if (c == '|') {
				ans += (False(i, k - 1, exp) * False(k + 1, j, exp));
			}
			if (c == '^') {
				ans += (True(i, k - 1, exp) * True(k + 1, j, exp) + False(i, k - 1, exp) * False(k + 1, j, exp));
			}
			ans = ans % mod;
		}
		falseMap.put(a, ans);
		return ans;

	}

	public static int canJump(int[] A) {

		int max = 0;
		for (int i = 0; i < A.length; i++) {
			if (i > max) {
				return 1;
			}
			max = Math.max(max, i + A[i]);
		}

		return 0;

	}

	public static int climbStairs(int A) {
		int[] dp = new int[A + 1];
		Arrays.fill(dp, -1);
		return climbStairsHelper(A, dp);
	}

	private static int climbStairsHelper(int A, int[] dp) {
		if (A <= 0) {
			return 1;
		}
		if (dp[A] > -1) {
			return dp[A];
		}
		int totalWays = 0;
		if (A - 1 >= 0) {
			totalWays += climbStairsHelper(A - 1, dp);
		}
		if (A - 2 >= 0) {
			totalWays += climbStairsHelper(A - 2, dp);
		}
		dp[A] = totalWays;
		return dp[A];
	}

	public static int numDecodings(String A) {
		int[] dp = new int[A.length()];
		Arrays.fill(dp, -1);
		return decodeHelper(A, 0, dp);
	}

	private static int decodeHelper(String a, int i, int[] dp) {
		// Base Case
		if (i >= a.length()) {
			return 1;
		}

		if (dp[i] > -1) {
			return dp[i];
		}

		int totalWays = 0;
		if (i + 1 <= a.length()) {
			String s = a.substring(i, i + 1);
			if (isValid(s)) {
				totalWays += decodeHelper(a, i + 1, dp);
			}
		}
		if (i + 2 <= a.length()) {
			String s = a.substring(i, i + 2);
			if (isValid(s)) {
				totalWays += decodeHelper(a, i + 2, dp);
			}
		}
		dp[i] = totalWays;
		return dp[i];
	}

	private static boolean isValid1(String s) {
		if (s.startsWith("0")) {
			return false;
		}
		int n = Integer.parseInt(s);
		return n >= 1 && n <= 26;
	};

	public static ArrayList<ArrayList<Integer>> printAllSubSets(int set[], int n, int sum) {
		boolean[][] dp = new boolean[n + 1][sum + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = true;
		}
		for (int j = 1; j <= sum; j++) {
			dp[0][j] = false;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= sum; j++) {
				if (j < set[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else if (j >= set[i - 1]) {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
				}
			}
		}
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> p = new ArrayList<>();
		printAllSubSetsRec(set, n, sum, p, res, dp);
		return res;
	}

	private static void printAllSubSetsRec(int[] set, int i, int sum, ArrayList<Integer> p,
			ArrayList<ArrayList<Integer>> res, boolean[][] dp) {
		if (i == 0 && sum == 0) {
			res.add(new ArrayList<>(p));
			return;
		}

		if (dp[i - 1][sum]) {
			printAllSubSetsRec(set, i - 1, sum, p, res, dp);
		}

		if (sum - set[i - 1] >= 0 && dp[i - 1][sum - set[i - 1]]) {
			p.add(set[i - 1]);
			printAllSubSetsRec(set, i - 1, sum - set[i - 1], p, res, dp);
			p.remove(p.size() - 1);
		}
	}

	public static int longestSubsequenceLength(final int[] A) {
		int len = A.length;
		int[] incSubSeqLen = new int[len];
		Arrays.fill(incSubSeqLen, 1);
		for (int i = 1; i < len; i++) {
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j] && incSubSeqLen[i] < incSubSeqLen[j] + 1) {
					incSubSeqLen[i] = incSubSeqLen[j] + 1;
				}
			}
		}

		int[] decSubSeqLen = new int[len];
		Arrays.fill(decSubSeqLen, 1);
		for (int i = len - 2; i >= 0; i--) {
			for (int j = len - 1; j > i; j--) {
				if (A[i] > A[j] && decSubSeqLen[i] < decSubSeqLen[j] + 1) {
					decSubSeqLen[i] = decSubSeqLen[j] + 1;
				}
			}
		}

		int max = 0;
		for (int i = 0; i < decSubSeqLen.length; i++) {
			int x = incSubSeqLen[i] + decSubSeqLen[i] - 1;
			if (x > max) {
				max = x;
			}
		}
		return max;
	}

	static int minSize = Integer.MAX_VALUE;

	public static ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dictV) {
		dictV.add(end);
		ArrayList<ArrayList<String>> res = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<>();
		boolean[] visited = new boolean[dictV.size()];
		dfs(start, end, dictV, temp, res, visited);
		return res;

	}

	public static void dfs(String start, String end, ArrayList<String> dictV, ArrayList<String> temp,
			ArrayList<ArrayList<String>> res, boolean[] visited) {
		temp.add(start);
		if (start.equals(end)) {
			if (temp.size() < minSize) {
				res.clear();
				minSize = temp.size();
			}
			if (temp.size() <= minSize && !res.contains(temp))
				res.add(new ArrayList<>(temp));
		}
		for (int i = 0; i < dictV.size(); i++) {
			String d = dictV.get(i);
			if (isAdj(start, d) && !visited[i]) {
				visited[i] = true;
				dfs(d, end, dictV, temp, res, visited);
				visited[i] = false;
			}
		}
		temp.remove(temp.size() - 1);
	}

	public static int ladderLength(String start, String end, ArrayList<String> dictV) {
		dictV.add(end);
		Queue<APair> q = new LinkedList<>();
		q.add(new APair(start, 1));
		boolean[] visited = new boolean[dictV.size()];
		while (!q.isEmpty()) {
			APair p = q.poll();
			if (p.s.equals(end)) {
				return p.count;
			}
			for (int i = 0; i < dictV.size(); i++) {
				String d = dictV.get(i);
				if (isAdj(p.s, d) && !visited[i]) {
					visited[i] = true;
					q.add(new APair(d, p.count + 1));
				}
			}
		}

		return 0;
	}

	public static boolean isAdj(String a, String b) {
		int count = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				count++;
				if (count > 1)
					return false;
			}
		}
		return count == 1 ? true : false;
	}

	public static int knight1(int N, int M, int x1, int y1, int x2, int y2) {
		int[] dx = { -1, -2, -1, -2, 1, 2, 1, 2 };
		int[] dy = { -2, -1, 2, 1, -2, -1, 2, 1 };
		boolean[][] isVisited = new boolean[N + 1][M + 1];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x1, y1 });
		isVisited[x1][y1] = true;
		int moveCount = 0;
		while (!queue.isEmpty()) {
			int nodesAtCurrentBreadth = queue.size();
			for (int count = 0; count < nodesAtCurrentBreadth; count++) {
				int[] currPos = queue.remove();
				if (currPos[0] == x2 && currPos[1] == y2) {
					return moveCount;
				}
				for (int i = 0; i < dx.length; i++) {
					int rA = currPos[0] + dx[i];
					int cA = currPos[1] + dy[i];
					if (rA > 0 && rA <= N && cA > 0 && cA <= M && !isVisited[rA][cA]) {
						queue.add(new int[] { rA, cA });
						isVisited[rA][cA] = true;
					}
				}
			}
			moveCount++;
		}
		return -1;
	}

	public static int knight(int N, int M, int x1, int y1, int x2, int y2) {
		int[] dx = { -1, -2, -1, -2, 1, 2, 1, 2 };
		int[] dy = { -2, -1, 2, 1, -2, -1, 2, 1 };
		boolean[][] isVisited = new boolean[N + 1][M + 1];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x1, y1 });
		isVisited[x1][y1] = true;
		int moveCount = 0;
		while (!queue.isEmpty()) {
			int nodesAtCurrentBreadth = queue.size();
			for (int count = 0; count < nodesAtCurrentBreadth; count++) {
				int[] currPos = queue.remove();
				if (currPos[0] == x2 && currPos[1] == y2) {
					return moveCount;
				}
				for (int i = 0; i < dx.length; i++) {
					int rA = currPos[0] + dx[i];
					int cA = currPos[1] + dy[i];
					if (rA > 0 && rA <= N && cA > 0 && cA <= M && !isVisited[rA][cA]) {
						queue.add(new int[] { rA, cA });
						isVisited[rA][cA] = true;
					}
				}
			}
			moveCount++;
		}
		return -1;
	}

	public static int dfs(int A, int B, int C, int D, int E, int F, int count, boolean[][] visited) {
		if (C == E && E == F) {
			return count;
		}
		int[] rowNbr = { -2, -1, 1, 2, 2, 1, -1, -2 };
		int[] colNbr = { 1, 2, 2, 1, -1, -2, -2, -1 };
		for (int i = 0; i < 8; i++) {
			int rowAdj = C + rowNbr[i];
			int colAdj = D + colNbr[i];
			System.out.println(rowAdj + " " + colAdj);
			if (rowAdj >= 0 && rowAdj < A && colAdj >= 0 && colAdj < B && !visited[rowAdj][colAdj]) {
				visited[rowAdj][colAdj] = true;
				return dfs(A, B, rowAdj, colAdj, E, F, count + 1, visited);
			}
		}

		return -1;
	}

	public static ArrayList<Integer> stepnum(int A, int B) {
		if (B < A) {
			int temp = A;
			A = B;
			B = temp;
		}
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = A; i <= B; i++) {
			boolean isStepNum = true;
			int num = i;
			int r = num % 10;
			num = num / 10;
			while (num != 0) {
				int d = num % 10;
				if (Math.abs(r - d) != 1) {
					isStepNum = false;
					break;
				}
				r = d;
				num = num / 10;
			}
			if (isStepNum) {
				res.add(i);
			}
		}
		return res;
	}

	public static String solve(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
		int[][] ar = new int[A + 1][B + 1];
		for (int i = 0; i <= A; i++) {
			for (int j = 0; j <= B; j++) {
				for (int k = 0; k < C; k++) {
					double dis = Math.sqrt(Math.pow((E.get(k) - i), 2) + Math.pow((F.get(k) - j), 2));
					if (dis <= D) {
						ar[i][j] = -1;
					}
				}
			}
		}
		if (ar[0][0] == -1 || ar[A][B] == -1) {
			return "NO";
		}
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0 });
		ar[0][0] = 1;
		while (!q.isEmpty()) {
			int[] t = q.poll();
			int x = t[0];
			int y = t[1];
			// top left
			if (x > 1 && y < B && ar[x - 1][y + 1] == 0) {
				q.add(new int[] { x - 1, y + 1 });
				ar[x - 1][y + 1] = 1;
			}
			// top
			if (y < B && ar[x][y + 1] == 0) {
				q.add(new int[] { x, y + 1 });
				ar[x][y + 1] = 1;
			}
			// top right
			if (x < A && y < B && ar[x + 1][y + 1] == 0) {
				q.add(new int[] { x + 1, y + 1 });
				ar[x + 1][y + 1] = 1;
			}
			// right
			if (x < A && ar[x + 1][y] == 0) {
				q.add(new int[] { x + 1, y });
				ar[x + 1][y] = 1;
			}
			// bottom right
			if (x < A && y > 1 && ar[x + 1][y - 1] == 0) {
				q.add(new int[] { x + 1, y - 1 });
				ar[x + 1][y - 1] = 1;
			}
			// bottom
			if (y > 1 && ar[x][y - 1] == 0) {
				q.add(new int[] { x, y - 1 });
				ar[x][y - 1] = 1;
			}

			// bottom left
			if (x > 1 && y > 1 && ar[x - 1][y - 1] == 0) {
				q.add(new int[] { x - 1, y - 1 });
				ar[x - 1][y - 1] = 1;
			}

			// left
			if (x > 1 && ar[x - 1][y] == 0) {
				q.add(new int[] { x - 1, y });
				ar[x - 1][y] = 1;
			}
		}

		return ar[A][B] == 1 ? "YES" : "NO";
	}

	public static ArrayList<Integer> solve(int A, int B, int C, int D) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> res = new ArrayList<>();
		pq.add(A);
		pq.add(B);
		pq.add(C);
		set.add(A);
		set.add(B);
		set.add(C);
		while (res.size() < D) {
			int t = pq.poll();
			if (res.isEmpty() || res.get(res.size() - 1) != t)
				res.add(t);

			if (!set.contains(t * A)) {
				pq.add(t * A);
				set.add(t * A);
			}
			if (!set.contains(t * B)) {
				pq.add(t * B);
				set.add(t * B);
			}
			if (!set.contains(t * C)) {
				pq.add(t * C);
				set.add(t * C);
			}
		}
		return res;
	}

	static TrieNode root;

	public static ArrayList<String> prefix(ArrayList<String> A) {
		root = new TrieNode();
		for (String s : A) {
			insert(s);
		}
		ArrayList<String> res = new ArrayList<>();
		for (String s : A) {
			char[] ar = s.toCharArray();
			StringBuilder sb = new StringBuilder();
			TrieNode p = root;
			for (int i = 0; i < ar.length; i++) {
				char c = ar[i];
				sb.append(c);
				int index = c - 'a';
				if (p.ar[index].count == 1) {
					break;
				}
				p = p.ar[index];
			}
			res.add(sb.toString());
		}
		return res;
	}

	public static ArrayList<Integer> solve(String A, ArrayList<String> B) {
		root = new TrieNode();
		String[] goodWords = A.split("_");
		for (String s : goodWords) {
			insert(s);
		}
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.a1 == o2.a1) {
					return o1.a2 - o2.a2;
				}
				return o2.a1 - o1.a1;
			}

		});
		for (int i = 0; i < B.size(); i++) {
			String[] words = B.get(i).split("_");
			int count = 0;
			for (String w : words) {
				if (search(w))
					count++;
			}
			pq.add(new Pair(count, i));
		}
		ArrayList<Integer> res = new ArrayList<>();
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			res.add(p.a2);
		}
		return res;
	}

	public static void insert(String s) {
		int level;
		int length = s.length();
		int index;
		TrieNode pCrawl = root;
		for (level = 0; level < length; level++) {
			index = s.charAt(level) - 'a';
			if (pCrawl.ar[index] == null)
				pCrawl.ar[index] = new TrieNode();
			pCrawl.ar[index].count++;
			pCrawl = pCrawl.ar[index];
		}
		pCrawl.isEndOfWord = true;
	}

	public static boolean search(String s) {
		int level;
		int length = s.length();
		int index;
		TrieNode pCrawl = root;
		for (level = 0; level < length; level++) {
			index = s.charAt(level) - 'a';
			if (pCrawl.ar[index] == null)
				return false;
			pCrawl = pCrawl.ar[index];
		}
		return (pCrawl != null && pCrawl.isEndOfWord);
	}

	public static int sumNumbers(TreeNode A) {
		ArrayList<Integer> nums = new ArrayList<>();
		helper(nums, 0, A);
		int sum = 0;
		for (int a : nums)
			sum += a;
		return sum % 1003;
	}

	public static void helper(ArrayList<Integer> nums, int n, TreeNode A) {
		if (A == null) {
			return;
		}
		n = (n * 10) + A.val;
		if (A.left == null && A.right == null) {
			System.out.println(n);
			nums.add(n);
		}
		helper(nums, n, A.left);
		helper(nums, n, A.right);
		n = (n - A.val) / 10;
	}

	public static ArrayList<ArrayList<Integer>> pathSum(TreeNode A, int B) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if (A == null)
			return res;
		ArrayList<Integer> temp = new ArrayList<>();
		helper(res, temp, A, 0, B);
		return res;
	}

	public static void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> temp, TreeNode A, int sum, int B) {
		if (A == null)
			return;
		temp.add(A.val);
		sum += A.val;
		if (A.left == null && A.right == null && sum == B) {
			res.add(new ArrayList<>(temp));
		}
		helper(res, temp, A.left, sum, B);
		helper(res, temp, A.right, sum, B);
		temp.remove(temp.size() - 1);
	}

	public static int hasPathSum(TreeNode A, int B) {
		if (A.left == null && A.right == null && B == A.val) {
			return 1;
		}
		if (A.left != null) {
			return hasPathSum(A.left, B - A.val);
		}
		if (A.right != null) {
			return hasPathSum(A.right, B - A.val);
		}
		return 0;
	}

	public static int lca(TreeNode A, int B, int C) {
		Stack<TreeNode> s = new Stack<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		s.push(A);
		map.put(A.val, null);
		while (!map.containsKey(B) || !map.containsKey(C)) {
			if (s.isEmpty()) {
				return -1;
			} else {
				TreeNode node = s.pop();
				if (node.right != null) {
					s.add(node.right);
					map.put(node.right.val, node.val);
				}
				if (node.left != null) {
					s.add(node.left);
					map.put(node.left.val, node.val);
				}
			}
		}
		Set<Integer> bSet = new HashSet<>();
		Integer bn = B;
		while (bn != null) {
			bSet.add(bn);
			bn = map.get(bn);
		}
		Integer cn = C;
		while (!bSet.contains(cn)) {
			cn = map.get(cn);
		}
		return cn;
	}

	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(A);
		q.add(null);
		ArrayList<Integer> t = new ArrayList<>();
		int d = 0;
		while (!q.isEmpty()) {
			TreeNode tmp = q.poll();
			if (tmp != null) {
				t.add(tmp.val);
				if (tmp.left != null)
					q.add(tmp.left);
				if (tmp.right != null)
					q.add(tmp.right);
			} else {
				if (!q.isEmpty()) {
					q.add(null);
				}
				if (d == 0) {
					d = 1;
				} else if (d == 1) {
					Collections.reverse(t);
					d = 0;
				}
				res.add(new ArrayList<>(t));
				t.clear();
			}
		}
		return res;
	}

	public static ArrayList<Integer> rightView(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		while (!q.isEmpty()) {
			TreeNode tmp = q.poll();
			if (tmp != null) {
				if (q.peek() == null) {
					res.add(tmp.val);
				}
				if (tmp.left != null)
					q.add(tmp.left);
				if (tmp.right != null)
					q.add(tmp.right);
			} else {
				if (!q.isEmpty()) {
					q.add(null);
				}
			}
		}
		return res;
	}

	public static ArrayList<Integer> leftView(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		res.add(q.peek().val);
		while (!q.isEmpty()) {
			TreeNode tmp = q.poll();
			if (tmp != null) {
				if (tmp.left != null)
					q.add(tmp.left);
				if (tmp.right != null)
					q.add(tmp.right);
			} else {
				if (!q.isEmpty()) {
					res.add(q.peek().val);
					q.add(null);
				}
			}
		}
		return res;
	}

	public static ArrayList<Integer> boundary(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		res.add(root.val);
		TreeNode l = root.left;
		while (l.left != null) {
			res.add(l.val);
			l = l.left;
		}

		Stack<TreeNode> s = new Stack<>();
		s.add(root);
		while (!s.isEmpty()) {
			TreeNode tmp = s.pop();
			if (tmp.left == null && tmp.right == null) {
				res.add(tmp.val);
			}
			if (tmp.right != null)
				s.add(tmp.right);
			if (tmp.left != null)
				s.add(tmp.left);
		}
		ArrayList<Integer> rL = new ArrayList<>();
		TreeNode r = root.right;
		while (r.right != null) {
			rL.add(r.val);
			r = r.right;
		}
		for (int i = rL.size() - 1; i >= 0; i--) {
			res.add(rL.get(i));
		}

		return res;
	}

	public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
		traverse(A, map, 0);
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		for (java.util.Map.Entry<Integer, ArrayList<Integer>> e : map.entrySet()) {
			res.add(e.getValue());
		}
		return res;
	}

	public static void traverse(TreeNode A, TreeMap<Integer, ArrayList<Integer>> map, int level) {
		if (A == null)
			return;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(A);
		while (!q.isEmpty()) {
			ArrayList<Integer> temp = map.get(level);
			if (temp == null) {
				temp = new ArrayList<>();
				temp.add(A.val);
			} else {
				temp.add(A.val);
			}
			map.put(level, temp);
		}

		traverse(A.left, map, level - 1);
		traverse(A.right, map, level + 1);
	}

	public static ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
		ArrayList<Integer> ret = new ArrayList<>();
		if (B <= 0 || B > A.size())
			return ret;
		int count = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < B; i++) {
			if (map.containsKey(A.get(i))) {
				map.put(A.get(i), map.get(A.get(i)) + 1);
			} else {
				map.put(A.get(i), 1);
				count++;
			}
		}
		for (int i = B; i < A.size(); i++) {
			ret.add(count);
			if (map.get(A.get(i - B)) > 1) {
				map.put(A.get(i - B), map.get(A.get(i - B)) - 1);
			} else {
				map.remove(A.get(i - B));
				count--;
			}

			if (map.containsKey(A.get(i))) {
				map.put(A.get(i), map.get(A.get(i)) + 1);
			} else {
				map.put(A.get(i), 1);
				count++;
			}
		}
		ret.add(count);
		return ret;
	}

	public static ListNode mergeKLists(ArrayList<ListNode> a) {
		PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				return Integer.valueOf(o1.val).compareTo(Integer.valueOf(o2.val));
			}

		});

		for (int i = 0; i < a.size(); i++) {
			ListNode tmp = a.get(i);
			while (tmp != null) {
				pq.add(tmp);
				tmp = tmp.next;
			}
		}
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (!pq.isEmpty()) {
			cur.next = pq.poll();
			cur = cur.next;
		}
		cur.next = null;
		return dummy.next;
	}

	public static int kthsmallestHelper(int[] arr, int low, int high, int k) {
		if (k > 0 && k <= high - low + 1) {
			int partitionIndex = partition(arr, low, high);
			if (k == partitionIndex - low + 1) {
				return arr[partitionIndex];
			}
			if (k < partitionIndex - low + 1) {
				return kthsmallestHelper(arr, low, partitionIndex - 1, k);
			} else {
				return kthsmallestHelper(arr, partitionIndex + 1, high, k - (partitionIndex - low + 1));
			}
		}
		return -1;
	}

	public static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1;
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

class TrieNode {
	int count;
	TrieNode[] ar;
	boolean isEndOfWord;

	TrieNode() {
		ar = new TrieNode[26];
		isEndOfWord = false;
		count = 0;
	}
}

class Pair {
	int a1;
	int a2;

	Pair(int a1, int a2) {
		this.a1 = a1;
		this.a2 = a2;
	}
}

class APair {
	String s;
	int count;

	APair(String s, int count) {
		this.s = s;
		this.count = count;
	}
}

class LRUCache {
	DLLNode start;
	DLLNode end;
	int capacity;
	HashMap<Integer, DLLNode> map;

	public LRUCache(int capacity) {
		map = new HashMap<>();
		this.start = new DLLNode(0, 0);
		this.end = new DLLNode(0, 0);
		start.next = end;
		end.prev = start;
		this.capacity = capacity;
	}

	public int get(int key) {
		if (map.containsKey(key)) {
			DLLNode node = map.get(key);
			removeNode(node);
			addNode(node);
			System.out.println(node.val);
			return node.val;
		}
		System.out.println(-1);
		return -1;
	}

	public void put(int key, int value) {
		DLLNode node = new DLLNode(key, value);
		if (map.containsKey(key)) {
			DLLNode tmp = map.get(key);
			removeNode(tmp);
			addNode(node);
			map.put(key, node);
		} else {
			if (map.size() == capacity) {
				map.remove(end.prev.key);
				removeNode(end.prev);
			}
			addNode(node);
			map.put(key, node);
		}
	}

	public void addNode(DLLNode node) {
		DLLNode next = start.next;

		start.next = node;
		node.prev = start;

		node.next = next;
		next.prev = node;
	}

	public void removeNode(DLLNode node) {
		DLLNode prev = node.prev;
		DLLNode next = node.next;
		prev.next = next;
		next.prev = prev;
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */

class DLLNode {
	int key;
	int val;
	DLLNode prev;
	DLLNode next;

	public DLLNode(int key, int val) {
		this.key = key;
		this.val = val;
	}
}

class horse {
	int b, w;

	horse(int b, int w) {
		this.b = b;
		this.w = w;
	}
}