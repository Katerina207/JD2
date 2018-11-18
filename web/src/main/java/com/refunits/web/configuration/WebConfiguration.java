package com.refunits.web.configuration;

import com.refunits.service.configuration.ServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceConfiguration.class})
public class WebConfiguration {
}

