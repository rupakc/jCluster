package org.jcluster.kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jcluster.dao.Center;
import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;
import org.jcluster.util.DistanceUtil;

/** 
 * Set of utility functions for the kmeans algorithm
 * @author Rupak Chakraborty
 * @since 15 May, 2016
 */
public class KMeansUtil {
	
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
	 * Given the list of clusters computes/recomputes the cluster centers
	 * @param clusters List<Cluster> containing the clusters whose centroid have to be calculated
	 */
	public static void computeClusterCenters(List<Cluster> clusters) { 
		
		for (Cluster cluster : clusters) { 
			List<Point> points = cluster.getPoints();
			Point meanPoint = getMeanPoint(points);
			cluster.getCenter().setPoint(meanPoint);
		}
	}
	
	/** 
	 * Given a set of points returns the mean point of the set
	 * @param points List<Point> containing the point objects
	 * @return Point object which represents the mean
	 */
	public static Point getMeanPoint(List<Point> points) { 
		
		int setSize = points.size();
		List<Double> meanValues = new ArrayList<Double>();
		int dimension = points.get(0).getDimension(); 
		Point p; 
		
		for (int i = 0; i < dimension; i++) { 
			
			for (int j = 0; j < points.size(); j++) { 
				
				meanValues.set(i, meanValues.get(i) + points.get(j).getValues().get(i));
			}
		}
		
		for (int i = 0; i < dimension; i++) { 
			
			meanValues.set(i, meanValues.get(i)/setSize);
		}
		
		p = new Point();
		p.setDimension(dimension);
		p.setValues(meanValues);
		
		return p;
	}
	
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
}