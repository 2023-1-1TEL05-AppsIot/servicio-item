package com.example.servicioitem.controller;

import com.example.servicioitem.model.dao.HeroesFeign;
import com.example.servicioitem.model.dao.ProductFeign;
import com.example.servicioitem.model.dao.ProductoDao;
import com.example.servicioitem.model.entity.Item;
import com.example.servicioitem.model.entity.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ProductoDao productoDao;

    @Autowired
    ProductFeign productFeign;

    @Autowired
    HeroesFeign heroesFeign;

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping("/listar")
    public List<Item> listarItems() {
        List<Item> lista = new ArrayList<>();
        for (Product product : productoDao.listar()) {
            Item item = new Item();
            item.setProduct(product);
            item.setCantidad(Math.round(Math.random() * 10));
            lista.add(item);
        }
        return lista;
    }

    @GetMapping("/listarF")
    public List<Item> listarItemsConFeign() {
        List<Item> lista = new ArrayList<>();
        for (Product product : productFeign.listarProductos()) {
            Item item = new Item();
            item.setProduct(product);
            item.setCantidad(Math.round(Math.random() * 10));
            lista.add(item);
        }
        return lista;
    }

    @GetMapping("/buscar/{id}")
    public Item buscarItemPorId(@PathVariable int id) {
        return circuitBreakerFactory.create("items")
                .run(() -> {
                    Product product = productFeign.buscarProductPorId(id);
                    Item item = new Item();
                    item.setCantidad(Math.round(Math.random() * 10));
                    item.setProduct(product);
                    return item;
                }, throwable -> metodoAlternativo());
    }

    public Item metodoAlternativo(){
        HashMap<String, String> hola = heroesFeign.hola();
        Item item = new Item();
        item.setCantidad(Math.round(Math.random() * 10));
        Product product = new Product();
        product.setProductname(hola.get("nombre"));
        item.setProduct(product);
        return item;
    }

    @CircuitBreaker(name = "items2",fallbackMethod = "metodoAlternativo" )
    @GetMapping("/buscar2/{id}")
    public Item buscarItemPorId2(@PathVariable int id) {
        Product product = productFeign.buscarProductPorId(id);
        Item item = new Item();
        item.setCantidad(Math.round(Math.random() * 10));
        item.setProduct(product);
        return item;
    }

    @GetMapping("/listarHeroes")
    public ResponseEntity<HashMap<String, String>> holaDesdeNode() {
        HashMap<String, String> data = heroesFeign.hola();
        return ResponseEntity.ok(data);
    }
}
