package org.jcluster.kmeansplusplus;

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
 * @since 17 May, 2016
 */
public class KMeansPlusPlusUtil {

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
	 * 
	 * @param points
	 * @param numOfClusters
	 * @return
	 */
	public static List<Center> getInitialCenters(List<Point> points,int numOfClusters) {  
		
		Random random = new Random(System.currentTimeMillis());
		int clusterIndex;
		List<Center> centers = new ArrayList<Center>();
		clusterIndex = random.nextInt(points.size());
		points.get(clusterIndex).setCenter(true);
		Center center = new Center();
		center.setPoint(points.get(clusterIndex));
		centers.add(center); 
		
		while(centers.size() != numOfClusters) { 
			
			List<Double> distancesFromNearestCenter = new ArrayList<Double>(); 
			
			for(int i = 0; i < points.size(); i++) { 
				
				distancesFromNearestCenter.add(Math.pow(getDistanceToNearestCenter(points.get(i), centers),2.0));
			}
			
			clusterIndex = getIndexOfCenter(distancesFromNearestCenter, points);
			Center tempCenter = new Center();
			tempCenter.setPoint(points.get(clusterIndex));
			centers.add(tempCenter);
		}
		
		return centers;
	}
	
	/** 
	 * 
	 * @param p
	 * @param centers
	 * @return
	 */
	public static Double getDistanceToNearestCenter(Point p,List<Center> centers) { 

		double minDistance;
		double tempDistance;

		minDistance = DistanceUtil.euclideanDistance(p, centers.get(0).getPoint());

		for (Center center : centers) { 

			tempDistance = DistanceUtil.euclideanDistance(p, center.getPoint()); 

			if (tempDistance < minDistance) {  

				minDistance = tempDistance;
			}
		}

		return minDistance;
	}
	
	/** 
	 * 
	 * @param distancesFromNearestCenter
	 * @param points
	 * @return
	 */
	public static int getIndexOfCenter(List<Double> distancesFromNearestCenter,List<Point> points) { 

		double sum = 0.0; 
		double percentProbability; 
		List<Integer> centerIndices = new ArrayList<Integer>(); 
		Random random = new Random(System.currentTimeMillis()); 
		int randomIndex; 

		for (int i = 0; i < distancesFromNearestCenter.size(); i++) {  

			sum = sum + distancesFromNearestCenter.get(i);
		}

		for (int i = 0; i < distancesFromNearestCenter.size(); i++) { 

			percentProbability = Math.ceil((distancesFromNearestCenter.get(i)/sum)*100.0);
			distancesFromNearestCenter.set(i, percentProbability);
		}

		for (int i = 0; i < distancesFromNearestCenter.size(); i++) { 

			for (int j = 1; j <= distancesFromNearestCenter.get(i); j++) { 

				centerIndices.add((Integer)i);
			}
		}

		while(true) {
			randomIndex = random.nextInt(centerIndices.size());
			if (!points.get(randomIndex).isCenter()) {
				points.get(randomIndex).setCenter(true);
				break;
			}
		}

		return randomIndex;
	}
}