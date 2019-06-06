/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maruno.app.domain.AccessToken;
import br.com.maruno.app.domain.Perfil;
import br.com.maruno.app.domain.Usuario;
import br.com.maruno.app.exceptions.AccessoNegadoException;
import br.com.maruno.app.exceptions.DadoInconsistenteException;
import br.com.maruno.app.persistence.AccessTokenDao;
import br.com.maruno.app.support.Util; 
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:57:57 AM
 * @Pacote br.com.maruno.app.service
 * @Nome TokenService.java
 * @NomeCompleto br.com.maruno.app.service.TokenService.java
 */
@Service
public class TokenService {

	private static AccessToken token;
	
	@Autowired
	private AccessTokenDao accessTokenDao;

	public AccessToken findAccessToken(String accessToken) {
		return accessTokenDao.findAccessToken(accessToken); 
	}
	
	public AccessToken findAccessToken(String accessToken, String urlRecurso, String metodo) {
		token = null;
		if (Util.isEmpty(urlRecurso)) {
			throw new DadoInconsistenteException("A URL do recurso não foi informada.");
		} 
		if (Util.isEmpty(accessToken)) {
			throw new DadoInconsistenteException("O accessToken não foi informado.");
		} 
		token = accessTokenDao.findAccessToken(accessToken, urlRecurso.toUpperCase(), metodo.toUpperCase());
		if (token == null) {
			throw new AccessoNegadoException("O token informado não possui acesso ao recurso solicitado.");
		}  
		System.out.println("TokenService.findAccessToken -> "+token); 
		return token;
	}

	public AccessToken getToken() {
		return token;
	}

	public Integer getCodUsuario() {
		return token.getUsuario().getCodigo();
	}
	
	public Usuario getUsuario() {
		return token.getUsuario();
	}
	
	public Integer getCodPerfil(){
		return token.getUsuario().getPerfil().getCodigo(); 
	}

	public Perfil getPerfil(){
		return token.getUsuario().getPerfil(); 
	}
	
	public String getAccess_token(){
		return token.getAccess_token();
	}
}
