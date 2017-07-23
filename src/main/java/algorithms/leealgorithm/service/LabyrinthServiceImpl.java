package algorithms.leealgorithm.service;

import algorithms.leealgorithm.domain.Labyrinth;

public class LabyrinthServiceImpl implements LabyrinthService {

	private Labyrinth labyrinth;

	@Override
	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}

	@Override
	public boolean isPossibleToMoveAdjacentPoint(int[] node, int[] offset) {
		if (!isAdjacentPointExist(node, offset)) {

			return false;
		}
		return (labyrinth.getScheme()[node[0] + offset[0]][node[1] + offset[1]][node[2] + offset[2]].equals("."));
	}

	@Override
	public boolean isFinishPoint(int[] node, int[] offset) {
		if (!isAdjacentPointExist(node, offset)) {

			return false;
		}
		return (labyrinth.getScheme()[node[0] + offset[0]][node[1] + offset[1]][node[2] + offset[2]].equals("f"));
	}

	@Override
	public boolean isAdjacentPointExist(int[] node, int[] offset) {
		if ((node[0] + offset[0]) < 0 || (node[0] + offset[0]) > labyrinth.getScheme().length - 1) {

			return false;
		}

		if ((node[1] + offset[1]) < 0 || (node[1] + offset[1]) > labyrinth.getScheme()[0].length - 1) {

			return false;
		}

		if ((node[2] + offset[2]) < 0 || (node[2] + offset[2]) > labyrinth.getScheme()[0][0].length - 1) {

			return false;
		}

		return true;
	}

}
