import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
/*
 * CSCI 221, Fall 2018, Programming HW 2
 * Alex Laughlin
 *
 * This program analyzes movie review data to determine if words have a 
 * negative or positive meaning. If a word is used more often in positive 
 * reviews, it is assumed that the word is positive, and vice versa.
 * Currently, it reads review data line by line - each line is a single review in teh form:
 * <integer rating of movie> <Verbal review - text supporting the rating> <newline>

 */

//This work is entirely my own

public class HW2 
{
    public static void main(String[] args) throws IOException 
    {
        // Create file instance for input
        File reviewFile = new File("movieReviews.txt");
        // This scanner instance will read from the aforementioned file, which
        // must be in your BlueJ project directory for it to find it.
        Scanner reviewScanner = new Scanner(reviewFile);
        // This scanner instance reads from the keyboard
        Scanner keyboard = new Scanner(System.in);
        Scanner menuScanner = new Scanner(System.in);
        Scanner optionOne = new Scanner(System.in);

        int reviewScore;
        int wordFreq = 0;
        float wordScore = 0;
        float fileScore = 0;
        float fileAvg;
        float wordAvg = 0;
        int numWords = 0;
        String reviewText;
        String word = "";
        String response;
        String filename;
        boolean cont = true;
        int menu = 0;

        System.out.println("What would you like to do?");
        System.out.println("1: Get the score of a word");
        System.out.println("2: Get the average score of words in a file (one word per line)");
        System.out.println("3: Find the highest / lowest scoring words in a file");
        System.out.println("4: Sort words from a file into positive.txt and negative.txt");
        System.out.println("5: Exit the program");
        menu = menuScanner.nextInt();
        while (menu != 5){
            if (menu == 1){
                System.out.println("Enter the word you want to score");
                word = optionOne.nextLine();
                wordScore = 0;
                wordFreq = 0;
                wordAvg = 0;
                reviewScanner = new Scanner(reviewFile);
                while(reviewScanner.hasNext())
                {
                    // Read the numeric movie rating
                    reviewScore = reviewScanner.nextInt();
                    // Read the text of the verbal review
                    reviewText = reviewScanner.nextLine();

                    // If the review contains the word the user entered, print the 
                    // rating and the review and continue; if it does not, process the
                    // next review. Your program will do more than this, of course!!
                    if(reviewText.contains(word))
                    {
                        wordScore = wordScore + reviewScore;
                        wordFreq = wordFreq +1;
                        //System.out.println("Score: "+reviewScore);
                        //System.out.println("Text: "+reviewText); 
                    }

                }
                if (wordFreq != 0){
                    wordAvg = wordScore / wordFreq;
                    System.out.println("The word " + word + " has a score of " + wordAvg);
                }
            }

            if (menu == 2){
                // Ask the user to enter a word
                //System.out.println("Enter a word.");
                //word = keyboard.nextLine();
                System.out.println("Enter the name of a file with words you want to score.");
                filename = keyboard.nextLine();
                File wordFile = new File(filename);
                Scanner wordScanner = new Scanner(wordFile);
                while (wordScanner.hasNext()){
                    word = wordScanner.nextLine();
                    wordScore = 0;
                    wordFreq = 0;
                    wordAvg = 0;
                    numWords = numWords + 1;
                    reviewScanner = new Scanner(reviewFile);
                    // Most of your code is added below this line, except possibly for variable declarations
                    // This loop iterates as long as there is text in the file      
                    while(reviewScanner.hasNext())
                    {
                        // Read the numeric movie rating
                        reviewScore = reviewScanner.nextInt();
                        // Read the text of the verbal review
                        reviewText = reviewScanner.nextLine();

                        // If the review contains the word the user entered, print the 
                        // rating and the review and continue; if it does not, process the
                        // next review. Your program will do more than this, of course!!
                        if(reviewText.contains(word))
                        {
                            wordScore = wordScore + reviewScore;
                            wordFreq = wordFreq +1;
                            //System.out.println("Score: "+reviewScore);
                            //System.out.println("Text: "+reviewText); 
                        }

                    }
                    if (wordFreq != 0){
                        wordAvg = wordScore / wordFreq;
                        fileScore = fileScore + wordAvg;

                    }
                }
                fileAvg = fileScore / numWords;
                //System.out.println(fileScore);
                //System.out.println(numWords);
                System.out.println("The average score for words in " + filename + " is " + fileAvg);
                //System.out.println("The average score for reviews containing the word " + word + " is " + wordAvg);
                if (fileAvg > 2.01){
                    System.out.println("The overall sentiment of " + filename + " is positive.");
                }
                else {
                    System.out.println("The overall sentiment of " + filename + " is negative.");
                }
            }
            if (menu == 3){
                Scanner optionThree = new Scanner(System.in);
                System.out.println("Enter the name of a file with words you want to find the highest and lowest score of.");
                filename = optionThree.nextLine();
                File thirdFile = new File(filename);
                String greatest=" ";
                String least = " ";
                float bigScore = 0;
                float smallScore = 90000;
                Scanner thirdScanner = new Scanner(thirdFile);
                while (thirdScanner.hasNext()){
                    word = thirdScanner.nextLine();
                    wordScore = 0;
                    wordFreq = 0;
                    wordAvg = 0;
                    reviewScanner = new Scanner(reviewFile);
                    // Most of your code is added below this line, except possibly for variable declarations
                    // This loop iterates as long as there is text in the file      
                    while(reviewScanner.hasNext())
                    {
                        // Read the numeric movie rating
                        reviewScore = reviewScanner.nextInt();
                        // Read the text of the verbal review
                        reviewText = reviewScanner.nextLine();

                        // If the review contains the word the user entered, print the 
                        // rating and the review and continue; if it does not, process the
                        // next review. Your program will do more than this, of course!!
                        if(reviewText.contains(word))
                        {
                            wordScore = wordScore + reviewScore;
                            wordFreq = wordFreq +1;
                            //System.out.println("Score: "+reviewScore);
                            //System.out.println("Text: "+reviewText); 
                        }

                    }
                    if (wordFreq != 0){
                        wordAvg = wordScore / wordFreq;
                        if (wordAvg > bigScore){
                            bigScore = wordAvg;
                            greatest = word;
                        }
                        if (wordAvg < smallScore){
                            smallScore = wordAvg;
                            least = word;
                        }

                    }
                }
                System.out.println("The most positive word, with a score of " + bigScore +" is " + greatest);
                System.out.println("The most negative word, with a score of " + smallScore + " is " + least);
            }
            if (menu == 4){
                Scanner optionFour = new Scanner(System.in);
                System.out.println("Enter the name of a file with words you want to find the highest and lowest score of.");
                filename = optionFour.nextLine();
                File fourthFile = new File(filename);
                String positives = "";
                String negatives = "";
                Scanner fourthScanner = new Scanner(fourthFile);
                while (fourthScanner.hasNext()){
                    word = fourthScanner.nextLine();
                    wordScore = 0;
                    wordFreq = 0;
                    wordAvg = 0;
                    reviewScanner = new Scanner(reviewFile);
                    // Most of your code is added below this line, except possibly for variable declarations
                    // This loop iterates as long as there is text in the file      
                    while(reviewScanner.hasNext())
                    {
                        // Read the numeric movie rating
                        reviewScore = reviewScanner.nextInt();
                        // Read the text of the verbal review
                        reviewText = reviewScanner.nextLine();

                        // If the review contains the word the user entered, print the 
                        // rating and the review and continue; if it does not, process the
                        // next review. Your program will do more than this, of course!!
                        if(reviewText.contains(word))
                        {
                            wordScore = wordScore + reviewScore;
                            wordFreq = wordFreq +1;
                            //System.out.println("Score: "+reviewScore);
                            //System.out.println("Text: "+reviewText); 
                        }

                    }
                    if (wordFreq != 0){
                        wordAvg = wordScore / wordFreq;
                        if (wordAvg > 2.1){
                            positives = positives + word + " ";
                        }
                        if (wordAvg < 1.9){
                            negatives = negatives + word + " ";
                        }

                    }

                }
                BufferedWriter addPos = new BufferedWriter(new FileWriter("positives.txt"));
                addPos.write(positives);

                addPos.close();
                
                BufferedWriter addNeg = new BufferedWriter(new FileWriter("negatives.txt"));
                addNeg.write(negatives);

                addNeg.close();
            }
            System.out.println("What would you like to do?");
            System.out.println("1: Get the score of a word");
            System.out.println("2: Get the average score of words in a file (one word per line)");
            System.out.println("3: Find the highest / lowest scoring words in a file");
            System.out.println("4: Sort words from a file into positive.txt and negative.txt");
            System.out.println("5: Exit the program");
            menu = menuScanner.nextInt();
        }
    }
}

