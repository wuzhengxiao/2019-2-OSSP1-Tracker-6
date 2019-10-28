import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawler {
    private static String URL = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=104";
    private static String SELECT_TAG = "div.cluster_text a.cluster_text_headline";
    private Document doc;

    //Jsoup을 이용한 크롤링.
    public Crawler() throws Exception {
        doc = Jsoup.connect(URL)
                .ignoreContentType(true)
                .get();
    }

    //크롤링한 데이터 반환.
    public Elements getCrawlingData(){
        return doc.select(SELECT_TAG);
    }
}
