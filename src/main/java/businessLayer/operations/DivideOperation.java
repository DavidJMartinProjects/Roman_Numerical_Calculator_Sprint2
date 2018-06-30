/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package businessLayer.operations;

import businessLayer.api.CalculatorStrategy;
import domainLayer.CalculationResult;

/**
 * @author eaamrvd
 *
 */	

public class DivideOperation extends RomanNumericalCalculator implements CalculatorStrategy  {

	@Override
	public boolean validateCalculation(final int numeral1, final int numeral2) {		
		return true;	
	}

	@Override	
	public CalculationResult calculate(final int num1, final int num2) {		
		return converter.toRomanNumeral(num1 / num2);		
	}


}
