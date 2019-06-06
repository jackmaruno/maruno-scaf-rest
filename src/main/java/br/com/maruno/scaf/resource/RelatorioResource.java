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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.annotation.TokenAuthentication;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.scaf.service.RelatorioService;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:34 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome RelatorioResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.RelatorioResource.java
 */
@ApiRestController("/api/relatorios")
public class RelatorioResource extends ResourceSupport {
	
	@Autowired
	private RelatorioService relatorioService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@TokenAuthentication
	@GetMapping 
	@ResponseBody
	public ResponseEntity<?> findRelatorio() { 
		return ok(cacheControl, relatorioService.findRelatorio());
	} 

	@TokenAuthentication
	@GetMapping("/agrupados")
	@ResponseBody
	public ResponseEntity<?> findRelatorioAgrupado() { 
		return ok(cacheControl, relatorioService.findRelatorioAgrupado());
	} 
}
