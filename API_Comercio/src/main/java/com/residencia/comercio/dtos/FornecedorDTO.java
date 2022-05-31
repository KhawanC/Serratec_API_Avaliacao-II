package com.residencia.comercio.dtos;

import java.util.List;

public class FornecedorDTO {

	private String idFornecedor;
	private String cnpj;
	private String situacao;
	private String tipo;
	private String nome;
	private String porte;
	private String abertura;
	private String data_situacao;
	private String motivo_situacao;
	private String email;
	private String natureza_juridica;
	private String ultima_ataulizacao;
	private String status;
	private String fantasia;
	private String complemento;
	private String cep;

	// private List<ProdutoDTO> listProdutos;
	public String getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(String idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public String getAbertura() {
		return abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	public String getData_situacao() {
		return data_situacao;
	}

	public void setData_situacao(String data_situacao) {
		this.data_situacao = data_situacao;
	}

	public String getMotivo_situacao() {
		return motivo_situacao;
	}

	public void setMotivo_situacao(String motivo_situacao) {
		this.motivo_situacao = motivo_situacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNatureza_juridica() {
		return natureza_juridica;
	}

	public void setNatureza_juridica(String natureza_juridica) {
		this.natureza_juridica = natureza_juridica;
	}

	public String getUltima_ataulizacao() {
		return ultima_ataulizacao;
	}

	public void setUltima_ataulizacao(String ultima_ataulizacao) {
		this.ultima_ataulizacao = ultima_ataulizacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

//	public List<ProdutoDTO> getListProdutos() {
//		return listProdutos;
//	}
//
//	public void setListProdutos(List<ProdutoDTO> listProdutos) {
//		this.listProdutos = listProdutos;
//	}

}
