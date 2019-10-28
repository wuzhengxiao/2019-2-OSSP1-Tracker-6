import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import com.twitter.penguin.korean.util.KoreanPos;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scala.collection.Iterator;
import scala.collection.Seq;

public class KoreanTokenize {
    private Elements elements;
    File file;

   public KoreanTokenize(){
       file=new File();
   }

   //데이터 저장.
   public void setElements(Elements elements) {
        this.elements = elements;
   }

   //
   public void tokenize(){
        for (Element e : elements) {
            CharSequence normalized = TwitterKoreanProcessorJava.normalize(e.text());
            Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);

            Iterator<KoreanTokenizer.KoreanToken> tokenized = tokens.iterator();
            KoreanTokenizer.KoreanToken token;
            int endFlag = 0;

            //Noun(명사) 뽑아내는 과정.
            while (true) {
                do {
                    if (!tokenized.hasNext()) {
                        endFlag = 1;
                        break;
                    }
                    token = tokenized.next();
                    if (token.pos().toString() == "Noun") {
                        //추출한 Noun을 파일에 저장.
                        file.Writing(token.text());
                    }
                } while (!false && token.pos() == KoreanPos.Space());
                if (endFlag == 1)
                    break;
            }

        }
        file.FileClose();
   }

}
