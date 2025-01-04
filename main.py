"""
Program: Shortest Path Problem Solver (Brute Force Approach)
Author: Reem Shady(s00046744), Parisa Salahi(s00059463), Shaikha Al-Sabah(s00058291)
Date: 05/01/2025
Course: CSIS 405 – Analysis of Algorithms
Instructor: Dr. Ali Roumani

Description & Purpose of the Code:
    This program solves the shortest path problem using a brute force approach.
    Given a starting point and a list of beeper locations in a 2D grid, the program calculates
    the shortest path for a robot to visit all beepers and return to the starting point.
    It employs the exhaustive enumeration of all possible paths (permutations of beeper visits)
    to determine the optimal solution.

"""


import itertools
import time


def bf_shortest_path():
    scenarios = int(input("Enter number of scenarios: "))
    for _ in range(scenarios):

        x_size, y_size = map(int, input().split())

        # this is the starting position
        start_x, start_y = map(int, input().split())

        # this part will initiate the number of beepers
        n = int(input())
        beepers = []
        for _ in range(n):
            beepers.append(tuple(map(int, input().split())))

        # brute force
        start_time = time.time()
        min_distance = float('inf')


        for perm in itertools.permutations(beepers):
            distance = 0
            current_x, current_y = start_x, start_y

            # This part will calculate the distance
            for x, y in perm:
                distance += abs(current_x - x) + abs(current_y - y)
                current_x, current_y = x, y

            # adding dist to return to start
            distance += abs(current_x - start_x) + abs(current_y - start_y)

            # updating min distance when we find it
            min_distance = min(min_distance, distance)

        end_time = time.time()


        print(f"The shortest path has length {min_distance}")
        print(f"Execution time: {end_time - start_time:.6f} seconds")


bf_shortest_path()

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