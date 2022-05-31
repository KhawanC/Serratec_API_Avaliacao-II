package com.residencia.comercio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.comercio.dtos.CategoriaDTO;
import com.residencia.comercio.entities.Categoria;
import com.residencia.comercio.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ArquivoService arquivoService;

	public List<Categoria> findAllCategoria() {
		return categoriaRepository.findAll();
	}

	public Categoria findCategoriaById(Integer id) {
		return categoriaRepository.findById(id).isPresent() ? categoriaRepository.findById(id).get() : null;
	}

	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria updateCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void deleteCategoria(Integer id) {
		Categoria inst = categoriaRepository.findById(id).get();
		categoriaRepository.delete(inst);
	}

	public void deleteCategoria(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}

	// DTO

	public CategoriaDTO findCategoriaDTOById(Integer id) {
		return categoriaRepository.findById(id).isPresent() ? converterParaDTO(categoriaRepository.findById(id).get())
				: null;
	}

	public Categoria saveCategoriaDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = converterParaEntidade(categoriaDTO);
		return categoriaRepository.save(categoria);
	}
	
	public Boolean findIfNomeCategoriaExists(String nomeCategoria) {
		Boolean categoriaExists = categoriaRepository.existsByNomeCategoria(nomeCategoria);
		return categoriaExists;
	}
	
	public Categoria saveCategoriaComFoto(String categoriaString, MultipartFile file) throws Exception {
		Categoria categoriaConvertida = new Categoria();
		try {
			ObjectMapper objMapper = new ObjectMapper();
			categoriaConvertida = objMapper.readValue(categoriaString, Categoria.class);

		} catch (Exception e) {
			System.out.println("Ocorreu um erro na conversão");
		}
		Categoria categoriaBD = categoriaRepository.save(categoriaConvertida);
		categoriaBD.setNomeImagem(categoriaBD.getIdCategoria()+"_"+file.getOriginalFilename());
		Categoria categoriaAtualizado = categoriaRepository.save(categoriaBD);
		
		try {
			arquivoService.criarArquivo(categoriaBD.getIdCategoria()+"_"+file.getOriginalFilename(), file);
		} catch (Exception e) {
			throw new Exception("Ocorreu um erro ao tentar copiar o arquivo - " + e.getStackTrace());
		}
		
		return categoriaAtualizado;
	}

	// CONVERSÃO

	public CategoriaDTO converterParaDTO(Categoria categoria) {
		CategoriaDTO categoraiDTO = new CategoriaDTO();
		categoraiDTO.setIdCategoria(categoria.getIdCategoria());
		categoraiDTO.setNomeCategoria(categoria.getNomeCategoria());

		return categoraiDTO;
	}

	public Categoria converterParaEntidade(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(categoriaDTO.getIdCategoria());
		categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());

		return categoria;
	}

}
