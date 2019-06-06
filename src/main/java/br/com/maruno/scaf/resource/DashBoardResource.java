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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.annotation.TokenAuthentication;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.scaf.service.DashBoardService;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:46 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome DashBoardResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.DashBoardResource.java
 */
@ApiRestController("/api/painel-gerencial")
public class DashBoardResource extends ResourceSupport {
	
	@Autowired
	private DashBoardService dashBoardService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@TokenAuthentication
	@GetMapping 
	@ResponseBody
	public ResponseEntity<?> findDashBoard() { 
		return ok(cacheControl, dashBoardService.findDashBoard());
	}  

	@TokenAuthentication
	@GetMapping("/lancamentos")
	@ResponseBody
	public ResponseEntity<?> findRelacaoLancamentos(@RequestParam(name = "codTipo", required = true) Integer codTipo) { 
		return ok(cacheControl, dashBoardService.findRelacaoLancamentos(codTipo));
	} 
}
