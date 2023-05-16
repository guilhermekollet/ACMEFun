import java.text.DecimalFormat;

public class BluRay extends AudioVisual {
    private int duracao; //minutos

    BluRay(String titulo, double precoBase, int duracao) {
        super(titulo, precoBase);
        this.duracao = duracao;
    }

    @Override
    public double calculaPrecoVenda() {

        double precoVenda = (getPrecoBase() * this.duracao) / 100;

        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        String precoVendaString = decimalFormat.format(precoVenda).replace(",", ".");
        
        return Double.parseDouble(precoVendaString);

    }
    
    @Override
    public double calculaImposto() {

        double precoImposto = (calculaPrecoVenda() * 0.4);

        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        String precoImpostoString = decimalFormat.format(precoImposto).replace(",", ".");
    
        return Double.parseDouble(precoImpostoString);
    }

}
