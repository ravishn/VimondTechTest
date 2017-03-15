package com.vimond.techtest.interval;

public class Point implements Comparable<Point> {
    public int value;
    public PointTypeEnum type;

    public Point(int value, PointTypeEnum type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public int compareTo(Point other) {
        if (other.value == this.value) {
            return this.type.ordinal() < other.type.ordinal() ? -1 : 1;
        } else {
            return this.value < other.value ? -1 : 1;
        }
    }

    // the order is important here: if multiple events happen at the same point,
    // this is the order in which you want to deal with them
    public enum PointTypeEnum {
        End, GapEnd, GapStart, Start
    }
}
