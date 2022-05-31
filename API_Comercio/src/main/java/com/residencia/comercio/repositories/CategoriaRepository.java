package com.residencia.comercio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.residencia.comercio.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

	Boolean existsByNomeCategoria(String nomeCategoria);
}