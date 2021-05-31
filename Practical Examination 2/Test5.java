import java.util.List;
import java.util.stream.Collectors;  
import java.util.stream.Stream;

class Test5 {
  /**
   * Main method for Test5.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    // PointStream
    i.expectReturn(
        "Main.pointStream(new Point(0, 0), p -> p).limit(5).collect(Collectors.toList())", 
        () -> Main.pointStream(new Point(0, 0), p -> p).limit(5)
                  .collect(Collectors.toList()), 
        List.of(new Point(0, 0), new Point(0, 0), new Point(0, 0), 
                new Point(0, 0), new Point(0, 0)));

    i.expectReturn(
        "Main.pointStream(new Point(2, 2), p -> p).limit(5).collect(Collectors.toList())", 
        () -> Main.pointStream(new Point(2, 2), p -> p).limit(5)
                  .collect(Collectors.toList()), 
        List.of(new Point(2, 2), new Point(2, 2), new Point(2, 2), 
                new Point(2, 2), new Point(2, 2)));
    
    i.expectReturn(
        "Main.pointStream(new Point(0, 0), p -> new Point(p.getX(), p.getY() + 1))"
          + ".limit(5).collect(Collectors.toList())", 
        () -> Main.pointStream(new Point(0, 0), p -> new Point(p.getX(), p.getY() + 1))
                  .limit(5).collect(Collectors.toList()), 
        List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2),
                new Point(0, 3), new Point(0, 4)));

    i.expectReturn(
        "Main.pointStream(new Point(0, 0), p -> new Point(p.getX() + 1, p.getY()))"
          + ".limit(5).collect(Collectors.toList())", 
        () -> Main.pointStream(new Point(0, 0), p -> new Point(p.getX() + 1, p.getY()))
                  .limit(5).collect(Collectors.toList()), 
        List.of(new Point(0, 0), new Point(1, 0), new Point(2, 0), 
               new Point(3, 0), new Point(4, 0)));
    
    i.expectReturn(
        "Main.pointStream(new Point(0, 0), p -> new Point(p.getX()+1, p.getY()+1))"
          + ".limit(5).collect(Collectors.toList())", 
        () -> Main.pointStream(new Point(0, 0), p -> new Point(p.getX() + 1, p.getY() + 1))
                  .limit(5).collect(Collectors.toList()), 
        List.of(new Point(0, 0), new Point(1, 1), new Point(2, 2), 
                new Point(3, 3), new Point(4, 4)));
    
    i.expectReturn(
        "Main.pointStream(new Point(5, 5), p -> new Point(p.getX() - 1, p.getY() - 1))"
          + ".limit(5).collect(Collectors.toList())", 
        () -> Main.pointStream(new Point(5, 5), p -> new Point(p.getX() - 1, p.getY() - 1))
                  .limit(5).collect(Collectors.toList()), 
        List.of(new Point(5, 5), new Point(4, 4), new Point(3, 3), 
                new Point(2, 2), new Point(1, 1)));

    i.expectReturn(
            "Main.pointStream(new Point(1, -1), p -> new Point(-p.getX(), -p.getY()))"
              + ".limit(5).collect(Collectors.toList())", 
            () -> Main.pointStream(new Point(1, -1), p -> new Point(-p.getX(), -p.getY()))
                      .limit(5).collect(Collectors.toList()), 
            List.of(new Point(1, -1), new Point(-1, 1), new Point(1, -1), 
                    new Point(-1, 1), new Point(1, -1)));
    
    // GenerateGrid
    i.expectReturn(
        "Main.generateGrid(new Point(0, 0), 2).collect(Collectors.toList())", 
        () -> Main.generateGrid(new Point(0, 0), 2).collect(Collectors.toList()), 
        List.of(new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)));

    i.expectReturn(
        "Main.generateGrid(new Point(0, 0), 3).collect(Collectors.toList())", 
        () -> Main.generateGrid(new Point(0, 0), 3).collect(Collectors.toList()), 
        List.of(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 0), 
                new Point(1, 1), new Point(1, 2), new Point(2, 0), new Point(2, 1), 
                new Point(2, 2)));
    
    i.expectReturn(
        "Main.generateGrid(new Point(-1, 0), 3).collect(Collectors.toList())", 
        () -> Main.generateGrid(new Point(-1, 0), 3).collect(Collectors.toList()), 
        List.of(new Point(-1, 0), new Point(-1, 1), new Point(-1, 2), new Point(0, 0), 
                new Point(0, 1), new Point(0, 2), new Point(1, 0), new Point(1, 1), 
                new Point(1, 2)));
            
    i.expectReturn(
        "Main.generateGrid(new Point(5, 5), 3).collect(Collectors.toList())", 
        () -> Main.generateGrid(new Point(5, 5), 3).collect(Collectors.toList()), 
        List.of(new Point(5, 5), new Point(5, 6), new Point(5, 7), new Point(6, 5), 
                new Point(6, 6), new Point(6, 7), new Point(7, 5), new Point(7, 6), 
                new Point(7, 7)));

    // concentricCircles
    i.expectReturn(
        "Main.concentricCircles(new Circle(new Point(0, 0), 2.0), x -> x)"
          + ".limit(5).collect(Collectors.toList())", 
        () -> Main.concentricCircles(new Circle(new Point(0, 0), 2.0), x -> x)
                  .limit(5).collect(Collectors.toList()), 
        List.of(new Circle(new Point(0, 0), 2.0), new Circle(new Point(0, 0), 2.0), 
                new Circle(new Point(0, 0), 2.0), new Circle(new Point(0, 0), 2.0), 
                new Circle(new Point(0, 0), 2.0)));
    
    i.expectReturn(
        "Main.concentricCircles(new Circle(new Point(1, 1), 1.0), x -> x + 1)"
          + ".limit(5).collect(Collectors.toList())", 
        () -> Main.concentricCircles(new Circle(new Point(1, 1), 1.0), x -> x + 1)
                  .limit(5).collect(Collectors.toList()), 
        List.of(new Circle(new Point(1, 1), 1.0), new Circle(new Point(1, 1), 2.0), 
                new Circle(new Point(1, 1), 3.0), new Circle(new Point(1, 1), 4.0), 
                new Circle(new Point(1, 1), 5.0)));

    i.expectReturn(
        "Main.concentricCircles(new Circle(new Point(1, 1), 5.0), x -> x - 1)"
          + ".limit(5).collect(Collectors.toList())", 
        () -> Main.concentricCircles(new Circle(new Point(1, 1), 5.0), x -> x - 1)
                  .limit(5).collect(Collectors.toList()), 
        List.of(new Circle(new Point(1, 1), 5.0), new Circle(new Point(1, 1), 4.0), 
                new Circle(new Point(1, 1), 3.0), new Circle(new Point(1, 1), 2.0), 
                new Circle(new Point(1, 1), 1.0)));
    
    i.expectReturn(
        "Main.concentricCircles(new Circle(new Point(0, 0), 2.0), x -> x * x)"
          + ".limit(5).collect(Collectors.toList())", 
        () -> Main.concentricCircles(new Circle(new Point(0, 0), 2.0), x -> x * x)
                  .limit(5).collect(Collectors.toList()), 
        List.of(new Circle(new Point(0, 0), 2.0), new Circle(new Point(0, 0), 4.0), 
                new Circle(new Point(0, 0), 16.0), new Circle(new Point(0, 0), 256.0), 
                new Circle(new Point(0, 0), 65536.0)));

    // pointStreamFromCircle
    i.expectReturn(
        "Main.pointStreamFromCircle(List.of(new Circle(new Point(0, 0), 1), " 
          + "new Circle(new Point(1, 1), 2), new Circle(new Point(-1, -1), 1))"
          + ".collect(Collectors.toList())", 
        () -> Main.pointStreamFromCircle(Stream.of(new Circle(new Point(0, 0), 1), 
                                                 new Circle(new Point(1, 1), 2), 
                                                 new Circle(new Point(-1, -1), 1)))
                                            .collect(Collectors.toList()), 
        List.of(new Point(0, 0), new Point(1, 1), new Point(-1, -1)));
  
    i.expectReturn(
        "Main.pointStreamFromCircle(List.of(new Circle(new Point(0, 0), 1), " 
          + "new Circle(new Point(0, 0), 2), new Circle(new Point(0, 0), 3))"
          + ".collect(Collectors.toList())", 
        () -> Main.pointStreamFromCircle(Stream.of(new Circle(new Point(0, 0), 1), 
                                                 new Circle(new Point(0, 0), 2), 
                                                 new Circle(new Point(0, 0), 3)))
                                              .collect(Collectors.toList()), 
        List.of(new Point(0, 0), new Point(0, 0), new Point(0, 0)));

    // estimatePi
    RandomPoint.setSeed(10);
    
    i.expectReturn(
        "Main.estimatePi(new Circle(new Point(0.5, 0.5), 0.5), "    
          + "() -> new RandomPoint(0, 1, 0, 1), 10000)", 
        () -> Main.estimatePi(new Circle(new Point(0.5, 0.5), 0.5),
                                              () -> new RandomPoint(0, 1, 0, 1), 10000),
        3.1284);
    
    RandomPoint.setSeed(1000);
    i.expectReturn(
        "Main.estimatePi(new Circle(new Point(0.5, 0.5), 0.5), "    
         + "() -> new RandomPoint(0, 1, 0, 1), 100000)", 
        () -> Main.estimatePi(new Circle(new Point(0.5, 0.5), 0.5),
                                             () -> new RandomPoint(0, 1, 0, 1), 10000),
        3.1304);
  }
}
