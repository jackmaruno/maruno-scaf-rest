/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.resource;

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
import br.com.maruno.app.domain.Usuario;
import br.com.maruno.app.exceptions.OperacaoNaoRealizadaException;
import br.com.maruno.app.service.TokenService;
import br.com.maruno.app.service.UsuarioService;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.app.support.Util;
import br.com.maruno.app.vo.FotoVO;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:57:21 AM
 * @Pacote br.com.maruno.app.resource
 * @Nome UsuarioResource.java
 * @NomeCompleto br.com.maruno.app.resource.UsuarioResource.java
 */
@ApiRestController("/api/usuarios")
public class UsuarioResource extends ResourceSupport {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TokenService tokenService;
	
	CacheControl cacheControl = CacheControl.maxAge(5L, TimeUnit.MINUTES);

	@TokenAuthentication
	@GetMapping("/logado")
	@ResponseBody
	public ResponseEntity<?> getUsuario() { 
		return ok(cacheControl, tokenService.getUsuario());
	}
	
	@TokenAuthentication
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findUsuarios(@RequestParam(name = "nome", required = false)  String nome
							            , @RequestParam(name = "login", required = false) String login
							            , @RequestParam(name = "excluido", required = false) Boolean excluido) {
		if(excluido == null) {
			excluido = false;
		}
		return ok(usuarioService.findUsuarios(nome, login, excluido));
	}

	@TokenAuthentication
	@GetMapping("/{codigo}")
	@ResponseBody
	public ResponseEntity<?> findUsuario(@PathVariable("codigo") Integer codigo) {
		return ok(cacheControl, this.usuarioService.findByCodigo(codigo));
	}

	@TokenAuthentication
	@GetMapping("/{codigo}/foto")
	@ResponseBody
	public ResponseEntity<?> findFotoUsuario(@PathVariable("codigo") Integer codigo) {
		return ok(cacheControl, new FotoVO(usuarioService.findByCodigo(codigo).getFoto()));
	}

	@TokenAuthentication
	@PutMapping("/{codigo}/foto")
	public ResponseEntity<Void> saveFotoUsuario(@PathVariable("codigo") Integer codigo, @RequestBody FotoVO fotoVO) {
		usuarioService.saveFotoUsuario(codigo, fotoVO);
		return ok();
	}

	@TokenAuthentication
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
		return post(usuarioService.saveUsuario(null, usuario));
	}

	@TokenAuthentication
	@PutMapping("/{codigo}")
	@ResponseBody
	public ResponseEntity<?> saveUsuario(@PathVariable("codigo") Integer codigo, @RequestBody Usuario usuario) {
		return ok(usuarioService.saveUsuario(codigo, usuario));
	}

	@TokenAuthentication
	@PutMapping("/{codUsuario}/status/{status}")
	public ResponseEntity<Void> alteraStatusUsuario(@PathVariable("codUsuario") Integer codUsuario, @PathVariable("status") String status) {
		if(Util.isEmpty(status)) {
			throw new OperacaoNaoRealizadaException("Status é obrigatório");
		}else if(status.toUpperCase().equals("ATIVAR")) {
			usuarioService.alterarStatus(codUsuario, true);
			
		}else if(status.toUpperCase().equals("INATIVAR")) {
			usuarioService.alterarStatus(codUsuario, false);
		}else {
			throw new OperacaoNaoRealizadaException("Status \""+status+"\" não é aceito");
		}
		return ok();
	}
	
	@TokenAuthentication
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> removeUsuario(@PathVariable("codigo") Integer codigo) {
		usuarioService.alterarStatus(codigo, false);
		return ok();
	}
}
