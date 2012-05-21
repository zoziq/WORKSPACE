package Anagram;

public class Permutasyon {

	public static char[] arr = "1234".toCharArray();

	public static void permute(int start) {
		if (start == arr.length - 1) 
		{
			System.out.println(arr);
		}
		for (int i = start; i < arr.length; i++) {
			swap(start, i);
			permute(start + 1);
			swap(start, i);
		}
	}

	private static void swap(int start, int i) {
		char temp = arr[i];
		arr[i] = arr[start];
		arr[start] = temp;
	}

	public static void main(String[] args) {
		permute(0);
		
	}

}
