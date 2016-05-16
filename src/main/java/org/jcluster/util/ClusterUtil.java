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

	/** 
	 * Given a cluster finds the distance of all the points from the centroid
	 * @param cluster Cluster object whose average intra cluster distance has to be calculated
	 * @return Double containing the value of the intra cluster distance
	 */
	public static Double getIntraClusterDistance(Cluster cluster) { 

		double distance = 0.0;
		List<Point> points = cluster.getPoints();
		Center center = cluster.getCenter();

		for (int i = 0; i < points.size(); i++) { 

			distance = distance + DistanceUtil.euclideanDistance(points.get(i), center.getPoint());
		} 

		return distance;
	}
	
	/** 
	 * Calculates the average distance of all points from the centroid of the cluster
	 * @param cluster Cluster object for which the average distance has to be calculated
	 * @return Double containing the average distance from the centroid
	 */
	public static Double getAverageDistanceFromCentroid(Cluster cluster) { 
		
		double distance = 0.0;
		List<Point> points = cluster.getPoints();
		Center center = cluster.getCenter();
		int clusterSize = points.size() + 1; 
		
		for (int i = 0; i < points.size(); i++) { 

			distance = distance + DistanceUtil.euclideanDistance(points.get(i), center.getPoint());
		} 

		return distance/clusterSize;
	}
	
	/** 
	 * Calculates the maximum distance between the points in a given cluster
	 * @param cluster Cluster object for which the minimum distance has to be calculated
	 * @return Double containing the maximum distance between any two points in the cluster
	 */
	public static Double getMaximumDistanceInCluster(Cluster cluster) {
		
		List<Point> points = cluster.getPoints();
		double maxDistance = 0.0;
		Point firstPoint;
		Point secondPoint; 
		double tempDistance; 
		
		for (int i = 0; i < points.size(); i++) {  
			
			firstPoint = points.get(i); 
			
			for (int j = 0; j < points.size(); j++) {  
				
				secondPoint = points.get(j); 
				
				if (i != j) { 
					
					tempDistance = DistanceUtil.euclideanDistance(firstPoint, secondPoint); 
					
					if ( tempDistance> maxDistance) { 
						
						maxDistance = tempDistance;
					}
				}
			}
		}
		
		return maxDistance;
	}
	
	/** 
	 * Calculates the pairwise intra-cluster euclidean distance 
	 * @param cluster Cluster object for which the pairwise intra cluster distance has to be calculated
	 * @return Double containing the pairwise intra-cluster distance
	 */
	public static Double getPairwiseIntraClusterDistance(Cluster cluster) {  
		
		int clusterSize = cluster.getPoints().size();
		double pairwiseDistance = getAverageIntraClusterDistance(cluster);
		pairwiseDistance = pairwiseDistance * (clusterSize+1); 
		
		return pairwiseDistance/(clusterSize*(clusterSize-1));
	}
	
	/** 
	 * Calculates the euclidean distance between the centers of two clusters
	 * @param firstCluster Cluster object representing the first cluster
	 * @param secondCluster Cluster object representing the second cluster
	 * @return Double containing the euclidean distance between the two cluster centers
	 */
	public static Double getDistanceBetweenClusterCenters(Cluster firstCluster,Cluster secondCluster) { 
		
		Center firstCenter = firstCluster.getCenter();
		Center secondCenter = secondCluster.getCenter();
		
		double centroidDistance = DistanceUtil.euclideanDistance(firstCenter.getPoint(), secondCenter.getPoint());
		
		return centroidDistance;
	} 
	
	/** 
	 * Given a list of clusters returns the sum of distances of each cluster from its centroid
	 * @param clusters List<Clusters> containing the cluster objects
	 * @return Double containing the value of the intra cluster distance
	 */
	public static Double getIntraClusterDistance(List<Cluster> clusters) { 

		double distance = 0.0; 

		for (Cluster cluster : clusters) {  

			distance = distance + getIntraClusterDistance(cluster);
		}

		return distance;
	}
	
	/** 
	 * Calculates the pairwise distance between all points in a given cluster
	 * @param cluster Cluster object for which the average intra cluster distance has to be calculated
	 * @return Double containing the value of the intra cluster distance
	 */
	public static Double getAverageIntraClusterDistance(Cluster cluster) { 

		List<Point> points = cluster.getPoints();
		double averageDistance = 0.0;
		int numOfPoints = points.size();
		Point firstPoint;
		Point secondPoint; 

		for (int i = 0; i < points.size(); i++) {  
			
			firstPoint = points.get(i); 
			
			for (int j = 0; j < points.size(); j++) {  
				
				secondPoint = points.get(j); 
				
				if (i != j) { 
					
					averageDistance = averageDistance + DistanceUtil.euclideanDistance(firstPoint, secondPoint);
				}
			}
		}
		
		averageDistance = averageDistance/numOfPoints+1;
		
		return averageDistance;
	}
	
	/** 
	 * Calculates the distance of a point from all the other points in a given cluster
	 * @param p Point whose distance has to be calculated
	 * @param cluster Cluster object from which the distances have to be calculated
	 * @return Double containing the intra cluster distance
	 */
	public static Double getIntraClusterDistance(Point p,Cluster cluster) { 
		
		double averageDistance = 0.0;
		List<Point> points = cluster.getPoints();
		int numOfPoints = points.size() + 1;
		
		for (Point tempPoint : points) { 
			
			averageDistance = averageDistance + DistanceUtil.euclideanDistance(tempPoint, p);
		}
		
		return (averageDistance/numOfPoints);
	}
}
