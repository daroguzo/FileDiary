package diary.main;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Component
public class KeywordSearch implements Search{

    // keyword search
    @Override
    public void search(Account account) {

        try {
            String accountDirectory = "/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/" + account.getId() + ".txt";
            BufferedReader reader = new BufferedReader(new FileReader(accountDirectory));
            String line = "";
            Scanner sc = new Scanner(System.in);
            System.out.print("찾을 키워드를 입력하세요: ");
            String target = sc.next();
            int flag = 0;

            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i].contains(target)) {
                        System.out.printf("date: %s\tcontent: %s\n", temp[i - 1], temp[i]);
                        flag++;
                    }
                }
            }
            if(flag == 0)
                System.out.println("해당되는 데이터가 없습니다.");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
