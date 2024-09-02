package com.taumaturgo.padroesprojetos.config;

public class AppConfig {

    private static AppConfig instance;
    private String configValue;

    private AppConfig() {
        this.configValue = "Valor padrão";
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
