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

