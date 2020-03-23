package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P652_FindDuplicateSubtrees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> res = new ArrayList<>();
		dfs(root, new HashMap<>(), res);
		return res;
	}

	public String dfs(TreeNode root, Map<String, Integer> map, List<TreeNode> res) {
		if (root == null) {
			return "null";
		}
		String cur = root.val + "," + dfs(root.left, map, res) + "," + dfs(root.right, map, res);

		if (map.getOrDefault(cur, 0) == 1)
			res.add(root);
		map.put(cur, map.getOrDefault(cur, 0) + 1);

		return cur;
	}

}
