package com.example.servicioitem.model.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@FeignClient(name = "SERVICIO-HEROES")
public interface HeroesFeign {

    @GetMapping("/hola")
    HashMap<String,String> hola();
}
