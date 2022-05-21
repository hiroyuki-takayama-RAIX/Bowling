package classes;

public class Pines {
	private String[] left = {"|", "|", "|"};
	private String[] center = {"|", "|", "|", "|"};
	private String[] right = {"|", "|", "|"};

	public void displayPines(){
		System.out.printf("\t\t\t%s\t\t\t\n", center[0]);
		System.out.printf("\t\t%s\t%s\t\t\n", center[1], center[2]);
		System.out.printf("\t%s\t%s\t%s\t\n", left[0], center[3], right[0]);
		System.out.printf("%s\t%s\t%s\t%s\n", left[2], left[1], right[1], right[2]);
	}

	public void resetPines(){
		left = new String[]{"|", "|", "|"};
		center = new String[]{"|", "|", "|", "|"};
		right = new String[]{"|", "|", "|"};
	}

	public int getLeftPinesNumber(){
		int count = 0;
		for (String s : left) {
			if (s == "") {
				count++;
			}
		}
		return count;
	}

	public int getCenterPinesNumber(){
		int count = 0;
		for (String s : left) {
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

	public void setLeftPine(int n){

		left[n] = "X";
	}

	public void setCenterPine(int n){
		center[n] = "X";
	}

	public void setRightPine(int n){
		right[n] = "X";
	}
}
