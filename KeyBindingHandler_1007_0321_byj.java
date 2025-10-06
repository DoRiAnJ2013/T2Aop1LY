// 代码生成时间: 2025-10-07 03:21:21
package com.example;

import io.micronaut.context.annotation.Requires;
import javax.inject.Singleton;
import io.micronaut.aop.Around;
import io.micronaut.context.annotation.Bean;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * The KeyBindingHandler class is responsible for handling keyboard shortcuts.
 */
@Singleton
@Requires(property = "keybinding.enabled")
public class KeyBindingHandler {

    private Robot robot;
    private Clipboard clipboard;

    /**
     * Initializes the Robot and Clipboard instances.
     */
    public KeyBindingHandler() {
        try {
            this.robot = new Robot();
            this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize Robot or Clipboard.", e);
        }
    }

    /**
     * Simulates a key press and release for the specified key.
     *
     * @param keyCode the key code of the key to press
     */
    public void simulateKeyPress(int keyCode) {
        try {
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        } catch (Exception e) {
            throw new RuntimeException("Failed to simulate key press.", e);
        }
    }

    /**
     * Copies the given text to the system clipboard.
     *
     * @param text the text to copy
     */
    public void copyToClipboard(String text) {
        try {
            StringSelection stringSelection = new StringSelection(text);
            clipboard.write(stringSelection);
        } catch (Exception e) {
            throw new RuntimeException("Failed to copy text to clipboard.", e);
        }
    }

    /**
     * AOP Advice to handle key binding actions.
     *
     * @param context the AOP context
     * @return the result of the intercepted method
     * @throws Throwable if an error occurs
     */
    @Bean
    @Around
    public Object handleKeyBinding(AdviceContext context) throws Throwable {
        // Retrieve the key code from the intercepted method arguments
        Integer keyCode = (Integer) context.getParameterValues()[0];

        // Handle key press and release
        simulateKeyPress(keyCode);

        // Return the result of the intercepted method
        return context.proceed();
    }
}
