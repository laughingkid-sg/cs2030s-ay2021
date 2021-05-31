class Circle {
  private Point center;
  private double radius;

  public Circle(Point center, double radius) {
    this.center = center;
    this.radius = radius;
  }

  public boolean contains(Point p) {
    return (p.distanceTo(this.center) < this.radius);
  }

  @Override
  public String toString() {
    return "{ center: " + this.center + ", radius: " + this.radius + " }";
  }

  public Point getCenter() {
    return this.center;
  }

  public double getRadius() {
    return this.radius;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Circle) {
      Circle circle = (Circle) obj;
      return (circle.center.equals(this.center) && circle.radius == this.radius);
    }
    return false;
  }
}
