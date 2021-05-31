/*
 * CS2030S PE2 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Main {

  public static Stream<Point> pointStream(Point point, Function<Point, Point> f) {
    return Stream.<Point>iterate(point, p -> f.apply(p));
  }

  public static Stream<Point> generateGrid(Point point, int n) {
    return Stream.<Point>iterate(point, p -> new Point(p.getX() + 1, p.getY())).limit(n)
                 .flatMap(p -> Stream.iterate(p, pt -> new Point(pt.getX(), pt.getY() + 1)).limit(n));
  }

  public static Stream<Circle> concentricCircles(Circle circle, Function<Double, Double> f) {
    return Stream.<Circle>iterate(circle, c -> new Circle(c.getCenter(), f.apply(c.getRadius())));
  }

  public static Stream<Point> pointStreamFromCircle(Stream<Circle> circles) {
    return circles.map(c -> c.getCenter());
  }

  public static double estimatePi(Circle c, Supplier<RandomPoint> supplier, int n) {
    return (double) (4 * Stream.<Point>generate(supplier).limit(n)
                 .reduce(0, (count, pt) -> c.contains(pt) ? count + 1 : count, Integer::sum)) / n;
  }
}
