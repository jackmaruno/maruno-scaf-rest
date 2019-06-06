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

import br.com.maruno.scaf.domain.vw.VWRelatorio;
   
/**
 * @Aplicativo 
 * @Modulo scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Pacote br.com.maruno.scaf.persistence.vw
 * @Nome VWRelatorioDao.java
 * @NomeCompleto br.com.maruno.scaf.persistence.vw.VWRelatorioDao.java
 */
public interface VWRelatorioDao extends JpaRepository<VWRelatorio, Integer> { 

	@Query("SELECT vw FROM VWRelatorio vw WHERE vw.codUsuario = :codUsuario" )
	List<VWRelatorio> find(@Param("codUsuario") Integer codUsuario);

	@Query(" SELECT DISTINCT l.anoMes FROM VWRelatorio l WHERE l.codUsuario = :codUsuario" )
	List<String> findCompetencias(@Param("codUsuario") Integer codUsuario);
}
