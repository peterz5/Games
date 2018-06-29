import java.util.Scanner;
import java.lang.Math;

public class battleship
{

	public static final int ROWS = 10;
	public static final int COLS = 10;
	public static final int[][] board = new int[ROWS][COLS]; 
	public static final int[][] opponent = new int[ROWS][COLS];

	public static int attempts;
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args)
	{
		initGame();
		while(attempts > 0 && !game_over())
		{
			makemove();
			attempts--;
		}

		System.out.println();
		if(attempts > 0){System.out.println("Congratulations, You Win!");}
		else{System.out.println("GAME OVER, You Lose!");}
		System.out.println();
		printBoard(true);
	}

	public static void initGame()
	{
		attempts = 40;

		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++)
			{
				board[i][j] = 0;
				opponent[i][j] = 0;
			}
		}

		int[] boatsizes = {1,2,3,4,5};
		int row, col;

		for(int i = 0; i < boatsizes.length; i++)
		{
			if(Math.random() < .5) //vertical positioning
			{
				row = (int)(Math.random() * (ROWS - boatsizes[i]));
				col = (int)(Math.random() * COLS);
				for(int j = row; j < row + boatsizes[i]; j++)
				{
					opponent[j][col] = 1;
				}
			}
			else //horizontal positioning
			{
				row = (int)(Math.random() * ROWS);
				col = (int)(Math.random() * (COLS - boatsizes[i]));
				for(int j = col; j < col+ boatsizes[i]; j++)
				{
					opponent[row][j] = 1;
				}
			}
		}
	}

	public static boolean game_over()
	{
		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++)
			{
				if(opponent[i][j] == 1)
				{
					return false;
				}
			}
		}
		return true;
	}

	public static void makemove()
	{
		boolean valid = false;
		while(!valid)
		{
			System.out.println("Attempts remaining: " + attempts);
			printBoard(false);
			System.out.print("Please choose a pair of coordinates to attack (col (0-9) row (0-9)): \n");
	
			int y = in.nextInt();
			int x = in.nextInt();

			if(x >= 0 && x<ROWS && y >= 0 && y<COLS)
			{
				if(board[x][y] != 0)
				{
					System.out.println("You've already fired in this position");
					System.out.println();
				}
				else
				{
					if(opponent[x][y] == 1)
					{
						opponent[x][y] = -1;
						board[x][y] = 1;
					}
					else
					{
						board[x][y] =-1;
					}
					valid = true;
				}
			}
			else
			{
				System.out.println("You messed up the input. Please try again.");
			}
		}
	}

	public static void printBoard(boolean end)
	{
		if(!end)
		{
			System.out.println("  0  1  2  3  4  5  6  7  8  9  ");
			System.out.println("--------------------------------");
			for(int i = 0; i < ROWS; i++)
			{
				System.out.print("|");
				for(int j = 0; j < COLS; j++)
				{
					if(board[i][j] == 0)
					{
						System.out.print(" . ");
					}
					else if(board[i][j] ==1)
					{
						System.out.print(" X ");
					}
					else{
						System.out.print(" O ");
					}
				}
				System.out.println("| " + i);
			}
			System.out.println("--------------------------------");
		}

		else
		{
			System.out.println("--------------------------------");
			for(int i = 0; i < ROWS; i++)
			{
				System.out.print("|");
				for(int j = 0; j < COLS; j++)
				{
					if(opponent[i][j] == 0)
					{
						System.out.print(" . ");
					}
					else
					{
						System.out.print(" X ");
					}
				}
				System.out.println("|");
			}
			System.out.println("--------------------------------");
		}
	}

}