package diary.main;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Component
public class DateSearch implements Search{

    // dateSearch
    @Override
    public void search(Account account) {

        try {
            String accountDirectory = "/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/" + account.getId() + ".txt";
            BufferedReader reader = new BufferedReader(new FileReader(accountDirectory));
            String line = "";
            Scanner sc = new Scanner(System.in);
            System.out.print("찾을 날짜를 입력하세요(YY/MM/DD): ");
            String target = sc.next();

            // 해당되는 데이터가 있는 지를 검사하는 flag
            int flag = 0;

            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i].equals(target)) {
                        System.out.printf("dates: %s\tcontent: %s\n", temp[i], temp[i + 1]);
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
