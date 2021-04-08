INSTRUCTIONS
------------

1. This Mock Practical Assessment consists of one question.  

2. The total mark for this assessment is 15.  

3. This is an OPEN BOOK assessment.  You are only allowed to refer to written/printed notes.  No online resources/digital documents are allowed, except those accessible from the PE nodes (peXXX.comp.nus.edu.sg) (e.g., man pages are allowed).

4. You should see the following in your home directory.
   
   - The files Test1.java, Test2.java... Test5.java, and CS2030STest.java for testing your solution.
   - The skeleton files for Question 1: `Trace.java`
   - The following files to solve Question 2 are provided for your: `SubTrace.java` and `Transformer.java`.
     
5. Solve the programming tasks by creating any necessary files and editing them.  Run the command:

   ~/cs2030s/submit-mockpe2

   after the exam is over.

6. Only the files directly under your home directory will be graded.  Do not put your code under a subdirectory.

7. Write your student number on top of EVERY FILE you created or edited as part of the @author tag.  Do not write your name.

# API Reference

You will need to use the interface `List<T>` and its implementation `ArrayList<T>` for this question.  Some useful methods are:

- `boolean add(T item)`: append the item to the end of the list
- `boolean addAll(Collection<? extends T> items)`: insert all the items in the given collection at the end of the list.  `List` is a subtype of `Collection`.
- `T get(index)`: retrieve the item at the specified index without removing the item.  The first element has an index of 0.
- `T remove(index)`: retrieve the item at the specified index and remove the item from the list.  The first element has an index of 0.
- `int size()`: return the number of elements in the list.


You can create a new empty `ArrayList` with `new ArrayList<T>()`. `ArrayList` overrides `Object::equals` to compare if every corresponding items in two lists are equals.

# Mock PE: A Blast From the Past

We would like to create a class that allows us to maintain a trace of changes that has occurred to a variable.  The ability to trace through changes that occurred is useful in many cases: to undo the changes if necessary, to help us debug, to explore a different path of computations, etc.

Create an _immutable_ `Trace<T>` class that encapsulates a variable of type `T` and its history of changes (as a `List`).

- A `Trace<T>` object can be created using the static factory `of` method, passing in its value as the first argument and its (optional) history as the rest of the arguments.  The history is always listed with the oldest change first.
- Implement a `get()` method to retrieve the current value encapsulated in the `Trace<T>` instance.
- Implement a `history()` method to retrieve the past values encapsulated in the `Trace<T>` instance as a `List<T>`.  The past values retrieved should list the oldest value first.

```
jshell> Trace.of("hello").get()
$.. ==> "hello"

jshell> Trace.of("hello").history()
$.. ==> []

jshell> Trace.of("hello", "h", "he", "hel", "hell").get()
$.. ==> "hello"

jshell> Trace.of("hello", "h", "he", "hel", "hell").history()
$.. ==> [h, he, hel, hell]

jshell> Trace.of(1).get()
$.. ==> 1

jshell> Trace.of(1, 5, 4, 3, 2).history()
$.. ==> [5, 4, 3, 2]
```

You will need to make `of` accepts a variable number of arguments (commonly known as varargs).  Recall that using generic type with varargs will lead to a compiler warning about heap pollution.  If you are sure that you are handling the generic type correctly, you can suppress this warning with `@SafeVarargs` annotation.

# Going Back in Time

Now that we have the history of changes to a variable, let's add a `back` method to go back to the past.  We cannot go back beyond the oldest value even if we try to.  The method `back` takes in an integer to indicate how many steps we want to go back.

Add an `equals` method to compare if two `Trace` objects are equals.  Two `Trace`s are equals if their values are equals and they have the same history.

```
jshell> Trace.of("hello", "h", "he", "hel", "hell").back(2).get()
$.. ==> "hel"

jshell> Trace.of("hello", "h", "he", "hel", "hell").back(2).history()
$.. ==> [h, he]

jshell> Trace.of("hello", "h", "he", "hel", "hell").back(9).get()
$.. ==> "h"

jshell> Trace.of(1, 5, 4, 3, 2).equals(Trace.of(1, 2, 3, 4, 5))
$.. ==> false

jshell> Trace.of(1, 5, 4, 3, 2).equals(Trace.of(1))
$.. ==> false

jshell> Trace.of(1, 5, 4, 3, 2).equals(Trace.of(0, 5, 4, 3, 2, 1).back(1))
$.. ==> true

jshell> Trace<Boolean> t = Trace.of(true, false, true, true, false)
jshell> Trace<Boolean> u = t.back(1)
jshell> t.get() == true 
$.. ==> true
jshell> t.history().equals(List.of(false, true, true, false))
$.. ==> true

```

# Map

Now that we have a value and its history encapsulated within the `Trace` class, let's add the ability to manipulate this value and adds it to the history.  Add a `map` method that takes in a `Transformer` to update its value.  Remember to add the pre-updated value to the history.

```
jshell> Trace.of("h").map(s -> s + "ello").get()
$.. ==> "hello"

jshell> Trace.of("h").map(s -> s + "ello").history()
$.. ==> [h]

jshell> Trace.of(1, 0).map(x -> x + 1).map(y -> y + 2).history()
$.. ==> [0, 1, 2]

jshell> Trace.of(1, 0).map(x -> x + 1).back(1).map(y -> y + 2).history()
$.. ==> [0, 1]

jshell> Trace.of("h").map(x -> x).get().equals(Trace.of("h").get())
$.. ==> true

jshell> Trace.of("h").map(x -> x).equals(Trace.of("h"))
$.. ==> false

jshell> Transformer<Integer,Integer> f = x -> x + 1
jshell> Transformer<Integer,Integer> g = x -> x * 10
jshell> Transformer<Integer,Integer> h = x -> g.transform(f.transform(x))

jshell> Trace.of(10).map(f).map(g).get().equals(Trace.of(10).map(h).get())
$.. ==> true
jshell> Trace.of(10).map(f).map(g).equals(Trace.of(10).map(h))
$.. ==> false

jshell> Transformer<Integer, Integer> collatz = x -> (x % 2 == 0) ? (x / 2) : (3 * x + 1)
jshell> Trace<Integer> t = Trace.of(9)
jshell> while (t.get() != 1) {
   ...>   t = t.map(collatz);
   ...> }
jshell> t.history()
$.. ==> [9, 28, 14, 7, 22, 11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2]

jshell> Trace<Boolean> t = Trace.of(true, false, true, true, false)
jshell> t.map(x -> !x)
$.. ==> false (history: [false, true, true, false, true])
jshell> t.get()
$.. ==> true
jshell> t.history()
$.. ==> [false, true, true, false]

```

As an aside, the `collatz` lambda implements the famous function that defines the <a href="https://en.wikipedia.org/wiki/Collatz_conjecture">Collatz conjecture</a> (aka 3n+1 problem).

# flatMap

Now that we have a rather useful `Trace` class, we can write a method that builds up its own `Trace` object. We want to be able to apply such methods to the value stored in `Trace` and merge the history.  Add a `flatMap` method to do this.

```
jshell> Trace.of(1).flatMap(x -> Trace.of(x + 1).map(y -> y + 1)).get()
$.. ==> 3
jshell> Trace.of(1).flatMap(x -> Trace.of(x + 1).map(y -> y + 1)).history()
$.. ==> [1, 2]

jshell> Trace<Long> div2(Long n) {
   ...>     return (n <= 2) ? Trace.of(1L) : Trace.of(n/2).flatMap(y -> div2(y));
   ...> }
jshell> Trace.of(4905L).flatMap(x -> div2(x))
$.. ==> 1 (history: [4905, 2452, 1226, 613, 306, 153, 76, 38, 19, 9, 4, 2])

jshell> Trace<Boolean> t = Trace.of(true, true, false, true, false)
jshell> Trace<Boolean> u = t.flatMap(x -> Trace.of(!x))
jshell> t.get()
$.. ==> true
jshell> t.history()
$.. ==> [true, false, true, false]
```

Now make sure your `map` and `flatMap` methods are flexible enough to handle functions other than those that take in `T` and returns `T`

Use the given class `SubTrace` to test if your `map` and `flatMap` have the correct type. 

In the following tests, it is more important for your code to compile without type errors.

```
jshell> Transformer<Object,Integer> f = x -> x.hashCode()
jshell> Trace<Number> t = Trace.<Number>of(23.6).map(f)

jshell> Transformer<Object, SubTrace<Integer>> g = x -> SubTrace.of(x.hashCode())
jshell> Trace<Number> t = Trace.<Number>of(23.6).flatMap(g)
```
