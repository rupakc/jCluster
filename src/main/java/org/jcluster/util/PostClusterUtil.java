package org.jcluster.util;

import java.util.List;

import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;

/** 
 * A set of utility functions used for post-processing of the clusters 
 * @author Rupak Chakraborty
 * @since 21 May, 2016
 */
public class PostClusterUtil {
	
	/** 
	 * For the given list of clusters sets the cluster number in each given point
	 * @param clusters List<Cluster> cluster objects whose points have to be indexed
	 */
	public static void setClusterCenterIndex(List<Cluster> clusters) {  
		
		List <Point> points; 
		
		for (int i = 0; i < clusters.size(); i++) { 
			
			points = clusters.get(i).getPoints();
			
			for (int j = 0; j < points.size(); j++) { 
				
				points.get(i).setClusterNumber(i);
			}
		}
	}
}
