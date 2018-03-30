User
--
-username: String <br/>
-password: String <br/>
-tripList: ArayList&lt;Booking&gt;
--
+User(String, String) <br/>
+getUsername(): String <br/>
+setUsername(String): void <br/>
+getPassword(): String <br/>
+setPassword(String): void <br/>
-generatePasswordHash(String): String <br/>
+validatePassword(String): boolean <br/>
+addTrip(Booking): void <br/>
+removeTrip(Flight): boolean <br/>
+findTrip(Flight): Booking <br/>
+getTripList(): ArrayList&lt;Booking&gt; <br/>
+toString(): String <br/>
