package org.jcluster.util;

import java.util.List;

import org.jcluster.dao.Point;

/** 
 * Defines a set of distance utility classes for distance metrics
 * @author Rupak Chakraborty
 * @since 15 May 2016
 */
public class DistanceUtil {
	
	/** 
	 * Calculates the euclidean distance between two given points
	 * @param p1 First Point 
	 * @param p2 Second Point
	 * @return Double value containing the euclidean distance between two points
	 */
	public static Double euclideanDistance(Point p1,Point p2) { 

		double distance = 0.0; 

		if (p1.getDimension() != p2.getDimension()) {  

			throw new RuntimeException("Points Not of the same dimension");
		}

		List<Double> p1Values = p1.getValues();
		List<Double> p2Values = p2.getValues(); 

		for (int i = 0; i < p1Values.size(); i++) { 
			distance = distance + Math.pow((p1Values.get(i)- p2Values.get(i)),2.0);
		}

		return Math.sqrt(distance);
	}
	
	/** 
	 * Calculates the manhattan distance between two given points
	 * @param p1 First Point 
	 * @param p2 Second Point
	 * @return Double value containing the manhattan distance between two points
	 */
	public static Double manhattanDistance(Point p1,Point p2) { 

		double distance = 0.0; 

		if (p1.getDimension() != p2.getDimension()) {  

			throw new RuntimeException("Points Not of the same dimension");
		}

		List<Double> p1Values = p1.getValues();
		List<Double> p2Values = p2.getValues(); 

		for (int i = 0; i < p1Values.size(); i++) { 
			distance = distance + Math.abs(p1Values.get(i)-p2Values.get(i));
		}

		return distance;
	}
	
	/** 
	 * Calculates the chessboard distance between two given points
	 * @param p1 First Point 
	 * @param p2 Second Point
	 * @return Double value containing the chessboard distance between two points
	 */
	public static Double chessboardDistance(Point p1,Point p2) { 

		double distance = 0.0; 
		double absoluteDifference;

		if (p1.getDimension() != p2.getDimension()) {  

			throw new RuntimeException("Points Not of the same dimension");
		}

		List<Double> p1Values = p1.getValues();
		List<Double> p2Values = p2.getValues(); 

		for (int i = 0; i < p1Values.size(); i++) { 

			absoluteDifference = Math.abs(p1Values.get(i)-p2Values.get(i)); 

			if (Math.abs(p1Values.get(i)-p2Values.get(i)) > distance) { 

				distance = absoluteDifference;
			}
		}

		return distance;
	}
	
	/** 
	 * Calculates the minkowski distance between two given points
	 * @param p1 First Point 
	 * @param p2 Second Point
	 * @param p value of the L-norm to consider
	 * @return Double value containing the minkowski distance between two points
	 */
	public static Double minkowskiDistance(Point p1,Point p2,double p) { 

		double distance = 0.0; 

		if (p1.getDimension() != p2.getDimension()) {  

			throw new RuntimeException("Points Not of the same dimension");
		}

		List<Double> p1Values = p1.getValues();
		List<Double> p2Values = p2.getValues(); 

		for (int i = 0; i < p1Values.size(); i++) {  

			distance = distance + Math.pow(p1Values.get(i)-p2Values.get(i),p);
		}

		return Math.pow(distance, 1.0/p);
	}
	
	/** 
	 * Calculates the cosine distance between two given points
	 * @param p1 First Point 
	 * @param p2 Second Point
	 * @return Double value containing the cosine distance between two points
	 */
	public static Double cosineDistance(Point p1,Point p2) { 

		double distance = 0.0; 
		
		if (p1.getDimension() != p2.getDimension()) {  

			throw new RuntimeException("Points Are Not of the same dimension");
		}
		
		List<Double> p1Values = p1.getValues();
		List<Double> p2Values = p2.getValues();
		double normFirstPoint = VectorUtil.getNorm(p1);
		double normSecondPoint = VectorUtil.getNorm(p2);
		double dotProduct = 0.0;
		
		for (int i = 0; i < p1Values.size(); i++) {  
			
			dotProduct = dotProduct + p1Values.get(i)*p2Values.get(i);
		}
		
		distance = (dotProduct/(normFirstPoint*normSecondPoint));
		
		return (1.0-distance);
	}
}
