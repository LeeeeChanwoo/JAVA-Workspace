package com.kh.first; // 패키지 선언부

import java.math.BigInteger;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
	
		for(int i = 2; i>0;i--) {
			System.out.print(a.charAt(i));
			
		}
	}
}
