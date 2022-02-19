public class BinarySearchTree {

	public static void main (String [] args) {

		BST t = new BST();
		t.insert(15);
		t.insert(8);
		t.insert(12);
		t.insert(19);
		t.insert(5);
		t.insert(23);

		System.out.println("Tree: " + t);
		System.out.println("height with bst class is: " + t.getHeight());
		System.out.println("height with node class is: " + t.getHeightWithNodeClass());

		System.out.println("------------------------");

	}

}

class BST {
	private NodeBst root;

	public BST () {
		root = null;
	}

	public BST (NodeBst root) {
		this.root = root;
	}

	public String toString () {
		String result = "Tree is empty";
		if (getRoot() != null) {
			result = getRoot().inorderTraversal();
		}
		return result;
	}

	public int getHeightWithNodeClass () {
		int height = Integer.MIN_VALUE;
		if (getRoot() != null) {
			height = getRoot().getHeightWithNodeClass(getRoot());
		}
		return height;
	}

	public int getHeight () {
		return getHeight(getRoot());
	}

	private int getHeight (NodeBst curr) {
		int height = -1;
		if (curr != null) {
			int leftHeight = getHeight(curr.getLeft());
			int rightHeight = getHeight(curr.getRight());
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		return height;
	}

	public boolean searchReturnBoolean (int key) {
		boolean found = false;
		NodeBst curr = getRoot();
		while (curr != null && !found) {
			if (key == curr.getItem()) {
				found = true;
			}
			else if (key < curr.getItem()) {// smaller, go left
				curr = curr.getLeft();
			}
			else {// bigger, go right
				curr = curr.getRight();
			}
		}
		return found;
	}

	public NodeBst searchReturnNode (int key) {
		NodeBst found = null;
		NodeBst curr = getRoot();
		while (curr != null && found == null) {
			if (key == curr.getItem()) {
				found = curr;
			}
			else if (key < curr.getItem()) {// smaller, go left
				curr = curr.getLeft();
			}
			else {// bigger, go right
				curr = curr.getRight();
			}
		}
		return found;
	}

	public boolean searchRec (int key) {
		boolean found = false;
		if (getRoot() != null) {
			found = getRoot().searchRec(key);
		}
		return found;
	}

	public void computeDepth () {
		if (getRoot() != null) {
			getRoot().computeDepth(-1);
		}
		System.out.println(getRoot().getDepth());
	}

	public void insert (int newItem) {
		if (getRoot() == null) {// empty list
			root = new NodeBst(newItem);
		}
		else {
			// find the correct place to insert the new item
			// by searching for the new item using both prev and curr pointers
			// if new item is not found, curr will be null and prev will point at the node
			// that should be the node's parent

			NodeBst curr = getRoot();
			NodeBst prev = null;
			while (curr != null) {
				if (newItem < curr.getItem()) {// move left
					prev = curr;
					curr = curr.getLeft();
				}
				else if (newItem >= curr.getItem()) {// move right, remove = if no duplicates
					prev = curr;
					curr = curr.getRight();
				}
			}

			if (newItem < prev.getItem()) {
				prev.setLeft(new NodeBst(newItem));
			}
			else if (newItem >= prev.getItem()) {// remove = if no duplicates
				prev.setRight(new NodeBst(newItem));
			}

		}
	}

	public void insert2 (int key) {
		if (getRoot() == null) {
			root = new NodeBst(key);
		}
		else {
			NodeBst curr = getRoot();
			NodeBst prev = null;
			while (curr != null && curr.getItem() != key) {
				prev = curr;
				if (key < curr.getItem()) {
					curr = curr.getLeft();
				}
				else {
					curr = curr.getRight();
				}
			}
			if (curr == null) {
				if (key < prev.getItem()) {
					prev.setLeft(new NodeBst(key));
				}
				else {
					prev.setRight(new NodeBst(key));
				}
			}
		}
	}

	public void insertRec (int key) {
		if (getRoot() == null) {
			root = new NodeBst(key);
		}
		else {
			getRoot().insertBelow(key);
		}
	}

	public void delete (int key) {
		// search for key first;
		NodeBst curr = getRoot();
		NodeBst prev = null;
		while (curr != null && curr.getItem() != key) {
			prev = curr;
			if (key < curr.getItem()) {
				curr = curr.getLeft();
			}
			else {
				curr = curr.getRight();
			}
		}
		// either key was found OR curr == null (not found, fell off)
		if (curr != null) {
			if (curr.getLeft() == null) {// node to delete has no children or only a right child
				easyDelete(curr, prev, curr.getRight());
			}
			else if (curr.getRight() == null) {// has only a left child
				easyDelete(curr, prev, curr.getLeft());
			}
			else {// has two children
				twoChildredDelete(curr);
			}
		} // do nothing if the key is not found

	}// end delete

	// node to be deleted, the parent of the node to be deleted, the sole child of
	// the node to be deleted (or null if it has no children)
	private void easyDelete (NodeBst del, NodeBst delParent, NodeBst delChild) {
		if (delParent != null) {// not the root of the root
			if (del == delParent.getLeft()) {
				delParent.setLeft(delChild);
			}
			else {
				delParent.setRight(delChild);
			}
		}
		else {
			// node to remove is the root
			root = delChild;
		}
	}

	private void twoChildredDelete (NodeBst curr) {
		// deleting a node with two children
		// find the inorder successor
		NodeBst inorderSuccessor = curr.getRight();// going right once from the node to delete
		NodeBst isParent = curr;// starting at the node to delete
		while (inorderSuccessor.getLeft() != null) {// going left as far as possible
			isParent = inorderSuccessor;
			inorderSuccessor = inorderSuccessor.getLeft();
		}
		curr.setItem(inorderSuccessor.getItem());// copying inorderSuccessor item to curr
		easyDelete(inorderSuccessor, isParent, inorderSuccessor.getRight());// delete old inorderSuccessor node
	}

	public void clear () {
		root = null;
	}

	public void insertWithParent (int key) {
		NodeBst toInsert = new NodeBst(key);
		if (getRoot() == null) {
			root = toInsert;
			toInsert.setParent(null);
		}
		else {
			NodeBst cur = getRoot();
			while (cur != null) {
				if (key < cur.getItem()) {
					if (cur.getLeft() == null) {
						cur.setLeft(toInsert);
						toInsert.setParent(cur);
						cur = null;
					}
					else
						cur = cur.getLeft();
				}
				else {
					if (cur.getRight() == null) {
						cur.setRight(toInsert);
						toInsert.setParent(cur);
						cur = null;
					}
					else
						cur = cur.getRight();
				}
			}
		}
	}

	public boolean replaceChild (NodeBst parent, int current, int newC) {
		boolean replaced = false;
		NodeBst currentChild = new NodeBst(current);
		NodeBst newChild = new NodeBst(newC);
		if (parent.getLeft() != currentChild && parent.getRight() != currentChild) {
			replaced = false;
		}
		if (parent.getLeft() == currentChild) {
			parent.setLeft(newChild);
		}
		else {
			parent.setRight(newChild);
		}
		if (newChild != null) {
			newChild.setParent(parent);
			replaced = true;
		}
		return replaced;
	}

	public void deleteRec (int key) {
		NodeBst nodeToDelete = searchReturnNode(key);
		NodeBst parent = root.getParent(nodeToDelete);
		deleteRec(parent, nodeToDelete);
	}

	private void deleteRec (NodeBst parent, NodeBst nodeToDelete) {
		if (nodeToDelete == null) {
			return;
		}
		if (nodeToDelete.getLeft() != null && nodeToDelete.getRight() != null) {
			// case 1: deleting internal node with 2 children
			NodeBst successorNode = nodeToDelete.getRight();
			NodeBst successorParent = nodeToDelete;
			while (successorNode.getLeft() != null) {
				successorParent = successorNode;
				successorNode = successorNode.getLeft();
			}
			nodeToDelete = successorNode;
			deleteRec(successorParent, successorNode);
		}
		else if (nodeToDelete == root) {
			// case 2: deleteing root node with 1 or 0 children
			if (nodeToDelete.getLeft() != null) {
				root = nodeToDelete.getLeft();
			}
			else {
				root = nodeToDelete.getRight();
			}
		}
		else if (nodeToDelete.getLeft() != null) {
			// case 3: deleting internal node with only left child
			if (parent.getLeft() == nodeToDelete) {
				parent.setLeft(nodeToDelete.getLeft());
			}
			else {
				parent.setRight(nodeToDelete.getRight());
			}
		}
		else {
			// case 4: deleting internal node with only right child or deleting leaf node
			if (parent.getLeft() == nodeToDelete) {
				parent.setLeft(nodeToDelete.getRight());
			}
			else {
				parent.setRight(nodeToDelete.getRight());
			}
		}
	}

	public NodeBst getRoot () {
		return root;
	}
}

class NodeBst {
	private int item;
	private NodeBst left;
	private NodeBst right;
	private int depth;
	private NodeBst parent;
	// height of a node is the number of edges on the longest path from the node to a leaf node
	// depth is the number of edges from the node to the root node

	public NodeBst (int item) {
		this.setItem(item);
		setLeft(setRight(null));
	}

	public NodeBst (int item, NodeBst left, NodeBst right) {
		this.setItem(item);
		this.setLeft(left);
		this.setRight(right);
	}

	public String inorderTraversal () {
		String result = "";
		// move here for pre order
		if (getLeft() != null) {
			result += getLeft().inorderTraversal();
		}
		result += getItem() + " ";
		// System.err.println(item);// visit the node
		if (getRight() != null) {
			result += getRight().inorderTraversal();
		}
		// move here for post order
		return result;
	}

	public void computeDepth (int parentDepth) {
		depth = parentDepth + 1;
		if (getLeft() != null) {
			getLeft().computeDepth(getDepth());
		}
		if (getRight() != null) {
			getRight().computeDepth(getDepth());
		}
	}

	public int getHeightWithNodeClass (NodeBst curr) {
		int height = -1;

		if (curr != null) {
			int leftHeight = getHeightWithNodeClass(curr.getLeft());
			int rightHeight = getHeightWithNodeClass(curr.getRight());
			height = 1 + Math.max(leftHeight, rightHeight);
		}

		return height;
	}

	public boolean searchRec (int key) {
		boolean found = getItem() == key;
		if (!found) {
			if (key < getItem()) {// smaller, go left
				if (getLeft() != null) {
					found = getLeft().searchRec(key);
				}
			}
			else {// bigger, go right
				if (getRight() != null) {
					found = getRight().searchRec(key);
				}
			}
		}
		return found;
	}

	public void inorder () {
		if (getLeft() != null) {
			getLeft().inorder();
		}
		System.out.print(getItem() + " ");
		if (getRight() != null) {
			getRight().inorder();
		}
	}

	public void postorder () {
		if (getLeft() != null) {
			getLeft().postorder();
		}
		if (getRight() != null) {
			getRight().postorder();
		}
		System.out.print(getItem() + " ");
	}

	public void preorder () {
		System.out.print(getItem() + " ");
		if (getLeft() != null) {
			getLeft().preorder();
		}
		if (getRight() != null) {
			getRight().preorder();
		}
	}

	public void insertBelow (int key) {
		if (key < getItem()) {
			if (getLeft() == null) {
				setLeft(new NodeBst(key));
			}
			else {
				getLeft().insertBelow(key);
			}
		}
		else if (getItem() < key) {
			if (getRight() == null) {
				setRight(new NodeBst(key));
			}
			else {
				getRight().insertBelow(key);
			}
		}
		// else item == key// assuming duplicates is not allowed
	}

	public NodeBst getLeft () {
		return left;
	}

	public void setLeft (NodeBst left) {
		this.left = left;
	}

	public NodeBst getRight () {
		return right;
	}

	public NodeBst setRight (NodeBst right) {
		this.right = right;
		return right;
	}

	public int getDepth () {
		return depth;
	}

	public int getItem () {
		return item;
	}

	public void setItem (int item) {
		this.item = item;
	}

	public NodeBst getParent () {
		return parent;
	}

	public void setParent (NodeBst parent) {
		this.parent = parent;
	}

	public String toString () {
		return item + "";
	}

	public NodeBst getParent (NodeBst curr) {
		return getParentRec(this, curr);
	}

	private NodeBst getParentRec (NodeBst root, NodeBst curr) {
		NodeBst parent = null;
		if (root == null || curr == null) {
			parent = null;
		}
		else if (root.left == curr || root.right == curr) {
			parent = root;
		}
		else if (curr.item < root.item) {
			parent = getParentRec(root.left, curr);
		}
		else if (curr.item > root.item) {
			parent = getParentRec(root.right, curr);
		}

		return parent;
	}

}
// my data structures and algorithm said to process the children first but when
// I said it, someone called 911