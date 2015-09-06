package org.cubsolver;

public class CubeSolver {

	private int[] cubedef;

	private int[][][] cube;

	private int folds = 0;

	public CubeSolver(int[] cubedef) {
		this.cubedef = cubedef;

		cube = new int[4][4][4];
	}

	public void clear(int orig_count) {
		for (int i = 0; i < cube.length; i++)
			for (int n = 0; n < cube[i].length; n++)
				for (int m = 0; m < cube[i][n].length; m++)
					if (cube[i][n][m] > orig_count)
						cube[i][n][m] = 0;
	}


	public void print() {
		for (int i = 0; i <= 3; i++) {
			for (int n = 0; n <= 3; n++) {
				for (int m = 0; m <= 3; m++)
					System.out.print(cube[m][n][i] + "   ");
				System.out.println(" ");
			}
			System.out.println("--");
		}
		System.out.println();
	}
	
	public void fold(int segm, int count, int pos[], int rchtng[]) {
		folds++;
		if (folds % 10000000 == 0) // Fortschrittsanzeige in Mio. Faltungen
			System.out.println("Mio: " + (folds / 1000000));
		cube[pos[0]][pos[1]][pos[2]] = count; // erstes Würfelchen setzen
		if (count >= 63) { // Wenn 64 Würfelchen im Spielraum, dann fertig
			System.out.println("Lösung:");
			print();
			return;
		}
		int orig_count = count; // Länge der bisherigen Faltung merken
		boolean fail = false;
		for (int i = 2; i <= cubedef[segm]; i++) { // weitere Würfelchen des
													// Segments setzen
			for (int j = 0; j <= 2; j++)
				pos[j] += rchtng[j]; // einen Schritt weiter
			count++;
			if (pos[0] >= 0 && pos[1] >= 0 && pos[2] >= 0 && pos[0] <= 3 && pos[1] <= 3 && pos[2] <= 3
					&& cube[pos[0]][pos[1]][pos[2]] == 0) { // im Spielraum und frei:
				cube[pos[0]][pos[1]][pos[2]] = count; // Würfelchen setzen		
			} else {
				fail = true; 
				break;
			}
		}
		if (!fail) { // Wenn Segment erfolgreich, dann nächstes Segment in alle erlaubten Richtungen falten
			for (int i = 0; i <= 2; i++)
				for (int j = -1; j <= 1; j+=2 )
					if (rchtng[i] == 0) {
						int[] neue_rchtng = new int[3];
						neue_rchtng[i] = j;
						fold(segm +1, count, pos.clone(), neue_rchtng);
					} 
		} else // sonst: Würfel auf den vorherige Stand setzen
			clear(orig_count -1); 
	}
}
