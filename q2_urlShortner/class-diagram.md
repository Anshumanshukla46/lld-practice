Improved:

Url
- longUrl: String
- shortUrl: String
- accessCount: long
- expirationDate: LocalDateTime
  
UrlService
- shortUrlToLongUrl: Map<String, Url>
- longUrlToShortUrl: Map<String, Url>

+ getShortUrl(longUrl: String): String
+ redirect(shortUrl: String): String
- isExpired(expirationDate: LocalDateTime): boolean

Relationships
- UrlService has-a Url
- UrlService manages Url objects through Maps

Main Flow Understanding

getShortUrl()
- check if long URL already exists
- if exists and not expired → return existing short URL
- otherwise create new Url object
- store in both maps
- return short URL

redirect()
- find Url using short URL
- validate expiration
- increment accessCount
- return long URL

----------
Url:
- longUrl
- shortUrl
- accessCount
- expirationDate

UrlService:
- Map<String, Url> urlMapping // shorturl to Url
- Map<String, Url> urlMapping // longurl to Url

+ getShortUrl(longUrl : String) :: String
+ getLongUrl(shortUrl : string) :: string
+ getAccessingCount(longUrl : String) :: String
+ getAccessingCount(shortUrl : string) :: string
+ redirect(shortUrl : string) :: string
