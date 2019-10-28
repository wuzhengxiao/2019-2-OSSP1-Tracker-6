import java.io.FileWriter;
import java.io.IOException;

public class File {
    private static String filePath = "C:\\Program Files\\pjt\\intelly_workspace\\EveryTimeCrawler\\test.txt";
    private FileWriter fileWriter;

    //파일 생성.
    public File() {
        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //파일 데이터 입력.
    public void Writing(String noun){
        try {
            fileWriter.write(noun + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //파일 닫기.
    public void FileClose(){
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
