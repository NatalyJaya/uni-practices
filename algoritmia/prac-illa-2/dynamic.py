import sys
import math

def count_calls(func):
    """Decorator to count the number of calls to a function."""
    def wrapper(*args, **kwargs):
        wrapper.count += 1
        return func(*args, **kwargs)
    wrapper.count = 0
    return wrapper

@count_calls
def dist(p1, p2):
    """Calculate Euclidean distance between two points."""
    return math.hypot(p1[0] - p2[0], p1[1] - p2[1])

def generate_distance_matrix(points):
    """Generate a distance matrix for a list of points."""
    total = len(points)
    for i in range(total):
        yield [dist(points[i], points[j]) for j in range(total)]

def read_input(filename):
    """Read input from a file."""
    with open(filename, 'r', encoding='utf-8') as f:
        n = int(f.readline())
        points = [tuple(map(int, line.split())) for line in f]
    return n, points

def write_output(filename, distance, team1, team2):
    """Write output to a file."""
    with open(filename, 'w', encoding='utf-8') as f:
        f.write(f"{distance:.6f}\n")
        f.write(' '.join(map(str, team1)) + '\n')

def maximize_distance(points):
    """Partition points into two teams maximizing the minimum distance between teams."""
    n = len(points) // 2
    total = len(points)

    dist_matrix = list(generate_distance_matrix(points))
    memo = {}

    def dp(i, mask1, mask2):
        key = (i, mask1, mask2)
        if key in memo:
            return memo[key]

        if bin(mask1).count('1') > n or bin(mask2).count('1') > n:
            return False
        if i == total:
            return bin(mask1).count('1') == n and bin(mask2).count('1') == n

        # Try to put in team 1
        if bin(mask1).count('1') < n:
            ok = True
            for j in range(total):
                if (mask2 >> j) & 1:
                    if dist_matrix[i][j] < min_dist:
                        ok = False
                        break
            if ok and dp(i + 1, mask1 | (1 << i), mask2):
                memo[key] = True
                return True

        # Try to put in team 2
        if bin(mask2).count('1') < n:
            ok = True
            for j in range(total):
                if (mask1 >> j) & 1:
                    if dist_matrix[i][j] < min_dist:
                        ok = False
                        break
            if ok and dp(i + 1, mask1, mask2 | (1 << i)):
                memo[key] = True
                return True

        memo[key] = False
        return False

    def reconstruct(i, mask1, mask2):
        if i == total:
            return [], []

        if bin(mask1).count('1') < n:
            ok = True
            for j in range(total):
                if (mask2 >> j) & 1:
                    if dist_matrix[i][j] < min_dist:
                        ok = False
                        break
            if ok and dp(i + 1, mask1 | (1 << i), mask2):
                team1, team2 = reconstruct(i + 1, mask1 | (1 << i), mask2)
                return [i] + team1, team2

        if bin(mask2).count('1') < n:
            ok = True
            for j in range(total):
                if (mask1 >> j) & 1:
                    if dist_matrix[i][j] < min_dist:
                        ok = False
                        break
            if ok and dp(i + 1, mask1, mask2 | (1 << i)):
                team1, team2 = reconstruct(i + 1, mask1, mask2 | (1 << i))
                return team1, [i] + team2

        return None, None

    left, right = 0.0, max(dist(p1, p2) for p1 in points for p2 in points)
    for _ in range(40):
        mid = (left + right) / 2
        global min_dist
        min_dist = mid
        memo.clear()
        if dp(1, 1, 0):
            left = mid
        else:
            right = mid

    min_dist = left
    memo.clear()
    dp(1, 1, 0)
    team1, team2 = reconstruct(1, 1, 0)
    team1 = [0] + team1

    return min_dist, sorted([i + 1 for i in team1]), sorted([i + 1 for i in team2])

def main():
    """Main entry point."""
    if len(sys.argv) >= 3:
        input_file = sys.argv[1]
        output_file = sys.argv[2]
    else:
        input_file = sys.stdin
        output_file = sys.stdout

    n, points = read_input(input_file)
    distance, team1, team2 = maximize_distance(points)
    write_output(output_file, distance, team1, team2)

    print(f"Number of calls to dist: {dist.count}")

if __name__ == "__main__":
    min_dist = 0.0  # Global for inner functions
    main()
