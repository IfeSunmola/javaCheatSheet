
public class sortingAlgorithms {
    private static final int BREAKPOINT = 50;// used in hybrid quick sort
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
     * private function to merge the two halves of the list. At the end of this method, we copy back
     * to the original array
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
    public static void quickSort (int [] array) {
        quickSort(array, 0, array.length);
    }

    /**
     * private recursive helper method to do quickSort. This method divides the list into "smalls" and
     * "bigs" with a pivot chosen using the median of three method; then recursively sort the smalls
     * and bigs
     * 
     * @param array the array to sort
     * @param start where to start sorting from
     * @param end   where to end sorting
     */
    private static void quickSort (int [] array, int start, int end) {
        if (end - start == 2) {// case for 2 items
            if (array[start + 1] < array[start]) {
                swap(array, start, start + 1);
            }
        }
        else if (end - start > 2) {// recursive call, more than 2 items
            setPivot(array, start, end);// make the pivot the first data
            int pivotPos = partition(array, start, end);// actual pivot position after partioning
            quickSort(array, start, pivotPos);
            quickSort(array, pivotPos + 1, end);
        }
    }

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
}
