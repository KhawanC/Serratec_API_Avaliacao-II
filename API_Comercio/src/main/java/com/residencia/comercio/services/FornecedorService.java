package com.residencia.comercio.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.comercio.dtos.CnpjDTO;
import com.residencia.comercio.dtos.EnderecoDTO;
import com.residencia.comercio.dtos.FornecedorDTO;
import com.residencia.comercio.dtos.ProdutoDTO;
import com.residencia.comercio.entities.Endereco;
import com.residencia.comercio.entities.Fornecedor;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.EnderecoRepository;
import com.residencia.comercio.repositories.FornecedorRepository;

@Service
public class FornecedorService {
	@Autowired
	FornecedorRepository fornecedorRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

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

	// DTO

	public Fornecedor saveFornecedorDTO(FornecedorDTO fornecedorDTO) {
		String cnpjFormatado = fornecedorDTO.getCnpj().replaceAll("[./-]", "");
		CnpjDTO cnpjDTO = consultarCnpj(cnpjFormatado);
		return fornecedorRepository.save(converterCNPJParaFornecedor(cnpjDTO));
	}

	// CONVERSÃO

	private FornecedorDTO converterParaDTO(Fornecedor fornecedor) {
		FornecedorDTO fornecedorDTO = new FornecedorDTO();
		String cnpjString = Integer.toString(fornecedor.getIdFornecedor());
		fornecedorDTO.setAbertura(fornecedor.getAbertura());
		fornecedorDTO.setCnpj(cnpjString);
		fornecedorDTO.setData_situacao(fornecedor.getDataSituacao());
		fornecedorDTO.setEmail(fornecedor.getEmail());
		fornecedorDTO.setFantasia(fornecedor.getFantasia());
		fornecedorDTO.setMotivo_situacao(fornecedor.getMotivoSituacao());
		fornecedorDTO.setNatureza_juridica(fornecedor.getNaturezaJuridica());
		fornecedorDTO.setNome(fornecedor.getNome());
		fornecedorDTO.setPorte(fornecedor.getPorte());
		fornecedorDTO.setSituacao(fornecedor.getSituacao());
		fornecedorDTO.setStatus(fornecedor.getStatus());
		fornecedorDTO.setTipo(fornecedor.getTipo());
		fornecedorDTO.setUltima_ataulizacao(fornecedor.getUltimaAtualizacao());

//		List<ProdutoDTO> listaProdutoDTO = new ArrayList<>();
//		
//		for (Produto produto : fornecedor.getProdutoList()) {
//			ProdutoDTO produtoDTO = new ProdutoDTO();
//			produtoDTO.setId_produto(produto.getId());
//			produtoDTO.setCnpj_fornecedor(produto.getFornecedor());
//			produtoDTO.setId_categoria(produto.getCategoria());
//			produtoDTO.setNome_produto(produto.getNome_produto());
//			produtoDTO.setSku(produto.getSku());
//
//			listaProdutoDTO.add(produtoDTO);
//		}
//		fornecedorDTO.setListProdutos(listaProdutoDTO);

		return fornecedorDTO;
	}

	private Fornecedor converterCNPJParaFornecedor(CnpjDTO cnpjDTO) {
		Fornecedor fornecedor = new Fornecedor();
		String cnpjFormatado = cnpjDTO.getCnpj().replaceAll("[./-]", "");
		fornecedor.setCnpj(cnpjFormatado);
		fornecedor.setAbertura(cnpjDTO.getAbertura());
		fornecedor.setDataSituacao(cnpjDTO.getData_situacao());
		fornecedor.setEmail(cnpjDTO.getEmail());
		fornecedor.setFantasia(cnpjDTO.getFantasia());
		fornecedor.setMotivoSituacao(cnpjDTO.getMotivo_situacao());
		fornecedor.setNaturezaJuridica(cnpjDTO.getNatureza_juridica());
		fornecedor.setNome(cnpjDTO.getNome());
		fornecedor.setPorte(cnpjDTO.getPorte());
		fornecedor.setSituacao(cnpjDTO.getSituacao());
		fornecedor.setStatus(cnpjDTO.getStatus());
		fornecedor.setTipo(cnpjDTO.getTipo());
		fornecedor.setUltimaAtualizacao(cnpjDTO.getUltima_ataulizacao());

		return fornecedor;
	}

}
