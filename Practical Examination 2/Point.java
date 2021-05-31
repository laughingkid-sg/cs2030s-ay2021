class Point {
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distanceTo(Point p) {
    return Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));
  }

  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      Point point = (Point) obj;
      return (this.x == point.x && this.y == point.y);
    }
    return false;
  }
}
