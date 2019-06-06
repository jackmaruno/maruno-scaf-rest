/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maruno.app.exceptions.DadoInconsistenteException;
import br.com.maruno.app.support.Util;
import br.com.maruno.scaf.domain.Contato;
import br.com.maruno.scaf.persistence.ContatoDao;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:18 AM
 * @Pacote br.com.maruno.scaf.service
 * @Nome ContatoService.java
 * @NomeCompleto br.com.maruno.scaf.service.ContatoService.java
 */
@Service
public class ContatoService {

	@Autowired
	private ContatoDao contatoDao; 
	

	public List<Contato> find(){ 
		return contatoDao.find();
	} 
	
	public void save(Contato contato){ 
		if(Util.isEmpty(contato.getNome())) {
			throw new DadoInconsistenteException("O campo \"Nome\" é obrigatório");
		}
		if(Util.isEmpty(contato.getAssunto())) {
			throw new DadoInconsistenteException("O campo \"Assunto\" é obrigatório");
		} 
		if(Util.isEmpty(contato.getEmail())) {
			throw new DadoInconsistenteException("O campo \"E-mail\" é obrigatório");
		} 
		if(Util.isEmpty(contato.getMensagem())) {
			throw new DadoInconsistenteException("O campo \"Mensagem\" é obrigatório");
		} 
		contatoDao.save(contato);
	}
	
	public void remove(Integer codigo){ 
		contatoDao.remove(codigo);
	} 
	
}
