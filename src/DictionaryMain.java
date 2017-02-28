import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 *  Main class for the Dictionary/Thesaurus application.
 *  The main method accepts one argument which is the input file name.
 *  The input file contains a list of operations that can be performed on the
 *  Dictionary, namely - add a word, add a synonym, lookup word, and lookup
 *  synonym.
 *
 *  @author Varun Sharma
 *  @version Feb 28, 2017
 */
public class DictionaryMain
{

    //Scanner object to read input file.
    private static Scanner in;

    /**
     * Main method. Handles incorrect arguments, file IO errors.
     * Also works as a driver to the Dictionary application.
     * @param args - accepts single argument which is the input file name.
     */
    public static void main(String[] args)
    {

        if (args.length == 0 || args.length > 1)
        {
            System.out.println(
                "Error: Incorrect amount of arguments. Please specify only the "
                + "file name with the file path as the argument");
        }
        else
        {
            try
            {
                in = new Scanner(new File(args[0]));

                //Initialize the Dictionary application
                DictionaryUtil util = new DictionaryUtil();

                while (in.hasNextLine())
                {
                    String line = in.nextLine();
                    String[] input = line.split(":");
                    switch (input[0])
                    {
                        //Adds a word into the dictionary
                        case "addWord":
                            util.addWord(input[1], input[2]);
                            break;

                        //Adds a synonym to an existing word in the dictionary.
                        case "addSynonym":
                            util.addSynonym(input[1], input[2]);
                            break;

                        //Looks up a word in the dictionary.
                        case "lookupWord":
                            String word = util.lookupWord(input[1]);
                            if (word != null)
                            {
                                System.out.println(input[1] + ":" + word);
                            }
                            else
                            {
                                System.out.println(
                                    input[1] + " not found in the dictionary.");
                            }
                            break;

                        //Looks up synonyms of a word in the dictionary.
                        case "lookupSynonyms":
                            HashSet<String> synonyms =
                                util.lookupSynonyms(input[1]);
                            if (synonyms != null && synonyms.size() != 0)
                            {
                                System.out.print(input[1] + ":");
                                String[] words = new String[synonyms.size()];
                                synonyms.toArray(words);
                                for (int i = 0; i < words.length; i++)
                                {
                                    if (!words[i].equals(input[1]))
                                    {
                                        System.out.print(words[i] + " ");
                                    }
                                }
                                System.out.println();
                            }
                            else
                            {
                                System.out.println(
                                    "No synonyms found for " + input[1]
                                        + " in the dictionary.");
                            }
                            break;

                        //Invalid input scenario.
                        default:
                            System.out.println("Error: Invalid input!");
                            break;
                    }
                }

            }
            catch (FileNotFoundException e)
            {
                System.out
                    .println("Error: Please check the file path or the "
                        + "filename.");
                e.printStackTrace();
            }
        }

    }

}
