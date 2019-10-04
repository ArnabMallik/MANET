/*
 * Created on Aug 21, 2005
 * 
 */
package UI.Components;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * @author soumya
 *
 * Custom textfield that allows only entering a decimal number
 * */
public class DoubleTextField extends JTextField {
	
        @Override
	protected Document createDefaultModel() {
				  return new DoubleDocument();
			 }

}
