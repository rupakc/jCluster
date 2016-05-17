package org.jcluster.kmeansplusplus;

import java.util.ArrayList;
import java.util.List;

import org.jcluster.dao.Center;
import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;
import org.jcluster.util.ClusterUtil;

/** 
 * Implements the KMeans algorithm with random initial centers
 * @author Rupak Chakraborty
 * @since 17 May,2016
 */
public class KMeansPlusPlus {
	
	private List<Point> points;
	private int numOfCenters; 
	private final Double threshold = 0.01; 
	
	/** 
	 * Public constructor to initialize the class with the set of points and number of clusters
	 * @param points List<Point> containing the points to be clustered
	 * @param numOfCenters Integer containing the number of centers in the cluster
	 */
	public KMeansPlusPlus(List<Point> points,int numOfCenters) { 
		
		this.points = points;
		this.numOfCenters = numOfCenters;
	}
	
	/** 
	 * Returns a list of clusters obtained after implementing KMeans algorithm
	 * @return List<Cluster> containing the clusters formed
	 */
	public List<Cluster> getClusters() {  
		
		List<Center> initialCenter = KMeansPlusPlusUtil.getInitialCenters(points, numOfCenters);
		List<Cluster> clusters = new ArrayList<Cluster>(); 
		Double prevObjective = 0.0;
		Double presentObjective = 0.0; 
		
		for (int i = 0;i < initialCenter.size(); i++) {  
			
			Cluster cluster = new Cluster();
			cluster.setCenter(initialCenter.get(i));
			clusters.add(cluster);
		}
		
		KMeansPlusPlusUtil.assignClusters(points, clusters);
		presentObjective = ClusterUtil.getIntraClusterDistance(clusters);
		
		while(Math.abs(presentObjective-prevObjective) > this.threshold) {  
			
			prevObjective = presentObjective;
			KMeansPlusPlusUtil.computeClusterCenters(clusters);
			KMeansPlusPlusUtil.assignClusters(points, clusters);
			presentObjective = ClusterUtil.getIntraClusterDistance(clusters);
		}
		
		return clusters;
	}
}
