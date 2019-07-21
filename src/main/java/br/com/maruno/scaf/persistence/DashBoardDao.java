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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.maruno.app.domain.Domain;
import br.com.maruno.scaf.domain.Lancamento;
   
/**
 * @Aplicativo 
 * @Modulo scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Pacote br.com.maruno.scaf.persistence
 * @Nome DashBoardDao.java
 * @NomeCompleto br.com.maruno.scaf.persistence.DashBoardDao.java
 */
public interface DashBoardDao extends JpaRepository<Lancamento, Integer> { 
 
    @Query(nativeQuery = true, 
    value = " select COD_LANCAMENTO, "+
    		"        DES_CATEGORIA, "+
    		"        VAL_LANCAMENTO, "+
    		"        TO_CHAR(DAT_LANCAMENTO,'DD/MM/YYYY') AS DAT_LANCAMENTO, "+
    		"        NUM_DIAS, "+
    		"        NUM_HORAS, "+
    		"        NUM_MINUTOS "+
			" from VW_ULTIMAS_ATIVIDADES "+ 
			" where ANO_MES = :competencia "+ 
		    " and COD_USUARIO = :codUsuario ")	
    List<Object[]> findUltimasAtividades(@Param("codUsuario") Integer codUsuario, @Param("competencia") String competencia);
    
	@Query(value = " " + 
			"  SELECT PARCELA.DAT_PARCELA AS DAT_PREVISTA " + 
			"      , 0 AS COD_AGENDAMENTO" + 
			"      , PARCELA.COD_PARCELA " + 
			"      , 0 AS COD_CARTAO_CREDITO" + 
			"      , '' AS NOME" + 
			"      , 46 AS COD_CATEGORIA " + 
			"      , concat('Parcela N ', PARCELA.NUM_PARCELA, ' (', CATEGORIA.NOM_CATEGORIA, ')') AS DES_CATEGORIA " + 
			"      , PARCELA.VAL_PARCELA AS VAL_PREVISTO " + 
			"	   , LANCAMENTO_PAGAMENTO.DAT_REFERENCIA AS DAT_PAGAMENTO " + 
			"      , LANCAMENTO_PAGAMENTO.VAL_LANCAMENTO AS VAL_PAGO " + 
			"      , timestampdiff(DAY,now(),PARCELA.DAT_PARCELA) AS DIAS " + 
			"\n" + 
			" FROM TB_PARCELA PARCELA\n" + 
			"\n" + 
			"	INNER JOIN TB_LANCAMENTO LANCAMENTO ON LANCAMENTO.COD_LANCAMENTO = PARCELA.COD_LANCAMENTO\n" + 
			"\n" + 
			"	INNER JOIN TB_CATEGORIA CATEGORIA ON CATEGORIA.COD_CATEGORIA = LANCAMENTO.COD_CATEGORIA\n" + 
			"\n" + 
			"	INNER JOIN TB_LANCAMENTO LANCAMENTO_PAGAMENTO ON LANCAMENTO_PAGAMENTO.COD_PARCELA = PARCELA.COD_PARCELA\n" + 
			"\n" + 
			"\n WHERE LANCAMENTO.COD_USUARIO = :codUsuario \n" + 
			"\n AND LANCAMENTO.COD_CARTAO_CREDITO IS NULL\n" + 
			"\n AND ( TO_CHAR(NOW(),'YYYYMM') = TO_CHAR(PARCELA.DAT_PARCELA,'YYYYMM')\n" + 
			"\n		or ((PARCELA.DAT_PARCELA < concat(TO_CHAR(NOW(),'YYYY-MM'),'-01')) and LANCAMENTO_PAGAMENTO.DAT_REFERENCIA IS NULL)\n" + 
			"\n    )", nativeQuery = true)	
	List<Object[]> findParcelas(@Param("codUsuario") Integer codUsuario);
	
	@Query(value = "" + 
	        " select FATURAS.DAT_FATURA \n" + 
			"      , NULL AS COD_AGENDAMENTO\n" + 
			"      , NULL AS COD_PARCELA\n" + 
			"      , FATURAS.COD_CARTAO_CREDITO \n" + 
			"      , concat(FATURAS.NOM_BANDEIRA,' - ', FATURAS.NOM_CARTAO_CREDITO) AS NOME\n" + 
			"      , CATEGORIA.COD_CATEGORIA \n" + 
			"      , concat(CATEGORIA.NOM_CATEGORIA, '(', FATURAS.NOM_BANDEIRA, ' - ', FATURAS.NOM_CARTAO_CREDITO, ')') AS DES_CATEGORIA\n" + 
			"      , FATURAS.VAL_PARCELAS_PENDENTES AS VAL_PREVISTO\n" + 
			"      , FATURAS.DAT_REFERENCIA AS DAT_PAGAMENTO\n" + 
			"      , FATURAS.VAL_LANCAMENTO AS VAL_PAGO\n" + 
			"      , timestampdiff(DAY,now(),FATURAS.DAT_FATURA) AS DIAS \n" + 
			"\n" + 
			" FROM VW_FATURAS_PREVISTAS_REALIZADAS FATURAS\n" + 
			"\n" + 
			"	INNER JOIN TB_CATEGORIA CATEGORIA ON CATEGORIA.COD_CATEGORIA = 48\n" + 
			"\n" + 
			" WHERE FATURAS.COD_USUARIO = :codUsuario  \n" + 
			" AND FATURAS.DAT_REFERENCIA IS NULL \n" + 
			" AND (\n" + 
			"  TO_CHAR(NOW(),'YYYYMM') = TO_CHAR(FATURAS.DAT_FATURA,'YYYYMM')\n" + 
			"  or (\n" + 
			"     FATURAS.DAT_FATURA < concat(TO_CHAR(NOW(),'YYYY-MM'),'-01') and FATURAS.DAT_REFERENCIA IS NULL\n" + 
			"  )\n" + 
			") \n" + 
			" AND FATURAS.QTD_PARCELAS_PENDENTES > 0\n" + 
			"\n" + 
			"\n" + 
			" order by DAT_PREVISTA,DES_CATEGORIA", nativeQuery = true)	
	List<Object[]> findFaturas(@Param("codUsuario") Integer codUsuario);
	
}
