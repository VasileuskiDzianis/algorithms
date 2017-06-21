package algorithms.leealgorithm.domain;

public class Labyrinth {

	private String[][][] scheme;

	//starting point
	private int startingLevel;
	private int startingRow;
	private int startingColumn;

	//scheme parameters
	private int levelsNum;
	private int rowsNum;
	private int columnsNum;

	private int counter;

	public int getLevelsNum() {
		return levelsNum;
	}

	public void setLevelsNum(int levelsNum) {
		this.levelsNum = levelsNum;
	}

	public int getRowsNum() {
		return rowsNum;
	}

	public void setRowsNum(int rowsNum) {
		this.rowsNum = rowsNum;
	}

	public int getColumnsNum() {
		return columnsNum;
	}

	public void setColumnsNum(int columnsNum) {
		this.columnsNum = columnsNum;
	}

	public int getCounter() {
		return counter;
	}

	public String[][][] getScheme() {
		return scheme;
	}

	public void setScheme(String[][][] levels) {
		this.scheme = levels;
	}

	public int getStartingLevel() {
		return startingLevel;
	}

	public void setStartingLevel(int startingLevel) {
		this.startingLevel = startingLevel;
	}

	public int getStartingRow() {
		return startingRow;
	}

	public void setStartingRow(int startingRow) {
		this.startingRow = startingRow;
	}

	public int getStartingColumn() {
		return startingColumn;
	}

	public void setStartingColumn(int startingColumn) {
		this.startingColumn = startingColumn;
	}

}
