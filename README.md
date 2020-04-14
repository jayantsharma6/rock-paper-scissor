# Rock Paper Scissor Game	

Rock-Paper-Scissor is a very commonaly played game. Here, in this project, it is implemented using Java and Spring Boot.	
The objective is to create a game of 50 rounds, played by 4 players viz; Player 1, Player 2, Player 3, Player 4.	
On each round, choices of each player should be chosen at random and printed. Along with that, winnings of each player against every other player,
up till that round, should also be printed.	

## Data Storing Requirements	

- List of String - 'list' - for Rock, Paper, Scissor	
-  An Integer - 'noOfPlayers' - for Number of Players	
- Array of Strings - 'players[]' - for Player Names	
- 2D Array/ Matrix of Integer - 'against[][]' - for maintaining Winning against each player	
- Array of Integers - totalWins[] - for maintaining cumulative winnings of each player.	
- Array of Strings - 'currRound[]' - for Storing each Player's chosen option, for every round.	
> Size of arrays will be based on noOfPlayers.	

## Approach	
1. Set number of Players	
2. Set player names	
3. for every round, repeat - 	
   - Get every player's choice of option for current round.	
   - Print player wise choices.	
   - Compare choices of each player to calculate who won from whom. For comparison, every index is compared with indexes ahead of it, starting
   from 0 index.	
	 - If 2 players have same choice, none of them gets point. So no operation is performed.	
     - Else, check if second player's choice beats first player's choice. If yes, increment second player's win against first player. If no,
	 increment first player's win against second player. In both cases, also increment respective winning player's cumulative win.	
   - Upon completion of comparison, print each player's winning against every other.	
4. Upon completion of all rounds, print cumulative winnings of each player.


## Project Files
```
[Game](Game.java)
[GameController_forConsoleResponse](GameController_forConsoleResponse.java)
[GameController2_forHtmlResponse](GameController2_forHtmlResponse.java)
```


### Output is generated in two manners
```
Output at Console itself - [GameController_forConsoleResponse](GameController_forConsoleResponse.java)
Output at HTML Page (secondary, commented controller) - [GameController2_forHtmlResponse](GameController2_forHtmlResponse.java)
```


### [1. Game Controller - for Response in Console](GameController_forConsoleResponse.java)

This controller prints output at the console itself using Core Java concepts. Various methods are used to complete required tasks.
Each round displays the current round number, players with their chosen option, and their winnings against every other player.
In the 2nd table for each round, each row represents the respective index player's winning against players mention as column.
For example, for 4 players, 1st row represents 1st Player.
Every table data corresponds to the column value specifies above.

![](/images/console-output1.png)
![](/images/console-output2.png)
![](/images/console-output3.png)


### [2. Game Controller - for Response in HTML page](GameController2_forHtmlResponse.java)

This controller displays the output at a HTML page using in-line HTML tags with Java code. Here as well, various methods are
used to complete required task. The output is well formatted here with tables and better fonts. To display the operations as a HTML page,
the Controller returns a ResponseBody. This response body is generated as String from the program. The first table displays players with their
chosen option, and the second table displays each player's winnings against every other player.

![](/images/html-output1.png)
![](/images/html-output2.png)
![](/images/html-output3.png)


## Thankyou

> PS: Check controller-1 for logic and implementation. Check controller-2 for formatted output.
