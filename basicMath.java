@SuppressWarnings("all")
public class basicMath {

	public static void main (String [] args) {
		System.out.println(medianOfThree2(40, 30, 60));
	}

	private static int exp (int x, int m) {
		int result = Integer.MIN_VALUE;
		if (m == 1) {// base case
			result = x;
		}
		else if (m > 1) { // recursive case
			result = exp(x, m - 1) * x;
		}
		else { // we don’t handle m<1
			System.out.println("Error"); // or throw exception
		}
		return result;
	}

	private static int medianOfThree (int startData, int midData, int endData) {
		return Math.max(Math.min(startData, midData), Math.min(Math.max(startData, endData), midData));
	}

	private static int medianOfThree2 (int startData, int midData, int endData) {
		int medIndex;
		// determine which of the 3 items is the median
		if ((midData < startData && startData < endData) || endData < startData && startData < midData) {
			medIndex = startData;
		}
		else if ((startData < midData && midData < endData) || endData < midData && midData < startData) {
			medIndex = midData;
		}
		else {
			medIndex = endData;
		}
		return medIndex;
	}
}
