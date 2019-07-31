/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.maruno.app.annotation.ApiRestController;
import br.com.maruno.app.domain.Usuario;
import br.com.maruno.app.service.UsuarioService;
import br.com.maruno.app.support.ResourceSupport;
import br.com.maruno.scaf.domain.Contato;
import br.com.maruno.scaf.service.ContatoService;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:41:37 AM
 * @Pacote br.com.maruno.scaf.resource
 * @Nome PortalResource.java
 * @NomeCompleto br.com.maruno.scaf.resource.PortalResource.java
 */
@ApiRestController("/api/portal")
public class PortalResource extends ResourceSupport {
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private UsuarioService usuarioService;

	/************************************************************************************
	 * SAVE CONTATO
	 ************************************************************************************/
	@PostMapping("/contatos")
	@ResponseBody
	public ResponseEntity<?> saveContato(@RequestBody Contato contato) {
		contatoService.save(contato);
		return post();
	}

	/************************************************************************************
	 * SAVE USUARIO
	 ************************************************************************************/
	@PostMapping("/usuarios")
	@ResponseBody
	public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) {
		return post(usuarioService.saveNovoUsuario(usuario));
	}
}
