# Rock Paper Scissor Game	
Rock-Paper-Scissor is a very commonaly played game. Here, in this project, it is implemented using Java and Spring Boot.	
The objective is to create a game of 50 rounds, played by 4 players viz; Player 1, Player 2, Player 3, Player 4.	
On each round, choices of each player should be chosen at random and printed. Along with that, winnings of each player against every other player, up till that round, should also be printed.	

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
   - Compare choices of each player to calculate who won from whom. For comparison, every index is compared with indexes ahead of it, starting from 0 index.	
   - If 2 players have same choice, none of them gets point. So no operation is performed.	
   - Else, check if second player's choice beats first player's choice. If yes, increment second player's win against first player. If no, increment first player's win against second player.	
In both cases, also increment respective winning player's cumulative win.	
   - Upon completion of comparison, print each player's winning against every other.	
4. Upon completion of all rounds, print cumulative winnings of each player.