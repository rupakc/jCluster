package org.jcluster.kmedoids;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jcluster.dao.Center;
import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;
import org.jcluster.util.ClusterUtil;
import org.jcluster.util.DistanceUtil;

/** 
 * Set of utility functions for implementing the k-medoids algorithm
 * @author Rupak Chakraborty
 * @since 19 May,2016
 */
public class KMedoidsUtil {

	/** 
	 * Given the list of points and the number of clusters returns the initial centers
	 * @param points List<Point> list of point objects to be clustered
	 * @param numOfClusters Integer containing the number of clusters
	 * @return List<Center> containing the list of initial centers
	 */
	public static List<Center> getInitialCenters(List<Point> points,int numOfClusters) { 

		List<Center> centers = new ArrayList<Center>();
		Random random = new Random();
		List<Integer> initialClusterIndex = new ArrayList<Integer>(); 
		int tempIndex; 

		while(initialClusterIndex.size() != numOfClusters) {  

			tempIndex = random.nextInt(points.size()); 

			if (!initialClusterIndex.contains((Integer)tempIndex)) { 

				initialClusterIndex.add(tempIndex);
			}
		} 

		for (int i = 0; i < initialClusterIndex.size(); i++) {  

			Center center = new Center();
			center.setPoint(points.get(initialClusterIndex.get(i)));
			centers.add(center);
		} 

		return centers;
	}

	/** 
	 * Given a set of points and the clusters assigns each point to its closest cluster
	 * @param points List<Point> containing the point objects to be clustered
	 * @param clusters List<Cluster> containing the clusters
	 */
	public static void assignClusters(List<Point> points, List<Cluster> clusters) {  

		double distance;
		double tempDistance;
		Point tempPoint;
		Cluster tempCluster; 
		int clusterIndex; 

		for (int i = 0; i < points.size(); i++) {  

			tempPoint = points.get(i);
			distance = DistanceUtil.euclideanDistance(tempPoint, clusters.get(0).getCenter().getPoint());
			clusterIndex = 0; 

			for (int j = 0; j < clusters.size(); j++) {  

				tempCluster = clusters.get(j);
				tempDistance = DistanceUtil.euclideanDistance(tempPoint, tempCluster.getCenter().getPoint());

				if (tempDistance < distance) {  

					distance = tempDistance;
					clusterIndex = j;
				}
			}

			if (!clusters.get(clusterIndex).getPoints().contains(tempPoint)) {  

				for (int j = 0; j < clusters.size(); j++) { 

					if (clusters.get(j).getPoints().contains(tempPoint) && j != clusterIndex) {  

						clusters.get(j).getPoints().remove(tempPoint);
					}
				}

				clusters.get(clusterIndex).getPoints().add(tempPoint);
			}
		}
	}
	
	/** 
	 * Given a list of points and a list of clusters swap each non-medoid point with a medoid
	 * @param points List<Point> containing the point objects
	 * @param clusters List<Cluster> containing the cluster objects
	 */
	public static void swapConfiguration(List<Point> points, List<Cluster> clusters) { 
		
		Center tempCenter;
		Point tempPoint; 
		double initialObjective;
		double currentObjective; 
		
		for (int i = 0; i < clusters.size(); i++) {  
			
			tempCenter = clusters.get(i).getCenter();
			tempPoint = tempCenter.getPoint(); 
			initialObjective = ClusterUtil.getIntraClusterDistance(clusters); 
			
			for (int j = 0; j < points.size(); j++) { 
				
				tempCenter.setPoint(points.get(j));
				assignClusters(points, clusters);
				currentObjective = ClusterUtil.getIntraClusterDistance(clusters);
				
				if (currentObjective > initialObjective) { 
					
					tempCenter.setPoint(tempPoint);
					assignClusters(points,clusters);
				}
			}
		}
	}
}
