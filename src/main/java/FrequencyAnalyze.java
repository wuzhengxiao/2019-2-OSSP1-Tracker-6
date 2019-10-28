import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;

import java.io.IOException;
import java.util.List;

public class FrequencyAnalyze {
    private static String filePath = "C:\\Program Files\\pjt\\intelly_workspace\\EveryTimeCrawler\\test.txt";
    private FrequencyAnalyzer frequencyAnalyzer;
    private List<WordFrequency> wordFrequencies;


    public FrequencyAnalyze(){
        frequencyAnalyzer = new FrequencyAnalyzer();
    }

    //파일 데이터 로딩.
    public void DataLoad(){
        try {
            wordFrequencies = frequencyAnalyzer.load(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //명사 뽑아오기.
    public String getWord(int i) {
        return wordFrequencies.get(i).getWord();
    }

    //빈도수 뽑아오기.
    public int getFrequency(int i){
        return wordFrequencies.get(i).getFrequency();
    }

    //데이터 갯수.
    public int getSize(){
        return wordFrequencies.size();
    }

}
