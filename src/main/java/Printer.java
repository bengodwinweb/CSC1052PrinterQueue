/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
Printer.java

Info: represents a Printer. Maintains a queue of jobs, creating a thread to print jobs as they are dequeued. Can only
run one thread at a time to print jobs, but may run concurrently with other printers.
 */

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer implements IPrinter {

    private final int m_id;
    private final PrinterType m_type;
    private final IQueue<PrintJob> m_jobQueue;

    private LocalDateTime m_finishTime;

    private boolean m_jobInProgress; // needs to be threadsafe
    private Lock statusLock = new ReentrantLock(); // lock for jobInProgress

    protected Printer(PrinterType type, int id) {
        m_type = type;
        m_id = id;
        m_jobQueue = new LinkedQueue<>();
    }

    @Override
    public int getId() {
        return m_id;
    }

    @Override
    public int getSpeed() {
        // minimum possible processing time for one page for the given printer type
        return m_type.pageProcessingTime();
    }

    @Override
    public LocalDateTime estimatedFinishTime() {
        // Check that an estimated time has been created and that it is later than now.
        // If either of those conditions are false, return now. Otherwise return the estimate.
        if (m_finishTime == null || m_finishTime.isBefore(LocalDateTime.now())) {
            return LocalDateTime.now();
        } else {
            return m_finishTime;
        }
    }

    @Override
    public boolean typeIsSupported(PrintJob job) {
        // Uses the PrinterType to check if it can print the job type
        return m_type.jobIsSupported(job);
    }

    @Override
    public void addJob(PrintJob job) throws IllegalArgumentException {
        // Make sure we can print this type of job
        if (!typeIsSupported(job)) {
            throw new IllegalArgumentException("Printer of type " + m_type.toString() + " does not support requested job " + job.toString());
        }

        // Add the job to the queue, add the estimated processing time to the estimated finish time
        m_jobQueue.enqueue(job);
        m_finishTime = IPrinter.estimatedTimeWithNewJob(this, job);

        // If we are not currently printing anything, start printing
        if (!isPrinting()) {
            print();
        }
    }

    @Override
    public void completePrintProcess(PrintJob job) {
        // PrintProcess thread is alerting us that it has finished the given job.
        // Log the completed job
        System.out.printf("Completed:\n\tJob: %d\tPrinter: %d\n", job.getId(), m_id);

        // If there are more jobs in the queue, grab the next item. Otherwise, log that the queue is currently empty
        // and set the status to not printing
        if (!m_jobQueue.isEmpty()) {
            print();
        }
        else {
            System.out.printf("Printer %d queue empty\n", m_id);
            setPrinting(false);
        }
    }

    @Override
    public String toString() {
        return String.format("Printer: %d, Type: %s", m_id, m_type.description());
    }

    /**
     * Grabs the first job from the queue and processes it
     */
    private void print() {
        // Get the first print job in the queue, create a PrintProcess thread and start it.
        // Set the status to printing

        PrintJob job;
        try {
            job = m_jobQueue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return;
        }

        setPrinting(true);
        PrintProcess process = new PrintProcess(this, job);
        process.start();
    }


    /**
     * True if there is currently a PrintProcess thread processing a job for this printer, tracked with a boolean
     */
    private boolean isPrinting() {
        statusLock.lock();
        try {
            return m_jobInProgress;
        } finally {
            statusLock.unlock();
        }
    }

    /**
     * Updates the currently printing status
     */
    private void setPrinting(boolean printing) {
        statusLock.lock();
        try {
            m_jobInProgress = printing;
        } finally {
            statusLock.unlock();
        }
    }
}
