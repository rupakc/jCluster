package org.jcluster.dbscan;

import java.util.ArrayList;
import java.util.List;

import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;
import org.jcluster.util.DistanceUtil;

/** 
 * Implements a set of utilities used for DBSCAN algorithm
 * @author Rupak Chakraborty
 * @since 21 May, 2016
 */
public class DBSCANUtil {
	
	/** 
	 * Given a point a the epsilon distance returns the list of neighborhood points
	 * @param points List<Point> containing the points to be clustered
	 * @param p Point whose epsilon neighborhood is to be found
	 * @param epsilon Double containing the radius of the neighborhood
	 * @return List<Point> containing the neighborhood points of a given point
	 */
	public static List<Point> getNeighborhoodPoints(List<Point> points, Point p,double epsilon) { 
		
		List<Point> neighborhoodPoints = new ArrayList<Point>();
		neighborhoodPoints.add(p);
		double distance; 
		
		for (Point tempPoint: points) {
			distance = DistanceUtil.euclideanDistance(p, tempPoint);
			if (distance <= epsilon) {
				neighborhoodPoints.add(tempPoint);
			}
		}
		
		return neighborhoodPoints;
	}
	
	/**
	 * Iteratively expands the cluster for a given point
	 * @param points List<Point> points which are to be clustered
	 * @param neighborhoodPoints List<Point> containing the neighborhood points
	 * @param p Core point which is to be expanded
	 * @param cluster Cluster object which is to be populated
	 * @param epsilon Double containing the radius of the epsilon neighborhood
	 * @param minPoints Integer containing the minimum number of points to be considered in the neighborhood
	 */
	public static void expandCluster(List<Point> points,List<Point> neighborhoodPoints,Point p,Cluster cluster,double epsilon,int minPoints) {
		
		p.setIspartOfCluster(true);
		cluster.getPoints().add(p);
		List<Point> extendedNeighborhood;
		
		for (Point tempPoint : neighborhoodPoints) {  
			
			if(!tempPoint.isSet()) {  
				
				tempPoint.setSet(true);
				extendedNeighborhood = getNeighborhoodPoints(points, tempPoint, epsilon); 
				extendedNeighborhood.add(tempPoint); 
				
				if (extendedNeighborhood.size() >= minPoints) {  
					
					neighborhoodPoints.addAll(extendedNeighborhood);
				}
			}
			
			if (!tempPoint.isIspartOfCluster()) {  
				
				tempPoint.setIspartOfCluster(true);
				cluster.getPoints().add(tempPoint);
			}
		}
	}
}
