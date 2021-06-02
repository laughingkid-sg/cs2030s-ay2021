/**
 * The BoxIt class transform an item into
 * a box containing the item.
 *
 * @author 
 * @version CS2030S AY20/21 Semester 2
 */
class BoxIt<T> implements Transformer<T, Box<T>> {

  @Override
  public Box<T> transform(T t) {
    return Box.ofNullable(t);
  }
}
