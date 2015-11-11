package com.manjiler.mathhelper.utils;

import java.util.List;

public class NumberUtils {

	public static boolean isPerfectSquare(int number) {
		if(number < 0)
			return false;
		long sqrt = (long)(Math.sqrt(number));
		return (sqrt*sqrt == number);
	}

	public static boolean isWholeNumber(double n) {
		if(n == (int)n) {
			return true;
		} else {
			return false;
		}
	}
	
	//checks whether an int is prime or not.
	public static boolean isPrime(int n) {
		//check if n is a multiple of 2
		if (n%2==0) return false;
		//if not, then just check the odds
		for(int i=3;i*i<=n;i+=2) {
			if(n%i==0)
				return false;
		}
		return true;
	}

	public static void representInPerfectSquare(List<Integer> perfectSquares, int n) {
		if(!isPrime(n)) {
			boolean canBeSplit = false;
			for(int i = 2;  i <= 9; i++) {
				if(n%(i*i) == 0) {
					perfectSquares.add(i*i);
					canBeSplit = true;
					representInPerfectSquare(perfectSquares,n/(i*i));
					break;
				}		
			}
			if(!canBeSplit) {
				perfectSquares.add(n);
			}
		} else {
			perfectSquares.add(n);
		}
		return;
	}
	
	public static String getSignAndValue(int number) {
		if(number > 0) {
			return "+ " + number;
		} else {
			return Integer.valueOf(number).toString();
		}
		
	}

}
