/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.vo;

import java.io.Serializable;

/**
 * @Aplicativo 
 * @Modulo scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Mar 5, 2019 4:25:51 PM
 * @Pacote br.com.maruno.scaf.vo
 * @Nome TipoLancamentoRelatorio.java
 * @NomeCompleto br.com.maruno.scaf.vo.TipoLancamentoRelatorio.java
 */
public class TipoLancamentoRelatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;  
	private String nome;
	
	public TipoLancamentoRelatorio() {
		super();
	}
	
	public TipoLancamentoRelatorio(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", nome:");
		builder.append(nome);
		builder.append("}");
		return builder.toString();
	}  
	
	
}
