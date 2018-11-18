package com.refunits.service.configuration;

import com.refunits.database.configuration.DatabaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfiguration.class})
public class ServiceConfiguration {
}

