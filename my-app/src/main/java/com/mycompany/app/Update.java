package com.mycompany.app;

public class Update{
    
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
