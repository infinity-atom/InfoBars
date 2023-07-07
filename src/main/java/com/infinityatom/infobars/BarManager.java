package com.infinityatom.infobars;

import com.infinityatom.infobars.config.ModConfigs;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.BossBarManager;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Collection;

public class BarManager {

    private static CommandBossBar info_bar;
    private static CommandBossBar warn_bar;
    private static BossBarManager bossBarManager;


    public static void initializeBars(MinecraftServer server) {

        Identifier INFO_BAR_ID = new Identifier("infobars", "info_bar");
        Identifier WARN_BAR_ID = new Identifier("infobars", "warn_bar");



        bossBarManager = server.getBossBarManager();

        info_bar = bossBarManager.add(INFO_BAR_ID, Text.literal(ModConfigs.INFO_CAPTION));
        warn_bar = bossBarManager.add(WARN_BAR_ID, Text.literal(ModConfigs.WARN_CAPTION));

        info_bar.setColor(getColorFromString(ModConfigs.INFO_COLOR));
        info_bar.setStyle(BossBar.Style.PROGRESS);
        info_bar.setPercent(1F);
        info_bar.setVisible(true);

        warn_bar.setColor(getColorFromString(ModConfigs.WARN_COLOR));
        warn_bar.setStyle(BossBar.Style.PROGRESS);
        warn_bar.setPercent(1F);
        warn_bar.setVisible(true);


    }

    public static boolean showBar(String path, MinecraftServer server) {
        if(path.equalsIgnoreCase("info_bar")) {
            Collection<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();

            info_bar.addPlayers(players);
            return true;

        } else if (path.equalsIgnoreCase("warn_bar")) {
            Collection<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();

            warn_bar.addPlayers(players);
            return true;

        } else {
            return false;
        }
    }

    public static boolean hideBar(String path, MinecraftServer server) {
        if(path.equalsIgnoreCase("info_bar")) {
            Collection<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();

            for(ServerPlayerEntity player : players) {
                info_bar.removePlayer(player);
            }

            return true;

        } else if (path.equalsIgnoreCase("warn_bar")) {
            Collection<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();

            for(ServerPlayerEntity player : players) {
                warn_bar.removePlayer(player);
            }

            return true;

        } else {
            return false;
        }
    }

    public static BossBar.Color getColorFromString(String color) {

        if(color.equalsIgnoreCase("BLUE")) {
            return BossBar.Color.BLUE;
        } else if(color.equalsIgnoreCase("GREEN")) {
            return BossBar.Color.GREEN;
        } else if(color.equalsIgnoreCase("PINK")) {
            return BossBar.Color.PINK;
        } else if(color.equalsIgnoreCase("PURPLE")) {
            return BossBar.Color.PURPLE;
        } else if(color.equalsIgnoreCase("RED")) {
            return BossBar.Color.RED;
        } else if(color.equalsIgnoreCase("WHITE")) {
            return BossBar.Color.WHITE;
        } else if(color.equalsIgnoreCase("YELLOW")) {
            return BossBar.Color.YELLOW;
        } else {
            return BossBar.Color.WHITE;
        }

    }

}
