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
import javax.swing.JPanel;

import Simulation.SimulationModel;
import Utilities.SimulationConstants;
import Utilities.SimulatorException;
import javax.swing.JOptionPane;
import Utilities.Node;
import Utilities.QueueManager;
import Utilities.SimulationUtilities;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.text.Utilities;
import org.jfree.chart.ChartColor;

/**
 * @author soumya
 *
 * This panel shows the basic configuration parameters of the simulator
 * It also paints two button where by the user can change the network configuration
 * in between the running of the simulator
 * 
 */
public class SimulationConfigurationPanel extends JPanel implements ActionListener {
	
	JButton propertyButton,updateButton,queueButton,addButton,deleteButton;
	ChartListener listener;
        public static int flag =0;
        public static int leader_node_changed=0,k,no_more_update=0,updated=0,node_added=0,node_deleted;
	
	public SimulationConfigurationPanel(SimulationModel model,boolean SimulationRunner)
	{
		super();
              
		setLayout(new GridLayout(1, 6));
                String labelText1;
                if(NetworkConfigurationPanel.deleted==1)
                     labelText1= " Nodes = " + (model.getNumberOfNodes()-1);
                else if(NetworkConfigurationPanel.added==0)
                    labelText1= " Nodes = " + (model.getNumberOfNodes()-3);
                else
                    labelText1="Nodes = "+(model.getNumberOfNodes()+NetworkConfigurationPanel.added-3);
		String labelText2 ="Max. Velocity = " + SimulationConstants.MAXVELOCITY + " Km/Hr";
		String labelText3 ="Comm. Range = "
				+ SimulationConstants.COMMUNICATIONRANGE
				+ " Km  ";
                String labelText4;
                k=QueueManager.store_leader[0];
                if(k==0)
                 labelText4 =" Leader Node = None";
                else
                    labelText4 =" Leader Node = "+k;
 
				
		add(new JLabel(labelText1));
		add(new JLabel(labelText2));
		add(new JLabel(labelText3));
                add(new JLabel(labelText4));
                
               // for(int i=0;i<QueueManager.store_leader.length;i++)
                            //QueueManager.cstore_leader[i]=QueueManager.store_leader[i];
                    
		if (flag==0) {
		updateButton = new JButton("Update");
		updateButton.setBackground(ChartConstants.BACKGROUNDCOLOR);
		updateButton.addActionListener(this);
		add(updateButton);
                flag=1;
                } 
		else {
                updateButton = new JButton("Re-Update");
		updateButton.setBackground(ChartConstants.BACKGROUNDCOLOR);
                updateButton.addActionListener(this);
		add(updateButton);
                }
                
                queueButton = new JButton("Show Queue");
                queueButton.setBackground(ChartConstants.BACKGROUNDCOLOR);
                queueButton.addActionListener(this);
                add(queueButton);
		
		if (SimulationRunner) {
		propertyButton = new JButton(ChartConstants.PROPERTYTEXT);
		updateButton.setEnabled(false);
                queueButton.setEnabled(false);
                
		} 
		else {
			propertyButton = new JButton(ChartConstants.RUNTEXT);
			updateButton.setEnabled(true);
                        queueButton.setEnabled(true);
		}
                
                if(NetworkConfigurationPanel.added<3)
                    queueButton.setEnabled(false);
                if(no_more_update==1)
                    
                    updateButton.setEnabled(false);

		propertyButton.setBackground(ChartConstants.BACKGROUNDCOLOR);
		propertyButton.addActionListener(this);
		add(propertyButton);
                addButton=new JButton("add a node");
                addButton.setBackground(ChartConstants.BACKGROUNDCOLOR);
		addButton.addActionListener(this);
		add(addButton);
                
                if(NetworkConfigurationPanel.delete_id==0) {
               deleteButton=new JButton("");
                deleteButton.setEnabled(false);
                }
                else{
                    deleteButton=new JButton("delete Node "+NetworkConfigurationPanel.delete_id);
                //deleteButton.setEnabled(true);
                }
                deleteButton.setBackground(ChartConstants.BACKGROUNDCOLOR);
		deleteButton.addActionListener(this);
                
                
               // if(NetworkConfigurationPanel.added<5)
                    
                   // queueButton.setEnabled(false);
                    
                    
                
               
		//add(deleteButton);

                 if(NetworkConfigurationPanel.added==3)
                    
                    addButton.setEnabled(false);
                 
                 if(NetworkConfigurationPanel.deleted==1){
                    
                    deleteButton.setEnabled(false);
                    
                   
                 }
               
               
		setBackground(ChartConstants.BACKGROUNDCOLOR);
		
		
	}
	
	public void addChartListener(ChartListener cl)
	{
		listener = cl;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
	
		ChartEvent event;
		if(arg0.getSource().equals(updateButton))
		{
                    QueueManager.change=1;updated=1;
			event = new ChartEvent(ChartConstants.UPDATECHART,null);
			try
			{
				listener.handleChartEvent(event);
                                int id=Node.getMaxNodeid();
                                k=id;
                                //JOptionPane.showMessageDialog(updateButton,"Node "+id+" is the Leader Node");
                                
                                
                              
			}
			catch(SimulatorException e)
			{
				return;
			}
                       
		}
		else if (arg0.getActionCommand().equals(ChartConstants.RUNTEXT)) 
		{
                    if(NetworkConfigurationPanel.added<3)
                   QueueManager.change=1;
			NetworkConfigurationPanel.run=1;
			if(listener != null)
			{
				event = new ChartEvent(ChartConstants.MOVECHART,null);
				try
				{
					listener.handleChartEvent(event);
                                        
                                        
				}
				catch(SimulatorException e)
				{
					return;
				}
                                 
			}
			
			propertyButton.setText(ChartConstants.PROPERTYTEXT);
			propertyButton.setActionCommand(ChartConstants.PROPERTYTEXT);
			addButton.setEnabled(false);
			updateButton.setEnabled(false);
                        deleteButton.setEnabled(false);

		}
		else if (arg0.getActionCommand().equals(ChartConstants.PROPERTYTEXT)) 
		{
			
			if(listener != null)
			{
				event = new ChartEvent(ChartConstants.STOPCHART,null);
				try
				{
					listener.handleChartEvent(event);
				}
				catch(SimulatorException e)
				{
					return;
				}
			}
			
			propertyButton.setText(ChartConstants.RUNTEXT);
			propertyButton.setActionCommand(ChartConstants.RUNTEXT);
			//updateButton.setEnabled(true);
                        if(NetworkConfigurationPanel.added>2)
                        queueButton.setEnabled(true);
                        
                        
                        
		}
                
                else if(arg0.getActionCommand().equals(ChartConstants.QUEUETEXT))
                {
                   if(QueueManager.isQueueEmpty())
                        
                        JOptionPane.showMessageDialog(this, "queue empty");
                    
                    else {
                    
                      String print="Queue size = "+(QueueManager.len-1)+"\n";
                      
                      
                      print=print+"Leader Node = "+QueueManager.store_leader[0]+"\n";
                      
                               print=print+"  Front";
                              
                      for(int i=1;i<QueueManager.len;i++)
                      
                          if(QueueManager.store_leader[i]==-1)
                              
                              print=print+"\n"+"  _";
                      else
                          
                          print=print+"\n   "+QueueManager.store_leader[i];
                      
                    
                    print=print+"\n  Rear";
                    
                    JOptionPane.showMessageDialog(this,print);
                    
                    
                }


	}
                else if(arg0.getActionCommand().equals("add a node")){
                    
                  
                    node_added=1;
                    NetworkConfigurationPanel.added++;
                    QueueManager.increase_length();
                    JOptionPane.showMessageDialog(this,"Node"+(SimulationUtilities.no_of_node-3+NetworkConfigurationPanel.added)+" added..press update to see updated network");
                    //addButton.setEnabled(false);
                }
                
                else if(arg0.getActionCommand().equals("delete Node "+NetworkConfigurationPanel.delete_id)){
                    
                    
                    node_deleted=1;
                    JOptionPane.showMessageDialog(this,"Node deleted..press update to see updated network");
                    NetworkConfigurationPanel.deleted=1;
                    NetworkConfigurationPanel.delete_confirmed=1;
                  // SimulationUtilities.al.remove(SimulationUtilities.no_of_node- NetworkConfigurationPanel.delete_id);
                   deleteButton.setEnabled(false);
                }
              
}
}