public class Acervo {

    public Acervo() {
        System.out.println("Criando acervo...");
    }

    /*
    * @Ler audiovisuais
    * Lê todos os dados de audiovisuais e escreve a quantidade de itens carregados com sucesso no formato: 1;quantidade de itens carregados
    */
    public void carregaAudioVisuais() {
        System.out.println("Carregando audiovisuais...");

        AudioVisual a1 = new Game();
        AudioVisual a2 = new BluRay();

        System.out.println(
            "Preço de venda do game: " + a1.calculaPrecoVenda() + "\n" +
            "Preço de venda do blu-ray: " + a2.calculaPrecoVenda() + "\n" +
            "Imposto do game: " + a1.calculaImposto() + "\n" +
            "Imposto do blu-ray: " + a2.calculaImposto() + "\n"
        );

        /*
         Path path = Paths.get("./data/dados.csv");
        
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String line = null;
            int itensCarregados = 0;
            String[] console;

            while ((line = reader.readLine()) != null) {
                //console = line.split(";");
                System.out.println(line);
                itensCarregados++;
            }
            
            return false;
        }
        catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
            return false;
        }
         */
    }
}
