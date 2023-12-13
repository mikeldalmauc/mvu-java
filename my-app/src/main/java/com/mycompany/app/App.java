package com.mycompany.app;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 *
 */
public class App 
{

    public static void main( String[] args ){
        counterProgram();
    }   

    public static void counterProgram(){

        Model model = new Model();
        View view = new View(model);
        model.setListener(view);

        SwingUtilities.invokeLater(() -> {
            view.getJframe().setVisible(true);
            view.getJframe().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
    }

}
