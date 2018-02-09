import java.util.Scanner;

public class Main {
	public static Game game;
	public static void main (String[] args) {
		while (true) {
			game = new Game();
			game.Loop(); // blocking....
			System.out.println("Do you want to play it again? (y/n) : ");
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

	// 0 .. 8
	private boolean Eq (int i1, int i2, int i3) {
		int r1 = i1 / 3;
		int c1 = i1 % 3;
		int r2 = i2 / 3;
		int c2 = i2 % 3;
		int r3 = i3 / 3;
		int c3 = i3 % 3;
		return (
			this.mat[r1][c1] == this.mat[r2][c2] &&
			this.mat[r1][c1] == this.mat[r3][c3] &&
			this.mat[r2][c2] == this.mat[r3][c3]
		);
	}
	public char WhoWins () {
		//return this.mat[2][2];
		int[][] wins = {
			{0, 1, 2},
			{3, 4, 5},
			{6, 7, 8},
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			{0, 4, 8},
			{2, 4, 6}
		}; // [8][3]
		for (int i = 0; i < 8; i += 1) {
			if (this.Eq(wins[i][0], wins[i][1], wins[i][2])) {
				return (this.mat[wins[i][0]/3][wins[i][0]%3]);
			}
		}
		return ' ';
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
		this.grid.Print();
		System.out.println("Player " + win_char + " wins on step " + this.stepnum + "!");
	}
}
