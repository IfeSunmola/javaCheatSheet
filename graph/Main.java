package graph;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main (String [] args) throws IOException {
		String fileLocation = "src/graph/graphInput.txt";
		Graph graph = new Graph(fileLocation);
//		System.out.println(graph);
		Vertex a = graph.getVertexAt(0);
		Vertex b = graph.getVertexAt(1);
		Vertex c = graph.getVertexAt(2);
		Vertex d = graph.getVertexAt(3);
		Vertex e = graph.getVertexAt(4);
		Vertex f = graph.getVertexAt(5);
		Vertex g = graph.getVertexAt(6);
		Vertex h = graph.getVertexAt(7);
		Vertex i = graph.getVertexAt(8);
		Vertex j = graph.getVertexAt(9);
		Vertex k = graph.getVertexAt(10);
		Vertex l = graph.getVertexAt(11);

		ArrayList<Vertex> vertices = graph.getVertices();

		Vertex start = b;
		Vertex end = g;

		graph.computeShortestPaths(start);

	}
}
