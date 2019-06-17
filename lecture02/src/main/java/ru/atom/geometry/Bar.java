package ru.atom.geometry;

public class Bar implements Collider {
    public int firstCornerX;
    public int firstCornerY;
    public int secondCornerX;
    public int secondCornerY;

    public Bar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY) {
        this.firstCornerX = firstCornerX;
        this.firstCornerY = firstCornerY;
        this.secondCornerX = secondCornerX;
        this.secondCornerY = secondCornerY;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other instanceof Point) {
            Point point = (Point) other;
            if ((point.x >= this.firstCornerX) && (point.x <= this.secondCornerX)
                    && (point.y >= this.firstCornerY) && (point.y <= this.secondCornerY))
                return true;
        }
        if (other instanceof Bar) {
            Bar bar = (Bar) other;
            if ((isValueInRange(bar.firstCornerX, firstCornerX, secondCornerX)
                    || isValueInRange(bar.secondCornerX, firstCornerX, secondCornerX)
                    || isValueInRange(firstCornerX, bar.firstCornerX, bar.secondCornerX)
                    || isValueInRange(secondCornerX, bar.firstCornerX, bar.secondCornerX))
                    && (isValueInRange(bar.firstCornerY, firstCornerY, secondCornerY)
                    || isValueInRange(bar.secondCornerY, this.firstCornerY, this.secondCornerY)
                    || isValueInRange(this.firstCornerY, bar.firstCornerY, bar.secondCornerY)
                    || isValueInRange(this.secondCornerY, bar.firstCornerY, bar.secondCornerY)))
                return true;
        }
        return false;
    }

    boolean isValueInRange(int value, int x1, int x2) {
        if (((value >= x1) && (value <= x2)) || ((value >= x2) && (value <= x1)))
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        // cast from Object to Point
        Bar bar = (Bar) o;

        // your code here
        if ((firstCornerX == bar.firstCornerX) && (firstCornerY == bar.firstCornerY)
                && (secondCornerX == bar.secondCornerX) && (secondCornerY == bar.secondCornerY))
            return true;
        else if ((firstCornerX == bar.secondCornerX) && (firstCornerY == bar.secondCornerY)
                && (secondCornerX == bar.firstCornerX) && (secondCornerY == bar.firstCornerX))

            return true;
        else if ((firstCornerX == bar.secondCornerX) && (firstCornerY == bar.firstCornerY)
                && (secondCornerX == bar.firstCornerX) && (secondCornerY == bar.secondCornerY))
            return true;
        else return false;
    }
}
