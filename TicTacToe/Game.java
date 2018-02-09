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
		while (true) {
			this.grid.Print();
			// if win:
			if ((win_char = this.grid.WhoWins()) != ' ') {
				System.out.println(
					"Player "+
					win_char+
					" wins on step "+
					this.stepnum + "!"
				);
				return;
			}
			// if full:
			if (this.grid.IsFull()) {
				System.out.println("Grid Full. No one wins.");
				return;
			}
			System.out.print("Player " + ((this.stepnum%2==0)?'O':'X') + " >>> ");
			row = ipt.nextInt();
			col = ipt.nextInt();
			err = this.grid.Fill(row, col, (this.stepnum%2==0)?'O':'X');
			if (err.length() != 0) {
				System.out.println(err);
				System.out.println("Try again!");
				continue;
			}
			this.stepnum += 1;
		}
	}
}
