/*
 * Created on Aug 21, 2005
 *
 */
package UI.Components;

import javax.swing.JTextField;
import javax.swing.text.Document;

import UI.Components.*;

/**
 * @author soumya
 *
 * * Custom textfield that allows only entering a integer
 */
public class IntegerTextField extends JTextField {
	
	protected Document createDefaultModel() {
			  return new IntegerDocument();
		 }

}


