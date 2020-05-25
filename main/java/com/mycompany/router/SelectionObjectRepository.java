package com.mycompany.router;

import java.util.Comparator;
import java.util.List;

public interface SelectionObjectRepository<Entity extends SelectionObject> {
    
    List<Entity> getSelectionPriceLess(Integer threshold, Comparator<Entity> comp);

    default String wcardTest(final int models) {
        if (models == 0) {
            return "";
        } else if (models == 1) {
            return "AND m.name = (?) ";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(" AND (m.name = (?) ");
            for (int i = 0; i < models - 1; i++) {
                sb.append(" OR m.name = (?) ");
            }
            sb.append(") ");
            return sb.toString();
        }
    }
}
