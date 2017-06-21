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
	private String[][][] operatingLab;

	public List<int[]> getShortestPath(Labyrinth labyrinth) {

		List<int[]> wave = new ArrayList<int[]>(); // we will store here wave's
													// nodes
		operatingLab = labyrinth.getScheme();
		boolean success = false;
		int waveCounter = 0;

		wave.add(new int[] { labyrinth.getStartingLevel(), labyrinth.getStartingRow(), labyrinth.getStartingColumn() });

		int[] finishCoordinates = new int[3]; // princess coordinates

		List<int[]> shortestPath = null;

		do {
			List<int[]> waveTemp = new ArrayList<int[]>();
			for (int[] node : wave) {

				// trying to build node on the next level
				if (node[0] < (labyrinth.getLevelsNum() - 1) && // check
																// existence of
																// the next
																// level
						(operatingLab[(node[0] + 1)][node[1]][node[2]].equals("."))) {// check
																						// possibility
																						// to
																						// move
																						// to
																						// the
																						// next
																						// level

					// add coordinates of a new node into a wave
					waveTemp.add(new int[] { (node[0] + 1), node[1], node[2] });

					// set into labyrinth's node value of waveCounter
					operatingLab[(node[0] + 1)][node[1]][node[2]] = String.valueOf(waveCounter);
				} else if (node[0] < (labyrinth.getLevelsNum() - 1)
						&& (operatingLab[(node[0] + 1)][node[1]][node[2]].equals("f"))) {

					// we found a princess
					success = true;
					finishCoordinates[0] = (node[0] + 1);
					finishCoordinates[1] = (node[1]);
					finishCoordinates[2] = (node[2]);
					break;
				}

				// trying to build node on the left
				if ((node[2] > 0) && // check existence of a cell from the left
						(operatingLab[node[0]][node[1]][(node[2] - 1)].equals("."))) {// check
																						// possibility
																						// to
																						// make
																						// one
																						// step
																						// to
																						// the
																						// left

					// add coordinates of a new node into a wave
					waveTemp.add(new int[] { node[0], node[1], (node[2] - 1) });

					// set into labyrinth's node value of waveCounter
					operatingLab[node[0]][node[1]][(node[2] - 1)] = String.valueOf(waveCounter);
				} else if ((node[2] > 0) && (operatingLab[node[0]][node[1]][(node[2] - 1)].equals("f"))) {

					// we found a finish point
					success = true;
					finishCoordinates[0] = (node[0]);
					finishCoordinates[1] = (node[1]);
					finishCoordinates[2] = (node[2] - 1);
					break;
				}

				// trying to build node above
				if ((node[1] > 0) && // check existence of a cell above
						(operatingLab[node[0]][(node[1] - 1)][node[2]].equals("."))) {// check
																						// possibility
																						// to
																						// make
																						// one
																						// step
																						// up

					// add coordinates of a new node into a wave
					waveTemp.add(new int[] { node[0], (node[1] - 1), node[2] });

					// set into labyrinth's node value of waveCounter
					operatingLab[node[0]][(node[1] - 1)][node[2]] = String.valueOf(waveCounter);
				} else if ((node[1] > 0) && (operatingLab[node[0]][(node[1] - 1)][node[2]].equals("f"))) {

					// we found a finish point
					success = true;
					finishCoordinates[0] = (node[0]);
					finishCoordinates[1] = (node[1] - 1);
					finishCoordinates[2] = (node[2]);
					break;
				}

				// trying to build node on the right
				if ((node[2] < (labyrinth.getColumnsNum() - 1)) && // check
																	// existence
																	// of a cell
																	// from the
																	// right
						(operatingLab[node[0]][node[1]][(node[2] + 1)].equals("."))) {// check
																						// possibility
																						// to
																						// make
																						// one
																						// step
																						// to
																						// the
																						// right

					// add coordinates of a new node into a wave
					waveTemp.add(new int[] { node[0], node[1], (node[2] + 1) });

					// set into labyrinth's node value of waveCounter
					operatingLab[node[0]][node[1]][(node[2] + 1)] = String.valueOf(waveCounter);
				} else if ((node[2] < (labyrinth.getColumnsNum() - 1))
						&& (operatingLab[node[0]][node[1]][(node[2] + 1)].equals("f"))) {

					// we found a princess
					success = true;
					finishCoordinates[0] = (node[0]);
					finishCoordinates[1] = (node[1]);
					finishCoordinates[2] = (node[2] + 1);
					break;
				}

				// trying to build node under
				if ((node[1] < (labyrinth.getRowsNum() - 1)) && // check
																// existence of
																// a cell under
						(operatingLab[node[0]][(node[1] + 1)][node[2]].equals("."))) {// check
																						// possibility
																						// to
																						// make
																						// one
																						// step
																						// down

					// add coordinates of a new node into a wave
					waveTemp.add(new int[] { node[0], (node[1] + 1), node[2] });

					// set into labyrinth's node value of waveCounter
					operatingLab[node[0]][(node[1] + 1)][node[2]] = String.valueOf(waveCounter);
				} else if ((node[1] < (labyrinth.getRowsNum() - 1))
						&& (operatingLab[node[0]][(node[1] + 1)][node[2]].equals("f"))) {

					// we found a princess
					success = true;
					finishCoordinates[0] = (node[0]);
					finishCoordinates[1] = (node[1] + 1);
					finishCoordinates[2] = (node[2]);
					break;
				}

			}
			waveCounter++;
			wave = waveTemp;
		} while (!success && wave.size() != 0);

		// build path from princess to prince
		if (success) {
			waveCounter = waveCounter - 2; // we get number of wave in adjacent cell
			shortestPath = new ArrayList<int[]>();
			for (int i = 0; i <= (waveCounter); i++) {

				// check upper level
				if (finishCoordinates[0] > 0
						&& operatingLab[finishCoordinates[0] - 1][finishCoordinates[1]][finishCoordinates[2]]
								.equals(String.valueOf(waveCounter - i))) {
					shortestPath
							.add(new int[] { (finishCoordinates[0] - 1), finishCoordinates[1], finishCoordinates[2] });
					finishCoordinates[0]--;

					// check from the left
				} else if (finishCoordinates[2] > 0
						&& operatingLab[finishCoordinates[0]][finishCoordinates[1]][finishCoordinates[2] - 1]
								.equals(String.valueOf(waveCounter - i))) {
					shortestPath
							.add(new int[] { finishCoordinates[0], finishCoordinates[1], finishCoordinates[2] - 1 });
					finishCoordinates[2]--;
					
					// check from the right
				} else if (finishCoordinates[2] < (labyrinth.getColumnsNum() - 1)
						&& operatingLab[finishCoordinates[0]][finishCoordinates[1]][finishCoordinates[2] + 1]
								.equals(String.valueOf(waveCounter - i))) {
					shortestPath
							.add(new int[] { finishCoordinates[0], finishCoordinates[1], finishCoordinates[2] + 1 });
					finishCoordinates[2]++;

					// check above
				} else if (finishCoordinates[1] > 0
						&& operatingLab[finishCoordinates[0]][finishCoordinates[1] - 1][finishCoordinates[2]]
								.equals(String.valueOf(waveCounter - i))) {
					shortestPath
							.add(new int[] { finishCoordinates[0], finishCoordinates[1] - 1, finishCoordinates[2] });
					finishCoordinates[1]--;
				}
				
				// check under
				if (finishCoordinates[1] < (labyrinth.getRowsNum() - 1)
						&& operatingLab[finishCoordinates[0]][finishCoordinates[1] + 1][finishCoordinates[2]]
								.equals(String.valueOf(waveCounter - i))) {
					shortestPath
							.add(new int[] { finishCoordinates[0], finishCoordinates[1] + 1, finishCoordinates[2] });
					finishCoordinates[1]++;
				}
			}

		}

		return shortestPath;
	}

	public String[][][] getTraceMap() {

		return operatingLab;
	}

}
