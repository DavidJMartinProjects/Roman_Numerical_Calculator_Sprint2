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

import businessLayer.Converter;
import businessLayer.api.CalculatorStrategy;
import domainLayer.CalculationResult;
import utils.Validator;

public abstract class RomanNumericalCalculator implements CalculatorStrategy {
	
	final Converter converter = new Converter();
	final Validator validator = new Validator();
	
	@Override
	public CalculationResult doCalculation(final String romanNumeral1, final String romanNumeral2) {		
		if(validateRomanNumerals(romanNumeral1, romanNumeral2) && validateCalculation(converter.toBaseTen(romanNumeral1), converter.toBaseTen(romanNumeral2))) {
			return calculate(converter.toBaseTen(romanNumeral1), converter.toBaseTen(romanNumeral2));			
		}		
		return null;		
	}
	
	public boolean validateRomanNumerals(final String numeral1, final String numeral2) {
		return (validator.validateRomanNumeral(numeral1) && validator.validateRomanNumeral(numeral2));
	}
	
	public abstract boolean validateCalculation(final int numeral1, final int numeral2);
	
	public abstract CalculationResult calculate(int num1, int num2);
}
