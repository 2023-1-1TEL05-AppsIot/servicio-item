package com.example.servicioitem.model.dao;

import com.example.servicioitem.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductoDao {

    @Autowired
    RestTemplate clienteRest;

    public List<Product> listar() {
        String url = "http://servicio-producto/listar";
        Product[] arreglo = clienteRest.getForObject(url, Product[].class);
        return Arrays.asList(arreglo);
    }
}
