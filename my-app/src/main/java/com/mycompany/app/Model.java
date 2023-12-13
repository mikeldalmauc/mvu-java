package com.mycompany.app;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model {
    
    /*
     * Support to add change listeners 
     */
    private PropertyChangeSupport support;

    public void setListener(PropertyChangeListener view) {
        support.addPropertyChangeListener(view);
    }

    /**
     *  Model data should go here
     */
    private Integer count;


    public Model(){
        this.support = new PropertyChangeSupport(this);

        /**
         * Model initial data
         */
        this.count = 0;
    }


    public Integer getCount() {
        return count;
    }

    /*
     * This can be improved and be made with a tag
     */
    public void setCount(Integer count) {
        Integer old = this.count;
        this.count = count;

        support.firePropertyChange("count", old, this.count);;
    }

    
}
