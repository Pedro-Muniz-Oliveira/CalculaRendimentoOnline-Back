package backend.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {

    private static final String LOG_DIR = "logs";

    public static void log(String mensagem) {
        try {
            Path logDir = Paths.get(LOG_DIR);
            if (!Files.exists(logDir)) Files.createDirectories(logDir);

            String dataHoje = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Path logFile = logDir.resolve(dataHoje + ".log");

            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String linha = "[" + dataHora + "] " + mensagem + System.lineSeparator();

            Files.write(logFile, linha.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("⚠️ Falha ao gravar log: " + e.getMessage());
        }

        System.out.println(mensagem);
    }
}
