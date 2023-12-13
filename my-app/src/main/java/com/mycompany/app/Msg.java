package com.mycompany.app;


public enum Msg {

    UP(1),
    DOWN(2);

    private int value;

    private Msg(int value){
        this.value = value;
    }
}
