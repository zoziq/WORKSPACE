package Anagram;

public class sayilar {

	public static int[] arr = { 1, 2, 3, 4 };

	public static void permute(int a) {
		if (a == arr.length - 1) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] +  " ");
			}
			System.out.println();
		}
		for (int i = a; i < arr.length; i++) {
			swap(a, i);
			permute(a + 1);
			swap(a, i);
		}
	}

	private static void swap(int a, int i) {
		int temp = arr[i];
		arr[i] = arr[a];
		arr[a] = temp;
	}

	public static void main(String[] args) {

		permute(0);

	}

}
