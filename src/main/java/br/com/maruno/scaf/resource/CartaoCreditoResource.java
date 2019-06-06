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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.annotation.TokenAuthentication;
import br.com.maruno.app.support.DateUtils;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.scaf.domain.CartaoCredito;
import br.com.maruno.scaf.service.CartaoCreditoService;
import br.com.maruno.scaf.service.LancamentoService;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:53 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome CartaoCreditoResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.CartaoCreditoResource.java
 */
@ApiRestController("/api/cartoes")
public class CartaoCreditoResource extends ResourceSupport {
	
	@Autowired
	private CartaoCreditoService cartaoCreditoService;

	@Autowired
	private LancamentoService lancamentoService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@TokenAuthentication
	@GetMapping 
	@ResponseBody
	public ResponseEntity<?> find() { 
		return ok(cacheControl, cartaoCreditoService.find());
	}

	@TokenAuthentication
	@GetMapping("/ativos")
	@ResponseBody
	public ResponseEntity<?> findAtivos() { 
		return ok(cacheControl, cartaoCreditoService.findAtivos());
	}

	@TokenAuthentication
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody CartaoCredito cartaoCredito) {
		cartaoCreditoService.save(null, cartaoCredito);
		return post();
	}

	@TokenAuthentication
	@PutMapping("/{codigo}")
	@ResponseBody
	public ResponseEntity<Void> save(@PathVariable("codigo") Integer codigo, @RequestBody CartaoCredito cartaoCredito) {
		cartaoCreditoService.save(codigo, cartaoCredito);
		return ok();
	}

	@TokenAuthentication
	@PutMapping("/{codigo}/ativar")
	@ResponseBody
	public ResponseEntity<Void> ativar(@PathVariable("codigo") Integer codigo) {
		cartaoCreditoService.alterarStatus(codigo, true);
		return ok();
	}

	@TokenAuthentication
	@PutMapping("/{codigo}/desativar")
	@ResponseBody
	public ResponseEntity<Void> desativar(@PathVariable("codigo") Integer codigo) {
		cartaoCreditoService.alterarStatus(codigo, false);
		return ok();
	}

	@TokenAuthentication
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remove(@PathVariable("codigo") Integer codigo) {
		cartaoCreditoService.remove(codigo);
		return ok();
	}
	
	/************************************************************************************
	 * FIND FATURAS DE CARTAO 
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/{codCartaoCredito}/faturas")
	@ResponseBody
	public ResponseEntity<?> findFaturas(@PathVariable("codCartaoCredito") Integer codCartaoCredito, @RequestParam(name = "anoMes", required = false) String anoMes) { 
		return ok(cacheControl, lancamentoService.findFaturas(codCartaoCredito, anoMes));  
	}
	
	/************************************************************************************
	 * FIND DATAS DAS FATURAS DE CARTAO 
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/{codCartaoCredito}/faturas/datas")
	@ResponseBody
	public ResponseEntity<?> findDatasFaturas(@PathVariable("codCartaoCredito") Integer codCartaoCredito) { 
		return ok(cacheControl, lancamentoService.findDatasFaturas(codCartaoCredito));  
	}

	/************************************************************************************
	 * FIND PARCELAS DE FATURA DE CARTAO 
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/{codCartaoCredito}/faturas/{data}/parcelas")
	@ResponseBody
	public ResponseEntity<?> findParcelasByParametros(@PathVariable("codCartaoCredito") Integer codCartaoCredito
                                                    , @PathVariable("data") String data) { 
		return ok(cacheControl, lancamentoService.findParcelasByParametros(codCartaoCredito, DateUtils.convertStringDate(data, DateUtils.YYYY_MM_DD)));  
	}
}
