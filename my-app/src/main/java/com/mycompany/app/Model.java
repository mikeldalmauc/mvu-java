package com.mycompany.app;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.nio.channels.Channel;

import lombok.Builder;
import lombok.Data;


public class Model {
    
    private PropertyChangeSupport support;

    private Integer count;


    public Model(){
        this.support = new PropertyChangeSupport(this);
        this.count = 0;
    }

    // Getter y Setter para 'count'
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        Integer old = this.count;
        this.count = count;
        
        support.firePropertyChange("count", old, this.count);;
    }


    public void setListener(View view) {
        support.addPropertyChangeListener(view);
    }
    
}
