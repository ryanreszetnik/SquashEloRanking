package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Player {
	public String name;
	private int eloRating;
	public int position;
	public ArrayList<Game> games;
	public static final double number = 400;

	public Player(String names, int startingElo) {
		name = names;
		eloRating = startingElo;
	}

	public int getElo() {
		return eloRating;
	}

	public void setElo(int newElo) {
		eloRating = newElo;
	}

	public void addGame(Game played) {
		games.add(played);
	}

	public static ArrayList<Player> sort(ArrayList<Player> people) {
	
		ArrayList<Integer> elos = new ArrayList<>();
		for(int i = 0; i < people.size(); i++){
			elos.add(people.get(i).eloRating);
		}
		
		
		Collections.sort(people, Comparator.comparingInt(Player ::getElo));
		Collections.reverse(people);
		return people;
	}

	public static void updatePositions(ArrayList<Player> things) {
		ArrayList<Player> newpepes = new ArrayList<>();
		int[] ratings = new int[things.size()];
		for (int i = 0; i < things.size(); i++) {
			ratings[i] = things.get(i).eloRating;
		}
		Arrays.sort(ratings);
		for (int i = 0; i < ratings.length; i++) {
			for (int p = 0; p < things.size(); p++) {
				if (things.get(p).eloRating == ratings[i]) {
					things.get(p).position = i + 1;
					newpepes.add(things.get(p));
					//things.remove(p);
				}
			}
		}
		things = newpepes;
	}
	public static double getPercent(Player you, Player oponent){
		double ratingDiff = (oponent.getElo()-you.getElo())/number;
		double percent = 1.0/(1.0+Math.pow(10.0, ratingDiff));
		return percent;
		
	}
}
