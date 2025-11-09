package backend.model;

import org.json.JSONObject;

public class CalculoResponse {
    private double quantidade;
    private double valorAtual;
    private double lucro;

    public CalculoResponse(double quantidade, double valorAtual, double lucro) {
        this.quantidade = quantidade;
        this.valorAtual = valorAtual;
        this.lucro = lucro;
    }

    public String toJson() {
        return new JSONObject()
            .put("quantidade", quantidade)
            .put("valorAtual", valorAtual)
            .put("lucro", lucro)
            .toString();
    }
}
