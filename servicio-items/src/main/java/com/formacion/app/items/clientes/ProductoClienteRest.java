package com.formacion.app.items.clientes;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacion.app.items.models.Producto;



@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {


    @GetMapping("/listar")
    public List<Producto> findAll();    
    
    @GetMapping(value="/buscar/{id}")
    public Producto findById(@PathVariable Long id);
}
