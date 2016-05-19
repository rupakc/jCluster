package org.jcluster.fuzzykmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jcluster.dao.Center;
import org.jcluster.dao.Point;
import org.jcluster.util.DistanceUtil;
import org.jcluster.util.VectorUtil;

/** 
 * Contains a set of utility functions for Fuzzy C means algorithm
 * @author Rupak Chakraborty
 * @since 19 May,2016
 */
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

	/** 
	 * 
	 * @param points
	 * @param membershipMatrix
	 * @param degree
	 * @return
	 */
	public static List<Center> getUpdatedCenters(List<Point> points,double [][] membershipMatrix,int degree) { 

		List<Center> centers = new ArrayList<Center>(); 
		double sumMembership;
		Point centerPoint;
		double memberPowerDegree; 

		for (int i = 0; i < membershipMatrix[0].length; i++) {  

			sumMembership = 0.0;
			centerPoint = Point.getPointWithZeroValues(points.get(0).getValues().size()); 

			for (int j = 0; j < membershipMatrix.length; j++) {  

				memberPowerDegree = Math.pow(membershipMatrix[j][i],degree);
				sumMembership = sumMembership + memberPowerDegree;
				centerPoint = VectorUtil.addPoints(centerPoint,VectorUtil.multiplyPointWithNumber(points.get(j),memberPowerDegree));
			}

			centerPoint = VectorUtil.dividePointWithNumber(centerPoint,sumMembership);
			Center center = new Center();
			center.setPoint(centerPoint);
			centers.add(center);
		}

		return centers;
	}
	
	/** 
	 * Updates the values of the class memberships for all the given points 
	 * @param points List<Point> point objects 
	 * @param membershipMatrix The matrix containing the membership values
	 * @param centers List<Center> list of centers
	 * @param degree Integer containing the degree of membership of the points
	 */
	public static void updateMemberships(List<Point> points,double[][] membershipMatrix,List<Center> centers,int degree) { 

		Point p;
		double numerator; 
		double denominator; 
		double updatedMembership; 

		for (int i = 0; i < membershipMatrix.length; i++) {  

			p = points.get(i); 

			for (int j = 0; j < membershipMatrix[i].length; j++) {  

				numerator = DistanceUtil.euclideanDistance(p,centers.get(j).getPoint());
				updatedMembership = 0.0; 

				for (int k = 0; k < centers.size(); k++) {  

					denominator = DistanceUtil.euclideanDistance(p,centers.get(k).getPoint());
					updatedMembership = updatedMembership + Math.pow((numerator/denominator),2.0/degree-1);
				}

				membershipMatrix[i][j] = 1.0/updatedMembership;
			}
		}
	}
	
	/** 
	 * Calculates the value of the objective function for the current cluster configuration
	 * @param points List<Point> points in the cluster
	 * @param membershipMatrix Matrix containing the membership values
	 * @param centers List<Center> containing the centers in the present configuration
	 * @param degree Integer containing value of the membership degree 
	 * @return Double value containing the objective function value
	 */
	public static Double getValueOfObjectiveFunction(List<Point> points,double [][] membershipMatrix,List<Center> centers,int degree) { 
		
		double objectiveValue = 0.0;
		
		for (int i = 0; i < membershipMatrix.length; i++) {  
			
			for (int j = 0; j < membershipMatrix[i].length; j++) { 
				
				objectiveValue += Math.pow(membershipMatrix[i][j],degree)*DistanceUtil.euclideanDistance(points.get(i),centers.get(j).getPoint());
			}
		}
		
		return objectiveValue;
	}
}
