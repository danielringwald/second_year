package Sudoku;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SudokuTest {
	SudokuSolver sv;
	int[][] matrix;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		matrix = new int[9][9];
		sv = new SudokuSolver(matrix);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testEmptySudoku() {
		assertTrue(sv.solve());
		
	}
	
	@Test
	public void testSudokuFig1() {
		matrix[2][0] = 8;
		matrix[5][0] = 9;
		matrix[7][0] = 6;
		matrix[8][0] = 2;
		matrix[8][1] = 5;
		matrix[0][2] = 1;
		matrix[2][2] = 2;
		matrix[3][2] = 5;
		matrix[3][3] = 2;
		matrix[4][3] = 1;
		matrix[7][3] = 9;
		matrix[1][4] = 5;
		matrix[6][4] = 6;
		matrix[0][5] = 6;
		matrix[7][5] = 2;
		matrix[8][5] = 8;
		matrix[0][6] = 4;
		matrix[1][6] = 1;
		matrix[3][6] = 6;
		matrix[5][6] = 8;
		matrix[0][7] = 8;
		matrix[1][7] = 6;
		matrix[4][7] = 3;
		matrix[6][7] = 1;
		matrix[6][8] = 4;
		assertTrue(sv.solve());
	}
	
	@Test
	public void testSudokuUnsolvable() {
		matrix[2][2] = 4;
		matrix[7][2] = 4;
		assertFalse(sv.solve());
	}
	

}
