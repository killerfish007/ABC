package leetcode;

import java.util.Stack;

public class P230_KthSmallestElementinaBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int kthSmallest(TreeNode root, int k) {
        TreeNode cur = root;
        Stack<TreeNode> s = new Stack<>();
        while(!s.isEmpty() || cur!=null){
            while(cur!=null){
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            if(k==1)
                return cur.val;
            else{
                k--;
            }
            cur = cur.right;
        }
        return 0;
    }

}
