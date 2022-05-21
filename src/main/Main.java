package main;

import classes.Player;
import java.util.*;

public class Main {
	public static void main(String[] args){
		System.out.println("Lets Bowling Simulator!");
		System.out.println("write rules");
		String name = new Scanner(System.in).nextLine();

		Player player = new Player(name);
		Player npc = new Player();

		System.out.println(player.getName() + "'s ScoreBoard");
		player.board.displayBoard();
		System.out.println();
		System.out.println(npc.getName() + "'s ScoreBoard");
		npc.board.displayBoard();

		System.out.println("Lets Start!!!");
		for(int i = 1; i < 10; i ++){
			for(int j = 1; j < 3; j ++){
				System.out.printf("%s's %d - %d Throwing!", player.getName(), i, j);

				String bowl;
				while(true){
					System.out.println("Which bowl do you use?");
					System.out.println("1. Heavy Bowl   2. Light Bowl");
					int input = new Scanner(System.in).nextInt();
					if(input == 1){
						bowl = "heavy";
						break;
					}else if(input == 2){
						bowl = "light";
						break;
					}else{
						System.out.println("Invalid Input : " + input);
					}
				}

				String direction;
				while(true){
					System.out.println("Where do you throw the bowl?");
					System.out.println("1. Left   2. Center   3.Right");
					int input = new Scanner(System.in).nextInt();
					if(input == 1){
						direction = "left";
						break;
					}else if(input == 2){
						direction = "left";
						break;
					}else if(input == 3){
						direction = "left";
						break;
					}else{
						System.out.println("Invalid Input : " + input);
					}
				}

				player.throwBowl(bowl, direction, i, j);
				player.pines.displayPines();
				player.board.arrangeScore(i, j);
				System.out.println();
				player.board.displayBoard();
			}
			player.pines.resetPines();
			player.board.calculateFrameScore(player.board.score0109[i]);
		}

		//2回FORループ
		//NPCのーフレームの〜当目
		//投げる
		//スコア計算
		//ピンを表示
		//ボードを表示

		//10当目
				//3回FORループ
						//プレイヤーのーフレームの〜当目
						//ボールを選択
						//技を選択
						//投げる
						//スコア計算
						//ピンを表示
						//ボードを表示
				//3回FORループ
						//プレイヤーのーフレームの〜当目
						//投げる
						//スコア計算
						//ピンを表示
						//ボードを表示
	}
}


