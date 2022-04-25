package com.formacion.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacion.app.productos.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto,Long>{
    
}
