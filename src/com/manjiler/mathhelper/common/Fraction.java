/**
 * 
 */
package com.manjiler.mathhelper.common;

import com.manjiler.mathhelper.utils.NumberUtils;

/**
 * @author Manoj Srivatsav
 *
 * 
 */
public class Fraction {

	private int numerator;
	private int denomenator;
	
	public Fraction(int numerator, int denomenator) {
		this.numerator = numerator;
		this.denomenator = denomenator;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public int getDenomenator() {
		return denomenator;
	}

	public void setDenomenator(int denomenator) {
		this.denomenator = denomenator;
	}
	
	public Fraction multiplyBy(int multiplyBy) {
		return new Fraction(this.numerator * multiplyBy, this.denomenator);
	}
	
	public Fraction divideBy(int divideBy) {
		return new Fraction(this.numerator, this.denomenator * divideBy);
	}
	
	public Fraction subractFrom(Fraction f) {
		Fraction fraction = null;
		int newNumerator = (this.numerator * f.getDenomenator()) - (f.getNumerator() * this.denomenator);
		int newDenomenator = (this.denomenator * f.getDenomenator());
		fraction = new Fraction(newNumerator, newDenomenator);
		return fraction;
	}
	
	public Fraction subractBy(Fraction f) {
		Fraction fraction = null;
		int newNumerator = (f.getNumerator() * this.denomenator) - (this.numerator * f.getDenomenator());
		int newDenomenator = (f.getDenomenator() * this.denomenator);
		fraction = new Fraction(newNumerator, newDenomenator);
		return fraction;
	}
	
	public void reduceFraction() {
		int min = min();
		for(int i = 2; i <= min; i++) {
			if((Math.abs(this.numerator)%i == 0) && (Math.abs(this.denomenator)%i == 0)) {
				this.numerator = this.numerator/i;
				this.denomenator = this.denomenator/i;
				reduceFraction();
				break;
			}
		}
	}
	
	private int min()
	{
		if(Math.abs(this.numerator) < Math.abs(denomenator)) {
			return Math.abs(numerator);
		} else {
			return Math.abs(denomenator);
		}
	}
	
	@Override
	public String toString() {
		if(NumberUtils.isWholeNumber((double)this.numerator/this.denomenator)) {
			return Double.toString((double)this.numerator/this.denomenator);
		} else {
			if((this.numerator < 0) && (this.denomenator < 0)) {
				return Math.abs(this.numerator) + "/" + Math.abs(this.denomenator); 
			} else if((this.numerator > 0) && (this.denomenator > 0)) {
				return Math.abs(this.numerator) + "/" + Math.abs(this.denomenator);
			} else if((this.numerator < 0) && (this.denomenator > 0)) {
				return "-" + Math.abs(this.numerator) + "/" + Math.abs(this.denomenator);
			} else if((this.numerator > 0) && (this.denomenator < 0)) {
				return "-" + Math.abs(this.numerator) + "/" + Math.abs(this.denomenator);
			} else {
				return numerator + "/" + denomenator;
			}
			
		}
	}
	
}
