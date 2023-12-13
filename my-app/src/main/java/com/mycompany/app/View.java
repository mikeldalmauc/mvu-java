package com.mycompany.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class View implements PropertyChangeListener{
    
    private JFrame jframe;
    private JTextField count;
    
    public View(Model model){

        model.setView(this);

        jframe = new JFrame("Counter");
        jframe.setSize(300, 300);

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);

        // Crear componentes
        // Aqui se usa el modelo!
        count = new JTextField(3);
        count.setText(model.getCount()+"");
        JButton boton1 = new JButton("up");
        JButton boton2 = new JButton("down");

        // Configurar el layout
        jframe.setLayout(new BoxLayout(jframe.getContentPane(), BoxLayout.Y_AXIS));

        // Agregar componentes al frame
        jframe.add("count", count);
        jframe.add("up", boton1);
        jframe.add("down", boton2);

        // updates
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Update.update(Msg.UP, model);
            }
        });

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update.update(Msg.DOWN, model);
            }
        });
    }

   
    
    public void view(Model model){
        count.setText(model.getCount()+"");
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.view((Model) evt.getSource());
    }
    
}

