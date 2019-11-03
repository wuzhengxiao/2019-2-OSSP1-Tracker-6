import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Crawler {
    private static String URL = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=104";
    private static String SELECT_TAG = "div.cluster_text a.cluster_text_headline";
    private Document doc;
    private String time;

    //Jsoup을 이용한 크롤링.
    // 크롤링한 현재 시간 저장.
    public Crawler() throws Exception {
        doc = Jsoup.connect(URL)
                .ignoreContentType(true)
                .get();

        setTime();
    }

    //크롤링한 데이터 반환.
    public Elements getCrawlingData(){
        return doc.select(SELECT_TAG);
    }

    //time getter.
    public String getTime() {
        return time;
    }

    //현재시간 time 필드에 저장.
    private void setTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String str = dayTime.format(new Date(time));
        this.time = str;
    }
}
