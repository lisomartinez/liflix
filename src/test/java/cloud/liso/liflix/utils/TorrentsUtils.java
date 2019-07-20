package cloud.liso.liflix.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TorrentsUtils {
    private static Path zooqlePath = Paths.get("src", "test", "resources", "zooqle");
    private static Path eztvPath = Paths.get("src", "test", "resources", "eztv");

    public static String getZooglePage() {
        Path file = zooqlePath.resolve("zooqleWebPage.html");
        String page = "";
        try {
            Stream<String> lines = Files.lines(file);
            page = lines.collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    public static String getEztvPage() {
        Path file = eztvPath.resolve("search-result.html");
        String page = "";
        try {
            Stream<String> lines = Files.lines(file);
            page = lines.collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }
}