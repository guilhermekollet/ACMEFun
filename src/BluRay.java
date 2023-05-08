public class BluRay extends AudioVisual {
    int duracao; //minutos

    BluRay(String titulo, double precoBase, int duracao) {
        System.out.println("Criando blu-ray...");
        this.titulo = titulo;
        this.precoBase = precoBase;
        this.duracao = duracao;
    }

    @Override
    public double calculaPrecoVenda()  {
        return ((this.precoBase * this.duracao)/100);
    }

    @Override
    public double calculaImposto()  {
        return 0.0;
    }

}
