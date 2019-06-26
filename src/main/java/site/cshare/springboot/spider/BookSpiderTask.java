package site.cshare.springboot.spider;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.cshare.springboot.config.DouBanConfig;
import site.cshare.springboot.model.entity.Book;
import site.cshare.springboot.model.entity.BookTag;
import site.cshare.springboot.service.BookService;
import site.cshare.springboot.service.BookTagService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component("bookSpiderTask")
@Slf4j
public class BookSpiderTask implements SpiderTask{

    @Autowired
    BookTagService bookTagService;

    @Autowired
    BookService bookService;


    @Autowired
    DouBanConfig douBanConfig;


    @Override
    public void handler() throws IOException {
        BookTag leastSpiderBookTag = bookTagService.findFirstLeastSpider();
        log.info("Begin spider book tag = {}", leastSpiderBookTag.getTag());
        String url = douBanConfig.getBaseUrl() + leastSpiderBookTag.getTag();
        Integer currentPage = leastSpiderBookTag.getCurrentPage();
        url += "?start=" + getStart(currentPage) +"&type" + getType();
        Document document = Jsoup.parse(new URL(url), 3000);
        List<Book> books = this.getBooks(document, leastSpiderBookTag.getTag());
        bookService.saveAll(books);

        if (books.isEmpty()) {
            leastSpiderBookTag.setHasNextPage(0);
        } else {
            leastSpiderBookTag.setCurrentPage(currentPage + 1);
        }
        bookTagService.save(leastSpiderBookTag);
        log.info("End spider book tag = {}", leastSpiderBookTag.getTag());
    }


    private List<Book> getBooks(Document document, String tag) {

        List<Book> books = new ArrayList<>();

        document.body().select("ul.subject-list").select("li.subject-item").forEach(e -> {
            String imgSrc = e.select("img").attr("src");
            String name = e.select("div.info").select("a").attr("title");
            String text = e.select("div.pub").text();
            String star = e.select("span.rating_nums").text();
            String plNum = e.select("span.pl").text();
            String description = e.select("div.info").select("p").text();
            String[] split = text.split(" / ");
            Book book = new Book()
                    .setName(name)
                    .setImg(imgSrc)
                    .setStar(Float.valueOf(star))
                    .setRatingNum(Integer.valueOf(plNum.substring(plNum.indexOf("(") + 1, plNum.indexOf("人"))))
                    .setDesc(description)
                    .setAuthor(split[0])
                    .setPress(text)
                    .setTag(tag);
            books.add(book);
        });
        return books;
    }

    private int getStart(int page) {
        return 20 * (page - 1);
    }

    private String getType() {
        return "T";
    }
}
