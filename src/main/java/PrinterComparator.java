/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
PrinterComparator.java

Info: comparator for sorting Printers in a list. Critical - this takes in a print job which is used to help sort the printers.
It is important to include the print job in the comparison because that will help us determine how long the given printers
would take to process the job.

This is a better system than just counting the number of pages or jobs queued up on the printer
because it takes into account the job quality and printer speed to give us what we're actually looking for - which
printer should I pick to get this job back the fastest.

The time used in the comparison is only an estimate because of multithreading, but is still a good indicator for our sort
 */

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class PrinterComparator implements Comparator<IPrinter> {

    private final PrintJob m_job;

    public PrinterComparator(PrintJob job) {
        m_job = job;
    }

    /**
     * Compares printers, attempting to sort a list of printers by prioritizing printers that support a given job type
     * then by which printer would complete the job fastest
     */
    @Override
    public int compare(IPrinter o1, IPrinter o2) {
        // Null checking, if both are null return 0, otherwise not null should be lower
        if (o1 == null || o2 == null) {
            if (o1 == o2) {
                return 0;
            }
            return o1 == null ? -1 : 1;
        }

        // Have to prioritize printers that support the given job type first
        if (o1.typeIsSupported(m_job) != o2.typeIsSupported(m_job)) {
            return o1.typeIsSupported(m_job) ? -1 : 1;
        }

        // Both printers support the job type.
        // Figure out which printer would get it done faster and return that one if there
        // is a significant difference (> 50 milliseconds)
        LocalDateTime printer1EstimatedTime = IPrinter.estimatedTimeWithNewJob(o1, m_job);
        LocalDateTime printer2EstimatedTime = IPrinter.estimatedTimeWithNewJob(o2, m_job);
        if (ChronoUnit.MILLIS.between(printer1EstimatedTime, printer2EstimatedTime) > 50) {
            return printer1EstimatedTime.compareTo(printer2EstimatedTime);
        }

        // Too close to call. Return the result of comparing the printers' speeds
        return Integer.compare(o1.getSpeed(), o2.getSpeed());
    }
}
