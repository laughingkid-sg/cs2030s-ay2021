/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author Boyd Anderson
 */
public class IntegerToString implements Transformer<Integer, String> {

  @Override
  public String transform(Integer t) {
    return "\"" + t.toString() + "\"";
  }
}
