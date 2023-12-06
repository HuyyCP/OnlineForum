package Helper;

import java.text.Normalizer;

public class HandleString {
    public static String changeTitleToURL(String title) {
        // Chuyển đổi chuỗi tiếng Việt thành chuỗi không dấu
        String url = Normalizer.normalize(title, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Thay thế khoảng trắng bằng dấu gạch "-"
        url = url.replaceAll(" ", "-");

        return url;
    }

}
