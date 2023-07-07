package com.infinityatom.infobars;

import com.infinityatom.infobars.config.ModConfigs;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;

public class InfoBarsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("infobars")
                .then(CommandManager.argument("action", StringArgumentType.word())
                        .then(CommandManager.argument("name", StringArgumentType.word())
                                .executes(InfoBarsCommand::execute))));
    }

    private static int execute(CommandContext<ServerCommandSource> context) {
        String actionArg = context.getArgument("action", String.class);
        String nameArg = context.getArgument("name", String.class);

        if(actionArg.equalsIgnoreCase("show")) {
            if(nameArg.equalsIgnoreCase("info")) {
                if(ModConfigs.INFO_ENABLED.equalsIgnoreCase("true")) {
                    BarManager.showBar("info_bar", InfoBars.MCserver);
                    context.getSource().sendFeedback(Text.literal("InfoBar \"info\" is visible."), false);
                } else {
                    context.getSource().sendError(Text.literal("An error occurred. The specified InfoBar is not enabled."));
                    return 0;
                }

            } else if(nameArg.equalsIgnoreCase("warn")) {
                if(ModConfigs.WARN_ENABLED.equalsIgnoreCase("true")) {
                    BarManager.showBar("warn_bar", InfoBars.MCserver);
                    context.getSource().sendFeedback(Text.literal("InfoBar \"warn\" is visible."), false);
                } else {
                    context.getSource().sendError(Text.literal("An error occurred. The specified InfoBar is not enabled."));
                    return 0;
                }
            } else {
                context.getSource().sendError(Text.literal("An error occurred. The \"name\" argument was not valid. It must be \"info\" or \"warn\"."));
                return 0;
            }
        } else if(actionArg.equalsIgnoreCase("hide")) {
            if(nameArg.equalsIgnoreCase("info")) {
                BarManager.hideBar("info_bar", InfoBars.MCserver);
                context.getSource().sendFeedback(Text.literal("InfoBar \"info\" is hidden."), false);
            } else if(nameArg.equalsIgnoreCase("warn")) {
                BarManager.hideBar("warn_bar", InfoBars.MCserver);
                context.getSource().sendFeedback(Text.literal("InfoBar \"warn\" is hidden."), false);
            } else {
                context.getSource().sendError(Text.literal("An error occurred. The \"name\" argument was not valid. It must be \"info\" or \"warn\"."));
                return 0;
            }
        } else {
            context.getSource().sendError(Text.literal("An error occurred. The \"action\" argument was not valid. It must be \"show\" or \"hide\"."));
            return 0;
        }

        return 1;
    }
}
