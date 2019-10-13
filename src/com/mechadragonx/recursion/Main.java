package com.mechadragonx.recursion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Main
{
    private static HashSet<String> words = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException
    {
        readDictionary(".\\data\\dictionary_words_small.txt");
    }
    private static void readDictionary(String path) throws FileNotFoundException
    {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        if(file.exists())
        {
            while(scanner.hasNextLine())
            {
                words.add(scanner.nextLine());
            }
        }
    }
    private static void findAnagrams(String phrase)
    {
        phrase = tokenizeInput(phrase);
        HashSet<String> found = new HashSet<>();
    }
    private static String tokenizeInput(String input)
    {
        Scanner wordScan = new Scanner(input);
        StringBuilder builder = new StringBuilder();
        while(wordScan.hasNext())
        {
            builder.append(wordScan.next().toLowerCase());
        }
        return builder.toString();
    }
}
