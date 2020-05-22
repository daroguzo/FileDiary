package diary.main;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"diary.main"},
    excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "diary\\..*"))
public class JavaConfig {

    @Bean
    @Scope("prototype")
    public Account account(){
        return new Account();
    }

    @Bean
    @Scope("prototype")
    public Diary diary(){
        return new Diary();
    }

    @Bean
    @Scope("prototype")
    public DateSearch dateSearch(){ return new DateSearch(); }

    @Bean
    @Scope("prototype")
    public KeywordSearch keywordSearch(){ return new KeywordSearch(); }

}
