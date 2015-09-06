public class Cube4x4 {

	// Definition des Würfels (Längen der Segmente)
	static int[] cubedef = { 2, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 4, 2, 2, 2, 4, 3, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2,
			2, 2, 2, 2, 3, 2, 3, 2, 3, 2, 4, 2, 2, 3, 2, 3 };

	// 4x4x4-Würfel ("Spielraum") zum "Reinfalten" der Würfeldefinition
	static int[][][] cube = new int[4][4][4];

	static int folds = 0; // zählt die versuchten Faltungen der Würfelsegmente

	// löscht die Würfelchen aus dem Spielraum ab dem mit der Nummer orig_count
	static void cube_clear(int orig_count) {
		for (int i = 0; i <= 3; i++)
			for (int n = 0; n <= 3; n++)
				for (int m = 0; m <= 3; m++)
					if (cube[i][n][m] > orig_count)
						cube[i][n][m] = 0;
	}

	// Scheiben des Würfels ausgeben, als Positionen der gefalteten Würfelchen
	static void cube_print() {
		for (int i = 0; i <= 3; i++) {
			for (int n = 0; n <= 3; n++) {
				for (int m = 0; m <= 3; m++)
					System.out.print(cube[m][n][i] + "   ");
				System.out.println(" ");
			}
			System.out.println("--");
		} System.out.println();
	}

	// faltet ein Segment in den Spielraum
	static void fold(int segm, int count, int pos[], int rchtng[]) {
		// segm: aktuelles Segment, count: aktuelles Würfelchen,
		// pos: aktuelle Position im Spielraum, rchtng: aktuelle Faltrichtung	
		folds++;
		if (folds % 10000000 == 0) // Fortschrittsanzeige in Mio. Faltungen
			System.out.println("Mio: " + (folds / 1000000));
		cube[pos[0]][pos[1]][pos[2]] = count; // erstes Würfelchen setzen
		if (count >= 63) { // Wenn 64 Würfelchen im Spielraum, dann fertig
			System.out.println("Lösung:");
			cube_print();
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
			cube_clear(orig_count -1); 
	}

	public static void main(String[] args) {
		System.out.println("Starting Cube Simulation");
		// Start bei einer Auwahl von Positionen/Richtungen (Rest redundant wegen Symmetrie)
		fold(0, 1, new int[] { 0, 0, 0 }, new int[] { 1, 0, 0 }); cube_clear(0);	
		  fold(0, 1, new int[] {0, 1, 0}, new int[] { 1, 0, 0 }); cube_clear(0); 
		  fold(0, 1, new int[] {0, 1, 0}, new int[] { 0, 1, 0 }); cube_clear(0); 
		  fold(0, 1, new int[] {0, 1, 0}, new int[] { 0, -1, 0 }); cube_clear(0); 
		  fold(0, 1, new int[] {0, 1, 0}, new int[] { 0, 0, 1 }); cube_clear(0); 
		  fold(0, 1, new int[] {1, 1, 0}, new int[] { 1, 0, 0 }); cube_clear(0); 
		  fold(0, 1, new int[] {1, 1, 0}, new int[] { -1, 0, 0 }); cube_clear(0); 
		  fold(0, 1, new int[] {1, 1, 0}, new int[] { 0, 0, 1 }); cube_clear(0); 
		  fold(0, 1, new int[] {1, 1, 1}, new int[] { 1, 0, 0 }); cube_clear(0);
		  fold(0, 1, new int[] {1, 1, 1}, new int[] { -1, 0, 0 }); cube_clear(0); 
	}
}
