package rekursif;

import java.util.ArrayList;
import java.util.Vector;

public class fibonacci {
	
	static int n0 = 1, n1 = 1, n2;
	
	private static int fib(int n) {
		
		if (n==0) {
			return 1;
		}
		if (n==1) {
			return 1;
		}
		return fib(n-2)+fib(n-1);
	}
	
	static int sayac = 0;
	private static int fib(int f1, int f2, int n) {
		sayac++;
		if (n==sayac) {
			return 1;
		}
		System.out.println(f1+f2);
		return fib(f2, f1+f2,n);
	}
	
	
	static int f1 = 1;

	static int f2 = 1;
	
	
	private static void fib3(int n)
	{
	    if(n<2)return;
	    int fib = f1 + f2;
	    f1 = f2;
	    f2 = fib;
//	    System.out.println(fib);
	    fib3(n-1);
	} 
	
	private static int fib4(int n)
	{
	    if(n<2)return 1;
	    int fib = f1 + f2;
	    f1 = f2;
	    f2 = fib;
	    return f1+f2+fib4(n-1);
	} 
	
	
	private int fib2(int n)
	{
	    if (n == 1 || n == 2)
	        return 1;
	    int fibprev = 1;
	    int fib = 1;
	    for (int cur = 2 ; cur < n ; ++cur)
	    {
	        int temp = fib;
	        fib += fibprev;
	        fibprev = temp;
	    }
	    return fib;
	} 
	
	public static long anFibN(final long n)
	{
	 double p = (1 + Math.sqrt(5)) / 2;
	 double q = 1 / p;
	 return (long) ((Math.pow(p, n) + Math.pow(q, n)) / Math.sqrt(5));
	}
	
	private static void FIBONNACCI(int X) {
		for (int i = 0; i < X; i++) { // Loop for the next 18 terms
	      n2 = n1 + n0; // Next term is sum of previous two
	      System.out.print(n2 + " "); // Print it out
	      n0 = n1; // First previous becomes 2nd previous
	      n1 = n2; // And current number becomes previous
	    }
	    System.out.println(); // Terminate the line
	}
	
	public static void main(String[] args) {
	
//		int k = 40;
//		long i = System.nanoTime();
//		fib3(k);
//		System.out.println(f2);
//		System.out.println("==" + (System.nanoTime()-i));
//		i = System.nanoTime();
//		System.out.println(fib(k));
//		System.out.println("==" + (System.nanoTime()-i));
//		i = System.nanoTime();
//		System.out.println(anFibN(k+1));
//		System.out.println("==" + (System.nanoTime()-i));
		
		
		 // Initialize variables
	    System.out.print(n0 + " " + // Print first and second terms
	        n1 + " "); // of the series

	    FIBONNACCI(18);
	}

	
}
