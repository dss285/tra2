package tra2.vk6;

import java.text.DecimalFormat;
import java.util.*;


public class Ajastin2 {

	long start, end = 0;
    String name = "";

    static DecimalFormat df = new DecimalFormat("0.000");
    public Ajastin2() {
        start = System.nanoTime();
    }

    public Ajastin2(String n) {
        this();
        name = n;
    }

    public long stop() {
        end = System.nanoTime();
        return (end - start);
    }

    public void start() {
        start = System.nanoTime();
    }

    public void restart() {
        start = System.nanoTime() - (end - start);
    }

    public long time() {
        long t = end - start;
        if (end == 0)
            t += System.nanoTime();
        return t;
    }

    public long nanos() {
        long t = end - start;
        if (end == 0)
            t += System.nanoTime();
        return t;
    }

    public double micros() {
        long t = end - start;
        if (end == 0)
            t += System.nanoTime();
        return t/1000.0;
    }

    public double millis() {
        long t = end - start;
        if (end == 0)
            t += System.nanoTime();
        return t/(1000.0*1000.0);
    }

    public double sek() {
        long t = end - start;
        if (end == 0)
            t += System.nanoTime();
        return t/(1000.0*1000.0*1000.0);
    }

    public String toString() {
        if (end == 0)
            end = System.nanoTime();
        if (sek() > 1)
            return name + " " + df.format(sek()) + "s";
        else if (millis() > 1)
            return name + " " + df.format(millis()) + "ms";
        else if (micros() > 1)
            return name + " " + df.format(micros()) + "us";
        else
            return name + " " + nanos() + "ns";

    }

}
			

