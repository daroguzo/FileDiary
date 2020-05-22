package diary.main;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

@Component

public class Account {

    Scanner sc = new Scanner(System.in);

    private String id;

    private String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 새로운 계정 만들기
    public void newAccount(String id, String password) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/user.txt", true));
            String accountDirectory = "/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/" + id + ".txt";
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(accountDirectory, true));
            writer.write(String.format("%s,%s,\n\n", id, password));

            writer.flush();
            writer.close();
            System.out.println("작성 완료.");

        } catch (IOException e) { e.printStackTrace(); }
    }

    // 중복 된 id 검사
    public boolean isDuplicated (String id){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/user.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] users = line.split(",");
                String[] ids = new String[users.length/2 + 1];
                for (int i = 0; i < users.length; i++) {
                    if (i % 2 == 0)
                        ids[i] = users[i];
                }
                // 중복 검사
                if(Arrays.asList(ids).contains(id)){
                    reader.close();
                    return true;
                }
            }
            reader.close();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return true;
        }
    }

    // 로그인
    public boolean login(String id, String password){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/user.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] users = line.split(",");
                String[] ids = new String[users.length/2 + 1];
                for (int i = 0; i < users.length; i++) {
                    if (i % 2 == 0)
                        ids[i] = users[i];
                }
                if(Arrays.asList(ids).contains(id)){
                    if(users[Arrays.asList(users).indexOf(id) + 1].equals(password)) {
                        System.out.println("로그인 성공!");
                        reader.close();
                        return true;
                    }
                    else {
                        System.out.println("password가 맞지 않습니다.");
                        reader.close();
                        return false;
                    }
                }else {
                    System.out.println("존재하지 않는 id입니다.");
                    reader.close();
                    return false;
                }
            }
            reader.close();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return true;
        }
    }

    // 비밀번호 변경
    public void changePwd(String id, String password){
        String oldFileName = "/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/user.txt";
        String tmpFileName = "/Users/jinoo/Desktop/4-1/4-1 SpringFramework/txtFile/userTemp.txt";

        BufferedReader br = null;
        BufferedWriter bw = null;

        System.out.print("새로운 비밀번호: ");
        String newPwd = sc.next();

        try {
            br = new BufferedReader(new FileReader(oldFileName));
            bw = new BufferedWriter(new FileWriter(tmpFileName));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(password))
                    line = line.replace(password, newPwd);
                bw.write(line+"\n");
            }
        } catch (Exception e) {
            return;
        } finally {
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                //
            }
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                //
            }
        }

        // 임시 파일인 userTemp.txt를 user.txt로 교체하고 임시파일 삭제
        File oldFile = new File(oldFileName);
        oldFile.delete();

        File newFile = new File(tmpFileName);
        newFile.renameTo(oldFile);

        System.out.println("변경 완료!");
    }
}
