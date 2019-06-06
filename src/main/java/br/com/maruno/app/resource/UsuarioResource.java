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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.service.TokenService;
import br.com.maruno.app.service.UsuarioService;
import br.com.maruno.app.support.PaginacaoUtils;
import br.com.maruno.app.support.ResourceSupport;
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

	@GetMapping("/logado")
	@ResponseBody
	public ResponseEntity<?> getUsuario() { 
		return ok(cacheControl, tokenService.getUsuario());
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findUsuarios(@RequestParam(name = "page", required = false)  Integer page
							            , @RequestParam(name = "size", required = false)  Integer size
							            , @RequestParam(name = "nome", required = false)  String nome
							            , @RequestParam(name = "login", required = false) String login) {
		return ok(usuarioService.findUsuarios(nome, login, PaginacaoUtils.getPage(page, size, true, "nome")));
	}

	@GetMapping("/{login}")
	@ResponseBody
	public ResponseEntity<?> findUsuario(@PathVariable("login") String login) {
		return ok(cacheControl, this.usuarioService.findByLogin(login));
	}

	@GetMapping("/{login}/foto")
	@ResponseBody
	public ResponseEntity<?> findFotoUsuario(@PathVariable("codigo") Integer codigo) {
		return ok(cacheControl, new FotoVO(usuarioService.findByCodigo(codigo).getFoto()));
	}

	@PutMapping("/{codigo}/foto")
	public ResponseEntity<Void> saveFotoUsuario(@PathVariable("codigo") Integer codigo, @RequestBody FotoVO fotoVO) {
		usuarioService.saveFotoUsuario(codigo, fotoVO);
		return ok();
	}
}
