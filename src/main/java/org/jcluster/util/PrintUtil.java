package org.jcluster.util;

import java.util.List;

/** 
 * Defines a set of utility functions for printing to the console 
 * 
 * @author Rupak Chakraborty
 * @since 23 April, 2016
 */

public class PrintUtil {
	
	public static <T> void printVector(T array[]) { 
		
		for (T temp : array) { 
			
			System.out.println(temp);
		}
	}
	
	public static <T> void printVector(List<T> vector) { 
		
		for (T temp : vector) { 
			
			System.out.println(temp);
		}
	}
	
	public static <T> void printMatrix(T [][] matrix) { 
		
		for (int i = 0; i < matrix.length; i++) {  
			
			for (int j = 0; j < matrix[i].length; j++) { 
				
				System.out.print(matrix[i][j] + " ");
			}
			
			System.out.println();
		}
	}
}
