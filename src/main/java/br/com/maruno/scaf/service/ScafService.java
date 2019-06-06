/*
 * Direitos Autorais 2018 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.service;

import br.com.maruno.app.support.Util;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:40:59 AM
 * @Pacote br.com.maruno.scaf.service
 * @Nome ScafService.java
 * @NomeCompleto br.com.maruno.scaf.service.ScafService.java
 */
public abstract class ScafService {

	
	protected Integer tratarValor(Integer valor) {
		if(Util.isEmpty(valor)) {
			return 0;
		}
		return valor;
	}

	protected String tratarValor(String valor) {
		if(Util.isEmpty(valor) || valor.trim().length() < 4){ 
			return "";
		}
		return valor;
	}
}
