import java.util.Arrays;

public class Heap {
	public static void main (String [] args) {
		int [] num =
			{ 89, 81, 83, 65, 26, 12, 64 };
		Heap h = new Heap();
		for (int n : num) {
			h.insert(new HeapData(n));
		}
		// 89 81 83 65 26 12 64
		System.out.println(h);

		HeapData a1 = new HeapData(23);
		HeapData a2 = new HeapData(21);
		HeapData a3 = new HeapData(56);
		HeapData a4 = new HeapData(12);
		HeapData a5 = new HeapData(54);
		HeapData a6 = new HeapData(34);
		HeapData a7 = new HeapData(90);
		HeapData a8 = new HeapData(40);
		HeapData a9 = new HeapData(21);

		a1 = new HeapData(91);
		a2 = new HeapData(61);
		a3 = new HeapData(73);
		a4 = new HeapData(46);
		a5 = new HeapData(14);
		a6 = new HeapData(23);
		a7 = new HeapData(69);

		HeapData [] d =
//			{ a1, a2, a3, a4, a5, a6, a7, a8, a9 };
				{ a1, a2, a3, a4, a5, a6, a7 };

		System.out.println(Arrays.toString(d));

		System.out.println("h before: " + h);
		h.heapSortOrHeapify(d);
		System.out.println("h after: " + h);
	}

	private HeapData [] heap;
	private int heapSize;

	public Heap () {
		heap = new HeapData [2000];
		heapSize = 0;
	}

	public void insert (HeapData itemPriority) {
		if (!isFull()) {
			heap[heapSize] = itemPriority;
			heapSize++;
			siftUp(heapSize - 1);
		}
	}

	public HeapData deleteMax () {
		HeapData deleted = null;
		if (!isEmpty()) {
			deleted = heap[0];
			heap[0] = heap[heapSize - 1];
			heapSize--;
			siftDown(0);
		}
		return deleted;
	}

	public void heapSortOrHeapify (HeapData [] h) {
		heap = h;
		heapSize = h.length;

		for (int i = heapSize / 2 - 1; i >= 0; i--) {
			siftDown(i);
			System.out.println(i + ". " + Arrays.toString(heap));
		}
		heap[0] = new HeapData(91);
		heap[1] = new HeapData(61);
		heap[2] = new HeapData(73);
		heap[3] = new HeapData(46);
		heap[4] = new HeapData(14);
		heap[5] = new HeapData(23);
		heap[6] = new HeapData(69);
		for (int i = heapSize - 1; i > 0; i--) {
			swap(heap, 0, i);
			siftDown(0);
			System.out.println(i + ". " + Arrays.toString(heap));
		}
	}

	public String toString () {
		String result = "";
		for (int i = 0; i < heapSize; i++) {
			result += heap[i] + " ";
		}
		return result;
	}

	public boolean isEmpty () {
		return heapSize == 0;
	}

	public boolean isFull () {
		return heapSize >= heap.length;
	}

	private void swap (HeapData [] array, int i, int j) {
		HeapData temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private static int leftChild (int i) {
		return 2 * i + 1;
	}

	private static int rightChild (int i) {
		return 2 * i + 2;
	}

	private static int parent (int i) {
		return (i - 1) / 2;
	}

	private void siftUp (int index) {
		HeapData toSift = heap[index]; // make a "hole" in the heap
		int i = index;
		int iParent = parent(index);

		while (i > 0 && (heap[iParent].compareTo(toSift)) == -1) {
			heap[i] = heap[iParent]; // move the "hole" up to the parent
			i = iParent;
			iParent = parent(i);
		} // end while
		heap[i] = toSift; // put the sifted item into the correct position

	}

	private void siftDown (int index) {
		int largestIndex = index;
		int leftIndex = leftChild(index);
		int rightIndex = rightChild(index);

		if (leftIndex < heapSize || rightIndex < heapSize) {
			if (heap[leftIndex].compareTo(heap[largestIndex]) > 0) {
				// if (heap[leftIndex] > heap[largestIndex]) {
				largestIndex = leftIndex;
			}
			if (heap[leftIndex].compareTo(heap[largestIndex]) < 0) {
				largestIndex = rightIndex;
			}
		}
		if (largestIndex != index) {
			swap(heap, index, largestIndex);
			siftDown(largestIndex);
		}
	}

	private void percolateDown (int index, HeapData [] heap, int heapSize) {// same as sift down
		int child = leftChild(index);
		HeapData data = heap[index];

		while (child < heapSize) {
			HeapData max = data;
			int maxIndex = -1;
			for (int i = 0; i < 2 && i + child < heapSize; i++) {
				if (heap[i + child].compareTo(max) > 0) {
					max = heap[i + child];
					maxIndex = i + child;
				}
			}
			if (max.compareTo(data) == 0) {
				return;
			}
			else {
				swap(heap, maxIndex, index);
				index = maxIndex;
				child = leftChild(index);
			}
		}
	}
}

class HeapData {
	private Object data;

	public HeapData (Object data) {
		this.data = data;
	}

	public int compareTo (Object toCompare) {
		int result = Integer.MIN_VALUE;
		if (data instanceof Integer && toCompare instanceof Integer) {
			int tempData = (Integer) data;
			int tempCompare = (Integer) toCompare;
			if (tempData < tempCompare) {
				result = -1;
			}
			else if (tempData > tempCompare) {
				result = 1;
			}
			else {
				result = 0;
			}

		}

		return result;
	}

	public String toString () {
		return data.toString();
	}
}
