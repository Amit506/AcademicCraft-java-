package com.example.Core.startup;

import com.example.Core.DefaultDataManager;
import com.example.Core.VaultConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class DataSourceConfig {
    private static final Logger LOG = LogManager.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.url}")
    String databaseUrl;
    @Value("${spring.datasource.username}")
    String username;

    @Autowired
    LoadProperties loadProperties;

    @Bean
    public DataSource dataSource() {
        LOG.info("username {} : url {}",username,databaseUrl);
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(databaseUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(DefaultDataManager.defaultPropertyList.get(VaultConstants.DB_PASSWORD).getValue());
        return dataSourceBuilder.build();
    }


}
