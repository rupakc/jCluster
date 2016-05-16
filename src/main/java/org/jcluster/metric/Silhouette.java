package org.jcluster.metric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jcluster.dao.Cluster;
import org.jcluster.dao.Point;
import org.jcluster.util.ClusterUtil;

public class Silhouette {
	
	/** 
	 * Calculates the silhouette coefficient for the given point and the list of clusters
	 * @param p Point for which the silhouette coefficient has to be calculated
	 * @param clusters List<Cluster> of clusters obtained after a successful run of the algorithm
	 * @return Double containing the value of the silhouette coefficient
	 */
	public static Double silhouetteCoefficient(Point p,List<Cluster> clusters) { 

		Cluster pointOwnCluster = clusters.get(p.getClusterNumber());
		double averageIntraClusterDistance = ClusterUtil.getIntraClusterDistance(p, pointOwnCluster);
		double averageInterClusterDistance;
		double denominator;
		double numerator; 
		List<Double> averageInterClusterDistances = new ArrayList<Double>();

		for (int i = 0; i < clusters.size(); i++) {  

			if (i != p.getClusterNumber()) {

				averageInterClusterDistances.add(ClusterUtil.getIntraClusterDistance(p, clusters.get(i)));

			}
		}

		Collections.sort(averageInterClusterDistances);
		averageInterClusterDistance = averageInterClusterDistances.get(0);
		denominator = Math.max(averageIntraClusterDistance, averageInterClusterDistance);
		numerator = averageInterClusterDistance - averageIntraClusterDistance;

		return (numerator/denominator);
	}
	
	/** 
	 * Calculates the average silhouette score (i.e. average of all silhouette scores in the cluster)
	 * @param cluster Cluster object whose average silhouette score has to be calculated
	 * @param clusters List<Clusters> containing all the clusters obtained by the clustering algorithms
	 * @return Double containing the average silhouette score
	 */
	public static Double averageSilhouetteCoefficientCluster(Cluster cluster,List<Cluster> clusters) {  
		
		List<Point> points = cluster.getPoints();
		int clusterSize = points.size();
		double silhouette = 0.0;
		
		for (Point p : points) {  
			
			silhouette = silhouette + silhouetteCoefficient(p, clusters);
		}
		
		return silhouette/clusterSize;
	}
}
