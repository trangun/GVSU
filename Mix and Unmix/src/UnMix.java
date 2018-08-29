
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**********************************************************************
 * The un-mix class that will take mix message as input and un-mix it
 * to get the original message.
 *
 * @author (Michelle Vu and Trang Nguyen)
 * @version 4/12/18
 *********************************************************************/

public class UnMix {
	
	/**The variable that allows us to use methods from TheLinkedList */
    private static TheLinkedList<Character> message;
    
    /** To get the commands in the saved file */
    private static String[] command;
    
    /******************************************************************
     * Main method that will read the mixed message and load the saved
     * file.
     *
     * @param args which is the mixed message you want to decrypt.
     *****************************************************************/
    
    public static void main (String[] args) {
        UnMix unMixer = new UnMix();
        message = new TheLinkedList<>();

        //add mix message to linked list
        for(int i = args[1].length() - 1; i >= 0; i--) {
            message.addAtTop(args[1].charAt(i));
        }

        System.out.println("Mixed message:\n" + 
        message.toStringNoSpace());
        unMixer.UnMixByFile(args[0]);

    }
    
    /******************************************************************
     * Using the file to decrypt the message that saved the commands.
     *
     * @param fileName saved file
     *****************************************************************/
    private void UnMixByFile(String fileName) {
        command = load(fileName);
        System.out.println("The Original message was:\n" + 
        decrypt(command));
    }

    /******************************************************************
     * Read the file and scan the commands inside.
     *
     * @param fileName the saved file that has commands
     * @return the commands as array
     *****************************************************************/
    
    private static String[] load (String fileName) {
        String[] commandsInArray;

        String commands = "";
        try {
            Scanner scanner = new Scanner(new File(fileName));
            commands = scanner.useDelimiter("\\Z").next();
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File is not found!");
        }
        //System.out.println("Commands:\n" + commands);
        commandsInArray = commands.split("\n");
        return commandsInArray;
    }


    /******************************************************************
     * Decrypts the mixed message according to the saved commands.
     *
     * @param args the array that has the whole commands.
     * @return String the original message.
     *****************************************************************/
    
    private String decrypt(String[] args) {
        for (int i = args.length-1; i >=0 ; i--) {
            String[] commands = commandsA(args[i]);
            if (!commands[0].equals(" ")) {
                switch (args[i].charAt(0)) {
                    case 'b':
                        try {
                            int x = Integer.parseInt(commands[1]);
                            message.delAt(x);
                        } catch (Exception e) {
                            System.out.println("Error2");
                        }
                        break;

                    
                    case 'r':
                        int a = Integer.parseInt(commands[2]);
                        if(commands[1].charAt(0)=='_') {
                            message.addAtPosition(' ', a);
                        } else {
                            message.addAtPosition
                            (commands[1].charAt(0), a);
                        }
                        break;

                    case 'y':
                        try {
                            int d1 = Integer.parseInt(commands[1]);
                            int d2 = Integer.parseInt(commands[2]);
                            message.swapLetters(d1, d2);
                        }catch(Exception e) {
                            System.out.println("Error3");
                        }
                        break;

                }
            }
        }
        return message.toStringNoSpace();
    }
    
    /******************************************************************
     * Splits the command to arrays
     * @param command string that you want to split
     * @return string array that you can use to decode
     *****************************************************************/

    private String[] commandsA (String command) {
        String[] temp;
        temp = command.split(" ");
        return temp;
    }
}
