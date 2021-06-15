# 2048 with AI
## Some background
This project is made with Eclipse version 2018-12, but can be executed on any machine with JDK 8.

## How to run the program.
RunMe.java is the file which contains the main method and can be executed in the same way you execute any other java code.

## To see every move.
In the main method of RunMe.java two lines

```java
game.printState(state, move);	
TimeUnit.MILLISECONDS.sleep(300);
```

By default every move is shown, but can be changed to only show winning boards by commenting them out.


```java
while(winCounter != 100)
```
