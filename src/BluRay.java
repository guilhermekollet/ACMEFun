public class BluRay extends AudioVisual {
    int duracao; //minutos

    @Override
    public double calculaPrecoVenda()  {
        return ((this.precoBase * this.duracao)/100);
    }

    @Override
    public double calculaImposto()  {
        return 0.0;
    }

}
