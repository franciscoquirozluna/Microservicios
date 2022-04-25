package com.formacion.app.items.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.app.items.models.Item;
import com.formacion.app.items.models.Producto;
import com.formacion.app.items.models.service.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
public class ItemController {
   
    //@Qualifier("serviceFeign")
    @Autowired
    private ItemService itemService;

    @GetMapping("/listar")
    @CircuitBreaker(name="listaNoDisponible",fallbackMethod="getListarCB")
    public List<Item> listar(){
        return itemService.findAll();
    }


    private List<Item> getListarCB(RuntimeException e){

        Producto producto = new Producto();

        producto.setId(0L);
        producto.setNombre("No Disponible");
        producto.setPrecio(0.00);
        producto.setPort(0);
        producto.setCreado(new Date());

        List<Item> items = new ArrayList<>();
        items.add(new Item(producto,0));
        return items;
        //return new ResponseEntity("La lista de productos no esta disponible", HttpStatus.OK);
    }

    

    @GetMapping(value="/buscar/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.findById(id, cantidad);
    }
    
}
