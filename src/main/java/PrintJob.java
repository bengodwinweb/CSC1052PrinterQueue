/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
PrintJob.java

Info: class representing a print job to be completed by a printer. Includes information like the type
of job to be printed, number of pages, and quality.
 */

public class PrintJob {

    private final int m_id;
    private PrintJobType m_type;
    private PrintJobQuality m_quality;
    private int m_pages;

    public PrintJob(int id, PrintJobType type, PrintJobQuality quality, int pages) {
        m_id = id;
        m_type = type;
        m_quality = quality;
        m_pages = pages;
    }

    /**
     * Identifying int for the print job, these should be unique through the application's lifecycle
     */
    public int getId() {
        return m_id;
    }

    /**
     * Type of job, examples include CARBON_COPY or COLOR.
     * Determines which type(s) of printer this job can be completed on
     */
    public PrintJobType getType() {
        return m_type;
    }

    /**
     * Multiplier applied to processing time per page for this type of job.
     * Higher qualities take much longer to process per page.
     */
    public int getProcessingTimeMultiplier() {
        return m_quality.getTimeMultiplier();
    }

    /**
     * Total number of pages to be printed for this job
     */
    public int getPages() {
        return m_pages;
    }

    @Override
    public String toString() {
        return String.format("Print Job: %d, Type: %s, Quality: %s, Pages: %d", m_id, m_type.description(), m_quality.description(), m_pages);
    }
}
