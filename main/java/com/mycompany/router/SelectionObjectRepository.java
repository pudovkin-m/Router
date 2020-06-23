package com.mycompany.router;

import java.util.Comparator;
import java.util.List;

public interface SelectionObjectRepository<Entity extends SelectionObject> {
    
    List<Entity> getSelectionPriceLess(Integer threshold, Comparator<Entity> comp);

}
