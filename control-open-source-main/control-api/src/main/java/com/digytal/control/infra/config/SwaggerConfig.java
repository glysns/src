package com.digytal.control.infra.config;

import com.digytal.control.infra.utils.Classes;
import com.digytal.control.webservice.modulo.financeiro.consulta.PagamentoResumoDiarioResource;
import com.digytal.control.webservice.modulo.financeiro.consulta.PagamentoConsultaResource;
import com.digytal.control.webservice.modulo.contrato.ContratoResource;
import com.digytal.control.webservice.modulo.acesso.AplicacaoResource;
import com.digytal.control.webservice.modulo.acesso.EmpresaResource;
import com.digytal.control.webservice.modulo.cadastro.*;
import com.digytal.control.webservice.modulo.financeiro.TransacaoResource;
import com.digytal.control.webservice.modulo.param.BancoResource;
import com.digytal.control.webservice.publico.PublicoResource;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        /* Dados para contato */

        Contact contato = new Contact();
        contato.name("Glayson Sampaio");
        contato.email("glaysonsampaio@gmail.com");
        return new OpenAPI()
                        .info(new Info().title("Digytal-code")
                        .description("Gerenciamento de receitas e despesas")
                        .contact(contato)
                        .version("v3")
                        .license(new License().name("Apache 2.4").url("http://springdoc.org")))
                        .externalDocs(new ExternalDocumentation()
                        .description("Link da página oficial")
                        .url("https://www.linkedin.com/company/digytalfactory/about/"));
    }
    /*
    *  Grupos de endpoits do pacote webservices
    */
    @Bean
    public GroupedOpenApi apiPagamento() {
        return GroupedOpenApi.builder().group("Pagamentos").packagesToScan(TransacaoResource.class.getPackage().getName()).build();
    }
    @Bean
    public GroupedOpenApi apiParagamentoResumoDiario() {
        String paths[] = {"/resumo-diario/**"};
        String packagesToscan[] = {PagamentoResumoDiarioResource.class.getPackage().getName()};
        return GroupedOpenApi.builder().group("Resumo diário").pathsToMatch(paths).packagesToScan(packagesToscan).build();
    }
    @Bean
    public GroupedOpenApi grupoAcessos() {
        return GroupedOpenApi.builder().group("Acessos").packagesToScan(EmpresaResource.class.getPackage().getName()).build();
    }
    @Bean
    public GroupedOpenApi grupoCadastros() {
        return GroupedOpenApi.builder().group("Cadastros").packagesToScan(CadastroResource.class.getPackage().getName()).build();
    }
    @Bean
    public GroupedOpenApi apiPagamentoConsulta() {
        String paths[] = {"/consultas/pagamentos/**","/resumo-diario/**"};
        String packagesToscan[] = {PagamentoConsultaResource.class.getPackage().getName(), PagamentoResumoDiarioResource.class.getPackage().getName()};

        return GroupedOpenApi.builder().group("Consultas e pagamentos").pathsToMatch(paths).packagesToScan(packagesToscan)
                .build();
    }

    @Bean
    public GroupedOpenApi apiContrato() {
        String paths[] = {"/contratos/**"};
        String packagesToscan[] = {ContratoResource.class.getPackage().getName()
        };
        return GroupedOpenApi.builder().group("Contratos").pathsToMatch(paths).packagesToScan(packagesToscan)
                .build();
    }
    @Bean
    public GroupedOpenApi grupoParams() {
        String paths[] = {"/bancos/**","/ceps/**"};
        String packagesToscan[] = {BancoResource.class.getPackage().getName()};
        return GroupedOpenApi.builder().group("Params").pathsToMatch(paths).packagesToScan(packagesToscan)
                .build();
    }
    @Bean
    public GroupedOpenApi grupoPublicos() {
        String paths[] = {"/public/**"};
        String packagesToscan[] = {PublicoResource.class.getPackage().getName()};
        return GroupedOpenApi.builder().group("Publico").pathsToMatch(paths).packagesToScan(packagesToscan)
                .build();
    }
    private String[] classes(Class classe){
        List<String> resources = null;
        try {
            resources = Classes.classesName(CadastroResource.class.getPackage().getName());
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return resources.toArray(new String[0]);
    }
}

