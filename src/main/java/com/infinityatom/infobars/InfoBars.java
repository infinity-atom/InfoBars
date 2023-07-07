package com.infinityatom.infobars;

import com.infinityatom.infobars.config.ModConfigs;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfoBars implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("infobars");
	public static MinecraftServer MCserver;

	@Override
	public void onInitialize() {



		ModConfigs.registerConfigs();

		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
			InfoBarsCommand.register(dispatcher);
		}));
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			MCserver = server;
			BarManager.initializeBars(MCserver);
		});




	}
}