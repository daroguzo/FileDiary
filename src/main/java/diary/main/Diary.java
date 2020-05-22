package diary.main;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Diary {

    private String id;

    private String date;

    private String content;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // 새로운 다이어리 생성
    public void newDiary(String date, String content){
        try {
            String accountDirectory = "/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/" + id + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(accountDirectory, true));
            writer.write(String.format("%s,%s,\n", date, content));

            writer.close();
            System.out.println("작성 완료.");

        } catch (IOException e) { e.printStackTrace(); }
    }

    // 다이어리 보기
    public void myDiary(Account account){
        try{
            String accountDirectory = "/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/" + account.getId() + ".txt";
            BufferedReader reader = new BufferedReader(new FileReader(accountDirectory));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                System.out.printf("date: %s\tcontent: %s\n", temp[0], temp[1]);
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 다이어리 수정

}
