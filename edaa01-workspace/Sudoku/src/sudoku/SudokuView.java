package Sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

import javafx.scene.layout.Border;

public class SudokuView {
	private JTextField[][] textGrid;

	public SudokuView(String title, int width, int height) {
		textGrid = new JTextField[9][9];
		SwingUtilities.invokeLater(() -> createWindow(title, width, height));
	}

	private void createWindow(String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();

		// Skapar 2 panels, en där jag ska ha knappar och en där jag ska ha alla olika
		// rutor man kan skriva in siffror i
		JPanel buttonPanel = new JPanel();
		JPanel sudokuPanel = new JPanel(new GridLayout(9, 9));

		// Skapar och lägger till knappar på vår undre panel
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");

		buttonPanel.add(solve);
		buttonPanel.add(clear);

		//Bestämmer färgen för knappar och dess panel
		clear.setBackground(Color.YELLOW);
		solve.setBackground(Color.YELLOW);
		buttonPanel.setBackground(Color.BLACK);

		// Skapar en border
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.GRAY, 2);

		// Skapa ett JTextField längst hela rutnätet
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				JTextField field = new JTextField();
				textGrid[x][y] = field;

				//Storlek på JTextField-objekt
				field.setPreferredSize(new Dimension(height, width));
				//Lägger till i ordning vänster till höger
				sudokuPanel.add(field);
				//Lägger in vår border mellan JTextField-objekten
				field.setBorder(border);
				//Centrerar texten
				field.setHorizontalAlignment(JTextField.CENTER);
				//En font
				field.setFont(new Font("banan", Font.TYPE1_FONT, 40));
				if ((x > 2 && x < 6 && (y < 3 || y > 5)) || ((x < 3 || x > 5) && (y > 2 && y < 6))) {
					//Sätter att vissa regioner har svart text, gul bakgrund
					field.setForeground(Color.BLACK);
					field.setBackground(Color.YELLOW);
				} else {
					//Sätter att vissa regioner har gul text, svart bakgrund
					field.setBackground(Color.BLACK);
					field.setForeground(Color.YELLOW);
				}

			}
		}
		
		//Så att solve-knappen gör något
		solve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Om all indata är ok
				if (checkMatrix()) {
					//Om det 'r en tom ruta sätter vi en nolla då om det är null kommer inte .getText() fungera
					//Skapar en heltalsmatris 
					int[][] grid = new int[9][9];
					for (int x = 0; x < 9; x++) {
						for (int y = 0; y < 9; y++) {
							if (textGrid[x][y].getText().equals("")) {
								grid[x][y] = 0;
							} else {
								grid[x][y] = Integer.parseInt(textGrid[x][y].getText());
							}
						}
					}

					SudokuSolver sv = new SudokuSolver(grid);
					if (!sv.solve()) {
						JOptionPane.showMessageDialog(frame, "Unsolvable", "Error", JOptionPane.INFORMATION_MESSAGE);
					} else if (sv.solve()) {
						//Om solve() fungerar tar vi heltalsmatrisen från SudokuSolver och sen för vi över den till den 
						//interna matrisen (som används till gränssnittet
						int[][] theGrid = sv.get();
						for (int r = 0; r < 9; r++) {
							for (int c = 0; c < 9; c++) {
								textGrid[r][c].setText(theGrid[r][c] + "");
							}
						}
					}
					//Om checkMatrix() inte går igenom
				} else {
				    JOptionPane.showMessageDialog(frame, "Ej tillåtna tecken, lär dig lira sudoku", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// Om man klickar på knappen clear sätter den alla textFields till tomma
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int x = 0; x < 9; x++) {
					for (int y = 0; y < 9; y++) {
						textGrid[x][y].setText("");
					}
				}
			}
		});
		
		//Lägger till panels och grejer
		frame.add(sudokuPanel);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	//Kontrollerar att indata är rätt
	private boolean checkMatrix() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (!textGrid[x][y].getText().equals("")) {
					try {
						Integer.parseInt(textGrid[x][y].getText());
					} catch (Exception e) {
						return false;
					}
					if (Integer.parseInt(textGrid[x][y].getText()) < 1
							|| Integer.parseInt(textGrid[x][y].getText()) > 9) {
						return false;
					}
				}
			}
		}

		return true;
	}
}
