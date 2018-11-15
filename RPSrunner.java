package RockPaperScissors_Arrays;

import java.util.Scanner;
import java.util.ArrayList;

public class RPSrunner {
	
	public static void main(String[] args) {
		RPSclass game = new RPSclass();
		//create lists to hold each players' moves
		ArrayList<Integer> player = new ArrayList<Integer>();
		ArrayList<Integer> computer = new ArrayList<Integer>();
		
		//create scanner in console
		Scanner input = new Scanner(System.in);
		
		//save name of user
		String name = game.getReady(input);
		
		//create array to track [user wins, computer wins, ties]
		int[] score = new int[3];
		for(int i=0; i<3; i++) score[i] = 0;
		
		boolean willPlay = true;
		int round = 1;
		boolean makeMove = true;
		
		//main loop for each turn
		while(willPlay) {
			if(makeMove) {
				//initiate another round
				computer = game.makeCMove(player, computer, round);
				System.out.println("\nRock, Paper, Scissors, SHOOT!");
			}
			if(input.hasNext()) {
				//take in user command
				String move = input.next();
				
				if(move.charAt(0) != 'E') {
					//get user's move and display round results
					player = game.addMove(move, player);
					score = game.findWinner(score, player, computer);
					game.displayMovesAndScore(round, name,score,player,computer);
					round++;
					makeMove = true;
				} else {
					//break while loop & end game
					willPlay = false;
				}	
			} else {
				//user just pressed enter...prompt them to make a move
				System.out.println("Please make your move...");
				makeMove = false;
			}
		}
		input.close();
		System.out.println("Thanks for playing!");
	}
}
