package com.idus.market.config.datasource;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@Configuration
public class ReplicationDataSourceConfig {

  @Primary
  @Bean
  @DependsOn({"routingDataSource"})
  public DataSource dataSource() {
    return new LazyConnectionDataSourceProxy(routingDataSource());
  }

  @Bean
  public DataSource routingDataSource() {
    ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

    DataSource writeDataSource = getDataSource(writeDataSourceProperty());
    DataSource readDataSource = getDataSource(writeDataSourceProperty());

    Map<Object, Object> dataSourceMap = new HashMap<>();
    dataSourceMap.put("write", writeDataSource);
    dataSourceMap.put("read", readDataSource);

    routingDataSource.setTargetDataSources(dataSourceMap);
    routingDataSource.setDefaultTargetDataSource(readDataSource);

    return routingDataSource;
  }

  public DataSource getDataSource(DataSourceProperty dataSourceProperty) {
    return DataSourceBuilder.create()
        .type(HikariDataSource.class)
        .url(dataSourceProperty.getUrl())
        .username(dataSourceProperty.getUsername())
        .password(dataSourceProperty.getPassword())
        .build();
  }

  @Bean
  @ConfigurationProperties(prefix = "datasource.write")
  public DataSourceProperty writeDataSourceProperty() {
    return new DataSourceProperty();
  }

  @Bean
  @ConfigurationProperties(prefix = "datasource.read")
  public DataSourceProperty readDataSourceProperty() {
    return new DataSourceProperty();
  }

  @Bean
  public JPAQueryFactory jpaQueryFactory(EntityManager em) {
    return new JPAQueryFactory(em);
  }
}
