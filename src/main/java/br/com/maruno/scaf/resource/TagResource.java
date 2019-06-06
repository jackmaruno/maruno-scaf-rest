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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.annotation.TokenAuthentication;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.scaf.domain.Tag;
import br.com.maruno.scaf.service.TagService;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:30 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome TagResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.TagResource.java
 */
@ApiRestController("/api/tags")
public class TagResource extends ResourceSupport {
	
	@Autowired
	private TagService tagService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@TokenAuthentication
	@GetMapping 
	@ResponseBody
	public ResponseEntity<?> find() { 
		return ok(cacheControl, tagService.find());
	}

	@TokenAuthentication
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody Tag tag) {
		tagService.save(null, tag);
		return post();
	}

	@TokenAuthentication
	@PutMapping("/{codigo}")
	@ResponseBody
	public ResponseEntity<Void> save(@PathVariable("codigo") Integer codigo, @RequestBody Tag tag) {
		tagService.save(codigo, tag);
		return ok();
	}

	@TokenAuthentication
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remove(@PathVariable("codigo") Integer codigo) {
		tagService.remove(codigo);
		return ok();
	}
}
