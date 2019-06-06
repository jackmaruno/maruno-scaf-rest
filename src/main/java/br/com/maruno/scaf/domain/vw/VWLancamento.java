/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.domain.vw;

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
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.maruno.app.domain.Domain;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:11:49 AM
 * @Pacote br.com.maruno.scaf.domain.vw
 * @Nome VWLancamento.java
 * @NomeCompleto br.com.maruno.scaf.domain.vw.VWLancamento.java
 */
@Entity
@Table(name = "VW_RELACAO_LANCAMENTO", schema = Domain.SCHEMA)
public class VWLancamento extends Domain {
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_LANCAMENTO")
	private Integer codigo;  

	@JsonIgnore
	@Column(name = "COD_USUARIO")
	private Integer codUsuario;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_LANCAMENTO_PARCELA")
	private Integer codLancamentoParcela; 

	@Column(name = "ANO_MES")
	private String anoMes;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "COD_TIPO")
	private Integer codTipo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "DES_CATEGORIA")
	private String nomeCategoria;  

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NUM_PARCELA")
	private Integer numParcela; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "NOM_AGENDAMENTO")
	private String nomeAgendamento; 

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_LANCAMENTO")
	private Date dataLancamento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy", timezone="GMT-3")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DAT_REFERENCIA")
	private Date dataReferencia;

	@Column(name = "VAL_PREVISTO")
	private BigDecimal valorPrevisto;  

	@Column(name = "VAL_PAGO")
	private BigDecimal valorPago;  
	
	 
	public VWLancamento() {}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Integer getCodLancamentoParcela() {
		return codLancamentoParcela;
	}

	public void setCodLancamentoParcela(Integer codLancamentoParcela) {
		this.codLancamentoParcela = codLancamentoParcela;
	}

	public String getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public Integer getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	public String getNomeAgendamento() {
		return nomeAgendamento;
	}

	public void setNomeAgendamento(String nomeAgendamento) {
		this.nomeAgendamento = nomeAgendamento;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public BigDecimal getValorPrevisto() {
		return valorPrevisto;
	}

	public void setValorPrevisto(BigDecimal valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
 
}
