package com.residencia.comercio.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fornecedor")
	private Integer idFornecedor;

	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "situacao")
	private String situacao;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "porte")
	private String porte;

	@Column(name = "abertura")
	private String abertura;

	@Column(name = "data_situacao")
	private String dataSituacao;

	@Column(name = "motivo_situacao")
	private String motivoSituacao;

	@Column(name = "email")
	private String email;

	@Column(name = "natureza_juridica")
	private String naturezaJuridica;

	@Column(name = "ultima_atualizacao")
	private String ultimaAtualizacao;

	@Column(name = "status")
	private String status;

	@Column(name = "fantasia")
	private String fantasia;

	@ManyToOne
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;

	@OneToMany(mappedBy = "fornecedor")
	@JsonIgnore
	private List<Produto> produtoList;

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
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

	public String getDataSituacao() {
		return dataSituacao;
	}

	public void setDataSituacao(String dataSituacao) {
		this.dataSituacao = dataSituacao;
	}

	public String getMotivoSituacao() {
		return motivoSituacao;
	}

	public void setMotivoSituacao(String motivoSituacao) {
		this.motivoSituacao = motivoSituacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}

	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}

	public String getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(String ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		endereco = endereco;
	}

	public List<Produto> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}