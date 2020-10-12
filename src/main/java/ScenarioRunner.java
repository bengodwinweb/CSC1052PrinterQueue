/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
ScenarioRunner.java

Info: controlling class to run a simulated set of inputs through a PrintRoom object. Simulation parameters are
static variables in Main that are passed to this class in parameter objects. This class extends thread, must be
called with parameters, then make, then start in that order.
 */

import java.util.ArrayList;
import java.util.Random;

public class ScenarioRunner extends Thread {

    ScenarioParameters params;
    PrintRoom printRoom;

    public ScenarioRunner(ScenarioParameters params) {
        this.params = params;
    }

    /**
     * Makes the print room with the given printer parameters
     */
    public void make() {
        var printers = new ArrayList<IPrinter>();
        int printerCount = 0;

        for (int i = 0; i < params.getDotMatrixPrinters(); i++) {
            printers.add(new Printer(PrinterType.DOT_MATRIX, ++printerCount));
        }

        for (int i = 0; i < params.getLaserPrinters(); i++) {
            printers.add(new Printer(PrinterType.LASER, ++printerCount));
        }

        for (int i = 0; i < params.getInkJetPrinters(); i++) {
            printers.add(new Printer(PrinterType.INK_JET, ++printerCount));
        }

        for (var printer : printers) {
            System.out.printf("Printer created:\n\t%s\n", printer.toString());
        }

        printRoom = new PrintRoom(printers);
    }


    /**
     * Runs a simulation, passing in jobs to the print room using the given parameters
     */
    @Override
    public void run() {
        Random rand = new Random();
        int jobsMade = 0;
        while (jobsMade < params.getJobsToGenerate()) {
            try {
                PrintJob job = MakeJob(++jobsMade, rand); // create the randomized print job
                System.out.printf("Created:\n\t%s\n", job.toString()); // log
                printRoom.addJob(job); // add the job to the print room

                // Wait a random amount of milliseconds before looping again, use the parameters inclusive min and
                // exclusive max to generate the time to wait.
                Thread.sleep(rand.nextInt(params.getMaxInterval() - params.getMinInterval()) + params.getMinInterval());
            } catch (InterruptedException e) {
                System.out.println("Scenario interrupted");
            }
        }
    }

    /**
     * Makes a completely random print job. Page number range determined by parameters
     * @param rand Random object used in creating the randomized PrintJob
     */
    private PrintJob MakeJob(int id, Random rand) {
        PrintJobType type = EnumExtender.randomEnum(PrintJobType.class); // get random PrintJobType enum
        PrintJobQuality quality = EnumExtender.randomEnum(PrintJobQuality.class); // get random PrintJobQuality enum
        int pages = rand.nextInt(params.getMaxPages()) + 1; // random number of pages between 1 and the inclusive maximum
        return new PrintJob(id, type, quality, pages);
    }

}
