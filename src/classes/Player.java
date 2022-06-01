package classes;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Player {
	private final String name;
	public ScoreBoard board = new ScoreBoard();  //ここのアクセス制限をなるべく厳しくしたいが、オブジェクト指向も維持したい
	public Pines pines = new Pines();

	public Player(String name) {
		this.name = name;
	}

	public Player() {
		this("NPC");
	}

	public String getName() {
		return this.name;
	}

	public void throwBowl(String bowl, String direction, int frame, int throwing) {
		System.out.println(this.name + "の投球！");
		int knocked = 0;

		int tempKnocked = knockPinesTemp(bowl);
		//System.out.printf("Debug ： Knocked1 is %d\n", + tempKnocked);

		Random random = new Random(); //ここのランダム性を無くせばもっとピンが倒れるようになる。
		int firstKnocked = random.nextInt(0, tempKnocked + 1);
		//System.out.printf("Debug ： firstKnocked is %d\n", + firstKnocked);

		int secondKnocked = random.nextInt(0, firstKnocked + 1);
		//System.out.printf("Debug ： secondKnocked is %d\n", + secondKnocked);

		int thirdKnocked = random.nextInt(0, secondKnocked + 1);
		//System.out.printf("Debug ： thirdKnocked is %d\n", + thirdKnocked);

		switch (direction) { //ここの分岐をリファクタリングでコンパクトにできないか・・・？
			case "right" -> {
				int takeover1 = knockPines(firstKnocked, "right", 0);
				int takeover2 = knockPines(secondKnocked, "center", takeover1);
				knocked = knockPines(thirdKnocked, "left", takeover2);
			}
			case "left" -> {
				int takeover1 = knockPines(firstKnocked, "left", 0);
				int takeover2 = knockPines(secondKnocked, "center", takeover1);
				knocked = knockPines(thirdKnocked, "right", takeover2);
			}
			case "center" -> {
				int takeover1 = knockPines(firstKnocked, "center", 0);
				boolean isLeft = random.nextBoolean();
				if (isLeft) {
					int takeover2 = knockPines(secondKnocked, "left", takeover1);
					knocked = knockPines(thirdKnocked, "right", takeover2);
				} else {
					int takeover2 = knockPines(secondKnocked, "right", takeover1);
					knocked = knockPines(thirdKnocked, "left", takeover2);
				}
			}
		}
		board.setScore(frame, throwing, knocked);
		//System.out.printf("Debug : Knocked2 is %d\n", + knock);
	}

	public int knockPines(int knocked, String direction, int takeover) {
		int n = pines.getPinesNumber(direction);
		int limit;
		if (n >= knocked) {  //knockPines()で指定した数よりも残っているピンが多い時
			limit = knocked;
		} else {
			limit = n;
		}
		for (int i = 0; i < limit; i++) {
			takeover += pines.knockPine(direction, i);
		}
		return takeover;
	}

	public int knockPinesTemp(String bowl) {
		int tempKnocked = 0;
		switch (bowl) {
			case "light" -> tempKnocked = nextPseudoGauss();
			case "heavy" -> {
				Random random = new Random();
				tempKnocked = random.nextInt(0, 11);
			}
		}
		if (tempKnocked == 0) {
			tempKnocked = 1;
		}
		return tempKnocked;
	}

	public int nextPseudoGauss() { //軽いボールを選んだ時のピンの倒す本数の確率変数を生成する.正規分布みたいな確率分布。
		Random random = new Random();
		return random.nextInt(1, 7) + random.nextInt(1, 7) - 2;
	}

	public void displayBoard() {
		System.out.println(this.getName() + "のスコアボード");
		this.board.displayBoard();
		System.out.println();
	}
}