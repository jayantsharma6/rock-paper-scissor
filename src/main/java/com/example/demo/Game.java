package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

	final List<String> list = new ArrayList<String>(Arrays.asList("Rock","Paper","Scissor"));
	//final list because it won't change and is common for all instance of game

	private int noOfPlayers;
	private String[] players;		//Array of player names
	private int[][] against;		//2D Array/Matrix for maintaining each player's winnings-against another player
	private int[] totalWins;		//Array for maintaining total-wins of each player
	
	Game(int num){					//As number of players are given, initializing object attributes using it
		noOfPlayers = num;
		players = new String[noOfPlayers];
		against = new int[noOfPlayers][noOfPlayers];
		totalWins = new int[noOfPlayers];
	}
	
	public String[] getPlayers() {
		return players;
	}
	public void setPlayers(String[] players) {
		this.players = players;
	}
	public int[][] getAgainst() {
		return against;
	}
	public void setAgainst(int[][] against) {
		this.against = against;
	}
	public int[] getTotalWins() {
		return totalWins;
	}
	public void setTotalWins(int[] totalWins) {
		this.totalWins = totalWins;
	}
	public List<String> getList() {
		return list;
	}
	
	
}
