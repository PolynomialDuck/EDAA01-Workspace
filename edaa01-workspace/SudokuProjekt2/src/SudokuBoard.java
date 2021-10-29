
public class SudokuBoard implements SudokuSolver {

	private int[][] board;

	public SudokuBoard() {
		
		board = new int[9][9];
	}

	@Override
	public void setNumber(int r, int c, int nbr) {
		if (isValidCoordinate(r, c) && isValidNbr(nbr)) { // utnyttjar två hjälpmetoder
			board[r][c] = nbr;
		}
	}

	@Override
	public int getNumber(int r, int c) {
		if (isValidCoordinate(r, c)) {
			return board[r][c];
		}
		return 0; // Kommer ej hamna här
	}

	@Override
	public void clearNumber(int r, int c) {
		if (isValidCoordinate(r, c)) {
			board[r][c] = 0;
		}
	}

	@Override
	public boolean isValid(int r, int c, int nbr) {

		if (isValidCoordinate(r, c) && nbr == 0) { // låter 0:or = "tom" ruta vara ok
			return true;
		}

		if (isValidCoordinate(r, c) && isValidNbr(nbr)) {
			for (int rTest = 0; rTest < board.length; rTest++) { // itererar genom alla rader i kolumnen, kollar om
																	// siffran vi letar efter finns där. (exkluderar den
																	// rad vi vill sätta in på)
				if (rTest != r && nbr == board[rTest][c]) {
					return false;
				}
			}
			for (int cTest = 0; cTest < board.length; cTest++) { // samma som ovan för raden
				if (cTest != c && nbr == board[r][cTest]) {
					return false;
				}
			}

			int boxRow = (r / 3) * 3; // kommer ge antingen 0, 3, eller 6 --> första raden på boxen
			int boxCol = (c / 3) * 3; // första kolumn 

			for (int i = boxRow; i < boxRow + 3; i++) {
				for (int j = boxCol; j < boxCol + 3; j++) {
					if (i != r && j != c && board[i][j] == nbr) { // om boxen innehåller vårt nbr, return false
						return false;
					} 
				}
			}
			return true;
		}
		return false; // bör aldrig hamna här
	}

	@Override
	public boolean isAllValid() { // löper igenom hela, kollar om valid
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (!isValid(i, j, board[i][j])) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int r, int c) {
		if (c > 8) { // Om vi måste byta rad
			c = 0;
			r++;
		}
		if (board[r][c] != 0) {
			if (isValid(r, c, getNumber(r, c))) { // går bara vidare om OK
				return solve(r, c + 1);
			} else {
				return false;
			}
		} else {
			for (int n = 1; n < 10; n++) {
				if (isValid(r, c, n)) {
					setNumber(r, c, n);
					if (r == 8 && c == 8) { // Specialfall för om vi hamnar på sista elementet
						return true;
					}
					if (solve(r, c + 1)) {
						return true;
					} else {
						clearNumber(r, c); // om vi misslyckas kommer vi ta bort hela vägen
					}
				}
			}
			return false;
		}
	}

	public void printSudoku() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + " - ");
			}
			System.out.println();
		}
	}

	@Override
	public void clear() {
		board = new int[9][9]; // funkar lika bra och snabbare än att använda setMatrix med 0!
	}

	@Override
	public int[][] getMatrix() {
		return board;
	}

	@Override
	public void setMatrix(int[][] nbrs) {

		if (nbrs.length != board.length || nbrs[0].length != board[0].length) {
			throw new IllegalArgumentException("Your board must be 9x9");
		}
		for (int i = 0; i < nbrs.length; i++) {
			for (int j = 0; j < nbrs[0].length; j++) {
				if (nbrs[i][j] < 0 || nbrs[i][j] > 9) {
					throw new IllegalArgumentException("Board is containing values not in [0..9]");
				}
			}
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = nbrs[i][j];
			}
		}
	}

	/**
	 * Checks if the coordinates for placement is OK
	 * 
	 * @param r The row
	 * @param c The column
	 * @throws IllegalArgumentException
	 */
	private boolean isValidCoordinate(int r, int c) {
		if (r < 0 || r > 8 || c < 0 || c > 8) {
			throw new IllegalArgumentException("Illegal coordinates for Sudoku");
		}
		return true;
	}

	/**
	 * Checks if the Nbr is between 1-9
	 * 
	 * @param r The row
	 * @param c The column
	 * @throws IllegalArgumentException
	 */
	private boolean isValidNbr(int nbr) {
		if (nbr < 1 || nbr > 9) {
			throw new IllegalArgumentException("Illegal numer for Sudoku");
		}
		return true;
	}

}