package com.manjiler.mathhelper.lineareq;

import java.util.StringTokenizer;

import com.manjiler.mathhelper.common.Fraction;
import com.manjiler.mathhelper.common.StringUtils;

/**
 * Linear Equation of degree one and one variable. 
 * like 2X + 1 = 0;
 * @author Manoj Srivatsav
 *
 */
public class SingleVaraibleLinearEquation {
	private int coefficentX;
	private int constant;
	
	public SingleVaraibleLinearEquation(int coefficientX, int constant) {
		this.coefficentX = coefficientX;
		this.constant = constant;
	}
	
	private static int convertToInt(String numberWithSign) {
		if(numberWithSign.matches("[+-][0-9]*")) {
			int returnValue = Integer.parseInt(numberWithSign.substring(1, numberWithSign.length()));
			if(numberWithSign.charAt(0) == '+') {
				returnValue = +returnValue;
			} else if(numberWithSign.charAt(0) == '-') {
				returnValue = -returnValue;
			}
			return returnValue;
		} else if(numberWithSign.matches("[0-9]*")){
			return Integer.parseInt(numberWithSign);
		} else {
			return 0;
		}
	}
	
	public SingleVaraibleLinearEquation(String linearEquation) {
		linearEquation = linearEquation.replaceAll("\\s", "");
		if(linearEquation.startsWith("X")) {
			linearEquation = "1" + linearEquation;
		} else if(linearEquation.startsWith("-X")) {
			linearEquation = linearEquation.replaceFirst("-", "-1");
		}
		StringTokenizer tokenizer = new StringTokenizer(linearEquation, "X");
		tokenizer.hasMoreTokens();
		this.coefficentX = convertToInt(tokenizer.nextToken());
		tokenizer.hasMoreTokens();
		StringTokenizer tokenizer1 = new StringTokenizer(tokenizer.nextToken(), "=");
		tokenizer1.hasMoreTokens();
		this.constant = convertToInt(tokenizer1.nextToken());
		if(tokenizer1.hasMoreElements()) {
			int equals = convertToInt(tokenizer1.nextToken());
			this.constant += -equals;
		}		
	}
	
	public Fraction solveEquation() {
		// After reducing the equation to the for aX + b = 0; x = -b/a 
		Fraction fraction = new Fraction(-this.constant, this.coefficentX);
		fraction.reduceFraction();
		return fraction;
	}
	
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(this.coefficentX + StringUtils.getX() + " ");
		if(this.constant > 0) {
			toStringBuilder.append("+ " + this.constant + " ");
		} else {
			toStringBuilder.append(this.constant + " ");
		}

		toStringBuilder.append("= 0");
		return toStringBuilder.toString();
	}
}
