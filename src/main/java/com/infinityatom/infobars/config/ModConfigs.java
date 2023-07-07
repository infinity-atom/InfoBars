package com.infinityatom.infobars.config;

import com.infinityatom.infobars.InfoBars;
import com.mojang.datafixers.util.Pair;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String INFO_CAPTION;
    public static String INFO_COLOR;
    public static String INFO_ENABLED;


    public static String WARN_CAPTION;
    public static String WARN_COLOR;
    public static String WARN_ENABLED;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of("infobars_customs").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("info.caption", "Testing Info"), "String");
        configs.addKeyValuePair(new Pair<>("info.color", "aqua"), "Color");
        configs.addKeyValuePair(new Pair<>("info.enabled", "true"), "True/False");

        configs.addKeyValuePair(new Pair<>("warn.caption", "Testing Warning"), "String");
        configs.addKeyValuePair(new Pair<>("warn.color", "red"), "Color");
        configs.addKeyValuePair(new Pair<>("warn.enabled", "false"), "True/False");
    }

    private static void assignConfigs() {
        INFO_CAPTION = CONFIG.getOrDefault("info.caption", "Testing Info");
        INFO_COLOR = CONFIG.getOrDefault("info.color", "aqua");
        INFO_ENABLED = CONFIG.getOrDefault("info.enabled", "true");

        WARN_CAPTION = CONFIG.getOrDefault("warn.caption", "Testing Warning");
        WARN_COLOR = CONFIG.getOrDefault("warn.color", "red");
        WARN_ENABLED = CONFIG.getOrDefault("warn.enabled", "false");

        InfoBars.LOGGER.info("InfoBars config values assigned correctly.");
    }
}