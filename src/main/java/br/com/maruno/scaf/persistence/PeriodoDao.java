/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maruno.scaf.domain.Periodo;
    
/**
 * @Aplicativo 
 * @Modulo quaemo-scaf
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data 11 de set de 2018 12:56:54
 * @Pacote br.com.quaemo.scaf.persistence
 * @Nome PeriodoDao.java
 * @NomeCompleto br.com.quaemo.scaf.persistence.PeriodoDao.java
 */
public interface PeriodoDao extends JpaRepository<Periodo, Integer> { 
 
}
