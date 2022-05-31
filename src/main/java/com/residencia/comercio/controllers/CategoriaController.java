package com.residencia.comercio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.comercio.dtos.CategoriaDTO;
import com.residencia.comercio.entities.Categoria;
import com.residencia.comercio.exceptions.NoSuchElementFoundException;
import com.residencia.comercio.services.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	@Operation(summary = "Mostrar todas as categorias")
	public ResponseEntity<List<Categoria>> findAllCategoria() {
		List<Categoria> categoriaList = categoriaService.findAllCategoria();
		return new ResponseEntity<>(categoriaList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Encontrar uma categoria com um ID específico")
	public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findCategoriaById(id);
		if (null == categoria)
			throw new NoSuchElementFoundException("Não foi encontrado Categoria com o id " + id);
		else
			return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Salvar uma categoria")
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		Categoria novoCategoria = categoriaService.saveCategoria(categoria);
		return new ResponseEntity<>(novoCategoria, HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Atualizar uma categoria")
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria) {
		Categoria novoCategoria = categoriaService.updateCategoria(categoria);
		return new ResponseEntity<>(novoCategoria, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar uma categoria com um ID específico")
	public ResponseEntity<String> deleteCategoria(@PathVariable Integer id) {
		if (null == categoriaService.findCategoriaById(id))
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);

		categoriaService.deleteCategoria(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	// DTO 
	
	@PostMapping("/dto")
	@Operation(summary = "salvar uma categoria no padrão DTO")
	public ResponseEntity<Categoria> saveCategoriaDTO(@RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaService.saveCategoriaDTO(categoriaDTO);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED);
	}

}
