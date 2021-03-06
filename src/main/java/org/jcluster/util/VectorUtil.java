package org.jcluster.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jcluster.dao.Point;

/** 
 * Defines a set of utility functions for working with vectors
 * @author Rupak Chakraborty
 * @since 15 May, 2016
 */
public class VectorUtil {
	
	/** 
	 * Calculates the norm of a given point
	 * @param p Point whose norm is to be calculated
	 * @return Double containing the norm of the point
	 */
	public static Double getNorm(Point p) { 

		double normValue = 0.0;
		List<Double> pointValues = p.getValues();

		for (Double value : pointValues) {  

			normValue = normValue + Math.pow(value, 2.0);
		}

		return Math.sqrt(normValue);
	}
	
	/** 
	 * Given a point P and a number multiplies the point with the number
	 * @param p Point containing the list of values
	 * @param num Double containing the number
	 * @return Point with number multiplied
	 */
	public static Point multiplyPointWithNumber(Point p,double num) { 
		
		double temp;
		
		for (int i = 0; i < p.getValues().size(); i++) { 
			temp = p.getValues().get(i);
			p.getValues().set(i,(Double)temp*num);
		}
		
		return p;
	}
	
	public static Point dividePointWithNumber(Point p,double num) { 
		
		double temp;
		
		for (int i = 0; i < p.getValues().size(); i++) { 
			temp = p.getValues().get(i);
			p.getValues().set(i,(Double)temp/num);
		}
		
		return p;
	}

	/** 
	 * Given two points add them up
	 * @param p1 First Point which is to be added
	 * @param p2 Second Point which is to be added
	 * @return Point containing the addition of two points
	 */
	public static Point addPoints(Point p1,Point p2) { 
		
		Point p = new Point();
		List<Double> addPoints = new ArrayList<Double>();
		
		if (p1.getValues().size() != p2.getValues().size()) { 
			throw new RuntimeException("Points not of the same dimension can't add");
		}
		
		for (int i = 0; i < p1.getValues().size(); i++) { 
			
			addPoints.add(p1.getValues().get(i)+p2.getValues().get(i));
		}
		
		p.setValues(addPoints);
		
		return p;
	}
	
	/** 
	 * Given two points add their specific dimensions
	 * @param p1 first point which is to be added
	 * @param p2 second point which is to be added
	 * @return Point which contains the subtraction of two points
	 */
	public static Point subtractPoints(Point p1,Point p2) { 
		
		Point p = new Point();
		List<Double> addPoints = new ArrayList<Double>();
		
		if (p1.getValues().size() != p2.getValues().size()) { 
			throw new RuntimeException("Points not of the same dimension can't add");
		}
		
		for (int i = 0; i < p1.getValues().size(); i++) { 
			
			addPoints.add(p1.getValues().get(i)- p2.getValues().get(i));
		}
		
		p.setValues(addPoints);
		
		return p;
	}
	
	/** 
	 * Given the list of points calculates the median per dimension
	 * @param points List<Point> whose median per dimension has to be calculated
	 * @return Point object containing the value of the median per dimension
	 */
	public static Point getMedianPerDimension(List<Point> points) { 
		
		int dimension = points.get(0).getValues().size();
		List<Double> medianValues = new ArrayList<Double>();
		List<Double> pointsPerDimension = new ArrayList<Double>(); 
		Point medianPoint = new Point(); 
		
		for (int i = 0; i < dimension; i++) {  
			
			pointsPerDimension.clear(); 
			
			for (int j = 0; j < points.size(); j++) {  
				
				pointsPerDimension.add(points.get(j).getValues().get(i));
			}
			
			medianValues.add(getMedian(pointsPerDimension));
		}
		
		medianPoint.setValues(medianValues);
		medianPoint.setDimension(dimension);
		
		return medianPoint;
	} 
	
	/** 
	 * Given a list of numbers returns the median of the of the list
	 * @param numbers List<Double> containing the list of numbers
	 * @return Double containing the median of the list of numbers
	 */
	private static Double getMedian(List<Double> numbers) { 
		
		double median;
		Collections.sort(numbers);
		int size = numbers.size();
		
		if (size%2 != 0) { 
			median = numbers.get(size/2);
		} else {
			if (size != 1) {
				median = (numbers.get(size/2) + numbers.get((size/2)-1))/2.0;
			} else {
				median = numbers.get(size/2); 
			}
		}
		
		return median;
	}
}
