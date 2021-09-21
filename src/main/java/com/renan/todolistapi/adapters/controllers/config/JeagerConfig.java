package com.renan.todolistapi.adapters.controllers.config;

import org.springframework.context.annotation.Bean;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;

@org.springframework.context.annotation.Configuration
public class JeagerConfig {

    @Bean
    public Tracer getTracer() {
        Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
                .withType("const").withParam(1);
        Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
                .withLogSpans(true);
        Configuration config = new Configuration("todolist-service").withSampler(samplerConfig)
                .withReporter(reporterConfig);
        return config.getTracer();
    }
}
