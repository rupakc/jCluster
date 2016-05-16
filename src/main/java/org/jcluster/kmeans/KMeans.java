package org.jcluster.kmeans;

import java.util.ArrayList;
import java.util.List;

import org.jcluster.dao.Center;
import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;
import org.jcluster.util.ClusterUtil;

/** 
 * 
 * @author Rupak Chakraborty
 * @since 15 May,2016
 */
public class KMeans {
	
	private List<Point> points;
	private int numOfCenters; 
	private final Double threshold = 0.01; 
	
	/** 
	 * 
	 * @param points
	 * @param numOfCenters
	 */
	public KMeans(List<Point> points,int numOfCenters) { 
		
		this.points = points;
		this.numOfCenters = numOfCenters;
	}
	
	/** 
	 * 
	 * @return
	 */
	public List<Cluster> getClusters() {  
		
		List<Center> initialCenter = KMeansUtil.getInitialCenters(points, numOfCenters);
		List<Cluster> clusters = new ArrayList<Cluster>(); 
		Double prevObjective = 0.0;
		Double presentObjective = 0.0; 
		
		for (int i = 0;i < initialCenter.size(); i++) {  
			
			Cluster cluster = new Cluster();
			cluster.setCenter(initialCenter.get(i));
			clusters.add(cluster);
		}
		
		KMeansUtil.assignClusters(points, clusters);
		presentObjective = ClusterUtil.getIntraClusterDistance(clusters);
		
		while(Math.abs(presentObjective-prevObjective) > this.threshold) {  
			
			prevObjective = presentObjective;
			KMeansUtil.computeClusterCenters(clusters);
			KMeansUtil.assignClusters(points, clusters);
			presentObjective = ClusterUtil.getIntraClusterDistance(clusters);
		}
		
		return clusters;
	}
}
