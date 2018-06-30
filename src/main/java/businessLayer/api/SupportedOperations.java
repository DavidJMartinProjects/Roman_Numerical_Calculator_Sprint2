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
package businessLayer.api;

public enum SupportedOperations {

	PLUS("+"), MINUS("-"), DIVIDE("/"), MULTIPLY("*");
	
	private String symbol;
	
	SupportedOperations(final String aSymbol) {
		this.symbol = aSymbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(final String symbol) {
		this.symbol = symbol;
	}

	public static String supportedOperationsAsList() {
		String operations = "";
		final SupportedOperations arr[] = SupportedOperations.values();
		for(final SupportedOperations op : arr) {
			if(op != null) {
				operations+= op.getSymbol();
			}
		}
		return operations;
	}	

}
