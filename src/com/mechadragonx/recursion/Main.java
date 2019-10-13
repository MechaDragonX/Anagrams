package com.mechadragonx.recursion;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main
{
    private static HashSet<String> dictionary = new HashSet<>();
    private static Iterator<String> dictIterator = dictionary.iterator();
    private static HashSet<String> found = new HashSet<>();
    private static ArrayList<Character> used = new ArrayList<>();
    private static ArrayList<Character> currentWordList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException
    {
        readDictionary(".\\data\\dictionary_words_small.txt");
        ArrayList<Character> phraseList = phraseToList(tokenizeInput("Clint Eastwood"));
        findAnagramsRecursive(phraseList);
    }
    private static void readDictionary(String path) throws FileNotFoundException
    {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        if(file.exists())
        {
            while(scanner.hasNextLine())
            {
                dictionary.add(scanner.nextLine());
            }
        }
    }
    private static HashSet<String> findAnagramsRecursive(ArrayList<Character> phraseList)
    {
        String dictWord;
        int matches = 0;
        if(dictIterator.hasNext())
        {
            dictWord = dictIterator.next();
            if(!found.contains(dictWord))
            {
                for(int i = 0; i < dictWord.length(); i++)
                {
                    currentWordList.add(phraseList.get(i));
                }
                for(char dictLetter : dictWord.toCharArray())
                {
                    for(char currentLetter : currentWordList)
                    {
                        if(dictLetter == currentLetter)
                        {
                            matches++;
                        }
                    }
                }
                if(matches == dictWord.length())
                {
                    found.add(dictWord);
                    used.addAll(currentWordList);
                }
                else
                {
                    currentWordList.clear();
                    matches = 0;
                    phraseList.removeAll(currentWordList);
                }
            }
            findAnagramsRecursive(phraseList);
        }
        return found;
    }
    private static ArrayList<Character> phraseToList(String phrase)
    {
        ArrayList<Character> list = new ArrayList<>();
        for(char letter : phrase.toCharArray())
        {
            list.add(letter);
        }
        return list;
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
    private static void findAnagramsIterative(String phrase) // Uses slightly wrong logic since it has to make sure each word it searches is the same length
    {
        phrase = tokenizeInput(phrase);
        HashSet<String> found = new HashSet<>();
        Scanner wordScan = new Scanner(phrase);
        String currentWord = "";
        int matches = 0;
        while(wordScan.hasNext())
        {
            for(String word : dictionary)
            {
                if(!found.contains(word) && word.length() == currentWord.length())
                {
                    for(int i = 0; i < word.length(); i++)
                    {
                        for(int j = 0; j < currentWord.length(); j++)
                        {
                            if(word.charAt(i) == currentWord.charAt(j))
                            {
                                matches++;
                            }
                        }
                    }
                    if(matches == word.length())
                    {
                        found.add(word);
                    }
                }
            }
        }
    }
}
