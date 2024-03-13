package com.example.Core.startup;

import com.example.Core.DefaultDataManager;
import com.example.Core.DefaultProperty;
import com.example.Core.VaultConstants;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


@Service("loadProperties")
@DependsOn("startupService")
public class LoadProperties {
    private static final Logger LOG = LogManager.getLogger(LoadProperties.class);

    @Autowired
    StartupService startupService;

    @PostConstruct
    public void loadAll() {
        LOG.info("inside load properties");
       var secrets= startupService.getSecrets();
        secrets.forEach((key, value) -> {
            LOG.info("Key: " + key + ", Value: " + value);
            if(key.equalsIgnoreCase(VaultConstants.DB_PASSWORD)){
                DefaultDataManager.addDefaultProperty(new DefaultProperty(VaultConstants.DB_PASSWORD,value));
            }
        });
        return;
    }
}
