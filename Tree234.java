
public class Tree234 {
	public static void main (String [] args) {

		Node234 a8 = new Node234(65, 71);
		Node234 a6 = new Node234(81);
		Node234 a4 = new Node234(90);
		Node234 a3 = new Node234(101);
		Node234 a2 = new Node234(76, 85, 93, a8, a6, a4, a3);
		Node234 a5 = new Node234(16, 20);
		Node234 a7 = new Node234(55);
		Node234 a1 = new Node234(52, a5, a7);
		Node234 root = new Node234(61, a1, a2);

		int [] num =
			{ 61, 40, 52, 76, 85, 93, 23, 101, 90, 81, 71, 12, 65, 55, 20, 16 };

		Tree t = new Tree(root);
		for (int n : num) {
			if (n == 40 || n == 23 || n == 12) {
				System.out.println("Searching for: " + n + " (should be false): " + t.search(n));
			}
			else {
				System.out.println("Searching for: " + n + ": " + t.search(n));
			}
		}
		System.out.println("-----------------------------------");
		Node234 [] nodes =
			{ a1, a2, root, a3, a4, a5, a6, a7, a8 };
		for (Node234 n : nodes) {
			if (n.getDataLeft() != Integer.MIN_VALUE) {
				System.out.print("parent of " + n.getDataLeft());
				System.out.print(", ");
			}
			if (n.getDataMid() != Integer.MIN_VALUE) {
				System.out.print("parent of " + n.getDataMid());
				System.out.print(", ");
			}
			if (n.getDataRight() != Integer.MIN_VALUE) {
				System.out.print("parent of " + n.getDataRight());
			}

			System.out.print(" is: ");
			Node234 r = root.getParent(n);
			if (r != null) {
				if (r.getDataLeft() != Integer.MIN_VALUE) {
					System.out.print(r.getDataLeft() + ", ");
				}
				if (r.getDataMid() != Integer.MIN_VALUE) {
					System.out.print(r.getDataMid() + ", ");
				}
				if (r.getDataRight() != Integer.MIN_VALUE) {
					System.out.print(r.getDataRight() + ", ");
				}
			}
			else if (r == null) {
				System.out.print("null");
			}
			System.out.println();
		}
	}
}

class Tree {
	private Node234 root;

	public Tree () {
		root = null;
	}

	public Tree (Node234 root) {
		this.root = root;
	}

	public boolean search (int key) {
		return searchRec(root, key);
	}

	private boolean searchRec (Node234 curr, int key) {
		boolean found = false;
		if (curr != null && !found) {
			if (key == curr.getDataLeft() || key == curr.getDataMid() || key == curr.getDataRight()) {
				found = true;
			}
			else if (key < curr.getDataLeft()) {
				found = searchRec(curr.getLeft(), key);
			}
			else if (key < curr.getDataMid()) {
				found = searchRec(curr.getMidLeft(), key);
			}
			else if (key < curr.getDataRight()) {
				found = searchRec(curr.getMidRight(), key);
			}
			else {
				found = searchRec(curr.getRight(), key);
			}
		}
		return found;
	}

	private void split (Node234 toSplit) {
		if (toSplit.isFull()) {
			Node234 parent = root.getParent(toSplit);
			Node234 splitLeft = new Node234(toSplit.getDataLeft(), toSplit.getLeft(), toSplit.getMidLeft());
			Node234 splitRight = new Node234(toSplit.getDataRight(), toSplit.getMidRight(), toSplit.getRight());
			if (parent != null) {
				insertWithChildren(parent, toSplit.getDataMid(), splitLeft, splitRight);
			}
			else {
				parent = new Node234(toSplit.getDataMid(), splitLeft, splitRight);
				root = parent;
			}
		}
	}

	private void insertWithChildren (Node234 parent, int key, Node234 leftChild, Node234 rightChild) {
		if (key < parent.getDataLeft()) {
			parent.setDataRight(parent.getDataMid());
			parent.setDataMid(parent.getDataLeft());
			parent.setDataLeft(key);
			parent.setRight(parent.getMidRight());
			parent.setMidRight(parent.getMidLeft());
			parent.setMidLeft(rightChild);
			parent.setLeft(leftChild);
		}

		else if (key < parent.getDataMid()) {
			parent.setDataRight(parent.getDataMid());
			parent.setDataMid(key);
			parent.setRight(parent.getMidRight());
			parent.setMidRight(rightChild);
			parent.setMidLeft(leftChild);
		}
		else {
			parent.setDataRight(key);
			parent.setRight(rightChild);
			parent.setMidRight(leftChild);
		}
	}

}

class Node234 {
	private int dataLeft;
	private int dataMid;
	private int dataRight;
	private Node234 left;
	private Node234 midLeft;
	private Node234 midRight;
	private Node234 right;

	public Node234 (int data) {
		this.dataLeft = data;
		dataMid = dataRight = Integer.MIN_VALUE;
		left = midLeft = midRight = right = null;
	}

	public Node234 (int data, Node234 left, Node234 right) {
		this.dataLeft = data;
		this.left = left;
		this.right = right;
		dataMid = dataRight = Integer.MIN_VALUE;
	}

	public Node234 (int dataLeft, int dataMid, int dataRight) {
		this.dataLeft = dataLeft;
		this.dataMid = dataMid;
		this.dataRight = dataRight;
	}

	public Node234 (int dataLeft, int dataRight) {
		this.dataLeft = dataLeft;
		this.dataRight = dataRight;
		dataMid = Integer.MIN_VALUE;
	}

	public Node234 (int dataLeft, Node234 left) {
		this.dataLeft = dataLeft;
		this.left = left;
		dataMid = dataRight = Integer.MIN_VALUE;
	}

	public Node234 (int dataLeft, int dataMid, int dataRight, Node234 left, Node234 midLeft, Node234 midRight, Node234 right) {
		this.dataLeft = dataLeft;
		this.dataMid = dataMid;
		this.dataRight = dataRight;

		this.left = left;
		this.midLeft = midLeft;
		this.midRight = midRight;
		this.right = right;

	}

	public int getDataLeft () {
		return dataLeft;
	}

	public int getDataMid () {
		return dataMid;
	}

	public int getDataRight () {
		return dataRight;
	}

	public Node234 getLeft () {
		return left;
	}

	public Node234 getMidLeft () {
		return midLeft;
	}

	public Node234 getMidRight () {
		return midRight;
	}

	public void setDataLeft (int dataLeft) {
		this.dataLeft = dataLeft;
	}

	public void setDataMid (int dataMid) {
		this.dataMid = dataMid;
	}

	public void setDataRight (int dataRight) {
		this.dataRight = dataRight;
	}

	public void setLeft (Node234 left) {
		this.left = left;
	}

	public void setMidLeft (Node234 midLeft) {
		this.midLeft = midLeft;
	}

	public void setMidRight (Node234 midRight) {
		this.midRight = midRight;
	}

	public void setRight (Node234 right) {
		this.right = right;
	}

	public Node234 getRight () {
		return right;
	}

	public boolean isFull () {
		return dataLeft != Integer.MIN_VALUE && dataMid != Integer.MIN_VALUE && dataRight != Integer.MIN_VALUE;
	}

	public Node234 getParent (Node234 curr) {
		return getParentRec(this, curr);
	}

	private Node234 getParentRec (Node234 root, Node234 curr) {
		Node234 parent = null;
		if (root == null || curr == null) {
			parent = null;
		}
		else if (root.left == curr || root.midLeft == curr || root.midRight == curr || root.right == curr) {
			parent = root;
		}
		else if (curr.dataLeft < root.dataLeft) {
			parent = getParentRec(root.left, curr);
		}
		else if (curr.dataMid < root.dataMid) {
			parent = getParentRec(root.midLeft, curr);
		}
		else if (curr.dataRight < root.dataRight) {
			parent = getParentRec(root.midRight, curr);
		}
		else {// { if (curr.dataLeft < root.dataLeft) {
			parent = getParentRec(root.right, curr);
		}

		return parent;
	}
}