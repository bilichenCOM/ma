package hw1;
<<<<<<< HEAD

import java.util.Arrays;
=======
>>>>>>> 0e9ea38... corrections2

public class Matrix {

	public static double maxIn(double[][] matrix) {
		double max = matrix[0][0];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
<<<<<<< HEAD
				if (matrix[i][j] > max)
					max = matrix[i][j];
=======
				if (matrix[i][j] > max) {
					max = matrix[i][j];
				}
>>>>>>> 0e9ea38... corrections2
			}
		}
		return max;
	}

	public static double minIn(double[][] matrix) {
		double min = matrix[0][0];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
<<<<<<< HEAD
				if (matrix[i][j] < min)
					min = matrix[i][j];
=======
				if (matrix[i][j] < min) {
					min = matrix[i][j];
				}
>>>>>>> 0e9ea38... corrections2
			}
		}
		return min;
	}
<<<<<<< HEAD

	public static double[] getMainDiagonalOf(double[][] matrix) {
		double[] diagonal = new double[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			diagonal[i] = matrix[i][i];
		}
		return diagonal;
	}

	public static double[] getSubDiagonalOf(double[][] matrix) {
		double[] diagonal = new double[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			diagonal[i] = matrix[i][matrix.length - 1 - i];
		}
		return diagonal;
	}

	public static double[][] sortRows(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			Arrays.sort(matrix[i]);
		}
		return matrix;
	}

	public static double[][] sortColumns(double[][] matrix) {
		return revert(sortRows(revert(matrix)));
	}

	public static double[][] revert(double[][] matrix) {
		int r = matrix.length;
		int c = matrix[0].length;
		double[][] reverted = new double[c][r];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				reverted[j][i] = matrix[i][j];
			}
		}
		return reverted;
	}
=======
>>>>>>> 0e9ea38... corrections2
}