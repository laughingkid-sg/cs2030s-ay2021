import java.util.Random;

class RandomPoint extends Point {
  private static Random rng = new Random(1);

  public static void setSeed(int seed) {
    rng = new Random(seed);
  }

  public RandomPoint(double minX, double maxX, double minY, double maxY) {
    super(rng.nextDouble() * (maxX - minX) + minX, rng.nextDouble() * (maxY - minY) + minY);
  }
}

