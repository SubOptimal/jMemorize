/*
 * jMemorize - Learning made easy (and fun) - A Leitner flashcards tool
 * Copyright(C) 2004-2008 Riad Djemili and contributors
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 1, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package jmemorize.gui;

import java.awt.event.ActionEvent;
import java.io.File;

import jmemorize.core.Main;
import jmemorize.gui.swing.actions.AboutAction;
import jmemorize.gui.swing.actions.file.ExitAction;
import jmemorize.gui.swing.actions.file.PreferencesAction;

import com.apple.eawt.Application;
import com.apple.eawt.ApplicationEvent;
import com.apple.eawt.ApplicationListener;

/**
 * Contributed by Jakob Vogel 
 */
public class AppleAdapter
implements ApplicationListener
{
    private static final String aboutActionCmd = "about";
    private AboutAction aboutAction = new AboutAction();

    private static final String prefActionCmd = "pref";
    private PreferencesAction prefAction = new PreferencesAction();

    private static final String exitActionCmd = "exit";
    private ExitAction exitAction = new ExitAction();

    public AppleAdapter()
    {
        Application application = Application.getApplication();

        // show and enable the about item in the default application menu
        application.addAboutMenuItem();
        application.setEnabledAboutMenu(true);

        // hide and disable the preferences item in the same menu
        application.removePreferencesMenuItem();
        application.setEnabledPreferencesMenu(false);

        // add this class as listener
        application.addApplicationListener(this);
    }

    /**
     * <p>
     * Called when the user selects the About item in the application menu.
     * </p>
     * 
     * @see com.apple.eawt.ApplicationListener#handleAbout(com.apple.eawt.ApplicationEvent)
     */
    public void handleAbout(ApplicationEvent ev)
    {
        ev.setHandled(true);
        aboutAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            aboutActionCmd));
    }

    /**
     * <p>
     * Called when the application receives an Open Application event from the
     * Finder or another application.
     * </p>
     * 
     * @see com.apple.eawt.ApplicationListener#handleOpenApplication(com.apple.eawt.ApplicationEvent)
     */
    public void handleOpenApplication(ApplicationEvent ev)
    {}

    /**
     * <p>
     * Called when the application receives an Open Document event from the
     * Finder or another application.
     * </p>
     * 
     * @see com.apple.eawt.ApplicationListener#handleOpenFile(com.apple.eawt.ApplicationEvent)
     */
    public void handleOpenFile(ApplicationEvent ev)
    {
        // wait until the frame gets available
        while (Main.getInstance().getFrame() == null)
        {
            try
            {
                Thread.sleep(500);
            }

            catch (InterruptedException e)
            {}
        }

        ev.setHandled(true);
        Main.getInstance().getFrame().loadLesson(new File(ev.getFilename()));
    }

    /**
     * <p>
     * Called when the Preference item in the application menu is selected.
     * </p>
     * 
     * @see com.apple.eawt.ApplicationListener#handlePreferences(com.apple.eawt.ApplicationEvent)
     */
    public void handlePreferences(ApplicationEvent ev)
    {
        // actually not used as the preferences item was disabled
        ev.setHandled(true);
        prefAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            prefActionCmd));
    }

    /**
     * <p>
     * Called when the application is sent a request to print a particular file
     * or files.
     * </p>
     * 
     * @see com.apple.eawt.ApplicationListener#handlePrintFile(com.apple.eawt.ApplicationEvent)
     */
    public void handlePrintFile(ApplicationEvent ev)
    {}

    /**
     * <p>
     * Called when the application is sent the Quit event.
     * </p>
     * 
     * @see com.apple.eawt.ApplicationListener#handleQuit(com.apple.eawt.ApplicationEvent)
     */
    public void handleQuit(ApplicationEvent ev)
    {
        ev.setHandled(false); // instruct os x to let the application quit on its own
        exitAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
            exitActionCmd));
    }

    /**
     * <p>
     * Called when the application receives a Reopen Application event from the
     * Finder or another application.
     * </p>
     * 
     * @see com.apple.eawt.ApplicationListener#handleReOpenApplication(com.apple.eawt.ApplicationEvent)
     */
    public void handleReOpenApplication(ApplicationEvent ev)
    {}
}
