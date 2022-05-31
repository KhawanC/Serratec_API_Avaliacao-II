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

import com.residencia.comercio.dtos.ProdutoDTO;
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
	@Operation(summary = "Salvar um produto")
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		System.out.println(produto);
		return new ResponseEntity<>(produtoService.saveProduto(produto), HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Atualizar um produto")
	public ResponseEntity<String> update(@RequestBody Produto produto) {
		produtoService.updateProduto(produto);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um produto com um ID específico")
	public ResponseEntity<String> deleteById(Integer id) {
		produtoService.deleteProdutoById(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	// DTO

	@PostMapping("/dto")
	@Operation(summary = "salvar um produto no padrão DTO")
	public ResponseEntity<Produto> saveProdutoDTO(@RequestBody ProdutoDTO produtoDTO) {
		System.out.println("parte 1");
		System.out.println(produtoDTO.toString());
		Produto produto = produtoService.saveProdutoDTO(produtoDTO);
		return new ResponseEntity<>(produto, HttpStatus.CREATED);
	}
}
