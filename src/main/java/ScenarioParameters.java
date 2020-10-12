/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
ScenarioParameters.java

Info: class used to control the parameters for the simulated print room scenario
 */

public class ScenarioParameters {

    private int dotMatrixPrinters;
    private int laserPrinters;
    private int inkJetPrinters;
    private int maxPages;
    private int jobsToGenerate;
    private int minInterval;
    private int maxInterval;

    /**
     * Number of each type of printer to be created, and max pages for generated print jobs
     */
    public void setPrinterParameters(int dotMatrix, int laser, int inkJet, int maxPages) {
        dotMatrixPrinters = dotMatrix;
        laserPrinters = laser;
        inkJetPrinters = inkJet;
        this.maxPages = maxPages;
    }

    /**
     * Time parameters controlling the number of jobs the scenario should generate, and the min and max number of
     * milliseconds that should occur between each job being created.
     */
    public void setTimeParameters(int jobsToGenerate, int minInterval, int maxInterval) {
        this.jobsToGenerate = jobsToGenerate;
        this.minInterval = minInterval;
        this.maxInterval = maxInterval;
    }

    public int getDotMatrixPrinters() {
        return dotMatrixPrinters;
    }

    public int getLaserPrinters() {
        return laserPrinters;
    }

    public int getInkJetPrinters() {
        return inkJetPrinters;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public int getJobsToGenerate() {
        return jobsToGenerate;
    }

    public int getMinInterval() {
        return minInterval;
    }

    public int getMaxInterval() {
        return maxInterval;
    }
}
