package shortestPath;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DijkstraMain {
	static Vertex vertexA = new Vertex("A");
	static Vertex vertexB = new Vertex("B");
	static Vertex vertexC = new Vertex("C");
	static Vertex vertexD = new Vertex("D");
	static Vertex vertexE = new Vertex("E");
	static Vertex vertexF = new Vertex("F");
	static Vertex vertexG = new Vertex("G");
	static Vertex vertexH = new Vertex("H");
	static Vertex vertexI = new Vertex("I");
	static Vertex vertexJ = new Vertex("J");
	static Vertex vertexK = new Vertex("K");
	static Vertex vertexL = new Vertex("L");

	public static void main (String [] args) {
		tester();
	}

	private static void tester () {
		try {
			Scanner sc = new Scanner(new File("src/input.txt"));
			int count = 0;
			while (sc.hasNextLine()) {

				String [] tokens = sc.nextLine().split(", ");
				Vertex curr = getVertex(count);
				count++;

				for (int i = 0; i < tokens.length; i++) {
					Vertex targetVertex = getVertex(i);
					int weight = Integer.parseInt(tokens[i]);
					if (weight != 0) {
						curr.addNeighbour(new Edge(weight, curr, targetVertex));
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		DijkstraShortestPath shortestPath = new DijkstraShortestPath();
		Vertex starting = getVertex(2);
//		Vertex starting = getVertex(0);
		Vertex ending = getVertex(8);
		shortestPath.computeShortestPaths(starting);

		ending = getVertex(4);
		int distance = (int) ending.getDistance();
		System.out.println("Minimum distance from " + starting + " to " + ending + " = " + distance);
		System.out.println("Shortest path from " + starting + " to " + ending + " = " + shortestPath.getShortestPathTo(ending));
		System.out.println();

	}

	private static Vertex getVertex (int number) {
		Vertex result = null;

		switch (number) {
			case 0 -> result = vertexA;
			case 1 -> result = vertexB;
			case 2 -> result = vertexC;
			case 3 -> result = vertexD;
			case 4 -> result = vertexE;
			case 5 -> result = vertexF;
			case 6 -> result = vertexG;
			case 7 -> result = vertexH;
			case 8 -> result = vertexI;
			case 9 -> result = vertexJ;
			case 10 -> result = vertexK;
			case 11 -> result = vertexL;
		}
		return result;
	}

	private static int countLines () throws IOException {
		int total = 0;
		Scanner sc = new Scanner(new File("src/input.txt"));
		while (sc.hasNextLine()) {
			total++;
			sc.nextLine();
		}
		return total;
	}

	private static void ogCode () {
		Vertex vertexA = new Vertex("A");
		Vertex vertexB = new Vertex("B");
		Vertex vertexC = new Vertex("C");
		Vertex vertexD = new Vertex("D");
		Vertex vertexE = new Vertex("E");
		Vertex vertexF = new Vertex("F");

		vertexA.addNeighbour(new Edge(30, vertexA, vertexB));
		vertexA.addNeighbour(new Edge(60, vertexA, vertexD));
		vertexA.addNeighbour(new Edge(20, vertexA, vertexE));

		vertexB.addNeighbour(new Edge(30, vertexB, vertexA));
		vertexB.addNeighbour(new Edge(10, vertexB, vertexC));
		vertexB.addNeighbour(new Edge(20, vertexB, vertexE));
		vertexB.addNeighbour(new Edge(40, vertexB, vertexF));

		vertexC.addNeighbour(new Edge(10, vertexC, vertexB));
		vertexC.addNeighbour(new Edge(50, vertexC, vertexD));
		vertexC.addNeighbour(new Edge(20, vertexC, vertexF));

		vertexD.addNeighbour(new Edge(60, vertexD, vertexA));
		vertexD.addNeighbour(new Edge(50, vertexD, vertexC));
		vertexD.addNeighbour(new Edge(30, vertexD, vertexE));
		vertexD.addNeighbour(new Edge(30, vertexD, vertexF));

		vertexE.addNeighbour(new Edge(20, vertexE, vertexA));
		vertexE.addNeighbour(new Edge(20, vertexE, vertexB));
		vertexE.addNeighbour(new Edge(30, vertexE, vertexD));

		vertexF.addNeighbour(new Edge(40, vertexF, vertexB));
		vertexF.addNeighbour(new Edge(20, vertexF, vertexC));
		vertexF.addNeighbour(new Edge(30, vertexF, vertexD));

		DijkstraShortestPath shortestPath = new DijkstraShortestPath();
		Vertex starting = vertexA;
		shortestPath.computeShortestPaths(starting);

		System.out.println("==================");
		System.out.println("Calculating minimum distance");
		System.out.println("==================");

//		System.out.println("Minimum distance from " + starting + " to A " + vertexA.getDistance());
//		System.out.println("Minimum distance from " + starting + " to B: " + vertexB.getDistance());
//		System.out.println("Minimum distance from " + starting + " to C: " + vertexC.getDistance());
//		System.out.println("Minimum distance from " + starting + " to D: " + vertexD.getDistance());
//		System.out.println("Minimum distance from " + starting + " to E " + vertexE.getDistance());
		System.out.println("Minimum distance from " + starting + " to F " + vertexF.getDistance());

		System.out.println("==================");
		System.out.println("Calculating Paths");
		System.out.println("==================");

//		System.out.println("Shortest Path from " + starting + " to A: " + shortestPath.getShortestPathTo(vertexA));
//		System.out.println("Shortest Path from " + starting + " to B: " + shortestPath.getShortestPathTo(vertexB));
//		System.out.println("Shortest Path from " + starting + " to C: " + shortestPath.getShortestPathTo(vertexC));
//		System.out.println("Shortest Path from " + starting + " to D: " + shortestPath.getShortestPathTo(vertexD));
//		System.out.println("Shortest Path from " + starting + " to E " + shortestPath.getShortestPathTo(vertexE));
		System.out.println("Shortest Path from " + starting + " to F: " + shortestPath.getShortestPathTo(vertexF));

	}
}