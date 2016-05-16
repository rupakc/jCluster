package org.jcluster.metric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jcluster.dao.Cluster;
import org.jcluster.util.ClusterUtil;

/** 
 * Calculates the Davies Bouldin Index for a given cluster 
 * @author Rupak Chakraborty
 * @since 16 May, 2016
 */
public class DaviesBouldinIndex {
	
	/** 
	 * Calculates the davies bouldin Index given the list of clusters
	 * @param clusters List<Cluster> containing the list of clusters
	 * @return Double containing the value of the davies bouldin index
	 */
	public static Double getDaviesBouldinIndex(List<Cluster> clusters) {  
		
		double intraClusterDistanceFirst;
		double intraClusterDistanceSecond;
		double interClusterSeparation; 
		List<Double> rCoefficients;
		List<Double> dunnIndexList = new ArrayList<Double>(); 
		double tempR; 
		double dunnIndex = 0.0; 
		
		for (int i = 0; i < clusters.size(); i++) {  
			
			intraClusterDistanceFirst = ClusterUtil.getAverageDistanceFromCentroid(clusters.get(i));
			rCoefficients = new ArrayList<Double>(); 
			
			for (int j = i+1; j < clusters.size(); j++) {  
				
				interClusterSeparation = ClusterUtil.getDistanceBetweenClusterCenters(clusters.get(i), clusters.get(j));
				intraClusterDistanceSecond = ClusterUtil.getAverageDistanceFromCentroid(clusters.get(j));
				tempR = (intraClusterDistanceFirst + intraClusterDistanceSecond)/interClusterSeparation;
				rCoefficients.add(tempR);
			}
			
			Collections.sort(rCoefficients);
			dunnIndexList.add(rCoefficients.get(rCoefficients.size()-1));
		}
		
		for (Double dunnValue : dunnIndexList) { 
			
			dunnIndex = dunnIndex + dunnValue;
		}
		
		return dunnIndex/dunnIndexList.size();
	}
}
