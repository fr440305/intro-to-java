import java.util.Scanner;

public class Main {
	public static Game game;
	public static void main (String[] args) {
		while (true) {
			game = new Game();
			game.Loop(); // blocking....
			System.out.println("Do you want to play it again?");
			switch ((new Scanner(System.in)).nextLine().charAt(0)) {
				case 'n': case 'N': return;
			}
		}
	}
}

class Grid {
	char[][] mat; // matrix

	public Grid () {
		this.mat = new char[3][3];
		for (int r = 0; r < 3; r += 1) {
			for (int c = 0; c < 3; c += 1) {
				this.mat[r][c] = ' ';
			}
		}
	}

	// Use return-string instead of
	// throwing an exception because
	// i don't know how to use exception.
	public String Fill (int row, int col, char sym) {
		if (!(row >= 0 && row < 3 && col >= 0 && col < 3)) {
			return new String("Position out of boundries!");
		}
		if (this.mat[row][col] != ' ') {
			return new String("Position has already been filled!");
		}
		if (sym == ' ') {
			return new String("Illegal symbol!");
		}
		this.mat[row][col] = sym;
		return new String("");
	}
	public void Print () {
		System.out.print('\n');
		System.out.println("==============");
		System.out.println("   0   1   2");
		for (int r = 0; r < 3; r += 1) {
			System.out.print(r + "| ");
			for (int c = 0; c < 3; c += 1) {
				System.out.print(this.mat[r][c]);
				System.out.print(" | ");
			}
			System.out.print('\n');
		}
		System.out.println("==============");
	}

	public char WhoWins () {
		//return this.mat[2][2];
		
	}
}

class Game {
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
		System.out.println("Player " + win_char + " wins on step " + this.stepnum + "!");
	}
}
