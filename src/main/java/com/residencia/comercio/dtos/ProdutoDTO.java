package com.residencia.comercio.dtos;

public class ProdutoDTO {

	private String sku;
	private String nomeProduto;
	private Integer idFornecedor;
	private Integer idCategoria;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "ProdutoDTO [sku=" + sku + ", nomeProduto=" + nomeProduto + ", idFornecedor=" + idFornecedor
				+ ", idCategoria=" + idCategoria + "]";
	}

}
