package algorithms.leealgorithm.view;

import java.io.File;
import java.util.List;

import algorithms.leealgorithm.dao.LabyrinthDao;
import algorithms.leealgorithm.domain.Labyrinth;
import algorithms.leealgorithm.service.PathTracerService;

public class TracingExample{

	public static void main(String[] args) {
		LabyrinthDao princeDao = new LabyrinthDao();
		File file = new File("labyrinth.txt");//put your labyrinth into project's root directory and set here it name
		princeDao.setFile(file);
		Labyrinth labyrinth = princeDao.getPathTracer();
		PathTracerService pathFinder = new PathTracerService();
		
		String[][][] labyrinthBeforeTrace = labyrinth.getScheme();
		
		System.out.println("s - start point");
		System.out.println("f - finish point");
		System.out.println();
		System.out.println("Original labyrinth.");
		System.out.println();
		
		for (int i=0; i<labyrinthBeforeTrace.length; i++){
			for (int j=0; j<labyrinthBeforeTrace[i].length; j++){
				for (int k=0; k<labyrinthBeforeTrace[i][j].length; k++){
					System.out.print(labyrinthBeforeTrace[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		
		List<int[]> path = pathFinder.getShortestPath(labyrinth);
		
		labyrinthBeforeTrace = princeDao.getPathTracer().getScheme(); //we get original labyrinth without traced waves
		
		if (path != null) {
			
			//draw path on the labyrinth
			for (int[] point : path) {
				labyrinthBeforeTrace[point[0]][point[1]][point[2]] = "*";
			}
			
			System.out.println("Original labyrinth with path *");
			System.out.println();
			
			for (int i = 0; i < labyrinthBeforeTrace.length; i++) {
				for (int j = 0; j < labyrinthBeforeTrace[i].length; j++) {
					for (int k = 0; k < labyrinthBeforeTrace[i][j].length; k++) {
						System.out.print(labyrinthBeforeTrace[i][j][k]);
					}
					System.out.println();
				}
				System.out.println();
			}
			
			System.out.println("Total: " + (path.size() + 1) + " steps");
			System.out.println("Total: " + (path.size() + 1) * 5 + " seconds");
		} else {
			System.out.println("There isn't any valid path");
		}
	}
}
