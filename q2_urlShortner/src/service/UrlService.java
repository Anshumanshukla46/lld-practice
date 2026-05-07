package q2_urlShortner.src.service;

import q2_urlShortner.src.entity.Url;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UrlService {

    Map<String, Url> shortUrlToLongUrl = new HashMap<>();
    Map<String, Url> longUrlToShortUrl = new HashMap<>();

    //    - getShortUrl()
    // wrong as access count only updates in redirect not at the time of creation isn't ?
    public String getShortUrl(String url) throws Exception {
        if (longUrlToShortUrl.containsKey(url)) {
            Url res = longUrlToShortUrl.get(url);
            if (isExpired(res.getExpirationDate())) {
                throw new Exception("Invalid Url"); // will create custom exception as InvalidInputException
            }
            res.setAccessCount(res.getAccessCount() + 1);
//          not need: due to pass by reference:
//          longUrlToShortUrl.put(url, res);
//          shortUrlToLongUrl.put(res.getShortUrl(), res);
            return res.getShortUrl();
        }

        Url res = new Url();
        res.setLongUrl(url);
        String shortUrl = String.valueOf(url.hashCode());
        res.setShortUrl(shortUrl);// taking this as temporary short url
        res.setAccessCount(1);
        res.setExpirationDate(LocalDateTime.now().plusMonths(6)); // setting 6 months from now. had to google how to do this as i am not aware of this

        longUrlToShortUrl.put(url, res);
        shortUrlToLongUrl.put(shortUrl, res);
        return shortUrl;
    }

    private boolean isExpired(LocalDateTime expirationDate) {
        return expirationDate.isAfter(LocalDateTime.now()); // wrong: return expirationDate.getNano() <= LocalDateTime.now().getNano();
    }

    //- redirect()
    public String redirect(String shortUrl) throws Exception {
        if (shortUrlToLongUrl.containsKey(shortUrl)) {
            Url res = shortUrlToLongUrl.get(shortUrl);
            if (isExpired(res.getExpirationDate())) {
                throw new Exception("Invalid Url"); // will create custom exception as InvalidInputException
            }
            res.setAccessCount(res.getAccessCount() + 1);

            shortUrlToLongUrl.put(shortUrl, res);
            longUrlToShortUrl.put(res.getLongUrl(), res);

            return res.getLongUrl();
        } else {
            throw new Exception("Invalid Url"); // will create custom exception as InvalidInputException
        }
    }
}
