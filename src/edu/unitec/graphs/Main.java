package edu.unitec.graphs;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.unitec.adt.SLList;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author EdilsonFernando
 */
public class Main {

    private static final String GRAPHS_DIRECTORY = "./GraphsFiles/";
    private static final String APP_NAME = "EFGM Graphs Algorithms";

    private static DirectedSparseGraph<Vertex, Edge> readGraphFile(File file, JFrame window) {
        if (file == null) {
            return null;
        }

        DirectedSparseGraph<Vertex, Edge> graph = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            graph = new DirectedSparseGraph();

            while(line != null) {
                sb.append(line);
                line = br.readLine();
                
                if (line != null) {
                    sb.append("\n");
                }
            }
            
            String[] lines = sb.toString().split("\n");
            
            for (int i = 0; i < lines.length; i++) {
                
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(window, "File not exists", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(window, "Read error", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return graph;
    }

    public static void main(String args[]) {
        File graphsFolder = new File(GRAPHS_DIRECTORY);

        boolean folderAlready = true;
        if (!graphsFolder.exists()) {
            folderAlready = graphsFolder.mkdirs();
        }

        JFrame mainWindow = new JFrame(APP_NAME);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainWindow.setLocationRelativeTo(null);

        if (folderAlready) {
            File files[] = graphsFolder.listFiles();
            SLList graphs = new SLList();



            for (int i = 0; i < files.length; i++) {
                DirectedSparseGraph<Vertex, Edge> currentGraph = readGraphFile(files[i], mainWindow);

                if (currentGraph != null) {
                    graphs.insert(currentGraph);
                }
            }

            if (!graphs.isEmpty()) {
                CircleLayout layout = new CircleLayout((DirectedSparseGraph<Vertex, Edge>) graphs.get(0));

                VisualizationImageServer vs = new VisualizationImageServer(
                        layout, new Dimension(600, 600));

                vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
                vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
                
                mainWindow.add(vs);
                mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainWindow.pack();
                mainWindow.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(mainWindow, "There are not graphs in the default directory", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

        } else {
            System.exit(1);
        }
    }
}
