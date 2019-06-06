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
 * @Data Jun 6, 2019 10:55:06 AM
 * @Pacote br.com.maruno.app.exceptions
 * @Nome TrocaSenhaException.java
 * @NomeCompleto br.com.maruno.app.exceptions.TrocaSenhaException.java
 */
public class TrocaSenhaException extends RuntimeException {
	private static final long serialVersionUID = 1l;

	/**
	 * @param mensagem
	 */
	public TrocaSenhaException(String mensagem) {
		super(mensagem);
	}

	/**
	 * @param mensagem
	 * @param causa
	 */
	public TrocaSenhaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
