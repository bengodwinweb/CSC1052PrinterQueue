/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
Main.java

Info: Main entry point to the CSC1052PrinterQueue project. Creates the ScenarioRunner using the parameters defined
as constants in this class and starts the simulation
 */


public class Main {

    public static final int S_JOBS = 35;
    public static final int S_MIN_INTERVAL = 5;
    public static final int S_MAX_INTERVAL = 100;
    public static final int S_MAX_PAGES = 50;
    public static final int S_DOT_MATRIX_PRINTERS = 1;
    public static final int S_LASER_PRINTERS = 2;
    public static final int S_INK_JET_PRINTERS = 3;

    /*
        PROJECT SUMMARY:

        I implemented this as a controller (Print Room) that holds a collection of Printers.
        Each printer holds a queue of a jobs, and the controller adds jobs to printers.
        The printer completes the jobs in the order they are added, using the queue.

        The Print Room selects the best printer to add the job to by using a comparator to determine which printer
        will complete the job fastest. Each printer spawns a thread to handle its printing, printers run concurrently.

        The ScenarioRunner class creates the PrintRoom and PrintJobs (also creates the Printers, always useful for testing when you can stub the
        underlying collection) and runs the "simulation"

        Random info:
        Three types of printers: Dot Matrix, Laser, and Ink Jet.
        Five types of print jobs: Carbon Copy, Black and White, Color, Image, and Complex (in my head complex is like posters or brochures with color)

        Dot Matrix is the fastest printer type, only handles carbon copy and black and white.
        Laser is slightly slower, handles black and white and color.
        Ink Jet is slowest, handles color, image, and complex jobs.
     */

    /**
     * Main entry point to the application. Adjust constants to change simulation parameters
     */
    public static void main(String[] args) {
        ScenarioParameters params = new ScenarioParameters();
        params.setTimeParameters(S_JOBS, S_MIN_INTERVAL, S_MAX_INTERVAL);
        params.setPrinterParameters(S_DOT_MATRIX_PRINTERS, S_LASER_PRINTERS, S_INK_JET_PRINTERS, S_MAX_PAGES);

        ScenarioRunner runner = new ScenarioRunner(params);
        runner.make();
        runner.start();
    }

}
