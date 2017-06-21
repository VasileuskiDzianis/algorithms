package algorithms.leealgorithm.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import algorithms.leealgorithm.domain.Labyrinth;

public class LabyrinthDao {

	private File file;

	public void setFile(File file) {
		this.file = file;
	}

	public Labyrinth getPathTracer() {

		List<String> textLines;
		String line;
		BufferedReader fin = null;
		int levelsAmount; 
		int rowsAmount; 
		int columnsAmount;

		int startingLevel = 0;
		int startingRow = 0;
		int startingColumn = 0;

		try {
			fin = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		textLines = new ArrayList<String>();
		
		try {
			while ((line = fin.readLine()) != null) {
				textLines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Extract Labyrinths characteristics from 0 line
		Scanner scanner = new Scanner(textLines.get(0));
		levelsAmount = scanner.nextInt();
		rowsAmount = scanner.nextInt();
		columnsAmount = scanner.nextInt();
		scanner.close();

		// Create scheme of each level
		String scheme[][][] = new String[levelsAmount][rowsAmount][columnsAmount];

		for (int i = 0; i < levelsAmount; i++) {
			for (int j = 0; j < rowsAmount; j++) {
				line = textLines.get(i * (rowsAmount + 1) + 2 + j); // we pass 2 first lines
				for (int k = 0; k < columnsAmount; k++) {
					if (line.charAt(k) == '1') {
						startingColumn = k;
						startingRow = j;
						startingLevel = i;
						scheme[i][j][k] = "s"; // we use s - start because we
													// are going to use "Lee
													// Algorithm"
					} else if (line.charAt(k) == '2') {
						scheme[i][j][k] = "f"; //f - finish point
					} else {
						scheme[i][j][k] = String.valueOf(line.charAt(k));
					}
				}
			}
		}
		
		Labyrinth labyrinth = new Labyrinth();
		
		labyrinth.setScheme(scheme);
		labyrinth.setStartingLevel(startingLevel);
		labyrinth.setStartingRow(startingRow);
		labyrinth.setStartingColumn(startingColumn);
		labyrinth.setLevelsNum(levelsAmount);
		labyrinth.setRowsNum(rowsAmount);
		labyrinth.setColumnsNum(columnsAmount);

		return labyrinth;
	}
}
