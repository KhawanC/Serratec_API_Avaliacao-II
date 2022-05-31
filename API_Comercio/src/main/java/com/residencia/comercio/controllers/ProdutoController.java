package com.residencia.comercio.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.services.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	@Operation(summary = "Mostrar todas os produtos")
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produtoList = produtoService.findAll();
		if (produtoList.isEmpty()) {
			throw new NoSuchElementException("Não há registro de produtos em seu banco de dados");
		} else {
			return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK);

		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Encontrar um produto com um ID específico")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		if (null == produto) {
			throw new NoSuchElementException("O produto de id " + id + " não foi encontrado");
		} else {
			return new ResponseEntity<>(produto, HttpStatus.OK);
		}
	}

	@PostMapping
	@Operation(summary = "Salvar um fornecedor")
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.save(produto), HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Atualizar um fornecedor")
	public ResponseEntity<String> update(@RequestBody Produto produto) {
		produtoService.update(produto);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um produtor com um ID específico")
	public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto produto) {
		Produto produtoAtualizado = produtoService.updateComId(produto, id);
		if (null == produtoAtualizado) {
			return new ResponseEntity<>(produtoAtualizado, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
		}
	}


	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar uma categoria com um ID específico")
	public ResponseEntity<String> deleteById(Integer id) {
		produtoService.deleteById(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
