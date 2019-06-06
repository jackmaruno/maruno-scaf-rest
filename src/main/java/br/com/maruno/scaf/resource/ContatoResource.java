/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.resource;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.annotation.TokenAuthentication;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.scaf.service.ContatoService;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:49 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome ContatoResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.ContatoResource.java
 */
@ApiRestController("/api/contatos")
public class ContatoResource extends ResourceSupport {
	
	@Autowired
	private ContatoService contatoService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@TokenAuthentication
	@GetMapping 
	@ResponseBody
	public ResponseEntity<?> find() { 
		return ok(cacheControl, contatoService.find());
	}   

	@TokenAuthentication
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remove(@PathVariable("codigo") Integer codigo) {
		contatoService.remove(codigo);
		return ok();
	}
}
