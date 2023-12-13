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
import lombok.Data;

@Data
public class View implements PropertyChangeListener{
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.view((Model) evt.getSource());
    }
    
    /**
     * View
     */
    private JFrame jframe;
    private JTextField count;

    public View(Model model){

        jframe = new JFrame("Counter");
        jframe.setSize(260, 120);
        jframe.setLocationRelativeTo(null);

        // Configurar el layout
        jframe.setLayout(new BoxLayout(jframe.getContentPane(), BoxLayout.Y_AXIS));

        // Crear componentes
        count = new JTextField(1);
        count.setEditable(false);
        count.setText(model.getCount()+"");
        jframe.add("count", count);

        // Boton up
        JButton boton1 = new JButton("up");
        jframe.add("up", boton1);
        boton1.addActionListener(e -> Update.update(Msg.UP, model));

        // Boton down
        JButton boton2 = new JButton("down");
        jframe.add("down", boton2);
        boton2.addActionListener(e -> Update.update(Msg.DOWN, model));
    }

    /**
     * View function
     * @param model
     */
    public void view(Model model){
        count.setText(model.getCount()+"");
    }
    
}

