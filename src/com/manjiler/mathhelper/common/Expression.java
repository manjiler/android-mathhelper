/**
 * 
 */
package com.manjiler.mathhelper.common;

import com.manjiler.mathhelper.utils.NumberUtils;

/**
 * A class model around aX + b
 * this can get multiplied by either a number, variable or both like
 * 2(aX + b) , X(aX + b) or 2X(aX + b)
 * @author Manoj Srivatsav
 *
 */
public class Expression {
	private int coefficient;
	private int constant;
	private String variable;
	private String multiplyBy;
	
	public Expression() {
		
	}
	
	public String getMultiplyBy() {
		return multiplyBy;		
	}

	public int getCoefficient() {
		return coefficient;
	}
	
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	
	public int getConstant() {
		return constant;
	}
	
	public void setConstant(int powerOfVariable) {
		this.constant = powerOfVariable;
	}
	
	public String getVariable() {
		return variable;
	}
	
	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	public void multiply(int number, String variable, boolean simplify) {
		this.multiplyBy = number + variable;
		if(simplify) {
			simplifyExpression(number, variable);
		}
	}
	
	private void simplifyExpression(int number, String variable) {
		
	}
	
	public boolean compareExpressionWithoutMultiply(Expression exp) {
		if((this.coefficient == exp.coefficient) && 
				(this.constant == exp.constant) &&
				(this.variable == exp.variable)) {
			return true;
		} else {
			return false;
		}		
	}
	
	public void pullMinusSignOut() {
		if(this.multiplyBy.startsWith("-")) {
			this.multiplyBy = this.multiplyBy.replace("-", "");
		} else {
			this.multiplyBy = "-" + this.multiplyBy;
		}
		this.coefficient = this.coefficient * -1;
		this.constant = this.constant * -1;		
	}
	
	public String getCoreExpression() {
		StringBuilder coreExpression = new StringBuilder();
		if(coefficient == 1) {
			coreExpression.append(variable);
		} else {
			coreExpression.append(coefficient + variable);
		}		
		coreExpression.append(NumberUtils.getSignAndValue(constant));
		return coreExpression.toString();
	}
	
	@Override
	public String toString() {
		//return Html.fromHtml(this.coefficient + this.variable +"<sup><small>" + this.constant + "</sup></small>").toString();
		StringBuilder toStringBuilder = new StringBuilder();
		if(multiplyBy != null) {
			toStringBuilder.append(multiplyBy + "(");
		}
		
		if(coefficient == 1) {
			toStringBuilder.append(variable);
		} else {
			toStringBuilder.append(coefficient + variable);
		}		
		toStringBuilder.append(NumberUtils.getSignAndValue(constant));
		if(multiplyBy != null) {
			toStringBuilder.append(")");
		}
		return toStringBuilder.toString();
	}	
	
}
