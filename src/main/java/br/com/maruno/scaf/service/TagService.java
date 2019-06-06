/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maruno.app.exceptions.DadoInconsistenteException;
import br.com.maruno.app.service.TokenService;
import br.com.maruno.app.support.Util;
import br.com.maruno.scaf.domain.Tag;
import br.com.maruno.scaf.persistence.TagDao; 
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:40:49 AM
 * @Pacote br.com.maruno.scaf.service
 * @Nome TagService.java
 * @NomeCompleto br.com.maruno.scaf.service.TagService.java
 */
@Service
public class TagService {

	@Autowired
	private TagDao tagDao;
 
	
	@Autowired
	private TokenService tokenService;

	public List<Tag> find(){ 
		return tagDao.find(tokenService.getCodUsuario());
	}
	
	public void save(Integer codigo, Tag tag){
		if(Util.isNotEmpty(codigo)) {
			if(Util.isEmpty(tag.getCodigo())) {
				throw new DadoInconsistenteException("O código é obrigatório");
			}else if(!tag.getCodigo().equals(codigo)) {
				throw new DadoInconsistenteException("O codigo informado não corresponde ao registro selecionado!");
			}
			tag.setDataAlteracao(new Date());
		}
		if(Util.isEmpty(tag.getNome())) {
			throw new DadoInconsistenteException("O \"Nome\" é obrigatório");
		}
		tag.setNome(tag.getNome().replaceAll(";", ""));
		tag.setCodUsuario(tokenService.getCodUsuario());
		tagDao.save(tag);
	}
	
	public void remove(Integer codigo){ 
		tagDao.remove(tokenService.getCodUsuario(), codigo, new Date());
	} 
	
	
}
