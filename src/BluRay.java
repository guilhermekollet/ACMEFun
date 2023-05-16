public class BluRay extends AudioVisual {
    private int duracao; //minutos

    BluRay(String titulo, double precoBase, int duracao) {
        super(titulo, precoBase);
        System.out.println("Criando BluRay...");
        this.duracao = duracao;
    }

    @Override
    public double calculaPrecoVenda()  {
        return ((getPrecoBase() * this.duracao)/100);
    }

    @Override
    public double calculaImposto()  {
        return Math.round((calculaPrecoVenda() * 0.4) * 100.0) / 100.0;
    }

}
