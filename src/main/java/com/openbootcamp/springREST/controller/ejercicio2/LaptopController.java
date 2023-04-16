package com.openbootcamp.springREST.controller.ejercicio2;

import com.openbootcamp.springREST.entities.Laptop;
import com.openbootcamp.springREST.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if (laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //No funciona corregir
    @PostMapping("/api/laptops")
    public ResponseEntity<String> create(@RequestBody Laptop laptop) {
        if(laptop.getId() != null) {
            return ResponseEntity.badRequest().body("No se guardo correctamento el laptop, no debe ingresarse un id.");
        }else{
            laptopRepository.save(laptop);
            return ResponseEntity.ok("La laptop se creo correctamente.");
        }
    }

    //No funciona corregir
    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }

        if (!laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if (!laptopRepository.existsById(id)){
            System.out.println("Elemento no exite para borrar");
            return ResponseEntity.notFound().build();
        }
        String modelo = laptopRepository.findById(id).get().getModelo();
        laptopRepository.deleteById(id);
        return ResponseEntity.ok("La laptop" + modelo + " se elimino correctamente.");
    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity<String> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.ok("Todas las laptops se eliminaron correctamente.");
    }
}
