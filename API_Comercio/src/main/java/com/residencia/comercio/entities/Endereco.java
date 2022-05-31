package com.residencia.comercio.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@Column(name = "id_endereco")
	private Integer CEP;

	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "localidade")
	private String localidade;

	@Column(name = "uf")
	private String uf;

	@Column(name = "ibge")
	private String ibge;

	@OneToMany(mappedBy = "endereco")
	@JsonIgnore
	private List<Fornecedor> fornecedorList;

	public Integer getIdEndereco() {
		return CEP;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.CEP = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public List<Fornecedor> getFornecedorList() {
		return fornecedorList;
	}

	public void setFornecedorList(List<Fornecedor> fornecedorList) {
		this.fornecedorList = fornecedorList;
	}
	
	

}
