package com.vimond.techtest.interval;

/**
 * Interval implementation class
 * @author Ravish
 *
 */
public class Interval {
    public int start, end;

    /**
     * constructor to assign interval start and end points
     * @param start
     * @param end
     */
    public Interval(int start, int end) {       
        this.start = start;
        this.end = end;
    } // end Interval()

    /**
     * method to convert the start and end points to String
     */
    public String toString() {
        return "(" + start + "," + end + ")";
    } // end toString()
}