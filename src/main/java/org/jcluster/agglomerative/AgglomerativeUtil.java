package org.jcluster.agglomerative;

import java.util.ArrayList;
import java.util.List;

import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;

/** 
 * Implements the utility functions useful for agglomerative clustering
 * @author Rupak Chakraborty
 * @since 20 May,2016
 */
public class AgglomerativeUtil {
	
	public static Cluster mergeClusters(Cluster firstCluster, Cluster secondCluster) { 
		
		Cluster mergedCluster = new Cluster();
		List<Point> mergedPoints = new ArrayList<Point>();
		
		for (int i = 0; i < firstCluster.getPoints().size(); i++) { 
			
			mergedPoints.add(firstCluster.getPoints().get(i));
		}
		
		for (int i = 0; i < secondCluster.getPoints().size(); i++) { 
			
			mergedPoints.add(secondCluster.getPoints().get(i));
		}
		
		mergedCluster.setPoints(mergedPoints);
		
		return mergedCluster;
	}
}
