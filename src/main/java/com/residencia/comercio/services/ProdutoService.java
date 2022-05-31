package com.residencia.comercio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.comercio.dtos.ProdutoDTO;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.CategoriaRepository;
import com.residencia.comercio.repositories.FornecedorRepository;
import com.residencia.comercio.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	FornecedorRepository fornecedorRepository;
	

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
	}

	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void deleteProdutoById(Integer id) {
		produtoRepository.deleteById(id);
	}

	// DTO

	public Produto saveProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = converterProdutoDTOParaProduto(produtoDTO);
		return produtoRepository.save(produto);
	}

	// CONVERSÕES

	public ProdutoDTO converterProdutoParaProdutoDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setIdFornecedor(produto.getFornecedor().getIdFornecedor());
		produtoDTO.setIdCategoria(produto.getCategoria().getIdCategoria());
		produtoDTO.setSku(produto.getSku());

		return produtoDTO;
	}

	public Produto converterProdutoDTOParaProduto(ProdutoDTO produtoDTO) {
		System.out.println("parte2");
		System.out.println(produtoDTO);
		Produto produto = new Produto();
		produto.setCategoria(categoriaRepository.findById(produtoDTO.getIdCategoria()).get());
		produto.setFornecedor(fornecedorRepository.findById(produtoDTO.getIdFornecedor()).get());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setSku(produtoDTO.getSku());

		return produto;
	}

}
