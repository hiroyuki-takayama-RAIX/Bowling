package classes;

import java.util.*;

public class ScoreBoard {
	public String[][] score0109 = new String[][]{{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"}};
	private String[] score10 = new String[]{"0", "0", "0"};
	private String[] frameScore = new String[]{"0","0","0","0","0","0","0","0","0","0"};

	public void setScore(int frame, int throwing, int score){
		if(score0109[frame][throwing] == "0"){
		}
		if(throwing == 1 && score == 10){
			score0109[frame][throwing] = "X";
		}else if(throwing == 2 && Integer.parseInt(score0109[frame][throwing]) + score == 10){
			score0109[frame][throwing] = "/";
		}else if(score == 0){
			score0109[frame][throwing] = "-";
		}else{
			score0109[frame][throwing] = String.valueOf(score);
		}
	}

	public void setFrameScore(int frame){
		int score = calculateFrameScore(score0109[frame]);
		switch(frame){
			case 1:
				frameScore[frame] = String.valueOf(score);
				break;
			default:
				int lastScore = Integer.parseInt(frameScore[frame - 1]);
				if(frame == 10){
					score = calculateFrameScore(score10);
				}
				frameScore[frame] = String.valueOf(score + lastScore);
				break;
		}
	}

	public void arrangeScore(int frame, int throwing){
		if(frame > 1){
			String lastFrame1 = score0109[frame - 1][1];
			String lastFrame2 = score0109[frame - 1][2];
			switch(throwing){
				case 1:
					if(lastFrame2 == "/" || lastFrame1 == "X"){
						frameScore[frame -1] = String.valueOf(Integer.parseInt(lastFrame1) + Integer.parseInt(frameScore[frame -1]));
					}
					break;
				default:
					if(lastFrame1 == "X"){
						frameScore[frame -1] = String.valueOf(Integer.parseInt(lastFrame2) + Integer.parseInt(frameScore[frame -1]));
					}
					break;
			}
		}
	}

	public int calculateFrameScore(String[] scoreArray){
		int[] score = new int[3];
		for(int i = 0; i < scoreArray.length; i ++){
			switch(scoreArray[i]){
				case "X":
					score[i] = 10;
					break;
				case "/":
					score[i] = 10 - score[i - 1];
					break;
				case "-":
					score[i] = 0;
					break;
				default:
					score[i] = Integer.parseInt(scoreArray[i]);
					break;
			}
		}
		int total = Arrays.stream(score).sum();
		return total;
	}

	public void displayBoard(){
		for (int i = 1; i < 10; i ++) {
			System.out.printf("|\t%s\t|", i);
		}
		System.out.printf("|\t%s\t|\n", 10);

		for (int i = 0; i < 9; i ++) {
			System.out.printf("|%s\t%s|", score0109[i][0], score0109[i][1]);
		}
		System.out.printf("|%s%s%s|\n", score10[0], score10[1], score10[2]);

		for (int i = 0; i < 9; i ++) {
			System.out.printf("|\t%s\t|", frameScore[i]);
		}
		System.out.printf("|\t%s\t|\n", frameScore[9]);
	}


}