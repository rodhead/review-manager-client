/*
package org.block.analytics.core.configuration;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages
        = "org.block.analytics.*")
@ComponentScan(basePackages = { "org.block.analytics.*" })
public class ElasticsearchClientConfig extends
        AbstractElasticsearchConfiguration {
  @Override
  @Bean
  public RestHighLevelClient elasticsearchClient() {

  final ClientConfiguration clientConfiguration =
    ClientConfiguration
      .builder()
      .connectedTo("localhost:9200")
      .build();

  return RestClients.create(clientConfiguration).rest();
  }
}*/
