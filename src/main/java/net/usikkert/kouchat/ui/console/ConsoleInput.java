
/***************************************************************************
 *   Copyright 2006-2018 by Christian Ihle                                 *
 *   contact@kouchat.net                                                   *
 *                                                                         *
 *   This file is part of KouChat.                                         *
 *                                                                         *
 *   KouChat is free software; you can redistribute it and/or modify       *
 *   it under the terms of the GNU Lesser General Public License as        *
 *   published by the Free Software Foundation, either version 3 of        *
 *   the License, or (at your option) any later version.                   *
 *                                                                         *
 *   KouChat is distributed in the hope that it will be useful,            *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU      *
 *   Lesser General Public License for more details.                       *
 *                                                                         *
 *   You should have received a copy of the GNU Lesser General Public      *
 *   License along with KouChat.                                           *
 *   If not, see <http://www.gnu.org/licenses/>.                           *
 ***************************************************************************/

package net.usikkert.kouchat.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.usikkert.kouchat.message.CoreMessages;
import net.usikkert.kouchat.misc.CommandException;
import net.usikkert.kouchat.misc.CommandParser;
import net.usikkert.kouchat.misc.Controller;
import net.usikkert.kouchat.misc.MessageController;
import net.usikkert.kouchat.settings.Settings;
import net.usikkert.kouchat.ui.UserInterface;
import net.usikkert.kouchat.util.Validate;

/**
 * Contains the main input loop for the console mode.
 *
 * @author Christian Ihle
 */
public class ConsoleInput extends Thread {

    private static final Logger LOG = Logger.getLogger(ConsoleInput.class.getName());

    private final BufferedReader stdin;
    private final Controller controller;
    private final CommandParser cmdParser;
    private final MessageController msgController;
    private final Thread shutdownHook;

    /**
     * Constructor. Initializes input from System.in.
     *
     * @param controller The controller to use.
     * @param ui The user interface to send messages to.
     * @param settings The settings to use.
     * @param consoleMessages The messages to use for the console ui.
     * @param coreMessages The messages to use for the core components.
     */
    public ConsoleInput(final Controller controller, final UserInterface ui, final Settings settings,
                        final ConsoleMessages consoleMessages, final CoreMessages coreMessages) {
        Validate.notNull(controller, "Controller can not be null");
        Validate.notNull(ui, "UserInterface can not be null");
        Validate.notNull(settings, "Settings can not be null");
        Validate.notNull(consoleMessages, "Console messages can not be null");
        Validate.notNull(coreMessages, "Core messages can not be null");

        this.controller = controller;

        setName("ConsoleInputThread");
        msgController = ui.getMessageController();
        stdin = new BufferedReader(new InputStreamReader(System.in));
        cmdParser = new CommandParser(controller, ui, settings, coreMessages);

        shutdownHook = new Thread("ConsoleInputShutdownHook") {
            @Override
            public void run() {
                System.out.println(consoleMessages.getMessage("console.quit.message"));
            }
        };
    }

    /**
     * Starts a loop waiting for input.
     * To stop the loop and exit the application, write /quit.
     */
    @Override
    public void run() {
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        String input = "";

        while (input != null) {
            try {
                input = stdin.readLine();

                if (input != null && input.trim().length() > 0) {
                    if (input.startsWith("/")) {
                        cmdParser.parse(input);
                    }

                    else {
                        try {
                            controller.sendChatMessage(input);
                            msgController.showOwnMessage(input);
                        }

                        catch (final CommandException e) {
                            msgController.showSystemMessage(e.getMessage());
                        }
                    }
                }
            }

            catch (final IOException e) {
                LOG.log(Level.SEVERE, e.toString(), e);
                input = null;
            }
        }

        System.exit(1);
    }
}
