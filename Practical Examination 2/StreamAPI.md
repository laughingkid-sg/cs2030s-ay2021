# Stream API (Abridged)
This abridged Stream API contains documentation for the following methods:

- filter
- map
- flatMap
- distinct
- sorted (2 versions)
- peek
- limit
- takeWhile
- dropWhile
- forEach
- reduce (3 versions)
- count
- anyMatch
- allMatch
- noneMatch
- of
- iterate (2 versions)
- generate

## filter

    Stream<T> filter(Predicate<? super T> predicate)
    
    Returns a stream consisting of the elements of this stream that match the given predicate.
    
    This is an intermediate operation.
    
    Parameters:
    
    predicate - a non-interfering, stateless predicate to apply to each element to determine if it should be included
    
    Returns:
    
    the new stream
    
## map
    
    <R> Stream<R> map(Function<? super T, ? extends R> mapper)
    
    Returns a stream consisting of the results of applying the given function to the elements of this stream.
    
    This is an intermediate operation.
    
    Type Parameters:
    
    R - The element type of the new stream
    
    Parameters:
    
    mapper - a non-interfering, stateless function to apply to each element
    
    Returns:
    
    the new stream
    
## flatMap
    
    <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
    
    Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element. Each mapped stream is closed after its contents have been placed into this stream. (If a mapped stream is null an empty stream is used, instead.)
    
    This is an intermediate operation.
    
    API Note:
    
    The flatMap() operation has the effect of applying a one-to-many transformation to the elements of the stream, and then flattening the resulting elements into a new stream.
    
    Type Parameters:
    
    R - The element type of the new stream
    
    Parameters:
    
    mapper - a non-interfering, stateless function to apply to each element which produces a stream of new values
    
    Returns:
    
    the new stream
    
## distinct
    
    Stream<T> distinct()
    
    Returns a stream consisting of the distinct elements of this stream.
    
    For ordered streams, the selection of distinct elements is stable (for duplicated elements, the element appearing first in the encounter order is preserved.) For unordered streams, no stability guarantees are made.
    
    This is a stateful intermediate operation.
    
    Returns:
    
    the new stream
    
## sorted
    
    Stream<T> sorted()
    
    Returns a stream consisting of the elements of this stream, sorted according to natural order. If the elements of this stream are not Comparable, a java.lang.ClassCastException may be thrown when the terminal operation is executed.
    
    For ordered streams, the sort is stable. For unordered streams, no stability guarantees are made.
    
    This is a stateful intermediate operation.
    
    Returns:
    
    the new stream   

## sorted
    
    Stream<T> sorted(Comparator<? super T> comparator)
    
    Returns a stream consisting of the elements of this stream, sorted according to the provided Comparator.
    
    For ordered streams, the sort is stable. For unordered streams, no stability guarantees are made.
    
    This is a stateful intermediate operation.
    
    Parameters:
    
    comparator - a non-interfering, stateless Comparator to be used to compare stream elements
    
    Returns:
    
    the new stream   

## peek
    
    Stream<T> peek(Consumer<? super T> action)
    
    Returns a stream consisting of the elements of this stream, additionally performing the provided action on each element as elements are consumed from the resulting stream.
    
    This is an intermediate operation.
    
    For parallel stream pipelines, the action may be called at whatever time and in whatever thread the element is made available by the upstream operation. If the action modifies shared state, it is responsible for providing the required synchronization.
    
    Parameters:
    
    action - a non-interfering action to perform on the elements as they are consumed from the stream
    
    Returns:
    
    the new stream  

## limit
    
    Stream<T> limit(long maxSize)
    
    Returns a stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.
    
    This is a short-circuiting stateful intermediate operation.
    
    Parameters:
    
    maxSize - the number of elements the stream should be limited to
    
    Returns:
    
    the new stream
    
    Throws:
    
    IllegalArgumentException - if maxSize is negative
    
## takeWhile
    
    Stream<T> takeWhile(Predicate<? super T> predicate)
    
    Returns, if this stream is ordered, a stream consisting of the longest prefix of elements taken from this stream that match the given predicate. Otherwise returns, if this stream is unordered, a stream consisting of a subset of elements taken from this stream that match the given predicate.
    
    If this stream is ordered then the longest prefix is a contiguous sequence of elements of this stream that match the given predicate. The first element of the sequence is the first element of this stream, and the element immediately following the last element of the sequence does not match the given predicate.
    
    If this stream is unordered, and some (but not all) elements of this stream match the given predicate, then the behavior of this operation is nondeterministic; it is free to take any subset of matching elements (which includes the empty set).
    
    Independent of whether this stream is ordered or unordered if all elements of this stream match the given predicate then this operation takes all elements (the result is the same as the input), or if no elements of the stream match the given predicate then no elements are taken (the result is an empty stream).
    
    This is a short-circuiting stateful intermediate operation.
    
    Parameters:
    
    predicate - a non-interfering, stateless predicate to apply to elements to determine the longest prefix of elements.
    
    Returns:
    
    the new stream

## dropWhile
    
    Stream<T> dropWhile(Predicate<? super T> predicate)
    
    Returns, if this stream is ordered, a stream consisting of the remaining elements of this stream after dropping the longest prefix of elements that match the given predicate. Otherwise returns, if this stream is unordered, a stream consisting of the remaining elements of this stream after dropping a subset of elements that match the given predicate.
    
    If this stream is ordered then the longest prefix is a contiguous sequence of elements of this stream that match the given predicate. The first element of the sequence is the first element of this stream, and the element immediately following the last element of the sequence does not match the given predicate.
    
    If this stream is unordered, and some (but not all) elements of this stream match the given predicate, then the behavior of this operation is nondeterministic; it is free to drop any subset of matching elements (which includes the empty set).
    
    Independent of whether this stream is ordered or unordered if all elements of this stream match the given predicate then this operation drops all elements (the result is an empty stream), or if no elements of the stream match the given predicate then no elements are dropped (the result is the same as the input).
    
    This is a stateful intermediate operation.
    
    Parameters:
    
    predicate - a non-interfering, stateless predicate to apply to elements to determine the longest prefix of elements.
    
    Returns:
    
    the new stream

## forEach
    
    void forEach(Consumer<? super T> action)
    
    Performs an action for each element of this stream.
    
    This is a terminal operation.
    
    The behavior of this operation is explicitly nondeterministic. For parallel stream pipelines, this operation does _not_ guarantee to respect the encounter order of the stream, as doing so would sacrifice the benefit of parallelism. For any given element, the action may be performed at whatever time and in whatever thread the library chooses. If the action accesses shared state, it is responsible for providing the required synchronization.
    
    Parameters:
    
    action - a non-interfering action to perform on the elements

## reduce
    
    T reduce(T identity, BinaryOperator<T> accumulator)
    
    Performs a reduction on the elements of this stream, using the provided identity value and an associative accumulation function, and returns the reduced value. This is equivalent to:

             T result = identity;
             for (T element : this stream)
                 result = accumulator.apply(result, element)
             return result;
    
    but is not constrained to execute sequentially.
    
    The identity value must be an identity for the accumulator function. This means that for all t, accumulator.apply(identity, t) is equal to t. The accumulator function must be an associative function.
    
    This is a terminal operation.
    
    Parameters:
    
    identity - the identity value for the accumulating function
    
    accumulator - an associative, non-interfering, stateless function for combining two values
    
    Returns:
    
    the result of the reduction

## reduce
    
    Optional<T> reduce(BinaryOperator<T> accumulator)
    
    Performs a reduction on the elements of this stream, using an associative accumulation function, and returns an Optional describing the reduced value, if any. This is equivalent to:
    
        
             boolean foundAny = false;
             T result = null;
             for (T element : this stream) {
                 if (!foundAny) {
                     foundAny = true;
                     result = element;
                 }
                 else
                     result = accumulator.apply(result, element);
             }
             return foundAny ? Optional.of(result) : Optional.empty();
         
    but is not constrained to execute sequentially.
    
    The accumulator function must be an associative function.
    
    This is a terminal operation.
    
    Parameters:
    
    accumulator - an associative, non-interfering, stateless function for combining two values
    
    Returns:
    
    an Optional describing the result of the reduction
    
    Throws:
    
    NullPointerException - if the result of the reduction is null
    
## reduce
    
    <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
    
    Performs a reduction on the elements of this stream, using the provided identity, accumulation and combining functions. This is equivalent to:
    
        
             U result = identity;
             for (T element : this stream)
                 result = accumulator.apply(result, element)
             return result;
         
    
    but is not constrained to execute sequentially.
    
    The identity value must be an identity for the combiner function. This means that for all u, combiner(identity, u) is equal to u. Additionally, the combiner function must be compatible with the accumulator function; for all u and t, the following must hold:
    
        
             combiner.apply(u, accumulator.apply(identity, t)) == accumulator.apply(u, t)
         
    
    This is a terminal operation.
    
    Type Parameters:
    
    U - The type of the result
    
    Parameters:
    
    identity - the identity value for the combiner function
    
    accumulator - an associative, non-interfering, stateless function for incorporating an additional element into a result
    
    combiner - an associative, non-interfering, stateless function for combining two values, which must be compatible with the accumulator function
    
    Returns:
    
    the result of the reduction
    
## count
    
    long count()
    
    Returns the count of elements in this stream. This is a special case of a reduction and is equivalent to:

             return mapToLong(e -> 1L).sum();
    
    This is a terminal operation.
    
    Returns:
    
    the count of elements in this stream
    
## anyMatch
    
    boolean anyMatch(Predicate<? super T> predicate)
    
    Returns whether any elements of this stream match the provided predicate. May not evaluate the predicate on all elements if not necessary for determining the result. If the stream is empty then false is returned and the predicate is not evaluated.
    
    This is a short-circuiting terminal operation.
    
    Parameters:
    
    predicate - a non-interfering, stateless predicate to apply to elements of this stream
    
    Returns:
    
    true if any elements of the stream match the provided predicate, otherwise false
    
## allMatch
    
    boolean allMatch(Predicate<? super T> predicate)
    
    Returns whether all elements of this stream match the provided predicate. May not evaluate the predicate on all elements if not necessary for determining the result. If the stream is empty then true is returned and the predicate is not evaluated.
    
    This is a short-circuiting terminal operation.
    
    Parameters:
    
    predicate - a non-interfering, stateless predicate to apply to elements of this stream
    
    Returns:
    
    true if either all elements of the stream match the provided predicate or the stream is empty, otherwise false
    
## noneMatch
    
    boolean noneMatch(Predicate<? super T> predicate)
    
    Returns whether no elements of this stream match the provided predicate. May not evaluate the predicate on all elements if not necessary for determining the result. If the stream is empty then true is returned and the predicate is not evaluated.
    
    This is a short-circuiting terminal operation.
    
    Parameters:
    
    predicate - a non-interfering, stateless predicate to apply to elements of this stream
    
    Returns:
    
    true if either no elements of the stream match the provided predicate or the stream is empty, otherwise false
    
## of
    
    @SafeVarargs
    static <T> Stream<T> of(T... values)
    
    Returns a sequential ordered stream whose elements are the specified values.
    
    Type Parameters:
    
    T - the type of stream elements
    
    Parameters:
    
    values - the elements of the new stream
    
    Returns:
    
    the new stream
    

## iterate
    
    static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
    
    Returns an infinite sequential ordered Stream produced by iterative application of a function f to an initial element seed, producing a Stream consisting of seed, f(seed), f(f(seed)), etc.
    
    The first element (position 0) in the Stream will be the provided seed. For n > 0, the element at position n, will be the result of applying the function f to the element at position n - 1.
    
    The action of applying f for one element happens before the action of applying f for subsequent elements. For any given element the action may be performed in whatever thread the library chooses.
    
    Type Parameters:
    
    T - the type of stream elements
    
    Parameters:
    
    seed - the initial element
    
    f - a function to be applied to the previous element to produce a new element
    
    Returns:
    
    a new sequential Stream
    

## iterate
    
    static <T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
    
    Returns a sequential ordered Stream produced by iterative application of the given next function to an initial element, conditioned on satisfying the given hasNext predicate. The stream terminates as soon as the hasNext predicate returns false.
    
    Stream.iterate should produce the same sequence of elements as produced by the corresponding for-loop:
    
             for (T index=seed; hasNext.test(index); index = next.apply(index)) {
                 ...
             }
         
    The resulting sequence may be empty if the hasNext predicate does not hold on the seed value. Otherwise the first element will be the supplied seed value, the next element (if present) will be the result of applying the next function to the seed value, and so on iteratively until the hasNext predicate indicates that the stream should terminate.
    
    Type Parameters:
    
    T - the type of stream elements
    
    Parameters:
    
    seed - the initial element
    
    hasNext - a predicate to apply to elements to determine when the stream must terminate.
    
    next - a function to be applied to the previous element to produce a new element
    
    Returns:
    
    a new sequential Stream

## generate
    
    static <T> Stream<T> generate(Supplier<? extends T> s)
    
    Returns an infinite sequential unordered stream where each element is generated by the provided Supplier. This is suitable for generating constant streams, streams of random elements, etc.
    
    Type Parameters:
    
    T - the type of stream elements
    
    Parameters:
    
    s - the Supplier of generated elements
    
    Returns:
    
    a new infinite sequential unodered Stream
