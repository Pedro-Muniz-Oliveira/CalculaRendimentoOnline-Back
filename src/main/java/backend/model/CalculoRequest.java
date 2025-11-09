package backend.model;

import org.json.JSONObject;

public class CalculoRequest {
    private double investido;
    private double compra;
    private double atual;

    public CalculoRequest(double investido, double compra, double atual) {
        this.investido = investido;
        this.compra = compra;
        this.atual = atual;
    }

    public static CalculoRequest fromJson(String json) {
        JSONObject obj = new JSONObject(json);
        return new CalculoRequest(
            obj.getDouble("investido"),
            obj.getDouble("compra"),
            obj.getDouble("atual")
        );
    }

    public double getInvestido() { return investido; }
    public double getCompra() { return compra; }
    public double getAtual() { return atual; }
}
