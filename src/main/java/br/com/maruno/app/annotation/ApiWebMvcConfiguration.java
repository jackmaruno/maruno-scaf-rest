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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
/**
 * @Aplicativo 
 * @Modulo maruno-scaf-rest
 * @Autor <a href="mailto:jackmaruno@gmail.com">Willian.Maruno</a>
 * @Data Jun 6, 2019 10:52:55 AM
 * @Pacote br.com.maruno.app.annotation
 * @Nome ApiWebMvcConfiguration.java
 * @NomeCompleto br.com.maruno.app.annotation.ApiWebMvcConfiguration.java
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) 
@Configuration
@ComponentScan(useDefaultFilters = false, includeFilters = @Filter({ Controller.class }))
@EnableWebMvc
public @interface ApiWebMvcConfiguration {

	 @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	 String[] value() default ""; 
}
