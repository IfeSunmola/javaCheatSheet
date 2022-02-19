import java.util.Arrays;

public class Main {
	static class MaxHeap {
		private int [] heap;
		private int size;
		private int maxsize;

		public MaxHeap (int size) {
			this.maxsize = size;
			this.size = 0;
			heap = new int [this.maxsize + 1];
			heap[0] = Integer.MAX_VALUE;
		}

		private int parent (int pos) {
			return pos / 2;
		}

		private int leftChild (int pos) {
			return (2 * pos);
		}

		private int rightChild (int pos) {
			return (2 * pos) + 1;
		}

		private void swap (int fpos, int spos) {
			int tmp;
			tmp = heap[fpos];
			heap[fpos] = heap[spos];
			heap[spos] = tmp;
		}

		private void downHeapify (int pos) {
			if (pos >= (size / 2) && pos <= size)
				return;

			if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {

				if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
					swap(pos, leftChild(pos));
					downHeapify(leftChild(pos));
				}
				else {
					swap(pos, rightChild(pos));
					// System.out.println("pos: " + pos + ". " + Arrays.toString(heap));
					downHeapify(rightChild(pos));
				}
			}
		}

		private void heapifyUp (int pos) {
			int temp = heap[pos];
			while (pos > 0 && temp > heap[parent(pos)]) {
				heap[pos] = heap[parent(pos)];
				pos = parent(pos);
			}
			heap[pos] = temp;
		}

		public void insert (int element) {
			heap[++size] = element;

			int current = size;
			heapifyUp(current);

		}

		public void print () {
			for (int i = 1; i <= size / 2; i++) {
				// System.out.print(+heap[i] + ": L- " + heap[2 * i] + " R- " + heap[2 * i + 1]);
				// System.out.println();
			}
			for (int i = 0; i < size; i++) {
				System.out.print(heap[i] + " ");
			}
		}

		public int extractMax () {
			int max = heap[1];
			heap[1] = heap[size--];
			downHeapify(1);
			return max;
		}

		public void heapify (int [] h) {
			heap = h;
			size = h.length;
			System.out.println(Arrays.toString(heap));
			for (int i = size / 2 - 1; i >= 0; i--) {
				downHeapify2(i, i);
			}
			for (int i = size - 1; i > 0; i--) {
				swap(0, i);
				downHeapify2(0, i);
			}
		}

		private void downHeapify2 (int pos, int i) {
			// System.out.println(i + ". " + Arrays.toString(heap));
			System.out.println(i + ". " + Arrays.toString(heap));
			if (pos >= (size / 2) && pos <= size) {
				return;
			}
			if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {
				if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
					swap(pos, leftChild(pos));
					downHeapify2(leftChild(pos), i);
				}
				else {
					swap(pos, rightChild(pos));
					downHeapify2(rightChild(pos), i);
				}
			}

		}
	}

	public static void main (String [] arg) {

		MaxHeap maxHeap = new MaxHeap(15);
//		maxHeap.insert(1);
//		maxHeap.insert(4);
//		maxHeap.insert(2);
//		maxHeap.insert(5);
//		maxHeap.insert(13);
//		maxHeap.insert(6);
//		maxHeap.insert(17);
//
//		maxHeap.print();
//		System.out.println("The max is " + maxHeap.extractMax());

		int [] arr2 =
			{ 85, 84, 51, 33, 14 };
		maxHeap.heapify(arr2);
		// maxHeap.print();
	}

}