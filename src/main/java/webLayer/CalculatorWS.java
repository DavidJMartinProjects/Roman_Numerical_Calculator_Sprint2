package webLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import businessLayer.CalculatorContext;
import businessLayer.OperationFactory;
import businessLayer.operations.SupportedOperations;
import domainLayer.CalculationResult;

@RestController
@RequestMapping(value = "/calc")
public class CalculatorWS {
	
	@Autowired
	CalculationResult result;
	
	@Autowired
	OperationFactory operationFactory;
	
	@Autowired
	CalculatorContext context;

	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = { "application/json" })
	public String greeting() {
		return "status : webService online";
	}

	@GetMapping
	public ResponseEntity<Object> performCalculation(@RequestParam("num1") final String num1, @RequestParam("num2") final String num2, 
			@RequestParam("operationType") final String operationType) {
		try {			
			context.setOperation(operationFactory.getOperation(operationType));
			result = context.executeOperation(num1, num2);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/operations", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Object> getSupportedOperations() {		
		return new ResponseEntity<>(SupportedOperations.supportedOperationsAsList(), HttpStatus.OK);
	}	

}
