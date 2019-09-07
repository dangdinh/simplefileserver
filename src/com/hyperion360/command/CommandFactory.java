package com.hyperion360.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manage command enum and map to process their values
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public class CommandFactory {
    private static Map<CommandType, CommandStrategy> commandStrategyMap;
    static {
        commandStrategyMap = new ConcurrentHashMap<>();
        commandStrategyMap.put(CommandType.INDEX, new IndexCommand());
        commandStrategyMap.put(CommandType.GET, new GetFileCommand());
        commandStrategyMap.put(CommandType.ERROR, new ErrorCommand());
    }

    public static CommandStrategy getCommand(CommandType command) {
        return commandStrategyMap.get(command);
    }

    public enum CommandType {
        INDEX("index"), GET("get"), ERROR("error");
        private String command;
        CommandType(String command) {
            this.command = command;
        }

        public static CommandType valueOfCommand(String enumCommandStr) {
            try {
                if (enumCommandStr.startsWith("get"))
                    enumCommandStr = "GET";
                return CommandType.valueOf(enumCommandStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ERROR;
            }
        }
    }

}
