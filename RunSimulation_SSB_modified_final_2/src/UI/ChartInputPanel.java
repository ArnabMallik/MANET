/*
 * Created on Aug 28, 2005
 *
 */
package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UI.Components.IntegerTextField;
import Utilities.SimulatorException;
import Utilities.SimulatorMessages;
import java.io.*;

/**
 * @author soumya
 *
 * This panel is resposnsible to draw a integer text field along with
 * a button. User enters the number of nodes he or she wants in the 
 * network in the text field. The buttonclick is communicated through
 * a chart event.
 */
public class ChartInputPanel extends JPanel implements ActionListener{
	
	IntegerTextField tf;
	JButton inputButton;
	ChartListener listener = null;
	
	public ChartInputPanel() {
			JLabel lbl = new JLabel("Enter Number of Nodes:");
			tf = new IntegerTextField();
		    inputButton = new JButton("Generate Network");
			inputButton.addActionListener(this);
			inputButton.setBackground(ChartConstants.BACKGROUNDCOLOR);

			this.setLayout(new GridLayout(1, 3));
			this.add(lbl);
			this.add(tf);
			this.add(inputButton);
		    this.setBackground(ChartConstants.BACKGROUNDCOLOR);
		}
	
	public void addChartListener(ChartListener cl)
	{
		listener = cl;
	}
		
	public void actionPerformed(ActionEvent arg0) {
		
                                        
					int nodeNumber = getNodeCount(tf.getText())+3;
                                        
					ChartEvent event;
					if (listener != null && nodeNumber > 0)
					{
                                            File f=new File("record.txt");
                                          try {  f.createNewFile();
                                          
                                          }
                                          catch(IOException e) {
                                              e.printStackTrace();
                                          }
						event = new ChartEvent(ChartConstants.CREATECHART,new Integer(nodeNumber));
						try
						{
							listener.handleChartEvent(event);
						}
						catch(SimulatorException e)
						{
						}
						
					}
								

	}
	
	private int getNodeCount(String text) {
			int toReturn = -1;
			if (text == null || text.trim().length() == 0) {
				JOptionPane.showMessageDialog(
					this,
					SimulatorMessages.BLANKNODENUMBERTEXT);
			} else {
				try {
					toReturn = Integer.parseInt(text.trim());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(
						this,
						SimulatorMessages.IMPROPERNODENUMBERTEXT);
				}
			}
		
			return toReturn;
		}

}
