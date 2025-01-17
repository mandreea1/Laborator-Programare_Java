package com.example.lab11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evenimente")
public class EvenimenteController {
    @Autowired
    private EvenimentRepository repository;

    // 1. Afișarea tuturor evenimentelor
    @GetMapping
    public List<Eveniment> getAllEvenimente() {
        return repository.findAll();
    }

    // 2. Afișarea informațiilor despre un eveniment după ID
    @GetMapping("/{id}")
    public Optional<Eveniment> getEvenimentById(@PathVariable Long id) {
        return repository.findById(id);
    }

    // 3. Afișarea informațiilor despre evenimentele care au loc într-o anumită locație
    @GetMapping("/locatie/{locatie}")
    public List<Eveniment> getEvenimenteByLocatie(@PathVariable String locatie) {
        return repository.findAll().stream()
                .filter(e -> e.getLocatie().equalsIgnoreCase(locatie))
                .collect(Collectors.toList());
    }

    // 4. Afișarea informațiilor despre evenimentele care au loc într-o dată specificată
    @GetMapping("/data/{data}")
    public List<Eveniment> getEvenimenteByData(@PathVariable String data) {
        return repository.findAll().stream()
                .filter(e -> e.getData().equalsIgnoreCase(data))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> createEveniment(@RequestBody Eveniment eveniment) {
        repository.save(eveniment);
        return ResponseEntity.ok("Eveniment adăugat cu succes!");
    }

    // 6. Actualizare eveniment
    @PutMapping("/{id}")
    public Eveniment updateEveniment(@PathVariable Long id, @RequestBody Eveniment eveniment) {
        return repository.findById(id)
                .map(existing -> {
                    if (eveniment.getNume() != null) {
                        existing.setNume(eveniment.getNume());
                    }
                    if (eveniment.getData() != null) {
                        existing.setData(eveniment.getData());
                    }
                    if (eveniment.getLocatie() != null) {
                        existing.setLocatie(eveniment.getLocatie());
                    }
                    return repository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Evenimentul nu există!"));
    }
    // 7. Ștergere eveniment identificat prin ID
    @DeleteMapping("/{id}")
    public void deleteEveniment(@PathVariable Long id) {
        repository.deleteById(id);
    }
}