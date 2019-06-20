/** 
 * ConverterGUI is the entry point of the Number-Base Converter.
 * It uses the ConverterInterface class to convert a number to different forms.
 * Aside from converting, this GUI allows users to clear the textfields 
 * and pick a color to convert
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ConverterGUI {
	//Entry point of the application
	public static void main(String[] args){
		ConverterGUI converterGUI = new ConverterGUI();
	}
	
	public ConverterGUI() {
		//Create GUI
		panelMain.setLayout(new GridLayout(9,2));
		panelMain.add(decimalLabel);
		panelMain.add(decimalText);
		panelMain.add(binaryLabel);
		panelMain.add(binaryText);
		panelMain.add(octalLabel);
		panelMain.add(octalText);
		panelMain.add(hexadecimalLabel);
		panelMain.add(hexadecimalText);
		
		panelMain.add(asciiLabel);
		panelMain.add(asciiText);
		panelMain.add(colorLabel);
		panelMain.add(colorText);
		panelMain.add(floatDecimalLabel);
		panelMain.add(floatDecimalText);
		panelMain.add(convertButton);
		panelMain.add(clearButton);
		panelMain.add(colorButton);
		
		//Converts all of the textfields
		convertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ConverterInterface converter = new ConverterInterface();
				
				//Selects a textfield to convert
				if(!decimalText.getText().isEmpty())
					converter.setValue(decimalText.getText(), "decimal");
				else if(!binaryText.getText().isEmpty())
					converter.setValue(binaryText.getText(), "binary");
				else if(!octalText.getText().isEmpty())
					converter.setValue(octalText.getText(), "octal");
				else if(!hexadecimalText.getText().isEmpty())
					converter.setValue(hexadecimalText.getText(), "hexadecimal");
				else if(!asciiText.getText().isEmpty())
					converter.setValue(asciiText.getText(), "ascii");
				else if(!floatDecimalText.getText().isEmpty())
					converter.setValue(floatDecimalText.getText(), "floatDecimal");
				else
					converter.setValue(colorText.getBackground());
				
				//Sets all of the textfields
				decimalText.setText(converter.getDecimalString());
				binaryText.setText(converter.getBinaryString());
				octalText.setText(converter.getOctalString());
				hexadecimalText.setText(converter.getHexadecimalString());
				asciiText.setText(converter.getASCIIString());
				colorText.setBackground(converter.getColor());
				floatDecimalText.setText(converter.getFloatDecimalString());					
			}
		});
		
		//Allows user to pick a color
		colorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    colorText.setLayout(new FlowLayout());     
				Color color = JColorChooser.showDialog(colorText,"Select a color", Color.WHITE);   
				colorText.setBackground(color);    
			}
		});
		
		//Clears all of the textfields
		clearButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				decimalText.setText("");
				binaryText.setText("");
				octalText.setText("");
				hexadecimalText.setText("");
				asciiText.setText("");
				colorText.setBackground(Color.WHITE);
				floatDecimalText.setText(""); 
				
			}
		});
		
		
		frame.add(panelMain);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	
	private JFrame frame = new JFrame("Number Converter");
	private JLabel decimalLabel = new JLabel("Decimal");
	private JLabel binaryLabel = new JLabel("Binary");
	private JLabel octalLabel = new JLabel("Octal");
	private JLabel hexadecimalLabel = new JLabel("Hexadecimal");
	private JLabel asciiLabel = new JLabel("ASCII");
	private JLabel colorLabel = new JLabel("Color");
	private JLabel floatDecimalLabel = new JLabel("Float Decimal");
	
	private JTextField decimalText = new JTextField(20);
	private JTextField binaryText = new JTextField(20);
	private JTextField octalText = new JTextField(20);
	private JTextField hexadecimalText = new JTextField(20);
	private JTextField asciiText = new JTextField(20);
	private JTextField colorText = new JTextField(20);
	private JTextField floatDecimalText = new JTextField(20);
	
	private JButton convertButton = new JButton("Convert");
	private JButton colorButton = new JButton("Choose Color");
	private JButton clearButton = new JButton("Clear");
	private JPanel panelMain = new JPanel(new BorderLayout());
	
}
