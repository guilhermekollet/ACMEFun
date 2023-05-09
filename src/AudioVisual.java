abstract class AudioVisual implements Cobravel {
    private String titulo;
    private double precoBase;

    AudioVisual(String titulo, double precoBase) {
        this.titulo = titulo;
        this.precoBase = precoBase;
    }
    
    @Override
    public double calculaPrecoVenda() {
        
        return 0.0;
    }

    @Override
    public double calculaImposto() {
        
        return 0.0;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    
}
