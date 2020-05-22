package diary.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.util.Scanner;

public class DiaryApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        int menu1 = 0;
        int menu2 = 0;
        boolean loginFlag = false;


        // 초기 화면
        while (true) {
            while (menu1 != 1 && menu1 != 2 && menu1 != 9) {
                System.out.println("==== Diary ====");
                System.out.println("1. 로그인");
                System.out.println("2. 회원가입");
                System.out.println("9. 종료");
                System.out.println("===============");
                menu1 = sc.nextInt();
            }
            // 로그인
            if (menu1 == 1) {
                System.out.println("===============");

                System.out.print("id 입력: ");
                String id = sc.next();
                System.out.print("password 입력: ");
                String password = sc.next();
                Account account = new Account();
                account.setId(id);
                account.setPassword(password);
                loginFlag = account.login(id, password);

                while (loginFlag && menu2 != 1 && menu2 != 2 && menu2 != 3 && menu2 != 4 && menu2 != 5 && menu2 != 6 && menu2 != 9) {
                    System.out.println("===============");
                    System.out.println("1. 비밀번호 변경");
                    System.out.println("2. 다이어리 추가");
                    System.out.println("3. 다이어리 보기");
                    System.out.println("4. 다이어리 수정");
                    System.out.println("5. 키워드 검색");
                    System.out.println("6. 날짜 검색");
                    System.out.println("9. 종료");
                    System.out.println("===============");
                    menu2 = sc.nextInt();

                    if (menu2 == 1) {
                        account.changePwd(id, password);
                        menu2 = 0;
                    }

                    if (menu2 == 2){
                        Diary diary = new Diary();
                        diary.setId(account.getId());
                        String date = "", content = "";
                        System.out.println("===============");
                        System.out.print("날짜입력(YY/MM/DD): ");
                        date = sc.next();
                        sc.nextLine();
                        System.out.println("내용 입력 ");
                        content = sc.nextLine();
                        diary.newDiary(date, content);
                        System.out.println("===============");
                        menu2 = 0;
                    }

                    if (menu2 == 3){
                        System.out.println("===============");
                        Diary diary = new Diary();
                        diary.myDiary(account);
                        System.out.println("===============");
                        menu2 = 0;
                    }

                    if(menu2 == 4){
                        System.out.println("===============");
                        System.out.println("=====미구현=====");
                        System.out.println("===============");
                    }

                    if (menu2 == 5){
                        System.out.println("===============");
                        KeywordSearch keywordSearch = new KeywordSearch();
                        keywordSearch.search(account);
                        System.out.println("===============");
                        menu2 = 0;
                    }

                    if (menu2 == 6){
                        System.out.println("===============");
                        DateSearch dateSearch = new DateSearch();
                        dateSearch.search(account);
                        System.out.println("===============");
                        menu2 = 0;
                    }

                    // 종료
                    if (menu2 == 9) {
                        System.out.println("다이어리를 종료합니다.");
                        sc.close();
                        return;
                    }

                }
            }

            // 회원가입
            if (menu1 == 2) {
                String id = "", password = "";
                System.out.println("===============");
                Account newAccount = new Account();
                System.out.print("id : ");
                id = sc.next();
                System.out.print("password : ");
                password = sc.next();

                // 중복 검사
                if (newAccount.isDuplicated(id))
                    System.out.println("중복된 id입니다.");
                else
                    newAccount.newAccount(id, password);

                System.out.println("===============");
                menu1 = 0;
            }

            // 종료
            if (menu1 == 9) {
                System.out.println("다이어리를 종료합니다.");
                sc.close();
                return;
            }
        }
    }
}
