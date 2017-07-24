package algorithms.leealgorithm.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algorithms.leealgorithm.domain.Labyrinth;
import algorithms.leealgorithm.service.PathTracerService;

public class PathTracerServiceTest {

	
	Labyrinth labyrinth1;
	Labyrinth labyrinth2;
	
	String[][][] etalonLab = {
			{{".","o",".","."},
			 {".",".",".","o"},
			 {".","o",".","s"},
			 {".","o",".","."}},
			
			{{"o","o","o","o"},
			 {".",".",".","o"},
			 {".","o",".","."},
			 {".","f","o","."}}
		};
	String[][][] etalonLab1 = {
			{{".","o",".","."},
			 {".",".",".","o"},
			 {".","o",".","s"},
			 {".","o",".","."}},
			
			{{"o","o","o","o"},
			 {".",".",".","o"},
			 {".","o",".","."},
			 {".","f","o","."}}
	};
	String[][][] labyrinthFromTask = {
			   {{"s",".","."},
				{"o","o","."},
				{".",".","."}},
			   
			   {{"o","o","o"},
				{".",".","o"},
				{".","o","o"}},
			   
			   {{"o","o","o"},
				{"o",".","."},
				{"o",".","f"}},
				  
	};
	@Before
	public void initialize(){
		labyrinth1 = new Labyrinth();
		labyrinth1.setScheme(etalonLab);
		labyrinth1.setColumnsNum(4);
		labyrinth1.setRowsNum(4);
		labyrinth1.setLevelsNum(2);
		labyrinth1.setStartingPoint(new int[]{0, 2, 3});
		labyrinth1.setFinishPoint(new int[]{1, 3, 1});
		
		labyrinth2 = new Labyrinth();
		labyrinth2.setScheme(labyrinthFromTask);
		labyrinth2.setColumnsNum(3);
		labyrinth2.setRowsNum(3);
		labyrinth2.setLevelsNum(3);
		labyrinth2.setStartingPoint(new int[]{0, 0, 0});
		labyrinth2.setFinishPoint(new int[]{2, 2, 2});
	}
	
	
	@Test
	public void testGetShortestPath() {
		PathTracerService pathFinder = new PathTracerService();
		List<int[]> path;
		path = pathFinder.getShortestPath(labyrinth1);
		String[][][] tracedMap = labyrinth1.getScheme();
		System.out.println("Traced map:");
		for (int i=0; i<tracedMap.length; i++){
			for (int j=0; j<tracedMap[i].length; j++){
				for (int k=0; k<tracedMap[i][j].length; k++){
					System.out.print(tracedMap[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		
		System.out.println("Resulting path: ");
		for (int [] point : path) {
			etalonLab1[point[0]][point[1]][point[2]] = "*";
		}
		for (int i=0; i<etalonLab1.length; i++){
			for (int j=0; j<etalonLab1[i].length; j++){
				for (int k=0; k<etalonLab1[i][j].length; k++){
					System.out.print(etalonLab1[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		assertEquals(8,path.size()+1);
		
	}
	
	@Test
	public void testGetShortestPathInTaskLabyrinth(){
		PathTracerService pathFinder = new PathTracerService();
		List<int[]> path;
		path = pathFinder.getShortestPath(labyrinth2);
		assertEquals(12,path.size()+1);
	}

}
