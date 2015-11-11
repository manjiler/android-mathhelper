/**
 * 
 */
package com.manjiler.mathhelper.quadraticeq;

import java.util.List;

/**
 * @author Manoj Srivatsav
 *
 */
public class RootsOfQuadraticEquation {
	
	private String firstRoot;
	private String secondRoot;
	private Discriminant discriminant;
	private List<String> steps;
	private boolean solvedByFactorization;
	
	public RootsOfQuadraticEquation() {
		
	}
	
	public RootsOfQuadraticEquation(Discriminant discriminant) {
		this.discriminant = discriminant;
	}

	public String getFirstRoot() {
		return firstRoot;
	}

	public void setFirstRoot(String firstRoot) {
		this.firstRoot = firstRoot;
	}

	public String getSecondRoot() {
		return secondRoot;
	}

	public void setSecondRoot(String secondRoot) {
		this.secondRoot = secondRoot;
	}

	public Discriminant getDiscriminant() {
		return discriminant;
	}

	public void setDiscriminant(Discriminant discriminant) {
		this.discriminant = discriminant;
	}

	public List<String> getSteps() {
		return steps;
	}

	public void setSteps(List<String> steps) {
		this.steps = steps;
	}

	public boolean isSolvedByFactorization() {
		return solvedByFactorization;
	}

	public void setSolvedByFactorization(boolean solvedByFactorization) {
		this.solvedByFactorization = solvedByFactorization;
	}	

}
