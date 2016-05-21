package org.jcluster.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jcluster.dao.Point;

/** 
 * Defines a set of utlity functions for reading files
 * @author Rupak Chakraborty
 * @since 23 April, 2016
 */
public class FileUtils {
	
	/** 
	 * Given a CSV file containing the data points returns a 2-D array of all the training samples
	 * @param filename String containing the name of the csv file
	 * @return Double array containing the datapoints
	 * @throws IOException
	 */
	public static double[][] getDataSet(String filename) throws IOException { 
		
		double dataArray[][] = null;
		FileReader fr;
		BufferedReader br;
		List<String> dataPoints ;
		String temp;
		String tempArray[]; 
		int dimension;
		
		fr = new FileReader(filename);
		br = new BufferedReader(fr);
	
		dataPoints = new ArrayList<String>();
		temp = "";
		
		while((temp = br.readLine()) != null) { 
			
			dataPoints.add(temp);
		}
		
		dimension = dataPoints.get(0).split(",").length;
		dataArray = new double[dataPoints.size()][dimension];
		
		for (int i = 0; i < dataPoints.size(); i++) { 
			
			temp = dataPoints.get(i);
			tempArray = temp.split(",");
			
			for (int j = 0; j < tempArray.length; j++) { 
				
				dataArray[i][j] = Double.parseDouble(tempArray[j]);
			}
		}
		
		fr.close();
		br.close();
		
		return dataArray;
	}
	
	/** 
	 * Given a filename in a csv format returns a list of point objects from it
	 * @param filename String containing the filename containing the data points
	 * @return List<Point> containing the point objects
	 * @throws IOException
	 */
	public static List<Point> getPointList(String filename) throws IOException { 
		
		double [][] dataPoints = getDataSet(filename);
		List<Double> pointValues;
		int dimension = dataPoints[0].length;
		List<Point> points = new ArrayList<Point>(); 
		Point tempPoint; 
		
		for (int i = 0; i < dataPoints.length; i++) {  
			
			pointValues = new ArrayList<Double>();
			tempPoint = new Point();
			
			for (int j = 0; j < dataPoints[i].length; j++) { 
				
				pointValues.add(dataPoints[i][j]);
			}
			
			tempPoint.setValues(pointValues);
			tempPoint.setDimension(dimension);
			tempPoint.setIndexNumber(i);
			
			points.add(tempPoint);
		}
		
		return points;
	}
}
