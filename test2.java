
import java.util.Arrays;
import java.util.Random;

/**
 * SunmolaNicholasA2Q1.java
 *
 * COMP 2140 SECTION D01 ASSIGNMENT 2, QUESTION 1
 *
 * @author Nicholas Sunmola, 7844193
 * @version June 3rd, 2021
 *
 *          PURPOSE: This program implements insertion sort, bubble sort, selection sort, merge
 *          sort, quick sort, hybrid quick sort and shell sort
 **/

public class test2 {

	// Control the testing
	private static final int ARRAY_SIZE = 10000;
	private static final int SAMPLE_SIZE = 300; // The number of trials in each experiment.

	// Control the randomness
	private static final int NUM_SWAPS = ARRAY_SIZE / 2;
	private static Random generator = new Random(System.nanoTime());

	// Control the base cases for hybrid quick sort:
	private static final int BREAKPOINT = 50;

	// Controls which sort is tried.
	private static final int INSERTION_SORT = 0;
	private static final int BUBBLE_SORT = 1;
	private static final int SELECTION_SORT = 2;
	private static final int MERGE_SORT = 3;
	private static final int QUICK_SORT = 4;
	private static final int HYBRID_QUICK_SORT = 5;
	private static final int SHELL_SORT = 6;

	/*********** main and the method it calls *************************/

	/*******************************************************************
	 * main
	 *
	 * Purpose: Print out "bookend" messages and call the method that does all the testing of the
	 * sorting algorithms.
	 *
	 ******************************************************************/
	public static void main (String [] args) {
		int [] a =
			{ 78, 34, 50, 72, 53, 21, 26, 37, 29, 61, 59, 65, 16, 41, 12, 48 };
		int [] b =
			{ 13, 1, 10, 6, 20, 7, 4 };
		quickSort(a);
		System.out.println(Arrays.toString(a));

//		System.out.println(Arrays.toString(b));
//		System.out.println("\n\n");
//		quickSort(b);
//		System.out.println(Arrays.toString(b));
//		System.out.println("\nProcessing ends normally\n");
	} // end main

	/********** Add sort methods here ********************/
	// start insertion sort
	/**
	 * public driver method for insertionSort
	 * 
	 * @param array array to sort
	 */
	public static void insertionSort (int [] array) {
		insertionSort(array, 0, array.length);
	}

	/**
	 * private helper method for insertionSort. This method sorts the array data between start and end
	 * 
	 * @param array array to Sort
	 * @param start where to start sorting from
	 * @param end   where to end sorting
	 */
	private static void insertionSort (int [] array, int start, int end) {
		int curr;//
		int j; // index to compare to curr
		for (int i = start; i < end; i++) {
			curr = array[i];
			j = i - 1;
			System.out.println(Arrays.toString(array));
			while (j >= 0 && array[j] > curr) {
				array[j + 1] = array[j];
				j--;
			} // end while
			array[j + 1] = curr;
		}
	}
	// end insertion sort

	// start bubble sort
	/**
	 * public method to sort using bubleSort
	 * 
	 * @param array the array to sort
	 */
	public static void bubbleSort (int [] array) {
		int size = array.length - 1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size - i; j++) {
				if (array[j] > array[j + 1]) {// bigger
					swap(array, j + 1, j);
				}
			}
		}
	}
	// end bubble sort

	// start selection sort
	/**
	 * public driver method to sort using selection sort. This method uses the private helper method
	 * findMin
	 * 
	 * @param array the array to sort
	 **/
	public static void selectionSort (int [] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int minIndex = findMin(array, i);// get the index with the smallest data
			swap(array, minIndex, i);
		}
	}

	/**
	 * helper method to find the index with the smallest data starting from "start"
	 * 
	 * @param array array to search
	 * @param start index to start from
	 * 
	 * @return index of the smallest data in the array
	 */
	private static int findMin (int [] array, int start) {
		int minIndex = start;
		int size = array.length;
		for (int i = start + 1; i < size; i++) {
			if (array[i] < array[minIndex]) {// keep updating minIndex if a smaller data is found
				minIndex = i;
			}
		}
		return minIndex;
	}
	// end selection sort

	// start merge sort
	/**
	 * public driver method to do mergeSort
	 * 
	 * @param array the array to sort
	 */
	public static void mergeSort (int [] array) {
		int [] temp = new int [array.length];// used in merging
		mergeSort(array, 0, array.length, temp);
	}

	/**
	 * recursive function to sort the list. base case: if there are only 2 items to sort. This method
	 * recursively splits the list into two halves and keeps sorting till base case
	 * 
	 * @param array the array to sort
	 * @param start where to start sorting from
	 * @param end   where to end sorting
	 * @param temp  a temporary array to store the array in before/after merging
	 */
	private static void mergeSort (int [] array, int start, int end, int [] temp) {
		if (end - start == 2) {// case for 2 items
			if (array[start + 1] < array[start]) {
				swap(array, start, start + 1);
			}
		}
		else if (end - start > 2) {// more than 2 item to sort
			int mid = start + (end - start) / 2;// middle index
			mergeSort(array, start, mid, temp);// sort left half
			mergeSort(array, mid, end, temp);// sort right half
			merge(array, start, mid, end, temp);// merge list
		}
	}

	/**
	 * private function to merge the two halves of the list. At the end of this method, we copy back to
	 * the original array
	 * 
	 * the left half doesn't include mid. i.e left half is: start to mid - 1 and right half is mid to
	 * end
	 * 
	 * @param array the array to copy back to (original array)
	 * @param start staring index of the left half
	 * @param mid   end index of the left half and starting index for the right half (
	 * @param end   end index for the right half
	 * @param temp  used to store the new merged array before copying back to the original
	 */
	private static void merge (int [] array, int start, int mid, int end, int [] temp) {
		int leftIndex = start;// index of current item in left half
		int rightIndex = mid;/// index of the current item in right half
		int tempIndex;// index in temp

		for (tempIndex = start; tempIndex < end; tempIndex++) {
			// if still in left && (no values in right || leftIndex data is smaller than rightIndex data
			if (leftIndex < mid && (rightIndex >= end || array[leftIndex] < array[rightIndex])) {
				temp[tempIndex] = array[leftIndex];// copy the item from the left half into the temp array
				leftIndex++;
			}
			else {// there are values in the right half
				temp[tempIndex] = array[rightIndex];
				rightIndex++;
			}
		}
		for (tempIndex = start; tempIndex < end; tempIndex++)// copy the merged items into the original array
			array[tempIndex] = temp[tempIndex];
	}
	// end merge sort

	// start quick sort
	/**
	 * public driver method to do quicksort
	 * 
	 * @param array the array to sort
	 */
	/*******************************************************************
	 * quickSort --- public driver method
	 *
	 * Purpose: Call the private quickSort method that does all the work, passing the correct values to
	 * its extra parameters (indices start and end) to make it sort the whole array.
	 *
	 ******************************************************************/
	public static void quickSort (int [] array) {
		quickSort(array, 0, array.length);
	} // end quickSort (public driver method)

	/*******************************************************************
	 * quickSort --- private recursive helper method
	 *
	 * Purpose: Does a quick sort ONLY on array[start], array[start+1], ..., array[end-1] (the rest of
	 * the array is untouched).
	 *
	 * Base cases: If there are 0 items or only 1 item, the list is already sorted. If there are 2 items
	 * to sort, then swap them if they are out of order. (Needed because we choose the pivot using the
	 * median-of-three method, which requires at least three items.)
	 *
	 ******************************************************************/
	private static void quickSort (int [] array, int start, int end) {
		int pivotPosn; // Where pivot ends up after partitioning is done

		if ((end - start) == 2) { // One of the base cases: just two items to sort
			// If the two items are out of order, swap them
			if (array[start + 1] < array[start]) {
				System.out.println(Arrays.toString(array));
				swap(array, start, start + 1);
			}
		}
		else if ((end - start) > 2) { // Recursive case
			choosePivot(array, start, end); // Choose pivot and put into array[start]
//			pivotPosn = 1;
			pivotPosn = partition(array, start, end); // Partition into smalls;pivot;bigs
			quickSort(array, start, pivotPosn); // Quick sort smalls
			// System.out.println(Arrays.toString(array));
			quickSort(array, pivotPosn + 1, end); // Quick sort bigs

		} // end if-elseif
		System.out.println(Arrays.toString(array));

		// Base case: Do nothing if 0 items or just 1 item
	} // end quickSort (private recursive method)

	private static void choosePivot (int [] array, int start, int end) {
//		int mid = start + (end - start) / 2; // the middle index between indices start and end
//
//		if (array[mid] <= array[start]) {
//			if (array[end - 1] <= array[mid]) {
//				// array[end-1] <= array[mid] <= array[start]
//				// array[mid] is the median
//				swap(array, start, mid);
//			}
//			else if (array[end - 1] <= array[start]) {
//				// array[mid] < array[end-1] <= array[start]
//				// array[end-1] is the median
//				swap(array, start, end - 1);
//			} // end if-else-if
//				// else array[mid] <= array[start] < array[end-1]
//				// array[start] is the median, so we don't have to do anything
//		}
//		else { // array[start] < array[mid]
//			if (array[mid] <= array[end - 1]) { // array[start] < array[mid] <= array[end-1]
//				// array[mid] is the median
//				swap(array, start, mid);
//			}
//			else if (array[start] <= array[end - 1]) { // array[start] <= array[end-1] < array[mid]
//				// array[end-1] is the median
//				swap(array, start, end - 1);
//			} // end if-else-if
		swap(array, start, end - 1);
		// else array[end-1] < array[start] < array[mid]
		// array[start] is the median, so we don't have to do anything
	} // end if-else

	// end choosePivot

	/**
	 * finds the middle element between 3 elements
	 * 
	 * @param startData data at index start
	 * @param midData   data at index mid
	 * @param endData   data at index end
	 * 
	 * @return the data b for a < b < c
	 */
	private static int medianOfThree (int startData, int midData, int endData) {
		int medIndex;
		if ((midData < startData && startData < endData) || endData < startData && startData < midData) {
			medIndex = startData;// startData is median
		}
		else if ((startData < midData && midData < endData) || endData < midData && midData < startData) {
			medIndex = midData;// midData is median
		}
		else {
			medIndex = endData;// endData is median
		}
		return medIndex;
	}

	/**
	 * private helper method to select a pivot and swap it with the first element
	 * 
	 * @param array array to use
	 * @param start where to start checking from
	 * @param end   where to stop checking
	 */
	private static void setPivot (int [] array, int start, int end) {
		int mid = (start + end) / 2;
		swap(array, start, medianOfThree(array[start], array[mid], array[end - 1]));
	}

	/**
	 * private helper method to rearrange the array into smalls, pivot and bigs
	 * 
	 * @param array the array to rearrange
	 * @param start where to start from
	 * @param end   where to end
	 * 
	 * @return position of the pivot after partitioning
	 */
	private static int partition (int [] array, int start, int end) {
		int bigStart = start + 1;// index of the first data in bigs
		int pivot = array[start];// take the first data as pivot (has already been set with setPivot method)
		for (int curr = start + 1; curr < end; curr++) {
			if (array[curr] < pivot) {// belongs in smalls
				swap(array, bigStart, curr);
				bigStart++;
			}
			// else; belongs in big, do nothing
		} // end for
		swap(array, start, bigStart - 1);// put the pivot in position
		return bigStart - 1;// index of pivot
	}
	// end quick sort

	// start hybrid quick sort
	/**
	 * public driver method to do hybrid quick sort
	 * 
	 * @param array the array to sort
	 */
	public static void hybridQuickSort (int [] array) {
		hybridQuickSort(array, 0, array.length);
	}

	/**
	 * private helper method to sort the array using insertion sort and quickSort. The method will do
	 * insertion sort if there are less than BREAKPOINT items to sort. If notm it follows the same
	 * pattern as quickSort
	 * 
	 * @param array the array to sort
	 * @param start where to start sorting from
	 * @param end   where to stop sorting
	 */
	private static void hybridQuickSort (int [] array, int start, int end) {
		int pivotPos;
		if (end - start < BREAKPOINT) {// less than 50 items
			insertionSort(array, start, end);
		}
		else {
			setPivot(array, start, end);
			pivotPos = partition(array, start, end);
			hybridQuickSort(array, start, pivotPos);
			hybridQuickSort(array, pivotPos + 1, end);
		}
	}
	// end hybrid Quick sort

	// start shell sort
	/**
	 * 
	 * public method to sort using shellSort. Shell sort divides the list into sublists and sorts the
	 * sublist using insertionSort
	 * 
	 * @param array the array to sort
	 */
	public static void shellSort (int [] array) {
		int gap = 1;
		int size = array.length;
		while (gap <= size) {// get gap until gap is bigger than size for the first gap
			gap = 3 * gap + 1;// using knuth's sequence
		}
		while (gap > 0) {
			for (int outer = gap; outer < size; outer++) {// dividing into sub arrays
				int temp = array[outer];
				int inner = outer;
				while (inner > gap - 1 && array[inner - gap] >= temp) {
					array[inner] = array[inner - gap];// insertion sort
					inner -= gap;
				}
				array[inner] = temp;
			}
			gap = (gap - 1) / 3;
		}
	}

	// end shell sort
	/****************** Other miscellaneous methods ********************/

	/*******************************************************************
	 * swap
	 *
	 * Purpose: Swap the items stored in positions i and j in array.
	 *
	 ******************************************************************/
	private static void swap (int [] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	} // end swap

	/*******************************************************************
	 * isSorted
	 *
	 * Purpose: Return true if the input array is sorted into ascending order; return false otherwise.
	 *
	 * Idea: If every item is <= to the item immediately after it, then the whole list is sorted.
	 *
	 ******************************************************************/
	public static boolean isSorted (int [] array) {
		boolean sorted = true;

		// Loop through all adjacent pairs in the
		// array and check if they are in proper order.
		// Stops at first problem found.
		for (int i = 1; sorted && (i < array.length); i++)
			sorted = array[i - 1] <= array[i];
		return sorted;
	} // end method isSorted

	/*******************************************************************
	 * checkArray
	 *
	 * Purpose: Print an error message if array is not correctly sorted into ascending order. (If the
	 * array is correctly sorted, checkArray does nothing.)
	 *
	 ******************************************************************/
	private static void checkArray (int [] array, String sortType) {
		if (!isSorted(array))
			System.out.println(sortType + " DID NOT SORT CORRECTLY *** ERROR!!");
	}

	/*******************************************************************
	 * fillArray
	 *
	 * Purpose: Fills the given array with the numbers 0 to array.length-1.
	 *
	 ******************************************************************/
	public static void fillArray (int [] array) {

		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		} // end for

	} // end fillArray

	/*******************************************************************
	 * randomizeArray
	 *
	 * Purpose: Does numberOfSwaps swaps of randomly-chosen positions in the given array.
	 *
	 ******************************************************************/
	public static void randomizeArray (int [] array, int numberOfSwaps) {
		for (int count = 0; count < numberOfSwaps; count++) {
			int i = generator.nextInt(array.length);
			int j = generator.nextInt(array.length);
			swap(array, i, j);
		}
	} // end randomizeArray

	/*******************************************************************
	 * arithmeticMean
	 *
	 * Purpose: Compute the average of long values. To avoid long overflow, use type double in the
	 * computation.
	 *
	 ******************************************************************/
	public static double arithmeticMean (long data[]) {
		double sum = 0;
		for (int i = 0; i < data.length; i++)
			sum += (double) data[i];
		return sum / (double) data.length;
	} // end arithmeticMean

	/**
	 * COMP 2140 A2Q1 Sorting Test --- Summer 2021
	 * 
	 * Array size: 10000
	 * 
	 * Number of swaps: 5000
	 * 
	 * Number of trials of each sort: 300
	 * 
	 * 
	 * Insertion sort mean: 1.1532540333333334E7 ns
	 * 
	 * Bubble sort mean: 1.18952151E8 ns
	 * 
	 * Selection sort mean: 6.1971741E7 ns
	 * 
	 * Merge sort mean: 929185.0 ns
	 * 
	 * Quick sort mean: 774336.6666666666 ns
	 * 
	 * Hybrid quick sort mean: 622404.6666666666 ns
	 * 
	 * Shell sort mean: 1180473.3333333333 ns
	 */

	/*
	 * 1. Yes, Insertion Sort was faster than selection sort; most likely because the list is "nearly
	 * sorted", even though it was randomized. Insertion sort works well on nearly sorted lists while
	 * selection sort doesn't
	 * 
	 * 2. Yes, quick sort was faster than insertion sort; Insertion sort performs poorly on large amount
	 * of data. Even though the list was most likely nearly sorted, insertion sort is still slow
	 * compared to quick sort. If the array size was much smaller, insertion sort will most likely be
	 * faster than quick sort
	 * 
	 * 3. Yes, hybrid quick sort was faster than quick sort. This is because hybrid quick sort's base
	 * case is to do an insertion sort when there are less than BREAKPOINT items (50 items) and as said
	 * earlier, insertion sort is faster than quick sort if the array size is small
	 * 
	 * If each step of hybrid quick sort and quick sort is timed, both algorithms will have
	 * (approximately) the same time up to the point where only BREAKPOINT items are left to sort; in
	 * which hybrid quick sort will finish faster. The smaller BREAKPOINT is, the more hybrid quick sort
	 * begins to look more like quick sort
	 * 
	 * 4. Although hybrid quick sort and quick sort has a worst case of n^2, the worst case happens when
	 * a bad pivot is chosen and we've avoided that by using the median of 3 method. Assuming the
	 * machine allows doesn't have memory limitations and recursion won't be a problem, I would
	 * recommend hybrid quick sort.
	 * 
	 * 5. Bubble sort and selection sort.
	 * 
	 * For bubble sort, the continuous comparing and swapping can and will be inefficient on really
	 * large data sets
	 * 
	 * Selection sort isn't as bad as bubble sort but in most cases, insertion sort is more efficient
	 * than selection sort
	 */
} // end class A2Q1SortingTemplate
