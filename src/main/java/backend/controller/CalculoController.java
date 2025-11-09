package backend.controller;

import backend.model.CalculoRequest;
import backend.model.CalculoResponse;
import backend.service.CalculoService;
import backend.util.LoggerUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CalculoController implements HttpHandler {

    private final CalculoService service = new CalculoService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LoggerUtil.log("➡️ Nova requisição recebida: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());

        // Configura CORS
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            LoggerUtil.log("✅ Requisição OPTIONS tratada (CORS).");
            return;
        }

        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            exchange.sendResponseHeaders(405, -1);
            LoggerUtil.log("⚠️ Método não permitido: " + exchange.getRequestMethod());
            return;
        }

        try (InputStream input = exchange.getRequestBody()) {
            String body = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            LoggerUtil.log("📩 Corpo recebido: " + body);

            CalculoRequest req = CalculoRequest.fromJson(body);
            CalculoResponse resp = service.calcular(req);

            byte[] respostaBytes = resp.toJson().getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, respostaBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(respostaBytes);
            }

            LoggerUtil.log("✅ Resposta enviada: " + resp.toJson());

        } catch (Exception e) {
            LoggerUtil.log("❌ ERRO no processamento: " + e.getMessage());

            JSONObject erro = new JSONObject().put("erro", e.getMessage());
            byte[] respostaErro = erro.toString().getBytes(StandardCharsets.UTF_8);

            exchange.sendResponseHeaders(500, respostaErro.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(respostaErro);
            }
        }
    }
}
