import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;


/**
 * @author A0000000X
 */

public class Trace<T> {
  private T value;
  private List<T> history;

  Trace(T value, List<T> history) {
    this.value = value;
    this.history = history;
  }


  @SafeVarargs
  public static <T> Trace<T> of(T ...a) {
    if (a.length == 1) {
      return new Trace<T>(a[0], new ArrayList<T>());
    }
    List<T> temp = new ArrayList<>();
    Collections.addAll(temp, a);
    temp.remove(0);
    return new Trace<T>(a[0], temp);
  }

  public T get() {
    return this.value;
  }

  public List<T> history() {
    return this.history;
  }

  public Trace<T> back(int n) {
    if (this.history.size() - 1 < n) {
      return Trace.of(this.history.get(0));
    }
    return new Trace<>(this.history.get(this.history.size() - n), new ArrayList<T>(this.history.subList(0, this.history.size() - n)));
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Trace) {
      @SuppressWarnings("unchecked")
      Trace<T> trace = (Trace<T>) obj;
      if (this.value.equals(trace.get())) {
        return this.history.equals(trace.history());
      }
    }
    return false;
  } 

  public <U> Trace <U> map(Transformer <? super T, ? extends U> mapper) {
    List<T> temp = (List<T>) new ArrayList<>(this.history);
    temp.add(this.value);
    @SuppressWarnings("unchecked")
    List<U> out = (List<U>) temp; 
    return new Trace<>(mapper.transform(this.value), out);
  }

  public <U> Trace<U> flatMap(Transformer <? super T, ? extends Trace<? extends U>> mapper) {

    Trace<? extends U> temp = mapper.transform(this.value);
    @SuppressWarnings("unchecked")
    List<U> mapHis = (List<U>) temp.history();

    List<T> myList = (List<T>) new ArrayList<>(this.history);
    myList.add(this.value);
    
    @SuppressWarnings("unchecked")
    List<U> thisHis = (List<U>) myList;
    thisHis.addAll(mapHis);
    
    return new Trace<>(temp.get(), thisHis);
  }
}
