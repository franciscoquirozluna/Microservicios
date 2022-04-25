package com.formacion.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.formacion.app.items.clientes.ProductoClienteRest;
import com.formacion.app.items.models.Item;

/*Como existe 2 clases implementando ItemService, debemos indicar que implementacion usaremos en el
controller. Existen 2 formas: una es usar la anotacion Primary y la otra es usar un Qualifier en la
inyeccion del bean en el controller*/

//@Service("serviceFeign")
@Primary
@Service
public class ItemServiceFeign implements ItemService{

    @Autowired
    private ProductoClienteRest clientefeign;

    @Override
    public List<Item> findAll() {        
        return clientefeign.findAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {        
        return new Item(clientefeign.findById(id),cantidad);
    }
}
