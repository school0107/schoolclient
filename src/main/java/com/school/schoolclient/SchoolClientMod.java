package com.school.schoolclient;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchoolClientMod implements ModInitializer {
    public static final String MOD_ID = "schoolclient";
    public static final String MOD_NAME = "SchoolClient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("SchoolClient Mod initialized!");
    }
}
