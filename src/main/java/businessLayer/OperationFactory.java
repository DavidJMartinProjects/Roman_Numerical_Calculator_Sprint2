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

import org.springframework.stereotype.Component;

import businessLayer.api.CalculatorStrategy;
import businessLayer.operations.AddOperation;
import businessLayer.operations.DivideOperation;
import businessLayer.operations.MultiplyOperation;
import businessLayer.operations.SubtractOperation;

@Component	
public class OperationFactory { 

	public CalculatorStrategy getOperation(final String operationType) {
		if("add".equals(operationType)) {
			return new AddOperation();
		} else if("subtract".equals(operationType)) {
			return new SubtractOperation();		
		} else if("divide".equals(operationType)) {
			return new DivideOperation();
		} else if("multiply".equals(operationType)) {
			return new MultiplyOperation();
		} 
		return null;
	}
	
}
