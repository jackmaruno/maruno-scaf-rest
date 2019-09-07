/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.maruno.app.domain.Recurso;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:56:54 AM
 * @Pacote br.com.maruno.app.persistence
 * @Nome RecursoDao.java
 * @NomeCompleto br.com.maruno.app.persistence.RecursoDao.java
 */
public interface RecursoDao extends JpaRepository<Recurso, Integer> {
	 
	@Query("SELECT recurso FROM Recurso recurso WHERE UPPER(recurso.url) = :urlRecurso")
	Recurso findRecurso( @Param("urlRecurso") String urlRecurso); 
}