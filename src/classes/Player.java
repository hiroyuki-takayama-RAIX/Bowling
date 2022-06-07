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

		int takeover1;
		int takeover2;
		switch (direction) { //ここの分岐をリファクタリングでコンパクトにできないか・・・？
			case "right" -> {
				takeover1 = knockPines(firstKnocked, "right", 0);
				takeover2 = knockPines(secondKnocked, "center", takeover1);
				knocked = knockPines(thirdKnocked, "left", takeover2);
			}
			case "left" -> {
				takeover1 = knockPines(firstKnocked, "left", 0);
				takeover2 = knockPines(secondKnocked, "center", takeover1);
				knocked = knockPines(thirdKnocked, "right", takeover2);
			}
			case "center" -> {
				takeover1 = knockPines(firstKnocked, "center", 0);
				boolean isLeft = random.nextBoolean();
				if (isLeft) {
					takeover2 = knockPines(secondKnocked, "left", takeover1);
					knocked = knockPines(thirdKnocked, "right", takeover2);
				} else {
					takeover2 = knockPines(secondKnocked, "right", takeover1);
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

	public void oneThrowingAction(int frame, int throwing, boolean auto) {
		System.out.println();
		System.out.printf("%sの %d - %d 目の投球!\n", this.getName(), frame + 1, throwing + 1);
		Random autoChoice = new Random(); //NPCはランダムで弾を選び、ボールを投げる
		Scanner input = new Scanner(System.in);
		System.out.println();

		String bowl;
		if (auto) {
			boolean isHeavy = autoChoice.nextBoolean();
			if (isHeavy) {
				bowl = "heavy";
			} else {
				bowl = "light";
			}
		} else {
			while (true) {
				System.out.println("どっちのボールを使う?");
				System.out.println("1. 重いボール  2. 軽いボール");
				String line = new Scanner(System.in).nextLine(); //重いボールと軽いボールを選ぶ
				if (Objects.equals(line, "1")) { //１と２以外の入力があったときは無効な入力として再度入力を求める
					bowl = "heavy";
					break;
				} else if (Objects.equals(line, "2")) {
					bowl = "light";
					break;
				} else {
					System.out.println("無効な入力 : " + line);
				}
			}
			System.out.println();
		}

		String direction = "left";
		if (auto) {
			int randomDirection = autoChoice.nextInt(0, 3);
			if (randomDirection == 0) {
				direction = "left";
			} else if (randomDirection == 1) {
				direction = "center";
			} else if (randomDirection == 2) {
				direction = "right";
			}
			System.out.println("Enter : 次へ");
			String anyLine = input.nextLine();
		} else {
			while (true) {
				System.out.println("どの方向に投げる?");
				System.out.println("1. 左   2. 真ん中   3.右");
				String line = input.nextLine(); //投げる方向を選ぶ
				if (Objects.equals(line, "1")) {
					direction = "left";
					break;
				} else if (Objects.equals(line, "2")) {
					direction = "center";
					break;
				} else if (Objects.equals(line, "3")) {
					direction = "right";
					break;
				} else {
					System.out.println("無効な入力 : " + input);
				}
			}
			System.out.println();
		}

		//System.out.printf("Debug ： Bowl %s, Direction %s\n", bowl, direction); //デバッグ用
		//System.out.println();
		this.throwBowl(bowl, direction, frame, throwing);
		this.pines.displayPines();
		if (auto) {
			System.out.println();
			System.out.println("Enter : 次へ");
			String anyLine = input.nextLine();
		}
		System.out.println();
		this.board.arrangeBoard(frame, throwing);
		switch (throwing) {
			case 1 -> { //iフレームの２投球目の点数処理
				if (frame != 9) {
					this.board.setFrameScore(frame);
				}
			}
			case 2 -> this.board.setFrameScore(frame);
		}
		this.displayBoard();
		if (auto) {
			System.out.println();
			System.out.println("Enter : 次へ");
			String anyLine = input.nextLine();
		}
	}

	public void oneFrameAction(int frame, boolean auto) {
		for (int throwing = 0; throwing < 2; throwing++) {
			if (this.board.getScore(frame, 0) == "X") {
				this.board.setScore2(frame, throwing, "-");
				this.board.setFrameScore(frame);
				continue;
			}
			oneThrowingAction(frame, throwing, auto);
		}
	}

	public void finalFrameAction(boolean auto) {
		for (int throwing = 0; throwing < 3; throwing++) {
			if (throwing == 2 && this.board.getScore(9, 0) != "X") {
				this.board.setFrameScore(9);
				break;
			} else if (throwing == 2 && this.board.getScore(9, 1) != "/") {
				this.board.setFrameScore(9);
				break;
			}
			oneThrowingAction(9, throwing, auto);
		}
	}

}
