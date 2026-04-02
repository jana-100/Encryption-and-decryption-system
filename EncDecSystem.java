

import java.util.Scanner;


public class EncDecSystem {
    static int systemPin;               
    static Scanner read = new Scanner(System.in); 

    public static void main(String[] args) {
        System.out.println("                   ENCRYPTION/DECRYPTION SYSTEM                     ");
        System.out.println();

        setPin(); // ask the user to create a new PIN at the start

        
        Key key1 = new Key();
        Key key2 = new Key();
        Key key3 = new Key();

        
        SecureSentence sentence1 = new SecureSentence();
        SecureSentence sentence2 = new SecureSentence();
        SecureSentence current = null; 

        int choice;
        do {
            choice = menu(); // display the menu and get choice

            switch (choice) {
                case 1:
                    // Set-change a key
                    System.out.print("Enter pin: ");
                    int pin_valid = read.nextInt();
                    if (!pinMatches(pin_valid)) {
                        System.out.println("Invalid pin");
                        break;
                    }
                         //ask the user for a key to use and chek if it's valid choice
                    System.out.print("Choose a key to work with (1 or 2 or 3): ");
                    int keyNum = read.nextInt();
                    if (keyNum < 1 || keyNum > 3) {
                        System.out.println("\nInvalid key number ");
                        break;
                    }
                        // ask the user to enter the key origin and code
                    System.out.println("Enter origin characters: ");
                    read.nextLine(); // clear input buffer
                    String origin = read.nextLine();
                    System.out.println("Enter code characters: ");
                    String code = read.nextLine();
                     //set the selected key using the entered origin and code
                    if (keyNum == 1 && key1.setKey(origin, code))
                        System.out.println("\nKey set successfully.. ");
                    else if (keyNum == 2 && key2.setKey(origin, code))
                        System.out.println("\nKey set successfully..");
                    else if (keyNum == 3 && key3.setKey(origin, code))
                        System.out.println("\nKey set successfully..");
                    else
                        System.out.println("\nInvalid key");
                    break;

                case 2:
                    // Display all keys
                    System.out.print("Enter pin: ");
                    pin_valid = read.nextInt();
                    if (!pinMatches(pin_valid)) {
                        System.out.println("Invalid pin");
                        break;
                    }

                    System.out.println("Number of keys: " + Key.numKeys);
                    System.out.println();
                    key1.displayMe();
                    key2.displayMe();
                    key3.displayMe();
                    break;

                case 3:
                    // Select a securSentence 
                    System.out.print("Select a sentence ( 1 or 2 ): ");
                    int sentence_num = read.nextInt();
                    if (sentence_num == 1) {
                        current = sentence1;
                        System.out.println("Sentence 1 selected");
                    } else if (sentence_num == 2) {
                        current = sentence2;
                        System.out.println("Sentence 2 selected");
                    } else
                        System.out.println("Invalid selection");
                    break;

                case 4:
                    // Enter a sentence and check if it's encrypted
                    if (current == null) {
                        System.out.println("No sentence selected ");
                        break;
                    }

                    System.out.println("Enter Sentence: ");
                    read.nextLine();
                    String s = read.nextLine();

                    System.out.println("Is the Sentence already encrypted? (y/n): ");
                    String ansr = read.next();

                    if (ansr.equalsIgnoreCase("y")) {
                        System.out.println("Enter key ID used for encryption: ");
                        int keyID = read.nextInt();

                        if (key1.getID() == keyID && key1.getIsSet())
                            current.setSentence(s, key1);
                        else if (key2.getID() == keyID && key2.getIsSet())
                            current.setSentence(s, key2);
                        else if (key3.getID() == keyID && key3.getIsSet())
                            current.setSentence(s, key3);
                        else
                            System.out.println("Invalid key ID or key not set ");
                    } else
                        current.setSentence(s);
                    break;

                case 5:
                    // Display selected sentence
                    if (current == null) {
                        System.out.println("No sentence selected ");
                        break;
                    }
                    current.displayMe();
                    break;

                case 6:
                    // Encrypt the selected sentence
                    if (current == null) {
                        System.out.println("No sentence selected");
                        break;
                    }

                    System.out.print("Enter key number (1-3): ");
                    keyNum = read.nextInt();
                    if (keyNum < 1 || keyNum > 3) {
                        System.out.println("Invalid key number ");
                        break;
                    }

                    if (keyNum == 1)
                        current.encrypt(key1);
                    else if (keyNum == 2)
                        current.encrypt(key2);
                    else
                        current.encrypt(key3);
                    break;

                case 7:
                    // Decrypt the selected sentence
                    if (current == null) {
                        System.out.println("No sentence selected ");
                        break;
                    }
                    current.decrypt();
                    break;

                case 8:
                    // Display all secureSentences
                    if (sentence1 == current)
                        System.out.println("\nSentence 1 is current ");
                    else {
                        System.out.println("\nSentence 1 : ");
                        sentence1.displayMe();
                    }

                    System.out.println();

                    if (sentence2 == current)
                        System.out.println("\nSentence 2 is current ");
                    else {
                        System.out.println("\nSentence 2 : ");
                        sentence2.displayMe();
                    }
                    break;

                case 9:
                    // Exit the system
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 9); // loop until user choses to exit '9'
    }  // end main

    // ================= Helper methods =================

    // Method for setting a new system PIN
    public static void setPin() {
        boolean pinSet;
        String pin;

        do {
            System.out.println("* Make sure your pin does not start with \"0\" and has 4 digits ONLY * ");
            System.out.print("Set a new pin made of 4 digits: ");
            pin = read.next();
            pinSet = true;

            // check length
            if (pin.length() != 4 || pin.charAt(0) == '0')
                pinSet = false;

            // check that all characters are digits
            for (int i = 0; i < pin.length(); i++) {
                if (pin.charAt(i) < '0' || pin.charAt(i) > '9')
                    pinSet = false;
            }

            if (!pinSet) {
                System.out.println();
                System.out.println("Invalid pin format, try again...");
                System.out.println();
            }

        } while (!pinSet);

        systemPin = Integer.parseInt(pin);
        System.out.println("\nPin set successfully...");
    }

    // Check if entered PIN matche system PIN
    public static boolean pinMatches(int pin) {
        return pin == systemPin;
    }

    // to displays menu and return the user choice
    private static int menu() {
        int choice;
        System.out.println("\n**************************** Menu *******************************");
        System.out.println("1. Set/Change a key");
        System.out.println("2. Display all keys");
        System.out.println("3. Select a sentence to process (1 or 2)");
        System.out.println("4. Enter selected sentence ");
        System.out.println("5. Display selected sentence ");
        System.out.println("6. Encrypt selected sentence");
        System.out.println("7. Decrypt selected sentence and display it");
        System.out.println("8. Display all sentences");
        System.out.println("9. Exit the system");
        System.out.println("******************************************************************");
        System.out.println();
        System.out.print("Enter your choice: ");
        choice = read.nextInt();
        return choice;
    }
} //end class pba_phase_2
