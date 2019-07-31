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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.com.maruno.app.support.PaginacaoUtils;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.scaf.domain.Lancamento;
import br.com.maruno.scaf.service.LancamentoService;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:43 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome LancamentoResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.LancamentoResource.java
 */
@ApiRestController("/api")
@CrossOrigin(origins = "*")
public class LancamentoResource extends ResourceSupport {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	/************************************************************************************
	 * FIND LANCAMENTOS 
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/lancamentos")
	@ResponseBody
	public ResponseEntity<?> find(@RequestParam(name = "codLancamento", required = false) Integer codLancamento
			                    , @RequestParam(name = "codGrupo",      required = false) Integer codGrupo
			                    , @RequestParam(name = "codCategoria",  required = false) Integer codCategoria
			                    , @RequestParam(name = "dataInicio",    required = true)  String  dataInicio
			                    , @RequestParam(name = "dataFim",       required = true)  String  dataFim
			                    , @RequestParam(name = "descricao",     required = false) String  descricao) { 
		return ok(cacheControl, lancamentoService.findByParametros(codLancamento, codGrupo, codCategoria, dataInicio, dataFim, descricao));  
	}

	/************************************************************************************
	 * FIND LANCAMENTOS PAGINADO
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/lancamentos-paginado")
	@ResponseBody
	public ResponseEntity<?> findPage(
			  @RequestParam(name = "page", required = false)  Integer page
            , @RequestParam(name = "size", required = false)  Integer size
			, @RequestParam(name = "codLancamento", required = false) Integer codLancamento
            , @RequestParam(name = "codGrupo",      required = false) Integer codGrupo
            , @RequestParam(name = "codCategoria",  required = false) Integer codCategoria
            , @RequestParam(name = "dataInicio",    required = true)  String  dataInicio
            , @RequestParam(name = "dataFim",       required = true)  String  dataFim
            , @RequestParam(name = "descricao",     required = false) String  descricao) { 
		return ok(cacheControl, lancamentoService.findByParametros(codLancamento, codGrupo, codCategoria, dataInicio, dataFim, descricao, PaginacaoUtils.getPage(page, size, true, "dataReferencia")));  
	}
	/************************************************************************************
	 * FIND LANCAMENTO BY ID
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/lancamentos/{codigo}")
	@ResponseBody
	public ResponseEntity<?> find(@PathVariable("codigo") Integer codigo) { 
		return ok(cacheControl, lancamentoService.findById(codigo)); 
	}

	/************************************************************************************
	 * SAVE LANCAMENTO
	 ************************************************************************************/
	@TokenAuthentication
	@PostMapping("/lancamentos")
	@ResponseBody
	public ResponseEntity<?> saveLancamento(@RequestParam(name = "numParcelas", required = false) Integer numParcelas, @RequestBody Lancamento lancamento) {
		lancamentoService.saveLancamento(lancamento, numParcelas);
		return post();
	}
	
	/************************************************************************************
	 * SAVE LANCAMENTO AGENDADO 
	 ************************************************************************************/
	@TokenAuthentication
	@PostMapping("/lancamentos/agendados")
	@ResponseBody
	public ResponseEntity<?> saveLancamentoAgendado(@RequestParam(name = "numParcelas", required = false) Integer numParcelas, @RequestBody Lancamento lancamento) {
		return post(lancamentoService.saveLancamentoAgendado(lancamento, numParcelas));
	} 

	/************************************************************************************
	 * SAVE LANCAMENTO FATURA 
	 ************************************************************************************/
	@TokenAuthentication
	@PostMapping("/lancamentos/faturas")
	@ResponseBody
	public ResponseEntity<?> saveLancamentoFatura(@RequestBody Lancamento lancamento) {
		return post(lancamentoService.saveLancamentoFatura(lancamento));
	} 
	
	/************************************************************************************
	 * FIND PARCELAS LANCAMENTO FATURA 
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/lancamentos/faturas/{codLancamento}/parcelas")
	@ResponseBody
	public ResponseEntity<?> findParcelasByFatura(@PathVariable("codLancamento") Integer codLancamento) {
		return ok(cacheControl, lancamentoService.findParcelasByFatura(codLancamento));
	} 

	/************************************************************************************
	 * UPDATE LANCAMENTO
	 ************************************************************************************/
	@TokenAuthentication
	@PutMapping("/lancamentos/{codigo}")
	@ResponseBody
	public ResponseEntity<Void> save(@PathVariable("codigo") Integer codigo, @RequestBody Lancamento lancamento) {
		lancamentoService.updateLancamento(codigo, lancamento);
		return ok();
	}

	/************************************************************************************
	 * REMOVE LANCAMENTO
	 ************************************************************************************/
	@TokenAuthentication
	@DeleteMapping("/lancamentos/{codigo}")
	public ResponseEntity<Void> remove(@PathVariable("codigo") Integer codigo) {
		lancamentoService.remove(codigo);
		return ok();
	} 


	/*+**********************************************************************************
	 * FIND PARCELA BY ID
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/parcelas/{codigo}")
	@ResponseBody
	public ResponseEntity<?> findParcelaById(@PathVariable("codigo") Integer codigo) { 
		return ok(cacheControl, lancamentoService.findParcelaById(codigo)); 
	}

	/*+**********************************************************************************
	 * FIND PARCELAS PAGAS
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/parcelas/pagas")
	@ResponseBody
	public ResponseEntity<?> findParcelasPagas(@RequestParam(name = "anoMes", required = false) String anoMes) { 
		return ok(cacheControl, lancamentoService.findParcelasPagas(anoMes)); 
	}
	
	/*+**********************************************************************************
	 * FIND PARCELAS PENDENTES
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/parcelas/pendentes")
	@ResponseBody
	public ResponseEntity<?> findParcelasPendentes(@RequestParam(name = "anoMes", required = false) String anoMes) { 
		return ok(cacheControl, lancamentoService.findParcelasPendentes(anoMes)); 
	}

	/*+**********************************************************************************
	 * FIND DATAS PARCELAS PENDENTES
	 ************************************************************************************/
	@TokenAuthentication
	@GetMapping("/parcelas/pendentes/datas")
	@ResponseBody
	public ResponseEntity<?> findDatasParcelasPendentes() { 
		return ok(cacheControl, lancamentoService.findDatasParcelasPendentes()); 
	}
}
