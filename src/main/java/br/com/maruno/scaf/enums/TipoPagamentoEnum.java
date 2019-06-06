/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.enums;
 
public enum TipoPagamentoEnum {

	           A_VISTA(1, "À Vista")
	,        PARCELADO(2, "Parcelado")
	, PARCELADO_CARTAO(3, "Parcelado no Cartão de Crédito")
	;
	
	private Integer codigo;
	private String nome;
	
	private TipoPagamentoEnum(Integer codigo, String nome) {
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
	
	public static TipoPagamentoEnum get(Integer codigo) {
		for(TipoPagamentoEnum other: TipoPagamentoEnum.values()) {
			if(other.is(codigo)) {
				return other;
			}
		}
		return null;
	}

	public static String getNome(Integer codigo) {
		TipoPagamentoEnum other = TipoPagamentoEnum.get(codigo);
		return other == null ? null : other.getNome();
	}

	public static boolean in(Integer codigo, TipoPagamentoEnum... v) {
		for(TipoPagamentoEnum other: v) {
			if(other.is(codigo)) {
				return true;
			}
		}
		return false;
	}
	
}
