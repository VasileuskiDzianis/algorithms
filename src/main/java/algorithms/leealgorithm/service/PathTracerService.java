package algorithms.leealgorithm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import algorithms.leealgorithm.domain.Labyrinth;

/*
 * Dirty version of Lee Algorithm implementation in procedure-style.
 * For refactoring:
 * - unreadable conditions
 * - wasn't used OOP
 * - too long methods
 * - break operator in cycles
 * - bad name waveTemp - fixed
 * - getShortestPath () modifies original Labyrinth scheme
 */

public class PathTracerService {
	private static final int[] OFFSET_Z_DOWN = { 1, 0, 0 };
	private static final int[] OFFSET_Z_UP = { -1, 0, 0 };
	private static final int[] OFFSET_X_LEFT = { 0, 0, -1 };
	private static final int[] OFFSET_X_RIGHT = { 0, 0, 1 };
	private static final int[] OFFSET_Y_UP = { 0, -1, 0 };
	private static final int[] OFFSET_Y_DOWN = { 0, 1, 0 };

	private static final Set<int[]> OFFSETS = new HashSet<int[]>(
			Arrays.asList(OFFSET_Z_DOWN, OFFSET_Z_UP, OFFSET_X_LEFT, OFFSET_X_RIGHT, OFFSET_Y_UP, OFFSET_Y_DOWN));

	private String[][][] scheme;

	public List<int[]> getShortestPath(Labyrinth labyrinth) {

		List<int[]> wave = new ArrayList<int[]>();
		scheme = labyrinth.getScheme();
		boolean finishPointWasFound = false;
		int waveCounter = 0;

		wave.add(new int[] { labyrinth.getStartingLevel(), labyrinth.getStartingRow(), labyrinth.getStartingColumn() });

		int[] finishPoint = new int[3];

		do {
			List<int[]> waveFrontPoints = new ArrayList<int[]>();
			for (int[] node : wave) {

				for (int[] offset : OFFSETS) {
					if (isAdjacentCellExist(scheme, node, offset)
							&& isPossibleToMoveAdjacentCell(scheme, node, offset)) {

						waveFrontPoints.add(getAdjacentPoint(node, offset));
						setValueOfWaveCounter(scheme, node, offset, waveCounter);
					} else if (isAdjacentCellExist(scheme, node, offset) && isFinishPoint(scheme, node, offset)) {

						finishPointWasFound = true;

						finishPoint = getFinishPoint(node, offset);

						return buildShortestPath(waveCounter, finishPoint, labyrinth);
					}

				}

			}
			waveCounter++;
			wave = waveFrontPoints;
		} while (!finishPointWasFound && wave.size() != 0);

		return null;
	}

	private void setValueOfWaveCounter(String[][][] scheme, int[] node, int[] offset, int counter) {

		scheme[node[0] + offset[0]][node[1] + offset[1]][(node[2] + offset[2])] = String.valueOf(counter);
	}

	private List<int[]> buildShortestPath(int waveCounter, int[] finishPoint, Labyrinth labyrinth) {
		int counter = waveCounter - 1; // we get number of wave in adjacent cell
		List<int[]> shortestPath = new ArrayList<int[]>();
		int[] currentPoint = Arrays.copyOf(finishPoint, finishPoint.length);

		for (int i = 0; i <= (counter); i++) {

			for (int[] offset : OFFSETS) {
				if (isAdjacentCellExist(labyrinth.getScheme(), currentPoint, offset)
						&& isNextStepOfWave(labyrinth.getScheme(), currentPoint, counter, i, offset)) {
					addNewPointToPath(shortestPath, currentPoint, offset);
					currentPoint = getAdjacentPoint(currentPoint, offset);
					break;
				}
			}

		}

		return shortestPath;
	}

	private int[] getAdjacentPoint(int[] currentPoint, int[] offset) {
		
		return new int[] {currentPoint[0] + offset[0], currentPoint[1] + offset[1], currentPoint[2] + offset[2]};
	} 

	private boolean isPossibleToMoveAdjacentCell(String[][][] scheme, int[] node, int[] offset) {

		return (scheme[node[0] + offset[0]][node[1] + offset[1]][node[2] + offset[2]].equals("."));
	}

	private boolean isFinishPoint(String[][][] scheme, int[] node, int[] offset) {

		return (scheme[node[0] + offset[0]][node[1] + offset[1]][node[2] + offset[2]].equals("f"));
	}

	private boolean isNextStepOfWave(String[][][] scheme, int finishPointCoordinates[], int waveCounter, int counter,
			int[] offset) {

		return scheme[finishPointCoordinates[0] + offset[0]][finishPointCoordinates[1]
				+ offset[1]][finishPointCoordinates[2] + offset[2]].equals(String.valueOf(waveCounter - counter));
	}

	private boolean isAdjacentCellExist(String[][][] scheme, int[] node, int[] offset) {
		try {
			scheme[node[0] + offset[0]][node[1] + offset[1]][node[2] + offset[2]].hashCode();

		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		return true;
	}

	private int[] getFinishPoint(int[] node, int[] offset) {
		int[] finishPoint = { node[0] + offset[0], node[1] + offset[1], node[2] + offset[2] };

		return finishPoint;
	}

	public String[][][] getTraceMap() {

		return scheme;
	}

	private void addNewPointToPath(List<int[]> path, int[] point, int[] offset) {

		path.add(new int[] { point[0] + offset[0], point[1] + offset[1], point[2] + offset[2] });
	}
}