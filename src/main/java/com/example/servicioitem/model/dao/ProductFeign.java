package com.example.servicioitem.model.dao;

import com.example.servicioitem.model.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "servicio-producto")
public interface ProductFeign {

    @GetMapping("/listar")
    List<Product> listarProductos();

    @GetMapping("/obtener/{id}")
    Product buscarProductPorId(@PathVariable int id);
}
