import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    IPrinter o;

    @BeforeEach
    void setUp() {
        o = new Printer(PrinterType.LASER, 1);
    }

    @Test
    void calculateJobTimeInNanos() {
        long expectedNanos = PrinterType.LASER.pageProcessingTime() * PrintJobQuality.FINE.getTimeMultiplier() * 10 * 1_000_000;
        PrintJob job = new PrintJob(1, PrintJobType.COLOR, PrintJobQuality.FINE, 10);
        assertEquals(expectedNanos, IPrinter.calculateJobTimeInNanos(o, job));
    }

    @Test
    void estimatedTimeWithNewJob() {
        PrintJob job = new PrintJob(1, PrintJobType.BLACK_AND_WHITE, PrintJobQuality.NORMAL, 50);
        long expectedNanos = PrinterType.LASER.pageProcessingTime() * PrintJobQuality.NORMAL.getTimeMultiplier() * 50 * 1_000_000;
        LocalDateTime expectedTime = LocalDateTime.now().plusNanos(expectedNanos);
        assertTrue(ChronoUnit.NANOS.between(expectedTime, IPrinter.estimatedTimeWithNewJob(o, job)) < 5_000_000);
    }

    @Test
    void getSpeed() {
        assertEquals(PrinterType.LASER.pageProcessingTime(), o.getSpeed());
        o = new Printer(PrinterType.INK_JET, 2);
        assertEquals(PrinterType.INK_JET.pageProcessingTime(), o.getSpeed());
    }

}