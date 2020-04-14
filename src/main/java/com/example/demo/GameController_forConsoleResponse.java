package com.example.demo;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController_forConsoleResponse {

//	code to chose random of rock-paper-scissor
	static String getRandom(List<String> list){
		Random r = new Random();
		return list.get(r.nextInt(list.size()));
	}
	

//	code to decide who wins and if draw
	static int whoWins(String first, String second){

		if(first.equals("Rock")){
			if(second.equals("Paper"))
				return 0;
		}

		if(first.equals("Paper")){
			if(second.equals("Scissor"))
				return 0;
		}

		if(first.equals("Scissor")){
			if(second.equals("Rock"))
				return 0;
		}

		return 1;
	}
	
	
//	method to set player names
	static void SetPlayers(Game g) {
		String[] players = new String[g.getPlayers().length];
		for(int i=0; i<g.getPlayers().length; i++)
			players[i] = "Player " + (i+1);
		
		g.setPlayers(players);
	}
	

//	method to display player names
	static void DisplayPlayers(String[] players){
		for(int j=0; j<players.length; j++)
			System.out.print(players[j] + " | ");
		System.out.println();
	}
	
		
//	method to display choices for current game
	static void DisplayChoices(String[] choices, String[] players) {
		DisplayPlayers(players);
		for(int i=0; i<choices.length; i++)
			System.out.print(choices[i] + " | ");
		System.out.println("\n");
	}
	
	
//	method to display 2D array of winnings against each player
	static void DisplayAgainst(int[][] against, String[] players) {
		System.out.print("\t");
		DisplayPlayers(players);
		for(int i=0; i<against.length; i++) {
			System.out.print(players[i] + " | ");
			for(int j=0; j<against.length; j++)
				System.out.print(against[i][j] + " | ");
			System.out.println();
		}
		System.out.println();

	}
	
	
//	method to display the result
	static void DisplayResult(String[] players, int[] totalWins) {
		System.out.println("Result");
		DisplayPlayers(players);
		for(int i=0; i<4; i++)
			System.out.print(totalWins[i] + " | ");
		System.out.println();
	}
	

//	Driving method
	@RequestMapping("/start-game")
	void startGame() {

		int n = 4;		//Assigning number of players
		
		Game g = new Game(n);

//		Assigning each player name.
		SetPlayers(g);
		

		for(int i=0; i<50; i++){		//Loop for each round of game.

			System.out.println("Round " + (i+1) + "\n");

			String[] currRound = new String[n];		//Array for maintaining respective player choice for each iteration in game.

//			Set player choice for current round of game.
			for(int j=0; j<n; j++)
				currRound[j] = getRandom(g.list);

//			Display choice of each player for the round.
			DisplayChoices(currRound, g.getPlayers());
			
			
//			Code to check winners of current round and update total-wins and wins-against-each-player.
			for(int j=0; j<n-1; j++){
				for(int k=j+1; k<n; k++){		//each element in current round array will compare to element ahead in index.
					if(currRound[j]!=currRound[k]){
						int x = whoWins(currRound[j], currRound[k]);
						if(x==1){				//to update winning of j'th player -> updating upper triangular matrix
							g.getTotalWins()[j]++;
							g.getAgainst()[j][k]++;
						}
						else{					//to update winning of k'th player -> updating lower triangular matrix
							g.getTotalWins()[k]++;
							g.getAgainst()[k][j]++;							
						}
					}
				}
			}

//			Displaying winnings against each player till current round.
			DisplayAgainst(g.getAgainst(), g.getPlayers());

		}

//		Displaying total winnings of each player -> Result
		DisplayResult(g.getPlayers(), g.getTotalWins());

	}
}