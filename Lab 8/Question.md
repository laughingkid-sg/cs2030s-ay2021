# Lab 8: Keep Your World Moving

- Deadline: 16 April 2021, Friday, 23:59, SST
- Mark: 4%

Students who need more time to complete the lab will be granted a deadline extension until 19 April 2021, Monday, 23:59, SST

## Prerequisite

- Caught up to Unit 36 of Lecture Notes

## Problem Description

We have a Web API online for querying bus services and bus stops in Singapore.  You can go ahead and try:

- [https://cs2030-bus-api.herokuapp.com/bus_services/96](https://cs2030-bus-api.herokuapp.com/bus_services/96) returns the list of bus stops (id followed by description) served by Bus 96.
- [https://cs2030-bus-api.herokuapp.com/bus_stops/16189](https://cs2030-bus-api.herokuapp.com/bus_stops/16189) returns the description of the stop followed by a list of bus services that serve the stop.

(note: our database is three years old though -- don't rely on this for your daily commute!)

In this lab, we will write a program that uses the Web API to do the following: Given the current stop `S`, and a search string `Q`, returns the list of buses serving `S` that also serves any stop with a description containing `Q`.  For instance, given `16189` and `Clementi`, the program will output

```
Search for: 16189 <-> Clementi:
From 16189
- Can take 96 to:
  - 17171 Clementi Stn
  - 17091 Aft Clementi Ave 1
  - 17009 Clementi Int
- Can take 151 to:
  - 17091 Aft Clementi Ave 1
- Can take 151e to:
  - 17091 Aft Clementi Ave 1

Took 11,084ms
```

The pairs of `S` and `Q` can be either entered through the standard input or given to the program in a text file, with every pair of `S` and `Q` in a separate line.

A program to query the bus API and given a query has been written.  Study the program carefully to understand what it does and how it works.

The given program, however, is written synchronously.  Every query to the Web API is done one-by-one, and the program has to wait until one query completes before it can continue the execution of the program.  As a result, the program is slower than it should.

Your task, for this lab, is to change the given program so that it executes asynchronously.  Doing so can significantly speed up the program.  

The root of synchronous Web API access can be found in the method `httpGet` in `BusAPI.java`, in which the invocation of method `send(https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html#send(java.net.http.HttpRequest,java.net.http.HttpResponse.BodyHandler))` from the class [HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html) is done synchronously (i.e., it blocks until the response returns).    

`HttpClient` also provides an asynchronous version of `send` called `[sendAsync](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html#sendAsync(java.net.http.HttpRequest,java.net.http.HttpResponse.BodyHandler))`, which is the same as `send` excepts that it is asynchronous and returns a `CompletableFuture<HttpResponse<T>>` instead of `HttpResponse<T>`.  (You do not need to get into the nitty-gritty details of the `HttpClient` and `HttpResponse` for this lab -- but they are good to know, read up about them at your leisure).

To make the program asynchronous, you should first change the invocation of `send` in `BusAPI` to `sendAsync`.  All other changes will be triggered by this.  The list of things you should change are:

- `BusAPI::getBusStopsServedBy` now returns a `CompletableFuture<String>`
- `BusAPI::getBusServicesAt` now returns a `CompletableFuture<String>`
- `BusStop::getBusServices` now returns a `CompletableFuture<Set<BusService>>`
- `BusService::getBusStops` now returns a `CompletableFuture<Set<BusStop>>`
- `BusService::findStopsWith` now returns a `CompletableFuture<Set<BusStop>>`
- `BusRoutes` now stores a `CompletableFuture<Set<BusStop>>` instead.`
- `BusRoutes::description` now returns a `CompletableFuture<String>` (hint: use `thenCombine`)
- `BusSg::findBusServicesBetween` now returns a `CompletableFuture<BusRoutes>`

It is important that at no point in any of the code above, you call `CompletableFuture::join` (or `get`) on any of the `CompletableFuture`, so everything that has been done so far, from the lower-level Web API calls to the higher-level logic of searching for bus services, is done asynchronously.

For this lab, the only place where you should `join` is in `main()`, where you now have multiple `CompletableFuture` objects, one from each call to `findBusServicesBetween`.  As the final step, you should change `main()` so that it waits for all the `CompletableFuture` objects to complete using `allOf` and `join`, followed by printing out the description of the bus routes found.

The speed up your would experience for the asynchronous version depends on the complexity of the inputs.  For the following test input:
```
08031 Orchard
17009 NUS
17009 MRT
15131 Stn
08031 Int
12345 Dummy
```
I reduced the time from around 150-180 seconds to 10-15 seconds, more than 10 times speedup.  Your mileage may vary, but you should see some speed up in the total query time.

Your code should produce the exact same set of bus routes as the synchronous version, although it can be in a different order.

```bash
$ javac *java
$ java Main < test1.in
$ java Main < test2.in
$ java -jar ~cs2030s/bin/checkstyle.jar -c ~cs2030s/bin/cs2030_checks.xml *.java
```

## Following CS2030S Style Guide

You should make sure that your code follows the [given Java style guide](https://nus-cs2030s.github.io/2021-s2/style.html) and the [give Java documentation guide](https://nus-cs2030s.github.io/2021-s2/javadoc.html).

## Grading

This lab is worth 12 marks and contributes 4% to your final grade.

We will deduct 1 mark for each unnecessary use of `@SuppressWarnings` and each raw type.  `@SuppressWarnings` should be used appropriately and not abused to remove compilation warnings.

Note that style marks are no longer awarded.  You should know how to follow the prescribed Java style by now.  We will deduct up to 2 marks if there are serious violations of styles.


## WARNING ❗️

We would like to remind you of the following:

- Use only the `submit-labX` script to submit your lab.  Failure to do so will lead to a 50% penalty on your lab grade.
- The grace period for getting used to the submission system is over.  We will not waive the late penalty if students fail to submit properly.  Please check your repo after running `submit-labX` to ensure that your files have been added correctly. The URL to your repo is given after you run `submit-labX`.
