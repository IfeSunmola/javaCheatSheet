import java.io.PrintStream;
import java.util.Scanner;

@SuppressWarnings("all")
public class Misc {
	public static void main (String [] args) {
		removeComma();
	}

	public static void removeComma () {
		System.out.println("Enter text to remove comma from:");
		Scanner scanner = new Scanner(System.in);
		String input = "";
		while (scanner.hasNextLine()) {
			String [] tokens = scanner.nextLine().split(", ");
			for (String token : tokens) {
				input += token + " ";
			}
			input += "\n";
		}
		System.out.println(input);

		scanner.close();
	}

	public void setNext (int xPos, int yPos, int [] [] pos) {
		// get the next data from xPos and yPos
		int next;
		if (posIsValid(xPos, yPos + 1, pos.length, pos[xPos].length)) {// check if the next index is in the maze
			next = pos[xPos][yPos + 1];
		}
		else if (yPos + 1 >= pos[xPos].length && xPos + 1 < pos.length) {//
			// if at the end of a row and that row is not the last, make next the first data
			// on the next row
			// e.g if (3, 8) is the end of a row, the next of (3, 8) is (4,0)
			next = pos[xPos + 1][0];
		}
	}

	public void setPrev (int xPos, int yPos, int [] [] pos) {
		// get the previous data from xPos and yPos
		int prev;
		if (posIsValid(xPos, yPos - 1, pos.length, pos[1].length)) {
			prev = pos[xPos][yPos - 1];
		}
		else {
			if (yPos == 0) {
				if (!(xPos == 0)) {
					prev = pos[xPos - 1][pos[1].length - 1];
				}
			}
		}
	}

	public int [] getNeighbours (int xPos, int yPos, int [] [] array) {// no need for a for loop
		// get the neighbours of the cell at xPos, yPos from pos
		int size = 0;
		int [] neighbour = new int [8];
		if (posIsValid(xPos - 1, yPos, array.length, array[2].length)) {// top middle, North
			neighbour[size++] = array[xPos - 1][yPos];
		}

		if (posIsValid(xPos, yPos - 1, array.length, array[2].length)) {// middle left, West
			neighbour[size++] = array[xPos][yPos - 1];
		}

		if (posIsValid(xPos, yPos + 1, array.length, array[2].length)) {// middle right, East
			neighbour[size++] = array[xPos][yPos + 1];

		}

		if (posIsValid(xPos + 1, yPos, array.length, array[2].length)) {// bottom middle, South
			neighbour[size++] = array[xPos + 1][yPos];
		}

		if (posIsValid(xPos - 1, yPos - 1, array.length, array[2].length)) {// top left, North West
			neighbour[size++] = array[xPos - 1][yPos - 1];
		}

		if (posIsValid(xPos - 1, yPos + 1, array.length, array[2].length)) {// top right, North East
			neighbour[size++] = array[xPos - 1][yPos + 1];
		}

		if (posIsValid(xPos + 1, yPos - 1, array.length, array[2].length)) {// bottom left, South West
			neighbour[size++] = array[xPos + 1][yPos - 1];
		}

		if (posIsValid(xPos + 1, yPos + 1, array.length, array[2].length)) {// bottom right, South East
			neighbour[size++] = array[xPos + 1][yPos + 1];
		}
		return neighbour;
	}

	public boolean posIsValid (int xPos, int yPos, int row, int col) {
		return !(xPos < 0 || yPos < 0 || xPos >= row || yPos >= col);
	}

	private static String getFileName () {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the input file name (.txt files only):");
		String fileName = keyboard.nextLine();
		keyboard.close();
		System.out.println("\nProcessing file " + fileName + "...\n");
		return fileName;
	}

	private static int recBinarySearch (int [] numbers, int low, int high, int key) {
		if (low > high) {
			return -1;
		}
		int mid = (low + high) / 2;
		if (numbers[mid] < key) {
			return recBinarySearch(numbers, mid + 1, high, key);
		}
		if (numbers[mid] > key) {
			return recBinarySearch(numbers, low, mid - 1, key);
		}
		return mid;
	}

	private static void startForLoop () {
		int startRow = 2, startCol = 3;
		int row = 9, col = 9;
		for (int i = startRow; i < row; i++) {
			int j;
			if (i == startRow || i == startCol) {
				j = i;
			}
			// for (int j = (i == startRow ? startCol : 0); j < col; j++) {
			for (j = (i == startRow ? startCol : 0); j < col; j++) {

			}

		}

		for (int i = startRow; i < row; i++) {
			for (int j = (i == startRow ? startCol : 0); j < col; j++) {

			}
		}
	}

	public void traversePreOrder (StringBuilder sb, NodeBst curr) {
//		if (curr != null) {
//			sb.append(curr.toString());
//			sb.append("\n");
//			traversePreOrder(sb, curr.getLeft());
//			traversePreOrder(sb, curr.getRight());
//		}
	}

	public void print (PrintStream os) {
//		StringBuilder sb = new StringBuilder();
//		traversePreOrder(sb, this.root);
//		os.print(sb.toString());
	}

	void printLevelOrder () {
//		Queue queue = new Queue();
//		queue.enqueue(root);
//		queue.enqueue(null);
//		while (!queue.isEmpty()) {
//			Node temp = queue.dequeue();
//			if (temp != null)
//				System.out.print(temp.toString() + "  ");
//			if (temp == null) {
//				System.out.println();
//				if (queue.isEmpty())
//					break;
//				queue.enqueue(null);
//				continue;
//			}
//
//			/* Enqueue left child */
//			if (temp.getLeft() != null) {
//				queue.enqueue(temp.getLeft());
//			}
//
//			/* Enqueue right child */
//			if (temp.getRight() != null) {
//				queue.enqueue(temp.getRight());
//			}
//		}
	}
}
