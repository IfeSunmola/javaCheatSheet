package graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Graph {
	private final ArrayList<Vertex> vertices;

	public Graph (String fileLocation) throws IOException {
		vertices = new ArrayList<Vertex>();
		for (int i = 65; i < 91; i++) {// A = 65; Z = 90
			String temp = (char) i + "";
			vertices.add(new Vertex(temp));
		}
		createGraphFromMatrix(fileLocation);

	}

	private void createGraphFromMatrix (String fileLocation) throws IOException {
		Scanner sc = new Scanner(new File(fileLocation));
		String line = "";
		int currentRow = 0;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			String [] rowWeights = line.split(", ");
			Vertex startVertex = vertices.get(currentRow);

			for (int i = 0; i < rowWeights.length; i++) {
				int weight = Integer.parseInt(rowWeights[i]);
				if (weight != 0) {
					Vertex endVertex = vertices.get(i);
					startVertex.addNeighbour(new Edge(weight, endVertex));
				}
			}
			currentRow++;
		}
		sc.close();
	}

	public void computeShortestPaths (Vertex sourceVertex) {
		sourceVertex.setDistance(0);
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(sourceVertex);
		sourceVertex.setVisited(true);
		while (!priorityQueue.isEmpty()) {
			Vertex actualVertex = priorityQueue.poll();
			System.out.print("Dequeued: " + actualVertex + " updated: ");

			for (Edge edge : actualVertex.getNeighbours()) {
				Vertex v = edge.getEndVertex();

				if (!v.isVisited()) {
					int newDistance = actualVertex.getDistance() + edge.getWeight();
					if (newDistance < v.getDistance()) {
						priorityQueue.remove(v);
						System.out.print(v + " ");
						v.setDistance(newDistance);
						v.setPrevious(actualVertex);
						priorityQueue.add(v);
					}
				}
			}
			actualVertex.setVisited(true);
			System.out.println();
		}
	}

	public List<Vertex> getShortestPathTo (Vertex targetVertex) {
		List<Vertex> path = new ArrayList<>();

		for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPrevious()) {
			path.add(vertex);
		}

		Collections.reverse(path);
		return path;
	}

	public Vertex getVertexAt (int index) {
		return vertices.get(index);
	}

	public ArrayList<Vertex> getVertices () {
		return vertices;
	}

	public void bfsFromGraph (Vertex start) {
		Queue<Vertex> q = new LinkedList<>();
		q.add(start);
		start.setVisited(true);
		String result = "";
		while (!q.isEmpty()) {
			Vertex curr = q.poll();
			result += curr + " ";

			ArrayList<Edge> neighbours = curr.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
				Edge currNeighbour = neighbours.get(i);
				if (!currNeighbour.getEndVertex().isVisited()) {
					q.add(currNeighbour.getEndVertex());
					currNeighbour.getEndVertex().setVisited(true);
				}
			}
		}
		System.out.println("After BFS traversal starting from " + start + " is: " + result);

	}

	public void dfsFromGraph (Vertex start) {
		Stack<Vertex> s = new Stack<Vertex>();
		s.push(start);
		String result = "";
		while (!s.isEmpty()) {
			Vertex curr = s.pop();
			if (!curr.isVisited()) {
				result += curr + " ";
				curr.setVisited(true);

				ArrayList<Edge> neighbours = curr.getNeighbours();
				for (int i = 0; i < neighbours.size(); i++) {
					Edge currNeighbour = neighbours.get(i);
					s.push(currNeighbour.getEndVertex());
				}
			}
		}
		System.out.println("After DFS traversal starting from " + start + " is: " + result);
	}

	public void dfsRec (Vertex start) {
		System.out.println("After DFS REC traversal from: " + start + " is: " + dfsRec2(start, ""));
	}

	public String dfsRec2 (Vertex start, String result) {
		if (!start.isVisited()) {
			start.setVisited(true);
			result += start + " ";
			ArrayList<Edge> neighbours = start.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
				Vertex currNeighbour = neighbours.get(i).getEndVertex();
				result = dfsRec2(currNeighbour, result);
			}
		}
		return result;
	}

	public String toString () {
		String result = "";
		for (int i = 0; i < vertices.size(); i++) {
			Vertex curr = vertices.get(i);// store the current vertex to print neighbours for
			if (curr.hasNeighbours()) {// current vertex has neighbours
				ArrayList<Edge> currNeighbours = curr.getNeighbours();// get neighbours of the current vertex
				result += curr + " ~~ ";// A ~~~
				for (int j = 0; j < currNeighbours.size(); j++) {
					Edge temp = currNeighbours.get(j);// used to get end vertex and weight
					result += temp.getEndVertex() + " (" + temp.getWeight() + ")  ";
				}
				result += "\n";
			}

		}
		return result;
	}
}
