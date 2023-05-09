public class Game extends AudioVisual {
    String categoria;

    Game(String titulo, double precoBase, String categoria) {
        super(titulo, precoBase);
        System.out.println("Criando game...");
        this.categoria = categoria;
    }
    
    @Override
    public double calculaPrecoVenda()  {
        
        /*
        * O preço de venda de um game
        * É o preço base acrescido de um percentual sobre o preço base conforme a categoria: 20% se for ACAO; 30% se for ESPORTE; 40% se for ESTRATEGIA; 50% se for SIMULACAO ou 70% se for RPG.
        return 0.0;
        */

        if (this.categoria == "ACAO") {
            return (getPrecoBase() * 1.2);
        }
        else if (this.categoria == "ESPORTE") {
            return (getPrecoBase() * 1.3);
        }
        else if (this.categoria == "ESTRATEGIA") {
            return (getPrecoBase() * 1.4);
        }
        else if (this.categoria == "SIMULACAO") {
            return (getPrecoBase() * 1.5);
        }
        else if (this.categoria == "RPG") {
            return (getPrecoBase() * 1.7);
        }
        else {
            return 0.0;
        }

    }

    @Override
    public double calculaImposto()  {
        return 0.0;
    }

}
