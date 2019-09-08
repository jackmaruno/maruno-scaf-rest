/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.exceptions;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:54:41 AM
 * @Pacote br.com.maruno.app.exceptions
 * @Nome UsuarioNaoAutorizadoException.java
 * @NomeCompleto br.com.maruno.app.exceptions.UsuarioNaoAutorizadoException.java
 */
public class SessaoExpiradaException extends Exception {
	private static final long serialVersionUID = 1l;

	/**
	 * @param mensagem
	 */
	public SessaoExpiradaException(String mensagem) {
		super(mensagem);
	}

	/**
	 * @param mensagem
	 * @param causa
	 */
	public SessaoExpiradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
