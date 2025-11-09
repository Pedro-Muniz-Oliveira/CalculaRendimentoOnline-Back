package backend;

import com.sun.net.httpserver.HttpServer;
import backend.controller.CalculoController;
import backend.util.LoggerUtil;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/calcular", new CalculoController());
        server.setExecutor(null);

        LoggerUtil.log("🚀 Servidor iniciado em http://localhost:" + port + "/calcular");
        server.start();
    }
}
