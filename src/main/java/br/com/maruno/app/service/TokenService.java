/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maruno.app.domain.AccessToken;
import br.com.maruno.app.domain.Perfil;
import br.com.maruno.app.domain.Recurso;
import br.com.maruno.app.domain.Usuario;
import br.com.maruno.app.exceptions.AccessoNegadoException;
import br.com.maruno.app.exceptions.DadoInconsistenteException;
import br.com.maruno.app.exceptions.UsuarioNaoAutorizadoException;
import br.com.maruno.app.persistence.AccessTokenDao;
import br.com.maruno.app.persistence.RecursoDao;
import br.com.maruno.app.support.DateUtils;
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
	
	@Autowired
	private RecursoDao recursoDao;

	public AccessToken findAccessToken(String accessToken) {
		return accessTokenDao.findAccessToken(accessToken); 
	}
	
	public AccessToken findAccessToken(String accessToken, String urlRecurso, String metodo) throws Exception {
		token = null;
		if (Util.isEmpty(urlRecurso)) {
			throw new DadoInconsistenteException("A URL do recurso não foi informada.");
		} 
		if (Util.isEmpty(accessToken)) {
			throw new DadoInconsistenteException("O accessToken não foi informado.");
		} 
		
		Recurso recurso = recursoDao.findRecurso(urlRecurso.toUpperCase());
		
		if(recurso != null && !recurso.isExcluido()) { 
			token = accessTokenDao.findAccessToken(accessToken, urlRecurso.toUpperCase(), metodo.toUpperCase());
		}else {
			token = accessTokenDao.findAccessToken(accessToken);
		}
		if (token == null) {
			throw new AccessoNegadoException("O token informado não possui acesso ao recurso solicitado.");
		}

		if(token.isValido() && Util.isNotEmpty(token.getExpires_in())){
			Date dataInicio = token.getDataAtualizacao() == null ? token.getDataCadastro() : token.getDataAtualizacao();
			
			System.out.println("dataInicio.getTime()  -> "+dataInicio.getTime()+"\n");
			System.out.println("new Date().getTime()  -> "+new Date().getTime()+"\n");
			System.out.println("token.getExpires_in() -> "+ token.getExpires_in()+"\n\n");
			System.out.println("dataInicio.getTime() - new Date().getTime() -> "+(dataInicio.getTime() - new Date().getTime())+"\n");
			
			if((dataInicio.getTime() - new Date().getTime()) >= token.getExpires_in()) {
				token.setValido(false);
			}else {
				token.setDataAtualizacao(new Date());
			}
			accessTokenDao.save(token);
		}
		
		
		if(!token.isValido()){
			throw new UsuarioNaoAutorizadoException("Sessão expirada.");
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
