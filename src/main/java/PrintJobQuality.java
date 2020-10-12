/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
PrintJobQuality.java

Info: enumerated values for quality of a Print Job. Multiplier is used to affect the speed of the job.
Higher quality greatly increases printing time per page
 */

public enum PrintJobQuality {
    HIGH(5,"High"),
    FINE(4, "Fine"),
    NORMAL(3, "Normal"),
    LOW(2, "Low"),
    COURSE(1, "Course");

    private final int m_multiplier;
    private final String m_description;

    PrintJobQuality(int multiplier, String description) {
        m_multiplier = multiplier;
        m_description = description;
    }

    /**
     * Multiplier applied to a printer's speed to determine the printing time per page
     */
    public int getTimeMultiplier() {
        return m_multiplier;
    }

    /**
     * Returns a human readable string representing the PrintJobQuality
     */
    public String description() {
        return String.format("%d - %s", m_multiplier, m_description);
    }
}
