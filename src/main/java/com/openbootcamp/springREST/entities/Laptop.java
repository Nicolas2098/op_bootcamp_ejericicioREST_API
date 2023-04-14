package com.openbootcamp.springREST.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "laptop")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String disco;
    private Integer ram;
    private String pantalla;


    public Laptop() {
    }

    public Laptop(Long id, String marca, String modelo, String disco, Integer ram, String pantalla) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.disco = disco;
        this.ram = ram;
        this.pantalla = pantalla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }
}
