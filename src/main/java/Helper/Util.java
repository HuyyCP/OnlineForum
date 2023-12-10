package Helper;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Util {

  public static String timeAgo(Date date)
  {
    Duration duration = Duration.between(date.toInstant(), Instant.now());
    long seconds = duration.getSeconds();

    if (seconds < 60) {
      return "just now";
    } else if (seconds < 3600) {
      return seconds / 60 + " minutes ago";
    } else if (seconds < 86400) {
      return seconds / 3600 + " hours ago";
    } else if (seconds < 604800) {
      return seconds / 86400 + " days ago";
    } else if (seconds < 2628000) {
      return seconds / 604800 + " weeks ago";
    } else if (seconds < 31536000) {
      return seconds / 2628000 + " months ago";
    } else {
      return seconds / 31536000 + " years ago";
    }
  }
}
