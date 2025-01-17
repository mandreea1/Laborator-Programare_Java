package com.example.repository;

import java.util.List;
import com.example.entity.Masina;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class MasinaJpaRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Masina> findAll(){
        TypedQuery<Masina> query=entityManager.createQuery("from Masina",Masina.class);
        return query.getResultList();
    }
    public Masina findById(String id){
        return entityManager.find(Masina.class, id);
    }
    public void deleteById(String id){
        Masina masina=findById(id);
        entityManager.remove(masina);
    }
    public Masina insert(Masina masina){
        return entityManager.merge(masina);
    }
    public Masina update(Masina masina){
        return entityManager.merge(masina);
    }


}