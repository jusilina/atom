package ru.atom.geometry;

/**
 * Template class for
 */
public class Point implements Collider/* super class and interfaces here if necessary */ {
    // fields
    public int x;
    public int y;
    // and methods

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param o - other object to check equality with
     * @return true if two points are equal and not null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // cast from Object to Point
        Point point = (Point) o;

        // your code here
        if ((x == point.x) && (y == point.y)) return true;
        else return false;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other instanceof Point) {
            Point point = (Point) other;
            if ((x == point.x) && (y == point.y)) return true;
        }
        return false;
    }

    public boolean isInRange(int x1, int x2) {
        if ((this.x >= x1) && (this.x <= x2)) return true;
        return false;
    }
}
