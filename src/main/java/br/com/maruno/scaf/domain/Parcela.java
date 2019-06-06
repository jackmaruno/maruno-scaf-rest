/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.maruno.app.domain.Domain;
  
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:09:13 AM
 * @Pacote br.com.maruno.scaf.domain
 * @Nome Parcela.java
 * @NomeCompleto br.com.maruno.scaf.domain.Parcela.java
 */
@Entity
@Table(name = "TB_PARCELA", schema = Domain.SCHEMA)
public class Parcela extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COD_PARCELA")
	private Integer codigo;  

	@JsonIgnore
	@Column(name = "COD_LANCAMENTO")
	private Integer codLancamento;  
	
	@Column(name = "NUM_PARCELA")
	private Integer numero;  
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Digits(integer = 8, fraction = 2, message = "Formato inválido para o campo valor. Ex: ##########.##")
	@NotNull(message = "O campo \"valor\" é obrigatório.")
	@Column(name = "VAL_PARCELA")
	private BigDecimal valor;  
	
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@NotNull(message = "O campo \"data\" é obrigatório.")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_PARCELA")
	private Date data;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private Lancamento lancamento;
	
	public Parcela() {}
	
	public Parcela(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodLancamento() {
		return codLancamento;
	}

	public void setCodLancamento(Integer codLancamento) {
		this.codLancamento = codLancamento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{codigo:");
		builder.append(codigo);
		builder.append(", codLancamento:");
		builder.append(codLancamento);
		builder.append(", numero:");
		builder.append(numero);
		builder.append(", valor:");
		builder.append(valor);
		builder.append(", data:");
		builder.append(data);
		builder.append("}");
		return builder.toString();
	}
 
 
}
