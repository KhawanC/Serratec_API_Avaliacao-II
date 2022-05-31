package com.residencia.comercio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.comercio.dtos.ProdutoDTO;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(Integer id) {
		return produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;
	}

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto update(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto updateComId(Produto produto, Integer id) {
		Produto produtoDB = produtoRepository.findById(id).isPresent() ? produtoRepository.findById(id).get() : null;

		Produto produtoAtualizado = null;
		if (null != produtoDB) {
			produtoDB.setCategoria(produto.getCategoria());

			produtoAtualizado = produtoRepository.save(produtoDB);
		}
		return produtoAtualizado;
	}

	public void deleteById(Integer id) {
		produtoRepository.deleteById(id);
	}

	// DTO

	// CONVERSÃO

	public ProdutoDTO converterEntidadeParaDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setId_produto(produto.getId());
		produtoDTO.setCnpj_fornecedor(produto.getFornecedor());
		produtoDTO.setId_categoria(produto.getCategoria());
		produtoDTO.setSku(produto.getSku());

		return produtoDTO;
	}

	public Produto converterDTOParaEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setCategoria(produtoDTO.getId_categoria());
		produto.setFornecedor(produtoDTO.getCnpj_fornecedor());
		produto.setId(produtoDTO.getId_produto());
		produto.setNome_produto(produtoDTO.getNome_produto());
		produto.setSku(produtoDTO.getSku());

		return produto;
	}

}
