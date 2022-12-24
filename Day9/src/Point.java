public class Point {
    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point(" + x + "/" + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Point that))
            return false;
        if(this.x != that.getX() || this.y != that.getY())
            return false;
        return true;
    }
}
