package leetcode;

import java.util.Stack;

public class P101_SymmetricTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }
	
	public boolean isMirror(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (p == null || q == null) {
			return false;
		} else if (p.val != q.val) {
			return false;
		}
		return isMirror(p.left, q.right) && isMirror(p.right, q.left);
	
	}
	
	public boolean isMirrorIterative(TreeNode p, TreeNode q) {
		Stack<TreeNode> s = new Stack<>();
		s.push(p);
		s.push(q);
		while(!s.isEmpty()) {
			TreeNode t1 = s.pop();
			TreeNode t2 = s.pop();
			if(t1==null && t2==null) {
				continue;
			}
			if(t1==null || t2==null) {
				return false;
			}
			if(t1.val!=t2.val) {
				return false;
			}
			
			s.push(t1.left);
			s.push(t2.right);
			s.push(t1.right);
			s.push(t2.left);
		}
		return true;
	}

}
