package com.hyperion360.command;

/**
 * Error command if user input invalid one
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public class ErrorCommand implements CommandStrategy<String> {
    @Override
    public String runCommand(String input) {
        return "unknown command";
    }
}
