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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.maruno.app.domain.Domain;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:12:00 AM
 * @Pacote br.com.maruno.scaf.domain.vw
 * @Nome VWAtividadeMes.java
 * @NomeCompleto br.com.maruno.scaf.domain.vw.VWAtividadeMes.java
 */
@Entity
@Table(name = "VW_RESUMO_MES")//, schema = Domain.SCHEMA)
public class VWAtividadeMes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@Column(name = "ID")
	private String codigo;  

	@JsonIgnore
	@Column(name = "COD_USUARIO")
	private Integer codUsuario;  
	
	@Column(name = "COD_TIPO")
	private Integer codTipo;  
	
	@Column(name = "DES_LANCAMENTOS")
	private String descricao;  

	@Column(name = "VAL_PREVISTO")
	private BigDecimal valorPrevisto;

	@Column(name = "VAL_PAGO")
	private BigDecimal valorPago;  
	

	
	public VWAtividadeMes() {}

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

	public Integer getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
