package com.residencia.comercio.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArquivoService {

	@Autowired
	MailService mailService;

	@Value("${pasta.arquivos.imagens}")
	private String diretorioArquivo;

	private Path localFinaLArquivo;

	public void criarArquivo(String fileName, MultipartFile file) throws Exception {
		try {
			if (fileName.contains("..")) {
				throw new Exception("Nome do arquivo inválido");
			}

			this.localFinaLArquivo = Paths.get(diretorioArquivo).toAbsolutePath().normalize();
			Path destinoLocation = localFinaLArquivo.resolve(fileName);
			Files.copy(file.getInputStream(), destinoLocation, StandardCopyOption.REPLACE_EXISTING);
			String mensagem = "<p>Envio de email funcionando!!!!</p><a href=\\\"https://www.google.com/webhp?authuser=1\\\">Abra o google</a>";
			mailService.enviarEmailTexto("teste@teste.com","Teste envio de email", "teste");
			mailService.enviarEmailHTML("teste@teste.com", "teste@teste.com", "Teste com HTML", mensagem);
		} catch (IOException ex) {
			throw new IOException("Não foi possível mover o arquivo. - " + ex.getStackTrace());
		}
	}
}
