import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;

public class ThreadsOfSudoku {
    private static int nextIndex = 0;

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

            // create number threads based on arguments
            for (int i = 0; i < numThreads; i++) {
                final int threadId = i + 1;
                threads.add(new Thread(() -> solveSuds(suds, threadId)));
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

    private static void solveSuds(Sudoku[] suds, int id) {
        int index;
        while ((index = getSudIndex()) != -1) {
            System.out.println("Thread " + id + " solving " + index);
            if (suds[index].solve()) {
                synchronized (solutions) {
                    solutions.add(suds[index]);
                }
            }
        }
    }

    private static synchronized int getSudIndex() {
        if (nextIndex >= 80) {
            return -1;
        }
        return nextIndex++;
    }

    // END WORK HERE

    private static HashSet<Sudoku> solutions = new HashSet<>();
}
