package com.mycompany.app;

public class Update{

    /**
     * All changes on model should be performed by this function
     * 
     * @param msg
     * @param model
     */
    public static void update(Msg msg, Model model){

        switch (msg) {
            case UP:
                model.setCount(model.getCount() + 1);
                break;
                
            case DOWN:
                model.setCount(model.getCount() - 1);
                break;
            
            default:
                break;
        }

    }
    
}
