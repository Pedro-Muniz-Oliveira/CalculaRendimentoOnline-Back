package backend.service;

import backend.model.CalculoRequest;
import backend.model.CalculoResponse;

public class CalculoService {

    public CalculoResponse calcular(CalculoRequest req) {
        double quantidade = req.getInvestido() / req.getCompra();
        double valorAtual = quantidade * req.getAtual();
        double lucro = valorAtual - req.getInvestido();

        return new CalculoResponse(quantidade, valorAtual, lucro);
    }
}
