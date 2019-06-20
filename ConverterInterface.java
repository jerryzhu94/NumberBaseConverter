/** 
 * ConverterInterface is a class that utilizes the Converter class to create 
 * perform number-base conversions. Converter contains all of the implementation
 * and details while the ConverterInterface provides an interface for the client code.
 * Should the implementations in the Converter class change, the client code will be unaffected.
 * The core functionalities that this class provides are as follow:
 * 1) Set the value that needs to be converted to other forms
 * 2) Get the Decimal, Binary, Octal, Hexadecimal, ASCII, Color, and Float Decimal forms
*/

import java.awt.Color;

public class ConverterInterface {

	public ConverterInterface()
	{
		m_converter = new Converter();
	}
	
	//set the value of the binaryString
	public void setValue(String value, String valueForm)
	{
		switch(valueForm)
		{
			case "decimal" :
				this.m_value = m_converter.convertDecimalToBinary(value);
				break;
			case "binary" :
				this.m_value = value;
				break;
			case "octal" :
				this.m_value = m_converter.convertOctalToBinary(value);
				break;
			case "hexadecimal" :
				this.m_value = m_converter.convertHexadecimalToBinary(value);
				break;
			case "ascii" :
				this.m_value = m_converter.convertASCIIToBinary(value);
				break;
			case "floatDecimal" :
				this.m_value = m_converter.convertFloatDecimalToBinary(value);
				break;
		}
	}
	
	public void setValue(Color color)
	{
		this.m_value = m_converter.convertColorToBinary(color);
	}
	
	public String getDecimalString()
	{
		return Integer.toString(m_converter.convertBinaryToDecimal(m_value));
	}
	
	public String getBinaryString()
	{
		return m_value;
	}
	
	public String getOctalString()
	{
		return m_converter.convertBinaryToOctal(m_value);
	}
	
	public String getHexadecimalString()
	{
		return m_converter.convertBinaryToHexadecimal(m_value);
	}
	
	public String getASCIIString()
	{
		return m_converter.convertBinaryToASCII(m_value);
	}
	
	public Color getColor()
	{
		return m_converter.convertBinaryToColor(m_value);
	}
	
	public String getFloatDecimalString()
	{
		return m_converter.convertBinaryToFloatDecimal(m_value);
	}
	
	private static Converter m_converter;
	private String m_value;
	
	
}
