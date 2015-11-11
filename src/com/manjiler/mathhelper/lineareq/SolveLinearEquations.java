/**
 * 
 */
package com.manjiler.mathhelper.lineareq;

import com.manjiler.mathhelper.common.Fraction;
import com.manjiler.mathhelper.utils.NumberUtils;

/**
 * @author Manoj Srivatsav
 *
 */
public class SolveLinearEquations {

	private LinearEquation linearEq1;
	private LinearEquation linearEq2;
	
	public SolveLinearEquations() {
		
	}

	public LinearEquation getLinearEq1() {
		return linearEq1;
	}

	public void setLinearEq1(LinearEquation linearEq1) {
		this.linearEq1 = linearEq1;
	}

	public LinearEquation getLinearEq2() {
		return linearEq2;
	}

	public void setLinearEq2(LinearEquation linearEq2) {
		this.linearEq2 = linearEq2;
	}
	
	public PointOfIntersection solveEquations() {
		// First check if either X coefficients or Y coefficients are same - that would be are starting point.
		PointOfIntersection pointOfIntersection = null;
		if(linearEq1.getxCoefficient() == linearEq2.getxCoefficient()) {
			//If it comes here it means the X coefficient are same and their signs.
			//Now subtract the 2 equations.
			pointOfIntersection = subtractEquations(BasedOn.X);
		} else if(Math.abs(linearEq1.getxCoefficient()) == Math.abs(linearEq2.getxCoefficient())) {
			//if it comes here it means that X coefficient are same but their sign are opposite.
			//Hence we can add the 2 equations.
			pointOfIntersection = addEquations(BasedOn.X);
		} else if(linearEq1.getyCoefficient() == linearEq2.getyCoefficient()) {
			pointOfIntersection = subtractEquations(BasedOn.Y);
		} else if(Math.abs(linearEq1.getyCoefficient()) == Math.abs(linearEq2.getyCoefficient())) {
			pointOfIntersection = addEquations(BasedOn.Y);
		} else if((linearEq1.getxCoefficient() > 0) && (linearEq2.getxCoefficient() > 0)) {
			int multiplyEq1By = linearEq2.getxCoefficient();
			int multiplyEq2By = linearEq1.getxCoefficient();
			linearEq1.mulitiplyBy(multiplyEq1By);
			linearEq2.mulitiplyBy(multiplyEq2By);
			pointOfIntersection = subtractEquations(BasedOn.X);
		} else if(((linearEq1.getxCoefficient() > 0) && (linearEq2.getxCoefficient() < 0)) ||
					((linearEq1.getxCoefficient() < 0) && (linearEq2.getxCoefficient() > 0))) {
			int multiplyEq1By = Math.abs(linearEq2.getxCoefficient());
			int multiplyEq2By = Math.abs(linearEq1.getxCoefficient());
			linearEq1.mulitiplyBy(multiplyEq1By);
			linearEq2.mulitiplyBy(multiplyEq2By);
			pointOfIntersection = addEquations(BasedOn.X);
		}
		
		return pointOfIntersection;
	}
	
	private PointOfIntersection subtractEquations(BasedOn basedOn) {
		
		PointOfIntersection pointOfIntersection = null;
		
		switch(basedOn) {
		
		case X:
			Fraction yFraction = new Fraction((linearEq1.getConstant() - linearEq2.getConstant()), (linearEq1.getyCoefficient() - linearEq2.getyCoefficient()));
			yFraction.reduceFraction();
			Fraction xFraction = linearEq1.substituteYValue(yFraction);
			xFraction.reduceFraction();
			pointOfIntersection = new PointOfIntersection(xFraction.toString(), yFraction.toString());			
			break;
			
		case Y:
			xFraction = new Fraction((linearEq1.getConstant() - linearEq2.getConstant()), (linearEq1.getxCoefficient() - linearEq2.getxCoefficient()));
			xFraction.reduceFraction();
			yFraction = linearEq1.substituteXValue(xFraction);
			yFraction.reduceFraction();
			pointOfIntersection = new PointOfIntersection(xFraction.toString(), yFraction.toString());
			break;
			
		default:
			break;		
		}		
		return pointOfIntersection;		
	}
	
	private PointOfIntersection addEquations(BasedOn basedOn) {
		PointOfIntersection pointOfIntersection = null;
		switch(basedOn) {
		case X:
			Fraction yFraction = new Fraction((linearEq1.getConstant() + linearEq2.getConstant()), (linearEq1.getyCoefficient() + linearEq2.getyCoefficient()));
			yFraction.reduceFraction();
			Fraction xFraction = linearEq1.substituteYValue(yFraction);
			xFraction.reduceFraction();
			pointOfIntersection = new PointOfIntersection(xFraction.toString(), yFraction.toString());
			break;
			
		case Y:
			xFraction = new Fraction((linearEq1.getConstant() + linearEq2.getConstant()), (linearEq1.getxCoefficient() + linearEq2.getxCoefficient()));
			xFraction.reduceFraction();
			yFraction = linearEq1.substituteXValue(xFraction);
			yFraction.reduceFraction();
			pointOfIntersection = new PointOfIntersection(xFraction.toString(), yFraction.toString());
			break;
			
		default:
			break;
		}
		return pointOfIntersection;
	}
}
