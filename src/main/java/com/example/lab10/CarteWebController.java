package com.example.lab10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarteWebController {

    @Autowired
    private CarteRepository repository;

    @GetMapping("/lista-carti")
    public String getListaCarti(Model model) {
        model.addAttribute("lista", repository.findAll());
        return "carti";
    }

    @PostMapping("/operatii")
    public String handleForm(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String titlul,
            @RequestParam(required = false) String autorul,
            @RequestParam(required = false) String adauga,
            @RequestParam(required = false) String sterge,
            @RequestParam(required = false) String filtreaza,
            @RequestParam(required = false) String modifica,
            Model model) {

        String mesaj = "";

        if (adauga != null) {
            if (isbn.isEmpty() || titlul.isEmpty() || autorul.isEmpty()) {
                mesaj = "Adăugarea nu se poate realiza fără toate câmpurile completate!";
            } else {
                repository.save(new Carte(isbn, titlul, autorul));
                mesaj = "Adăugare realizată cu succes!";
            }
        } else if (sterge != null) {
            Carte carte = repository.findAll().stream()
                    .filter(c -> c.getIsbn().equals(isbn))
                    .findFirst()
                    .orElse(null);
            if (carte == null) {
                mesaj = "Nu există nicio carte cu ISBN-ul " + isbn;
            } else {
                repository.delete(carte);
                mesaj = "Cartea cu ISBN-ul " + isbn + " a fost ștearsă!";
            }
        } else if (filtreaza != null) {
            if (autorul == null || autorul.isEmpty()) {
                mesaj = "Afișarea tuturor cărților!";
                model.addAttribute("lista", repository.findAll());
            } else {
                var cartiFiltrate = repository.findAll().stream()
                        .filter(c -> c.getAutorul().equalsIgnoreCase(autorul))
                        .toList();
                if (cartiFiltrate.isEmpty()) {
                    mesaj = "Nu există cărți pentru autorul " + autorul + ".";
                } else {
                    mesaj = "Cărțile următoare aparțin autorului " + autorul + ":";
                    model.addAttribute("lista", cartiFiltrate);
                }
            }
        } else if (modifica != null) {
            Carte carte = repository.findAll().stream()
                    .filter(c -> c.getIsbn().equals(isbn))
                    .findFirst()
                    .orElse(null);
            if (carte == null) {
                mesaj = "Nu se găsește nicio carte cu ISBN-ul " + isbn;
            } else {
                if (!titlul.isEmpty()) {
                    carte.setTitlul(titlul);
                }
                if (!autorul.isEmpty()) {
                    carte.setAutorul(autorul);
                }
                repository.save(carte);
                mesaj = "Cartea cu ISBN-ul " + isbn + " a fost modificată!";
            }
        }

       
        model.addAttribute("mesaj", mesaj);
        return "carti";
    }
}