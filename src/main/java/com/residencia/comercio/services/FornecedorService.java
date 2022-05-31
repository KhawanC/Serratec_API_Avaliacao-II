package com.residencia.comercio.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.comercio.dtos.CepDTO;
import com.residencia.comercio.dtos.CnpjDTO;
import com.residencia.comercio.dtos.FornecedorDTO;
import com.residencia.comercio.entities.Fornecedor;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.FornecedorRepository;

@Service
public class FornecedorService {
	@Autowired
	FornecedorRepository fornecedorRepository;

	public List<Fornecedor> findAllFornecedor() {
		return fornecedorRepository.findAll();
	}

	public Fornecedor findFornecedorById(Integer id) {
		return fornecedorRepository.findById(id).isPresent() ? fornecedorRepository.findById(id).get() : null;
	}

	public Fornecedor saveFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public Fornecedor updateFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public void deleteFornecedor(Integer id) {
		Fornecedor inst = fornecedorRepository.findById(id).get();
		fornecedorRepository.delete(inst);
	}

	public void deleteFornecedor(Fornecedor fornecedor) {
		fornecedorRepository.delete(fornecedor);
	}

	// API EXTERNA

	public CnpjDTO consultarCnpj(String cnpj) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://www.receitaws.com.br/v1/cnpj/{cnpj}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);

		return restTemplate.getForObject(uri, CnpjDTO.class, params);
	}
	
	public CepDTO consultarCep(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		return restTemplate.getForObject(uri, CepDTO.class, params);
	}
	
	
	// DTO
	
	public Fornecedor saveFornecedorDTO(FornecedorDTO fornecedorDTO) {
		String cnpjFormatado = fornecedorDTO.getCnpj().replaceAll("[./-]", "");
		CnpjDTO cnpjDTO = consultarCnpj(cnpjFormatado);
		String cepFormatado = cnpjDTO.getCep().replaceAll("[./-]", "");
		CepDTO cepDTO = consultarCep(cepFormatado);
		Fornecedor newFornecedor =  converterCepCnpjParaEntidade(cnpjDTO, cepDTO);
		
		return fornecedorRepository.save(newFornecedor);
	}
	
	
	// CONVERSÃO
	
	public Fornecedor converterCepCnpjParaEntidade(CnpjDTO cnpjDTO, CepDTO cepDTO) {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setAbertura(cnpjDTO.getAbertura());
		fornecedor.setBairro(cepDTO.getBairro());
		fornecedor.setCep(cnpjDTO.getCep());
		fornecedor.setCnpj(cnpjDTO.getCnpj());
		fornecedor.setDataSituacao(cnpjDTO.getData_situacao());
		fornecedor.setDdd(cepDTO.getDdd());
		fornecedor.setEmail(cnpjDTO.getEmail());
		fornecedor.setFantasia(cnpjDTO.getFantasia());
		fornecedor.setGia(cepDTO.getGia());
		fornecedor.setIbge(cepDTO.getIbge());
		fornecedor.setLocalidade(cepDTO.getLocalidade());
		fornecedor.setLogradouro(cepDTO.getLogradouro());
		fornecedor.setMotivoSituacao(cnpjDTO.getMotivo_situacao());
		fornecedor.setNaturezaJuridica(cnpjDTO.getNatureza_juridica());
		fornecedor.setNome(cnpjDTO.getNome());
		fornecedor.setPorte(cnpjDTO.getPorte());
		fornecedor.setSituacao(cnpjDTO.getSituacao());
		fornecedor.setStatus(cnpjDTO.getStatus());
		fornecedor.setTelefone(cnpjDTO.getTelefone());
		fornecedor.setTipo(cnpjDTO.getTipo());
		fornecedor.setUf(cepDTO.getUf());
		fornecedor.setUltimaAtualizacao(cnpjDTO.getUltima_atualizacao());
	
		return fornecedor;
	}

}
