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
public class OperationContext {
	
	@Autowired
	CalculatorStrategy operation;	
			
	public OperationContext(CalculatorStrategy anOperation){
	    this.operation = anOperation;
	}	
	
	public CalculatorStrategy getOperation() {
		return operation;
	}	

	public void setOperation(CalculatorStrategy operation) {
		this.operation = operation;
	}

	public CalculationResult executeOperation(String num1, String num2) {
		return operation.doCalculation(num1, num2);
	}
}
