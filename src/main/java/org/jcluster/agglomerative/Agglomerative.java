package org.jcluster.agglomerative;

import java.util.ArrayList;
import java.util.List;

import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;
import org.jcluster.util.ClusterUtil;

/** 
 * Implements the hierarchical clustering algorithm
 * @author Rupak Chakraborty
 * @since 20 May, 2016
 */
public class Agglomerative {

	private int numOfClusters;

	/** 
	 * public constructor to initialize the number of clusters
	 * @param numOfClusters Integer containing the number of clusters
	 */
	public Agglomerative(int numOfClusters) {  

		this.numOfClusters = numOfClusters;
	}

	/** 
	 * Returns the list of clusters formed by maximum linkage
	 * @param points List<Point> containing the points to be clustered
	 * @return List<Cluster> cluster objects which are formed
	 */
	public List<Cluster> getClustersMaxDistance(List<Point> points) { 

		List<Cluster> clusters = new ArrayList<Cluster>();
		Cluster cluster;
		List<Point> clusterPoints;

		for (Point p : points) {  

			cluster = new Cluster();
			clusterPoints = new ArrayList<Point>();
			clusterPoints.add(p);
			cluster.setPoints(clusterPoints);
		}

		while(clusters.size() != this.numOfClusters) { 

			int indexFirstCluster = 0;
			int indexSecondCluster = 0;
			double tempDistance;
			double distance = ClusterUtil.getMaximumDistancesBetweenClusterCenters(clusters.get(0),clusters.get(clusters.size()-1));

			for (int i = 0; i < clusters.size(); i++) {

				for (int j = i+1; j < clusters.size(); j++) { 

					tempDistance = ClusterUtil.getMaximumDistancesBetweenClusterCenters(clusters.get(i),clusters.get(j));

					if (tempDistance < distance) { 

						distance = tempDistance;
						indexFirstCluster = i;
						indexSecondCluster = j;

					}
				}
			}

			Cluster firstCluster = clusters.get(indexFirstCluster);
			Cluster secondCluster = clusters.get(indexSecondCluster);
			clusters.remove(indexFirstCluster);
			clusters.remove(indexSecondCluster);
			clusters.add(AgglomerativeUtil.mergeClusters(firstCluster, secondCluster));
		}

		return clusters;
	}
	
	/** 
	 * Returns a list of clusters based on the minimum distance between the points
	 * @param points List<Point> points which are to be clustered
	 * @return List<Cluster> objects which are formed after clustering
	 */
	public List<Cluster> getClustersMinDistance(List<Point> points) { 

		List<Cluster> clusters = new ArrayList<Cluster>();
		Cluster cluster;
		List<Point> clusterPoints;

		for (Point p : points) {  

			cluster = new Cluster();
			clusterPoints = new ArrayList<Point>();
			clusterPoints.add(p);
			cluster.setPoints(clusterPoints);
		}

		while(clusters.size() != this.numOfClusters) { 

			int indexFirstCluster = 0;
			int indexSecondCluster = 0;
			double tempDistance;
			double distance = ClusterUtil.getMinimumDistancesBetweenClusterCenters(clusters.get(0),clusters.get(clusters.size()-1));
			indexFirstCluster = 0; 
			indexSecondCluster = clusters.size()-1;
			
			for (int i = 0; i < clusters.size(); i++) {

				for (int j = i+1; j < clusters.size(); j++) { 

					tempDistance = ClusterUtil.getMinimumDistancesBetweenClusterCenters(clusters.get(i),clusters.get(j));

					if (tempDistance < distance) { 

						distance = tempDistance;
						indexFirstCluster = i;
						indexSecondCluster = j;

					}
				}
			}

			Cluster firstCluster = clusters.get(indexFirstCluster);
			Cluster secondCluster = clusters.get(indexSecondCluster);
			clusters.remove(indexFirstCluster);
			clusters.remove(indexSecondCluster);
			clusters.add(AgglomerativeUtil.mergeClusters(firstCluster, secondCluster));
		}

		return clusters;
	}
	
	/** 
	 * Clusters the points based on the average distance
	 * @param points List<Point> points which are to be clustered
	 * @return List<Cluster> cluster objects which are to be clustered
	 */
	public List<Cluster> getClustersAvgDistance(List<Point> points) { 

		List<Cluster> clusters = new ArrayList<Cluster>();
		Cluster cluster;
		List<Point> clusterPoints;

		for (Point p : points) {  

			cluster = new Cluster();
			clusterPoints = new ArrayList<Point>();
			clusterPoints.add(p);
			cluster.setPoints(clusterPoints);
		}

		while(clusters.size() != this.numOfClusters) { 

			int indexFirstCluster = 0;
			int indexSecondCluster = 0;
			double tempDistance;
			double distance = ClusterUtil.getAverageDistanceBetweenClusterCenters(clusters.get(0),clusters.get(clusters.size()-1));
			indexFirstCluster = 0; 
			indexSecondCluster = clusters.size()-1;
			
			for (int i = 0; i < clusters.size(); i++) {

				for (int j = i+1; j < clusters.size(); j++) { 

					tempDistance = ClusterUtil.getAverageDistanceBetweenClusterCenters(clusters.get(i),clusters.get(j));

					if (tempDistance < distance) { 

						distance = tempDistance;
						indexFirstCluster = i;
						indexSecondCluster = j;

					}
				}
			}

			Cluster firstCluster = clusters.get(indexFirstCluster);
			Cluster secondCluster = clusters.get(indexSecondCluster);
			clusters.remove(indexFirstCluster);
			clusters.remove(indexSecondCluster);
			clusters.add(AgglomerativeUtil.mergeClusters(firstCluster, secondCluster));
		}

		return clusters;
	}
}
