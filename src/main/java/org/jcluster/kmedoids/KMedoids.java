package org.jcluster.kmedoids;

import java.util.ArrayList;
import java.util.List;

import org.jcluster.dao.Center;
import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;
import org.jcluster.util.ClusterUtil;

/** 
 * Class to implement the KMedoids algorithm
 * @author Rupak Chakraborty
 * @since 19 May, 2016
 */
public class KMedoids {
	
	private int numOfClusters;
	private double threshold = 0.1; 
	
	/** 
	 * public constructor to initialize the number of pre-defined clusters
	 * @param numOfClusters Integer containing the number of pre-defined clusters
	 */
	public KMedoids(int numOfClusters) { 
		
		this.numOfClusters = numOfClusters;
	}
	
	/** 
	 * public constructor to initialize the number of clusters and the threshold
	 * @param numOfClusters Integer containing the number of clusters
	 * @param threshold Double value containing the threshold
	 */
	public KMedoids(int numOfClusters,double threshold) { 
		
		this.numOfClusters = numOfClusters;
		this.threshold = threshold;
	}
	
	/** 
	 * Given the list of points returns the list of resulting clusters
	 * @param points List<Point> containing the points to be clustered
	 * @return List<Cluster> cluster objects resulting from the given algorithm
	 */
	public List<Cluster> getClusters(List<Point> points) { 
		
		List<Center> centers = KMedoidsUtil.getInitialCenters(points, this.numOfClusters);
		Cluster cluster; 
		List<Cluster> clusters = new ArrayList<Cluster>(); 
		double prevObjective = 0.0;
		double currentObjective = 0.0;
		
		for (int i = 0; i < centers.size(); i++) { 
			
			cluster = new Cluster();
			cluster.setCenter(centers.get(i));
			clusters.add(cluster);
		}
		
		KMedoidsUtil.assignClusters(points, clusters);
		currentObjective = ClusterUtil.getIntraClusterDistance(clusters);
		
		while(Math.abs(currentObjective-prevObjective) > this.threshold) { 
			
			prevObjective = currentObjective;
			KMedoidsUtil.swapConfiguration(points, clusters);
			currentObjective = ClusterUtil.getIntraClusterDistance(clusters);
		}
		
		return clusters;
	}
}
