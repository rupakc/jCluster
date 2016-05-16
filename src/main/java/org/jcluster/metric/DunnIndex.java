package org.jcluster.metric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jcluster.dao.Cluster;
import org.jcluster.util.ClusterUtil;

/** 
 * Calculates the Dunn Index for a given cluster
 * @author Rupak Chakraborty
 * @since 16 May,2016
 */
public class DunnIndex {
	
	/** 
	 * Calculates the Dunn Index of the clusters 
	 * @param clusters List<Cluster> containing the output of the clustering algorithm
	 * @return Double containing the value of the Dunn Index
	 */
	public static Double getDunnIndex(List<Cluster> clusters) { 
		
		List<Double> intraClusterMetrics = new ArrayList<Double>();
		List<Double> interCentroidMetrics = new ArrayList<Double>();
		double numerator;
		double denominator; 
		
		for (Cluster cluster : clusters) {  
			
			intraClusterMetrics.add(ClusterUtil.getAverageDistanceFromCentroid(cluster));
		}
		
		for (int i = 0; i < clusters.size(); i++) { 
			
			for (int j = i+1; j < clusters.size(); j++) { 
				
				interCentroidMetrics.add(ClusterUtil.getDistanceBetweenClusterCenters(clusters.get(i), clusters.get(j)));
			}
		}
		
		Collections.sort(intraClusterMetrics);
		Collections.sort(interCentroidMetrics);
		
		numerator = interCentroidMetrics.get(0);
		denominator = intraClusterMetrics.get(intraClusterMetrics.size()-1);
		
		return numerator/denominator;
	}
}
