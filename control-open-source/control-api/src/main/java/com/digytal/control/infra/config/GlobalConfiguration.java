package com.digytal.control.infra.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//https://medium.com/@andylke/rest-controller-configure-date-time-format-in-json-response-201e97aa74b0
//https://www.baeldung.com/spring-boot-customize-jackson-objectmapper
@Configuration
//@EnableAutoConfiguration(exclude={JacksonAutoConfiguration.class})
//https://www.baeldung.com/spring-boot-formatting-json-dates
public class GlobalConfiguration   {
    //implements Jackson2ObjectMapperBuilderCustomizer
    private static final String datePattern = "dd/MM/yyyy";
    private static final String dateTimePattern = "dd/MM/yyyy HH:mm:ss";
    private static final String timePattern = "HH:mm:ss";

    @Bean
    public ObjectMapper mapper(){
        ObjectMapper instance = new ObjectMapper();
        instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        instance.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        instance.registerModule(new JavaTimeModule());
        instance.registerModule(new Jdk8Module());
        return instance;
    }
    /*
    @Bean
    @Qualifier("employeeServiceB")
    @Scope("prototype")
    public EmployeeService employeeServiceB() {
        new EmployeeService();
    }

     */

     /*
    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        builder.serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        builder.serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.failOnUnknownProperties(false);
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }



     private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }
     */
}
