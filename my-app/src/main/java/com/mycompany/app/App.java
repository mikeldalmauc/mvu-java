package com.mycompany.app;

import javax.swing.SwingUtilities;

/**
 * 
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        
        Model model = new Model();
        View view = new View(model);
        model.setListener(view);
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.getJFrame().setVisible(true);
            }
        });
    }   
}
