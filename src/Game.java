import java.text.DecimalFormat;

public class Game extends AudioVisual {
    String categoria;

    Game(String titulo, double precoBase, String categoria) {
        super(titulo, precoBase);
        this.categoria = categoria;
    }

    String getCategoria() {
        return categoria;
    }
    
    @Override
    public double calculaPrecoVenda() {
        double precoVenda;
    
        switch (this.categoria) {
            case "ACAO":
                precoVenda = getPrecoBase() * 1.2;
                break;
            case "ESPORTE":
                precoVenda = getPrecoBase() * 1.3;
                break;
            case "ESTRATEGIA":
                precoVenda = getPrecoBase() * 1.4;
                break;
            case "SIMULACAO":
                precoVenda = getPrecoBase() * 1.5;
                break;
            case "RPG":
                precoVenda = getPrecoBase() * 1.7;
                break;
            default:
                precoVenda = 0.0;
        }
    
        return Math.round(precoVenda * 100.0) / 100.0;
    }

    @Override
    public double calculaImposto() {
        double precoImposto = (calculaPrecoVenda() * 0.5);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        String precoImpostoString = decimalFormat.format(precoImposto).replace(",", ".");
    
        return Double.parseDouble(precoImpostoString);
    }

}
