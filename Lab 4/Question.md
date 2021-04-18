# Lab 4: Box

- Deadline: 1 March, 2021, Monday, 23:59, SST
- Mark: 4%

## Prerequisite:

- Caught up to Unit 24 of Lecture Notes
- Familiar with CS2030S Java style guide

## A Box

In this lab, we are going to build our own generic wrapper class, a `Box<T>`.  This is a wrapper class that can be used to store an item of any reference type.  For this lab, our `Box<T>` is not going to be a very useful abstraction.  Not to worry.  we will slowly add more functionalities to it later in this module.

In the following, we will slowly build up the `Box<T>` class along with some additional interfaces.  We suggest that you develop your class step-by-step in the order below.

## The Basics

Build a generic class `Box<T>` that
- contains a `private final` field of type `T` to store the content of the box.
- overrides the `equals` method from `Object` to compare if two boxes are the same.  Two boxes are the same if the content of the box equals each other, as decided by their respective `equals` method.
- overrides the `toString` method so it returns the string representation of its content, between `[` and `]`.
- provides a class method called `of` that returns a box with a given object.  If `null` is passed into `of`, then a `null` should be returned.

The method `of` is called a _factory method_.  A factory method is a method provided by a class for the creation of an instance of the class.
Using a public constructor to create an instance necessitates calling `new` and allocating a new object on the heap every time.  A factory method, on the other hand, allows the flexibility of reusing the same instance.  The `of` method does not reuse instances.  You will write another one that reuses available instances in the next section.

With the availability of the `of` factory method, `Box<T>` should keep the constructor private.  

Remember to use `@SuppressWarnings("unchecked")` responsibly.

The sequence below shows how we can use a `Box` using the methods you developed above.

```
jshell> Box.of(4)
$.. ==> [4]
jshell> Box.of(4).equals(Box.of(4))
$.. ==> true
jshell> Box.of(4).equals(4)
$.. ==> false
jshell> Box.of(Box.of(0)).equals(Box.of(0))
$.. ==> false
jshell> Box.of(Box.of(0)).equals(Box.of(Box.of(0)))
$.. ==> true
jshell> Box.of("string")
$.. ==> [string]
jshell> Box.of("string").equals(Box.of(4))
$.. ==> false
jshell> Box.of("string").equals(Box.of("null"))
$.. ==> false
jshell> Box.of(null)
$.. ==> null
```

You can test your `Box<T>` more comprehensively by running:
```
javac Test1.java
java Test1
```

There shouldn't be any compilation error when you compile `Test1.java` and all tests should prints `ok`.

## An Empty Box

The `of` method returns a `null` if it is given a `null`.  An alternative (some might say, cleaner) design is to make our factory method returns an empty box instead if we try to create a box of `null`.

- Add a class method in `Box` called `empty()` that creates and returns an empty box, i.e., a box with a `null` item stored in it.

Since empty boxes are likely common, we want to _cache_ and reuse the empty box, that is, create one as a private final class field called `EMPTY_BOX`, and whenever we need to return an empty box, `EMPTY_BOX` is returned.

What should the type of `EMPTY_BOX` be? The type should be general enough to hold a box of any type (`Box<Shop>`, `Box<Circle>`, etc). `EMPTY_BOX` should, therefore, be assigned the most general generic `Box<T>` type. Hint: It is not `Box<Object>`.

Your method `empty()` should do nothing more than to type-cast `EMPTY_BOX` to the right type (i.e., to `Box<T>`) before returning, to ensure type consistency.  

Again, remember to use `@SuppressWarnings("unchecked")` responsibly.

Add a boolean method `isPresent` that returns `true` if the box contains something; `false` if the box is empty.

Finally, add a class factory method called `ofNullable`, which behaves just like `of` if the input is non-null, and returns an empty box if the input is `null`.

Here is how the `Box` class can be used with the added methods above:

```
jshell> Box.ofNullable(4)
$.. ==> [4]
jshell> Box.ofNullable("string")
$.. ==> [string]
jshell> Box.ofNullable(null)
$.. ==> []
jshell> Box.empty() == Box.empty()
$.. ==> true
jshell> Box.ofNullable(null) == Box.empty()
$.. ==> true
jshell> Box.ofNullable(null).equals(Box.empty())
$.. ==> true
jshell> Box.ofNullable(null).equals(Box.of(null))
$.. ==> false
jshell> Box.ofNullable("string").isPresent()
$.. ==> true
jshell> Box.ofNullable(null).isPresent()
$.. ==> false
```

You can test the additions to `Box<T>` above more comprehensively by running:
```
javac Test2.java
java Test2
```
There shouldn't be any compilation error when you compile `Test2.java` and all tests should prints `ok`.

## Checking the Content of the Box

So far, we can only keep things inside our `Box`, which is not very exciting.  In the rest of the lab, we will expand `Box` to support operations on the content inside.

Let's start by writing a generic interface called `BooleanCondition<T>` with a single abstract boolean method `test`.  The method `test` should take a single argument of type `T`.

Now, one can create a variety of classes by implementing this interface.  By implementing the method `test` differently, we can create different conditions and check if the item contained in the box satisfies a given condition or not.

Create a method `filter` in `Box` that takes in a `BooleanCondition` as a parameter.  The method `filter` should return an empty box if the item in the box failed the test (i.e., the call to `test` returns `false`).  Otherwise, `filter` leaves the box untouched and returns the box as it is.  Calling `filter` on an empty box just returns an empty box.

Here is an example of how `BooleanCondition<T>` can be used with `Box<T>`.  Note that we make use of the class `Number`, a superclass of `Integer`, below.

```
jshell> class AlwaysTrue<T> implements BooleanCondition<T> {
   ...>   public boolean test(T t) { return true; }
   ...> }
jshell> class AlwaysFalse<T> implements BooleanCondition<T> {
   ...>   public boolean test(T t) { return false; }
   ...> }
jshell> Box.of(4).filter(new AlwaysTrue<>());
$.. ==> [4]
jshell> Box.empty().filter(new AlwaysTrue<>());
$.. ==> []
jshell> Box.of("string").filter(new AlwaysFalse<>());
$.. ==> []
jshell> Box.empty().filter(new AlwaysFalse<>());
$.. ==> []
jshell> class IntValueIsPositive implements BooleanCondition<Number> {
   ...>   public boolean test(Number t) { return t.intValue() > 0; }
   ...> }
jshell> Box.<Double>ofNullable(8.8).filter(new IntValueIsPositive());
$.. ==> [8.8]
jshell> Box.<Long>ofNullable(-100L).filter(new IntValueIsPositive());
$.. ==> []
jshell> Box.<Double>ofNullable(8.8).filter(new IntValueIsPositive()).filter(new IntValueIsPositive())
$.. ==> [8.8]
jshell> Box.<Long>ofNullable(-100L).filter(new IntValueIsPositive()).filter(new IntValueIsPositive());
$.. ==> []
```

You can test the additions to `Box<T>` above more comprehensively by running:
```
javac Test3.java
java Test3
```
There shouldn't be any compilation error when you compile `Test3.java` and all tests should prints `ok`.

## Implement Your Own Conditions

The test cases above show you how you could create a class that implements a `BooleanCondition`.  Now you should implement your own.

Create a class called `DivisibleBy` that implements `BooleanCondition` on `Integer` that checks if a given integer is divisible by another integer.  The `test` method should return `true` if it is divisible; return `false` otherwise.

Create another class called `LongerThan` that implements `BooleanCondition` on `String` that checks if a given string is longer than a given limit.  The `test` method should return `true` if it is longer; return `false` otherwise.

Here is how it should work:

```
jshell> new DivisibleBy(5).test(4);
$.. ==> false
jshell> new DivisibleBy(5).test(10);
$.. ==> true
jshell> Box.of(10).filter(new DivisibleBy(2));
$.. ==> [10]
jshell> Box.of(3).filter(new DivisibleBy(2));
$.. ==> []
jshell> Box.<Integer>empty().filter(new DivisibleBy(10));
$.. ==> []
jshell>
jshell> Box.of("").filter(new LongerThan(10));
$.. ==> []
jshell> Box.of("123456789").filter(new LongerThan(10));
$.. ==> []
jshell> Box.of("1234567890").filter(new LongerThan(10));
$.. ==> []
jshell> Box.of("1234567890A").filter(new LongerThan(10));
$.. ==> [1234567890A]
jshell> Box.<String>empty().filter(new LongerThan(10));
$.. ==> []
```

On the other hand, calling
```
Box.of("hello").filter(new DivisibleBy(10));
```
should result in a compilation error.

You can test your additions to `Box<T>` more comprehensively by running:
```
javac Test4.java
java Test4
```
There shouldn't be any compilation error when you compile `Test4.java` and all tests should prints `ok`.

## Transforming a Box

Now, we are going to write an interface (along with its implementations) and a method in Box that allows a box to be transformed into another box, possibly containing a different type.

First, create an interface called `Transformer<T,U>` with an abstract method called `transform` that takes in an argument of generic type `T` and returns a value of generic type `U`.  

Write a method called `map` in the class `Box` that takes in a `Transformer`, and use the given `Transformer` to transform the box (and the value inside) into another box of type `Box<U>`.  Calling `map` on an empty box should just return an empty box.

In addition, implement your own `Transformer` in a non-generic class called `LastDigitsOfHashCode` to transform the content of the box into a box of integer, the value of which is the last _k_ digits of the value returned by calling `hashCode()` on the content of the original box (ignoring the positive/negative sign and leading zeros).  The value _k_ is passed in through the object of `LastDigitsOfHashCode`.  The method `hashCode()` is defined in the class `Object`.

```
jshell> class AddOne implements Transformer<Integer,Integer> {
   ...>   public Integer transform(Integer t) { return t + 1; }
   ...> }
jshell> class StringLength implements Transformer<String,Integer> {
   ...>   public Integer transform(String t) { return t.length(); }
   ...> }
jshell>
jshell> Box.of(4).map(new AddOne());
$.. ==> [5]
jshell> Box.<Integer>empty().map(new AddOne());
$.. ==> []
jshell> Box<Number> b = Box.of(4).map(new AddOne());
$.. ==> [5]
jshell>
jshell> Box.of("string").map(new StringLength());
$.. ==> [6]
jshell> Box.of("string").map(new StringLength()).map(new AddOne());
$.. ==> [7]
jshell> Box.of("string").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne());
$.. ==> []
jshell> Box.of("chocolates").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne());
$.. ==> [11]
jshell> Box.<String>empty().map(new StringLength());
$.. ==> []
jshell>
jshell> class AlwaysNull implements Transformer<Integer,Object> {
   ...>   public Object transform(Integer t) { return null; }
   ...> }
jshell> Box.of(4).map(new AlwaysNull());
$.. ==> []
jshell>
jshell> new LastDigitsOfHashCode(4).transform("string");
$.. ==> 5903
jshell> new LastDigitsOfHashCode(4).transform(123456);
$.. ==> 3456
jshell> Box.of("string").map(new LastDigitsOfHashCode(2));
$.. ==> [3]
jshell> Box.of(123456).map(new LastDigitsOfHashCode(5));
$.. ==> [23456]
jshell> Box<Number> b = Box.of(new Integer[] {8, 8, 8}).map(new LastDigitsOfHashCode(5));
```

You can test your additions to `Box<T>` more comprehensively by running:
```
javac Test5.java
java Test5
```
There shouldn't be any compilation error when you compile `Test5.java` and all tests should prints `ok`.

## Box in a Box

The `Transformer` interface allows us to transform the content of the box from one type into any other type, including a box! You have seen examples above where we have a box inside a box: `Box.of(Box.of(0))`.

Now, implement your own `Transformer` in a class called `BoxIt<T>` to transform an item into a box containing the item. The corresponding type `T` is transformed into `Box<T>`. This transformer, when invoked with `map`, results in a new box within the box.

```
jshell> Box.of(4).map(new BoxIt<>())
$.. ==> [[4]]
jshell> Box.of(Box.of(5)).map(new BoxIt<>())
$.. ==> [[[5]]]
jshell> Box.ofNullable(null).map(new BoxIt<>())
$.. ==> []
jshell>
```

You can test your `Box<T>` by running:
```
javac Test6.java
java Test6
```
There shouldn't be any compilation error when you compile `Test6.java` and all tests should prints `ok`.

## Files

A set of empty files have been given to you.  You should only edit these files.  You must not add any additional files.

The files `Test1.java`, `Test2.java`, etc., as well as `CS2030STest.java`, are provided for testing.  You can edit them to add your own test cases, but they will not be submitted.

## Following CS2030S Style Guide

You should make sure that your code follows the [given Java style guide](https://nus-cs2030s.github.io/2021-s2/style.html)


## Grading

This lab is worth 16 marks and contributes 4% to your final grade.  The marking scheme is as follows:

- Style: 2 marks
- Correctness: 14 marks

We will deduct 1 mark for each unnecessary use of `@SuppressWarnings` and for each raw type.  `@SuppressWarnings` should be used only in the two places mentioned above.

Note that the style marks are conditioned on the evidence of efforts in solving Lab 4.  

## WARNING ❗️

We would like to remind you of the following:

- Use only the `submit-labX` script to submit your lab.  Failure to do so will lead to a 50% penalty on your lab grade.
- The grace period for getting used to the submission system is over.  We will not waive the late penalty if students fail to submit properly.  Please check your repo after running `submit-labX` to ensure that your files have been added correctly. The URL to your repo is given after you run `submit-labX`.
