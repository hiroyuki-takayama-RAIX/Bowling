package classes;

import java.util.*;

public class ScoreBoard {
	private String[][] score0109 = new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}};
	private String[] score10 = new String[]{"0", "0", "0"};
	private String[] frameScores = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};

	public String getScore(int frame, int throwing) {
		String score;
		if (frame == 9) {
			score = score10[throwing];
		} else {
			score = score0109[frame][throwing];
		}
		return score;
	}

	public void setScore2(int frame, int throwing, String score) {
		if (frame == 9) {
			score10[throwing] = score;
		} else {
			score0109[frame][throwing] = score;
		}
	}

	public int getFrameScore(int frame) {
		return Integer.parseInt(this.frameScores[frame]);
	}

	public void setScore(int frame, int throwing, int score) {
		int lastScore = 0;
		String[] scores;
		if (frame == 9) {
			scores = score10;
		} else {
			scores = score0109[frame];
		}
		if (throwing > 0) {
			switch (scores[throwing - 1]) {
				case "X" -> lastScore = 10;
				case "G", "-" -> lastScore = 0;
				default -> lastScore = Integer.parseInt(scores[throwing - 1]);
			}
		}
		if (Objects.equals(scores[throwing], "-")) {
			scores[throwing] = "0";
		}
		if (throwing == 0 && score == 10) {
			scores[throwing] = "X";
			System.out.println("Strike!!!");
		} else if (throwing == 1 && lastScore + score == 10) { //ここ計算がおかしい
			scores[throwing] = "/";
			System.out.println("Spare!");
		} else if (score == 0 && (throwing == 1 || throwing == 2)) {
			scores[throwing] = "-";
			System.out.println();
		} else if (score == 0 && (throwing == 0)) {
			scores[throwing] = "G";
		} else {
			scores[throwing] = String.valueOf(score);
		}
	}

	public void setFrameScore(int frame) {
		int score;
		int lastScore = 0;
		String[] scores;
		if (frame == 9) {
			scores = score10;
		} else {
			scores = score0109[frame];
		}
		if (frame != 0) {
			lastScore = Integer.parseInt(frameScores[frame - 1]);
		}
		score = calculateFrameScore(scores);
		frameScores[frame] = String.valueOf(score + lastScore);
	}

	public void arrangeBoard(int frame, int throwing) {
		if (frame > 0) {
			String lastScore10 = score0109[frame - 1][0];
			String lastScore11 = score0109[frame - 1][1];
			if (throwing == 0) {
				if (Objects.equals(lastScore11, "/") || Objects.equals(lastScore10, "X")) {
					arrangeFrameScore(frame, throwing);
				}
			} else {
				if (Objects.equals(lastScore10, "X")) {
					arrangeFrameScore(frame, throwing);
				}
			}
			if (frame != 1) {
				String lastScore20 = score0109[frame - 2][0];
				if (Objects.equals(lastScore20, "X")) {
					frameScores[frame - 2] = String.valueOf(Integer.parseInt(lastScore20) + Integer.parseInt(frameScores[frame - 2]));
					frameScores[frame - 1] = String.valueOf(Integer.parseInt(lastScore20) + Integer.parseInt(frameScores[frame - 1]));
					frameScores[frame] = String.valueOf(Integer.parseInt(lastScore20) + Integer.parseInt(frameScores[frame]));
				}
			}
		}
	}

	public void arrangeFrameScore(int frame, int throwing) {
		String[] scores;
		if (frame == 9) {
			scores = score10;
		} else {
			scores = score0109[frame];
		}
		int score = convertUniqueScore(scores[throwing]);
		frameScores[frame - 1] = String.valueOf(score + Integer.parseInt(frameScores[frame - 1]));
		frameScores[frame] = String.valueOf(score + Integer.parseInt(frameScores[frame]));
	}


	public int convertUniqueScore(String score) {
		int converted;
		switch (score) {
			case "X" -> converted = 10;
			case "G", "-" -> converted = 0;
			default -> converted = Integer.parseInt(score);
		}
		return converted;
	}

	public int calculateFrameScore(String[] scores) {
		int[] score = new int[3];
		for (int i = 0; i < scores.length; i++) {
			switch (scores[i]) {
				case "X" -> score[i] = 10;
				case "/" -> score[i] = 10 - score[i - 1];
				case "-", "G" -> score[i] = 0;
				default -> score[i] = Integer.parseInt(scores[i]);
			}
		}
		return Arrays.stream(score).sum();
	}

	public void displayBoard() {
		for (int i = 1; i < 10; i++) {
			System.out.printf("|\t%s\t", i);
		}
		System.out.printf("|\t%s\t|\n", 10);

		for (int i = 0; i < 9; i++) {
			System.out.printf("| %s   %s ", this.score0109[i][0], this.score0109[i][1]);
		}
		System.out.printf("| %s %s %s |\n", this.score10[0], this.score10[1], this.score10[2]);

		for (int i = 0; i < 9; i++) {
			System.out.printf("|\t%s\t", this.frameScores[i]);
		}
		System.out.printf("|\t%s\t|\n", this.frameScores[9]);
	}
}