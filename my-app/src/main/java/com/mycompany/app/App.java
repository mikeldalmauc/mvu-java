package com.mycompany.app;


/**
 * 
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        Model model = Model.init();

        View view = new View(model);
        view.view(model);
    }   
}
