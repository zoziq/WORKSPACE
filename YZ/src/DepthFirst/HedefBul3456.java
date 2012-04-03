package DepthFirst;

import java.awt.Container;
import java.util.Collection;

public class HedefBul3456 {

	static int sayilar[] = {6,3,5,4};
	static int islemler[] = {1,1,1};
	static boolean bulunduMu = false;
	static int sayac = 0;
	
	public static boolean test() {
		
		int i1 = hesap(sayilar[0],sayilar[1],islemler[0]);
		int i2 = hesap(i1,sayilar[2],islemler[1]);
		int i3 = hesap(i2,sayilar[3],islemler[2]);
		System.out.println(i3);
		
		if (i3==28) {
			System.out.println(sayilar[0]+" "+isaret(islemler[0])+" "+sayilar[1]+" "+isaret(islemler[1])+" "+sayilar[2]+" "+isaret(islemler[2])+" "+ sayilar[3]+" = 28");
			bulunduMu = true;
			return true;
		}
		
		islemler[0]++;
		if (islemler[0]<5) {
			test();
		}
		islemler[1]++;
		if (islemler[1]<5) {
			islemler[0]=1;
			test();
		}
		islemler[2]++;
		if (islemler[2]<5) {
			islemler[0]=1;
			islemler[1]=1;
			test();
		}
		return false;
	}
	
	public static int hesap(int s1, int s2, int islem) {
		if (islem==1) {
			return s1+s2;
		}
		else if (islem==2) {
			return s1-s2;
		}
		else if (islem==3) {
			return s1*s2;
		}
		else if (islem==4) {
			return s1/s2;
		}
		return 0;
	}
	
	public static String isaret(int i) {
		if (i==1)return "+"; 
		else if (i==2)return "-"; 
		else if (i==3)return "*"; 
		else if (i==4)return "/"; 
		return "";
	}
	
	public static void yeniSayilarDizisi() {
		if(sayac==24) {
			return;
		}
		sayac++;
		
		test();
		int []a = {};
		dizi(sayilar, a);
		
		
		
		
	}
	
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
	
	public static void main(String[] args) {
		
		yeniSayilarDizisi();
		
	}
	
	
}
