package com.formacion.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.app.productos.models.entity.Producto;
import com.formacion.app.productos.models.service.IProductoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ProductoController {

    @Autowired
    private Environment env;

    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/listar")
    public List<Producto> listar() {
        
       return iProductoService.findAll().stream().map(producto -> {
        producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return producto;
       }).collect(Collectors.toList());
         
    }

    @GetMapping("/buscar/{id}")
    public Producto buscar(@PathVariable Long id) {

        Producto producto = iProductoService.findById(id);
        producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        log.info("Busqueda productos");
        return producto;
    }

}

