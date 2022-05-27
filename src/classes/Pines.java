package classes;

public class Pines {
	private String[] left = {"|", "|", "|"};
	private String[] center = {"|", "|", "|", "|"};
	private String[] right = {"|", "|", "|"};

	public void displayPines(){
		System.out.printf("   %s   \n", center[0]);
		System.out.printf("  %s %s  \n", center[1], center[2]);
		System.out.printf(" %s %s %s \n", left[0], center[3], right[0]);
		System.out.printf("%s %s %s %s\n", left[2], left[1], right[1], right[2]);
	}

	public void resetPines(){
		left = new String[]{"|", "|", "|"};
		center = new String[]{"|", "|", "|", "|"};
		right = new String[]{"|", "|", "|"};
	}

	public int getLeftPinesNumber(){
		int count = 0;
		for (String s : left) {
			if (s == "|") {
				count++;
			}
		}
		return count;
	}

	public int getCenterPinesNumber(){
		int count = 0;
		for (String s : center) {
			if (s == "|") {
				count++;
			}
		}
		return count;
	}

	public int getRightPinesNumber(){
		int count = 0;
		for (String s : right) {
			if (s == "|") {
				count++;
			}
		}
		return count;
	}

	public int getPinesNumber(){
		int count = getLeftPinesNumber() + getCenterPinesNumber() + getRightPinesNumber();
		return count;
	}

	public int knockLeftPine(int n){
		int knocked = 0;
		if(left[n] == "|"){
			left[n] = "X";
			knocked = 1;
		}
		return knocked;
	}

	public int knockCenterPine(int n){
		int knocked = 0;
		if(center[n] == "|"){
			center[n] = "X";
			knocked = 1;
		}
		return knocked;
	}

	public int knockRightPine(int n){
		int knocked = 0;
		if(right[n] == "|"){
			right[n] = "X";
			knocked = 1;
		}
		return knocked;
	}
}
