package algorithms.leealgorithm.domain;

public class Labyrinth {

	private String[][][] scheme;
	private int[] startingPoint = new int[3];
	private int[] finishPoint = new int[3];

	private int levelsNum;
	private int rowsNum;
	private int columnsNum;

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

	public String[][][] getScheme() {
		return scheme;
	}

	public void setScheme(String[][][] levels) {
		this.scheme = levels;
	}

	public int[] getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(int[] startingPoint) {
		this.startingPoint = startingPoint;
	}

	public int[] getFinishPoint() {
		return finishPoint;
	}

	public void setFinishPoint(int[] finishPoint) {
		this.finishPoint = finishPoint;
	}
}
