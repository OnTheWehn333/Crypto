package Crypto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnhancedCeasarCipher {
    
    static List<Character> lowerAlphabet = new ArrayList<Character>();
    static List<Character> upperAlphabet = new ArrayList<Character>();
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int intInput = -1;
        boolean goodInput = false;

        for(int i = 65; i <= 90; i++) {
            upperAlphabet.add(new Character((char) i));
        }
        for (int i = 97; i <= 122; i++) {
            lowerAlphabet.add(new Character((char) i));
        }

        do {
            System.out.println("What would you like to do?: \n1. Encrypt\n2. Decyrpt\n");
            intInput = input.nextInt();
            String output = "";
            switch(intInput) {
                case 1:
                    output = encrypt(args[0], Integer.parseInt(args[1]));
                    goodInput = true;
                    break;
                case 2:
                    output = decrypt(args[0], Integer.parseInt(args[1]));
                    goodInput = true;
                    break;
                default:
                    System.out.println("Please Try again");
                    goodInput = false;
                    break;
            }

            System.out.println("Output: " + output);
        } while(!goodInput);
        input.close();
    }

    public static String encrypt(String plainText, int gap) {
        int count = 0;
        String cipherString = "";
        boolean addedChar = false;

        while (count < plainText.length()) {
            addedChar = false;
            for(char x : upperAlphabet) {
                if(plainText.charAt(count) == x) {
                    cipherString += upperAlphabet.get((upperAlphabet.indexOf(x) + gap) % 26);
                    addedChar = true;
                    break;
                }
            }
            for(char x : lowerAlphabet) {
                if(plainText.charAt(count) == x) {
                    cipherString += lowerAlphabet.get((lowerAlphabet.indexOf(x) + gap) % 26);
                    addedChar = true;
                    break;
                }
            }
            if (plainText.charAt(count) == ' ') {
                cipherString += ' ';
                addedChar = true;
            }
            
            if(!addedChar) {
                cipherString += plainText.charAt(count);
            }
            count++;
        }
        return cipherString;
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