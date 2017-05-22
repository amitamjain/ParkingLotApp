# ParkingLotApp
Automated Parking Lot Application

# Downloads 
Uses Latest JDK - 1.8 
Uses Maven 3.5.0

#To run executable in interactive mode in windows
java -jar target/my-app-1.0.jar

#To run executable from file in windows 
java -jar target/my-app-1.0.jar file_inputs.txt

#To run executable in interactive mode in linux
$ ./parking_lot

#To run executable from file in linux
$ ./parking_lot file_inputs.txt

#Parking Strategy
Uses Closest Available Parking Strategy 
This can be changed by implementing a different strategy

#command.properties
Storehouse of commands. All available commands needs to be listed here. 
Government regulations may change and there may be need to remove some commands. 
pattern -> commandname=methodTobeExecuted ParamClasses 
Example - >create_parking_lot=createSlot,java.lang.String

#message.properties
Used to retrieve messages. 
Can change message text.  
To be used with logger when introduced. 






 






