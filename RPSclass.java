package RockPaperScissors_Arrays;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class RPSclass {

	public String getReady(Scanner input){
		System.out.println("Welcome to Rock, Paper Scissors! Whats your name?");
		String name = input.nextLine();
		System.out.println("Hi " + name + ", here are the rules:");
		System.out.println("When prompted, type in \"Rock,\" \"Paper,\" or \"Scissors.\"\nThe game will continue until you type \"End Game.\"");
		return name;
	}
	
	public ArrayList<Integer> addMove(String pMove, ArrayList<Integer> player) {
		pMove =  pMove.toLowerCase();
		int play;
		char playerMove = pMove.charAt(0);
		if(playerMove=='r') play = 0;
		else if(playerMove=='p') play = 1;
		else play = 2;
		player.add(play);
		return player;
	}
	
	public ArrayList<Integer> makeCMove(ArrayList<Integer> player, ArrayList<Integer> computer, int round) {
		int next = -1;
		if(round>6){
			ArrayList<Integer> played = new ArrayList<Integer>();
			for(int group = 4; group>0; group--){
				int[] lp = new int[group];
				for(int i=0; i<group; i++){
					lp[i] = player.get(player.size()-group+i);
				}
				for(int i=0; i<player.size()-group-1; i++){
					int[] last = new int[group];
					for(int j=0; j<group; j++){
						last[j] = player.get(i+j);
					}
					if(Arrays.equals(last,lp)) played.add(player.get(i+group));
				}
				if(played.size()>0) break;
			}
			int z = 0, o = 0, t = 0;
			for(int i = 0; i<played.size(); i++){
				int tp = played.get(i);
				if(tp == 0) z++;
				else if(tp==1) o++;
				else t++;
			}
			if(z>o && z>t) next = 1;
			else if(o>z && o>t) next = 2;
			else if(t>z && t>o) next = 0;
			else if(z==t && z==o) next = (int)(Math.random()*2);//any
			else if(z==t){
				//either 0 or 2
				next = (int)(Math.random());
				if(next>=1) next = 2;
			}
			else if(z==o) next = (int)(Math.random());//either 0 or 1
			else next = (int)(1 + Math.random());//either 1 or 2
		} else next = (int)(Math.random()*2);
		
		computer.add(next);
		return computer;
	}
	
	public int[] findWinner(int[] score, ArrayList<Integer> player, ArrayList<Integer> computer){
		int pLast = player.get(player.size()-1);
		int cLast = computer.get(computer.size()-1);
		if(pLast==cLast) score[1] += 1;
		else if((pLast==2 && cLast==1) || (pLast==1 && cLast==0) || (pLast==0 && cLast==2)) score[2] += 1;
		else score[0] += 1;
		return score;
	}

	public void displayMovesAndScore(int round, String name, int[] score, ArrayList<Integer> player, ArrayList<Integer> computer) {
		int c = computer.get(computer.size()-1);
		int p = player.get(player.size()-1);
		String cMove=null, pMove=null;
		if(c==0) cMove = "Rock";
		else if(c==1) cMove = "Paper";
		else cMove = "Scissors";
		if(p==0) pMove = "Rock";
		else if(p==1) pMove = "Paper";
		else pMove = "Scissors";
		System.out.println("\nRound " + round + ":");
		System.out.println("Computer's Move: " + cMove);
		System.out.println(name + "'s Move: " + pMove);
		System.out.println("Score...");
		System.out.println(name + ": " + score[2] + ", Computer: " + score[0] + ", Tie: " + score[1]);
	}

}