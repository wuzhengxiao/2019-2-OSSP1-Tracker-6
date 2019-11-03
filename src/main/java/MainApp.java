import org.jsoup.select.Elements;

public class MainApp {
    public static void main(String[] args) throws Exception {
        Crawler crawler = new Crawler(); //Crawler 객체 생성.
        KoreanTokenize koreanTokenize = new KoreanTokenize();//KoreanTokenize 객체 생성.
        FrequencyAnalyze frequencyAnalyze = new FrequencyAnalyze();//FrequencyAnalyze 객체 생성.
        MySql mysql = new MySql();//MySql 객체 생성.

        //Crawling한 결과 데이터 가져오기.
        Elements elements = crawler.getCrawlingData();
        //Crawling한 결과 값을 한국어 형태소 분석기에 넣어줌.
        koreanTokenize.setElements(elements);
        //입력받은 데이터 형태소 분석 후 명사만 File에 넣어줌.
        koreanTokenize.tokenize();

        //빈도수 측정을 위한 데이터 로딩.
        frequencyAnalyze.DataLoad();

        //mysql driver 로딩.
        mysql.DriverLoading();
        //mysql 연결
        mysql.Connect();
        //기존에 있던 table 데이터 초기화.
        mysql.ResetTable();
        //테이블에 데이터 삽입.

        for(int i=0;i<frequencyAnalyze.getSize();i++){
            mysql.Insert(frequencyAnalyze.getWord(i),frequencyAnalyze.getFrequency(i),crawler.getTime());
        }
        //테이블 연결 끊기.
        mysql.DisConnect();
    }

}