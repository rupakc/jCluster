package org.jcluster.dao;

import java.util.List;

/**
 * Defines a cluster object
 * @author Rupak Chakraborty
 * @since 15 May, 2016
 */
public class Cluster {

	private List<Point> points;
	private int clusterSize;
	private double averageSize;
	private Center center;
	
	/**
	 * @return the points
	 */
	public List<Point> getPoints() {
		return points;
	} 
	
	/**
	 * @param points the points to set
	 */
	public void setPoints(List<Point> points) {
		this.points = points;
	} 
	
	/**
	 * @return the clusterSize
	 */
	public int getClusterSize() {
		return clusterSize;
	} 
	
	/**
	 * @param clusterSize the clusterSize to set
	 */
	public void setClusterSize(int clusterSize) {
		this.clusterSize = clusterSize;
	} 
	
	/**
	 * @return the averageSize
	 */
	public double getAverageSize() {
		return averageSize;
	} 
	 
	/**
	 * @param averageSize the averageSize to set
	 */
	public void setAverageSize(double averageSize) {
		this.averageSize = averageSize;
	} 
	
	/**
	 * @return the center
	 */
	public Center getCenter() {
		return center;
	} 
	
	/**
	 * @param center the center to set
	 */
	public void setCenter(Center center) {
		this.center = center;
	}
}
