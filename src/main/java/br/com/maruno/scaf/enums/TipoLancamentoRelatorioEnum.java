/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.enums;

import java.util.ArrayList;
import java.util.List;

import br.com.maruno.scaf.vo.TipoLancamentoRelatorio;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:10:51 AM
 * @Pacote br.com.maruno.scaf.enums
 * @Nome TipoLancamentoRelatorioEnum.java
 * @NomeCompleto br.com.maruno.scaf.enums.TipoLancamentoRelatorioEnum.java
 */
public enum TipoLancamentoRelatorioEnum {

	  PAGAMENTOS_AGENDADOS(1, "Pagamentos Agendados")
	,             PARCELAS(2, "Parcelas")
	,      DESPESAS_GERAIS(3, "Despesas Gerais")
	,             RECEITAS(4, "Receitas")
	,              FATURAS(5, "Faturas")
	;
 
	private Integer codigo;
	private String nome;
	
	private TipoLancamentoRelatorioEnum(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public boolean is(Integer codigo) {
		return this.codigo.equals(codigo);
	}
	
	public static TipoLancamentoRelatorioEnum get(Integer codigo) {
		for(TipoLancamentoRelatorioEnum other: TipoLancamentoRelatorioEnum.values()) {
			if(other.is(codigo)) {
				return other;
			}
		}
		return null;
	}
	
	public static List<TipoLancamentoRelatorio> getvalues() {
		List<TipoLancamentoRelatorio> lista = new ArrayList<TipoLancamentoRelatorio>();
		for(TipoLancamentoRelatorioEnum e: TipoLancamentoRelatorioEnum.values()) {
			lista.add(new TipoLancamentoRelatorio(e.getCodigo(), e.getNome()));
		}
		return lista;
	}

	public static String getNome(Integer codigo) {
		TipoLancamentoRelatorioEnum other = TipoLancamentoRelatorioEnum.get(codigo);
		return other == null ? null : other.getNome();
	}
 
	
}
