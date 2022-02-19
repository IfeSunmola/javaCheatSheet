package graph;

public class Edge {
	private int weight;
	private Vertex startVertex;
	private Vertex endVertex;

	public Edge (int weight, Vertex startVertex, Vertex endVertex) {
		this.weight = weight;
		this.startVertex = startVertex;
		this.endVertex = endVertex;
	}

	public Edge (int weight, Vertex endVertex) {
		this.weight = weight;
		this.endVertex = endVertex;
	}

	public int getWeight () {
		return weight;
	}

	public Vertex getStartVertex () {
		return startVertex;
	}

	public Vertex getEndVertex () {
		return endVertex;
	}

	public void setWeight (int weight) {
		this.weight = weight;
	}

	public void setStartVertex (Vertex startVertex) {
		this.startVertex = startVertex;
	}

	public void setEndVertex (Vertex endVertex) {
		this.endVertex = endVertex;
	}

}
