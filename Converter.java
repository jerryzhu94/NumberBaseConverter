/**
 * Converter is a utility class that performs several number-base conversions. 
 * It is used by the ConverterInterface class. 
 * The core functionalities that this class provides are as follow:
 * 1) Convert Decimal, Octal, Hexadecimal, ASCII, Color, or Float Decimal to Binary
 * 2) Convert Binary to Decimal, Octal, Hexadecimal, ASCII, Color, or Float Decimal.
 * Please note that this class specifically handles 32-bit numbers. 
 */

import java.awt.Color;

public class Converter {

	public Converter() {
	}

	//Converts decimal string to binary string
	public String convertDecimalToBinary(String decimalString)
	{
		return reformatString(Integer.toBinaryString(Integer.parseInt(decimalString)), 32);
	}
	
	//Converts octal string to binary string
	public String convertOctalToBinary(String octalString)
	{
		String binaryString = "";
		
		for(int i = 0; i < 11; i++)
		{
			String threeBits = Integer.toBinaryString(Character.getNumericValue(octalString.charAt(i)));
			threeBits = reformatString(threeBits, 3);
			
			binaryString = binaryString + threeBits;
		}
		
		return reformatString(binaryString, 32);
	}
	
	//Converts hexadecimal string to binary string
	public String convertHexadecimalToBinary(String hexadecimalString)
	{
		String binaryString = "";
		
		for(int i = 0; i < 8; i++)
		{
			Integer decimalValue = Integer.parseInt(Character.toString(hexadecimalString.charAt(i)), 16);
			String fourBits = Integer.toBinaryString(decimalValue);
			fourBits = reformatString(fourBits, 4);
			
			binaryString = binaryString + fourBits;
		}

		return reformatString(binaryString, 32);
	}
	
	//Converts ASCII string to binary string
	public String convertASCIIToBinary(String asciiString)
	{
		String binaryString = "";
		for(int i = 0; i < asciiString.length(); i++)
		{
			String eightBits = Integer.toBinaryString(asciiString.charAt(i));
			eightBits = reformatString(eightBits, 8);
			binaryString = binaryString + eightBits;
		}
		
		return reformatString(binaryString, 32);
	}
	
	//Converts color to binary string
	public String convertColorToBinary(Color color)
	{
		String alphaBits = reformatString(Integer.toBinaryString(color.getAlpha()), 8);
		String redBits = reformatString(Integer.toBinaryString(color.getRed()), 8);
		String greenBits = reformatString(Integer.toBinaryString(color.getGreen()), 8);
		String blueBits = reformatString(Integer.toBinaryString(color.getBlue()), 8);
		return alphaBits + redBits + greenBits + blueBits;
	}
	
	//Converts float decimal string to binary string
	public String convertFloatDecimalToBinary(String floatDecimalString)
	{
		double decimal = Double.parseDouble(floatDecimalString);
		
		//get signBit
		int signBit = (decimal > 0) ? 0 : 1;
		
		//get exponentBits
		int exponent = 0;
		while(Math.abs(decimal) >= 2)
		{
			decimal /= 2;
			exponent++;
		}
		String exponentBits = Integer.toBinaryString(exponent + 127);
		
		//get mantissaBits
		String mantissaBits = "";
		double mantissa = Math.abs(decimal) - 1;
		for(int i = 0; i < 23; i++)
		{
			mantissa *= 2;
			if(mantissa < 1.0)
				mantissaBits = mantissaBits + "0";
			else
			{
				mantissaBits = mantissaBits + "1";
				mantissa--;
			}
		}
		return signBit + exponentBits + mantissaBits;
	}
	
	//Converts binary string to decimal string 
	public int convertBinaryToDecimal(String binaryString)
	{
		Boolean isNegative = false;
		//if binaryString is negative, convert to positive 
		if(binaryString.charAt(0) == '1')
		{
			binaryString = binaryString.replace('0', '2').replace('1', '0').replace('2', '1');
			isNegative = true;
		}
		int binary = Integer.parseInt(binaryString,2);
		
		return (isNegative) ? -1 * (binary + 1) : binary;
	}
	
	//Converts binary string to octal string
	public String convertBinaryToOctal(String binaryString)
	{
		String octalString = "" + Integer.parseInt(binaryString.substring(0,2), 2);
		for(int i = 0; i < 10; i++)
		{
			octalString = octalString + Integer.parseInt(binaryString.substring(2 + i * 3, 5 + i * 3), 2);
		}
		return octalString;
	}
	
	//Converts binary string to hexadecimal string
	public String convertBinaryToHexadecimal(String binaryString)
	{
		String hexadecimalString = "";
		Character hexdecimalDigit;
		for(int i = 0; i < 8; i++)
		{
			hexdecimalDigit = Character.forDigit(Integer.parseInt(binaryString.substring(i * 4, 4 + i * 4), 2), 16);
			hexadecimalString = hexadecimalString + hexdecimalDigit;
		}
		return hexadecimalString;
	}
	
	//Converts binary string to ASCII string
	public String convertBinaryToASCII(String binaryString)
	{
		String asciiString = "";
		for(int i = 0; i < 4; i++)
			asciiString = asciiString + (char)Integer.parseInt(binaryString.substring(i * 8, 8 + i * 8), 2);
		
		return asciiString;
	}
	
	//Converts binary string to color
	public Color convertBinaryToColor(String binaryString)
	{
		return new Color(convertBinaryToDecimal(binaryString));
	}
	
	//Converts binary string to float decimal string
	public String convertBinaryToFloatDecimal(String binaryString)
	{	
		//evaluate signBit
		int sign = Character.getNumericValue(binaryString.charAt(0));
		
		//evaluate exponentBits
		int exponent = Integer.parseInt(binaryString.substring(1,9), 2) - 127;
		
		//evaluate mantissaBits
		String mantissaBits = binaryString.substring(9,32);
		double mantissa = 0;
		double bitValue = 0.5;
		for(int i = 0; i < 23; i++)
		{
			if(mantissaBits.charAt(i) == '1')
				mantissa += bitValue;
			bitValue /= 2;
		}
		
		//get float decimal
		double floatDecimal = Math.pow(-1, sign) * ((1 + mantissa) * Math.pow(2, exponent));
		
		return Double.toString(floatDecimal);
	}
	
	//Helper method used to reformat strings to desired length. 
	//If inputted string is too short, it will left pad with zeros
	//If inputted string is too long, it will right shift till string has desiredLength
	private String reformatString(String string, int desiredLength)
	{
		if(string.length() < desiredLength)
		{
			while(string.length() < desiredLength)
				string = "0" + string;
		}
		if(string.length() > desiredLength)
		{
			string = string.substring(string.length() - desiredLength);
		}
		return string;
	}
	
}
