/**
 * 
 */
package com.manjiler.mathhelper.lineareq;

import com.manjiler.mathhelper.common.Fraction;

/**
 * @author Manoj Srivatsav
 *
 */
public class LinearEquation {
	
	private int xCoefficient;
	private int yCoefficient;
	private int constant;
	
	public LinearEquation(int xCoefficient, int yCoefficient, int constant) {
		this.xCoefficient = xCoefficient;
		this.yCoefficient = yCoefficient;
		this.constant = constant;
	}

	public int getxCoefficient() {
		return xCoefficient;
	}

	public void setxCoefficient(int xCoefficient) {
		this.xCoefficient = xCoefficient;
	}

	public int getyCoefficient() {
		return yCoefficient;
	}

	public void setyCoefficient(int yCoefficient) {
		this.yCoefficient = yCoefficient;
	}

	public int getConstant() {
		return constant;
	}

	public void setConstant(int constant) {
		this.constant = constant;
	}
	
	public void mulitiplyBy(int multiplyBy) {
		this.xCoefficient = this.xCoefficient * multiplyBy;
		this.yCoefficient = this.yCoefficient * multiplyBy;
		this.constant = this.constant * multiplyBy;
	}
	
	/**
	 * 
	 * @param xValue
	 * @return the Y value after substituting the X value 
	 */
	public double substituteXValue(double xValue) {
		double yValue = ((double)(this.constant - (this.xCoefficient * xValue)))/ this.yCoefficient;
		return yValue;
	}
	
	public Fraction substituteXValue(Fraction xFraction) {
		Fraction returnValue;
		Fraction newXFraction = xFraction.multiplyBy(this.xCoefficient);
		Fraction cFraction = new Fraction(this.constant, 1);
		Fraction yFraction = cFraction.subractFrom(newXFraction);
		returnValue = yFraction.divideBy(this.yCoefficient);
		return returnValue;
	}
	
	/**
	 * 
	 * @param yValue
	 * @return the X value after substituting the Y value
	 */
	public double substituteYValue(double yValue) {
		double xValue = ((double)(this.constant - (this.yCoefficient * yValue)))/ this.xCoefficient;
		return xValue;
	}
	
	public Fraction substituteYValue(Fraction yFraction) {
		Fraction xFraction = null;
		Fraction newyFraction = yFraction.multiplyBy(this.yCoefficient);
		Fraction cFraction = new Fraction(this.constant, 1);
		xFraction = cFraction.subractFrom(newyFraction);
		Fraction newxFraction = xFraction.divideBy(this.xCoefficient);
		return newxFraction;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		if(this.xCoefficient > 0) {
			stringBuilder.append(this.xCoefficient + "X ");
		} else {
			stringBuilder.append("-" + Math.abs(this.xCoefficient) + "X ");
		}
		
		if(this.yCoefficient > 0) {
			stringBuilder.append("+ " + this.yCoefficient + "Y ");
		} else {
			stringBuilder.append("- " + Math.abs(this.yCoefficient) + "Y ");		
		}
		
		stringBuilder.append("= ");
		
		if(this.constant > 0) {
			stringBuilder.append(this.constant);			
		} else {
			stringBuilder.append("-" + this.constant);
		}
		return stringBuilder.toString();
	}

}
