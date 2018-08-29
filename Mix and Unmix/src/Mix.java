
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**********************************************************************
 * The mix class that will take in your inputs and mix it up for the 
 * unmixer to read.
 * @author (Michelle Vu and Trang Nguyen)
 * @version(4/12/18)
 *
 *********************************************************************/
public class Mix {
	
	/**The variable that allows us to use methods from TheLinkedList */
    private static TheLinkedList<Character> message;
    
    /**An array list that takes in the commands for the unmixer */
    private ArrayList<String> commands = new ArrayList<>();
    
    /******************************************************************
     * Main method that will take in your input for your message
     * @param args which is the message you want to scramble.
     *****************************************************************/
    
    public static void main(String [] args) {

        Mix mixer = new Mix();
        message = new TheLinkedList<>();
        
        for(int i = args.length - 1; i >= 0; i--) {
            for(int j = args[i].length()-1; j >= 0; j--)
                message.addAtTop(args[i].charAt(j));
        }
        System.out.print ("Message:\n");
        mixer.runMessage();
    }
    
    /******************************************************************
     * Prints the message in the format of index on top of the 
     * message.
     *****************************************************************/
    
    private void printMessage() {
        for (int i = 0; i < message.getLen(); i++)
            System.out.format ("%3d", i);
        System.out.format ("\n");
        System.out.print("  " + message);
    }
    /******************************************************************
     * This runs the message, allowing the user to input commands
     * that will mix the message up.
     *****************************************************************/
    
    private void runMessage() {
        Boolean quit = false;
        Scanner scan = new Scanner(System.in);
        while(!quit) {
            printMessage();
            System.out.println("\n" + "Insert Commands: ");

            String userMessage = scan.nextLine();
            String [] split = userMessage.split(" ");
            try {
                if(split[0].length() == 1) {
                    switch(split[0].charAt(0)) {
                        //Prints out help
                        case 'H': help();
                            break;
                        //deletes all spaces
                        case 's':
                        	try {             		
                        	commands.add(message.deleteSpace());
                        	}catch(Exception e) {
                        		System.out.println("Error, try again");
                        	}
                        	break;
                        //adds a string to the location you input
                        case 'b':
                            try {
                                Integer.parseInt(split[2]);
                                //checks if you're adding only one character
                                if(split[1].length() == 1) {
                                	if(split[1].charAt(0) == '_') {
                                		message.addAtPosition(' ',
                                                Integer.parseInt
                                                (split[2]));
                                        commands.add("b "+ 
                                                Integer.parseInt
                                                (split[2]) + "\n");
                                	}else {
                                    message.addAtPosition
                                    (split[1].charAt(0),
                                            Integer.parseInt
                                            (split[2]));
                                    commands.add("b "+ 
                                            Integer.parseInt
                                            (split[2]) + "\n");
                                	}
                                }
                                //if you're adding a string
                                else {
                                    for(int i = split[1].length() - 1
                                    		; i >= 0; i--) {
                                    	if(split[1].charAt(i) == '_') {
                                    		message.addAtPosition(' ',
                                                    Integer.parseInt
                                                    (split[2]));
                                            commands.add("b "+ 
                                                    Integer.parseInt
                                                    (split[2]) + "\n");
                                    	}else {
                                        message.addAtPosition
                                        (split[1].charAt(i),
                                                Integer.parseInt(split[2]));
                                        commands.add("b "+ 
                                                Integer.parseInt
                                                (split[2]) + "\n");
                                    	}
                                    }
                                }

                            }catch(NumberFormatException e) {
                                System.out.println("Error, try again");

                            } break;

                        //removes a range of characters
                        case 'r':
                            try {
                                int r1 = Integer.parseInt(split[1]);
                                int r2 = Integer.parseInt(split[2]);
                              
                                commands.add(message.removeRange(r1, r2));
                            }catch(Exception e) {
                                System.out.println("Error, try again");
                            } break;

                    
                        //swaps letter
                        case 'y':
                            try {
                                int d1 = Integer.parseInt(split[1]);
                                int d2 = Integer.parseInt(split[2]);
                                message.swapLetters(d1,d2);
                                commands.add("y "+ d1 +" " +d2 +"\n");

                            } catch(NumberFormatException e) {
                                System.out.println("Error, try again");
                            }
                            break;
                        //deletes all vowels
                        case 'm':
                        	try {
                        		String x = message.deleteVowels();
                        		//System.out.println(x);
                        		commands.add(x);
                        	}catch(Exception e) {
                        		System.out.println("Error, try again");
                        	}
                        break;
                        //copies
                        case 'c':
                            try {
                                int c1 = Integer.parseInt(split[1]);
                                int c2 = Integer.parseInt(split[2]);
                                int c3 = Integer.parseInt(split[3]);
                               message.copy(c1,c2,c3);

                           }catch(Exception e) {
                                System.out.println("Error, try again");
                            }
                            break;
                            //pastes
                        case 'p':
                            try {
                                int p1 = Integer.parseInt(split[1]);
                                int p2 = Integer.parseInt(split[2]);
                                commands.add(message.paste(p1,p2));

                            } catch(Exception e) {
                                System.out.println("Error, try again");
                            }
                            break;
                            //cuts
                        case 'x':
                            try {
                                int f1 = Integer.parseInt(split[1]);
                                int f2 = Integer.parseInt(split[2]);
                                int f3 = Integer.parseInt(split[3]);
                                commands.add(message.cut(f1,f2,f3));

                            } catch(Exception e) {
                                System.out.println("Error, try again");

                            }
                            break;
                        //quits
                        case 'Q':
                        	try {
                            System.out.println("Final Mixed Message: " );
                            System.out.println("\"" + message.toStringNoSpace());
                            save(split[1]);
                            quit = true;
                            commands.add(userMessage + "\n");
                        	}catch(Exception e) {
                        		System.out.println("Error, try again");
                        	}
                            break;
                    }
                }
            }catch(Exception e) {
                System.out.println("Error");
            }
        }
    }
    
    /******************************************************************
     * Creates a help list of commands
     *****************************************************************/
    
    private void help() {
        System.out.println("List of Commands: " + "\n" +
                "Insert String 's' at position # \t b s # " + "\n"
                + "Remove all characters in a range from # to * \t " +
                "r # *" + "\nDeletes all spaces # \t s" + "\nRemoves "
                		+ "all numbers \t a" + "\nSwap two letters at "
                				+ "position & and * \t y & *" 
                		+"\nDeletes all vowels \t m" +"\nCopies"
                			+ " to  a clipboard % from # to * \t c # * %"
                		+"\nCuts into a clipboard % from # to * \t x # * %"
                			+"\nPastes from clipboard % "
                			+ "at position # \t p # %"
                + "\nQuit and finish the message with savefile"
                + " \t Q savefile");
    }

    /******************************************************************
     * Saves the command into a file name.
     * @param fileName stores the commands for the unmix
     *****************************************************************/
    
    public void save(String fileName){
        try {
            // new file writer with user defined name
            FileWriter saveFile = new FileWriter(fileName);

            // writes each command on separate line in file
            for(int i = 0; i < commands.size(); i++)
                saveFile.write(commands.get(i) + "");
            saveFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
