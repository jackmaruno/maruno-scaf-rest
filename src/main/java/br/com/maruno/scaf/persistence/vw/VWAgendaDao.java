/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.persistence.vw;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.maruno.scaf.domain.vw.VWAgenda;
   
/**
 * @Aplicativo 
 * @Modulo scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Pacote br.com.maruno.scaf.persistence.vw
 * @Nome VWAgendaDao.java
 * @NomeCompleto br.com.maruno.scaf.persistence.vw.VWAgendaDao.java
 */
public interface VWAgendaDao extends JpaRepository<VWAgenda, String> { 

	@Query("SELECT vw FROM VWAgenda vw WHERE vw.codUsuario = :codUsuario ORDER BY vw.dataPrevista, vw.nomeCategoria")
	List<VWAgenda> find(@Param("codUsuario") Integer codUsuario);
 
}
