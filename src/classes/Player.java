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

		int knocked = calculateKnocked(bowl);
		board.setScore(frame, throwing, knocked);

		Random random = new Random();
		int firstKnocked =  random.nextInt(1, knocked);
		if(firstKnocked == 0){
			firstKnocked = 1;
		}
		int secondKnocked = random.nextInt(0, firstKnocked);
		if(secondKnocked == 0){
			secondKnocked = 1;
		}
		int thirdKnocked = random.nextInt(0, secondKnocked);
		switch(direction){
			case "right" -> {
				int extra1 = subtractPines(firstKnocked, "right", 0);
				int extra2 = subtractPines(firstKnocked, "center", extra1);
				int extra3 = subtractPines(firstKnocked, "left", extra2);
			}
			case "left" -> {
				int extra1 = subtractPines(firstKnocked, "left", 0);
				int extra2 = subtractPines(firstKnocked, "center", extra1);
				int extra3 = subtractPines(firstKnocked, "right", extra2);
			}
			case "center" -> {
				int extra1 = subtractPines(firstKnocked, "center", 0);
				boolean isLeft = random.nextBoolean();
				if(isLeft){
					int extra2 = subtractPines(firstKnocked, "left", extra1);
					int extra3 = subtractPines(firstKnocked, "right", extra2);
				}else{
					int extra2 = subtractPines(firstKnocked, "right", extra1);
					int extra3 = subtractPines(firstKnocked, "left", extra2);
				}
			}
		}

	}

	public int subtractPines(int knocked, String direction, int extra) {
		int takeover = extra;
		switch (direction) {
			case "left" -> {
				int n = pines.getLeftPinesNumber();
				if (n >= knocked) {
					for (int i = 0; i < knocked; i++) {
						pines.setLeftPine(i);
					}
				} else {
					takeover = knocked - n;
					for (int i = 0; i < n; i++) {
						pines.setLeftPine(i);
					}
				}
			}
			case "center" -> {
				int n = pines.getCenterPinesNumber();
				if (n >= knocked) {
					for (int i = 0; i < knocked; i++) {
						pines.setCenterPine(i);
					}
				} else {
					takeover = knocked - n;
					for (int i = 0; i < n; i++) {
						pines.setCenterPine(i);
					}
				}
			}
			case "right" -> {
				int n = pines.getRightPinesNumber();
				if (n >= knocked) {
					for (int i = 0; i < knocked; i++) {
						pines.setRightPine(i);
					}
				} else {
					takeover = knocked - n;
					for (int i = 0; i < n; i++) {
						pines.setRightPine(i);
					}
				}
			}
		}
		return takeover;
	}

	public int calculateKnocked(String bowl){
		int score;
		int knocked = knockPines(bowl);
		int pinesNumber = pines.getPinesNumber();
		if(knocked > pinesNumber){
			score = knocked;
		}else{
			score = pinesNumber;
		}
		return score;
	}

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
		int result = random.nextInt(0, 7) + random.nextInt(0, 7) - 2;
		return result;
	}


}
