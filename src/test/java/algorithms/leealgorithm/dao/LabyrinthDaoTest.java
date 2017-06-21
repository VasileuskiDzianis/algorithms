package algorithms.leealgorithm.dao;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import algorithms.leealgorithm.dao.LabyrinthDao;
import algorithms.leealgorithm.domain.Labyrinth;

public class LabyrinthDaoTest {
	LabyrinthDao prince = new LabyrinthDao();

	@Test
	public void testGetPrinceOfPercia() {
		File file = new File("labyrinth_dao_test.txt");
		prince.setFile(file);
		Labyrinth labyrinth = prince.getPathTracer();
		String[][][] etalonLab = {
				{{".","o",".","."},
				 {".",".",".","o"},
				 {".",".",".","s"},
				 {".","o",".","."}},
					{{"o","o","o","o"},
					 {".",".",".","o"},
					 {".",".",".","."},
					 {".",".","f","."}}
			};
		String[][][] labFromDao = labyrinth.getScheme();
		
		for (int i=0; i<labFromDao.length; i++){
			for (int j=0; j<labFromDao[i].length; j++){
				for (int k=0; k<labFromDao[i][j].length; k++){
					assertEquals(etalonLab[i][j][k],labFromDao[i][j][k]);
					System.out.print(labFromDao[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}

	}

}