package org.block.analytics.core.utility;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "app.display")
@Getter
@Setter
public class ApllicationPropertiesValueBinder {

    List<String> uploadColumnNames;
}
