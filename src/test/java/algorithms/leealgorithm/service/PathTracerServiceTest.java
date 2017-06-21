package algorithms.leealgorithm.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import algorithms.leealgorithm.domain.Labyrinth;
import algorithms.leealgorithm.service.PathTracerService;

public class PathTracerServiceTest {

	
	Labyrinth prince;
	Labyrinth prince2;
	
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
		prince = new Labyrinth();
		prince.setScheme(etalonLab);
		prince.setColumnsNum(4);
		prince.setRowsNum(4);
		prince.setLevelsNum(2);
		prince.setStartingLevel(0);
		prince.setStartingRow(2);
		prince.setStartingColumn(3);
		
		prince2 = new Labyrinth();
		prince2.setScheme(labyrinthFromTask);
		prince2.setColumnsNum(3);
		prince2.setRowsNum(3);
		prince2.setLevelsNum(3);
		prince2.setStartingLevel(0);
		prince2.setStartingRow(0);
		prince2.setStartingColumn(0);
		
		
	}
	
	
	@Test
	public void testGetShortestPath() {
		PathTracerService pathFinder = new PathTracerService();
		List<int[]> path;
		path = pathFinder.getShortestPath(prince);
		String[][][] tracedMap = pathFinder.getTraceMap();
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
		path = pathFinder.getShortestPath(prince2);
		assertEquals(12,path.size()+1);
	}

}
