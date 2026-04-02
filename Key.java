
public class Key
{
    public static int numKeys = 0; //static variable to count total keys made
    private int ID;                //ID for each key
    private String original;       //string of original characters
    private String code;           //string of encrypted characters
    private boolean isSet;         //boolean to check if key is already set or not

    // Default constructor
    public Key() {
        numKeys++;
        ID = numKeys; // give each key a ID number
        original = "";
        code = "";
        isSet = false;
    }

    // Checks if the given original and code strings are valid
    private boolean validKey(String o, String c) {
        // both o and c must have the same length
        if (o.length() != c.length())
            return false;

        // check that every character in o exists in c
                
        for (int i = 0; i < o.length(); i++) {
            char n = o.charAt(i);
            if (c.indexOf(n) == -1)
                return false;
        }


        // check that every character in c exists in o
        for (int i = 0; i < c.length(); i++) {
            char n = c.charAt(i);
            if (o.indexOf(n) == -1)
                return false;
        }

        return true;
    }

    // Set the key only if original and code string are valid
    public boolean setKey(String o, String c) {
        if (validKey(o, c)) {
            original = o;
            code = c;
            isSet = true;
            return true;
        } else {
            original = null;
            code = null;
            isSet = false;
            return false;
        }
    }

    // Displays the key in table format
    public void displayMe() {
        System.out.println("+-------+");
        System.out.println("| Key#" + ID + " |");

        if (!isSet)
            System.out.println("|not set|");
        else {
            System.out.println("|  set  |");
            System.out.println("|---+---|");
            System.out.println("| O | C |");
            System.out.println("|---+---|");

            // print original to code characters pairs
            for (int i = 0; i < original.length(); i++)
                System.out.println("| " + original.charAt(i) + " | " + code.charAt(i) + " |");
        }

        System.out.println("+-------+");
    }

    // Getters
    public boolean getIsSet() { return isSet; }
    public String getOriginal() { return original; }
    public String getCode() { return code; }
    public int getID() { return ID; }
}  //end class Key

