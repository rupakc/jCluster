package org.jcluster.dbscan;

import java.util.ArrayList;
import java.util.List;

import org.jcluster.dao.Cluster;
import org.jcluster.dao.Density;
import org.jcluster.dao.Point;

/**
 * Implements the DBSCAN algorithm (Density based clustering algorithm)
 * @author Rupak Chakraborty
 * @since 21 May, 2016
 */
public class DBSCAN {
	
	private int minPoints;
	private double epsilon; 
	
	/** 
	 * public constructor to initialize the minimum number of points and epsilon
	 * @param minPoints Integer containing the minimum number of points
	 * @param epsilon Double containing the value of the epsilon
	 */
	public DBSCAN(int minPoints,double epsilon) {  
		
		this.minPoints = minPoints;
		this.epsilon = epsilon;
	}
	
	/**
	 * Calculates the clusters for the given list of points
	 * @param points List<Point> point objects which are to be clustered
	 * @return Density object containing the list of clusters and the outliers
	 */
	public Density getClusters(List<Point> points) { 
		
		List<Cluster> clusters = new ArrayList<Cluster>();
		Cluster cluster;
		List<Point> noisePoints = new ArrayList<Point>();
		List<Point> neighborhoodPoints; 
		Density density = new Density();
		
		for(Point p : points) { 
			
			if (p.isSet()) { 
				continue;
			}
			
			p.setSet(true);
			neighborhoodPoints = DBSCANUtil.getNeighborhoodPoints(points,p,this.epsilon);
			
			if (neighborhoodPoints.size() < this.minPoints) {  
				
				noisePoints.add(p); 
				
			} else {  
				
				cluster = new Cluster();
				DBSCANUtil.expandCluster(points,neighborhoodPoints,p,cluster,this.epsilon,this.minPoints);
				clusters.add(cluster);
			}
		}
		
		density.setClusters(clusters);
		density.setNoisePoints(noisePoints);
		
		return density;
	}
}
