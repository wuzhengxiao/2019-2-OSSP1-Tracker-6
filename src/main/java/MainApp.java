import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import com.twitter.penguin.korean.util.KoreanPos;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scala.collection.Iterator;
import scala.collection.Seq;

import java.awt.*;
import java.io.FileWriter;
import java.sql.*;
import java.util.List;

public class MainApp {
    private static String URL = "https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=104";
    private static String filePath = "C:\\Program Files\\pjt\\intelly_workspace\\EveryTimeCrawler\\test.txt";

    public static void main(String[] args) throws Exception {
        FileWriter fileWriter = new FileWriter(filePath);
        System.out.println("URL :: " + URL);
        Document doc = Jsoup.connect(URL)
                .ignoreContentType(true)
                .get();

        Elements elements = doc.select("div.cluster_text a.cluster_text_headline");
        for (Element e : elements) {
            CharSequence normalized = TwitterKoreanProcessorJava.normalize(e.text());
            System.out.println(normalized);
            Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);

            Iterator<KoreanTokenizer.KoreanToken> tokenized = tokens.iterator();
            KoreanTokenizer.KoreanToken token;
            int endFlag = 0;
            while (true) {
                do {
                    if (!tokenized.hasNext()) {
                        endFlag = 1;
                        break;
                    }
                    token = tokenized.next();
                    if (token.pos().toString() == "Noun") {
                        System.out.println(token.text());
                        fileWriter.write(token.text()+" ");
                    }
                } while (!false && token.pos() == KoreanPos.Space());
                if (endFlag == 1)
                    break;
            }

        }

        fileWriter.close();


        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(filePath);
        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(2);
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("C:\\Program Files\\pjt\\intelly_workspace\\EveryTimeCrawler\\datarank_wordcloud_circle_sqrt_font.png");

        //우선순위 높은것부터 뽑아냄.
        System.out.println(wordFrequencies.get(0).getWord());

        //mysql
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String server = "localhost";
        String database = "tracker";
        String user_name = "root";
        String password = "비밀번호";

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

        //쿼리 수행을 위한 Statement 객체 생성.
        statement = con.createStatement();

        //SQl 쿼리 작성.

        String sql = "insert into word_table (data1,data2) values (" + "\""+wordFrequencies.get(0).getWord()+"\"" + ", " + wordFrequencies.get(0).getFrequency()+")";
        //String sql = "select * from word_table";
        statement.executeUpdate(sql);        //    "INSERT INTO product VALUES (" + p_no + ", '" + p_name + "', " + p_price + ", '" + p_detail + "')";

        //ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        // 3.해제
        try {
            if(con != null) {
                con.close();
                System.out.println("정상적으로 해제되었습니다.");
            }
        } catch (SQLException e) {}


    }

}