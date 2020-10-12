/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
IPrinter.java

Info: public interface for a Printer with static methods.
 */

import java.time.LocalDateTime;

public interface IPrinter {

    long NANOS_PER_MILLI = 1_000_000;

    /**
     * Calculates how long a job would take to print under ideal circumstances in nanoseconds
     * @param printer
     * @param job
     * @return calculated print time in nanoseconds
     */
    static long calculateJobTimeInNanos(IPrinter printer, PrintJob job) {
        // speedPerPage = printerSpeed * qualityTimeMultiplier
        // milliseconds = speedPerPage * pages
        // return milliseconds * NANOS_PER_MILLI
        return (printer.getSpeed() * job.getProcessingTimeMultiplier() * job.getPages()) * NANOS_PER_MILLI;
    }

    /**
     * Calculated what the printer's estimated completion time would be for the job, if it was added to the printer's queue
     * @param printer
     * @param job
     * @return LocalDateTime estimated time of completion
     */
    static LocalDateTime estimatedTimeWithNewJob(IPrinter printer, PrintJob job) {
        /*
         *  Get the printer's current estimated finish time
         *  Calculate the estimated nanoseconds this job would take on the printer
         *  Add the nanoseconds onto the estimated time and return
         */
        return printer.estimatedFinishTime().plusNanos(calculateJobTimeInNanos(printer, job));
    }

    /**
     * Get the printer's ID int
     * @return
     */
    int getId();

    /**
     * The printer's speed is the minimum number of milliseconds this printer takes to print one page at the lowest quality setting
     * @return
     */
    int getSpeed();

    /**
     * Adds a job to the printer's queue
     * @throws IllegalArgumentException if the job type is not supported by the printer
     */
    void addJob(PrintJob job) throws IllegalArgumentException;

    /**
     * Checks if the printer is able to process the given job based on its type
     */
    boolean typeIsSupported(PrintJob job);

    /**
     * Used by the PrintProcess thread to notify the printer that the currently printing job has been completed
     */
    void completePrintProcess(PrintJob job);

    /**
     * Returns the LocalDateTime representing the estimated time the last job in the queue should finish printing
     * under ideal (multithreading) conditions. If no jobs are queued or time has passed, returns LocalDateTime.now()
     */
    LocalDateTime estimatedFinishTime();
}
