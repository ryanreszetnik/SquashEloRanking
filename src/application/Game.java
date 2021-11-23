package application;

public class Game {
	int player1Score;
	int player2Score;
	public static int Kfactor = 40;
	public static final double number = 400;
	String p1Name;
	String p2Name;
	String winner;
	String date;
	Player p1;
	Player p2;

	public Game(Player p1n, Player p2n, String date, int p1score, int p2score){
		p1Name = p1n.name;
		p2Name = p2n.name;
		p1 = p1n;
		p2= p2n;
		this.date = date;
		gameOver(p1score,p2score);
	}
	
	public void gameOver(int p1Score, int p2Score){
		player1Score=p1Score;
		player2Score=p2Score;
		if(p1Score==p2Score){
			winner = "Tie";
		}
		else if(p1Score>p2Score){
			winner = p1Name;
		}else{
			winner = p2Name;
		}
		updateElos(p1,p2);
	}
	public String predicted(){
		return p1Name +": "+getPercent(p1,p2)+"--"+p2Name +": "+getPercent(p2,p1);
	}
	
	
	public boolean isWinner(String player) {
		if (winner == player) {
			return true;
		}
		return false;
	}
	public void updateElos(Player p1, Player p2){
		double R1 = calculatePercent(p1,p2);
		double R2 = calculatePercent(p2,p1);
		//System.out.println(getPercent(p1,p2)+getPercent(p2,p1));
		double S1 = 0;
		double S2 = 0;
		if(isWinner("Tie")){
			S1 = 0.5;
			S2 = 0.5;
		}
		else if(isWinner(p1.name)){
			S1 =1;
			S2 = 0;
		}else{
			S1=0;
			S2=1;
		}
		double E1 = R1/(R1+R2);
		double E2 = R2/(R1+R2);
		//double Probabilityqq =	1 / (1 + 10 ^ ((p2.getElo() – p1.getElo()) / 400);
		//System.out.println((int)(Math.round(p1.getElo()+(Kfactor*(S1-R1)))));
		p1.setElo((int)(Math.round(p1.getElo()+(Kfactor*(S1-R1)))));
		p2.setElo((int)(Math.round(p2.getElo()+(Kfactor*(S2-R2)))));
	}
	public String getPercent(Player you, Player oponent) {
		return Math.round(calculatePercent(you,oponent)*10000)/100+"%";
	}
	public double calculatePercent(Player you, Player oponent){
		double ratingDiff = (oponent.getElo()-you.getElo())/number;
		double percent = 1.0/(1.0+Math.pow(10.0, ratingDiff));
		return percent;
	}
	
}
