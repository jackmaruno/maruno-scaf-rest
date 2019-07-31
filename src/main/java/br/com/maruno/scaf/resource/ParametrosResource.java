/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.annotation.TokenAuthentication;
import br.com.maruno.app.service.UsuarioService;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.scaf.service.ParametrosService;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 5, 2019 10:46:21 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome ParametrosResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.ParametrosResource.java
 */
@ApiRestController("/api")
public class ParametrosResource extends ResourceSupport{

	@Autowired
	private ParametrosService parametrosService;

	@Autowired
	private UsuarioService usuarioService;
	 
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES); 
	
	
	@GetMapping("/grupos")
	@ResponseBody
	public ResponseEntity<?> findGrupos() {  
		return ok(cacheControl, parametrosService.findGrupos());
	}

	@GetMapping("/categorias")
	@ResponseBody
	public ResponseEntity<?> findCategorias(@RequestParam(name = "codGrupo", required = false) Integer codGrupo) { 
		return ok(cacheControl, parametrosService.findCategorias(codGrupo));
	}
	
	@GetMapping("/periodos")
	@ResponseBody
	public ResponseEntity<?> findPeriodos() { 
        System.out.println("ParametrosResource.findPeriodos() -> "+new java.util.Date());
		return ok(cacheControl, parametrosService.findPeriodos());
	}

	@GetMapping("/perfis")
	@ResponseBody
	public ResponseEntity<?> findPerfis() {
		return ok(cacheControl, usuarioService.findPerfis());  
	}

	@TokenAuthentication
	@GetMapping("/recursos")
	@ResponseBody
	public ResponseEntity<?> findRecursos() {
		return ok(cacheControl, usuarioService.findRecursos()); 
	}
}
