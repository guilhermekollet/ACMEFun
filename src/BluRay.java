import java.text.DecimalFormat;

public class BluRay extends AudioVisual {
    private int duracao; //minutos

    BluRay(String titulo, double precoBase, int duracao) {
        super(titulo, precoBase);
        System.out.println("Criando BluRay...");
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
        double precoImpostoArredondado = Math.round(precoImposto * 100.0) / 100.0;
    
        return precoImpostoArredondado;
    }

}
