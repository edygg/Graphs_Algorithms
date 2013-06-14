
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
 * Ventana principal que procesa y muestra los resultados para cada grafo.
 * @author EdilsonFernando
 */
public class MainWindow extends JFrame {

    /**
     * Cuenta de los vértices.
     */
    private JLabel lbl_vertexCount;
    /**
     * Cuenta de las aristas.
     */
    private JLabel lbl_edgesCount;
    /**
     * Panel para acomodar los labels con la cuentas de las aristas y los 
     * vértices.
     */
    private JPanel labelPanel;
    /**
     * Lista con los grafos analizados
     */
    private SLList graphs;
    /**
     * Colección de series con los datos extraídos a partir de los resultados.
     */
    private XYSeriesCollection dataTable;

    /**
     * Constructor por defecto que recibe el nombre de la aplicación. Prepara 
     * la ventana para la visualizar los grafos procesados.
     * @param string Nombre de la aplicación
     * @throws HeadlessException 
     */
    public MainWindow(String string) throws HeadlessException {
        super(string);
        readFilesForm();
    }
    
    /**
     * Prepara la ventana para su forma de visualización de grafos.
     */
    private void readFilesForm() {
        //Nuevo layout
        this.setLayout(new BorderLayout());
        
        //Prepara el panel de los labels para las cuentas de aristas y vértices
        labelPanel = new JPanel();
        //Layout para el nuevo panel de labels
        labelPanel.setLayout(new GridLayout(1, 2));
        //Asigna la cuenta de vértices en cero
        lbl_vertexCount = new JLabel("Vertices = 0");
        //Asigna la cuenta de aristas en cero
        lbl_edgesCount = new JLabel("Edges = 0");

        //Agrega ayudas visuales para cada arista
        lbl_vertexCount.setToolTipText("Vertex count of the current graph");
        lbl_edgesCount.setToolTipText("Edge count of the current graph");
        
        //Orienta los labels 
        lbl_vertexCount.setVerticalAlignment(SwingConstants.BOTTOM);
        lbl_edgesCount.setVerticalAlignment(SwingConstants.BOTTOM);
        lbl_vertexCount.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_edgesCount.setHorizontalAlignment(SwingConstants.LEFT);

        //Agregando los labels al panel
        labelPanel.add(lbl_vertexCount);
        labelPanel.add(lbl_edgesCount);
        
        //Agregando el panel a la ventana
        this.add(labelPanel, BorderLayout.SOUTH);
    }

    /**
     * Prepara la ventana para mostrar los datos analizados. Muestra gráficas
     * y recoge los datos desde la colección de series
     * @see MainWindow#dataTable
     */
    private void dataAnalisysForm() {
        //Remueve el panel
        this.remove(labelPanel);
        //Cambia el layout de ventana
        this.setLayout(new FlowLayout());
        //Crea el gráfico de puntos 
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
        
        //Cambia el modo de render de la gráfica
        XYPlot plot = (XYPlot) analisys.getPlot();
        //Render que dibuja la línea de tendencia
        XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
        render.setSeriesLinesVisible(0, true); //N-Djikstra Serie
        render.setSeriesLinesVisible(1, true); //Floyd Serie
        plot.setRenderer(render);
        //Crea un panel para la gráfica
        ChartPanel chartPanel = new ChartPanel(analisys);
        //Dimensiones de la gráfica
        chartPanel.setSize(new Dimension(600, 600));
        //Agrega el panel a la ventana
        this.setContentPane(chartPanel);
    }
    
    /**
     * Mostrando estadísticas en la ventana principal.
     */
    public void showStatistics() {
        //Ocualtar la ventana
        this.setVisible(false);
        //Preparar la ventana para mostrar las gráficas
        dataAnalisysForm();
        //Mostrar la ventana
        this.setVisible(true);
    }    
    
    /**
     * Prepara las estadísticas de la ventana para ser recogidas dentro de la 
     * colección de series que es propiedad de esta clase.
     * @see MainWindow#dataTable
     * @param directory Directorio principal donde se encuentran los grafos
     */
    public void prepareStatistics(String directory) {
        //Oculta la ventana
        this.setVisible(false);
        //Crea un archivo con el directorio especificado
        File graphsFolder = new File(directory);

        boolean folderAlready = true;
        //Creando el folder si no existe
        if (!graphsFolder.exists()) {
            folderAlready = graphsFolder.mkdirs();
        }

        //Si el directorio está listo
        if (folderAlready) {
            //Arreglo con los archivos que contiene el directorio principal
            File files[] = graphsFolder.listFiles();
            //Lista de grafos
            graphs = new SLList();
            //Inicializa la colección de series
            dataTable = new XYSeriesCollection();
            
            //Series que almacenan los datos
            XYSeries nDjisktraData = new XYSeries("n-Djisktra");
            XYSeries floydData = new XYSeries("Floyd");
            
            //Ciclo que prepara los datos archivos por archivo
            for (int i = 0, j = 0; i < files.length; i++) {
                //Oculta la ventana
                this.setVisible(false);
                //Recoge el grafo ya realizado y leido desde el archivo
                DirectedSparseGraph<String, Edge> currentGraph = readGraphFile(files[i]);
                //Servidor de imágenes
                VisualizationImageServer vs = null;
          
                //Mientras el grafo leido tenga datos
                if (currentGraph != null) {
                    //Inseta el grafo a la lista
                    graphs.insert(currentGraph);
                    
                    //Agrega los contadores de vértices y aristas
                    lbl_vertexCount.setText("Vertices = " + currentGraph.getVertexCount());
                    lbl_edgesCount.setText("Edges = " + currentGraph.getEdgeCount());
                    //Agrega los datos extraidos de tiempo y la cuenta de vértices
                    nDjisktraData.add(n_djikstra(currentGraph), currentGraph.getVertexCount());
                    floydData.add(floyd(currentGraph), currentGraph.getVertexCount());
                    
                    //Layout de círculo para mostrar el grafo
                    CircleLayout layout = new CircleLayout((DirectedSparseGraph<String, Edge>) graphs.get(j));
                    
                    //Servidor para visualizar el grafo en pantalla
                    vs = new VisualizationImageServer(
                            layout, new Dimension(600, 600));

                    //Cambia el render para visualizar los datos del vértices en pantalla
                    vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
                    vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

                    //Prepara la ventana para la visualización
                    this.add(vs, BorderLayout.NORTH);
                    this.pack();
                    //Mostrando las ventana
                    this.setVisible(true);
                    //Procesado 
                    JOptionPane.showMessageDialog(this, 
                            "Graph process successful", 
                            "Information", 
                            JOptionPane.INFORMATION_MESSAGE);
                    //Repintando la ventanna
                    this.repaint();
                    this.remove(vs);
                    j++;
                }
            }

            //Añade las series a la colección
            dataTable.addSeries(nDjisktraData);
            dataTable.addSeries(floydData);
            //Si la lista de grafos está vacía no hay nada que procesar
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
    
    /**
     * Método que corre n veces Djikstra y retorna el tiempo que tarda en correr
     * dicho algoritmo.
     * @param g Grafo a procesar
     * @return Retorna un <code>long</code> con el tiempo en milisegundos que se
     * tardó el proceso.
     */
    private long n_djikstra(DirectedSparseGraph<String, Edge> g) {
        Object[] vertices = g.getVertices().toArray();
        long initialTime = System.currentTimeMillis();
        for (int i = 0; i < vertices.length; i++) {
            double[] gc = GraphsAlgorithms.dijkstra(g, (String) vertices[i]);
        }
        long finalTime = System.currentTimeMillis();
        
        return finalTime - initialTime;
    }
    
    /**
     * Método que corre Floyd dentro de un grafo especificado y retorna el
     * tiempo que se tardó en procesar el algoritmo.
     * @param g Grafo a procesar
     * @return Retorna un <code>long</code> con el tiempo en milisegundos que 
     * se tardó el proceso.
     */
    private long floyd(DirectedSparseGraph<String, Edge> g) {
        long initialTime = System.currentTimeMillis();
        double [][] gc = GraphsAlgorithms.floyd(g);
        long finalTime = System.currentTimeMillis();
        
        return finalTime - initialTime;
    }
    
    /**
     * Método que lee un archivo con relaciones para convertirlo en un grafo
     * dirigido utilizable dentro de los procesos del programa.
     * @param file Archivo que contiene las relaciones.
     * @return Retorna un grafo dirigido con las relaciones especificadas en
     * el archivo.
     */
    private DirectedSparseGraph<String, Edge> readGraphFile(File file) {
        //Si el archivo no es válido retorna un grafo vacío
        if (file == null) {
            return null;
        }

        //Creando el grafo
        DirectedSparseGraph<String, Edge> graph = null;

        try {
            //Crea el lector y todo lo necesario para leer el archivo
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            graph = new DirectedSparseGraph();

            //Si la línea es nula para la lectura
            while (line != null) {
                sb.append(line);
                line = br.readLine();

                //Agrega nuevas líneas si es necesario
                if (line != null) {
                    sb.append("\n");
                }
            }

            //Crea un conjunto de vértices
            ListSet vertexSet = new ListSet();
            //Corta las relaciones una a una
            String[] lines = sb.toString().split("\n");
            for (int i = 0; i < lines.length; i++) {
                //Verifica vertices y peso entre éstos
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
