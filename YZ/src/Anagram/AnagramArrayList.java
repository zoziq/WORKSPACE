package Anagram;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Collection;

public class AnagramArrayList {

	public static void diziler(String s1, String s2) {
		if (s1.length()==0) {
			System.out.println(s2);
		}
		for (int i = 0; i < s1.length(); i++) {
			diziler(s1.substring(0, i) + s1.substring(i+1, s1.length()), s2 + s1.charAt(i));
		}
		
	}
	
	public static void main(String[] args) {
		
		diziler("1234", "");
		
	}
	
}
