package com.example.demo;

import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController2_forHtmlResponse {

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
	static String DisplayPlayers(String[] players){
		String x = "<p style=\"text-align:center\">";
		for(int j=0; j<players.length; j++)
			x += players[j] + " | ";
		x += "<br>";
		return x;
	}
	
		
//	method to display choices for current game
	static String DisplayChoices(String[] choices, String[] players) {
		String x = "<table border=1; align=center>"
						+ "<tr>";
		
							for (int i=0; i<players.length; i++) 
								x += "<th>" + players[i] + "</th>";
				
					x += "</tr>";
					x += "<tr>";
							for(int i=0; i<choices.length; i++)
								x += "<td>" + choices[i] + "</td>";
			x += "</table><br>";
			
		return x;
	}
	
	
//	method to display 2D array of winnings against each player
	static String DisplayAgainst(int[][] against, String[] players) {
		String x = "<table border=1; align=center>"
						+ "<tr>"
						+ "<th></th>";
						
						for (int i=0; i<players.length; i++) 
							x += "<th>" + players[i] + "</th>";
		
					x += "</tr>";
					
					for(int i=0; i<against.length; i++) {
						x += "<tr>"
								+ "<th>" + players[i] + "</th>";
						for(int j=0; j<against.length; j++)
							x += "<td>" + against[i][j] + "</td>";
						
						x += "</tr>";
					}
			x += "</table><br>";
		return x;
	}

	@RequestMapping("/start-game")
	@ResponseBody
	String startGame() {
		
		String result = "<h1 style=\"color:blue;text-align:center\">Rock-Paper-Scissor Game</h1>";
		
		int n = 4;		//Assigning number of players
		
		Game g = new Game(n);

//		Assigning each player name.
		SetPlayers(g);
		

		for(int i=0; i<50; i++){		//Loop for each round of game.

			result += "<h4 style=\"text-align:center\">Round " + (i+1) + "</h4>";

			String[] currGame = new String[n];		//Array for maintaining respective player choice for each iteration in game.

//			Set player choice for current round of game.
			for(int j=0; j<n; j++)
				currGame[j] = getRandom(g.list);

			result += DisplayChoices(currGame, g.getPlayers());
			
			
//			Code to check winners of current round and update total-wins and wins-against-each-player.
			for(int j=0; j<n-1; j++){
				for(int k=j+1; k<n; k++){
					if(currGame[j]!=currGame[k]){
						int x = whoWins(currGame[j], currGame[k]);
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
			result += DisplayAgainst(g.getAgainst(), g.getPlayers());
			result += "<br>";

		}

//		Displaying total winnings of each player -> Result
		result += "<h2 style=\"text-align:center\">Results</h2>";
		result += DisplayPlayers(g.getPlayers());
		result += "<p style=\"text-align:center\">";
		for(int i=0; i<4; i++)
			result += g.getTotalWins()[i] + " | ";
		
		return result;

	}
}