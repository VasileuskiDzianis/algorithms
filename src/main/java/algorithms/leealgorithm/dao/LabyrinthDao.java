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

	public Labyrinth getLabyrinth() {

		List<String> textScheme = readSchemeFromFile(file);

		Labyrinth labyrinth = new Labyrinth();

		setLabyrinthCharacteristics(labyrinth, textScheme.get(0));

		labyrinth.setScheme(parseTextSchemeToArraySchemeAndSetStartingPoint(textScheme, labyrinth));

		return labyrinth;
	}

	private String[][][] parseTextSchemeToArraySchemeAndSetStartingPoint(List<String> textScheme, Labyrinth labyrinth) {
		String scheme[][][] = new String[labyrinth.getLevelsNum()][labyrinth.getRowsNum()][labyrinth.getColumnsNum()];

		for (int i = 0; i < labyrinth.getLevelsNum(); i++) {
			String line;
			for (int j = 0; j < labyrinth.getRowsNum(); j++) {
				line = textScheme.get(i * (labyrinth.getRowsNum() + 1) + 2 + j); // pass
																					// 2
				// lines
				for (int k = 0; k < labyrinth.getColumnsNum(); k++) {
					if (line.charAt(k) == '1') {
						labyrinth.setStartingPoint(new int[]{i, j, k});
						
						scheme[i][j][k] = "s"; // we use s - start because we
												// are going to use "Lee
												// Algorithm"
					} else if (line.charAt(k) == '2') {
						scheme[i][j][k] = "f"; // f - finish point
						labyrinth.setFinishPoint(new int[]{i, j, k});
						
					} else {
						scheme[i][j][k] = String.valueOf(line.charAt(k));
					}
				}
			}
		}
		return scheme;
	}

	private void setLabyrinthCharacteristics(Labyrinth labyrinth, String characteristicsLine) {
		Scanner scanner = new Scanner(characteristicsLine);

		labyrinth.setLevelsNum(scanner.nextInt());
		labyrinth.setRowsNum(scanner.nextInt());
		labyrinth.setColumnsNum(scanner.nextInt());

		scanner.close();
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
