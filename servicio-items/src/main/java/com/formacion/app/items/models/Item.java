package com.formacion.app.items.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Item {

    private Producto producto;
    private Integer Cantidad;    

    public Double getTotal(){
        return producto.getPrecio()*Cantidad.doubleValue();
    }
}
