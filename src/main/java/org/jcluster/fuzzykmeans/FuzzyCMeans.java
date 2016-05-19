package org.jcluster.fuzzykmeans;

import java.util.List;

import org.jcluster.dao.Center;
import org.jcluster.dao.Fuzzy;
import org.jcluster.dao.Point;

/** 
 * Implements the fuzzy c means algorithm
 * @author Rupak Chakraborty
 * @since 19 May,2016
 */
public class FuzzyCMeans {
	
	private int numOfClusters; 
	private double threshold = 0.01; 
	private int degree;
	
	/** 
	 * public constructor to initialize the clusters and degree with default values
	 */
	public FuzzyCMeans() { 
		
		this.numOfClusters = 2;
		this.degree = 2;
	}
	
	/** 
	 * public constructor to initialize the number of clusters and degree of membership
	 * @param numOfClusters Integer containing the number of clusters
	 * @param degree Integer containing the degree of membership
	 */
	public FuzzyCMeans(int numOfClusters,int degree) { 
		
		this.numOfClusters = numOfClusters;
		this.degree = degree;
	}
	
	/** 
	 * public constructor to initialize the number of clusters, degree and threshold
	 * @param numOfClusters Integer containing the number of clusters
	 * @param degree Integer containing the degree of membership
	 * @param threshold Double containing the threshold
	 */
	public FuzzyCMeans(int numOfClusters,int degree,double threshold) { 
		
		this.numOfClusters = numOfClusters;
		this.degree = degree;
		this.threshold = threshold;
	}
	
	/** 
	 * Given a list of points returns the membership matrix and list of cluster centers
	 * @param points List<Point> list of points to be clustered
	 * @return Fuzzy object containing the membership matrix and list of cluster centers
	 */
	public Fuzzy getFuzzyCMeans(List<Point> points) { 
		
		double membershipMatrix[][] = FuzzyCMeansUtil.getInitialMembershipMatrix(points.size(), this.numOfClusters);
		double previousObjective = 0.0;
		double presentObjective = 0.0;
		Fuzzy result = new Fuzzy();
		List<Center> centers = FuzzyCMeansUtil.getUpdatedCenters(points, membershipMatrix, this.degree);
		FuzzyCMeansUtil.updateMemberships(points, membershipMatrix, centers, this.degree);
		presentObjective = FuzzyCMeansUtil.getValueOfObjectiveFunction(points, membershipMatrix, centers, this.degree);
		
		while(Math.abs(presentObjective-previousObjective) > this.threshold) { 
			
			previousObjective = presentObjective;
			centers = FuzzyCMeansUtil.getUpdatedCenters(points,membershipMatrix,this.degree);
			FuzzyCMeansUtil.updateMemberships(points, membershipMatrix, centers, this.degree);
			presentObjective = FuzzyCMeansUtil.getValueOfObjectiveFunction(points,membershipMatrix,centers,this.degree);
		}
		
		result.setCenters(centers);
		result.setMembershipMatrix(membershipMatrix);
		
		return result;
	}
}