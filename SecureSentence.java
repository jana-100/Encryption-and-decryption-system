
public class SecureSentence {
    private String sentence;  // store the actual sentence
    private Key keyUsed;      // the key used to encrypt or decrypt
    private boolean encrypted; // boolean to check if sentence is encrypted

    // default constructor
    public SecureSentence() {
        sentence = null;
        keyUsed = null;
        encrypted = false;
    }

    // set non encrypted sentence
    public void setSentence(String s) {
        sentence = s;
        keyUsed = null;
        encrypted = false;
    }

    // set already encrypted sentence with its key
    public void setSentence(String s, Key key) {
        sentence = s;
        keyUsed = key;
        encrypted = true;
    }

    // Encrypt the sentence using the given key
    public void encrypt(Key key) {
        if (!key.getIsSet()) {
            System.out.println("Key not set yet");
            return;
        }

        if (encrypted) {
            System.out.println("Sentence is already encrypted");
            return;
        }

        String sent = "";   //loop for encrypt
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            int c = key.getOriginal().indexOf(ch);
            if (c != -1)
                sent = sent + key.getCode().charAt(c);
            else
                sent = sent + ch;
        }

        sentence = sent;
        encrypted = true;
        keyUsed = key;
        System.out.println("Encrypted sentence: " + sentence);
    }

    // Decrypt the sentence by using the stored key
    public void decrypt() {
        if (!encrypted) {
            System.out.println("Sentence is not encrypted");
            return;
        }

        String sent = "";   //loop for decrypt
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            int c = keyUsed.getCode().indexOf(ch);
            if (c != -1)
                sent = sent + keyUsed.getOriginal().charAt(c);
            else
                sent = sent + ch;
        }

        sentence = sent;
        encrypted = false;
        System.out.println("Decrypted sentence: " + sentence);
    }

    // Displays the sentence info
    public void displayMe() {
        if (sentence == null)
            System.out.println("No sentence has been entered yet...");
        else {
            System.out.println("Sentence: " + sentence);
            System.out.println("Encrypted: " + encrypted);
            if (keyUsed != null) {
                System.out.println("Key Used:\n");
                keyUsed.displayMe();
            } else
                System.out.println("Key Used: None");
        }
    }

    public boolean isEncrypted() {
        return encrypted;
    }
} // end class SecureSentence

