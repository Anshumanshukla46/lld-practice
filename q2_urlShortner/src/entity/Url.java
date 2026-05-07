package q2_urlShortner.src.entity;

import java.time.LocalDateTime;

public class Url {
    private String longUrl;
    private String shortUrl;
    private long accessCount;
    private LocalDateTime expirationDate;

    public Url() {
    }

    public Url(String longUrl, String shortUrl, long accessCount, LocalDateTime expirationDate) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.accessCount = accessCount;
        this.expirationDate = expirationDate;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public long getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(long accessCount) {
        this.accessCount = accessCount;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
