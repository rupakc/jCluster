package org.jcluster.dao;

import java.util.List;

/** 
 * Density object which is the output of a density based clustering algorithm
 * @author Rupak Chakraborty
 * @since 21 May,2016
 */
public class Density {

	private List<Cluster> clusters;
	private List<Point> noisePoints;
	
	/**
	 * @return the clusters
	 */
	public List<Cluster> getClusters() {
		return clusters;
	}
	/**
	 * @param clusters the clusters to set
	 */
	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}
	/**
	 * @return the noisePoints
	 */
	public List<Point> getNoisePoints() {
		return noisePoints;
	}
	/**
	 * @param noisePoints the noisePoints to set
	 */
	public void setNoisePoints(List<Point> noisePoints) {
		this.noisePoints = noisePoints;
	}
}
