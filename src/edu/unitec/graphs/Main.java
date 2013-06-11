package edu.unitec.graphs;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author EdilsonFernando
 */
public class Main {

    private static final String GRAPHS_DIRECTORY = "./GraphsFiles/";
    private static final String APP_NAME = "EFGM Graphs Algorithms";

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        MainWindow m = new MainWindow(APP_NAME);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.pack();
        m.generateStatistics(GRAPHS_DIRECTORY);
    }
}
