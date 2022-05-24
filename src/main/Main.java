package main;

import classes.Player;
import java.util.*;

public class Main {
	public static void main(String[] args){
		System.out.println("ボーリングシュミレーター!");
		System.out.println("write rules");
		String name = new Scanner(System.in).nextLine();

		Player player = new Player(name);
		Player npc = new Player();

		System.out.println(player.getName() + "のスコアボード");
		player.board.displayBoard();
		System.out.println();
		System.out.println(npc.getName() + "のスコアボード");
		npc.board.displayBoard();

		System.out.println("ゲーム開始!!!");
		for(int i = 0; i < 9; i ++){
			for(int j = 0; j < 2; j ++) {
				System.out.printf("%sの %d - %d 目の投球!\n", player.getName(), i + 1, j + 1);

				String bowl;
				while (true) {
					System.out.println("どっちのボールを作る?");
					System.out.println("1. 重いボール  2. 軽いボール");
					String input = new Scanner(System.in).nextLine();
					if (Objects.equals(input, "1")) { // = だとInvalid Inputになる
						bowl = "heavy";
						break;
					} else if (Objects.equals(input, "2")) {
						bowl = "light";
						break;
					} else {
						System.out.println("無効な入力 : " + input);
					}
				}

				String direction;
				while (true) {
					System.out.println("どの方向に投げる?");
					System.out.println("1. 左   2. 真ん中   3.右");
					String input = new Scanner(System.in).nextLine();
					if (Objects.equals(input, "1")) {
						direction = "left";
						break;
					} else if (Objects.equals(input, "2")) {
						direction = "center";
						break;
					} else if (Objects.equals(input, "3")) {
						direction = "right";
						break;
					} else {
						System.out.println("Invalid Input : " + input);
					}
				}
				System.out.printf("Debug ： Bowl %s, Direction %s\n", bowl, direction);
				player.throwBowl(bowl, direction, i, j);
				player.pines.displayPines();
				player.board.arrangeScore(i, j);
				System.out.println();
				switch (j) {
					case 0 -> {
						System.out.println();
						System.out.println(player.getName() + "のスコアボード");
						player.board.displayBoard();
					}
					case 1 -> {
						player.board.setFrameScore(i);
						player.board.arrangeScore(i, j);
						System.out.println();
						System.out.println(player.getName() + "のスコアボード");
						player.board.displayBoard();
					}
				}
			}

			Random npcsChoice = new Random();
			Scanner anyInput = new Scanner(System.in);
			for(int j = 0; j < 2; j ++){
				System.out.printf("%sの %d - %d 目の投球!\n", npc.getName(), i + 1, j + 1);
				System.out.println("Enter : 次へ");
				String anyline = anyInput.nextLine();

				String bowl;
				boolean choice1 = npcsChoice.nextBoolean();
				if(choice1 == true){ // = だとInvalid Inputになる
					bowl = "heavy";
				}else{
					bowl = "light";
				}

				String direction = "left";
				int choice2 = npcsChoice.nextInt(0, 3);
				if(choice2 == 0){
					direction = "left";
				}else if(choice2 == 1){
					direction = "center";
				}else if(choice2 == 2) {
					direction = "right";
				}

				System.out.printf("Debug ： Bowl %s, Direction %s\n", bowl, direction);
				npc.throwBowl(bowl, direction, i, j);
				npc.pines.displayPines();
				npc.board.arrangeScore(i, j);
				System.out.println();
				switch(j) {
					case 0 -> {
						System.out.println();
						System.out.println(npc.getName() + "のスコアボード");
						npc.board.displayBoard();
					}
					case 1 -> {
						npc.board.setFrameScore(i);
						npc.board.arrangeScore(i, j);
						System.out.println();
						System.out.println(npc.getName() + "のスコアボード");
						npc.board.displayBoard();
					}
				}
				System.out.println();
				System.out.println();
				System.out.println("Enter : 次へ");
				anyline = anyInput.nextLine();
			}
			player.pines.resetPines();
			npc.pines.resetPines();
		}

		//10投球目
		int i = 9;
		for(int j = 0; j < 3; j ++) {
			System.out.printf("%sの %d - %d 目の投球!\n", player.getName(), i + 1, j + 1);

			String bowl;
			while (true) {
				System.out.println("どっちのボールを作る?");
				System.out.println("1. 重いボール  2. 軽いボール");
				String input = new Scanner(System.in).nextLine();
				if (Objects.equals(input, "1")) { // = だとInvalid Inputになる
					bowl = "heavy";
					break;
				} else if (Objects.equals(input, "2")) {
					bowl = "light";
					break;
				} else {
					System.out.println("無効な入力 : " + input);
				}
			}

			String direction;
			while (true) {
				System.out.println("どの方向に投げる?");
				System.out.println("1. 左   2. 真ん中   3.右");
				String input = new Scanner(System.in).nextLine();
				if (Objects.equals(input, "1")) {
					direction = "left";
					break;
				} else if (Objects.equals(input, "2")) {
					direction = "center";
					break;
				} else if (Objects.equals(input, "3")) {
					direction = "right";
					break;
				} else {
					System.out.println("Invalid Input : " + input);
				}
			}
			System.out.printf("Debug ： Bowl %s, Direction %s\n", bowl, direction);
			player.throwBowl(bowl, direction, i, j);
			player.pines.displayPines();
			player.board.arrangeScore(i, j);
			System.out.println();
			switch (j) {
				case 2 -> {
					player.board.setFrameScore(i);
					player.board.arrangeScore(i, j);
					System.out.println();
					System.out.println(player.getName() + "のスコアボード");
					player.board.displayBoard();
				}
				default -> {
					System.out.println();
					System.out.println(player.getName() + "のスコアボード");
					player.board.displayBoard();
				}
			}
		}

		Random npcsChoice = new Random();
		Scanner anyInput = new Scanner(System.in);
		for(int j = 0; j < 3; j ++){
			System.out.printf("%sの %d - %d 目の投球!\n", npc.getName(), i + 1, j + 1);
			System.out.println("Enter : 次へ");
			String anyline = anyInput.nextLine();

			String bowl;
			boolean choice1 = npcsChoice.nextBoolean();
			if(choice1 == true){ // = だとInvalid Inputになる
				bowl = "heavy";
			}else{
				bowl = "light";
			}

			String direction = "left";
			int choice2 = npcsChoice.nextInt(0, 3);
			if(choice2 == 0){
				direction = "left";
			}else if(choice2 == 1){
				direction = "center";
			}else if(choice2 == 2) {
				direction = "right";
			}

			System.out.printf("Debug ： Bowl %s, Direction %s\n", bowl, direction);
			npc.throwBowl(bowl, direction, i, j);
			npc.pines.displayPines();
			npc.board.arrangeScore(i, j);
			System.out.println();
			switch(j) {
				default -> {
					System.out.println();
					System.out.println(npc.getName() + "のスコアボード");
					npc.board.displayBoard();
				}
				case 1 -> {
					npc.board.setFrameScore(i);
					npc.board.arrangeScore(i, j);
					System.out.println();
					System.out.println(npc.getName() + "のスコアボード");
					npc.board.displayBoard();
				}
			}
			System.out.println();
			System.out.println();
			System.out.println("Enter : 次へ");
			anyline = anyInput.nextLine();
		}
		System.out.println(player.getName() + "のスコアボード");
		player.board.displayBoard();
		System.out.println(npc.getName() + "のスコアボード");
		npc.board.displayBoard();
	}
}


