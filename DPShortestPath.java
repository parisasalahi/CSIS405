/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpshortestpath;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author reemshady
 */
public class DPShortestPath {
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
            int[][] beepers = new int[n + 1][2];
            beepers[0] = new int[]{startX, startY};
            for (int i = 1; i <= n; i++) {
                System.out.print("Enter position of beeper " + i + " (x y): ");
                beepers[i][0] = scanner.nextInt();
                beepers[i][1] = scanner.nextInt();
            }

            long startTime = System.currentTimeMillis();
            int minDistance = tsp(beepers);
            long endTime = System.currentTimeMillis();

            System.out.println("The shortest path has length: " + minDistance);
            System.out.println("Execution time: " + (endTime - startTime) + " ms");
        }

        scanner.close();
    }

    private static int tsp(int[][] beepers) {
        int n = beepers.length;
        int[][] dp = new int[1 << n][n];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE / 2);
        }
        dp[1][0] = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    for (int j = 0; j < n; j++) {
                        if ((mask & (1 << j)) == 0) {
                            int nextMask = mask | (1 << j);
                            dp[nextMask][j] = Math.min(dp[nextMask][j],
                                    dp[mask][i] + manhattanDistance(beepers[i], beepers[j]));
                        }
                    }
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minDistance = Math.min(minDistance,
                    dp[(1 << n) - 1][i] + manhattanDistance(beepers[i], beepers[0]));
        }
        return minDistance;
    }

    private static int manhattanDistance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
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
}
