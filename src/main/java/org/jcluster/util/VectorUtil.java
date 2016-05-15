package org.jcluster.util;

import java.util.List;

import org.jcluster.dao.Point;

/** 
 * Defines a set of utility functions for working with vectors
 * @author Rupak Chakraborty
 * @since 15 May, 2016
 */
public class VectorUtil {

	public static Double getNorm(Point p) { 

		double normValue = 0.0;
		List<Double> pointValues = p.getValues();

		for (Double value : pointValues) {  

			normValue = normValue + Math.pow(value, 2.0);
		}

		return Math.sqrt(normValue);
	}
}
