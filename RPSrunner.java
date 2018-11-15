package RockPaperScissors_Arrays;

import java.util.Scanner;
import java.util.ArrayList;

public class RPSrunner {

	public static void main(String[] args) {
		RPSclass game = new RPSclass();

		ArrayList<Integer> player = new ArrayList<Integer>();
		ArrayList<Integer> computer = new ArrayList<Integer>();
		
		Scanner input = new Scanner(System.in);
		
		String name = game.getReady(input);
		
		int[] score = new int[3];
		for(int i=0; i<3; i++) score[i] = 0;
		
		boolean willPlay = true;
		int round = 1;
		boolean doIt = true;
		while(willPlay){
			if(doIt){
				computer = game.makeCMove(player, computer, round);
				System.out.println("\nRock, Paper, Scissors, SHOOT!");
			}
			if(input.hasNext()){
				String move = input.next();
				if(move.charAt(0)=='E') break;
				player = game.addMove(move, player);
				score = game.findWinner(score, player, computer);
				game.displayMovesAndScore(round, name,score,player,computer);
				round++;
				doIt = true;
			} else{
				System.out.println("Please make your move...");
				doIt = false;
			}
		}
		
		input.close();
		System.out.println("Thanks for playing!");
	}

}