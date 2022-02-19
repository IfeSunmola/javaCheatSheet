

/*
Java Program
Check if a matrix is symmetric
*/
public class MyMatrix {

//Display element of matrix
	public void show_data (int [] [] matrix, int row, int col) {
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				System.out.print("  " + matrix[i][j]);
			}
			System.out.print("\n");
		}

	}

//Check that if a given matrix is symmetric or not
	public void is_symmetric (int [] [] matrix) {
		boolean status = true;

		int row = matrix.length;

		int col = matrix[0].length;

		if (row != col) {
			status = false;
		}

		for (int i = 0; i < row && status == true; ++i) {
			for (int j = 0; j < col && status == true; ++j) {
				// Compare matrix element
				if (matrix[i][j] != matrix[j][i]) {
					status = false;
				}
			}
		}
		show_data(matrix, row, col);

		if (status == true) {
			System.out.print("  Yes\n");
		}
		else {
			System.out.print("  No\n");
		}
	}

	public static void main (String [] args) {

		MyMatrix obj = new MyMatrix();

		// Matrix A
		int [] [] matrix1 =
			{
					{ 7, 1, 5, 6 },
					{ 1, 2, 4, 4 },
					{ 5, 4, 1, 1 },
					{ 6, 4, 1, 6 } };

		obj.is_symmetric(matrix1);

		// Matrix B
		int [] [] matrix2 =
			{
					{ 1, 2, 3, 6 },
					{ 2, 5, 4, 4 },
					{ 5, 4, 1, 1 },
					{ 6, 4, 1, 6 } };
		obj.is_symmetric(matrix2);
	}
}
