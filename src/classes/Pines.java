package classes;

public class Pines {
	private String[] left = {"|", "|", "|"};
	private String[] center = {"|", "|", "|", "|"};
	private String[] right = {"|", "|", "|"};

	public void displayPines() {
		System.out.printf("   %s   \n", center[0]);
		System.out.printf("  %s %s  \n", center[1], center[2]);
		System.out.printf(" %s %s %s \n", left[0], center[3], right[0]);
		System.out.printf("%s %s %s %s\n", left[2], left[1], right[1], right[2]);
	}

	public void resetPines() {
		left = new String[]{"|", "|", "|"};
		center = new String[]{"|", "|", "|", "|"};
		right = new String[]{"|", "|", "|"};
	}

	public int getPinesNumber(String position) {
		int count = 0;
		String[] pines = getPines(position);
		for (String pine : pines) {
			if (pine == "|") {
				count++;
			}
		}
		return count;
	}

	public int knockPine(String position, int n) {
		int knocked = 0;
		String[] pines = getPines(position);
		if (pines[n] == "|") {
			pines[n] = "X";
			knocked = 1;
		}
		return knocked;
	}

	public String[] getPines(String position) { //文字列で変数を指定するやり方で良いのか？ intで指定するようにする。コンピューターが理解しやすいようにする。文字列の判断はトラブルが多い？
		String[] pines = new String[4]; //「配列が初期化されていない可能性があります」のエラーを消すために付け加えた
		switch (position) {
			case "left" -> {
				pines = left;
			}
			case "center" -> {
				pines = center;
			}
			case "right" -> {
				pines = right;
			}
			default -> {
				System.out.println("引数 position には\"left\", \"center\", \"right\"のいずれかをString方で指定してください！");
			}
		}
		return pines;
	}

}
