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
import br.com.maruno.scaf.domain.Agendamento;
import br.com.maruno.scaf.service.AgendamentoService;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:56 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome AgendamentoResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.AgendamentoResource.java
 */
@ApiRestController("/api/agendamentos")
public class AgendamentoResource extends ResourceSupport {
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@TokenAuthentication
	@GetMapping 
	@ResponseBody
	public ResponseEntity<?> find() { 
		return ok(cacheControl, agendamentoService.find());
	}

	@TokenAuthentication
	@GetMapping("/ativos")
	@ResponseBody
	public ResponseEntity<?> findAtivos() { 
		return ok(cacheControl, agendamentoService.findAtivos());
	}

	@TokenAuthentication
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody Agendamento agendamento) {
		agendamentoService.save(null, agendamento);
		return post();
	}

	@TokenAuthentication
	@PutMapping("/{codigo}")
	@ResponseBody
	public ResponseEntity<Void> save(@PathVariable("codigo") Integer codigo, @RequestBody Agendamento agendamento) {
		agendamentoService.save(codigo, agendamento);
		return ok();
	}

	@TokenAuthentication
	@PutMapping("/{codigo}/ativar")
	@ResponseBody
	public ResponseEntity<Void> ativar(@PathVariable("codigo") Integer codigo) {
		agendamentoService.alterarStatus(codigo, true);
		return ok();
	}

	@TokenAuthentication
	@PutMapping("/{codigo}/desativar")
	@ResponseBody
	public ResponseEntity<Void> desativar(@PathVariable("codigo") Integer codigo) {
		agendamentoService.alterarStatus(codigo, false);
		return ok();
	}

	@TokenAuthentication
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remove(@PathVariable("codigo") Integer codigo) {
		agendamentoService.remove(codigo);
		return ok();
	}
}
