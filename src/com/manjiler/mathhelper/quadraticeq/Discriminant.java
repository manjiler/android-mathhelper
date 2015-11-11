/**
 * 
 */
package com.manjiler.mathhelper.quadraticeq;

/**
 * @author Manoj Srivatsav
 *
 */
public class Discriminant {
	private int D;
	private String natureOfRoots;
	
	public Discriminant() {
		
	}
	
	public Discriminant(int discriminant, String natureOfRoots) {
		this.D = discriminant;
		this.natureOfRoots = natureOfRoots;
	}
	
	public int getD() {
		return D;
	}
	public void setD(int d) {
		D = d;
	}
	public String getNatureOfRoots() {
		return natureOfRoots;
	}
	public void setNatureOfRoots(String natureOfRoots) {
		this.natureOfRoots = natureOfRoots;
	}
	
	
}
