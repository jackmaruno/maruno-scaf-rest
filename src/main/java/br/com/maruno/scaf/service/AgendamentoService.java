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
import br.com.maruno.scaf.domain.Agendamento;
import br.com.maruno.scaf.persistence.AgendamentoDao;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:26 AM
 * @Pacote br.com.maruno.scaf.service
 * @Nome AgendamentoService.java
 * @NomeCompleto br.com.maruno.scaf.service.AgendamentoService.java
 */
@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoDao agendamentoDao;
	
	@Autowired
	private TokenService tokenService;
	

	public List<Agendamento> find(){ 
		return agendamentoDao.find(tokenService.getCodUsuario());
	}
	
	public List<Agendamento> findAtivos(){ 
		return agendamentoDao.findAtivos(tokenService.getCodUsuario());
	}

	public Agendamento findById(Integer codigo){ 
		return agendamentoDao.findByCodigoAndCodUsuario(codigo, tokenService.getCodUsuario());
	}
	
	public void save(Integer codigo, Agendamento agendamento){
		if(Util.isNotEmpty(codigo)) {
			if(Util.isEmpty(agendamento.getCodigo())) {
				throw new DadoInconsistenteException("O código é obrigatório");
			}else if(!agendamento.getCodigo().equals(codigo)) {
				throw new DadoInconsistenteException("O codigo informado não corresponde ao registro selecionado!");
			}
		}
		if(Util.isEmpty(agendamento.getNome())) {
			throw new DadoInconsistenteException("O campo \"Nome\" é obrigatório");
		}
		if(Util.isEmpty(agendamento.getDataInicio())) {
			throw new DadoInconsistenteException("O campo \"Data Início\" é obrigatório");
		}
		if(agendamento.getCategoria() == null || Util.isEmpty(agendamento.getCategoria().getCodigo())){
			throw new DadoInconsistenteException("O campo \"Categoria\" é obrigatório");
		}
		if(agendamento.getPeriodo() == null || Util.isEmpty(agendamento.getPeriodo().getNumero())){
			throw new DadoInconsistenteException("O campo \"Período\" é obrigatório");
		} 
		agendamento.setCodUsuario(tokenService.getCodUsuario());
		agendamentoDao.save(agendamento);
	}
	
	public void remove(Integer codigo){ 
		agendamentoDao.remove(tokenService.getCodUsuario(), codigo, new Date());
	}
	
	public void alterarStatus(Integer codigo, boolean ativo){ 
		agendamentoDao.alterarStatus(tokenService.getCodUsuario(), codigo, new Date(), ativo);
	}
	
}
