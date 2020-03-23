package leetcode;

public class ReverseWords {

	public static void main(String[] args) {

	}

	public String reverseWords(String s) {
		String[] ar = s.split(" ");
		StringBuilder re = new StringBuilder();
		for(int i=0;i<ar.length;i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(ar[i]);
			ar[i] = sb.reverse().toString();
			re.append(ar[i]);
			re.append(" ");
		}
		re.deleteCharAt(re.length()-1);
		return re.toString();
		
	}
}
