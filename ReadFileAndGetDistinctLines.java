import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

class Scratch {
    private static final String file = "/home/nibals/Documents/faulty-orders.txt";

    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get(file), Charset.defaultCharset())
                .map(s -> s.replace(",", ""))
                .distinct()
                .forEach(System.out::println);
    }
}