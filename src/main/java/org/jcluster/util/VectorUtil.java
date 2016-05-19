package org.jcluster.util;

import java.util.ArrayList;
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
	 * 
	 * @param p1
	 * @param p2
	 * @return
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
	 * 
	 * @param p1
	 * @param p2
	 * @return
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
}
