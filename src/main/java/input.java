import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
public class input {
    public static void main(String[] args) throws IOException {
        String user_input;
        int sentence_count = 0;
        int word_count = 0;
        int verb_count = 0;
        int noun_count = 0;
        int pronoun_count = 0;
        int adjective_count = 0;
        int adverb_count = 0;
        int preposition_count = 0;
        int conjunction_count = 0;
        int interjection_count = 0;

        //Accepts and then displays user input
        Scanner filereader = new Scanner(new File("News1.txt"));
        String article = "";
        while(filereader.hasNext()){
            article +=  filereader.nextLine();
        }
        /* while(filereader.hasNext()){
            String  line = filereader.nextLine();
            //Splits user input into sentences and prints each sentence
            String[] sentences = line.split("\\.\\ ");
            for (String s : sentences)
                System.out.println(s);
            //Splits user input into words and prints each word
            String[] words = line.split(" ");
            for (String s : words)
                System.out.println(s);

            //Counts the number of sentences and prints it
            for(int i = 0; i < sentences.length; i++){
                sentence_count++;
            }
            System.out.println("There are " + sentence_count + " sentences in this text.");

            //Counts the number of words and prints it
            for(int i = 0; i < words.length; i++){
                word_count++;
            }
            System.out.println("There are " + word_count + " words in this text.");

        }
        */
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = new CoreDocument(article);
        // annnotate the document
        pipeline.annotate(document);
        sentence_count = document.sentences().size();



        for (CoreLabel tok : document.tokens()){
            System.out.println(String.format("%s\t%s", tok.word(), tok.tag()));
            word_count++;
            if(tok.tag().matches("VBD") ||
                    tok.tag().matches("VBG") ||
                    tok.tag().matches("VB"))
                verb_count++;
            if(tok.tag().matches("NN") ||
                    tok.tag().matches("NNS") ||
                    tok.tag().matches("NNP") ||
                    tok.tag().matches("NNPS"))
                noun_count++;
            if(tok.tag().matches("PRP") ||
                    tok.tag().matches("PRP$"))
                pronoun_count++;
            if(tok.tag().matches("JJ") ||
                    tok.tag().matches("JJR") ||
                    tok.tag().matches("JJS"))
                adjective_count++;
            if(tok.tag().matches("RB") ||
                    tok.tag().matches("RBR") ||
                    tok.tag().matches("RBS"))
                adverb_count++;
            if(tok.tag().matches("IN"))
                preposition_count++;
            if(tok.tag().matches("CC"))
                conjunction_count++;
            if(tok.tag().matches("UH"))
                interjection_count++;

        }
        System.out.println("Sentences: " + sentence_count);
        System.out.println("Words: " + word_count);
        System.out.println("Nouns: " + noun_count);
        System.out.println("Verbs: " + verb_count);
        System.out.println("Pronouns: " + pronoun_count);
        System.out.println("Adjectives: " + adjective_count);
        System.out.println("Adverbs: " + adverb_count);
        System.out.println("Prepositions: " + preposition_count);
        System.out.println("Conjunctions: " + conjunction_count);
        System.out.println("Interjections: " + interjection_count);




    }
}
