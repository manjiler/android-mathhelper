/**
 * 
 */
package com.manjiler.mathhelper.quadraticeq;

import java.util.ArrayList;
import java.util.List;

import android.text.Html;
import android.util.Log;

import com.manjiler.mathhelper.common.ComplexFractionNumber;
import com.manjiler.mathhelper.common.Expression;
import com.manjiler.mathhelper.common.Fraction;
import com.manjiler.mathhelper.common.StringUtils;
import com.manjiler.mathhelper.lineareq.SingleVaraibleLinearEquation;
import com.manjiler.mathhelper.utils.NumberUtils;

/**
 * @author Manoj Srivatsav
 *
 * This class will solve the quadratic equation and give it roots. 
 * It will also analyze whether the root are real, positive and imaginary.
 * It will also handle the part of representing the roots properly. like
 * x^2 - 5x + 6 = 0   ----> (x-3)(x-2)
 *                    ----> (1 + sqrt(3))/2 and (1 - sqrt(3))/2 
 *
 * Discriminant (D) is given by the formula b^2 - 4*a*c
 * Roots of the equation are given by the formula (-b +- sqrt(b^2 - 4*a*c))/(2*a) 
 *
 */
public class QuadraticEquation {
	
	private int coefficientA;
	private int coefficientB;
	private int coefficientC;
	
	private static final String PLUS_MINUS_SYMBOL = "\u00B1";
	private static final String SQUARE_ROOT_SYMBOL = "\u221A";	
	
	public static final String FORMULA = "(-b " + PLUS_MINUS_SYMBOL + " " + SQUARE_ROOT_SYMBOL + "(" + 
											Html.fromHtml("b<sup><small>2</small></sup>") + " - " + "4ac)) / 2a";
	private static final String tag = QuadraticEquation.class.getSimpleName();													
			
	public QuadraticEquation(int coefficientA, int coefficientB, int coefficientC) {
		this.coefficientA = coefficientA;
		this.coefficientB = coefficientB;
		this.coefficientC = coefficientC;
	}

	public int getCoefficientA() {
		return coefficientA;
	}

	public void setCoefficientA(int coefficientA) {
		this.coefficientA = coefficientA;
	}

	public int getCoefficientB() {
		return coefficientB;
	}

	public void setCoefficientB(int coefficientB) {
		this.coefficientB = coefficientB;
	}

	public int getCoefficientC() {
		return coefficientC;
	}

	public void setCoefficientC(int coefficientC) {
		this.coefficientC = coefficientC;
	}
	
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder();
		toStringBuilder.append(this.coefficientA + StringUtils.getXSquare() + " ");
		if(this.coefficientB > 0) {
			toStringBuilder.append("+" + this.coefficientB + "X" + " ");
		} else {
			toStringBuilder.append(this.coefficientB + "X" + " ");
		}
		if(this.coefficientC > 0) {
			toStringBuilder.append("+" + this.coefficientC);
		} else {
			toStringBuilder.append(this.coefficientC);
		}
		
		return toStringBuilder.toString();
	}

	private Discriminant calculateDiscriminant() {
		int d = (this.coefficientB * this.coefficientB) - (4 * coefficientA * coefficientC);
		Discriminant discriminant = new Discriminant();
		discriminant.setD(d);
		if(d > 0) {
			if(NumberUtils.isPerfectSquare(d)) {
				discriminant.setNatureOfRoots("Roots are distinct, real and rational");
			} else {
				discriminant.setNatureOfRoots("Roots are distinct, real and irrational");
			}
		} else if(d == 0) {
			discriminant.setNatureOfRoots("There is exactly one real root give by -b/2a");
		} else if(d < 0) {
			discriminant.setNatureOfRoots("Roots are distinct and imaginary");
		}
		return discriminant;
	}
	
	public RootsOfQuadraticEquation getRootsOfEquationByFormula() {
		Discriminant discriminant = calculateDiscriminant();
		RootsOfQuadraticEquation roots = null;
		int d = discriminant.getD();
		if(this.coefficientA != 0) {
			roots = new RootsOfQuadraticEquation(discriminant);
			if(d == 0) {
				// there is exactly one real root.
				double firstRoot = -(coefficientB)/(2 * coefficientA);
				roots.setFirstRoot(Double.toString(firstRoot));
			} else if(d > 0) {				
				if(NumberUtils.isPerfectSquare(d)) {
					double firstRoot = ((-coefficientB) + Math.sqrt(d))/(2 * coefficientA);
					double secondRoot = ((-coefficientB) - Math.sqrt(d))/(2 * coefficientA);
					
					if(!NumberUtils.isWholeNumber(firstRoot)) {
						Fraction firstRootFraction = new Fraction(((-coefficientB) + (int)Math.sqrt(d)), (2 * coefficientA));
						firstRootFraction.reduceFraction();
						roots.setFirstRoot(firstRootFraction.toString());
					} else {
						roots.setFirstRoot(Double.toString(firstRoot));
					}
					
					if(!NumberUtils.isWholeNumber(secondRoot)) {
						Fraction secondRootFraction = new Fraction(((-coefficientB) - (int)Math.sqrt(d)), (2 * coefficientA));
						secondRootFraction.reduceFraction();
						roots.setSecondRoot(secondRootFraction.toString());
					} else {
						roots.setSecondRoot(Double.toString(secondRoot));						
					}
				} else {
					List<Integer> perfectSquares = new ArrayList<Integer>();
					NumberUtils.representInPerfectSquare(perfectSquares, d);
					StringBuilder firstRootBuilder = new StringBuilder();
					StringBuilder secondRootBuilder = new StringBuilder();
					
					firstRootBuilder.append("( " + -coefficientB);
					secondRootBuilder.append("( " + -coefficientB);
					
					if(perfectSquares.size() == 1) {
						// the value of discriminant cannot be split into perfect squares.
						firstRootBuilder.append(" + " + SQUARE_ROOT_SYMBOL + perfectSquares.get(0) + ") / " + (2 * coefficientA));
						secondRootBuilder.append(" - " + SQUARE_ROOT_SYMBOL + perfectSquares.get(0) + ") / " + (2 * coefficientA));
					} else {
						int prefix = 1;
						String rootForm = "";
						for(int index = 0; index < perfectSquares.size(); index++) {
							if(index != (perfectSquares.size() -1)) {
								prefix = prefix * (int)Math.sqrt(perfectSquares.get(index));
							} else {
								rootForm = prefix + SQUARE_ROOT_SYMBOL + perfectSquares.get(index);
							}							
						}
						firstRootBuilder.append(" + " + rootForm + ") / " + (2 * coefficientA));
						secondRootBuilder.append(" - " + rootForm + ") / " + (2 * coefficientA));
					}
				
					roots.setFirstRoot(firstRootBuilder.toString());
					roots.setSecondRoot(secondRootBuilder.toString());					
				}
			} else {
				int newD = Math.abs(d);
				if(NumberUtils.isPerfectSquare(newD)) {
					Fraction firstRealFraction = new Fraction(-coefficientB, (2 * coefficientA));
					Fraction firstImaginaryFraction = new Fraction((int)Math.sqrt(newD), (2 * coefficientA));
					Fraction secondImaginaryFraction = new Fraction(-(int)Math.sqrt(newD), (2 * coefficientA));
					ComplexFractionNumber firstComplexFraction = new ComplexFractionNumber(firstRealFraction, firstImaginaryFraction);
					ComplexFractionNumber secondComplexFraction = new ComplexFractionNumber(firstRealFraction, secondImaginaryFraction);
					
					roots.setFirstRoot(firstComplexFraction.toString());
					roots.setSecondRoot(secondComplexFraction.toString());					
				} else {
					List<Integer> perfectSquares = new ArrayList<Integer>();
					NumberUtils.representInPerfectSquare(perfectSquares, newD);
					StringBuilder firstRootBuilder = new StringBuilder();
					StringBuilder secondRootBuilder = new StringBuilder();
					
					firstRootBuilder.append("( " + -coefficientB);
					secondRootBuilder.append("( " + -coefficientB);
					
					if(perfectSquares.size() == 1) {
						// the value of discriminant cannot be split into perfect squares.
						firstRootBuilder.append(" + i" + SQUARE_ROOT_SYMBOL + perfectSquares.get(0) + ") / " + (2 * coefficientA));
						secondRootBuilder.append(" - i" + SQUARE_ROOT_SYMBOL + perfectSquares.get(0) + ") / " + (2 * coefficientA));
					} else {
						int prefix = 1;
						String rootForm = "";
						for(int index = 0; index < perfectSquares.size(); index++) {
							if(index != (perfectSquares.size() -1)) {
								prefix = prefix * (int)Math.sqrt(perfectSquares.get(index));
							} else {
								rootForm = prefix + SQUARE_ROOT_SYMBOL + perfectSquares.get(index);
							}							
						}
						firstRootBuilder.append(" + i" + rootForm + ") / " + (2 * coefficientA));
						secondRootBuilder.append(" - i" + rootForm + ") / " + (2 * coefficientA));
					}
					roots.setFirstRoot(firstRootBuilder.toString());
					roots.setSecondRoot(secondRootBuilder.toString());
				}
			}
			
		}
		roots.setSolvedByFactorization(false);
		return roots;
	}
	
	public RootsOfQuadraticEquation getRootsOfEquationByFactorization() {
		RootsOfQuadraticEquation roots = new RootsOfQuadraticEquation();
		List<String> steps = new ArrayList<String>();
		List<Integer> factors = getFactors();
		if(factors != null) {
			steps.add(this.toString());
			//Check for b1,b2 before blindly pass them through.
			FactoredQuadraticEquation factoredEq = null;
			if((factors.get(0).intValue() % coefficientA == 0) ||
					(coefficientA % factors.get(0).intValue() == 0)) {
				factoredEq = new FactoredQuadraticEquation(coefficientA, factors.get(0).intValue(), 
						factors.get(1).intValue(), coefficientC);
			} else if((factors.get(1).intValue() % coefficientA == 0) || 
					(coefficientA % factors.get(1).intValue() == 0)) {
				factoredEq = new FactoredQuadraticEquation(coefficientA, factors.get(1).intValue(), 
						factors.get(0).intValue(), coefficientC);
			} 
			
			steps.add(factoredEq.toString());
			Expression exp1 = new Expression();
			Expression exp2 = new Expression();
			if(factoredEq.getB1() % this.coefficientA == 0) {				
				exp1.setCoefficient(1);
				exp1.setVariable(StringUtils.getX());
				exp1.setConstant(factoredEq.b1 / this.coefficientA);
				exp1.multiply(coefficientA, StringUtils.getX(), false);				
			} else if (this.coefficientA % factoredEq.getB1() == 0) {
				exp1.setCoefficient(this.coefficientA / factoredEq.b1);
				exp1.setVariable(StringUtils.getX());
				exp1.setConstant(1);
				exp1.multiply(factoredEq.getB1(), StringUtils.getX(), false);
			}
			
			if(factoredEq.getB2() % this.coefficientC == 0) {
				exp2.setVariable(StringUtils.getX());
				exp2.setCoefficient(factoredEq.getB2()/this.coefficientC);
				exp2.setConstant(1);
				exp2.multiply(this.coefficientC, "", false);
			} else if(this.coefficientC % factoredEq.getB2() == 0) {
				exp2.setVariable(StringUtils.getX());
				exp2.setCoefficient(1);
				exp2.setConstant(this.coefficientC/factoredEq.getB2());
				exp2.multiply(factoredEq.getB2(), "", false);
			}			
						
			if(exp2.toString().startsWith("-")) {
				steps.add(exp1.toString() + exp2.toString());
			} else {
				steps.add(exp1.toString() + "+" + exp2.toString());
			}			
			
			if(exp1.compareExpressionWithoutMultiply(exp2)) {//(firstCommon.equalsIgnoreCase(secondCommon)) {
				String factor1 = exp1.getCoreExpression();
				String factor2 = "";
				
				if(exp2.getMultiplyBy().startsWith("-")) {
					factor2 = exp1.getMultiplyBy() + exp2.getMultiplyBy();
				} else {
					factor2 = exp1.getMultiplyBy() + " + " + exp2.getMultiplyBy();
				}
				
				steps.add("(" + factor1 + ")" + "(" + factor2 + ")");
				// Now to find the roots. we have to evaluate the equation. 
				SingleVaraibleLinearEquation firstRoot = new SingleVaraibleLinearEquation(factor1);
				SingleVaraibleLinearEquation secondRoot = new SingleVaraibleLinearEquation(factor2);
				
				roots.setFirstRoot(firstRoot.solveEquation().toString());
				roots.setSecondRoot(secondRoot.solveEquation().toString());				
			} else {
				exp2.pullMinusSignOut();
				if(exp1.compareExpressionWithoutMultiply(exp2)) {
					String factor1 = exp1.getCoreExpression();
					String factor2 = "";
					
					if(exp2.getMultiplyBy().startsWith("-")) {
						factor2 = exp1.getMultiplyBy() + exp2.getMultiplyBy();
					} else {
						factor2 = exp1.getMultiplyBy() + " + " + exp2.getMultiplyBy();
					}
					
					steps.add("(" + factor1 + ")" + "(" + factor2 + ")");
					// Now to find the roots. we have to evaluate the equation. 
					SingleVaraibleLinearEquation firstRoot = new SingleVaraibleLinearEquation(factor1);
					SingleVaraibleLinearEquation secondRoot = new SingleVaraibleLinearEquation(factor2);
					
					roots.setFirstRoot(firstRoot.solveEquation().toString());
					roots.setSecondRoot(secondRoot.solveEquation().toString());					
				} else {
					Log.e(tag, "Something went wrong because there was nothing common between the 2 sub expressions! Exp1 = " + exp1 + ", Exp2 = " + exp2);
				}				
			}
			steps.add(0, "Solving the Equation by Factorization");
			roots.setSolvedByFactorization(true);
		} else {
			Log.w(tag, "Equation cannot be factored. A proper message should be shown in UI.");
			steps.add(0, "Equation cannot be factorized, solving by applying the formula");
			roots = getRootsOfEquationByFormula();			
		}
		
		roots.setSteps(steps);
		return roots;
	}
	
	public boolean isValidQuadraticEquation() {
		if(this.coefficientA != 0) {
			return true;
		} else {
			return false;
		}			
	}
	
	private List<Integer> getFactors() {
		List<Integer> factors = null;
		int limit = Math.abs(this.coefficientA * this.coefficientC); ///2;
		int j = 0;
		for(int i = -limit; i <= limit; i++) {
			//make another loop which will start from i++ and end at limit.
			for(j = i/* + 1*/; j <= limit; j++) {		// comment becuase it was not working for perfect square like x^2 + 2x + 1 -> (x + 1)^2				
				if(((this.coefficientA * this.coefficientC) == (i * j)) &&
						((i + j) == this.coefficientB)) {
					factors = new ArrayList<Integer>();
					factors.add(Integer.valueOf(i));
					factors.add(Integer.valueOf(j));
					return factors;
				}
			}
		}
		return factors;
	}
	
	private class FactoredQuadraticEquation {
		private int a;
		private int b1;
		private int b2;
		private int c;
		
		public FactoredQuadraticEquation(int a, int b1, int b2, int c) {
			super();
			this.a = a;
			this.b1 = b1;
			this.b2 = b2;
			this.c = c;
		}
		
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getB1() {
			return b1;
		}
		public void setB1(int b1) {
			this.b1 = b1;
		}
		public int getB2() {
			return b2;
		}
		public void setB2(int b2) {
			this.b2 = b2;
		}
		public int getC() {
			return c;
		}
		public void setC(int c) {
			this.c = c;
		}
		
		public String toString() {
			StringBuilder toStringBuilder = new StringBuilder();
			toStringBuilder.append(this.a + StringUtils.getXSquare());
			if(this.b1 > 0) {
				toStringBuilder.append("+" + this.b1 + "X");
			} else {
				toStringBuilder.append(this.b1 + "X");
			}
			
			if(this.b2 > 0) {
				toStringBuilder.append("+ " + this.b2 + "X");
			} else {
				toStringBuilder.append(this.b2 + "X");
			}
			
			if(this.c > 0) {
				toStringBuilder.append("+" + this.c);
			} else {
				toStringBuilder.append(this.c);
			}		
			
			return toStringBuilder.toString();
		}		
		
	}
}
