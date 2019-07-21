/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain.vw;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.maruno.app.domain.Domain;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:11:56 AM
 * @Pacote br.com.maruno.scaf.domain.vw
 * @Nome VWAtividadesMeses.java
 * @NomeCompleto br.com.maruno.scaf.domain.vw.VWAtividadesMeses.java
 */
@Entity
@Table(name = "VW_ATIVIDADES_MESES")//, schema = Domain.SCHEMA)
public class VWAtividadesMeses implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@Column(name = "ID")
	private String codigo;  

	@JsonIgnore
	@Column(name = "COD_USUARIO")
	private Integer codUsuario;  

	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_COMPETENCIA")
	private Date data;  

	@Column(name = "VAL_DESPESA_PAGA")
	private BigDecimal valorDespesa;

	@Column(name = "VAL_RECEITA_PAGA")
	private BigDecimal valorReceita;  
	

	
	public VWAtividadesMeses() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public BigDecimal getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(BigDecimal valorReceita) {
		this.valorReceita = valorReceita;
	}

}
