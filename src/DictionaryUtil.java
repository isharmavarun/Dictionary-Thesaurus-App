import java.util.HashMap;
import java.util.HashSet;

/**
 * A Util class to perform all the required operations on a Dictionary.
 * Operations include - addWord, addSynonym, lookupWord, lookupSynonyms.
 *
 * @author Varun Sharma
 * @version Feb 28, 2017
 */
public class DictionaryUtil
{

    //A Hash map of dictionary to hold all the words.
    private HashMap<String, DictionaryEntry> dictionary;


    /**
     * DictionaryUtil constructor.
     * Initializes the dictionary hash map.
     */
    public DictionaryUtil()
    {
        this.setDictionary(new HashMap<String, DictionaryEntry>());
    }

    /**
     * @return the dictionary
     */
    public HashMap<String, DictionaryEntry> getDictionary()
    {
        return dictionary;
    }


    /**
     * @param dictionary the dictionary to set
     */
    public void setDictionary(HashMap<String, DictionaryEntry> dictionary)
    {
        this.dictionary = dictionary;
    }


    /**
     * Adds a word to the dictionary hash map.
     *
     * @param word - word to be added.
     * @param definition - definition of the corresponding word.
     */
    public void addWord(String word, String definition)
    {
        DictionaryEntry wordDef = new DictionaryEntry(definition);
        this.getDictionary().put(word, wordDef);
    }

    /**
     * Adds a synonym to the word.
     * Handles the the commutative and transitive associations of different
     * words.
     *
     * @param word
     * @param synonym
     */
    public void addSynonym(String word, String synonym)
    {

        // Check if the word or the synonym already exists in the dictionary.
        if (this.getDictionary().containsKey(word)
            && this.getDictionary().containsKey(synonym))
        {
            // Get the word and synonym Entry.
            DictionaryEntry currentWord = this.getDictionary().get(word);
            DictionaryEntry newWord = this.getDictionary().get(synonym);

            // If neither Entry has any synonyms then add the synonyms to the
            // word and also to the synonym itself as well.
            if (currentWord.isEmpty() && newWord.isEmpty())
            {
                currentWord.addSynonym(word);
                currentWord.addSynonym(synonym);
                newWord.setSynonyms(currentWord);
            }
            // If the current word Entry has no synonyms, then add the word to
            // synonym's synonym set and then assign the synonym set to the
            // current word.
            else if (currentWord.isEmpty())
            {
                newWord.addSynonym(word);
                currentWord.setSynonyms(newWord);
            }
            // If the current word Entry has synonyms, then add the synonym to
            // the existing synonym list and assign it to the synonym's synonym
            // set.
            else if (newWord.isEmpty())
            {
                currentWord.addSynonym(synonym);
                newWord.setSynonyms(currentWord);
            }
            // If the current word and the synonym both have synonyms defined,
            // then add all the synonyms from the synonym set to the current
            // word synonym set.
            else
            {
                for (String syn : newWord.getSynonyms())
                {
                    currentWord.addSynonym(syn);
                    DictionaryEntry currentSyn = this.getDictionary().get(syn);
                    currentSyn.setSynonyms(currentWord);
                }
            }
        }
        // If both the word and the synonym do not exist in the dictionary.
        else
        {
            System.out.println(
                "The word and the synonym do not exist in the dictionary.");
        }
    }


    /**
     * Searches a word inside the dictionary hashmap. If found returns the
     * definition of the word.
     * @param word - word to be looked up in the hashmap.
     * @return - definition of the word or null.
     */
    public String lookupWord(String word)
    {
        if (this.getDictionary().containsKey(word))
        {
            DictionaryEntry wordDef = this.getDictionary().get(word);
            return wordDef.getDefinition();
        }
        else
        {
            return null;
        }
    }

    /**
     * Searches the synonyms of the word in the dictionary hashmap. If found
     * returns the hashset of synonyms of the word.
     * @param word - word to be looked up in the hashmap.
     * @return - hashset of synonyms or null.
     */
    public HashSet<String> lookupSynonyms(String word)
    {
        DictionaryEntry wordDef = this.getDictionary().get(word);
        if (wordDef == null || wordDef.isEmpty())
        {
            return null;
        }
        else
        {
            return wordDef.getSynonyms();
        }
    }

}
