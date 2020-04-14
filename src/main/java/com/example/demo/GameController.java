package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

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
	static String[] SetPlayers(String[] players) {
		for(int i=0; i<players.length; i++)
			players[i] = "Player " + (i+1);

		return players;
	}

//	method to display player names
	static void DisplayPlayers(String[] players){
		System.out.println();
		for(int j=0; j<players.length; j++)
			System.out.print(players[j] + " | ");
		System.out.println();
	}
	
		
//	method to display choices for current game
	static void DisplayChoices(String[] choices, String[] players) {
		DisplayPlayers(players);
		for(int i=0; i<choices.length; i++)
			System.out.print(choices[i] + " | ");
		System.out.println();
	}
	
	
//	method to display 2D array of winnings against each player
	static void DisplayAgainst(int[][] against, String[] players) {
		DisplayPlayers(players);
		for(int i=0; i<against.length; i++) {
			for(int j=0; j<against.length; j++)
				System.out.print(against[i][j] + " | ");
			System.out.println();
		}
		System.out.println();

	}
	
	
//	method to display the result
	static void DisplayResult(String[] players, int[] totalWins) {
		System.out.println("Results");
		DisplayPlayers(players);
		for(int i=0; i<4; i++)
			System.out.print(totalWins[i] + " | ");
	}
	

//	Driving method
	@RequestMapping("/start-game")
	void startGame() {
		
//		Creating list of options.
		List<String> list = new ArrayList<>();
		list.add("Rock");
		list.add("Paper");
		list.add("Scissor");

		int n = 4;		//Assigning number of players

//		Creating array of players and assigning each player name.
		String[] players = new String[n];
		players = SetPlayers(players);
		
		int[] totalWins = new int[n];	//Array for maintaining total wins for each player.

		int[][] Against = new int[4][4];	//2D Array/Matrix for maintaining each player's winning against another.


		for(int i=0; i<50; i++){		//Loop for each round of game.

			System.out.println("Round " + (i+1));

			String[] currGame = new String[n];		//Array for maintaining respective player choice for each iteration in game.

//			Set player choice for current round of game.
			for(int j=0; j<n; j++)
				currGame[j] = getRandom(list);

//			Display choice of each player for the round.
			DisplayChoices(currGame, players);
			
			
//			Code to check winners of current round and update total-wins and wins-against-each-player.
			for(int j=0; j<n-1; j++){
				for(int k=j+1; k<n; k++){
					if(currGame[j]!=currGame[k]){
						int x = whoWins(currGame[j], currGame[k]);
						if(x==1){				//to update winning of j'th player -> updating upper triangular matrix
							totalWins[j]++;
							Against[j][k]++;
						}
						else{					//to update winning of k'th player -> updating lower triangular matrix
							totalWins[k]++;
							Against[k][j]++;							
						}
					}
				}
			}

//			Displaying winnings against each player till current round.
			DisplayAgainst(Against, players);

		}

//		Displaying total winnings of each player -> Result
		DisplayResult(players, totalWins);

	}
}