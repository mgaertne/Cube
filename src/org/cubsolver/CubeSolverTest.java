package org.cubsolver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class CubeSolverTest {

	@Test
	public void checkDimensionsForFourPieces() {
		CubeSolver solver = new CubeSolver(new int[] { 2, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 4, 2, 2, 2, 4, 3, 2, 2, 2,
				2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 3, 2, 3, 2, 4, 2, 2, 3, 2, 3 });
		assertThat(solver.dimensions(), is(4));
	}

	@Test
	public void dimensionsForEightPieces() {
		CubeSolver solver = new CubeSolver(new int[] { 2, 2, 2, 2 });
		assertThat(solver.dimensions(), is(2));
	}

}
