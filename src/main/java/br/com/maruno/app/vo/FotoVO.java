/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.vo;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:00:42 AM
 * @Pacote br.com.maruno.app.vo
 * @Nome FotoVO.java
 * @NomeCompleto br.com.maruno.app.vo.FotoVO.java
 */
public class FotoVO {

	private String foto;

	public FotoVO() {}
	
	public FotoVO(String foto) {
		super();
		this.foto = foto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	} 
}
