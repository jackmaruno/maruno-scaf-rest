/*
 * Direitos Autorais 2019 Willian Ferreira Maruno
 * 
 * Este sistema e confidencial e de propriedade do desenvolvedor Willian Ferreira Maruno.
 * A divulgacao das informacoes contidas aqui somente serao aceitas
 * mediante acordo previo estabelecido para com o desenvolvedor.
 */
package br.com.maruno.app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:53:27 AM
 * @Pacote br.com.maruno.app.annotation
 * @Nome ApiConfiguration.java
 * @NomeCompleto br.com.maruno.app.annotation.ApiConfiguration.java
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
@Configuration
@ComponentScan
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
@EnableAsync
@EnableJpaRepositories
@EnableTransactionManagement
public @interface ApiConfiguration {

	 @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	 String[] component() default "";
	 
	 @AliasFor(annotation = EnableJpaRepositories.class, attribute = "basePackages")
	 String[] persistence() default "";
}
