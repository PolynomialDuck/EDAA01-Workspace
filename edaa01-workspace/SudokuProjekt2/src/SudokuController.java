
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;

public class SudokuController { 

	public SudokuController(SudokuBoard s) {
		SwingUtilities.invokeLater(() -> createWindow(s, "SudokuSolver", 400, 300)); 
	}

	public void createWindow(SudokuBoard s, String title, int width, int height) {
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		JPanel panelSouth = new JPanel(); 
		JButton solve = new JButton("Solve"); 
		panelSouth.add(solve);
		JButton clear = new JButton("Clear");
		panelSouth.add(clear);
		JButton print = new JButton("Print"); 
		panelSouth.add(print); 
		
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(9, 9));
		JTextField[][] grid = new JTextField[9][9];

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				JTextField tf = new JTextField();
				if (isRedArea(r, c)) { // Använder en privat hjälpmetod
					tf.setBackground(Color.red);
				}
				tf.setHorizontalAlignment(JTextField.CENTER);
				grid[r][c] = tf;
				panelNorth.add(tf); 
			}
		}

		print.addActionListener(event -> {
			s.printSudoku();
		}); 

		solve.addActionListener(event -> {
			s.clear(); 
			boolean exceptionCalled = false; 
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					if (!grid[r][c].getText().isEmpty()) {
						try {
							int nbr = Integer.parseInt(grid[r][c].getText()); // måste göra om till int
							s.setNumber(r, c, nbr);
						} catch (IllegalArgumentException e) {
							exceptionCalled = true;
						}
					}
				}
			}
			
			JOptionPane jOption = new JOptionPane();
			
			if (exceptionCalled) {
				jOption.showMessageDialog(jOption, "Illegal numer for Sudoku");
			} else {
				if (s.solve()) {
					for (int r = 0; r < 9; r++) {
						for (int c = 0; c < 9; c++) {
							if (grid[r][c].getText().isEmpty()) {
								grid[r][c].setText(Integer.toString(s.getNumber(r, c)));
							}
						}
					}
				} else { // om ingen lösning  
					JOptionPane.showMessageDialog(jOption, "No possible solution");
				}
			} 
		});

		clear.addActionListener(event -> {
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					grid[r][c].setText(""); // tömmer hela UX
				} 
			}
			s.clear(); // riktiga clear 
		});

		pane.add(panelSouth, BorderLayout.SOUTH);
		pane.add(panelNorth, BorderLayout.NORTH);

		frame.setSize(width, height);
		frame.setVisible(true);

	} 

	private boolean isRedArea(int r, int c) {
		return (r / 3 != 1 && c / 3 != 1) || (r / 3 == 1 && c / 3 == 1);
	}

}