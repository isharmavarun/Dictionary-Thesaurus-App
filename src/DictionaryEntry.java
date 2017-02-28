import java.util.HashSet;

/**
 *  A DictionaryEntry object holds the definition and the synonyms of a word.
 *
 *  @author Varun Sharma
 *  @version Feb 28, 2017
 */
public class DictionaryEntry
{
    //String to hold the definition of the word.
    private String definition;

    //A Set to hold all the synonyms of the word.
    private HashSet<String> synonyms;

    /**
     * Constructor for the Entry class.
     * Sets the definition of the word and initializes the set of synonyms.
     * @param definition
     */
    public DictionaryEntry(String definition) {
        this.definition = definition;
        this.synonyms = new HashSet<String>();
    }

    /**
     * Add synonyms to existing set of synonyms.
     * @param synonym - new synonym to be added into the set.
     */
    public void addSynonym(String synonym) {
        this.synonyms.add(synonym);
    }

    /**
     * Get the definition of the word.
     * @return current definition of the word.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Set the new definition of the word.
     * @param newDefinition - new definition to be set for a word.
     */
    public void setDefinition(String newDefinition) {
        this.definition = newDefinition;
    }

    /**
     * Get the set of synonyms of a word.
     * @return current set of synonyms for a word.
     */
    public HashSet<String> getSynonyms() {
        return synonyms;
    }

    /**
     * Set the new synonyms of a word.
     * @param e - new set of synonyms to be set for a word.
     */
    public void setSynonyms(DictionaryEntry e) {
        this.synonyms = e.getSynonyms();
    }

    /**
     * Check if the set of synonyms is empty or not.
     * @return true/false depending on the size of synonym set.
     */
    public boolean isEmpty() {
        return this.synonyms.size() == 0;
    }
}
