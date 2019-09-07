package com.hyperion360.command;

/**
 * Strategy provides common command methods
 *
 * @author  Dang Dinh
 * @version 1.0
 * @since   2019-09-06
 */
public interface CommandStrategy<T> {
    T runCommand(String input);
}
