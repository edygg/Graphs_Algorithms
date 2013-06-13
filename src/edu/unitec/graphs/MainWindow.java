/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unitec.graphs;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.unitec.adt.GraphsAlgorithms;
import edu.unitec.adt.ListSet;
import edu.unitec.adt.SLList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author EdilsonFernando
 */
public class MainWindow extends JFrame {

    private JLabel lbl_vertexCount;
    private JLabel lbl_edgesCount;
    private JPanel labelPanel;
    private SLList graphs;
    private XYSeriesCollection dataTable;

    public MainWindow(String string) throws HeadlessException {
        super(string);
        readFilesForm();
    }

    private void readFilesForm() {
        this.setLayout(new BorderLayout());

        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 2));
        lbl_vertexCount = new JLabel("Vertices = 0");
        lbl_edgesCount = new JLabel("Edges = 0");

        lbl_vertexCount.setToolTipText("Vertex count of the current graph");
        lbl_edgesCount.setToolTipText("Edge count of the current graph");

        lbl_vertexCount.setVerticalAlignment(SwingConstants.BOTTOM);
        lbl_edgesCount.setVerticalAlignment(SwingConstants.BOTTOM);
        lbl_vertexCount.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_edgesCount.setHorizontalAlignment(SwingConstants.LEFT);

        labelPanel.add(lbl_vertexCount);
        labelPanel.add(lbl_edgesCount);

        this.add(labelPanel, BorderLayout.SOUTH);
    }

    //NOT IMPLEMENTED YET!!!!!!!!!!!-------------------------------------------
    private void dataAnalisysForm() {
        this.remove(labelPanel);
        this.setLayout(new FlowLayout());
        JFreeChart analisys = ChartFactory.createScatterPlot(
                "Performance Comparison",       //Title
                "Time (ms)",                    //Y Axis
                "Vertices",                     //X Axis
                dataTable,                      //Data series
                PlotOrientation.HORIZONTAL,     //Position
                true,                           //include legend
                true,                           //Tooltips
                false                           //URL
        );
        
        XYPlot plot = (XYPlot) analisys.getPlot();
        XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
        render.setSeriesLinesVisible(0, true); //N-Djikstra Serie
        render.setSeriesLinesVisible(1, true); //Floyd Serie
        plot.setRenderer(render);
        
        ChartPanel chartPanel = new ChartPanel(analisys);
        chartPanel.setSize(new Dimension(600, 600));
        this.setContentPane(chartPanel);
    }
    //-------------------------------------------------------------------------
    public void showStatistics() {
        this.setVisible(false);
        dataAnalisysForm();
        this.setVisible(true);
    }    
    public void prepareStatistics(String directory) {
        this.setVisible(false);
        File graphsFolder = new File(directory);

        boolean folderAlready = true;
        if (!graphsFolder.exists()) {
            folderAlready = graphsFolder.mkdirs();
        }

        if (folderAlready) {
            File files[] = graphsFolder.listFiles();
            graphs = new SLList();
            dataTable = new XYSeriesCollection();
            XYSeries nDjisktraData = new XYSeries("n-Djisktra");
            XYSeries floydData = new XYSeries("Floyd");
            
            for (int i = 0, j = 0; i < files.length; i++) {
                this.setVisible(false);
                DirectedSparseGraph<String, Edge> currentGraph = readGraphFile(files[i]);
                VisualizationImageServer vs = null;
          
                if (currentGraph != null) {
                    graphs.insert(currentGraph);
                    lbl_vertexCount.setText("Vertices = " + currentGraph.getVertexCount());
                    lbl_edgesCount.setText("Edges = " + currentGraph.getEdgeCount());
                    
                    nDjisktraData.add(n_djikstra(currentGraph), currentGraph.getVertexCount());
                    floydData.add(floyd(currentGraph), currentGraph.getVertexCount());
                    
                    CircleLayout layout = new CircleLayout((DirectedSparseGraph<String, Edge>) graphs.get(j));
                    
                    vs = new VisualizationImageServer(
                            layout, new Dimension(600, 600));

                    vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
                    vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

                    this.add(vs, BorderLayout.NORTH);
                    this.pack();
                    this.setVisible(true);
                    JOptionPane.showMessageDialog(this, 
                            "Graph process successful", 
                            "Information", 
                            JOptionPane.INFORMATION_MESSAGE);
                    this.repaint();
                    this.remove(vs);
                    j++;
                }
            }

            dataTable.addSeries(nDjisktraData);
            dataTable.addSeries(floydData);
            if (graphs.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                        "There are not graphs in the default directory", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

        } else {
            System.exit(1);
        }
    }
    
    private long n_djikstra(DirectedSparseGraph<String, Edge> g) {
        Object[] vertices = g.getVertices().toArray();
        long initialTime = System.currentTimeMillis();
        for (int i = 0; i < vertices.length; i++) {
            double[] gc = GraphsAlgorithms.dijkstra(g, (String) vertices[i]);
        }
        long finalTime = System.currentTimeMillis();
        
        return finalTime - initialTime;
    }
    
    private long floyd(DirectedSparseGraph<String, Edge> g) {
        long initialTime = System.currentTimeMillis();
        double [][] gc = GraphsAlgorithms.floyd(g);
        long finalTime = System.currentTimeMillis();
        
        return finalTime - initialTime;
    }
    
    private DirectedSparseGraph<String, Edge> readGraphFile(File file) {
        if (file == null) {
            return null;
        }

        DirectedSparseGraph<String, Edge> graph = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            graph = new DirectedSparseGraph();

            while (line != null) {
                sb.append(line);
                line = br.readLine();

                if (line != null) {
                    sb.append("\n");
                }
            }

            ListSet vertexSet = new ListSet();
            String[] lines = sb.toString().split("\n");
            for (int i = 0; i < lines.length; i++) {
                String[] tokens = lines[i].split(",");

                if (tokens.length != 3) {
                    throw new Exception("Parsing File Error");
                }

                if (!vertexSet.contains(tokens[0])) {
                    graph.addVertex(tokens[0]);
                    vertexSet.addElement(tokens[0]);
                }
                if (!vertexSet.contains(tokens[1])) {
                    graph.addVertex(tokens[1]);
                    vertexSet.addElement(tokens[1]);
                }
                Pair<String> pair = new Pair(tokens[0], tokens[1]);
                
                graph.addEdge(new Edge(Double.parseDouble(tokens[2])), pair);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, 
                    "File not exists", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                    "Read error", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return graph;
    }
}
