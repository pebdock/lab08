package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * Represents the standard output version of the swing version.
 */
public class DrawNumberStandardOutputView implements DrawNumberView {

    /**
     * Cnstructor used for reflection.
     */
    public DrawNumberStandardOutputView() {
        // Empty by choice
    }

    /**
     * Sets the controller controlled by this view.
     *
     * @param observer the controller to attach
     */
    @Override
    public void setController(final DrawNumberController observer) {
        // Controller isn't required for a consoleview.
    }

    /**
     * This method is called before the UI is used. It should finalize its status (if needed).
     */
    @Override
    public void start() {
        System.out.println("Console mode started: "); // NOPMD
    }

    /**
     * Tells the UI to display the result of the draw.
     *
     * @param res the result of the last draw
     */
    @Override
    public void result(final DrawResult res) {
        System.out.println("Result: " + res.getDescription()); // NOPMD
    }
}
