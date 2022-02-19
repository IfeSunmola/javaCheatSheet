package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Vertex implements Comparable<Vertex> {
	private String data;
	private ArrayList<Edge> neighbours;
	private boolean visited;
	private Vertex previous;
	private int distance;

	public Vertex (String data) {
		this.data = data;
		neighbours = new ArrayList<Edge>();
		distance = Integer.MAX_VALUE;
	}

	public void addNeighbour (Edge edge) {
		edge.setStartVertex(this);
		neighbours.add(edge);
	}

	public String toString () {
		return data;
	}

	public int compareTo (Vertex otherVertex) {
		return Integer.compare(distance, otherVertex.distance);
	}

	public String getData () {
		return toString();
	}

	public void setData (String data) {
		this.data = data;
	}

	public int getDistance () {
		return distance;
	}

	public void setDistance (int distance) {
		this.distance = distance;
	}

	public boolean isVisited () {
		return visited;
	}

	public void setVisited (boolean visited) {
		this.visited = visited;
	}

	public ArrayList<Edge> getNeighbours () {
		return neighbours;
	}

	public void setNeighbours (ArrayList<Edge> neighbours) {
		this.neighbours = neighbours;
	}

	public Vertex getPrevious () {
		return previous;
	}

	public void setPrevious (Vertex previous) {
		this.previous = previous;
	}

	public boolean hasNeighbours () {
		boolean has = false;
		for (int i = 0; i < neighbours.size() && !has; i++) {
			if (neighbours.get(i) != null) {
				has = true;
			}
		}
		return has;
	}

	public void computeShortestPaths () {
		Vertex starting = this;
		starting.setDistance(0);
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(starting);
		starting.setVisited(true);
		while (!priorityQueue.isEmpty()) {
			Vertex actualVertex = priorityQueue.poll();
			for (Edge edge : actualVertex.getNeighbours()) {
				Vertex v = edge.getEndVertex();

				if (!v.isVisited()) {
					int newDistance = actualVertex.getDistance() + edge.getWeight();
					if (newDistance < v.getDistance()) {
						priorityQueue.remove(v);
						v.setDistance(newDistance);
						v.setPrevious(actualVertex);
						priorityQueue.add(v);
					}
				}
			}
			actualVertex.setVisited(true);
		}
	}

	public ArrayList<Vertex> getShortestPathTo (Vertex endPoint, Graph g) {
		ArrayList<Vertex> paths = new ArrayList<Vertex>();
		for (Vertex vertex = endPoint; vertex != null; vertex = vertex.getPrevious()) {
			paths.add(vertex);
		}
		Collections.reverse(paths);
		return paths;
	}

	public void bfsFromVertex () {// do a Breadth first search starting from "this" vertex
		Queue<Vertex> q = new LinkedList<>();
		q.add(this);
		String result = "";
		this.visited = true;
		while (!q.isEmpty()) {
			Vertex curr = q.poll();
			result += curr + " ";

			ArrayList<Edge> neighbours = curr.neighbours;// get neighbours of curr
			for (int i = 0; i < neighbours.size(); i++) {
				Edge currNeighbour = curr.neighbours.get(i);
				if (!currNeighbour.getEndVertex().isVisited()) {
					q.add(currNeighbour.getEndVertex());
					currNeighbour.getEndVertex().setVisited(true);
				}
			}
		}
		System.out.println("After BFS traversal starting from " + this + " is: " + result);
	}

}
