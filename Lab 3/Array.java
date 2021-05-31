class Array<T extends Comparable<T>> {
  private T[] array;
  private int arraySize;

  Array(int size) {
    this.arraySize = size;   
    @SuppressWarnings("unchecked")
    T[] myArray = (T[]) new Comparable[size];
    this.array = myArray;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    T minItem = this.array[0]; 
    for (T item : this.array) {
      if (item.compareTo(minItem) == -1) {
        minItem = item;
      }
    }
    return minItem; 
  }
}
