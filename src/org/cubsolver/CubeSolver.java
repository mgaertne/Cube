package org.cubsolver;

import static java.lang.Double.valueOf;
import static java.lang.Math.pow;
import static java.lang.Math.round;

public class CubeSolver {

	private int[] cubedef;

	private int[][][] cube;

	private int folds = 0;

	public CubeSolver(int[] cubedef) {
		this.cubedef = cubedef;

		cube = new int[dimensions()][dimensions()][dimensions()];
	}
	
	public void reset() {
		clear(0);
	}

	public void clear(int resetPoint) {
		for (int layer = 0; layer < cube.length; layer++)
			for (int row = 0; row < cube[layer].length; row++)
				for (int column = 0; column < cube[layer][row].length; column++)
					if (cube[layer][row][column] > resetPoint)
						cube[layer][row][column] = 0;
	}

	public void print() {
		for (int layer = 0; layer < cube.length; layer++) {
			for (int row = 0; row < cube[layer].length; row++) {
				for (int column = 0; column < cube[layer][row].length; column++) {
					System.out.print(cube[layer][row][column] + "   ");
				}
				System.out.println(" ");
			}
			System.out.println("--");
		}
		System.out.println();
	}

	public void fold(int segment, int count, int pos[], int direction[]) {
		folds++;
		if (folds % 10000000 == 0) { // Fortschrittsanzeige in Mio. Faltungen
			System.out.println("Mio: " + (folds / 1000000));
		}
		cube[pos[0]][pos[1]][pos[2]] = count; // Würfelchen setzen
		if (isSolved(count)) {
			System.out.println("Lösung:");
			print();
			return;
		}
		int orig_count = count; // Länge der bisherigen Faltung merken
		for (int i = 2; i <= cubedef[segment]; i++) { // weitere Würfelchen des
													// Segments setzen
			for (int j = 0; j <= 2; j++)
				pos[j] += direction[j]; // einen Schritt weiter
			count++;
			if (pos[0] >= 0 && pos[1] >= 0 && pos[2] >= 0 && pos[0] <= 3 && pos[1] <= 3 && pos[2] <= 3
					&& cube[pos[0]][pos[1]][pos[2]] == 0) { // im Spielraum und
															// frei:
				cube[pos[0]][pos[1]][pos[2]] = count; // Würfelchen setzen
			} else {
				clear(orig_count - 1);
				return;
			}
		}
		for (int i = 0; i <= 2; i++)
			for (int j = -1; j <= 1; j += 2)
				if (direction[i] == 0) {
					int[] newDirection = new int[3];
					newDirection[i] = j;
					fold(segment + 1, count, pos.clone(), newDirection);
				}
	}

	private boolean isSolved(int count) {
		return count >= 63;
	}

	public int dimensions() {
		int counts = 1;
		for (int i = 0; i < cubedef.length; i++) {
			counts += cubedef[i] -1;
		}
		return valueOf(round(pow(counts, 1.0/3))).intValue();
	}
}
