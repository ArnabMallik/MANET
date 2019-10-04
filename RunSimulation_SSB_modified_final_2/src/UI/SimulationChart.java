/*
 * Created on Aug 13, 2005
 * 
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import Simulation.SimulationModel;
import Utilities.BaseConfigurationReader;
import Utilities.NetworkDirector;
import Utilities.SimulationUtilities;
import Utilities.SimulatorException;
import Utilities.SimulatorMessages;
import java.io.*;

/**
 * @author soumya
 *
 * This class is responsible to show the simulator.
 * It controls the different panels and handles the communication
 * between different panels implementing the Chart Listener
 *  
 */
public class SimulationChart
	extends ApplicationFrame
	implements ChartListener {

	SimulationModel model;
	
	NetworkConfigurationPanel propertiesPanel;
	
	Thread thread;
	Runnable run;
	
	NetworkDirector generator;	
	String SnapFileName;
	int SnapCount;

	public SimulationChart(
		String Title,
		SimulationModel mdl,
		Runnable inputThread,
		boolean NetworkSnapNeeded) {
		super(Title);
		model = mdl;
		run = inputThread;
		if(NetworkSnapNeeded)
		{
			SnapFileName = Title;
			SnapCount = 1;
		}
			

	}
	
	public void addNetworkOperator(NetworkDirector gen)
	{
		generator = gen;		
	}
  //method used to show the first input form
	public void showInputForm() {
		ChartInputPanel pnl = new ChartInputPanel();
		setDisplayPanel(pnl);
		pnl.addChartListener(this);
	}

	private void setDisplayPanel(JPanel pnl) {
		setContentPane(pnl);
		invalidate();
		validate();
		pack();
		RefineryUtilities.centerFrameOnScreen(this);
		setLocation(0, 0);
	}

	//method to show network properties. it generates a default network and 
	//allows user to change
/*
private void showNetworkProperties() {
		JPanel pnl = new JPanel();
		
		SimulationConfigurationPanel configPanel = new SimulationConfigurationPanel(model,false);
		configPanel.addChartListener(this);
		
		propertiesPanel = new NetworkConfigurationPanel(model,generator);

		pnl.setLayout(new BorderLayout());
		pnl.add(configPanel, BorderLayout.NORTH);
		pnl.add(new JScrollPane(propertiesPanel), BorderLayout.CENTER);

		setDisplayPanel(pnl);
		
	}
	*/

	
	public void updateChart(Hashtable Nodes) 
	{
		updateChart(Nodes,true);
	}
	// this method draws the actual chart and shows the simulation window
	private void updateChart(Hashtable Nodes,boolean ButtonState) {
		SimulationUtilities.printNetwork(Nodes);
			
		SimulationChartPanel panel = new SimulationChartPanel(generator);
		ChartPanel chartPanel = panel.getChartPanel(model);
		saveChart(panel);
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBackground(ChartConstants.BACKGROUNDCOLOR);

		//SimulationConfigurationPanel configPanel = new SimulationConfigurationPanel(model,true);
		//SimulationConfigurationPanel configPanel = new SimulationConfigurationPanel(model,false);
		SimulationConfigurationPanel configPanel = new SimulationConfigurationPanel(model,ButtonState);
		configPanel.addChartListener(this);
		configPanel.setPreferredSize(new Dimension(1000,25));
                try {
		
		propertiesPanel = new NetworkConfigurationPanel(model,generator);
		
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
		JScrollPane scrollingPropertyPanel = new JScrollPane(propertiesPanel);
		scrollingPropertyPanel.setPreferredSize(new Dimension(1000,150));

		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(configPanel, BorderLayout.NORTH);
		displayPanel.add(chartPanel, BorderLayout.CENTER);
		displayPanel.add(scrollingPropertyPanel, BorderLayout.SOUTH);
		propertiesPanel.setEnabled(false);

		setContentPane(displayPanel);

		RefineryUtilities.centerFrameOnScreen(this);
		setSize(1280, 720);
		
		//if(!scrollingPropertyPanel.isVisible())
			setLocation(0, 0);

		invalidate();
		validate();
	}
	
	private void saveChart(SimulationChartPanel panel)
	{
		if(SnapFileName == null) return;
		panel.saveChart(SnapFileName+(SnapCount++)+".png");
		//panel.saveChart(SnapFileName+(SnapCount++)+".jpeg");
	}

	
	public void handleChartEvent(ChartEvent event) throws SimulatorException{
	
		
		if (event.getEventId() == ChartConstants.CREATECHART)
		{
			int NumberOfNodes = ((Integer)event.getEventValue()).intValue();
                        
                        Utilities.SimulationUtilities.set_total(NumberOfNodes);
			if (NumberOfNodes < 2)
			{
				JOptionPane.showMessageDialog(this,SimulatorMessages.MINIMUMNODENUMBERTEXT);
			}
			else
			{
				if(generator != null)
					{
						generator.generateNetwork(NumberOfNodes) ;
						//showNetworkProperties();
						updateChart(model.getNetwork(),false);
					}
				else
					JOptionPane.showMessageDialog(this,	SimulatorMessages.NOGENERATORTEXT);
				
			}
		}
		else if (event.getEventId() == ChartConstants.MOVECHART)
		{
			try
			{
				propertiesPanel.updateModel(model);				
				generator.validateNetwork(model.getNetwork());
			}
			catch (SimulatorException e)
			{
				JOptionPane.showMessageDialog(this,	e.getMessage());
				//System.out.println("Returning from here");
				//return;
				throw e;
			}
			
			updateChart(model.getNetwork());
			propertiesPanel.setEnabled(false);
				
			thread = new Thread(run);
			thread.start();
		}
		else if (event.getEventId() == ChartConstants.STOPCHART)
		{
			try {
					if (thread != null)
						thread.stop();
					// need to find a better way
						thread = null;
				} catch (Exception e) 
					{
						e.printStackTrace();
					}
									
			propertiesPanel.setEnabled(true);
		}
		else if (event.getEventId() == ChartConstants.UPDATECHART)
		{
			propertiesPanel.updateModel(model);
			generator.refreshNetwork(model.getNetwork());
			updateChart(model.getNetwork(),false);
		}
			
      
	}
	
	public void windowClosing(WindowEvent evt)
	{
		SimulationUtilities.terminate();
		super.windowClosing(evt);
	}

}
