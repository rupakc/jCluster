package org.jcluster.dao;

import java.util.ArrayList;
import java.util.List;

public class Point {
	
	private List<Double> values;
	private int dimension;
	private int indexNumber;
	private int clusterNumber;
	private boolean isSet;
	private boolean isCenter;

	public Point() {  
		
		this.values = new ArrayList<Double>();
		this.isCenter = false;
	}
	
	/** 
	 * 
	 * @param dimension
	 * @return
	 */
	public static Point getPointWithZeroValues(int dimension) { 
		
		Point p = new Point();
		p.setDimension(dimension);
		List<Double> values = new ArrayList<Double>(); 
		
		for(int i = 0; i < dimension; i++) {  
			
			values.add(0.0);
		}
		
		p.setValues(values);
		
		return p;
	}
	
	/**
	 * @return the values
	 */
	public List<Double> getValues() {
		return values;
	} 
	
	/**
	 * @param values the values to set
	 */
	public void setValues(List<Double> values) {
		this.values = values;
	} 
	
	/**
	 * @return the dimension
	 */
	public int getDimension() {
		return dimension;
	} 
	
	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	} 
	
	/**
	 * @return the indexNumber
	 */
	public int getIndexNumber() {
		return indexNumber;
	}
	
	/**
	 * @param indexNumber the indexNumber to set
	 */
	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}

	/**
	 * @return the clusterNumber
	 */
	public int getClusterNumber() {
		return clusterNumber;
	}

	/**
	 * @param clusterNumber the clusterNumber to set
	 */
	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	/**
	 * @return the isSet
	 */
	public boolean isSet() {
		return isSet;
	}

	/**
	 * @param isSet the isSet to set
	 */
	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}

	/**
	 * @return the isCenter
	 */
	public boolean isCenter() {
		return isCenter;
	}

	/**
	 * @param isCenter the isCenter to set
	 */
	public void setCenter(boolean isCenter) {
		this.isCenter = isCenter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dimension;
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (dimension != other.dimension)
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}
}
