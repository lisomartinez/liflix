package cloud.liso.liflix.torrentfinder.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZoogleUtils {
    private static Path path = Paths.get("src", "test", "resources", "zooqle");

    public static String getZooglePage() {
        Path file = path.resolve("zooqleWebPage.html");
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
