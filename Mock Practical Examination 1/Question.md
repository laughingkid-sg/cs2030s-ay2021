b 5 (Mock PE1): Snatch A Ride
  2
  3 Snatch Pte Ltd is a transport service provider trying to vie for a place in the public transport arena.  Snatch provides three      types of ride services:
  4
  5 `JustRide`: `JustRide` charges a fare based on the distance traveled, at 22 cents per km, and the fare is the same regardless       of the number of passengers.
  6 There is a surcharge of 500 cents if a ride request is issued between 0600 hours and 0900 hours, both inclusive.
  7
  8 `TakeACab`: `TakeACab` charges its fare based on distance traveled, at 33 cents per km, but there is a booking fee of 200           cents.  The fair is the same regardless of the number of passengers.  There is no peak hour surcharge.
  9
 10 `ShareARide`: The fare depends on the number of passengers and is calculated as follows: the base fare is 50 cents per km, but      the passengers pay less if they share the ride.  The paid fare is the base fare divided by the number of passengers with any        fractional part of the fare (after division) is absorbed by the driver.  There is a surcharge of 500 cents if a ride request is     issued between 0600 hours and 0900 hours, both inclusive.
 11
 12 In addition, there are two types of cars under Snatch.  A `Cab` can provide only `JustRide` and `TakeACab` services.  A             `PrivateCar` can provide only `JustRide` and `ShareARide` services.
 13
 14 A customer can issue a Snatch ride request, specified by the distance of the ride, the number of passengers, and the time of        the request.  A booking is made when a request is matched with a car under a particular ride service.
 15
 16 To get full marks, your code not only needs to be correct (including passing all the test cases) but its design must be             extensible.  In case, Snatch decides to provide additional types of ride services, support additional types of cars, or change      the fare structure, your code should require minimal changes to support the new requirements.
 17
 18 ## Task
 19
 20 ### Request
 21
 22 Implement a `Request` class that encapsulates a request for a ride.  The constructor for `Request` takes in three `int`             parameters, the distance of the ride, the number of passengers, and the time of the request.  For instance,
 23
 24 ### Services
 25
 26 2. Implement the three classes `JustRide`, `TakeACab`, and `ShareARide`.  These classes should implement a `computeFare` method     that takes in a `Request` instance as a parameter and returns the fare in cents.
 27
 28 ```
 29 jshell> new JustRide().computeFare(new Request(20, 3, 1000))
 30 $.. ==> 440
 31 jshell> new JustRide().computeFare(new Request(10, 1, 900))
 32 $.. ==> 720
 33 jshell> new TakeACab().computeFare(new Request(20, 3, 1000))
 34 $.. ==> 860
 35 jshell> new TakeACab().computeFare(new Request(10, 1, 900))
 36 $.. ==> 530
 37 jshell> new ShareARide().computeFare(new Request(20, 3, 1000))
 38 $.. ==> 333
 39 jshell> new ShareARide().computeFare(new Request(10, 1, 900))
 40 $.. ==> 1000
 41 ```
 42
 43 In addition, each class should override `toString` to return the name of the service.
 44
 45 ```
 46 jshell> new JustRide().toString()
 47 $.. ==> "JustRide"
 48 jshell> new TakeACab().toString()
 49 $.. ==> "TakeACab"
 50 jshell> new ShareARide().toString()
 51 $.. ==> "ShareARide"
 52 ```
 53
 54 You can test your code by running the `Test1.java` provided.  Make sure your code follows the CS2030S Java style.
 55
 56 ```
 57 $ javac Test1.java
 58 $ java Test1
 59 $ java -jar ~cs2030s/bin/checkstyle.jar -c ~cs2030s/bin/cs2030_checks.xml *.java
 60 ```
 61
 62 ### Cars
 63
 64 Implement two classes `Cab` and `PrivateCar`.  Their constructors should take in a `String` instance that corresponds to the        license plate and the time (in minutes) until the driver is available.  In addition, each class should override `toString` to       return the type of car, the license plate, and the time until the driver is available.  The string should be formatted as shown     in the examples below.
 65
 66 ```
 67 jshell> new Cab("SHA1234", 5).toString()
 68 $.. ==> "Cab SHA1234 (5 mins away)"
 69 jshell> new Cab("SHA1234", 1).toString()
 70 $.. ==> "Cab SHA1234 (1 min away)"
 71 jshell> new PrivateCar("SU4032", 4).toString()
 72 $.. ==> "PrivateCar SU4032 (4 mins away)"
 73 jshell> new PrivateCar("SU4032", 1).toString()
 74 $.. ==> "PrivateCar SU4032 (1 min away)"
 75 ```
 76
 77 You can test your code by running the `Test2.java` provided.  Make sure your code follows the CS2030S Java style.
 78
 79 ```
 80 $ javac Test2.java
 81 $ java Test2
 82 $ java -jar ~cs2030s/bin/checkstyle.jar -c ~cs2030s/bin/cs2030_checks.xml *.java
 83 ```
 84
 85 ### Bookings
 86
 87 Implement a class `Booking` that encapsulates a car, a service, and a request.  A booking should implement the                      `Comparable<Booking>` interface.  A booking is compared to another booking based on the fare, breaking ties by the waiting          time.  If two bookings have the same fare and waiting time, you can break ties arbitrarily.
 88
 89 ```
 90 jshell> Comparable<Booking> b = new Booking(new Cab("SHA1234", 5), new JustRide(), new Request(20, 3, 1000));
 91 jshell> Booking b1 = new Booking(new Cab("SHA1234", 3), new JustRide(), new Request(20, 3, 1000));
 92 jshell> Booking b2 = new Booking(new Cab("SBC8888", 5), new JustRide(), new Request(20, 3, 1000));
 93 jshell> Booking b3 = new Booking(new PrivateCar("SU4032", 5), new ShareARide(), new Request(20, 3, 1000));
 94 jshell> b3.compareTo(b2) < 0
 95 $.. ==> true
 96 jshell> b1.compareTo(b3) < 0
 97 $.. ==> false
 98 jshell> b1.compareTo(b2) < 0
 99 $.. ==> true
100 ```
101
102 If a booking is created with a car and a service that is not compatible (i.e., the type of car does not        provide the given service), throw an `IllegalArgumentException`.  Construct an `IllegalArgumentException`      instance by passing in a message (of type `String`) into its constructor. This message can be retrieved by     the `getMessage()` method.
103
104 ```
105 jshell> try {
106    ...>   new Booking(new Cab("SHA1234", 5), new ShareARide(), new Request(20, 3, 1000));
107    ...> } catch (IllegalArgumentException e) {
108    ...>   System.out.println(e.getMessage());
109    ...> }
110 Cab SHA1234 (5 mins away) does not provide the ShareARide service.
111 ```
112
113 You can test your code by running the `Test3.java` provided.  Make sure your code follows the CS2030S Java     style.
114
115 ```
116 $ javac Test3.java
117 $ java Test3
118 $ java -jar ~cs2030s/bin/checkstyle.jar -c ~cs2030s/bin/cs2030_checks.xml *.java
119 ```
