"""
Program: Shortest Path Problem Solver (Dynamic Programming with Bitmasking)
Author: Reem Shady(s00046744), Parisa Salahi(s00059463), Shaikha Al-Sabah(s00058291)
Date: 05/01/2025
Course: CSIS 405 – Analysis of Algorithms
Instructor: Dr. Ali Roumani

Description & Purpose of this program:
    This program solves the shortest path problem using a Dynamic Programming (DP) approach with Bitmasking.
    It determines the shortest path for a robot (Karel) to visit all beepers in a 2D grid, starting and ending
    at a given position. The algorithm leverages memoization to optimize the solution, reducing redundant
    calculations compared to brute force.
"""

import time


def dp_shortest_path():
    def tsp(mask, pos):
        if mask == (1 << (n + 1)) - 1:
            # when all beepers are visited, the program returns to the start of the program
            return distance(pos, 0)

        if dp[mask][pos] != -1:
            return dp[mask][pos]

        min_cost = float('inf')
        for next_pos in range(n + 1):
            # If next_pos is not visited
            if mask & (1 << next_pos) == 0:
                cost = distance(pos, next_pos) + tsp(mask | (1 << next_pos), next_pos)
                min_cost = min(min_cost, cost)

        dp[mask][pos] = min_cost
        return min_cost

    def distance(a, b):
        return abs(beepers[a][0] - beepers[b][0]) + abs(beepers[a][1] - beepers[b][1])

    scenarios = int(input("Enter number of scenarios: "))
    for _ in range(scenarios):

        x_size, y_size = map(int, input().split())

        # the starting position of Karel
        start_x, start_y = map(int, input().split())

        # The number of beepers
        n = int(input())
        # the starting position will be set as beeper 0
        beepers = [(start_x, start_y)]
        for _ in range(n):
            beepers.append(tuple(map(int, input().split())))

        # initializing the DP table, where the results are saved
        dp = [[-1] * (n + 1) for _ in range(1 << (n + 1))]


        start_time = time.time()
        result = tsp(1, 0)
        # start from beeper 0
        end_time = time.time()

        print(f"The shortest path has length {result}")
        print(f"Execution time: {end_time - start_time:.6f} seconds")


dp_shortest_path()

"""
use to test. result is 24.

1
10 10
1 1
4
2 3
5 5
9 4
6 5

use to test. result is 12

1
10 10
1 1
3
2 2
3 3
4 4
"""
