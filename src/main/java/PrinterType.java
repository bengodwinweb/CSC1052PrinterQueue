/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
PrinterType.java

Info: enumerated values for types of Printers. Holds logic for what types of jobs are supported by a PrinterType
 */

import java.util.Arrays;
import java.util.List;

public enum PrinterType {
    DOT_MATRIX(5, Arrays.asList(PrintJobType.CARBON_COPY, PrintJobType.BLACK_AND_WHITE), "Dot Matrix"), // very fast printer that only handles carbon copy or black and white jobs
    LASER(10, Arrays.asList(PrintJobType.BLACK_AND_WHITE, PrintJobType.COLOR), "Laser"), // moderately fast printer that handles black and white or color jobs
    INK_JET(20, Arrays.asList(PrintJobType.COLOR, PrintJobType.IMAGE, PrintJobType.COMPLEX_JOB), "Ink Jet"); // slower printer that handles color, image, and complex job types

    private final int m_processingTime;
    private final List<PrintJobType> m_supportedTypes;
    private final String m_description;

    PrinterType(int processingTime, List<PrintJobType> supportedTypes, String description) {
        m_processingTime = processingTime;
        m_supportedTypes = supportedTypes;
        m_description = description;
    }

    /**
     * The time in Milliseconds that it takes the given printer type to print one page at the lowest quality setting
     */
    public int pageProcessingTime() {
        return m_processingTime;
    }

    /**
     * Checks that the given printer type supports a PrintJob
     */
    public boolean jobIsSupported(PrintJob job) {
        return m_supportedTypes.contains(job.getType());
    }

    /**
     * Returns a human readable string representing the PrintJobQuality
     */
    public String description() {
        return m_description;
    }

}
