package main;

import classes.Player;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("ボーリングシュミレーター!");
		System.out.println("ボールの組み合わせと投げる方向を調整してNPCより高いスコアを出そう！");
		System.out.println("重いボールは倒せるボールの数も多いがコントロールが難しいぞ！");
		System.out.println("軽いボールはコントロールしやすいが、あまりピンは倒れないぞ!");
		System.out.println("後は左・真ん中・右と投げる方向を決めよう！");
		System.out.println();
		System.out.println("名前を入力してください：");
		String name = new Scanner(System.in).nextLine();

		Player player = new Player(name);
		Player npc = new Player();

		player.displayBoard();
		npc.displayBoard();

		System.out.println("ゲーム開始!!!");
		for (int i = 0; i < 9; i++) {
			oneFrameAction(player, i, false);
			oneFrameAction(npc, i, true);
			player.pines.resetPines();
			npc.pines.resetPines();
		}

		finalFrameAction(player, false);
		finalFrameAction(npc, true);

		System.out.println("試合終了！");
		System.out.println();
		player.displayBoard();
		npc.displayBoard();
		int playerScore = player.board.getFrameScore(9);
		int npcScore = npc.board.getFrameScore(9);
		if (playerScore > npcScore) {
			System.out.println(player.getName() + "の勝利！おめでとう！");
		} else if (playerScore < npcScore) {
			System.out.println(npc.getName() + "の勝利！残念・・・！");
		} else {
			System.out.println("引き分け！");
		}
	}

	public static void oneThrowingAction(Player player, int frame, int throwing, boolean auto) {
		System.out.println();
		System.out.printf("%sの %d - %d 目の投球!\n", player.getName(), frame + 1, throwing + 1);
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
		player.throwBowl(bowl, direction, frame, throwing);
		player.pines.displayPines();
		if (auto) {
			System.out.println();
			System.out.println("Enter : 次へ");
			String anyline = input.nextLine();
		}
		System.out.println();
		player.board.arrangeBoard(frame, throwing);
		switch (throwing) {
			case 1 -> { //iフレームの２投球目の点数処理
				if (frame != 9) {
					player.board.setFrameScore(frame);
				}
			}
			case 2 -> player.board.setFrameScore(frame);
		}
		player.displayBoard();
		if (auto) {
			System.out.println();
			System.out.println("Enter : 次へ");
			String anyLine = input.nextLine();
		}
	}

	public static void oneFrameAction(Player player,int frame, boolean auto){
		for (int throwing = 0; throwing < 2; throwing++) {
			if (player.board.getScore(frame, 0) == "X") {
				player.board.setScore2(frame, throwing, "-");
				player.board.setFrameScore(frame);
				continue;
			}
			oneThrowingAction(player, frame, throwing, auto);
		}
	}

	public static void finalFrameAction(Player player, boolean auto){
		for (int throwing = 0; throwing < 3; throwing++) {
			if (throwing == 2 && player.board.getScore(9, 0) != "X") {
				player.board.setFrameScore(9);
				break;
			} else if (throwing == 2 && player.board.getScore(9, 1) != "/") {
				player.board.setFrameScore(9);
				break;
			}
			oneThrowingAction(player, 9, throwing, auto);
		}
	}

}