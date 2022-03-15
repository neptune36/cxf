package org.peartopeer.accounting.middleware.client.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.peartopeer.accounting.middleware.client.services.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiddlewareClientConfiguration {

    @Value("${middleware.api.url:}")
    private String apiUrl = "";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean(name = "myServiceClient")
    MyService myServiceClient(@Autowired ObjectMapper objectMapper) {
        List providers = new ArrayList();
        providers.add(new JacksonJsonProvider(objectMapper));

        List<Feature> features = new ArrayList();
        features.add(new LoggingFeature());

        MyService service = JAXRSClientFactory.create(apiUrl, MyService.class, providers,
                features, null);
        return service;
    }

}
