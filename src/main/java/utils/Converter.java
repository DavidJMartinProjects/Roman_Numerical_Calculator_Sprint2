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
package utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import domainLayer.CalculationResult;

@Component
public class Converter {

	CalculationResult calculationResult = new CalculationResult();
	
	private final static Map<Integer, String> ROMAN_NUMERICS = new LinkedHashMap<Integer, String>();
	
	public Converter() {
		ROMAN_NUMERICS.put(1000, "M");
		ROMAN_NUMERICS.put(900, "CM");
		ROMAN_NUMERICS.put(500, "D");
		ROMAN_NUMERICS.put(400, "CD");	
		ROMAN_NUMERICS.put(100, "C");
		ROMAN_NUMERICS.put(90, "XC");
		ROMAN_NUMERICS.put(50, "L");
		ROMAN_NUMERICS.put(40, "XL");
		ROMAN_NUMERICS.put(10, "X");
		ROMAN_NUMERICS.put(9, "IX");
		ROMAN_NUMERICS.put(5, "V");
		ROMAN_NUMERICS.put(4, "IV");
		ROMAN_NUMERICS.put(1, "I");
	}

	public int toBaseTen(final String input) {
		for (final Map.Entry<Integer, String> entry : ROMAN_NUMERICS.entrySet()) {
			if (input.startsWith(entry.getValue())) {
				return entry.getKey() + toBaseTen(input.replaceFirst(entry.getValue(), ""));
			}
		}
		return 0;
	}

	public CalculationResult toRomanNumeral(int input) {
		String result = "";
		for (final Map.Entry<Integer, String> entry : ROMAN_NUMERICS.entrySet()) {
			while (input >= entry.getKey()) {
				result += entry.getValue();
				input -= entry.getKey();
			}
		}
		calculationResult.setTheResult(result);
		return calculationResult;
	}

} 
  