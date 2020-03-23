package interviewbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class Equal {

	public static void main(String[] args) {
		int[] a = { 0, 0, 1, 0, 2, 1 };
		ArrayList<Integer> A = new ArrayList<>();
		for (int i = 0; i < a.length; i++) {
			A.add(a[i]);
		}
		System.out.println(equal(A));

	}

	public static ArrayList<Integer> equal(ArrayList<Integer> A) {
		ArrayList<Integer> res = new ArrayList<>();
		if (A == null || A.size() < 4) {
			return res;
		}
		LinkedHashMap<Integer, ArrayList<Integer>> nums = new LinkedHashMap<>();
		for (int i = 0; i < A.size(); i++) {
			if(nums.get(A.get(i))!=null) {
				nums.get(A.get(i)).add(i);
			}else {
				nums.put(A.get(i), new ArrayList<>(Arrays.asList(i)));
			}
		}
		for (int i = 0; i < A.size(); i++) {
			for (int j = i + 1; j < A.size(); j++) {
				for (int k = i + 1; k < A.size(); k++) {
					int num = A.get(i) + A.get(j) - A.get(k);
					if(k!=j && nums.get(num)!=null) {
						for(int l :  nums.get(num)) {
							if(l>k && l!=j) {
								res.add(i);
								res.add(j);
								res.add(k);
								res.add(l);
								return res;
							}
						}
					}
				}

			}
		}
		return res;
	}
}
