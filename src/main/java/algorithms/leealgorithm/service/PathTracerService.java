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
 * - unreadable conditions - fixed (extract method)
 * - wasn't used OOP
 * - too long methods - fixed (extract method)
 * - break operator in cycles
 * - bad name waveTemp - fixed
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

	private LabyrinthService labyrinthService = new LabyrinthServiceImpl();

	private int waveCounter;

	public List<int[]> getShortestPath(Labyrinth labyrinth) {
		labyrinthService.setLabyrinth(labyrinth);
		drawWaveFieldInLabyrinthScheme(labyrinth);

		return calculateShortestPath(labyrinth);
	}

	private void drawWaveFieldInLabyrinthScheme(Labyrinth labyrinth) {
		List<int[]> previousWavePoints = new ArrayList<int[]>();

		previousWavePoints.add(labyrinth.getStartingPoint());

		do {
			List<int[]> waveFrontPoints = new ArrayList<int[]>();
			for (int[] currentPoint : previousWavePoints) {
				for (int[] offset : OFFSETS) {
					if (labyrinthService.isFinishPoint(currentPoint, offset)) {

						return;
					}
					if (labyrinthService.isPossibleToMoveAdjacentPoint(currentPoint, offset)) {
						waveFrontPoints.add(getAdjacentPoint(currentPoint, offset));
						setValueOfWaveCounterToScheme(labyrinth, currentPoint, offset, waveCounter);
					}
				}
			}
			waveCounter++;
			previousWavePoints = waveFrontPoints;

		} while (previousWavePoints.size() != 0);

		throw new RuntimeException("There is no possible way to rich finish point");
	}

	private List<int[]> calculateShortestPath(Labyrinth labyrinth) {
		int counter = waveCounter - 1; // we get number of wave in adjacent cell
		List<int[]> shortestPath = new ArrayList<int[]>();
		int[] currentPoint = Arrays.copyOf(labyrinth.getFinishPoint(), labyrinth.getFinishPoint().length);

		for (int i = 0; i <= (counter); i++) {
			for (int[] offset : OFFSETS) {
				if (labyrinthService.isAdjacentPointExist(currentPoint, offset)
						&& isNextStepOfWave(labyrinth.getScheme(), currentPoint, counter, i, offset)) {
					addNewPointToPath(shortestPath, currentPoint, offset);
					currentPoint = getAdjacentPoint(currentPoint, offset);

					break;
				}
			}
		}
		return shortestPath;
	}

	private void setValueOfWaveCounterToScheme(Labyrinth labyrinth, int[] point, int[] offset, int counter) {
		labyrinth.getScheme()[point[0] + offset[0]][point[1] + offset[1]][(point[2] + offset[2])] = String
				.valueOf(counter);
	}

	private int[] getAdjacentPoint(int[] currentPoint, int[] offset) {

		return new int[] { currentPoint[0] + offset[0], currentPoint[1] + offset[1], currentPoint[2] + offset[2] };
	}

	private boolean isNextStepOfWave(String[][][] scheme, int finishPointCoordinates[], int waveCounter, int counter,
			int[] offset) {

		return scheme[finishPointCoordinates[0] + offset[0]][finishPointCoordinates[1]
				+ offset[1]][finishPointCoordinates[2] + offset[2]].equals(String.valueOf(waveCounter - counter));
	}

	private void addNewPointToPath(List<int[]> path, int[] point, int[] offset) {
		path.add(new int[] { point[0] + offset[0], point[1] + offset[1], point[2] + offset[2] });
	}
}