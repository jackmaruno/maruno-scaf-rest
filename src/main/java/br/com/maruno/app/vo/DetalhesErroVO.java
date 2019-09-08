/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Sep 8, 2019 4:25:43 PM
 * @Pacote br.com.maruno.app.vo
 * @Nome DetalhesErroVO.java
 * @NomeCompleto br.com.maruno.app.vo.DetalhesErroVO.java
 */
public class DetalhesErroVO {

	private String titulo;
	private int status;
	private String detalhe;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String urlAlteracaoSenha;

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetalhe() {
		return this.detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public String getUrlAlteracaoSenha() {
		return this.urlAlteracaoSenha;
	}

	public void setUrlAlteracaoSenha(String urlAlteracaoSenha) {
		this.urlAlteracaoSenha = urlAlteracaoSenha;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{titulo:");
		builder.append(titulo);
		builder.append(", status:");
		builder.append(status);
		builder.append(", detalhe:");
		builder.append(detalhe);
		builder.append(", urlAlteracaoSenha:");
		builder.append(urlAlteracaoSenha);
		builder.append("}");
		return builder.toString();
	}
	
}
