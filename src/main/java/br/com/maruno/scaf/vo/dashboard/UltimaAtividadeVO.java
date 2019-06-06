/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.vo.dashboard;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.maruno.app.support.Util;
 
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:12:15 AM
 * @Pacote br.com.maruno.scaf.vo.dashboard
 * @Nome UltimaAtividadeVO.java
 * @NomeCompleto br.com.maruno.scaf.vo.dashboard.UltimaAtividadeVO.java
 */
public class UltimaAtividadeVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codLancamento;  
	private String nomeCategoria;  
	private BigDecimal valor;  
	
	private String data;    
	private Integer dias;  
	private Integer horas;  
	private Integer minutos;  
	

	
	public UltimaAtividadeVO() {}


	/**
	 * @param dados
	 */
	public UltimaAtividadeVO(Object[] dados) {
		int i = -1;
		this.codLancamento = Util.getInteger(dados, ++i);
		this.nomeCategoria = Util.getString(dados, ++i);
		this.valor = Util.getBigDecimal(dados, ++i);
		
		this.data = Util.getString(dados, ++i);
		this.dias = Util.getInteger(dados, ++i);
		this.horas = Util.getInteger(dados, ++i);
		this.minutos = Util.getInteger(dados, ++i);
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Integer getHoras() {
		return horas;
	}

	public void setHoras(Integer horas) {
		this.horas = horas;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public Integer getCodLancamento() {
		return codLancamento;
	}

	public void setCodLancamento(Integer codLancamento) {
		this.codLancamento = codLancamento;
	}

}
