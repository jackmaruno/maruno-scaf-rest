/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.handler;

import java.io.FileNotFoundException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.maruno.app.exceptions.AccesTokenException;
import br.com.maruno.app.exceptions.AccessoNegadoException;
import br.com.maruno.app.exceptions.ClienteInvalidoException;
import br.com.maruno.app.exceptions.DadoInconsistenteException;
import br.com.maruno.app.exceptions.GrantTypeException;
import br.com.maruno.app.exceptions.MetodoException;
import br.com.maruno.app.exceptions.OperacaoNaoRealizadaException;
import br.com.maruno.app.exceptions.RecursoNaoEncontradoException;
import br.com.maruno.app.exceptions.RefreshTokenInvalidoException;
import br.com.maruno.app.exceptions.SessaoExpiradaException;
import br.com.maruno.app.exceptions.TrocaSenhaException;
import br.com.maruno.app.exceptions.UsuarioInvalidoException;
import br.com.maruno.app.exceptions.UsuarioNaoAutorizadoException;
import br.com.maruno.app.vo.DetalhesErroVO;
 

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Sep 8, 2019 4:24:56 PM
 * @Pacote br.com.maruno.app.handler
 * @Nome ResourceExceptionHandler.java
 * @NomeCompleto br.com.maruno.app.handler.ResourceExceptionHandler.java
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	  public static final int MOVED_TEMPORARILY = 3202;
	  
	  public static final int BAD_REQUEST = 400;
	  public static final int UNAUTHORIZED = 401;
	  public static final int FORBIDDEN = 403;
	  public static final int NOT_FOUND = 404;
	  public static final int METHOD_NOT_ALLOWED = 405;
	  public static final int INTERNAL_SERVER_ERROR = 500;
	  
	@ExceptionHandler({ AccessoNegadoException.class })
	public ResponseEntity<DetalhesErroVO> handleAccessoNegadoException(AccessoNegadoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(FORBIDDEN);
		erro.setTitulo("acesso_nao_permitido");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(FORBIDDEN).body(erro);
	}

	@ExceptionHandler({ AccesTokenException.class })
	public ResponseEntity<DetalhesErroVO> handleAccesTokenException(AccesTokenException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(UNAUTHORIZED);
		erro.setTitulo("invalid_token");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(UNAUTHORIZED).body(erro);
	}

	@ExceptionHandler({ ClienteInvalidoException.class })
	public ResponseEntity<DetalhesErroVO> handleClienteInvalidoException(ClienteInvalidoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(UNAUTHORIZED);
		erro.setTitulo("invalid_client");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(UNAUTHORIZED).body(erro);
	}

	@ExceptionHandler({ DadoInconsistenteException.class })
	public ResponseEntity<DetalhesErroVO> handleDadoInconsistenteException(DadoInconsistenteException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(BAD_REQUEST);
		erro.setTitulo("Requisição inválida.");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(BAD_REQUEST).body(erro);
	}

	@ExceptionHandler({ GrantTypeException.class })
	public ResponseEntity<DetalhesErroVO> handleGrantTypeException(GrantTypeException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(BAD_REQUEST);
		erro.setTitulo("invalid_grant");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(BAD_REQUEST).body(erro);
	}

	@ExceptionHandler({ MetodoException.class })
	public static ResponseEntity<DetalhesErroVO> handleMetodoException(MetodoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(METHOD_NOT_ALLOWED);
		erro.setTitulo("metodo_nao_permitido");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(METHOD_NOT_ALLOWED).body(erro);
	}

	@ExceptionHandler({ OperacaoNaoRealizadaException.class })
	public ResponseEntity<DetalhesErroVO> handleOperacaoNaoRealizadaException(OperacaoNaoRealizadaException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(INTERNAL_SERVER_ERROR);
		erro.setTitulo("Operação não realizada.");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(erro);
	}
	
	@ExceptionHandler({ RecursoNaoEncontradoException.class })
	public ResponseEntity<DetalhesErroVO> handleLivroNaoEncontradoException(RecursoNaoEncontradoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(NOT_FOUND);
		erro.setTitulo("O recurso solicitado não foi localizado.");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(NOT_FOUND).body(erro);
	}

	@ExceptionHandler({ RefreshTokenInvalidoException.class })
	public ResponseEntity<DetalhesErroVO> handleRefreshTokenInvalidoException(RefreshTokenInvalidoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(UNAUTHORIZED);
		erro.setTitulo("invalid_Refresh_token");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(UNAUTHORIZED).body(erro);
	}

	@ExceptionHandler({ SessaoExpiradaException.class })
	public ResponseEntity<DetalhesErroVO> handleSessaoExpiradaException(SessaoExpiradaException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(UNAUTHORIZED);
		erro.setTitulo("Sessão Expirada");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(UNAUTHORIZED).body(erro);
	}

	@ExceptionHandler({ TrocaSenhaException.class })
	public ResponseEntity<DetalhesErroVO> handleTrocaSenhaException(TrocaSenhaException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(MOVED_TEMPORARILY);
		erro.setTitulo("Alteração de Senha Obrigatória.");
		erro.setDetalhe("O usuário deve redefinir sua senha!");
		erro.setUrlAlteracaoSenha(e.getMessage());
		return ResponseEntity.status(MOVED_TEMPORARILY).body(erro);
	}

	@ExceptionHandler({ UsuarioInvalidoException.class })
	public ResponseEntity<DetalhesErroVO> handleUsuarioInvalidoException(UsuarioInvalidoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(BAD_REQUEST);
		erro.setTitulo("invalid_grant");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(BAD_REQUEST).body(erro);
	}

	@ExceptionHandler({ UsuarioNaoAutorizadoException.class })
	public ResponseEntity<DetalhesErroVO> handleUsuarioNaoAutorizadoException(UsuarioNaoAutorizadoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(UNAUTHORIZED);
		erro.setTitulo("unauthorized_client");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(UNAUTHORIZED).body(erro);
	}
	
	
	
	

	@ExceptionHandler({ FileNotFoundException.class })
	public ResponseEntity<DetalhesErroVO> FileNotFoundException(RecursoNaoEncontradoException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(NOT_FOUND);
		erro.setTitulo("O recurso solicitado não foi localizado.");
		erro.setDetalhe("A URI solicitada não existe");
		return ResponseEntity.status(NOT_FOUND).body(erro);
	}

	@ExceptionHandler({ NamingException.class })
	public ResponseEntity<DetalhesErroVO> handleNamingException(NamingException e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(INTERNAL_SERVER_ERROR);
		erro.setTitulo("Operação não realizada.");
		erro.setDetalhe(e.getMessage() + e.getStackTrace().toString());
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(erro);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<DetalhesErroVO> handleException(Exception e, HttpServletRequest request) {
		DetalhesErroVO erro = new DetalhesErroVO();
		erro.setStatus(INTERNAL_SERVER_ERROR);
		erro.setTitulo("Erro interno.");
		erro.setDetalhe(e.getMessage());
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(erro);
	}

}
