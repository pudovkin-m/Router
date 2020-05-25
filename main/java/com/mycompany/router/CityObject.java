package com.mycompany.router;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityObject {
    
    private int id;
    private String name;
    private Double lat;
    private Double lon;
    private String obj_type;
    private String descrirption;
    private Integer price;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityObject)) return false;
        CityObject cityObject = (CityObject) o;
        return name.equals(cityObject.name) && lat.equals(cityObject.lat) 
                && lon.equals(cityObject.lon) && obj_type.equals(cityObject.obj_type) 
                && descrirption.equals(cityObject.descrirption) && price.equals(cityObject.price);
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lat.hashCode();
        result = 31 * result + lon.hashCode();
        result = 31 * result + obj_type.hashCode();
        result = 31 * result + descrirption.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
