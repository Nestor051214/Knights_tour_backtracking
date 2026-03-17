# Knight's Tour Solver (Java)

A Java program that finds a **Knight's tour** on a square chessboard using backtracking with Warnsdorff's heuristic.  
The user can specify the board size, starting position, and whether to use the heuristic for faster search.

## 📌 What is a Knight's Tour?

A knight's tour is a sequence of moves of a knight on a chessboard such that the knight visits every square exactly once.  
If the knight ends on a square that is a knight's move away from the starting square, the tour is called **closed**; otherwise it is **open**.

This program finds **any valid tour** (open or closed) if one exists.

## ✨ Features

- Interactive console input: board size, starting position, choice of heuristic.
- Warnsdorff's heuristic: at each step, choose the move with the fewest onward moves (dramatically speeds up search).
- Pure backtracking option (slower, useful for demonstration).
- Displays the tour as a board with move numbers and as a sequence of coordinates.
- Handles invalid inputs gracefully.
- Works for boards up to 8×8 quickly; larger boards may take longer.

## 🧠 How It Works

1. The board is represented as a 2D array of integers (0 = unvisited).
2. Starting from the given position, the algorithm tries all legal knight moves.
3. If Warnsdorff's heuristic is enabled, the moves are sorted by the number of onward moves (fewest first) to prune dead ends early.
4. Recursive backtracking continues until either:
    - All squares are visited (success), or
    - No moves remain (failure, backtrack).
5. When a solution is found, the move numbers are stored and displayed.

## 🛠 Requirements

- Java 17 or higher (uses modern collections and features).
- No external libraries.

## 🚀 Compilation and Execution

```bash
javac *.java
java Main
```
## 📝 Usage Example
```
=====================================
        KNIGHT'S TOUR SOLVER        
=====================================
Enter board size (e.g., 5 for a 5x5 board): 5
Enter starting position (row and column, 0-based).
Row: 0
Column: 0
Use Warnsdorff's heuristic? (y/n): y

Searching for a tour... (this may take a moment for large boards)

✅ Tour found in 15 ms!

--- Board with move numbers ---
1 14  9 20  3
24 19  2 15 10
13  8 25  4 21
18 23  6 11 16
7 12 17 22  5

--- Path sequence ---
(0,0) -> (2,1) -> (4,2) -> (3,4) -> (1,3) -> (0,1) -> (2,2) -> (4,3) -> (3,1) -> (1,2) -> (0,4) -> (2,3) -> (4,4) -> (3,2) -> (1,1) -> (0,3) -> (2,4) -> (4,1) -> (3,3) -> (1,4) -> (0,2) -> (2,0) -> (4,0) -> (3,0) -> (1,0)
```

## 📁 Project Structure
Position.java:	
Immutable coordinate class (row, col).
KnightTour.java:
Core solver implementing backtracking and Warnsdorff's heuristic.
Main.java:	
Interactive console program that takes user input and displays results.

## 🔍 Possible Improvements
Add option to search for closed tours only.

Implement other heuristics (e.g., divide and conquer).

Add graphical output using ASCII or simple GUI.

Support loading/saving tours to file.

Add unit tests with JUnit.

## 📄 License
This project is open source and available under the MIT License.

## 👥 Authors

- **Nestor Nuñez Díaz** – Computer Science student at the Central University "Marta Abreu" of Las Villas, Cuba.  
  [![GitHub](https://img.shields.io/badge/GitHub-100000?style=flat&logo=github&logoColor=white)](https://github.com/Nestor051214)  
  📧 nestor141205@gmail.com

- **Adriana Isabel Acosta González** – Computer Science student at the Central University "Marta Abreu" of Las Villas, Cuba.  
  [![GitHub](https://img.shields.io/badge/GitHub-100000?style=flat&logo=github&logoColor=white)](https://github.com/acostaAdriana-cyber)  
  📧 acostagonzalez3136@gmail.com

**_This project was developed as an academic exercise in recursion, backtracking, and algorithm optimization_**
