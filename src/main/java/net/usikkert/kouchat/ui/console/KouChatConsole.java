
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

import net.usikkert.kouchat.message.CoreMessages;
import net.usikkert.kouchat.misc.ErrorHandler;
import net.usikkert.kouchat.settings.Settings;
import net.usikkert.kouchat.util.Validate;

/**
 * Loads KouChat in console mode.
 *
 * @author Christian Ihle
 */
public class KouChatConsole {

    private final ConsoleMediator consoleMediator;

    /**
     * Constructor.
     *
     * @param settings The settings to use for this application.
     * @param errorHandler The error handler to use for this application.
     */
    public KouChatConsole(final Settings settings, final ErrorHandler errorHandler) {
        Validate.notNull(settings, "Settings can not be null");
        Validate.notNull(errorHandler, "Error handler can not be null");

        settings.setClient("Console");

        final ConsoleMessages consoleMessages = new ConsoleMessages();
        final CoreMessages coreMessages = new CoreMessages();

        consoleMediator = new ConsoleMediator(settings, consoleMessages, coreMessages, errorHandler);
    }

    /**
     * Initializes the User Interface and the necessary services.
     */
    public void start() {
        consoleMediator.start();
    }
}
