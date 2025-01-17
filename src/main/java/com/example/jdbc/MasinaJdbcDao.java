package com.example.jdbc;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MasinaJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Masina> findAll() {
        return jdbcTemplate.query("select * from masini", new MasinaMapper());
    }

    public int insert(Masina masina) {
        return jdbcTemplate.update("insert into masini (numar_inmatriculare, marca, anul_fabricatiei, culoare, kilometri) values(?, ?, ?, ?, ?)",
                new Object[]{masina.getNrInmatriculare(), masina.getMarca(), masina.getAnulFabricatiei(), masina.getCuloare(), masina.getNrKm()});
    }

    public int update(Masina masina) {
        return jdbcTemplate.update("update masini set marca = ?, anul_fabricatiei = ?, culoare = ?, kilometri = ? where numar_inmatriculare = ?",
                new Object[]{masina.getMarca(), masina.getAnulFabricatiei(), masina.getCuloare(), masina.getNrKm(), masina.getNrInmatriculare()});
    }

    public int deleteById(String nrInmatriculare) {
        return jdbcTemplate.update("delete from masini where numar_inmatriculare = ?", new Object[]{nrInmatriculare});
    }

    public Masina findById(String nrInmatriculare) {
        return jdbcTemplate.queryForObject("select * from masini where numar_inmatriculare = ?", new MasinaMapper(),nrInmatriculare);
    }

}