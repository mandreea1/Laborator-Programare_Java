package com.example.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.entity.Masina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MasinaSpringDataJpaRepository extends JpaRepository<Masina, String>{

   List<Masina> findByMarca(String marca);
   List<Masina> findByKilometriIsBefore(int nrkm);
    List<Masina> findByAnulFabricatieiIsAfter(int anulFabricatiei);
}