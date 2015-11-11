/**
 * 
 */
package com.manjiler.mathhelper.lineareq;

/**
 * @author Manoj Srivatsav
 *
 */
public class PointOfIntersection {

	private String xValue;
	private String yValue;
	
	public PointOfIntersection(String xValue, String yValue) {
		this.xValue = xValue;
		this.yValue = yValue;
	}
	
	public PointOfIntersection() {
		
	}
	
	public String getxValue() {
		return xValue;
	}
	public void setxValue(String xValue) {
		this.xValue = xValue;
	}
	public String getyValue() {
		return yValue;
	}
	public void setyValue(String yValue) {
		this.yValue = yValue;
	}
	
	@Override
	public String toString() {
		return "( " + xValue + ", " + yValue + " )"; 
	}
	
}
