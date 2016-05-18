package org.jcluster.fuzzykmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FuzzyCMeansUtil {
	
	/** 
	 * Returns the value of the initial memberships of the points in fuzzy c-means
	 * @param numOfDataPoints Integer containing the numOfDataPoints
	 * @param numOfClusters Integer containing the numbre of pre-defined clusters
	 * @return Double [][] containing the matrix of initial memberships
	 */
	public static double[][] getInitialMembershipMatrix(int numOfDataPoints,int numOfClusters) { 
		
		double membershipMatrix[][] = new double[numOfDataPoints][numOfClusters];
		for (int i = 0; i < numOfDataPoints; i++) { 
			List<Double> clusterMemberships = getRandomMemberships(1.0, numOfClusters);
			for (int j = 0; j < clusterMemberships.size(); j++) { 
				membershipMatrix[i][j] = clusterMemberships.get(j);
			}
		}
		
		return membershipMatrix;
	}
	
	/** 
	 * Given a limit maxNum returns sum of random numbers equal to that number
	 * @param maxNum Double containing the value of the maxNum
	 * @param countNum Integer containing the number of integers to add
	 * @return List<Double> containing the random numbers
	 */
	public static List<Double> getRandomMemberships(double maxNum,int countNum) { 

		Random random = new Random(System.currentTimeMillis());
		double firstNum = random.nextDouble()*maxNum;
		List<Double> numbers = new ArrayList<Double>();
		numbers.add(firstNum); 
		double remainder = maxNum - firstNum;
		double randomNumber; 

		while(numbers.size() < countNum) { 

			if (remainder == 0.0) {  

				break;
			}

			if (numbers.size() == countNum-1) {   
				
				break;
			}
			
			while(true) { 
				
				randomNumber = random.nextDouble()*maxNum; 
				if (randomNumber <= remainder) {  
					break;
				}
			}
			
			numbers.add(randomNumber);
			remainder = remainder - randomNumber;
		}

		for (int i = numbers.size(); i < countNum; i++) {  
			
			numbers.add(remainder); 
		}

		return numbers;
	}
}
