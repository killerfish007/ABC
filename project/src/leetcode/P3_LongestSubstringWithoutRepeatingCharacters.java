package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P3_LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		System.out.println(partition("abba"));
	}
	

    public static ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        backtrack(results,temp,s,0);
        return results;
    }
    
    private static void backtrack(ArrayList<ArrayList<String>> results,  ArrayList<String> temp,String s, int startIndex ){
        if(startIndex==s.length()){
            results.add(new ArrayList<>(temp));
        }else{
            for(int i=startIndex+1;i<=s.length();i++){
                String stemp = s.substring(startIndex,i);
                System.out.println(stemp);
                if(isPalindrome(stemp)){
                    temp.add(stemp);
                    backtrack(results,temp,s,i);
                    temp.remove(temp.size()-1);
                }
            }
        }
        
    }
    
    private static boolean isPalindrome(String s){
        int l=0; int r = s.length()-1;
        while(l<r){
            if(s.charAt(l)!=s.charAt(r)) return false;
            l++;r--;
        }
        return true;
    }

	
	public static int lengthOfLongestSubstring1(String s) {
        if(s.length()==0){
            return 0;
        }
        
        int max = 0;
        for(int i=0,j=1;j<s.length();j++){
            if(s.charAt(j)==s.charAt(i)){
                i++;
            }
            max = Math.max(max, j-i);
        }
        return max+1;
    }

	public static int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> map = new HashMap<>();
		int max = 0;
		for (int i = 0, j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			if (map.containsKey(c)) {
				i = Math.max(map.get(c), i);
			}
			max = Math.max(max, j - i + 1);
			map.put(c, j + 1);
		}
		return max;
	}
}
