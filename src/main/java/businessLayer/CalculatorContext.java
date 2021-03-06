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
package businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import businessLayer.api.CalculatorStrategy;
import domainLayer.CalculationResult;

/**
 * @author eaamrvd
 *
 */
@Component
public class CalculatorContext {
	
	@Autowired
	CalculatorStrategy strategy;	
			
	public CalculatorContext(final CalculatorStrategy anOperation){
	    this.strategy = anOperation;
	}	
	
	public CalculatorStrategy getOperation() {
		return strategy;
	}	

	public void setOperation(final CalculatorStrategy operation) {
		this.strategy = operation;
	}

	public CalculationResult executeOperation(final String num1, final String num2) {
		return strategy.doCalculation(num1, num2);
	}
}
