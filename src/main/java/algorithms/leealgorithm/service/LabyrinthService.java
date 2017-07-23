package algorithms.leealgorithm.service;

import algorithms.leealgorithm.domain.Labyrinth;

public interface LabyrinthService {
	
	void setLabyrinth(Labyrinth labyrinth);
	
	boolean isPossibleToMoveAdjacentPoint(int[] node, int[] offset);
	
	boolean isFinishPoint(int[] node, int[] offset);
	
	boolean isAdjacentPointExist(int[] node, int[] offset);

}
