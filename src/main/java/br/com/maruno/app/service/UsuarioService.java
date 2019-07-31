/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.maruno.app.domain.Perfil;
import br.com.maruno.app.domain.Recurso;
import br.com.maruno.app.domain.Usuario;
import br.com.maruno.app.exceptions.DadoInconsistenteException;
import br.com.maruno.app.exceptions.OperacaoNaoRealizadaException;
import br.com.maruno.app.exceptions.RecursoNaoEncontradoException;
import br.com.maruno.app.persistence.PerfilDao;
import br.com.maruno.app.persistence.RecursoDao;
import br.com.maruno.app.persistence.UsuarioDao;
import br.com.maruno.app.support.CryptUtil;
import br.com.maruno.app.support.EmailValidator;
import br.com.maruno.app.support.Util;
import br.com.maruno.app.vo.FotoVO; 
 

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:57:46 AM
 * @Pacote br.com.maruno.app.service
 * @Nome UsuarioService.java
 * @NomeCompleto br.com.maruno.app.service.UsuarioService.java
 */
@Transactional
@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao; 

	@Autowired
	private PerfilDao perfilDao; 

	@Autowired
	private RecursoDao recursoDao;

	public List<Perfil> findPerfis() {
		List<Perfil> lista = perfilDao.findAll();
		if (Util.isEmpty(lista)) {
			throw new RecursoNaoEncontradoException("Nenhum perfil foi localizado.");
		} 
		return lista;
	}

	public List<Recurso> findRecursos() {
		List<Recurso> lista = recursoDao.findAll();
		if (Util.isEmpty(lista)) {
			throw new RecursoNaoEncontradoException("Nenhum recurso foi localizado.");
		}
		return lista;
	}
	
	public Page<Usuario> findUsuarios(String nome, String login, Pageable paginacao) {
		Page<Usuario> lista = null;
		if (Util.isNotEmpty(nome) && Util.isNotEmpty(login)) {
			lista = usuarioDao.findByNomeContainingIgnoreCaseAndLoginContainingIgnoreCaseAndExcluido(nome, login, false, paginacao);
		} else if (Util.isNotEmpty(nome) && Util.isEmpty(login)) {
			lista = usuarioDao.findByNomeContainingIgnoreCaseAndExcluido(nome, false, paginacao);
		} else if (Util.isEmpty(nome)  && Util.isNotEmpty(login)) {
			lista = usuarioDao.findByLoginContainingIgnoreCaseAndExcluido(login, false, paginacao);
		} else {
			lista = usuarioDao.findByExcluido(false, paginacao);
		}
		if ((lista == null) || (lista.getTotalPages() == 0)) {
			throw new RecursoNaoEncontradoException("Nenhum usuario foi localizado.");
		}
		for(Usuario u:lista) {
			u.setFoto(null); 
		}
		return lista;
	}

	public Usuario findByLogin(String login) {
		Usuario usuario = usuarioDao.findByLoginIgnoreCaseAndExcluido(login, false);
		if (usuario == null) {
			throw new RecursoNaoEncontradoException("Usuario não foi localizado.");
		}
		return usuario;
	}

	public Usuario findByCodigo(Integer codigo) {
		Usuario usuario = usuarioDao.findByCodigoAndExcluido(codigo, false);
		if (usuario == null) {
			throw new RecursoNaoEncontradoException("Usuario não foi localizado.");
		}
		return usuario;
	}

	public void saveFotoUsuario(Integer codigo, FotoVO fotoVO) { 
		findByCodigo(codigo);
		try {
			usuarioDao.saveFotoUsuario(codigo, fotoVO.getFoto());
		} catch (Exception e) {
			throw new OperacaoNaoRealizadaException(e.toString());
		}
	}
   
 

	public Usuario saveUsuario(Integer codUsuario, Usuario usuario) {
		if (validaUsuario(codUsuario, usuario)) {
			Usuario usuarioBase = null;
			if (Util.isNotEmpty(codUsuario) ) {
				usuarioBase = usuarioDao.findByCodigoAndExcluido(codUsuario, false);
			}
			return saveUsuario(usuarioBase, usuario);
		}
		return null;
	}
 

	private Usuario saveUsuario(Usuario usuarioBase, Usuario usuario) {
		if (usuarioBase != null) {
			usuarioBase = setUsuario(usuarioBase, usuario);
			usuarioBase.setDataAtualizacao(new Date());
			usuarioDao.save(usuarioBase);
		} else {
			validarNovoUsuario(usuario.getLogin());
			usuarioBase = setUsuario(usuarioBase, usuario);
			usuarioBase.setDataCadastro(new Date());
			usuarioDao.save(usuarioBase);
		}
		return usuarioBase;
	}

	private static final String SENHA_PADRAO = "@PSW123456";
	
	private Usuario setUsuario(Usuario usuarioBase, Usuario usuario) {
		if (usuarioBase == null) {
			usuarioBase = new Usuario();
			usuarioBase.setSenha(CryptUtil.hashEncrypt(SENHA_PADRAO)); 
			usuarioBase.setLogin(usuario.getLogin());
		}
		usuarioBase.setNome(usuario.getNome());
		usuarioBase.setEmail(usuario.getEmail());
		usuarioBase.setPerfil(perfilDao.findByCodigo(usuario.getPerfil().getCodigo()));
		return usuarioBase;
	}

	private boolean validaUsuario(Integer codUsuario, Usuario usuario) {
		boolean valido = true;
		if (Util.isNotEmpty(codUsuario) ) {
			if(Util.isEmpty(usuario.getCodigo()) || !usuario.getCodigo().equals(codUsuario)) {
				valido = false;
				throw new DadoInconsistenteException("O codigo do usuário não é válido.");
			}
			findByCodigo(codUsuario);
		}else {
			if(Util.isNotEmpty(usuario.getCodigo()) ) {
				valido = false;
				throw new DadoInconsistenteException("O codigo do usuário não é válido.");
			}
		}
		
		if (Util.isEmpty(usuario.getLogin()) 
				|| Util.isEmpty(usuario.getNome())
					|| Util.isEmpty(usuario.getEmail())) {
			valido = false;
			throw new DadoInconsistenteException("Os campos nome, login e email são obrigatórios.");
		} 
		if (!EmailValidator.validate(usuario.getEmail())) {
			throw new DadoInconsistenteException("O campo email deve ser um e-mail válido!.");
		}
		if (usuario.getPerfil() == null
				|| Util.isEmpty(usuario.getPerfil().getCodigo())) {
			throw new DadoInconsistenteException("O perfil é obrigatório.");
		}
		Perfil perfilUsuario = perfilDao.findByCodigo(usuario.getPerfil().getCodigo());
		if (perfilUsuario == null) {
			throw new DadoInconsistenteException("O usuário não tem permissão para cadastros com o perfil selecionado.");
		}
		return valido;
	}

	private void validarNovoUsuario(String login) {
		if (!EmailValidator.validate(login)) {
			throw new DadoInconsistenteException("O login de um usuário deve ser um e-mail válido!");
		}
		Usuario usuario = usuarioDao.findByLoginIgnoreCase(login);
		if (usuario != null) {
			throw new DadoInconsistenteException("O login \""+usuario+"\" já está em uso!");
		}
	}
 
 
	public int alterarStatus(Integer codUsuario, Boolean excluido) {
		findByCodigo(codUsuario);
		try {
			return usuarioDao.updateStatus(codUsuario, excluido, new Date());
		} catch (Exception e) {
			throw new OperacaoNaoRealizadaException(e.toString());
		}
	}

}
