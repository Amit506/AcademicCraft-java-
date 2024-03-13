package com.example.Core.startup;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.response.LogicalResponse;
import com.example.Core.DefaultDataManager;
import com.example.Core.DefaultProperty;
import com.example.Core.VaultConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("startupService")
@Primary()
public class StartupService {
    private static final Logger LOG = LogManager.getLogger(StartupService.class);

    public Map<String, String> getSecrets() {
        LOG.info("inside vault secret");
        try {
            final VaultConfig config = new VaultConfig()
                    .address("http://13.201.131.78:8200")
                    .token("hvs.mdo6T6sw9v1304Cgf7FUmtuj")
                    .build();
            var vault = new Vault(config);
            LogicalResponse logicalResponse = vault.logical().read("dev/data");
            return logicalResponse.getData();
        } catch (Exception e) {
            LOG.error("Error", e);
        }


        return null;
    }

}
