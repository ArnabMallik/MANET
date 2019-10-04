/*
 * Created on Aug 21, 2005
 *
 */
package UI.Components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author soumya
 *
 * The document class to ensure entry of only integers 
 * It is needed to help the user interface
 */
public class IntegerDocument extends PlainDocument {
	
	public void insertString(int offs, String str, AttributeSet a) 
			 throws BadLocationException {
             int number;
			 if (str == null) {
			 return;
			 }
			 try
			 {
			 	number = Integer.parseInt(str);
			 }
			 catch(Exception e)
			 {
			 	return;
			 }
			
			 super.insertString(offs, Integer.toString(number), a);
		 }

}
