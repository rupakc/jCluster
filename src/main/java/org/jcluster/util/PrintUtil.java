package org.jcluster.util;

import java.util.List;

/** 
 * Defines a set of utility functions for printing to the console 
 * 
 * @author Rupak Chakraborty
 * @since 23 April, 2016
 */

public class PrintUtil {
	
	/** 
	 * Given a vector prints it to the console
	 * @param array Array which is to be printed
	 */
	public static <T> void printVector(T array[]) { 
		
		for (T temp : array) { 
			
			System.out.println(temp);
		}
	}
	
	/** 
	 * Given a list prints it to the screen
	 * @param vector List which is to be printed
	 */
	public static <T> void printVector(List<T> vector) { 
		
		for (T temp : vector) { 
			
			System.out.println(temp);
		}
	}
	
	/** 
	 * Given a matrix prints it to the screen
	 * @param matrix 2-D matrix which is to be printed
	 */
	public static <T> void printMatrix(T [][] matrix) { 
		
		for (int i = 0; i < matrix.length; i++) {  
			
			for (int j = 0; j < matrix[i].length; j++) { 
				
				System.out.print(matrix[i][j] + " ");
			}
			
			System.out.println();
		}
	}
}
