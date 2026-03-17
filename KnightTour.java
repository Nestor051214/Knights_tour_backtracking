import java.util.*;

/**
 * Solves the Knight's Tour problem using backtracking with Warnsdorff's heuristic.
 * Finds a sequence of moves that visits every square exactly once (if it exists).
 */
public class KnightTour {
    private final int size;                // board size (size x size)
    private final Position start;           // starting position
    private final boolean useWarnsdorff;    // heuristic or pure backtracking
    private int[][] board;                  // move numbers (0 = unvisited)
    private List<Position> solution;        // ordered list of positions in the tour
    private boolean solved;

    // All possible knight moves (8 directions)
    private static final int[][] MOVES = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
    };

    /**
     * Constructor.
     *
     * @param size           board size (size x size)
     * @param start          starting position
     * @param useWarnsdorff  true to use Warnsdorff's heuristic, false for pure backtracking
     */
    public KnightTour(int size, Position start, boolean useWarnsdorff) {
        if (size < 1) throw new IllegalArgumentException("Board size must be positive.");
        if (start.getRow() < 0 || start.getRow() >= size || start.getCol() < 0 || start.getCol() >= size)
            throw new IllegalArgumentException("Start position out of board.");
        this.size = size;
        this.start = start;
        this.useWarnsdorff = useWarnsdorff;
        this.board = new int[size][size];
        this.solution = new ArrayList<>();
        this.solved = false;
    }

    /**
     * Attempts to find a Knight's tour.
     * @return true if a tour was found, false otherwise.
     */
    public boolean solve() {
        // Initialize board: all zeros
        for (int i = 0; i < size; i++) Arrays.fill(board[i], 0);
        solved = false;
        solution.clear();

        // Start the tour
        board[start.getRow()][start.getCol()] = 1;
        solution.add(start);
        solved = backtrack(start, 2); // next move number is 2
        return solved;
    }

    /**
     * Recursive backtracking search.
     *
     * @param pos    current position
     * @param moveNo next move number to assign
     * @return true if a complete tour was found from this state
     */
    private boolean backtrack(Position pos, int moveNo) {
        if (moveNo > size * size) {
            return true; // all squares visited
        }

        List<Position> candidates = getCandidates(pos);

        if (useWarnsdorff) {
            // Sort candidates by number of onward moves (Warnsdorff's heuristic)
            candidates.sort(Comparator.comparingInt(this::countOnwardMoves));
        }

        for (Position next : candidates) {
            board[next.getRow()][next.getCol()] = moveNo;
            solution.add(next);
            if (backtrack(next, moveNo + 1)) {
                return true;
            }
            // backtrack
            board[next.getRow()][next.getCol()] = 0;
            solution.remove(solution.size() - 1);
        }
        return false;
    }

    /**
     * Returns all legal, unvisited moves from a given position.
     */
    private List<Position> getCandidates(Position pos) {
        List<Position> candidates = new ArrayList<>();
        for (int[] m : MOVES) {
            int nr = pos.getRow() + m[0];
            int nc = pos.getCol() + m[1];
            if (nr >= 0 && nr < size && nc >= 0 && nc < size && board[nr][nc] == 0) {
                candidates.add(new Position(nr, nc));
            }
        }
        return candidates;
    }

    /**
     * Counts how many unvisited moves are available from a given position.
     * Used by Warnsdorff's heuristic.
     */
    private int countOnwardMoves(Position pos) {
        int count = 0;
        for (int[] m : MOVES) {
            int nr = pos.getRow() + m[0];
            int nc = pos.getCol() + m[1];
            if (nr >= 0 && nr < size && nc >= 0 && nc < size && board[nr][nc] == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a copy of the solution path (list of positions in order).
     * @return list of positions, or empty list if not solved.
     */
    public List<Position> getSolution() {
        return new ArrayList<>(solution);
    }

    /**
     * Returns a string representation of the board with move numbers.
     */
    public String getBoardAsString() {
        if (!solved) return "No solution found.";
        StringBuilder sb = new StringBuilder();
        int cellWidth = String.valueOf(size * size).length();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                sb.append(String.format("%" + cellWidth + "d ", board[r][c]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a string with the sequence of positions.
     */
    public String getPathAsString() {
        if (!solved) return "No solution found.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < solution.size(); i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(solution.get(i));
        }
        return sb.toString();
    }
}