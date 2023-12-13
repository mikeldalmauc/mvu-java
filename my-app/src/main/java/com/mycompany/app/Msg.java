package com.mycompany.app;

/**
 * Enum with messages of the application, this 
 */
public enum Msg {

    UP(1),
    DOWN(2);

    private int value;
    
    private Msg(int value){
        this.value = value;
    }
}
