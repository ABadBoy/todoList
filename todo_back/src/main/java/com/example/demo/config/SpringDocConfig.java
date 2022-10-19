package com.example.demo.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig
{
    @Bean
    public OpenAPI myOpenAPI()
    {
        return new OpenAPI().components(new Components()
                             .addSecuritySchemes("TENANT-ID", securityScheme("TENANT-ID"))
        ).info(new Info().title("SpringDoc")
                                          .description("测试 SpringDoc")
                                          .version("v1.0.0"));
    }

    private SecurityScheme securityScheme(String name) {
        return new io.swagger.v3.oas.models.security.SecurityScheme()
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.APIKEY)
                .in(io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER)
                .name(name);
    }
}
