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
			player.oneFrameAction(i, false);
			npc.oneFrameAction(i, true);
			player.pines.resetPines();
			npc.pines.resetPines();
		}

		player.finalFrameAction(false);
		npc.finalFrameAction(true);

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
}