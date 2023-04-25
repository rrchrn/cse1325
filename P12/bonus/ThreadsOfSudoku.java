import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;

public class ThreadsOfSudoku {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.err.println("usage: java ThreadsOfSudoku threads puzzleFilename [puzzleName]");
                System.exit(-1);
            }

            Scanner in = new Scanner(new File(args[1]));
            String name = (args.length > 2) ? args[2] : null;
            Sudoku sud = new Sudoku(in, name);
            System.out.println(sud + "\n\n");

            int numThreads = Integer.parseInt(args[0]);

            // Split the search field into 81 different sub-puzzles
            Sudoku[] suds = new Sudoku[81];
            int index = 0;
            for (int y = 1; y <= 9; ++y) {
                for (int x = 1; x <= 9; ++x) {
                    suds[index++] = new Sudoku(sud, new int[] { x, y });
                }
            }

            // Create list of threads
            ArrayList<Thread> threads = new ArrayList<>();

            // split sub-puzzles into numThreads groups
            int indices = 81;
            int subIndices = indices / numThreads;
            int subIndicesRemainder = indices % numThreads;

            ArrayList<Integer> ranges = new ArrayList<>();

            int curr = 0;
            for (int i = 0; i < numThreads; i++, subIndicesRemainder--) {
                ranges.add(curr);
                curr += subIndices;
                if (subIndicesRemainder > 0) {
                    curr += 1;
                    subIndicesRemainder--;
                }
                if (indices >= curr) {
                    ranges.add(curr);
                } else {
                    ranges.add(81);
                }
            }

            // create number threads based on arguments
            for (int i = 0; i < numThreads; i++) {
                final int threadId = i + 1;
                final int startIndex = ranges.get(i * 2);
                final int endIndex = ranges.get(i * 2 + 1);
                threads.add(new Thread(() -> solveSuds(suds, startIndex, endIndex, threadId)));
            }

            // Start the threads
            for (Thread t : threads) {
                t.start();
            }

            // Wait for the threads to finish
            for (Thread t : threads) {
                t.join();
            }

            // Show the solution(s), if any
            System.out.println("Found " + solutions.size() + " solutions");
            for (var s : solutions)
                System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }

    private static void solveSuds(Sudoku[] suds, int first, int last, int id) {
        System.out.println("Thread " + id + " will solve " + first + " to " + last);
        for (int i = first; i < last; ++i) {
            System.out.println("Thread " + id + " solving " + i);
            if (suds[i].solve()) {
                synchronized (solutions) {
                    solutions.add(suds[i]);
                }
            }
        }
    }

    // END WORK HERE

    private static HashSet<Sudoku> solutions = new HashSet<>();
}
