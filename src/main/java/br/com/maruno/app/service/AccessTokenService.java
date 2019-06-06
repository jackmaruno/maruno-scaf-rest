/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.service;

import java.util.Base64;
import java.util.Date;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maruno.app.domain.AccessToken;
import br.com.maruno.app.exceptions.ClienteInvalidoException;
import br.com.maruno.app.exceptions.DadoInconsistenteException;
import br.com.maruno.app.exceptions.GrantTypeException;
import br.com.maruno.app.exceptions.UsuarioInvalidoException;
import br.com.maruno.app.persistence.AccessTokenDao;
import br.com.maruno.app.persistence.UsuarioDao;
import br.com.maruno.app.support.CryptUtil;
import br.com.maruno.app.support.Util;
import eu.bitwalker.useragentutils.UserAgent;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:58:02 AM
 * @Pacote br.com.maruno.app.service
 * @Nome AccessTokenService.java
 * @NomeCompleto br.com.maruno.app.service.AccessTokenService.java
 */
@Service
public class AccessTokenService {

	@Autowired
	private AccessTokenDao accessTokenDao;

	@Autowired
	private UsuarioDao usuarioDao; 
	

	private static final String GRAND_TYPE = "grant_type";
	private static final String CLIENT_ID = "client_id";
	private static final String CLIENT_SECRET = "client_secret";
	private static final String CLIENT_ID_SCAF = "ID_SCAF_cj4XjHXNSRmL3TWQcgoEOW3220BFonJsEO7RAmCIc";
	private static final String CLIENT_SECRET_SCAF = "SECRET_SCAF_H4At4cj4XjHXEEWNSRFDSQRLOTcgyKooOW0Bo";
	
	private static final String USER_AGENT = "user-agent";
	private static final String USER_NAME = "username";
	private static final String PASSWORD = "password"; 
	private static final String BEARER = "Bearer";
	
	public AccessToken getAccessToken(HttpServletRequest request) throws Exception {
		validar(request); 
		AccessToken token = new AccessToken();
		token.setAccess_token(getAccessToken(request.getParameter(USER_NAME)));
		token.setToken_type(BEARER); 
		token.setDataCadastro(new Date());
		token.setUsuario(usuarioDao.findByLogin(request.getParameter(USER_NAME)));
		UserAgent userAgente = UserAgent.parseUserAgentString(request.getHeader(USER_AGENT));
		
		token.setUserAgent(userAgente.getId()+"");
		if(userAgente.getBrowser() != null) {
			token.setBrowser(userAgente.getBrowser().toString());
		}
		if(userAgente.getBrowserVersion() != null) {
			token.setVersaoBrowser(userAgente.getBrowserVersion().toString());
		}
		if(userAgente.getOperatingSystem() != null) {
			token.setSistemaOperacional(userAgente.getOperatingSystem().toString());
		}
		token.setIp(request.getHeader("x-forwarded-for")); 
		if(Util.isEmpty(token.getIp())) {
			token.setIp(request.getHeader("X-FORWARDED-FOR")); 
			if(Util.isEmpty(token.getIp())) {
				token.setIp(request.getHeader("X_FORWARDED_FOR")); 
				if(Util.isEmpty(token.getIp())) {
					token.setIp(request.getRemoteAddr()); 
				}
			}
		}

		System.out.println("token: "+token.toString());  
		
		for(Entry<String, String> e : Util.getRequestHeadersInMap(request).entrySet()) {
			System.out.println(e.getKey() +" -> "+e.getValue()); 
		}
		accessTokenDao.save(token);
		return token;
	} 
    
	private void validar(HttpServletRequest request) {
		if(Util.isEmpty(request.getParameter(GRAND_TYPE)) || !request.getParameter(GRAND_TYPE).equalsIgnoreCase(PASSWORD)) {
			throw new GrantTypeException("O "+GRAND_TYPE+" está inválido.");
		}
		
		if(Util.isEmpty(request.getParameter(CLIENT_ID)) || Util.isEmpty(request.getParameter(CLIENT_SECRET))) {
			throw new ClienteInvalidoException("cliente não localizado");
		}
		if(!request.getParameter(CLIENT_ID).equalsIgnoreCase(CLIENT_ID_SCAF) || !request.getParameter(CLIENT_SECRET).equalsIgnoreCase(CLIENT_SECRET_SCAF)){
			throw new ClienteInvalidoException(CLIENT_ID+" ou "+CLIENT_SECRET+" estão inválidos.");
		}

		if(Util.isEmpty(request.getParameter(USER_NAME)) || Util.isEmpty(request.getParameter(PASSWORD))) {
			throw new DadoInconsistenteException("Usuario e senha são obrigatorios.");
		}

		if (!usuarioDao.autenticar(request.getParameter(USER_NAME), CryptUtil.hashEncrypt(request.getParameter(PASSWORD)))) { 
			throw new UsuarioInvalidoException("Usuário ou senha inválidos.");
		}
	}

	private String getAccessToken(String login) {
		String token = Math.random() + login + new Date().getTime();
		return new String(Base64.getEncoder().encode(token.getBytes()));
	}
}
