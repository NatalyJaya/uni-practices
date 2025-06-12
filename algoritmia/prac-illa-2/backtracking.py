import sys
import math

def read_input(filename):
    """
    Reads the input from a file.
    Returns the number of players per team and the list of points.
    """
    with open(filename, 'r') as f:
        n = int(f.readline())
        points = [tuple(map(int, line.split())) for line in f]
    return n, points

def write_output(filename, distance, team1, team2):
    """
    Writes the output to the specified file: the distance and the members of team 1.
    """
    with open(filename, 'w') as f:
        f.write(f"{distance:.6f}\n")
        f.write(' '.join(map(str, team1)) + '\n')

def euclidean_distance(p1, p2):
    """
    Calculates the Euclidean distance between two points.
    """
    return math.hypot(p1[0] - p2[0], p1[1] - p2[1])

def calculate_min_distance(team1, team2, points):
    """
    Calculates the minimum distance between any pair of players from the two teams.
    """
    return min(euclidean_distance(points[i], points[j]) for i in team1 for j in team2)

def backtrack(idx, team1, team2, points, n, best):
    """
    Backtracking algorithm to split players into two teams maximizing the minimum distance.
    Includes several prunings to reduce the search space.
    """
    # Prune: cannot exceed the team size
    if len(team1) > n or len(team2) > n:
        return

    # Prune: not enough players left to complete both teams
    remaining = 2 * n - idx
    if len(team1) + remaining < n or len(team2) + remaining < n:
        return

    # Base case: all players have been assigned
    if idx == 2 * n:
        min_dist = calculate_min_distance(team1, team2, points)
        if min_dist > best[0]:
            best[0] = min_dist
            best[1] = [i + 1 for i in sorted(team1)]
            best[2] = [j + 1 for j in sorted(team2)]
        return

    # Prune: if both teams have members, we can compute a lower bound
    if team1 and team2:
        current_min = calculate_min_distance(team1, team2, points)
        if current_min <= best[0]:
            return

    # Branch: assign player idx to team1 or team2
    for team in [team1, team2]:
        team.append(idx)
        backtrack(idx + 1, team1, team2, points, n, best)
        team.pop()

def maximize_distance(points):
    """
    Initializes the search and returns the best solution found.
    """
    n = len(points) // 2
    best = [-1, [], []]
    # Fix player 0 in team1 to avoid symmetries
    backtrack(1, [0], [], points, n, best)
    return best[0], best[1], best[2]

def main():
    """
    Main function: reads input, solves the problem, and writes the output.
    """
    if len(sys.argv) >= 3:
        input_file = sys.argv[1]
        output_file = sys.argv[2]
    else:
        input_file = sys.stdin
        output_file = sys.stdout

    n, points = read_input(input_file)
    distance, team1, team2 = maximize_distance(points)
    write_output(output_file, distance, team1, team2)

if __name__ == "__main__":
    main()
