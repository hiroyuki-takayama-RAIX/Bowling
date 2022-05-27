package classes;

import java.util.*;

public class ScoreBoard {
	public String[][] score0109 = new String[][]{{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"},{"0", "0"}};
	private final String[] score10 = new String[]{"0", "0", "0"};
	private final String[] frameScores = new String[]{"0","0","0","0","0","0","0","0","0","0"};

	public String getScore(int frame, int throwing){
		String score;
		if(frame == 9){
			score = score10[throwing];
		}else{
			score = score0109[frame][throwing];
		}
		return score;
	}

	public void setScore2(int frame, int throwing, String score){
		if(frame == 9){
			score10[throwing] = score;
		}else{
			score0109[frame][throwing] = score;
		}
	}

	public int getFrameScore(int frame){
		return Integer.parseInt(this.frameScores[frame]);
	}

	public void setScore(int frame, int throwing, int score){
		int lastScore = 0;
		switch(frame){
			case 9 -> {
				if(throwing > 0){
					switch(score10[throwing - 1]){
						case "X" -> {
							lastScore = 10;
						}
						case "G", "-" -> {
							lastScore = 0;
						}
						default -> {
							lastScore = Integer.parseInt(score10[throwing - 1]);
						}
					}
				}
				if(score10[throwing] == "-"){
					score10[throwing] = "0";
				}
				if(throwing == 0 && score == 10){
					score10[throwing] = "X";
					System.out.println("Strike!!!");
				}else if(throwing == 1 && lastScore + score == 10){ //ここ計算がおかしい
					score10[throwing] = "/";
					System.out.println("Spare!");
				}else if(score == 0 && (throwing == 1 || throwing == 2)) {
					score10[throwing] = "-";
					System.out.println();
				}else if(score == 0 && (throwing == 0)){ 
					score10[throwing] = "G";
				}else{
					score10[throwing] = String.valueOf(score);
				}
			}
			default -> {
				if(throwing > 0){
					switch(score0109[frame][throwing - 1]){
						case "X" -> {
							lastScore = 10;
						}
						case "G", "-" -> {
							lastScore = 0;
						}
						default -> {
							lastScore = Integer.parseInt(score0109[frame][throwing - 1]);
						}
					}
				}
				if(throwing == 0 && score == 10){
					score0109[frame][throwing] = "X";
					System.out.println("Strike!!!");
				}else if(throwing == 1 && lastScore + score == 10){
					score0109[frame][throwing] = "/";
					System.out.println("Spare!");
				}else if(score == 0 && throwing == 1) {
					score0109[frame][throwing] = "-";
				}else if(score == 0 && throwing == 0){
					score0109[frame][throwing] = "G";
				}else{
					score0109[frame][throwing] = String.valueOf(score);
				}
			}
		}
	}

	public void setFrameScore(int frame){
		int score;
		int lastScore;
		switch(frame) {
			case 0 -> {
				score = calculateFrameScore(score0109[frame]);
				frameScores[frame] = String.valueOf(score);
			}
			case 9 -> {
				lastScore = Integer.parseInt(frameScores[frame - 1]);
				score = calculateFrameScore(score10);
				frameScores[frame] = String.valueOf(score + lastScore);
			}
			default -> {
				score = calculateFrameScore(score0109[frame]);
				lastScore = Integer.parseInt(frameScores[frame - 1]);
				frameScores[frame] = String.valueOf(score + lastScore);
			}
		}
	}

	public void arrangeFrameScore(int frame, int throwing){
		if(frame > 0){
			String lastScore10 = score0109[frame - 1][0];
			String lastScore11 = score0109[frame - 1][1];
			int score;
			switch(throwing){
				case 0 -> {
					if (lastScore11 == "/" || lastScore10 == "X") {
						score = convertUniqueScore(score0109[frame][throwing]);
						frameScores[frame - 1] = String.valueOf(score + Integer.parseInt(frameScores[frame - 1]));
						frameScores[frame] = String.valueOf(score + Integer.parseInt(frameScores[frame]));
					}
				}
				case 1 -> {
					if (lastScore10 == "X") {
						score = convertUniqueScore(score0109[frame][throwing]);
						frameScores[frame - 1] = String.valueOf(score + Integer.parseInt(frameScores[frame - 1]));
						frameScores[frame] = String.valueOf(score + Integer.parseInt(frameScores[frame]));
					}
				}
			}
		}else if(frame > 1){
			String lastScore10 = score0109[frame - 1][0];
			String lastScore11 = score0109[frame - 1][1];
			String lastScore20 = score0109[frame - 2][0];
			String lastScore21 = score0109[frame - 2][1];
			int score;
			switch(throwing){
				case 0 -> {
					if (lastScore11 == "/" || lastScore10 == "X") {
						score = convertUniqueScore(score0109[frame][throwing]);
						frameScores[frame - 1] = String.valueOf(score + Integer.parseInt(frameScores[frame - 1]));
						frameScores[frame] = String.valueOf(score + Integer.parseInt(frameScores[frame]));
					}
				}
				default -> {
					if (lastScore10 == "X") {
						score = convertUniqueScore(score0109[frame][throwing]);
						frameScores[frame - 1] = String.valueOf(score + Integer.parseInt(frameScores[frame - 1]));
						frameScores[frame] = String.valueOf(score + Integer.parseInt(frameScores[frame]));
					}
				}
			}
			if (lastScore20 == "X") {
				frameScores[frame - 2] = String.valueOf(Integer.parseInt(lastScore20) + Integer.parseInt(frameScores[frame - 2]));
				frameScores[frame - 1] = String.valueOf(Integer.parseInt(lastScore20) + Integer.parseInt(frameScores[frame - 1]));
				frameScores[frame] = String.valueOf(Integer.parseInt(lastScore20) + Integer.parseInt(frameScores[frame]));
			}
		}
	}

	public int convertUniqueScore(String score){
		int converted;
		switch(score){
			case "X" -> {
				converted = 10;
			}
			case "G", "-" -> {
				converted = 0;
			}
			default -> {
				converted = Integer.parseInt(score);
			}
		}
		return converted;
	}

	public int calculateFrameScore(String[] scores){
		int[] score = new int[3];
		for(int i = 0; i < scores.length; i ++){
			switch(scores[i]){
				case "X":
					score[i] = 10;
					break;
				case "/":
					score[i] = 10 - score[i - 1];
					break;
				case "-":
				case "G":
					score[i] = 0;
					break;
				default:
					score[i] = Integer.parseInt(scores[i]);
					break;
			}
		}
		return Arrays.stream(score).sum();
	}

	public void displayBoard(){
		for (int i = 1; i < 10; i ++) {
			System.out.printf("|\t%s\t", i);
		}
		System.out.printf("|\t%s\t|\n", 10);

		for (int i = 0; i < 9; i ++) {
			System.out.printf("| %s   %s ", score0109[i][0], score0109[i][1]);
		}
		System.out.printf("| %s %s %s |\n", score10[0], score10[1], score10[2]);

		for (int i = 0; i < 9; i ++) {
			System.out.printf("|\t%s\t", frameScores[i]);
		}
		System.out.printf("|\t%s\t|\n", frameScores[9]);
	}
}
