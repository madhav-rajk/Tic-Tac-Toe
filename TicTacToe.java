import java.util.Scanner;
/**
 * Purpose of this class was to build a two - player tic-tac-toe game where
 * there are two users and they will chose a spot on the board to put their
 * Xs and Os. The board will be displayed after each move of each player and
 * the winner will be called after the diagonals or columns have identical
 * letters of either, or there will be declared a tie if neither players won.
 * 
 * Madhav Rajkondawar
 *
 * 3/13/19
 */
public class TicTacToe {
    /*
     * made two class variables for the static class, so the static
     * reference, of a 2D array for a tic-tac-toe board and made the scanner
     * for input from user, which can be used throughout the whole class in
     * multiple methods. The purpose of these variables was that the String
     * 2D array will save all the values for the tic-tac-toe board and the
     * scanner will be used to get user input and call it in multiple
     * methods, not restricting it to just the main method.
     */
    private static String[][] ticTacBoard = new String[3][3];
    private static Scanner keyboard = new Scanner(System.in);

    /*
     * The purpose of the main method was to put all the methods together to
     * run the actual game. First, the user is asked for X, since X will go
     * first, then there is a loop to check whether the enter input was a
     * valid number and then we will check whether the number is in the given
     * range, and then the character will be printed on the board and the board
     * will be displayed. Same thing will be done again for O, and in between
     * all these steps, there will be a check made to make sure that all spots
     * are not filled and that there isn't a winner for at that particular
     * stage of the game.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to a 2 player tic-tac-toe game!");
        System.out.println("Here's what the board looks like: ");
        printBoard();

        //while loop repeats until isThereAWinner becomes true, which is
        // false at the start, but becomes true when one of the patterns
        // exists where the letter is matching, like diagonals, columns or
        // rows for either X or O.

        while(!isThereAWinner())
        {
            //testVariableMethod();
            System.out.print("X's will go now. Give me your spot (1-9): ");
            while(!keyboard.hasNextInt())
            {
                System.out.print("That's not a number! Enter again! (1-9): ");
                keyboard.next();
            }
            int spotX = keyboard.nextInt();
            isValidNum(spotX,"X");
            printBoard();
            if(isThereAWinner())
            {
                printBoard();
                break;
            }
            else if(allSpotsFilled())
            {
                //checks to make sure that if all the spots in the array are
                // filled with values and winner wasn't declared, then that
                // means its a tie.
                System.out.println("All spots filled! It's a tie! Game over!");
                printBoard();
                break;
            }
            //repeats the above, but for O.
            System.out.print("O's will go now. Give me your spot (1-9): ");
            while(!keyboard.hasNextInt())
            {
                System.out.print("That's not a number! Enter again! (1-9): ");
                keyboard.next();
            }
            int spotO = keyboard.nextInt();
            isValidNum(spotO,"O");
            printBoard();
            if(isThereAWinner())
            {
                printBoard();
                break;
            }
            else if(allSpotsFilled())
            {
                System.out.println("All spots filled! It's a tie! Game over!");
                break;
            }
        }
    }

    /*
     * The purpose of this method was to check whether the methods that were
     * assigned to check whether the pattern given reports the winner when
     * using the methods like isThereAWinner, which should return the winner.
     * @param none
     */
    private static void testVariableMethod()
    {
        // I have entered X at the specific spots on the board so that the
        // isThereAWinner method should return a winner, since the pattern is
        // a winning pattern, which is a way to check whether that method,
        // which is critical, works. This is important because that method
        // basically decides how long the whole loop of asking the user for
        // input will run.
        System.out.println("Enters 3 spots for X on the tic-tac-toe board: ");
        printSpot(3,"X");
        printSpot(6,"X");
        printSpot(9,"X");
        isThereAWinner();

        // I have entered O at particular spots on the board that the method
        // should return the name of a winner, since the pattern is a winning
        // pattern. This is important because the method is crucial for the
        // whole class to work well and decides how many times the user will
        // be asked for input.
        System.out.println("Enters 3 spots for O on the tic-tac-toe board: ");
        printSpot(1,"O");
        printSpot(5,"O");
        printSpot(9,"O");
        isThereAWinner();

        // The methods below check to make sure that the methods are catching
        // invalid inputs and makes sure that the user provides valid input.
        // The first two calls should say the number is not valid and the
        // last should say that the input is valid. Then, the method would
        // check if there is a blank spot at that particular spot on the
        // board, which is not necessary for this test, but the main point of
        // whether the spots are valid or not is accomplished here.
        System.out.println("Give a number in the range 1 - 9: ");
        isValidNum(10,"X");
        isValidNum(15, "O");
        isValidNum(9,"X");

    }

    /*
     * The purpose of this method was to check and make sure that the spot
     * entered by the user was within the range 1-9. The method takes the
     * spot and the character as parameters and sets isValidNum to false,
     * assuming number is not valid, and then repeats the while loop until
     * the number is valid, and inside the loop, if the number is not valid,
     * asks to user for another number and if that also is wrong, then the
     * while loop will go on until the if/else loop goes into the else, which
     * means the number is valid, and the loop will be exited. The method
     * then saves the spot and the character into the printSpot, which saves
     * the character at that particular spot in the ticTacBoard 2D array. The
     * loop also checks to make sure the input given by the user is in fact
     * a number.
     * @param spot, userChar
     */
    private static void isValidNum(int spot, String userChar)
    {
        boolean isValidNum = false;
        while(!isValidNum)
        {
            if(spot < 1 || spot > 9)
            {
                System.out.print("Number out of range! Enter another number " +
                    "(1-9): ");
                while(!keyboard.hasNextInt())
                {
                    System.out.print("That's not a number! Enter again! " +
                        "(1-9): ");
                    keyboard.next();
                }
                spot = keyboard.nextInt();
            }
            else
            {
                isValidSpot(spot,userChar);
                isValidNum = true;
            }
        }
    }

    /*
     * The purpose of this method was to check whether the spot is taken by
     * another X or O. There is a while loop that repeats until the a valid
     * spot is found. Inside the loop, there is check made to see where the
     * user's spot is and inside each of the loops, there is a check made to
     * make sure the input given is valid. Accordingly the value is saved in
     * the actual 2D array if all the requirements are met. The each if/else
     * if loop is so that the spot can be marked according to which row it
     * lies in, since we're working with a 2D array.
     * @param spot, userChar
     */
    private static void isValidSpot(int spot, String userChar)
    {
        boolean isValidSpot = false;
        while (!isValidSpot)
        {
            if(spot >= 1 && spot <= 3)
            {
                if(!ticTacBoard[0][spot-1].equals(" "))
                {
                    System.out.print("Spot taken! Enter a valid num! (1-9): ");
                    while(!keyboard.hasNextInt())
                    {
                        System.out.print("That's not a number! Enter again!" +
                            " (1-9): ");
                        keyboard.next();
                    }
                    spot = keyboard.nextInt();
                    boolean isValidNum = false;
                    //repeats until a valid number is found, else asks the
                    // user to keep entering numbers.
                    while (!isValidNum)
                    {
                        if(spot < 1 || spot > 9)
                        {
                            System.out.print("Number is not valid! Enter " +
                                "again!: ");
                            spot = keyboard.nextInt();
                        }
                        else
                        {
                            isValidNum = true;
                        }
                    }
                }
                else
                {
                    isValidSpot = true;
                }
            }
            else if(spot >= 4 && spot <= 6)
            {
                if(!ticTacBoard[1][spot-4].equals(" "))
                {
                    System.out.print("Spot taken! Enter again! (1-9): ");
                    while(!keyboard.hasNextInt())
                    {
                        System.out.print("That's not a number! Enter again!" +
                            " (1-9): ");
                        keyboard.next();
                    }
                    spot = keyboard.nextInt();
                    boolean isValidNum = false;
                    while (!isValidNum)
                    {
                        if(spot < 1 || spot > 9)
                        {
                            System.out.print("Number is not valid! Enter " +
                                "again!: ");
                            spot = keyboard.nextInt();
                        }
                        else
                        {
                            isValidNum = true;
                        }
                    }
                }
                else
                {
                    isValidSpot = true;
                }
            }
            else if(spot >= 7 && spot <= 9)
            {
                if(!ticTacBoard[2][spot-7].equals(" "))
                {
                    System.out.print("Spot taken! Enter again! (1-9): ");
                    while(!keyboard.hasNextInt())
                    {
                        System.out.print("That's not a number! Enter again!" +
                            " (1-9): ");
                        keyboard.next();
                    }
                    spot = keyboard.nextInt();
                    boolean isValidNum = false;
                    while (!isValidNum)
                    {
                        if(spot < 1 || spot > 9)
                        {
                            System.out.print("Number is not valid! Enter " +
                                "again!: ");
                            while(!keyboard.hasNextInt())
                            {
                                System.out.print("That's not a number! Enter " +
                                    "again! (1-9): ");
                                keyboard.next();
                            }
                            spot = keyboard.nextInt();
                        }
                        else
                        {
                            isValidNum = true;
                        }
                    }
                }
                else
                {
                    isValidSpot = true;
                }
            }
        }
        //saves the spot and the character in the 2D array at the particular
        // row and column. This statement is at the end because this is
        // showing that all the conditions are met and the number is a legal,
        // in the range, unique spot where there isn't any other character.
        printSpot(spot,userChar);
    }

    /*
     * The purpose of this method was to check to make sure that all
     * spots are not filled, which means that make sure that there is at
     * least one spot that has " ", which is an empty array value at that
     * index. The nested for loops check through each row and column and each
     * value at that index of the 2D array. Returns false if there are spots
     * left, and true if all the spots are indeed filled.
     * @param none
     */
    private static boolean allSpotsFilled()
    {
        for(int row = 0; row < ticTacBoard.length; row++)
        {
            for(int col = 0; col < ticTacBoard[row].length; col++)
            {
                if(ticTacBoard[row][col].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * The purpose of this method was to print all the values in the array as
     * a board, with the vertical lines making it actually look like a
     * ticTacToe board. Saves each index as a space if there is no value
     * assigned at that index of the 2D array. prints only 2 vertical bars
     * between index 0 and 1, and 1 and 2.
     * @param none
     */
    private static void printBoard()
    {
        for(int row = 0; row < ticTacBoard.length; row++)
        {
            for(int col = 0; col < ticTacBoard[row].length; col++)
            {
                if(ticTacBoard[row][col] == null)
                {
                    System.out.print(ticTacBoard[row][col] = " ");
                }
                else
                {
                    System.out.print(ticTacBoard[row][col]);
                }
                if(col != ticTacBoard[row].length-1)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    /*
     * The purpose of this method is to save the spot and the userChar at the
     * particular row and column, this method being after the it being
     * ensured that the spot given is between the valid range. The first if
     * loop is to check whether the spot is in the first row, second
     * condition is to check if its in the 2nd row and the third is to check
     * if in the third row. The 2D array is given the respective index for
     * the spot that the user wants, when the row will be check internally,
     * since 1 - 3 will be in first row, 4 - 6 in the second and rest will be
     * in the third row.
     * @param spot, userChar
     */
    private static void printSpot(int spot, String userChar)
    {
        if(spot >= 1 && spot <= 3)
        {
            System.out.println(userChar+" has been marked successfully!");
            ticTacBoard[0][spot-1] = userChar;
        }
        else if(spot >= 4 && spot <= 6)
        {
            System.out.println(userChar+" has been marked successfully!");
            ticTacBoard[1][spot-4] = userChar;
        }
        else if(spot >= 7 && spot <= 9)
        {
            System.out.println(userChar+" has been marked successfully!");
            ticTacBoard[2][spot-7] = userChar;
        }
    }

    /*
     * The purpose of this method was to check whether each row, column or
     * diagonals are filled with the same character and if so, then the
     * winner will be called and the method will return true that there is a
     * winner, which will in turn break out of the while loop in the main,
     * which is repeating until there is no winner. The conditions in the if
     * statements are making sure that the blanks are made sure to avoid,
     * since the method will return true at the beginning itself, since at
     * the beginning all values at all indexes of the 2D array are blanks,
     * which are equal to each other. The other conditions are that one of
     * the 8 possible patterns in a tic-tac-toe are met. Then, if these
     * conditions are met, then the method prints the respective winner and
     * returns true, which helps break out of the while loop in the main,
     * which repeats until this method returns true. This method returns
     * false, if none of the patterns are met.
     * @param none
     */
    private static boolean isThereAWinner()
    {
        if(!ticTacBoard[0][0].equals(" ") &&
        ticTacBoard[0][0].equals(ticTacBoard[0][1]) &&
        ticTacBoard[0][1].equals(ticTacBoard[0][2]))
        {
            System.out.println(ticTacBoard[0][0]+ " is the winner!");
            return true;
        }
        else if(!ticTacBoard[0][0].equals(" ")
        && ticTacBoard[0][0].equals(ticTacBoard[1][0]) &&
        ticTacBoard[1][0].equals(ticTacBoard[2][0]))
        {
            System.out.println(ticTacBoard[0][0]+ " is the winner!");
            return true;
        }
        else if(!ticTacBoard[0][0].equals(" ")
        && ticTacBoard[0][0].equals(ticTacBoard[1][1]) &&
        ticTacBoard[1][1].equals(ticTacBoard[2][2]))
        {
            System.out.println(ticTacBoard[0][0]+ " is the winner!");
            return true;
        }
        else if(!ticTacBoard[0][1].equals(" ")
        && ticTacBoard[0][1].equals(ticTacBoard[1][1])
        && ticTacBoard[1][1].equals(ticTacBoard[2][1]))
        {
            System.out.println(ticTacBoard[0][1]+ " is the winner!");
            return true;
        }
        else if(!ticTacBoard[0][2].equals(" ")
        && ticTacBoard[0][2].equals(ticTacBoard[1][1])
        && ticTacBoard[1][1].equals(ticTacBoard[2][0]))
        {
            System.out.println(ticTacBoard[0][2]+ " is the winner!");
            return true;
        }
        else if(!ticTacBoard[0][2].equals(" ")
        && ticTacBoard[0][2].equals(ticTacBoard[1][2])
        && ticTacBoard[1][2].equals(ticTacBoard[2][2]))
        {
            System.out.println(ticTacBoard[0][2]+ " is the winner!");
            return true;
        }
        else if(!ticTacBoard[1][0].equals(" ")
        && ticTacBoard[1][0].equals(ticTacBoard[1][1])
        && ticTacBoard[1][1].equals(ticTacBoard[1][2]))
        {
            System.out.println(ticTacBoard[1][0]+ " is the winner!");
            return true;
        }
        else if(!ticTacBoard[2][0].equals(" ")
        && ticTacBoard[2][0].equals(ticTacBoard[2][1])
        && ticTacBoard[2][1].equals(ticTacBoard[2][2]))
        {
            System.out.println(ticTacBoard[2][0]+ " is the winner!");
            return true;
        }
        return false;
    }
}