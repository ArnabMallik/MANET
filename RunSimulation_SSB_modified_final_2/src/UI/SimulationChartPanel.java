/*
 * Created on Aug 28, 2005
 * * 
 */
package UI;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.StandardXYItemRenderer;
import org.jfree.data.XYSeries;
import org.jfree.data.XYSeriesCollection;

import Simulation.SimulationModel;
import Utilities.NetworkDirector;
import Utilities.Node;
import Utilities.NodePosition;
import Utilities.SimulationConstants;
import java.awt.Color;
import org.jfree.data.XYDataPair;


/**
 * @author soumya
 *
 * This clas paints the graph to show the actual network.
 * It uses JFreeChart classes
 * 
 * */
public class SimulationChartPanel {
	
	NetworkDirector generator;
	JFreeChart chart;	
	
	public SimulationChartPanel(NetworkDirector operator)
	{
		generator = operator;
	}
	
	public ChartPanel getChartPanel(SimulationModel model)
	{
		chart = createChart(model);
		return createChartPanel(chart);
	}
	
	private JFreeChart createChart(SimulationModel model) {

			XYSeriesCollection dataset = formXYDataSet(model);

			JFreeChart chart =
				ChartFactory.createLineXYChart(
					"MANET Movement Simulator",
					"X (In KM)",
					"Y (In KM)",
					dataset,
					false,
					true,
					false);
			customizeChart(chart, dataset,model);	
		
			return chart;
		}
     
	private  XYSeriesCollection formXYDataSet(SimulationModel model) {
			XYSeriesCollection toReturn = new XYSeriesCollection();

			XYSeries series;
            
            Hashtable Nodes = model.getNetwork();
             
			Enumeration nodes = Nodes.elements();
			Node current, temp;
			Iterator neighbours;
			String neighbourName;
			ArrayList TipList;
                        
                        
			
			ArrayList ConsideredNodes = new ArrayList();//to avoid duplication

			model.resetToolTip();
			while (nodes.hasMoreElements()) {
				current = (Node) nodes.nextElement();
				//System.out.println("Processing for Node "+current.getNodeName());
				ConsideredNodes.add(current.getNodeName()) ;
				
				if (current.getNumberOfNeighbours() > 0) {
					neighbours = current.getNeighbourList();

					while (neighbours.hasNext()) {
						neighbourName = (String) neighbours.next();
						
						if (ConsideredNodes.contains(neighbourName)) continue;
						
						temp = (Node) Nodes.get(neighbourName);
						series = new XYSeries("Simulator");
                                               
						series.add(
							current.getCurrentPosition().getXPosition(),
							current.getCurrentPosition().getYPosition());
						series.add(
							temp.getCurrentPosition().getXPosition(),
							temp.getCurrentPosition().getYPosition());
						toReturn.addSeries(series);

						TipList = new ArrayList();
						TipList.add(current.getNodeName());
						TipList.add(temp.getNodeName());
						model.addToolTip(TipList);
					}

				} else {
					series = new XYSeries("Simulator");
					series.add(
						current.getCurrentPosition().getXPosition(),
						current.getCurrentPosition().getYPosition());
					toReturn.addSeries(series);

					TipList = new ArrayList();
					TipList.add(current.getNodeName());
					model.addToolTip(TipList);
				}

			}

			return toReturn;
		}
		
	private void customizeChart(JFreeChart chart, XYSeriesCollection dataset,SimulationModel model) {
			chart.setBackgroundPaint(ChartConstants.BACKGROUNDCOLOR);

			XYPlot plot = chart.getXYPlot();
			StandardXYItemRenderer renderer =
				(StandardXYItemRenderer) plot.getRenderer();
                        
                     
			renderer.setPlotShapes(true);
			renderer.setDefaultShapeFilled(false);
			//renderer.setPlotImages(true);
			XYSeries series;
                        
                        
                              
                          int flag=0;     
                            
                       
		
			int noOfSeries = dataset.getSeriesCount();
                        
			//System.out.println("Number of Series " + noOfSeries);
			for (int i = 0; i <noOfSeries; i++) {
				series = dataset.getSeries(i);
                                
                               
                                 //System.out.println(series.getXValue(0).doubleValue()+" "+series.getYValue(0).doubleValue());
                            //System.out.println(series.getXValue(1).doubleValue()+" "+series.getYValue(1).doubleValue());
                           
                             
                                 
                              
                             
                                
                              //System.out.println(n.getCurrentPosition().getXPosition()+" "+n.getCurrentPosition().getYPosition());
                           
                            
                            
                            
                            
				if(isWellConnected(series)) {
                                    renderer.setSeriesPaint(i, ChartConstants.PREFEREDLINKCOLOR);
                                    for(int k=2;k>-1;k--) {
                                         Node n=(Node)Utilities.SimulationUtilities.al.get(k);
                                         
                                       // System.out.println(n);
                                         
                                          
                                    if(((series.getXValue(0).doubleValue()==n.getCurrentPosition().getXPosition() && series.getYValue(0).doubleValue()==n.getCurrentPosition().getYPosition())|| (series.getXValue(1).doubleValue()==n.getCurrentPosition().getXPosition() && series.getYValue(1).doubleValue()==n.getCurrentPosition().getYPosition()))) {
                                        //System.out.println("sd");
                                        if(2-NetworkConfigurationPanel.added==k-1 || NetworkConfigurationPanel.s.contains(Integer.toString(k))){
                               renderer.setSeriesPaint(i,Color.BLUE);
                               NetworkConfigurationPanel.s=NetworkConfigurationPanel.s.concat(Integer.toString(k));
                                        }
                               
                                        else 
                                            
                                            renderer.setSeriesPaint(i,Color.white);
                               
                                    }
                                      if(NetworkConfigurationPanel.deleted==1) {
                                        
                                          Node m=(Node)NetworkConfigurationPanel.al1.get(Utilities.SimulationUtilities.no_of_node-NetworkConfigurationPanel.delete_id);
                                          
                                          //System.out.println(m.getCurrentPosition().getXPosition());
                                        
                                         if(((series.getXValue(0).doubleValue()==m.getCurrentPosition().getXPosition() && series.getYValue(0).doubleValue()==m.getCurrentPosition().getYPosition())|| (series.getXValue(1).doubleValue()==m.getCurrentPosition().getXPosition() && series.getYValue(1).doubleValue()==m.getCurrentPosition().getYPosition()))) 
                                        
                                        
                                          renderer.setSeriesPaint(i,Color.white);
                                         
                                        
                                        
                                    }
                                    
                                  
                             
                                    }
                                }
                              
                                    
                                    
                               // System.out.println("node:"+(i+1));   
                                
                                
                                else {
                                    int k;
                             
                                  for(k=2;k>-1;k--) {
                                         Node n=(Node)Utilities.SimulationUtilities.al.get(k);
                                         
                                        //System.out.println(n);
                                           if(((series.getXValue(0).doubleValue()==n.getCurrentPosition().getXPosition() && series.getYValue(0).doubleValue()==n.getCurrentPosition().getYPosition())|| (series.getXValue(1).doubleValue()==n.getCurrentPosition().getXPosition() && series.getYValue(1).doubleValue()==n.getCurrentPosition().getYPosition()))) 
                                           {
                                               flag=1;
                                               break;
                                           }
                                  }
                                    if(flag==1) {
                                       // System.out.println("sd");
                                        if(2-NetworkConfigurationPanel.added==k-1)
                               renderer.setSeriesPaint(i,ChartConstants.ALTERNATELINKCOLOR);
                              
    
                                        else renderer.setSeriesPaint(i,Color.white);
                                    }
                                    else  if(NetworkConfigurationPanel.deleted==1) {
                                        
                                          Node m=(Node)NetworkConfigurationPanel.al1.get(Utilities.SimulationUtilities.no_of_node-NetworkConfigurationPanel.delete_id);
                                          
                                          //System.out.println(m.getCurrentPosition().getXPosition());
                                        
                                         if(((series.getXValue(0).doubleValue()==m.getCurrentPosition().getXPosition() && series.getYValue(0).doubleValue()==m.getCurrentPosition().getYPosition())|| (series.getXValue(1).doubleValue()==m.getCurrentPosition().getXPosition() && series.getYValue(1).doubleValue()==m.getCurrentPosition().getYPosition()))) 
                                        
                                        
                                          renderer.setSeriesPaint(i,Color.white);
                                         
                                        
                                        
                                    }
                                    else
                                           renderer.setSeriesPaint(i,ChartConstants.ALTERNATELINKCOLOR);
                                  }
                                    
        
                                   flag=0;
                                    
                                    
                                    //renderer.setSeriesPaint(i, ChartConstants.ALTERNATELINKCOLOR);
                                    
                                    
                                 
                                    
                                    
					
                                        
                                           
                                        
                                }
                        
                            
                      

			renderer.setToolTipGenerator(new NodeToolTipGenerator(model));
		}
		
	private boolean isWellConnected(XYSeries series)
		{
			NodePosition position1 , position2;
			position1 = new NodePosition(series.getXValue(0).doubleValue(),series.getYValue(0).doubleValue());
			
			//catching the exception assuming an isolated node
			try
			{
				position2 = new NodePosition(series.getXValue(1).doubleValue(),series.getYValue(1).doubleValue());
			}
			catch(Exception e)
			{
				position2 = position1;
			}
			
			return generator.isNodesWellConnected(position1,position2);

		}
		

	private ChartPanel createChartPanel(JFreeChart chart) {
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1300, 425));

		chartPanel.setDisplayToolTips(true);

		return chartPanel;
	}
	
	
	public void saveChart(String ChartOutFileName)
	{
		try
		{
			File file = new File(SimulationConstants.LOGFILEPATH+ChartOutFileName);
			ChartUtilities.saveChartAsPNG(file,chart,1000,400);
			//ChartUtilities.saveChartAsJPEG(file,chart,900,400);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//ChartUtilities ut;
	}

}


