import java.util.Scanner;

public class Main {
	public static Game game;
	public static void main (String[] args) {
		while (true) {
			game = new Game();
			String again;
			game.Loop(); // blocking....
			System.out.println("Do you want to play it again? (y/n) : ");
			if ((again = (new Scanner(System.in)).nextLine()).length() != 0) {
				switch (again.charAt(0)) {
					case 'n': case 'N': return;
				}
			}
		}
	}
}

