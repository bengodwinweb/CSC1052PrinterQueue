/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
PrintRoom.java

Info: class representing a Print Room that receives jobs and intelligently queues them onto printers, attempting
to do so in a wy to complete each job as quickly as possible.
 */

import java.util.List;

public class PrintRoom {

    private final List<IPrinter> m_printers;

    public PrintRoom(List<IPrinter> printers) {
        m_printers = printers;
    }

    /**
     * Adds a job for the print room to handle.
     * @param job
     */
    public void addJob(PrintJob job) {
        // sort the printers using the PrinterComparator. PrinterComparator takes the job into account, sorting
        // by printers that support the job type then by which printer should complete the job fastest
        m_printers.sort(new PrinterComparator(job));
        IPrinter printer = m_printers.get(0);

        if (printer.typeIsSupported(job)) {
            printer.addJob(job); // add the job to the printer's queue
            System.out.printf("Queued:\n\tJob: %d\tPrinter: %d\n", job.getId(), printer.getId());
        }
        else {
            System.out.println("Skipping. This print room does not have a printer capable of printing job:\n\t" + job.toString());
        }
    }

}
