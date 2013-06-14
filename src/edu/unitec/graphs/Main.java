
package edu.unitec.graphs;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Clase principal que controla el flujo del programa y el aspecto del programa.
 * @author EdilsonFernando
 */
public class Main {

    /**
     * Directorio principal donde se guardan los archivos con los grafos.
     */
    private static final String GRAPHS_DIRECTORY = "./GraphsFiles/";
    
    /**
     * Nombre de la aplicación.
     */
    private static final String APP_NAME = "EFGM Graphs Algorithms";

    /**
     * Método principal de ejecución del programa.
     * @param args Parámetros de la consola.
     */
    public static void main(String args[]) {
        //Cambiando el aspecto de la ventana para que coincida con la del
        //sistema operativo.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Creando una instancia de la ventana con su respectivo nombre.
        MainWindow m = new MainWindow(APP_NAME);
        //Operación por defecto de cierre.
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Acomodando la ventana a los componentes.
        m.pack();
        //Preparando las estadísticas a partir de la lectura de los archivos
        //con lo grafos. Se envía la carpeta a donde se encuentran.
        m.prepareStatistics(GRAPHS_DIRECTORY);
        //Mostrando las estadísticas procesadas.
        m.showStatistics();
    }
}
