package com.residencia.comercio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.comercio.dtos.FornecedorDTO;
import com.residencia.comercio.entities.Fornecedor;
import com.residencia.comercio.exceptions.NoSuchElementFoundException;
import com.residencia.comercio.services.FornecedorService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
	@Autowired
	FornecedorService fornecedorService;

	@GetMapping
	@Operation(summary = "Mostrar todas os fornecedores")
	public ResponseEntity<List<Fornecedor>> findAllFornecedor() {
		List<Fornecedor> fornecedorList = fornecedorService.findAllFornecedor();
		return new ResponseEntity<>(fornecedorList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Encontrar um fornecedor com um ID específico")
	public ResponseEntity<Fornecedor> findFornecedorById(@PathVariable Integer id) {
		Fornecedor fornecedor = fornecedorService.findFornecedorById(id);
		if (null == fornecedor)
			throw new NoSuchElementFoundException("Não foi encontrado Fornecedor com o id " + id);
		else
			return new ResponseEntity<>(fornecedor, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Salvar um fornedor")
	public ResponseEntity<Fornecedor> saveFornecedor(@RequestParam String cnpj) {
		Fornecedor fornecedor = new Fornecedor();
		Fornecedor novoFornecedor = fornecedorService.saveFornecedor(fornecedor);
		return new ResponseEntity<>(novoFornecedor, HttpStatus.CREATED);
	}

	@PostMapping("/completo")
	@Operation(summary = "Salvar um fornecedor com todos os campos")
	public ResponseEntity<Fornecedor> saveFornecedorCompleto(@RequestBody Fornecedor fornecedor) {
		Fornecedor novoFornecedor = fornecedorService.saveFornecedor(fornecedor);
		return new ResponseEntity<>(novoFornecedor, HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Atualizar um fornecedor")
	public ResponseEntity<Fornecedor> updateFornecedor(@RequestBody Fornecedor fornecedor) {
		Fornecedor novoFornecedor = fornecedorService.updateFornecedor(fornecedor);
		return new ResponseEntity<>(novoFornecedor, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um fornecedor com um ID específico")
	public ResponseEntity<String> deleteFornecedor(@PathVariable Integer id) {
		if (null == fornecedorService.findFornecedorById(id))
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);

		fornecedorService.deleteFornecedor(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	// DTO
	
	@PostMapping("/dto")
	@Operation(summary = "Salvar um fornecedor no padrão DTO")
	public ResponseEntity<Fornecedor> saveFornecedorDTO(@RequestBody FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = fornecedorService.saveFornecedorDTO(fornecedorDTO);
		return new ResponseEntity<>(fornecedor, HttpStatus.CREATED);
	}

}
