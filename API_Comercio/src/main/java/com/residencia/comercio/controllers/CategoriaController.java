package com.residencia.comercio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	// Arquivo
	
	@PostMapping(value = "/com-foto", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE
	})
	public ResponseEntity<Categoria> saveCategoriaComFoto(
			@RequestPart("categoria") String categoria,
			@RequestPart("file") MultipartFile file
			) throws Exception {
		Categoria novaCategoria = categoriaService.saveCategoriaComFoto(categoria, file);
		return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
	}

	// DTO End Points

	@GetMapping("/dto/{id}")
	@Operation(summary = "Encontrar uma categoria no padrão DTO com um ID específico")
	public ResponseEntity<CategoriaDTO> findCategoriaDTOById(@PathVariable Integer id) {
		CategoriaDTO categoriaDTO = categoriaService.findCategoriaDTOById(id);
		if (categoriaDTO.getIdCategoria() == null) {
			throw new NoSuchElementFoundException("GET DTO - Não foi possível encontrar a categoria com o ID " + id);
		} else {
			return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
		}
	}
	
	@PostMapping("/dto")
	@Operation(summary = "Salvar uma categoria no padrão DTO")
	public ResponseEntity<Categoria> saveCategoriaDTO(@RequestBody CategoriaDTO categoriaDTO) {
		if(categoriaDTO.getNomeCategoria() == null || categoriaDTO.getIdCategoria() != null){
			throw new NoSuchElementFoundException("Há um problema no Body do seu JSON");
		}
		else if(categoriaService.findIfNomeCategoriaExists(categoriaDTO.getNomeCategoria())) {
			throw new NoSuchElementFoundException("Já existe uma categoria com o nome " + categoriaDTO.getNomeCategoria());
		}
		else {
			return new ResponseEntity<>(categoriaService.saveCategoriaDTO(categoriaDTO), HttpStatus.CREATED);
		}
	}

}
