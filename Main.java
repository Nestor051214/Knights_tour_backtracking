import java.util.Scanner;

/**
 * Interactive console program to find a Knight's tour on a square board.
 * The user can specify board size, starting position, and whether to use Warnsdorff's heuristic.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("=====================================");
            System.out.println("        KNIGHT'S TOUR SOLVER        ");
            System.out.println("=====================================");

            // Board size
            System.out.print("Enter board size (e.g., 5 for a 5x5 board): ");
            int size = Integer.parseInt(scanner.nextLine().trim());
            if (size < 1) {
                System.out.println("Size must be at least 1.");
                return;
            }

            // Starting position
            System.out.println("Enter starting position (row and column, 0-based).");
            System.out.print("Row: ");
            int startRow = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Column: ");
            int startCol = Integer.parseInt(scanner.nextLine().trim());

            // Heuristic choice
            System.out.print("Use Warnsdorff's heuristic? (y/n): ");
            String heuristicChoice = scanner.nextLine().trim().toLowerCase();
            boolean useWarnsdorff = heuristicChoice.startsWith("y");

            // Create solver
            KnightTour solver = new KnightTour(size, new Position(startRow, startCol), useWarnsdorff);

            System.out.println("\nSearching for a tour... (this may take a moment for large boards)");
            long startTime = System.currentTimeMillis();
            boolean found = solver.solve();
            long elapsed = System.currentTimeMillis() - startTime;

            if (found) {
                System.out.println("\n✅ Tour found in " + elapsed + " ms!");
                System.out.println("\n--- Board with move numbers ---");
                System.out.println(solver.getBoardAsString());
                System.out.println("\n--- Path sequence ---");
                System.out.println(solver.getPathAsString());
            } else {
                System.out.println("\n❌ No tour exists from this starting position (or algorithm failed).");
                System.out.println("Note: For boards smaller than 5x5, a closed tour may not exist.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}