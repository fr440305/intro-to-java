import java.util.Scanner;

public class Game {
	public Grid grid; // private
	public int stepnum; // private
	public Game () {
		this.grid = new Grid();
		this.stepnum = 0;
	}

	public void Loop () {
		char win_char;
		int row;
		int col;
		String err;
		Scanner ipt = new Scanner(System.in);
		while ((win_char = this.grid.WhoWins()) == ' ') {
			this.grid.Print();
			System.out.print("Player " + ((this.stepnum%2==0)?'O':'X') + " >>> ");
			row = ipt.nextInt();
			col = ipt.nextInt();
			err = this.grid.Fill(row, col, (this.stepnum%2==0)?'O':'X');
			if (!err.equals("")) {
				System.out.println(err);
				System.out.println("Try again!");
				continue;
			}
			this.stepnum += 1;
		}
		this.grid.Print();
		System.out.println("Player " + win_char + " wins on step " + this.stepnum + "!");
	}
}
