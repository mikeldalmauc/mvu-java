package com.mycompany.app;

import java.io.IOException;
import java.nio.channels.Channel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Model {
    
    private Integer count;

    private View view;

    public static Model init(){

        return  Model.builder()
                    .count(0)
                    .build();

    }

    

}
