/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
PrintProcess.java

Info: thread that represents a printer processing a job. When a job is added to a printer, the printer should
create and start a PrintProcess with the job. When the job is finished printing, the process hands it back to the printer.
A printer should only have one print process running at a time, but the PrintProcess for each printer in a room can and
should run concurrently.
 */

public class PrintProcess extends Thread {

    private final IPrinter m_printer;
    private final PrintJob m_printJob;

    public PrintProcess(IPrinter printer, PrintJob printJob) {
        m_printer = printer;
        m_printJob = printJob;
    }

    /**
     * Runnable that gets run when the thread is started. Prints the job and hands it back to the printer when complete.
     * Ticks through the pages, and "processes" each.
     */
    @Override
    public void run() {
        int i = 0;
        while (i++ < m_printJob.getPages()) {
            processPage();
        }
        m_printer.completePrintProcess(m_printJob);
    }

    /**
     * Just sleeps the thread for the (printerSpeed * jobQualityMultipler) number of milliseconds,
     * simulates the time needed to print a page
     */
    private void processPage() {
        int tickSpeed = m_printer.getSpeed() * m_printJob.getProcessingTimeMultiplier();
        try {
            Thread.sleep(tickSpeed);
        } catch (InterruptedException e) {
            System.out.println("PrintProcess.processPage() interrupted");
        }
    }
}
