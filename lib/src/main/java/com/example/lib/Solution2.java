package com.example.lib;

import java.util.Scanner;

public class Solution2 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
//    	Scanner scn = null;
//    	try {
//    		scn = new Scanner(new File("input.txt"));
//    	} catch (Exception ex) {
//    		ex.printStackTrace();
//    	}
        int noOfMines = scn.nextInt();
        int noOfPickupLocations = scn.nextInt();
        int[] mineDistance = new int[noOfMines];
        long[] goldWeight = new long[noOfMines];
        for (int i = 0; i < noOfMines; ++i) {
            mineDistance[i] = scn.nextInt();
            goldWeight[i] = scn.nextLong();
        }

        long f[][] = new long[noOfMines][noOfPickupLocations + 1];
        for (int i = 0; i < noOfMines; ++i) {
            for (int j = 0; j <= noOfPickupLocations; ++j) {
                f[i][j] = Long.MAX_VALUE / 2;
            }
        }

        /*4 2 10309
        22 394
        42 601
        89 366
        96 347*/

        int b[][] = new int[noOfMines][noOfPickupLocations + 1];

        f[0][1] = 0;
        long s = goldWeight[0];
        for (int i = 1; i < noOfMines; ++i) {
            f[i][1] = f[i - 1][1] + s * (mineDistance[i] - mineDistance[i - 1]);
            s += goldWeight[i];
        }

        for (int j = 2; j <= noOfPickupLocations; ++j) {
            for (int i = j - 1; i < noOfMines; ++i) {
                int l = i;
                s = goldWeight[i];
                long t = goldWeight[i];
                long cost = 0;
                for (int k = i - 1; k >= j - 2; --k) {
                    cost += (s - t) * (mineDistance[k + 1] - mineDistance[k]);
                    s += goldWeight[k];
                    while (l - 1 >= k && mineDistance[i] - mineDistance[l - 1] <= mineDistance[l - 1] - mineDistance[k]) {
                        l--;
                        t += goldWeight[l];
                        cost += goldWeight[l] * (mineDistance[i] - mineDistance[l]) - goldWeight[l] * (mineDistance[l] - mineDistance[k]);
                    }
                    if (f[i][j] > f[k][j - 1] + cost) {
                        f[i][j] = f[k][j - 1] + cost;
                        b[i][j] = k;
                    }
                    if (k < b[i - 1][j]) {
                        break;
                    }
//        			System.out.println("current cost = " + (f[k][j - 1] + cost));
                }
//    			System.out.println("+++");
            }
        }
        long ret = f[noOfMines - 1][noOfPickupLocations];
//    	System.out.println("ret = " + ret);
        s = goldWeight[noOfMines - 1];
        long cost = 0;
        for (int i = noOfMines - 2; i >= noOfPickupLocations - 1; --i) {
//    		System.out.println("i = " + i);
//    		System.out.println("fim = " + f[i][m]);
            cost += s * (mineDistance[i + 1] - mineDistance[i]);
//    		System.out.println("cost = " + cost);
            ret = Math.min(ret, f[i][noOfPickupLocations] + cost);
            s += goldWeight[i];
//        	System.out.println("ret = " + ret);
//        	System.out.println("+++");
        }
        System.out.println(ret);
    }
}