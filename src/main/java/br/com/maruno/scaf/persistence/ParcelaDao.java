/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.scaf.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.maruno.app.domain.Domain;
import br.com.maruno.scaf.domain.Parcela;
    
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 11:14:22 AM
 * @Pacote br.com.maruno.scaf.persistence
 * @Nome ParcelaDao.java
 * @NomeCompleto br.com.maruno.scaf.persistence.ParcelaDao.java
 */
public interface ParcelaDao extends JpaRepository<Parcela, Integer> { 

	List<Parcela> findByCodLancamento(Integer codLancamento);

//	@Query(value = " SELECT * FROM TB_PARCELA WHERE COD_PARCELA = :codParcela", nativeQuery = true )
//	Parcela findByCodigo(@Param("codParcela") Integer codParcela);

	@Query(value = " SELECT p.* FROM TB_PARCELA p"
		         + " INNER JOIN TB_LANCAMENTO l ON l.COD_LANCAMENTO = p.COD_LANCAMENTO"
		         + " WHERE l.IND_EXCLUIDO = 0"
		         + " AND l.COD_USUARIO = :codUsuario"
				 + " AND p.COD_PARCELA = :codParcela"  , nativeQuery = true)	
	Parcela findParcelaById(@Param("codUsuario") Integer codUsuario, @Param("codParcela") Integer codParcela);

	@Query(value = " SELECT p.* FROM TB_PARCELA p"
		         + " INNER JOIN TB_LANCAMENTO l ON l.COD_LANCAMENTO = p.COD_LANCAMENTO"
		         + " LEFT JOIN TB_LANCAMENTO l2 ON l2.COD_PARCELA = p.COD_PARCELA"
		         + " WHERE l2.COD_LANCAMENTO IS NULL "
		         + " AND l.IND_EXCLUIDO = 0"
		         + " AND l.COD_USUARIO = :codUsuario"
				 + " AND (l.COD_TIPO_PAGAMENTO = 2 Or l.COD_CATEGORIA = 63)"
				 + " AND (:anoMes = '' OR TO_CHAR(p.DAT_PARCELA,'YYYYMM') = :anoMes) "
				 + " ORDER BY p.DAT_PARCELA, l.DAT_LANCAMENTO", nativeQuery = true)	
	List<Parcela> findParcelasPendentes(@Param("codUsuario") Integer codUsuario, @Param("anoMes") String anoMes);
	
	@Query(value = " SELECT p.* FROM TB_PARCELA p"
			+ " INNER JOIN TB_LANCAMENTO l ON l.COD_LANCAMENTO = p.COD_LANCAMENTO"
			+ " LEFT JOIN TB_LANCAMENTO l2 ON l2.COD_PARCELA = p.COD_PARCELA"
			+ " WHERE l2.COD_LANCAMENTO IS NOT NULL "
	        + " AND l.IND_EXCLUIDO = 0"
			+ " AND l.COD_USUARIO = :codUsuario"
			+ " AND (l.COD_TIPO_PAGAMENTO = 2 Or l.COD_CATEGORIA = 63)"
			+ " AND (:anoMes = '' OR TO_CHAR(p.DAT_PARCELA,'YYYYMM') = :anoMes) "
			+ " ORDER BY p.DAT_PARCELA, l.DAT_LANCAMENTO", nativeQuery = true)	
	List<Parcela> findParcelasPagas(@Param("codUsuario") Integer codUsuario, @Param("anoMes") String anoMes);
	
	@Query(value = " SELECT DISTINCT TO_CHAR(p.DAT_PARCELA,'YYYYMM') AS ANO_MES, TO_CHAR(p.DAT_PARCELA,'MM/YYYY') AS DATA "
			+ " FROM TB_PARCELA p"
			+ " INNER JOIN TB_LANCAMENTO l ON l.COD_LANCAMENTO = p.COD_LANCAMENTO"
			+ " LEFT JOIN TB_LANCAMENTO l2 ON l2.COD_PARCELA = p.COD_PARCELA"
			+ " WHERE l2.COD_LANCAMENTO IS NULL "
	        + " AND l.IND_EXCLUIDO = 0"
			+ " AND l.COD_USUARIO = :codUsuario"
			+ " AND (l.COD_TIPO_PAGAMENTO = 2 Or l.COD_CATEGORIA = 63)"
			+ " ORDER BY 1" , nativeQuery = true)	
	List<Object[]> findDatasParcelasPendentes(@Param("codUsuario") Integer codUsuario);
	
	@Query(value = " SELECT p.* FROM TB_PARCELA p"
			+ " INNER JOIN TB_LANCAMENTO l ON l.COD_LANCAMENTO = p.COD_LANCAMENTO"
	        + " WHERE l.IND_EXCLUIDO                      = 0"
	        + " AND l.COD_USUARIO                         = :codUsuario"
			+ " AND l.COD_CARTAO_CREDITO                  = :codCartaoCredito"
			+ " AND TO_CHAR(p.DAT_PARCELA,'DD/MM/YYYY')   = :data"
			+ " ORDER BY p.DAT_PARCELA, l.DAT_LANCAMENTO", nativeQuery = true)	
	List<Parcela> findByParameters(@Param("codUsuario") Integer codUsuario, @Param("codCartaoCredito") Integer codCartaoCredito, @Param("data") String data);
	
	@Query(value = " SELECT p.* "
			     + " FROM TB_PARCELA p"
				 + " 	INNER JOIN RL_LANCAMENTO_PARCELA RL ON RL.COD_PARCELA = p.COD_PARCELA"
				 + " WHERE RL.COD_LANCAMENTO      = :codLancamento" 
				 + " ORDER BY p.DAT_PARCELA", nativeQuery = true)	
	List<Parcela> findByFatura(@Param("codLancamento") Integer codLancamento);

	@Modifying
	@Transactional
	@Query(value = "  MERGE INTO RL_LANCAMENTO_PARCELA RL ("
				+ "\n     SELECT :codLancamento  AS COD_LANCAMENTO"
				+ "\n          , :codParcela     AS COD_PARCELA" 
				+ "\n     FROM DUAL"
				+ "\n) TP  ON (RL.COD_LANCAMENTO = TP.COD_LANCAMENTO AND RL.COD_PARCELA = TP.COD_PARCELA) "
				+ "\n WHEN NOT MATCHED THEN "
				+ "\n INSERT (RL.COD_LANCAMENTO, RL.COD_PARCELA) "
				+ "\n VALUES (TP.COD_LANCAMENTO, TP.COD_PARCELA)", nativeQuery = true)
	int saveLancamentoParcela(@Param("codLancamento") Integer codLancamento, @Param("codParcela") Integer codParcela);
	
	@Modifying
	@Transactional
	@Query(value =  " INSERT INTO RL_LANCAMENTO_PARCELA (COD_LANCAMENTO, COD_PARCELA) \r\n" + 
			" VALUES (:codLancamento, :codParcela)  \r\n" + 
			" ON DUPLICATE KEY UPDATE COD_PARCELA = COD_PARCELA" , nativeQuery = true)	
	int saveLancamentoParcelaMYSQL(@Param("codLancamento") Integer codLancamento, @Param("codParcela") Integer codParcela);
	
	
	
	
}
