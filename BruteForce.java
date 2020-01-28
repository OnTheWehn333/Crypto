import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class BruteForce {
    static List<String> vocabList = new ArrayList<String>();
    static List<String> message = new ArrayList<String>();
    static List<String> decryptedMessage = new ArrayList<String>();
    static List<Character> lowerAlphabet = new ArrayList<Character>();
    static List<Character> upperAlphabet = new ArrayList<Character>();
    static File vocab = new File("D:\\New Quick Access\\Documents\\Programming\\Crypto\\vocab.txt");
    static Scanner scanner;


    public static void main(String[] args) {
        String cipherText = args[0];
        int wordBegin = 0, wordLength = 0;
        while((wordBegin + wordLength) < cipherText.length()) {
            if (cipherText.charAt(wordBegin + wordLength) == ' ') {
                message.add(cipherText.substring(wordBegin, (wordBegin + wordLength)).toLowerCase());
                wordBegin = (wordBegin + wordLength) + 1;
                wordLength = 0;
            }
            wordLength++;
        }
            
        for(int i = 65; i <= 90; i++) {
            upperAlphabet.add(new Character((char) i));
        }
        for (int i = 97; i <= 122; i++) {
            lowerAlphabet.add(new Character((char) i));
        }

        try {
            scanner = new Scanner(vocab);
        } catch (Exception e) {
            //TODO: handle exception
        }
        //getting vocabulary words from the text file and adding them to a list. 
        while(scanner.hasNextLine()) {
            int beginning = 0, count = 0;
            String line = scanner.nextLine();
            while((beginning + count) < line.length()) {
                if (line.charAt(beginning + count) == ' ') {
                    vocabList.add(line.substring(beginning, (beginning + count)).toLowerCase());
                    beginning = (beginning + count) + 1;
                    count = 0;
                }
                count++;
            }
        }
        for(String string : message) {
            for(int i = 0; i < 26; i++) {
                String attemptedDecrypt = decrypt(string, i);
                for(String vocabString : vocabList) {
                    if(attemptedDecrypt.toLowerCase().equals(vocabString)) {
                        decryptedMessage.add(attemptedDecrypt);
                        break;
                    }
                }
            }
        }
        String finalMessage = "";
        for(String string : decryptedMessage) {
            finalMessage += string;
            finalMessage += ' ';
        }
        System.out.println(finalMessage);
    }

    public static String decrypt(String cipherText, int gap) {
        int count = 0;
        String plainText = "";
        boolean addedChar = false;

        //loop over whole string
        while (count < cipherText.length()) {
            addedChar = false;
            //check the current char in string for Upper Case letters
            for(char x : upperAlphabet) {
                if(cipherText.charAt(count) == x) {
                    int index = upperAlphabet.indexOf(x) - gap;
                    if (index < 0) {
                        index = 26 + index;
                    }

                    plainText += upperAlphabet.get(index % 26);
                    addedChar = true;
                    break;
                }
            }

            //check the current char in string for Lower Case letters
            for(char x : lowerAlphabet) {
                if(cipherText.charAt(count) == x) {
                    int index = lowerAlphabet.indexOf(x) - gap;
                    if (index < 0) {
                        index = 26 + index;
                    }

                    plainText += lowerAlphabet.get(index % 26);
                    addedChar = true;
                    break;
                }
            }
            if (cipherText.charAt(count) == ' ') {
                plainText += ' ';
                addedChar = true;
            }
            if(!addedChar) {
                plainText += cipherText.charAt(count);
            }
            count++;
        }
        return plainText;
    }

}