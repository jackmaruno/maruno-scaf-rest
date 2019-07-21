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

import br.com.maruno.app.domain.AccessToken;
import br.com.maruno.app.domain.Domain;

/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:57:11 AM
 * @Pacote br.com.maruno.app.persistence
 * @Nome AccessTokenDao.java
 * @NomeCompleto br.com.maruno.app.persistence.AccessTokenDao.java
 */
public interface AccessTokenDao extends JpaRepository<AccessToken, String> {
	
	@Query("SELECT a FROM AccessToken a WHERE a.access_token = ?1 ")
	AccessToken findAccessToken(String access_token); 
	
	@Query(value =  
		"\n SELECT TOKEN.* " + 
		"\n FROM TB_ACCES_TOKEN TOKEN" + 
		"\n" + 
		"\n  INNER JOIN TB_USUARIO USUARIO ON USUARIO.COD_USUARIO = TOKEN.COD_USUARIO  " + 
		"\n  INNER JOIN TB_PERFIL PERFIL ON PERFIL.COD_PERFIL = USUARIO.COD_PERFIL " + 
		"\n  INNER JOIN RL_PERFIL_RECURSO RL_RECURSO ON RL_RECURSO.COD_PERFIL = PERFIL.COD_PERFIL " + 
		"\n  INNER JOIN TB_RECURSO RECURSO ON RECURSO.COD_RECURSO = RL_RECURSO.COD_RECURSO " + 
		"\n" + 
		"\n WHERE RECURSO.IND_EXCLUIDO = 0 "+
		"\n AND TOKEN.DES_ACCES_TOKEN = :access_token " +
		"\n AND UPPER(RECURSO.URL_RECURSO) = :urlRecurso " +
		"\n AND UPPER(RL_RECURSO.NOM_METODO) = :metodo "
		, nativeQuery = true)
	AccessToken findAccessToken(@Param("access_token") String access_token, 
			                    @Param("urlRecurso")   String urlRecurso, 
			                    @Param("metodo")       String metodo);
 
}
