{-|
Module      : battle
Description : Partition points into two teams maximizing the minimal distance between any two players from different teams.

Functions:

- readInput :: FilePath -> IO [Point]
    -- Reads the input file and parses the list of points. Each point is a tuple of integers (x, y).

- distance :: Point -> Point -> Double
    -- Computes the Euclidean distance between two points.

- distMatrix :: [Point] -> [[Double]]
    -- Constructs a matrix of distances between all pairs of points.

- countBits :: Int -> Int
    -- Counts the number of set bits (1s) in the binary representation of an integer.

- dp :: Int -> Int -> Int -> Int -> Int -> [[Double]] -> Double -> Memo -> (Bool, Memo)
    -- Dynamic programming function to determine if it is possible to partition the points into two teams of size n,
       such that the minimal distance between any two players from different teams is at least minDist.
       Uses memoization to avoid redundant computations.

- reconstruct :: Int -> Int -> Int -> Int -> Int -> [[Double]] -> Double -> Memo -> ([Int], [Int])
    -- Reconstructs the actual partition of points into two teams based on the memoization map from dp.

- maxMinDist :: [Point] -> (Double, [Int], [Int])
    -- Finds the maximum possible minimal distance between any two players from different teams,
       and returns this distance along with the indices of the players in each team.

- writeOutput :: FilePath -> (Double, [Int], [Int]) -> IO ()
    -- Writes the result to the output file: the maximal minimal distance and the indices of the first team.

- main :: IO ()
    -- Main entry point. Reads input and output file paths from command line arguments,
       processes the input, and writes the result to the output file.
-}
import System.Environment (getArgs)
import System.IO
import Data.List
import Data.Bits
import qualified Data.Map.Strict as Map
import Control.Monad
import Text.Printf

type Point = (Int, Int)
type Mask = Int
type Memo = Map.Map (Int, Mask, Mask) Bool

readInput :: FilePath -> IO [Point]
readInput file = do
    ls <- lines <$> readFile file
    let n = read (head ls) :: Int
    return $ fmap ((\[a,b] -> (a,b)) . fmap read . words) (take (2*n) (tail ls))

distance :: Point -> Point -> Double
distance (x1, y1) (x2, y2) = sqrt $ fromIntegral ((x1-x2)^2 + (y1-y2)^2)

distMatrix :: [Point] -> [[Double]]
distMatrix pts = [[distance p1 p2 | p2 <- pts] | p1 <- pts]

countBits :: Int -> Int
countBits 0 = 0
countBits x = (x .&. 1) + countBits (shiftR x 1)

dp :: Int -> Int -> Int -> Int -> Int -> [[Double]] -> Double -> Memo -> (Bool, Memo)
dp i mask1 mask2 n total dmat minDist memo
    | Map.member key memo = (memo Map.! key, memo)
    | countBits mask1 > n || countBits mask2 > n = (False, Map.insert key False memo)
    | i == total = (countBits mask1 == n && countBits mask2 == n, Map.insert key (countBits mask1 == n && countBits mask2 == n) memo)
    | otherwise =
        let -- Try to put i in team 1
            ok1 = countBits mask1 < n &&
                  all (\j -> testBit mask2 j == False || dmat!!i!!j >= minDist) [0..total-1]
            (res1, memo1) = if ok1 then dp (i+1) (setBit mask1 i) mask2 n total dmat minDist memo else (False, memo)
            -- Try to put i in team 2
            ok2 = countBits mask2 < n &&
                  all (\j -> testBit mask1 j == False || dmat!!i!!j >= minDist) [0..total-1]
            (res2, memo2) = if not res1 && ok2 then dp (i+1) mask1 (setBit mask2 i) n total dmat minDist memo1 else (False, memo1)
            result = res1 || res2
        in (result, Map.insert key result memo2)
  where key = (i, mask1, mask2)

reconstruct :: Int -> Int -> Int -> Int -> Int -> [[Double]] -> Double -> Memo -> ([Int], [Int])
reconstruct i mask1 mask2 n total dmat minDist memo
    | i == total = ([], [])
    | otherwise =
        let ok1 = countBits mask1 < n &&
                  all (\j -> testBit mask2 j == False || dmat!!i!!j >= minDist) [0..total-1]
            (res1, _) = if ok1 then dp (i+1) (setBit mask1 i) mask2 n total dmat minDist memo else (False, memo)
            -- Try to put i in team 1
        in if ok1 && res1
            then let (t1, t2) = reconstruct (i+1) (setBit mask1 i) mask2 n total dmat minDist memo
                 in (i:t1, t2)
            else
                let ok2 = countBits mask2 < n &&
                          all (\j -> testBit mask1 j == False || dmat!!i!!j >= minDist) [0..total-1]
                    (res2, _) = if ok2 then dp (i+1) mask1 (setBit mask2 i) n total dmat minDist memo else (False, memo)
                in if ok2 && res2
                    then let (t1, t2) = reconstruct (i+1) mask1 (setBit mask2 i) n total dmat minDist memo
                         in (t1, i:t2)
                    else ([], [])

maxMinDist :: [Point] -> (Double, [Int], [Int])
maxMinDist pts =
    let n = length pts `div` 2
        total = length pts
        dmat = distMatrix pts
        allDists = [dmat!!i!!j | i <- [0..total-1], j <- [0..total-1], i /= j]
        search l r 0 = l
        search l r k =
            let mid = (l + r) / 2
                (ok, _) = dp 1 (setBit 0 0) 0 n total dmat mid Map.empty
            in if ok then search mid r (k-1) else search l mid (k-1)
        bestDist = search 0 (maximum allDists) 40
        (_, memo) = dp 1 (setBit 0 0) 0 n total dmat bestDist Map.empty
        (team1, team2) = reconstruct 1 (setBit 0 0) 0 n total dmat bestDist memo
        team1' = 0:team1
    in (bestDist, sort $ fmap (+1) team1', sort $ fmap (+1) team2)  

writeOutput :: FilePath -> (Double, [Int], [Int]) -> IO ()
writeOutput file (dist, team1, _) = do
    withFile file WriteMode $ \h -> do
        hPrintf h "%.6f\n" dist
        hPutStrLn h $ unwords (fmap show team1)  -- fmap instead of map (functor style)

main :: IO ()
main = do
    args <- getArgs
    (inputFile, outputFile) <- case args of
        (a:b:_) -> return (a, b)
        _       -> error "Usage: battle input.txt output.txt"
    pts <- readInput inputFile
    let result = maxMinDist pts
    writeOutput outputFile result
