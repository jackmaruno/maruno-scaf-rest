/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.maruno.app.domain.Domain;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:01:10 AM
 * @Pacote br.com.maruno.scaf.domain
 * @Nome Periodo.java
 * @NomeCompleto br.com.maruno.scaf.domain.Periodo.java
 */
@Entity
@Table(name = "TB_PERIODO")//, schema = Domain.SCHEMA)
public class Periodo extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NUM_PERIODO")
	private Integer numero;  
	
	@Column(name = "NOM_PERIODO")
	private String nome;  
 
	
	public Periodo() {}
	
	public Periodo(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{numero:");
		builder.append(numero);
		builder.append(", nome:");
		builder.append(nome);
		builder.append("}");
		return builder.toString();
	}
  
}
