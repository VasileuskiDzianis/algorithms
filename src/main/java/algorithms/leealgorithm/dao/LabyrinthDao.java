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

		List<String> textScheme;
		
		Labyrinth labyrinth = new Labyrinth();

		textScheme = readSchemeFromFile(file);

		// Extract Labyrinths characteristics from 0 line
		Scanner scanner = new Scanner(textScheme.get(0));
		labyrinth.setLevelsNum(scanner.nextInt());
		labyrinth.setRowsNum(scanner.nextInt());
		labyrinth.setColumnsNum(scanner.nextInt());
		scanner.close();

		// Create scheme of each level
		String scheme[][][] = new String[labyrinth.getLevelsNum()][labyrinth.getRowsNum()][labyrinth.getColumnsNum()];

		for (int i = 0; i < labyrinth.getLevelsNum(); i++) {
			String line;
			for (int j = 0; j < labyrinth.getRowsNum(); j++) {
				line = textScheme.get(i * (labyrinth.getRowsNum() + 1) + 2 + j); // pass
																					// 2
				// lines
				for (int k = 0; k < labyrinth.getColumnsNum(); k++) {
					if (line.charAt(k) == '1') {
						labyrinth.setStartingColumn(k);
						labyrinth.setStartingRow(j);
						labyrinth.setStartingLevel(i);
						scheme[i][j][k] = "s"; // we use s - start because we
												// are going to use "Lee
												// Algorithm"
					} else if (line.charAt(k) == '2') {
						scheme[i][j][k] = "f"; // f - finish point
					} else {
						scheme[i][j][k] = String.valueOf(line.charAt(k));
					}
				}
			}
		}

		labyrinth.setScheme(scheme);

		return labyrinth;
	}

	private List<String> readSchemeFromFile(File file) {
		List<String> linesFromFile = new ArrayList<String>();
		String oneLineFromFile;
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			while ((oneLineFromFile = bufferedReader.readLine()) != null) {
				linesFromFile.add(oneLineFromFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return linesFromFile;
	}

}
