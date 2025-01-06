/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruteforceshortestpath;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author reemshady
 */
public class BruteForceShortestPath {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of scenarios: ");
        int scenarios = scanner.nextInt();

        for (int scenario = 0; scenario < scenarios; scenario++) {
            System.out.print("Enter grid size (x y): ");
            int xSize = scanner.nextInt();
            int ySize = scanner.nextInt();

            System.out.print("Enter starting position (x y): ");
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();

            System.out.print("Enter number of beepers: ");
            int n = scanner.nextInt();
            int[][] beepers = new int[n][2];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter position of beeper " + (i + 1) + " (x y): ");
                beepers[i][0] = scanner.nextInt();
                beepers[i][1] = scanner.nextInt();
            }

            long startTime = System.currentTimeMillis();
            int minDistance = findShortestPath(startX, startY, beepers);
            long endTime = System.currentTimeMillis();

            System.out.println("The shortest path has length: " + minDistance);
            System.out.println("Execution time: " + (endTime - startTime) + " ms");
        }

        scanner.close();
    }

    private static int findShortestPath(int startX, int startY, int[][] beepers) {
        List<int[]> permutations = new ArrayList<>();
        generatePermutations(beepers, 0, permutations);

        int minDistance = Integer.MAX_VALUE;
        for (int[] perm : permutations) {
            int distance = 0;
            int currentX = startX;
            int currentY = startY;

            for (int i = 0; i < perm.length / 2; i++) {
                int x = perm[2 * i];
                int y = perm[2 * i + 1];
                distance += Math.abs(currentX - x) + Math.abs(currentY - y);
                currentX = x;
                currentY = y;
            }

            // Return to start
            distance += Math.abs(currentX - startX) + Math.abs(currentY - startY);
            minDistance = Math.min(minDistance, distance);
        }
        return minDistance;
    }

    private static void generatePermutations(int[][] beepers, int start, List<int[]> permutations) {
        if (start == beepers.length) {
            int[] perm = new int[beepers.length * 2];
            for (int i = 0; i < beepers.length; i++) {
                perm[2 * i] = beepers[i][0];
                perm[2 * i + 1] = beepers[i][1];
            }
            permutations.add(perm);
            return;
        }

        for (int i = start; i < beepers.length; i++) {
            swap(beepers, i, start);
            generatePermutations(beepers, start + 1, permutations);
            swap(beepers, i, start);
        }
    }

    private static void swap(int[][] arr, int i, int j) {
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    /**
     * small grid (3x3) with 2 beepers
     *
     * 1
     * 3 3
     * 1 1
     * 2
     * 2 2
     * 3 3
     *
     * 
     * medium grid (5x5) with 3 beepers
     * 1
     * 5 5
     * 1 1
     * 3
     * 2 3
     * 4 4
     * 5 1
     * 
     * large grid (20x20) with 4 beepers
     * 1
     * 20 20
     * 1 1
     * 4
     * 5 5
     * 10 10
     * 15 15
     * 20 5
     * 
     * 
     * 
     ** /
     
