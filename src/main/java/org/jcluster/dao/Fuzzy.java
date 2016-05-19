package org.jcluster.dao;

import java.util.List;

/** 
 * Defines an object which forms the output of the 
 * @author Rupak Chakraborty
 * @since 19 May, 2016
 */
public class Fuzzy {
	
	private double [][] membershipMatrix;
	private List<Center> centers;
	
	/**
	 * @return the membershipMatrix
	 */
	public double[][] getMembershipMatrix() {
		return membershipMatrix;
	} 
	
	/**
	 * @param membershipMatrix the membershipMatrix to set
	 */
	public void setMembershipMatrix(double[][] membershipMatrix) {
		this.membershipMatrix = membershipMatrix;
	} 
	
	/**
	 * @return the centers
	 */
	public List<Center> getCenters() {
		return centers;
	} 
	
	/**
	 * @param centers the centers to set
	 */
	public void setCenters(List<Center> centers) {
		this.centers = centers;
	}
}
