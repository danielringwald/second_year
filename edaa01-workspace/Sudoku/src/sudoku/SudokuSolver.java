package Sudoku;

import javax.swing.JTextField;

public class SudokuSolver {
	private int[][] grid;

	
	/**Creates a SudokuSolver object which takes a matrix as parameter. 
	 * 
	 * 
	 * 
	 * @param grid int[][] which represents a sudoku
	 */
	public SudokuSolver(int[][] grid) {
		this.grid = grid;

	}

	
	/**This method checks to see if the Sudoku is solvable. 
	 * If a solution is found it changes the grid to the solved one but if it doesn't find a solution the grid
	 * is unchanged.
	 * 
	 * @return Returns true when sudoku is solvable and false when it is unsolvable.
	 */
	public boolean solve() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (!valid(r, c)) {
					return false;
				}
			}
		}
		return solve(0, 0);
	}

	private boolean solve(int r, int c) {
		if (r > 8) {
			return true;
		}
		int nextCol = c + 1;
		int nextRow = r;
		if (nextCol >= 9) {
			nextCol = 0;
			nextRow++;
		}

		if (grid[r][c] != 0) {
			return valid(r, c) && solve(nextRow, nextCol);
		} else {
			for (int n = 1; n < 10; n++) {
				put(n, r, c);
				if (valid(r, c) && solve(nextRow, nextCol)) {
					return true;
				}
			}
			put (0, r, c);
			return false;
		}

	}

	private boolean valid(int r, int c) throws IllegalArgumentException {
		if (r < 0 || r >= 9 || c < 0 || c >= 9) {
			throw new IllegalArgumentException();
		} else if (grid[r][c] == 0) {
			return true;
		}

		// Kolla att det stämmer i raden
		for (int i = 0; i < 9; i++) {
			if (i != r && grid[r][c] == grid[i][c]) {
				return false;
			}
		}
		// Kolla att kolumner stämmer
		for (int i = 0; i < 9; i++) {
			if (i != c && grid[r][c] == grid[r][i]) {
				return false;
			}
		}

		// Kolla regioner
		int rowReg = (int) (r / 3) * 3;
		int colReg = (int) (c / 3) * 3;
		for (int i = rowReg; i < rowReg + 3; i++) {
			for (int k = colReg; k < colReg + 3; k++) {
				if (i != r && k != c && grid[r][c] == grid[i][k]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**This method returns the matrix, solved or not. 
	 * 
	 * @return Returns the matrix which represents the sudoku
	 */
	public int[][] get() {
		return grid;
	}

	
	private void put(int n, int r, int c) {
		grid[r][c] = n;
	}

}
