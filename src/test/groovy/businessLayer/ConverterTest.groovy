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
package businessLayer

import org.junit.Test

import domainLayer.CalculationResult
import domainLayer.RomanNumerics
import spock.lang.Specification
import spock.lang.Unroll
import utils.Converter

/**
 * @author eaamrvd
 *
 */
class ConverterTest extends Specification {

	def input, expected
	def calculationResult = new CalculationResult()
	def romanNumerics = Mock(RomanNumerics.class)
	def theConverter= new Converter(calculationResult:calculationResult);
 
	@Test
	@Unroll
	def 'test that when #input is passed to the toBaseTen method the expected result of #expected is returned'() {

		when: 'when the toBaseTen method is called'
		def result = theConverter.toBaseTen(input);

		then: 'the expected base10 value of the Roman numeral is returned'
		expected == result

		/* Test parameters include :
		 * the lowest number, 
		 * the highest number, 
		 * every number that translates to a single-character Roman numeral, 
		 * and a sampling of other valid numbers focusing on typically troublesome conversions (9's, 4's, longest roman numeral String etc).*/
		where:
		input	 			|| expected
		"I"		 			|| 1
		"IV"	 			|| 4
		"V"		 			|| 5
		"IX"	 			|| 9
		"X"		 			|| 10
		"XL"	 			|| 40
		"L"		 			|| 50
		"XC"	 			|| 90
		"C"		 			|| 100
		"CD"	 			|| 400
		"D"		 			|| 500
		"CM"	 			|| 900
		"M"		 			|| 1000
		"XXXI"	 			|| 31
		"CXLVIII" 			|| 148
		"CCXCIV"			|| 294
		"CCCXII"			|| 312
		"CDXXI"				|| 421
		"DXXVIII"			|| 528
		"DCXXI"				|| 621
		"DCCLXXXII"			|| 782
		"DCCCLXX"			|| 870
		"CMXLI"	 			|| 941
		"MXLIII" 			|| 1043
		"MCX" 				|| 1110
		"MCCXXVI"			|| 1226
		"MCCCI"				|| 1301
		"MCDLXXXV"			|| 1485
		"MDIX"				|| 1509
		"MDCVII"			|| 1607
		"MDCCLIV"			|| 1754
		"MDCCCXXXII"		|| 1832
		"MCMXCIII"			|| 1993
		"MMLXXIV" 			|| 2074
		"MMCLII" 			|| 2152
		"MMCCXII"			|| 2212
		"MMCCCXLIII"		|| 2343
		"MMCDXCIX"			|| 2499
		"MMDLXXIV"			|| 2574
		"MMDCXLVI"			|| 2646
		"MMDCCXXIII"		|| 2723
		"MMDCCCXCII"		|| 2892
		"MMCMLXXV"			|| 2975
		"MMMLI" 		  	|| 3051
		"MMMCLXXXV" 	  	|| 3185
		"MMMCCL"		  	|| 3250
		"MMMCCCXIII"	  	|| 3313
		"MMMCDVIII"		  	|| 3408
		"MMMDI"			  	|| 3501
		"MMMDCX"		  	|| 3610
		"MMMDCCXLIII"	 	|| 3743
		"MMMDCCCXLIV"	 	|| 3844
		"MMMDCCCLXXXVIII"	|| 3888
		"MMMCMXL"		 	|| 3940
		"MMMCMXCIX"		 	|| 3999
	}

	@Test
	@Unroll
	def 'test that when #input is passed to the toRomanNumeral method the expected result of #expected is returned'() {
		given:
		romanNumerics.getRomanNumerics() >> new RomanNumerics().getRomanNumerics();

		when: 'when the toRomanNumeral method is called'
		def result = theConverter.toRomanNumeral(input);

		then: 'the expected roman numeral representation of the integer value is returned'
		expected == result.theResult

		/* Test parameters include :
		 * the lowest number, 
		 * the highest number, 
		 * every number that translates to a single-character Roman numeral, 
		 * and a sampling of other valid numbers focusing on typically troublesome conversions (9's, 4's, longest roman numeral String etc).*/
		where:
		expected			|| input
		"I"		 			|| 1
		"IV"	 			|| 4
		"V"		 			|| 5
		"IX"	 			|| 9
		"X"		 			|| 10
		"XL"	 			|| 40
		"L"		 			|| 50
		"XC"	 			|| 90
		"C"		 			|| 100
		"CD"	 			|| 400
		"D"		 			|| 500
		"CM"	 			|| 900
		"M"		 			|| 1000
		"XXXI"	 			|| 31
		"CXLVIII" 			|| 148
		"CCXCIV"			|| 294
		"CCCXII"			|| 312
		"CDXXI"				|| 421
		"DXXVIII"			|| 528
		"DCXXI"				|| 621
		"DCCLXXXII"			|| 782
		"DCCCLXX"			|| 870
		"CMXLI"	 			|| 941
		"MXLIII" 			|| 1043
		"MCX" 				|| 1110
		"MCCXXVI"			|| 1226
		"MCCCI"				|| 1301
		"MCDLXXXV"			|| 1485
		"MDIX"				|| 1509
		"MDCVII"			|| 1607
		"MDCCLIV"			|| 1754
		"MDCCCXXXII"		|| 1832
		"MCMXCIII"			|| 1993
		"MMLXXIV" 			|| 2074
		"MMCLII" 			|| 2152
		"MMCCXII"			|| 2212
		"MMCCCXLIII"		|| 2343
		"MMCDXCIX"			|| 2499
		"MMDLXXIV"			|| 2574
		"MMDCXLVI"			|| 2646
		"MMDCCXXIII"		|| 2723
		"MMDCCCXCII"		|| 2892
		"MMCMLXXV"			|| 2975
		"MMMLI" 		  	|| 3051
		"MMMCLXXXV" 	  	|| 3185
		"MMMCCL"		  	|| 3250
		"MMMCCCXIII"	  	|| 3313
		"MMMCDVIII"		  	|| 3408
		"MMMDI"			  	|| 3501
		"MMMDCX"		  	|| 3610
		"MMMDCCXLIII"	 	|| 3743
		"MMMDCCCXLIV"	 	|| 3844
		"MMMDCCCLXXXVIII"	|| 3888
		"MMMCMXL"		 	|| 3940
		"MMMCMXCIX"		 	|| 3999
	}
}
