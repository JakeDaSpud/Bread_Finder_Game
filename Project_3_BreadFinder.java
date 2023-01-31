
package A_Project_GD1A_Jake;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Started 03/12/2022
 * Finished 08/12/2022
 * @author Jake O'Reilly
 */
public class Project_3_BreadFinder {
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();
        File scoreFile = new File("BreadFinderScores.txt");
        FileWriter writer = new FileWriter("BreadFinderScores.txt", true);
        // link File object with a file on disk
        Scanner score = new Scanner(scoreFile);
        
        //BreadFinder, bread is hidden in a room somewhere, go around diff rooms and choose different locations to check, 6 checks only and then bread expires.
        //Rooms: Bedroom 1 -> Hall 2 -> (Kitchen 3, Bathroom 4)
        //Locations: Bedroom, 1 Under Sheets, 2 Behind Poster, 3 In Wardrobe
        //Hall, 1 On Windowsill, 2 Under Carpet, 3 Under Doormat
        //Kitchen, 1 In Microwave, 2 In Cupboard, 3 In Fruit Bowl
        //Bathroom, 1 In Toilet, 2 In Washing Machine, 3 In clothes basket
        
        //Variables
        String Name = null; //Username
        
        int BreadRoom = 0; //Room where bread is
        String BreadRoomName = null; //Name of room bread is 
        int BreadLocation = 0; //Location where bread is
        String BreadLocationName = null; //Name of location bread is
        int PlayerLocation = 1; //Player starts in Bedroom
        String RoomName = null; //Name of room
        
        int GuessesLeft = 6; //Location guesses left for user
        
        double StartTime = 0.00; //Start timestamp
        double EndTime = 0.00; //End timestamp
        double TotalTime = 0.00; //End - Start
        
        
        
        String Hint = null; //Hint message
        boolean UsedHint = false; //Hint indicator
        boolean FoundBread = false; //Win condition
        String Choice = null; //Player choice 
        
        //Previous Game Data
        String PastName = null; //Saved Name
        int PastGuessAmt = 0; //Saved Guess Amount
        double PastTime = 0.00; //Saved Game Length
        boolean PastHint = false; //Whether last game used a hint
        String PastRoom = null; //Saved Bread Room
        String PastLocation = null; //Saved Bread Location
         
        //Display previous game's data
        while (score.hasNext())
        {
            PastName = score.next(); // read name
            PastGuessAmt = score.nextInt(); //read guess count
            PastTime = score.nextDouble(); //read time
            PastHint = score.nextBoolean(); //read boolean
            PastRoom = score.next(); //read string
            PastLocation = score.next(); //read string part 1, a useless part
            PastLocation = score.next(); //skip string pt1 and get good part
        }
        
        System.out.printf("========================================\n"
                        + "Last Player: %s\n"
                        + "Guesses Taken: %d\n"
                        + "Time Taken: %.2f seconds\n"
                        + "Used Hint?: %s\n"
                        + "Room: %s\n"
                        + "Location: %s\n"
                        + "========================================\n\n", PastName, PastGuessAmt, PastTime, PastHint, PastRoom, PastLocation);
        
        //Input
        System.out.println("Welcome to Bread Finder by Jake O'Reilly!");
        System.out.println("RULES: You will have 6 GUESSES to find the bread. \nThere are 3 LOCATIONS to check in each of the 4 ROOMS. \nIf you do not get the bread WITHIN 60 SECONDS, it will EXPIRE!");
        System.out.println("Enter your name to begin:");
        Name = keyboard.next();
        
        BreadRoom = rand.nextInt(1, 5); //Generate room number, 1-4
        BreadLocation = rand.nextInt(1, 4); //Generate location in room number, 1-3
        
        switch (BreadRoom)
        {
            //Room 1, Bedroom
            case 1: //When BreadRoom is 1
                BreadRoomName = "Bedroom";
                Hint = "Last I heard, the bread was feeling sleepy...";
                switch (BreadLocation)
                {
                    //Location 1
                    case 1:
                        BreadLocationName = "Under Sheets";
                        
                    case 2:
                        BreadLocationName = "Behind Poster";
                        
                    case 3:
                        BreadLocationName = "In Wardrobe";
                        
                    default:
                        break;
                }
                break;

            //Room 2, Hall
            case 2: //When BreadRoom is 2
                BreadRoomName = "Hall";
                Hint = "Last I heard, the bread went to answer the front door... ";
                switch (BreadLocation)
                {
                    //Location 1
                    case 1:
                        BreadLocationName = "On Windowsill";
                        
                    case 2:
                        BreadLocationName = "Under Carpet";
                        
                    case 3:
                        BreadLocationName = "Under Doormat";
                        
                    default:
                        break;
                }
                break;

            //Room 3, Kitchen
            case 3: //When BreadRoom is 3
                BreadRoomName = "Kitchen";
                Hint = "Last I heard, the bread was in a completely normal room for bread...";
                switch (BreadLocation)
                {
                    //Location 1
                    case 1:
                        BreadLocationName = "In Microwave";
                        
                    case 2:
                        BreadLocationName = "In Cupboard";
                        
                    case 3:
                        BreadLocationName = "In FruitBowl";
                        
                    default:
                        break;
                }
                break;
                
            //Room 4, Bathroom
            case 4: //When BreadRoom is 4
                BreadRoomName = "Bathroom";
                Hint = "Last I heard, the bread needed to clean itself up...";
                switch (BreadLocation)
                {
                    //Location 1
                    case 1:
                        BreadLocationName = "In Toilet";
                        
                    case 2:
                        BreadLocationName = "In WashingMachine";
                        
                    case 3:
                        BreadLocationName = "In ClothesBasket";
                        
                    default:
                        break;
                }
                break;

            default:
                break;
        }
        
        //System.out.printf("(DEBUG) Bread in room no.%d (%s) in location %d\n\n", BreadRoom, BreadRoomName, BreadLocation );
        
        System.out.println("\nYou wake up and slowly rise from your sheets, you come to the realisation that... oh no! The bread expires today, quick, go look for it!");
        
        StartTime = System.currentTimeMillis(); //Starting time
        
        outer: for (int i = 1; i < 7; i++)
        {
            while (GuessesLeft >= 1 && FoundBread == false)
            {
                /*switch (PlayerLocation) //Change room name
                {
                    //Room 1, Bedroom
                    case 1: //When PlayerLocation is 1
                        RoomName = "Bedroom";
                        break;

                    //Room 2, Hall
                    case 2: //When PlayerLocation is 2
                        RoomName = "Hall";
                        break;

                    //Room 3, Kitchen
                    case 3: //When PlayerLocation is 3
                        RoomName = "Kitchen";
                        break;

                    //Room 4, Bathroom
                    case 4: //When PlayerLocation is 4
                        RoomName = "Bathroom";
                        break;

                    default:
                        break;
                }*/

                //Change room name
                if (PlayerLocation == 1) 
                {
                    RoomName = "Bedroom";
                }

                else if (PlayerLocation == 2)
                {
                    RoomName = "Hall";
                }

                else if (PlayerLocation == 3)
                {
                    RoomName = "Kitchen";
                }

                else if (PlayerLocation == 4)
                {
                    RoomName = "Bathroom";
                }

                System.out.printf("\nWhere to, %s? (Type [Hint] to use your hint! This will -4 guesses!)\n", Name);

                //============================================================================================================================================

                //Game Output
                if (PlayerLocation == 1) //BEDROOM
                {
                    System.out.printf("\n%s is in the Bedroom, Guesses left: %d\n"
                                    + "To Check, type: [Under Sheets] [Behind Poster] [In Wardrobe]\n"
                                    + "To Leave, type: [Hall]\n", Name, GuessesLeft);

                    if (i == 1)
                    {
                        keyboard.nextLine();
                        Choice = keyboard.nextLine();
                    }

                    else
                    {
                        Choice = keyboard.next();
                    }
                    
                    EndTime = System.currentTimeMillis();
                    if (((EndTime - StartTime)/1000) > 60.00)
                    {
                        FoundBread = false;
                        break outer;
                    }

                    if (Choice.toUpperCase().equalsIgnoreCase("UNDER SHEETS"))
                    {
                        if (BreadRoom == 1 && BreadLocation == 1) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread under the sheets!\n");
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("BEHIND POSTER"))
                    {
                        if (BreadRoom == 1 && BreadLocation == 2) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread behind the poster!\n");
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("IN WARDROBE"))
                    {
                        if (BreadRoom == 1 && BreadLocation == 3) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread in the wardrobe!\n");
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("HALL"))
                    {
                        PlayerLocation = 2;
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("HINT"))
                    {
                        System.out.printf("\nHint: %s", Hint);
                        GuessesLeft = GuessesLeft - 4;
                        UsedHint = true;
                    }
                }

                //============================================================================================================================================

                if (PlayerLocation == 2) //HALL
                {
                    System.out.printf("\n%s is in the Hall, Guesses left: %d\n"
                                    + "To Check, type: [On Windowsill] [Under Carpet] [Under Doormat]\n"
                                    + "To Leave, type: [Bedroom] [Kitchen] [Bathroom]\n", Name, GuessesLeft);
                    
                    Choice = keyboard.nextLine();
                    
                    EndTime = System.currentTimeMillis();
                    if (((EndTime - StartTime)/1000) > 60.00)
                    {
                        FoundBread = false;
                        break outer;
                    }

                    if (Choice.toUpperCase().equalsIgnoreCase("ON WINDOWSILL"))
                    {
                        if (BreadRoom == 2 && BreadLocation == 1) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread on the windowsill!\n");
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("UNDER CARPET"))
                    {
                        if (BreadRoom == 2 && BreadLocation == 2) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread under the carpet!\n");
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("UNDER DOORMAT"))
                    {
                        if (BreadRoom == 2 && BreadLocation == 3) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread under the doormat!\n");
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("BEDROOM")) //Go to Bedroom
                    {
                        PlayerLocation = 1;
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("KITCHEN")) //Go to Kitchen
                    {
                        PlayerLocation = 3;
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("BATHROOM")) //Go to Bathroom
                    {
                        PlayerLocation = 4;
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("HINT"))
                    {
                        System.out.printf("\nHint: %s", Hint);
                        GuessesLeft = GuessesLeft - 4;
                        UsedHint = true;
                    }
                }

                //============================================================================================================================================

                if (PlayerLocation == 3) //KITCHEN
                {
                    System.out.printf("\n%s is in the Kitchen, Guesses left: %d\n"
                                    + "To Check, type: [In Microwave] [In Cupboard] [In FruitBowl]\n"
                                    + "To Leave, type: [Hall]\n", Name, GuessesLeft);

                    Choice = keyboard.nextLine();
                    
                    EndTime = System.currentTimeMillis();
                    if (((EndTime - StartTime)/1000) > 60.00)
                    {
                        FoundBread = false;
                        break outer;
                    }

                    if (Choice.toUpperCase().equalsIgnoreCase("IN MICROWAVE"))
                    {
                        if (BreadRoom == 3 && BreadLocation == 1) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread in the microwave!\n");
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("IN CUPBOARD"))
                    {
                        if (BreadRoom == 3 && BreadLocation == 2) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.printf("Why would there be bread in the cupboard? Silly %s!\n", Name);
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("IN FRUITBOWL"))
                    {
                        if (BreadRoom == 3 && BreadLocation == 3) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread in the fruit bowl!\n");
                            GuessesLeft--;
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("HALL")) //Go to Kitchen
                    {
                        PlayerLocation = 2;
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("HINT"))
                    {
                        System.out.printf("\nHint: %s", Hint);
                        GuessesLeft = GuessesLeft - 4;
                        UsedHint = true;
                    }
                }

                //============================================================================================================================================

                if (PlayerLocation == 4) //TOILET
                {
                    System.out.printf("\n%s is in the Bathroom, Guesses left: %d\n"
                                    + "To Check, type: [In Toilet] [In WashingMachine] [In ClothesBasket]\n"
                                    + "To Leave, type: [Hall]\n", Name, GuessesLeft);

                    Choice = keyboard.nextLine();
                    
                    EndTime = System.currentTimeMillis();
                    if (((EndTime - StartTime)/1000) > 60.00)
                    {
                        FoundBread = false;
                        break outer;
                    }

                    if (Choice.toUpperCase().equalsIgnoreCase("IN TOILET"))
                    {
                        if (BreadRoom == 4 && BreadLocation == 1) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread in the toilet!\n");
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("IN WASHINGMACHINE"))
                    {
                        if (BreadRoom == 4 && BreadLocation == 2) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread in the washing machine!\n");
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("IN CLOTHESBASKET"))
                    {
                        if (BreadRoom == 4 && BreadLocation == 3) //FOUND BREAD!!!!!!!!!
                        {
                            FoundBread = true;
                            break outer;
                        }

                        else
                        {
                            System.out.println("Nope, no bread in the clothes basket!\n");
                        }
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("HALL")) //Go to Kitchen
                    {
                        PlayerLocation = 2;
                    }

                    else if (Choice.toUpperCase().equalsIgnoreCase("HINT"))
                    {
                        System.out.printf("\nHint: %s", Hint);
                        GuessesLeft = GuessesLeft - 4;
                        UsedHint = true;
                    }
                }
            }
        }
        
        EndTime = System.currentTimeMillis(); //End time
        
        //Calculations
        TotalTime = EndTime - StartTime; //Calc time taken
        
        //Final Output
        if (FoundBread == true)
        {
            System.out.printf("\n========================================\n"
                            + "You found the bread %s!\n"
                            + "Guesses: %d\n"
                            + "Time taken: %.2f seconds\n"
                            + "Used Hint?: %s\n"
                            + "Room: %s\n"
                            + "Location: %s\n"
                            + "========================================\n", Name, 7-GuessesLeft, TotalTime/1000, UsedHint, BreadRoomName, BreadLocationName);
            //save score to .txt
            GuessesLeft = 7-GuessesLeft;
            writer.write(Name+" "+String.valueOf(GuessesLeft)+" "+TotalTime/1000+" "+String.valueOf(UsedHint)+" "+BreadRoomName+" "+BreadLocationName);
            writer.write("\n");
            writer.close();
        }
        
        else
        {
            System.out.printf("\n========================================\n"
                            + "Sorry %s, "
                            + "you didn't find the bread\n"
                            + "and now it's expired!\n"
                            + "Time taken: %.2f seconds\n"
                            + "Room: %s\n"
                            + "Location: %s\n"
                            + "========================================\n", Name, TotalTime/1000, BreadRoomName, BreadLocationName);
        }
    }
}
