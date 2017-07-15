package algorithms.leealgorithm.service;

import java.util.ArrayList;
import java.util.List;

import algorithms.leealgorithm.domain.Labyrinth;

/*
 * Dirty version of Lee Algorithm implementation in procedure-style.
 * For refactoring:
 * - unreadable conditions
 * - wasn't used OOP
 * - too long methods
 * - break operator in cycles
 * - bad name waveTemp
 * - getShortestPath () modifies original Labyrinth scheme
 */

public class PathTracerService {
	private String[][][] scheme;

	public List<int[]> getShortestPath(Labyrinth labyrinth) {

		List<int[]> wave = new ArrayList<int[]>(); // we will store here wave's
													// nodes
		scheme = labyrinth.getScheme();
		boolean success = false;
		int waveCounter = 0;

		wave.add(new int[] { labyrinth.getStartingLevel(), labyrinth.getStartingRow(), labyrinth.getStartingColumn() });

		int[] finishPointCoordinates = new int[3];

		do {
			List<int[]> waveFrontCoordinates = new ArrayList<int[]>();
			for (int[] node : wave) {

				// trying to build node on the next level
				if (isAdjacentCellExist(scheme, node, 1, 0, 0) && isPossibleToMoveAdjacentCell(scheme, node, 1, 0, 0)) {

					// add coordinates of a new node into a wave
					waveFrontCoordinates.add(new int[] { (node[0] + 1), node[1], node[2] });

					// set into labyrinth's node value of waveCounter
					scheme[(node[0] + 1)][node[1]][node[2]] = String.valueOf(waveCounter);
				} else if (isAdjacentCellExist(scheme, node, 1, 0, 0) && isFinishPoint(scheme, node, 1, 0, 0)) {

					// we found the finish point
					success = true;
					finishPointCoordinates[0] = (node[0] + 1);
					finishPointCoordinates[1] = (node[1]);
					finishPointCoordinates[2] = (node[2]);
					break;
				}

				// trying to build node on the left
				if (isAdjacentCellExist(scheme, node, 0, 0, -1)
						&& isPossibleToMoveAdjacentCell(scheme, node, 0, 0, -1)) {

					// add coordinates of a new node into a wave
					waveFrontCoordinates.add(new int[] { node[0], node[1], (node[2] - 1) });

					// set into labyrinth's node value of waveCounter
					scheme[node[0]][node[1]][(node[2] - 1)] = String.valueOf(waveCounter);
				} else if (isAdjacentCellExist(scheme, node, 0, 0, -1) && isFinishPoint(scheme, node, 0, 0, -1)) {

					// we found the finish point
					success = true;
					finishPointCoordinates[0] = (node[0]);
					finishPointCoordinates[1] = (node[1]);
					finishPointCoordinates[2] = (node[2] - 1);
					break;
				}

				// trying to build node above
				if (isAdjacentCellExist(scheme, node, 0, -1, 0)
						&& isPossibleToMoveAdjacentCell(scheme, node, 0, -1, 0)) {

					// add coordinates of a new node into a wave
					waveFrontCoordinates.add(new int[] { node[0], (node[1] - 1), node[2] });

					// set into labyrinth's node value of waveCounter
					scheme[node[0]][(node[1] - 1)][node[2]] = String.valueOf(waveCounter);
				} else if (isAdjacentCellExist(scheme, node, 0, -1, 0) && isFinishPoint(scheme, node, 0, -1, 0)) {

					// we found the finish point
					success = true;
					finishPointCoordinates[0] = (node[0]);
					finishPointCoordinates[1] = (node[1] - 1);
					finishPointCoordinates[2] = (node[2]);
					break;
				}

				// trying to build node on the right
				if ((isAdjacentCellExist(scheme, node, 0, 0, 1))
						&& isPossibleToMoveAdjacentCell(scheme, node, 0, 0, 1)) {

					// add coordinates of a new node into a wave
					waveFrontCoordinates.add(new int[] { node[0], node[1], (node[2] + 1) });

					// set into labyrinth's node value of waveCounter
					scheme[node[0]][node[1]][(node[2] + 1)] = String.valueOf(waveCounter);
				} else if ((isAdjacentCellExist(scheme, node, 0, 0, 1)) && isFinishPoint(scheme, node, 0, 0, 1)) {

					// we found the finish point
					success = true;
					finishPointCoordinates[0] = (node[0]);
					finishPointCoordinates[1] = (node[1]);
					finishPointCoordinates[2] = (node[2] + 1);
					break;
				}

				// trying to build node under
				if ((isAdjacentCellExist(scheme, node, 0, 1, 0))
						&& isPossibleToMoveAdjacentCell(scheme, node, 0, 1, 0)) {

					// add coordinates of a new node into a wave
					waveFrontCoordinates.add(new int[] { node[0], (node[1] + 1), node[2] });

					// set into labyrinth's node value of waveCounter
					scheme[node[0]][(node[1] + 1)][node[2]] = String.valueOf(waveCounter);
				} else if ((isAdjacentCellExist(scheme, node, 0, 1, 0)) && isFinishPoint(scheme, node, 0, 1, 0)) {

					// we found the finish point
					success = true;
					finishPointCoordinates[0] = (node[0]);
					finishPointCoordinates[1] = (node[1] + 1);
					finishPointCoordinates[2] = (node[2]);
					break;
				}
			}
			waveCounter++;
			wave = waveFrontCoordinates;
		} while (!success && wave.size() != 0);

		// build path from finish point to start point
		List<int[]> shortestPath = null;

		waveCounter = waveCounter - 2; // we get number of wave in adjacent cell
		shortestPath = new ArrayList<int[]>();
		for (int i = 0; i <= (waveCounter); i++) {

			// check upper level
			if (finishPointCoordinates[0] > 0
					&& isNextStepOfWave(scheme, finishPointCoordinates, waveCounter, i, -1, 0, 0)) {
				shortestPath.add(new int[] { (finishPointCoordinates[0] - 1), finishPointCoordinates[1],
						finishPointCoordinates[2] });
				finishPointCoordinates[0]--;

				// check from the left
			} else if (finishPointCoordinates[2] > 0
					&& isNextStepOfWave(scheme, finishPointCoordinates, waveCounter, i, 0, 0, -1)) {
				shortestPath.add(new int[] { finishPointCoordinates[0], finishPointCoordinates[1],
						finishPointCoordinates[2] - 1 });
				finishPointCoordinates[2]--;

				// check from the right
			} else if (finishPointCoordinates[2] < (labyrinth.getColumnsNum() - 1)
					&& isNextStepOfWave(scheme, finishPointCoordinates, waveCounter, i, 0, 0, 1)) {
				shortestPath.add(new int[] { finishPointCoordinates[0], finishPointCoordinates[1],
						finishPointCoordinates[2] + 1 });
				finishPointCoordinates[2]++;

				// check above
			} else if (finishPointCoordinates[1] > 0
					&& isNextStepOfWave(scheme, finishPointCoordinates, waveCounter, i, 0, -1, 0)) {
				shortestPath.add(new int[] { finishPointCoordinates[0], finishPointCoordinates[1] - 1,
						finishPointCoordinates[2] });
				finishPointCoordinates[1]--;
			}

			// check under
			if (finishPointCoordinates[1] < (labyrinth.getRowsNum() - 1)
					&& isNextStepOfWave(scheme, finishPointCoordinates, waveCounter, i, 0, 1, 0)) {
				shortestPath.add(new int[] { finishPointCoordinates[0], finishPointCoordinates[1] + 1,
						finishPointCoordinates[2] });
				finishPointCoordinates[1]++;
			}
		}

		return shortestPath;
	}

	public String[][][] getTraceMap() {

		return scheme;
	}

	private boolean isPossibleToMoveAdjacentCell(String[][][] scheme, int[] node, int levelOffset, int rowOffset,
			int columnOffset) {

		return (scheme[node[0] + levelOffset][node[1] + rowOffset][node[2] + columnOffset].equals("."));
	}

	private boolean isFinishPoint(String[][][] scheme, int[] node, int levelOffset, int rowOffset, int columnOffset) {

		return (scheme[node[0] + levelOffset][node[1] + rowOffset][node[2] + columnOffset].equals("f"));
	}

	private boolean isNextStepOfWave(String[][][] scheme, int finishPointCoordinates[], int waveCounter, int counter,
			int levelOffset, int rowOffset, int columnOffset) {

		return scheme[finishPointCoordinates[0] + levelOffset][finishPointCoordinates[1]
				+ rowOffset][finishPointCoordinates[2] + columnOffset].equals(String.valueOf(waveCounter - counter));
	}

	private boolean isAdjacentCellExist(String[][][] scheme, int[] node, int levelOffset, int rowOffset,
			int columnOffset) {
		try {
			scheme[node[0] + levelOffset][node[1] + rowOffset][node[2] + columnOffset].hashCode();

		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		return true;
	}
}