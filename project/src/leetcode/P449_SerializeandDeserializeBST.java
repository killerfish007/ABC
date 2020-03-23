package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P449_SerializeandDeserializeBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}

	public void buildString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("null").append(",");
		} else {
			sb.append(node.val).append(",");
			buildString(node.left, sb);
			buildString(node.right, sb);
		}
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Queue<String> q = new LinkedList<>();
		q.addAll(Arrays.asList(data.split(",")));
		return buildTree(q);
	}

	public TreeNode buildTree(Queue<String> q) {
		String val = q.poll();
		if (val.equals("null"))
			return null;
		else {
			TreeNode node = new TreeNode(Integer.valueOf(val));
			node.left = buildTree(q);
			node.right = buildTree(q);
			return node;
		}

	}
}
