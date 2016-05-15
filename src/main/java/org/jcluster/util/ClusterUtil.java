package org.jcluster.util;

import java.util.List;

import org.jcluster.dao.Center;
import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;

/** 
 * A set of utility functions for managing the cluster object
 * @author Rupak Chakraborty
 * @since 15 May, 2016
 */
public class ClusterUtil {
	
	public static Double getIntraClusterDistance(Cluster cluster) { 
		
		double distance = 0.0;
		List<Point> points = cluster.getPoints();
		Center center = cluster.getCenter();
		
		for (int i = 0; i < points.size(); i++) { 
			
			distance = distance + DistanceUtil.euclideanDistance(points.get(i), center.getPoint());
		} 
		
		return distance;
	}
}
