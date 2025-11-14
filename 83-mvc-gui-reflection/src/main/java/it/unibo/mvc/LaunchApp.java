package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private static final String SWING_VIEW_CLASS = "it.unibo.mvc.view.DrawNumberSwingView";
    private static final String CONSOLE_VIEW_CLASS = "it.unibo.mvc.view.DrawNumberStandardOutputView";
    private static final int SWING_VIEW_COUNT = 3;
    private static final int CONSOLE_VIEW_COUNT = 3;

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        final List<String> viewClassNames = new ArrayList<>();

        for (int i = 0; i < CONSOLE_VIEW_COUNT; i++) {
            viewClassNames.add(CONSOLE_VIEW_CLASS);
        }

        for (int i = 0; i < SWING_VIEW_COUNT; i++) {
            viewClassNames.add(SWING_VIEW_CLASS);
        }

        for (final String viewName : viewClassNames) {
            try {
                final Class<?> viewClass = Class.forName(viewName);
                final var zeroConstructor = viewClass.getDeclaredConstructor();
                final Object newView = zeroConstructor.newInstance();
                final DrawNumberView view = (DrawNumberView) newView;
                app.addView(view);
            } catch (final NoSuchMethodException
            | ClassNotFoundException
            | InstantiationException
            | IllegalAccessException
            | InvocationTargetException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
