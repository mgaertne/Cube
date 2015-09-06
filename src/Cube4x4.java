import org.cubsolver.CubeSolver;

public class Cube4x4 {

	// Definition des Würfels (Längen der Segmente)
	static int[] cubedef = { 2, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 2, 4, 2, 2, 2, 4, 3, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2,
			2, 2, 2, 2, 3, 2, 3, 2, 3, 2, 4, 2, 2, 3, 2, 3 };

	public static void main(String[] args) {
		System.out.println("Starting Cube Simulation");
		
		CubeSolver solver = new CubeSolver(cubedef);
		
		// Start bei einer Auwahl von Positionen/Richtungen (Rest redundant wegen Symmetrie)
		solver.fold(0, 1, new int[] {0, 0, 0}, new int[] { 1, 0, 0 }); solver.reset();	
		solver.fold(0, 1, new int[] {0, 1, 0}, new int[] { 1, 0, 0 }); solver.reset(); 
		solver.fold(0, 1, new int[] {0, 1, 0}, new int[] { 0, 1, 0 }); solver.reset(); 
		solver.fold(0, 1, new int[] {0, 1, 0}, new int[] { 0, -1, 0 }); solver.reset(); 
		solver.fold(0, 1, new int[] {0, 1, 0}, new int[] { 0, 0, 1 }); solver.reset(); 
		solver.fold(0, 1, new int[] {1, 1, 0}, new int[] { 1, 0, 0 }); solver.reset(); 
		solver.fold(0, 1, new int[] {1, 1, 0}, new int[] { -1, 0, 0 }); solver.reset(); 
		solver.fold(0, 1, new int[] {1, 1, 0}, new int[] { 0, 0, 1 }); solver.reset(); 
		solver.fold(0, 1, new int[] {1, 1, 1}, new int[] { 1, 0, 0 }); solver.reset();
		solver.fold(0, 1, new int[] {1, 1, 1}, new int[] { -1, 0, 0 }); solver.reset();
		
		System.out.println("Finished Cube Simulation");
	}
}
