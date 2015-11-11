/**
 * 
 */
package com.manjiler.mathhelper.common;

/**
 * @author Manoj Srivatsav
 *
 */
public class ComplexFractionNumber {

	private Fraction realFraction;
	private Fraction imaginaryFraction;
	
	public ComplexFractionNumber() {
		
	}
	
	public ComplexFractionNumber(Fraction realFraction, Fraction imaginaryFraction) {
		this.realFraction = realFraction;
		this.imaginaryFraction = imaginaryFraction;
	}
	
	public Fraction getRealFraction() {
		return realFraction;
	}
	
	public void setRealFraction(Fraction realFraction) {
		this.realFraction = realFraction;
	}
	
	public Fraction getImaginaryFraction() {
		return imaginaryFraction;
	}
	
	public void setImaginaryFraction(Fraction imaginaryFraction) {
		this.imaginaryFraction = imaginaryFraction;
	}
	
	@Override
	public String toString() {
		realFraction.reduceFraction();
		imaginaryFraction.reduceFraction();
		if(imaginaryFraction.toString().startsWith("-")) {
			return realFraction.toString() + " -i " + imaginaryFraction.toString().substring(1, imaginaryFraction.toString().length());
		} else {
			return realFraction.toString() + " +i " + imaginaryFraction.toString();
		}		
	}
	
}
