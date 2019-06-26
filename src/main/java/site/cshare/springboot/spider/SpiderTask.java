package site.cshare.springboot.spider;

import java.io.IOException;
import java.net.MalformedURLException;

public interface SpiderTask {

    void handler() throws IOException;
}
