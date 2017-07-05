package com.lsq.test;

public class Test {
	public static void main(String[] args) {
		
		for(int i = 0; i < 10; i ++){
			for(int j = 0; j < 10 - i - 1; j++){
				System.out.print(" ");
			}
			for(int k = 0; k < i; k ++){
				System.out.print("*");	 
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
