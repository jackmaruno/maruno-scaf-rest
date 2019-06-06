/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.service.AccessTokenService;
import br.com.maruno.app.support.ResourceSupport; 
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:57:32 AM
 * @Pacote br.com.maruno.app.resource
 * @Nome TokenResource.java
 * @NomeCompleto br.com.maruno.app.resource.TokenResource.java
 */
@ApiRestController("/api")
public class TokenResource extends ResourceSupport {

	@Autowired
	private AccessTokenService service;

	@RequestMapping("/token")
	@ResponseBody
	public ResponseEntity<?> getToken(@Context HttpServletRequest request) throws Exception {
		return post(service.getAccessToken(request));
	}
}
