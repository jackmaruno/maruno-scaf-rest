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
import br.com.maruno.scaf.domain.CartaoCredito;
import br.com.maruno.scaf.persistence.CartaoCreditoDao;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:22 AM
 * @Pacote br.com.maruno.scaf.service
 * @Nome CartaoCreditoService.java
 * @NomeCompleto br.com.maruno.scaf.service.CartaoCreditoService.java
 */
@Service
public class CartaoCreditoService {

	@Autowired
	private CartaoCreditoDao cartaoCreditoDao;
 
	
	@Autowired
	private TokenService tokenService;

	public List<CartaoCredito> find(){ 
		return cartaoCreditoDao.find(tokenService.getCodUsuario());
	}
	
	public CartaoCredito findById(Integer codigo){ 
		return cartaoCreditoDao.findByCodigoAndCodUsuario(codigo, tokenService.getCodUsuario());
	}
	
	public List<CartaoCredito> findAtivos(){ 
		return cartaoCreditoDao.findAtivos(tokenService.getCodUsuario());
	}
	
	public void save(Integer codigo, CartaoCredito cartaoCredito){
		if(Util.isNotEmpty(codigo)) {
			if(Util.isEmpty(cartaoCredito.getCodigo())) {
				throw new DadoInconsistenteException("O código é obrigatório");
			}else if(!cartaoCredito.getCodigo().equals(codigo)) {
				throw new DadoInconsistenteException("O codigo informado não corresponde ao registro selecionado!");
			}
		}
		if(Util.isEmpty(cartaoCredito.getNome())) {
			throw new DadoInconsistenteException("O campo \"Nome\" é obrigatório");
		}
		if(Util.isEmpty(cartaoCredito.getBandeira())) {
			throw new DadoInconsistenteException("O campo \"Bandeira\" é obrigatório");
		}
		if(Util.isEmpty(cartaoCredito.getVencimento())) {
			throw new DadoInconsistenteException("O campo \"Vencimento\" é obrigatório");
		}
		if(Util.isEmpty(cartaoCredito.getFechamento())) {
			throw new DadoInconsistenteException("O campo \"Fechamento\" é obrigatório");
		}
		cartaoCredito.setCodUsuario(tokenService.getCodUsuario());
		cartaoCreditoDao.save(cartaoCredito);
	}
	
	public void remove(Integer codigo){ 
		cartaoCreditoDao.remove(tokenService.getCodUsuario(), codigo, new Date());
	}
	
	public void alterarStatus(Integer codigo, boolean ativo){ 
		cartaoCreditoDao.alterarStatus(tokenService.getCodUsuario(), codigo, new Date(), ativo);
	}
	
}
