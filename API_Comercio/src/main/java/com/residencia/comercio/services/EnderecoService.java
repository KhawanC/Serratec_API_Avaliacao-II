package com.residencia.comercio.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.comercio.dtos.EnderecoDTO;
import com.residencia.comercio.entities.Endereco;
import com.residencia.comercio.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public List<Endereco> findAllEnderecos() {
		return enderecoRepository.findAll();
	}

	public Endereco findEnderecoById(Integer id) {
		return enderecoRepository.findById(id).isPresent() ? enderecoRepository.findById(id).get() : null;

	}

	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public void deleteEndereco(Integer id) {
		Endereco endereco = enderecoRepository.findById(id).get();
		enderecoRepository.delete(endereco);
	}

	// DTO

	public EnderecoDTO consultarEnderecoByCEP(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		return restTemplate.getForObject(uri, EnderecoDTO.class, params);
	}

	public Endereco saveEnderecoDTO(EnderecoDTO enderecoDTO) {
		EnderecoDTO newEndereco = consultarEnderecoByCEP(enderecoDTO.getCep());
		Endereco endereco = converterDTOParaEntidade(newEndereco);
		return enderecoRepository.save(endereco);
	}

	// CONVERSÕES

//	private EnderecoDTO converterEntidadeParaDTO(Endereco endereco) {
//		EnderecoDTO enderecoDTO = new EnderecoDTO();
//		
//		enderecoDTO.setBairro(endereco.getBairro());
//		enderecoDTO.setComplemento(endereco.getComplemento());
//		enderecoDTO.setIbge(endereco.getIbge());
//		enderecoDTO.setId_endereco(endereco.getIdEndereco());
//		enderecoDTO.setLocalidade(endereco.getLocalidade());
//		enderecoDTO.setLogradouro(endereco.getLogradouro());
//		enderecoDTO.setUf(endereco.getUf());
//
//		return enderecoDTO;
//	}

	private Endereco converterDTOParaEntidade(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();

		String cep_String = enderecoDTO.getCep();
		cep_String = cep_String.replaceAll("[-.]", "");
		Integer cep_Integer = Integer.parseInt(cep_String);
		endereco.setIdEndereco(cep_Integer);
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setIbge(enderecoDTO.getIbge());
		endereco.setLocalidade(enderecoDTO.getLocalidade());
		endereco.setLogradouro(enderecoDTO.getLogradouro());
		endereco.setUf(enderecoDTO.getUf());

		return endereco;
	}

}
