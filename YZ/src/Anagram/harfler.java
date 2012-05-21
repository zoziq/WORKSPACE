package Anagram;

public class harfler {

	public static boolean areAnagrams(StringBuffer s1b, StringBuffer s2b) {

	    for (int i=0; i<s1b.length(); ++i) {
	        for (int j=0; j<s2b.length(); ++j) {

	            if (s1b.charAt(i) == s2b.charAt(j)) {

	                s1b.deleteCharAt(i);
	                s2b.deleteCharAt(j);

	                i=0;
	                j=0;
	            }
	        }
	    }

	    if (s1b.equals(s2b)) {
	        return true;
	    } else
	        return false;

	}

	public static void main(String[] args) {
		areAnagrams(new StringBuffer("mustafa"),new StringBuffer(""));
	}
	
}
