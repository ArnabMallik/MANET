/*
 * Created on Aug 21, 2005
 *
 * 
 */
package UI.Components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author soumya
 *
 * The document class to ensure entry of only decimal numbers. 
 * It is needed to help the user interface
 * 
 */
public class DoubleDocument extends PlainDocument {
	
	public void insertString(int offs, String str, AttributeSet a) 
				 throws BadLocationException {
				 double number = 0;
				 String toInsert = null;
				 String ExistingValue  = super.getContent().getString(0,offs);
								
				// System.out.println("Old string was "+ExistingValue);
				 if (str == null) {
				 return;
				 }
				 
				 if(ExistingValue.trim().length() == 0 && str.equals("-"))
				 {
				 	toInsert = str;
				 }
				 else
				 {
					try
									 {
										number = Double.parseDouble(str);
									 }
									 catch(Exception e)
									 {
					
										if(str.equals(".") && ExistingValue.indexOf(".") < 0)
											toInsert = str;
										else	
											return;
									 }
				 
									 if(toInsert == null)
									 {
										toInsert = Double.toString(number);
										//System.out.println("Going to insert "+toInsert);
				 
										 if(toInsert.endsWith("0"))
										 {
											toInsert = toInsert.substring(0,toInsert.length()-1);
											if(toInsert.endsWith("."))
											{
												toInsert = toInsert.substring(0,toInsert.length()-1);
											}
										 }
				 
									 }
				 
				
				 }
				  
				 super.insertString(offs, toInsert, a);
			 }

}
