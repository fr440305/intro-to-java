public class Grid {
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

	private char AtIndex (int idx) {
		return this.mat[idx/3][idx%3];
	}
	// 0 .. 8
	private boolean Eq (int i1, int i2, int i3) {
		return (
			this.AtIndex(i1) == this.AtIndex(i2) &&
			this.AtIndex(i1) == this.AtIndex(i3) &&
			this.AtIndex(i2) == this.AtIndex(i3)
		);
	}
	public boolean IsFull () {
		for (int r = 0; r < 3; r += 1) {
			for (int c = 0; c < 3; c += 1) {
				if (this.mat[r][c] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	public char WhoWins () {
		// all the winning situations:
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
				return this.AtIndex(wins[i][0]);
			}
		}
		return ' ';
	}
}


