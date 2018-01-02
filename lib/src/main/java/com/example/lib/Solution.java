package com.example.lib;

import java.util.Arrays;

public class Solution {

    private static long minimalCost = Integer.MAX_VALUE;

    private static class Pair {
        long first;
        long second;

        Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        /*Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int noOfMines = Integer.valueOf(s.split(" ")[0]);*/
        int noOfPickupLocations = 5;//Integer.valueOf(s.split(" ")[1]);
        Pair[] mines = new Pair[8];
        //8,5
        mines[0] = new Pair(28, 445);
        mines[1] = new Pair(34, 468);
        mines[2] = new Pair(56, 304);
        mines[3] = new Pair(63, 752);
        mines[4] = new Pair(71, 432);
        mines[5] = new Pair(72, 38 );
        mines[6] = new Pair(78, 697);
        mines[7] = new Pair(97, 872);
        //8,3
        /*mines[0] = new Pair(3 , 176);*/
        /*mines[1] = new Pair(7 , 226);*/
        /*mines[2] = new Pair(31, 329);*/
        /*mines[3] = new Pair(32, 585);*/
        /*mines[4] = new Pair(33, 705);*/
        /*mines[5] = new Pair(77, 812);*/
        /*mines[6] = new Pair(89, 900);*/
        /*mines[7] = new Pair(92, 996);*/

        /*for (int i = 0; i < noOfMines; i++) {
            String s1 = sc.nextLine();
            mines[i] = new Pair(Integer.valueOf(s1.split(" ")[0]), Integer.valueOf(s1.split(" ")[1]));
        }*/

        long minimalCost = calculateMinimalCost(mines, noOfPickupLocations);

        System.out.println("" + minimalCost);
    }

    private static long calculateMinimalCost(Pair[] mines, int noOfPickupLocations) {
        if (noOfPickupLocations == 1) {
            return getMinimalCost(mines);
        }

        for (int start = 1; start < mines.length - 1; start++) {
            Pair[] firstPickup = Arrays.copyOfRange(mines,0, start);
            Pair[] secondPickup = Arrays.copyOfRange(mines, start, mines.length);
            minimalCost = Math.min(minimalCost, getMinimalCost(firstPickup) + calculateMinimalCost(secondPickup, noOfPickupLocations - 1));
        }

        return minimalCost;
    }

    private static long getMinimalCost(Pair[] mines) {
        long minimalCost = Integer.MAX_VALUE;
        long movedCoins = 0;
        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines.length; j++) {
                if (i != j) {
                    Pair currentMine = mines[i];
                    Pair nextMine = mines[j];
                    movedCoins = movedCoins + (Math.abs(currentMine.first - nextMine.first)) * nextMine.second;
                }
            }

            minimalCost = Math.min(movedCoins, minimalCost);
            movedCoins = 0;
        }
        return minimalCost;
    }
}