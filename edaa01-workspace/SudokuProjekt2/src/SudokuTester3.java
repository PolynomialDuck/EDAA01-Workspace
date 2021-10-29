import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTester3 {
	private SudokuBoard sb;
	
	@BeforeEach
	void setUp() throws Exception {
		sb = new SudokuBoard();
	}

	@AfterEach
	void tearDown() throws Exception {
		sb = null; 
	}

	@Test
	void testSolvingEmptySb() {
		assertTrue(sb.solve());
	}
	
	@Test
	void solveOfSolvableSb() {
		int[][] nbrs = new int[9][9];
		
		nbrs[0][0] = 9;
		nbrs[0][1] = 8;
		nbrs[0][2] = 7;
		nbrs[8][0] = 6;
		nbrs[2][3] = 4;
		nbrs[7][3] = 8;
		nbrs[5][6] = 2;
		
		sb.setMatrix(nbrs);
		assertTrue(sb.solve());
	}
	
	@Test
	public void solveOfUnsolvableSb() {
		int[][] nbrs = new int[9][9];
		
		nbrs[0][0] = 1;
		nbrs[0][1] = 1;
		nbrs[5][5] = 1;
		nbrs[5][6] = 1;
		
		sb.setMatrix(nbrs);
		assertFalse(sb.solve()); // då den ej går att lösa 
	}
	
	@Test
	public void solveOfUnsolvableSb2() {
		int[][] nbrs = new int[9][9];
		
		nbrs[0][0] = 9;
		nbrs[0][1] = 8;
		nbrs[0][2] = 7;
		nbrs[8][0] = 6;
		nbrs[2][3] = 4;
		nbrs[7][3] = 8;
		nbrs[5][6] = 2;
		
		nbrs[0][0] = 1;
		nbrs[0][1] = 1;
		nbrs[5][5] = 1;
		nbrs[5][6] = 1;
		
		sb.setMatrix(nbrs);
		assertFalse(sb.solve()); 
	}

}