package DepthFirst;

public class Anagram {
	
	
	public static void dizi(int s1[], int s2[])	{
		if(s1.length == 0)	{
			for (int i = 0; i < s2.length; i++) {
				System.out.print(s2[i]+ ", ");
			}
			System.out.println();
		}
		for(int i = 0; i < s1.length; i++)	{
			dizi(diziBirlestir(altDizi(0, i, s1), altDizi(i+1, s1.length, s1)), diziSayiBirlestir(s1[i], s2));
		}
	}
	
	public static int[] altDizi(int bas, int son,int dizi[]) {
		int a[] = new int[son-bas];
		for (int i = 0; i < son-bas; i++) {
			a[i]=dizi[i+bas];
		}
		return a;
	}
	
	public static int[] diziBirlestir(int dizi1[], int dizi2[]) {
		int a [] = new int[dizi1.length+dizi2.length];
		for (int i = 0; i < dizi1.length; i++) {
			a[i]=dizi1[i];
		}
		for (int i = 0; i < dizi2.length; i++) {
			a[i+dizi1.length]=dizi2[i];
		}
		return a;
	}
	
	public static int[] diziSayiBirlestir(int sayi,int dizi[]) {
		int a [] = new int[dizi.length+1];
		a[0]=sayi;
		for (int i = 0; i < dizi.length; i++) {
			a[i+1]=dizi[i];
		}
		return a;
	}
	
	
	public static void anag(String s1, String s2)	{
		if(s1.length() == 0)	{
			System.out.println(s2);
		}
		for(int i = 0; i < s1.length(); i++)	{
			anag(s1.substring(0, i) + s1.substring(i+1, s1.length()), s2 + s1.charAt(i)  );
		}
	}
	public static void main(String[] args)	{
			
		
		anag("1234","");
	
		
	}
}