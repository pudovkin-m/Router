package com.mycompany.router;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class SelectionObject {

    private String objName;
    private Integer price;

    @Override
    public String toString(){
        return "Object: " + objName + " Price: " + price.toString();
    }
}
