package com.residencia.comercio.dtos;

import com.residencia.comercio.entities.Categoria;
import com.residencia.comercio.entities.Fornecedor;

public class ProdutoDTO {

	private Integer id_produto;
	private String sku;
	private String nome_produto;
	private Fornecedor cnpj_fornecedor;
	private Categoria id_categoria;

	public Integer getId_produto() {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public Fornecedor getCnpj_fornecedor() {
		return cnpj_fornecedor;
	}

	public void setCnpj_fornecedor(Fornecedor cnpj_fornecedor) {
		this.cnpj_fornecedor = cnpj_fornecedor;
	}

	public Categoria getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Categoria id_categoria) {
		this.id_categoria = id_categoria;
	}

}
