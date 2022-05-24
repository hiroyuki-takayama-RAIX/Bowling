package classes;

import java.util.Random;

public class Player {
	private String name;
	public ScoreBoard board = new ScoreBoard();
	public Pines pines =  new Pines();

	public Player(String name){
		this.name = name;
	}

	public Player(){
		this("NPC");
	}

	public String getName(){
		return this.name;
	}

	public void throwBowl(String bowl, String direction, int frame, int throwing){
		System.out.println(this.name + "の投球！");
		int knock = 0;

		int knocked = knockPines(bowl);
		System.out.printf("Knocked1 : %d\n", + knocked);
		if(knocked == 0){
			knocked = 1;
		}

		Random random = new Random();
		int firstKnocked =  random.nextInt(0, knocked);
		System.out.printf("firstKnocked : %d\n", + firstKnocked);
		if(firstKnocked == 0){
			firstKnocked = 1;
		}
		int secondKnocked = random.nextInt(0, firstKnocked);
		System.out.printf("secondKnocked : %d\n", + secondKnocked);
		if(secondKnocked == 0){
			secondKnocked = 1;
		}
		int thirdKnocked = random.nextInt(0, secondKnocked);
		System.out.printf("thirdKnocked : %d\n", + thirdKnocked);
		switch(direction){
			case "right" -> {
				int extra1 = subtractPines(firstKnocked, "right", 0);
				int extra2 = subtractPines(secondKnocked, "center", extra1);
				knock = subtractPines(thirdKnocked, "left", extra2);
			}
			case "left" -> {
				int extra1 = subtractPines(firstKnocked, "left", 0);
				int extra2 = subtractPines(secondKnocked, "center", extra1);
				knock = subtractPines(thirdKnocked, "right", extra2);
			}
			case "center" -> {
				int extra1 = subtractPines(firstKnocked, "center", 0);
				boolean isLeft = random.nextBoolean();
				if(isLeft){
					int extra2 = subtractPines(secondKnocked, "left", extra1);
					knock = subtractPines(thirdKnocked, "right", extra2);
				}else{
					int extra2 = subtractPines(secondKnocked, "right", extra1);
					knock = subtractPines(thirdKnocked, "left", extra2);
				}
			}
		}
		board.setScore(frame, throwing, knock);
		System.out.printf("Knocked2 : %d\n", + knock);
	}

	public int subtractPines(int knocked, String direction, int extra) {
		int knock = extra;
		switch (direction) {
			case "left" -> {
				int n = pines.getLeftPinesNumber();
				if (n >= knocked) { //knockPines()で指定した数よりも残っているピンが多い時
					for (int i = 0; i < knocked; i++) {
						knock += pines.knockLeftPine(i);
					}
				} else { //knockPinesで指定した数よりも残っているピンが少ない時
					for (int i = 0; i < n; i++) {
						knock += pines.knockLeftPine(i);
					}
				}
			}
			case "center" -> {
				int n = pines.getCenterPinesNumber();
				if (n >= knocked) {
					for (int i = 0; i < knocked; i++) {
						knock += pines.knockCenterPine(i);
					}
				} else {
					for (int i = 0; i < n; i++) {
						knock += pines.knockCenterPine(i);
					}
				}
			}
			case "right" -> {
				int n = pines.getRightPinesNumber();
				if (n >= knocked) {
					for (int i = 0; i < knocked; i++) {
						knock += pines.knockRightPine(i);
					}
				} else {
					for (int i = 0; i < n; i++) {
						knock += pines.knockRightPine(i);
					}
				}
			}
		}
		return knock;
	}

	/*public int calculateKnocked(String bowl){
		int score;
		int pinesNumber = pines.getPinesNumber();
		if(knocked > pinesNumber){
			score = knocked;
		}else{
			score = pinesNumber;
		}
		return score;
	}*/

	public int knockPines(String bowl){
		int knocked = 0;
		switch (bowl) {
			case "light" -> knocked = nextPseudoGauss();
			case "heavy" -> {
				Random random = new Random();
				knocked = random.nextInt(0, 11);
			}
		}
		return knocked;
	}

	public int nextPseudoGauss(){
		Random random = new Random();
		int result = random.nextInt(1, 7) + random.nextInt(1, 7) - 2;
		return result;
	}



}
