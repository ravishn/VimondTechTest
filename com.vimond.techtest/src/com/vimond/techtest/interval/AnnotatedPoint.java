package com.vimond.techtest.interval;

/**
 * AnnotatedPoint class implementation
 * This class implements Comparable interface to compare each element in the interval
 * with start and end points
 * @author Ravish
 *
 */
public class AnnotatedPoint implements Comparable<AnnotatedPoint> {
	
    // value within the intervals
    public int value;
    
    // type of the point(End, GapEnd, GapStart, Start)
    public PointTypeEnum type;

    /**
     * the constructor to initialise the value and type
     * @param value
     * @param type
     */
    public AnnotatedPoint(int value, PointTypeEnum type) {
        this.value = value;
        this.type = type;
    } // end AnnotatedPoint()

    @Override
    public int compareTo(AnnotatedPoint other) {
        if (other.value == this.value) {
            return this.type.ordinal() < other.type.ordinal() ? -1 : 1;
        } else {
            return this.value < other.value ? -1 : 1;
        } // end if
    } // end compareTo()

    /**
     * Enum to identify/tag start and end points of intervals and 
     * start and end points of gaps within the intervals
     * 
     * the order is important here: if multiple events happen at the same point,
     * this is the order in which you want to deal with them
     * @author Ravish
     */
    public enum PointTypeEnum {
        End, GapEnd, GapStart, Start
    } // end PointTypeEnum
} // end of AnnotatedPoint class
