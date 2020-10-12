/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
PrintJobType.java

Info: enumeration of types of Print Jobs. Each printer will have one or more types of jobs that
it is able to process.
 */

public enum PrintJobType {
    CARBON_COPY     ("Carbon Copy"),
    BLACK_AND_WHITE ("Black and white"),
    COLOR           ("Color"),
    IMAGE           ("Image"),
    COMPLEX_JOB     ("Complex Job");

    private final String m_description;

    PrintJobType(String description) {
        m_description = description;
    }

    /**
     * Human readable string representing the print job type.
     */
    public String description() {
        return m_description;
    }
}
