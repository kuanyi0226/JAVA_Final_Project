import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScoreRecord {
    
    public void scoreService(){
        String filePath = "score_history";
        readScore(filePath);
        writeScore(filePath);
    }

    private ArrayList<String> readScore(String filePath){
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String tmp = null;

        try {
            while (((tmp = br.readLine()) != null)) {
                System.out.println(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write(String filePath, ArrayList<String> scores){
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String s: scores){
                bw.write(s);    // 寫入資料
                bw.newLine();   // 新增一行
            }
            bw.flush(); // 把記憶體資料寫進去
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
