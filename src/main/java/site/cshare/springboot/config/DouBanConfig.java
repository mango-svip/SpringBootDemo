package site.cshare.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "douban.tag")
public class DouBanConfig {

    private String hotUrl;

    private String baseUrl;

}
