package com.example.eksamen24timersejlads2.controller;

import com.example.eksamen24timersejlads2.model.Sejlbod;
import com.example.eksamen24timersejlads2.repositories.SejlBodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SejlbodRestController {
    @Autowired
    private SejlBodRepository sejlBodRepository;

    @GetMapping("/sejlbode")
    public List<Sejlbod> getAllSejlbod() {
        return sejlBodRepository.findAll();
    }

    @PostMapping("/opretsejlbod")
    public ResponseEntity<String> opretSejlbod(@RequestBody Sejlbod sejlbod) {
        sejlBodRepository.save(sejlbod);
        return ResponseEntity.ok("Du har oprettet en sejlbod");
    }

    @GetMapping("/{id}")
    public Sejlbod hentSejlbod(@PathVariable Long id) {
        return sejlBodRepository.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public Sejlbod opdaterSejlbod(@PathVariable Long id, @RequestBody Sejlbod sejlbod) {
        if (sejlBodRepository.existsById(id)) {
            sejlbod.setId(id);
            return sejlBodRepository.save(sejlbod);
        }
        return null;
    }

    @DeleteMapping("/deletebod/{id}")
    public ResponseEntity<String> sletSejlbod(@PathVariable Long id) {
        sejlBodRepository.deleteById(id);
        return ResponseEntity.ok("Du har slettet sejlboden med ID nummer = " + id);
    }

    @PutMapping("/addpoints/{id}")
    public Sejlbod addPointsToSejlbod(@PathVariable Long id, @RequestParam int points) {
        Sejlbod sejlbod = sejlBodRepository.findById(id).orElse(null);

        if (sejlbod != null) {
            sejlbod.setPoints(sejlbod.getPoints() + points);
            sejlBodRepository.save(sejlbod);
        }

        return sejlbod;
    }

    @GetMapping("/totalwinner")
    public Sejlbod calculateTotalWinner() {
        List<Sejlbod> allSejlbods = sejlBodRepository.findAll();

        // Beregn den samlede vinder (båd med laveste point)
        Sejlbod totalWinner = null;
        int minPoints = Integer.MAX_VALUE;

        for (Sejlbod sejlbod : allSejlbods) {
            if (sejlbod.getPoints() < minPoints) {
                totalWinner = sejlbod;
                minPoints = sejlbod.getPoints();
            }
        }

        return totalWinner;
    }

}


