import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Acervo {
    ArrayList<AudioVisual> audiovisuais = new ArrayList<AudioVisual>();

    public Acervo() {
        System.out.println("Criando acervo...");
    }

    /**
    * @lerAudioVisuais
    * Lê todos os dados de audiovisuais e escreve a quantidade de itens carregados com sucesso no formato: 1;quantidade de itens carregados
    */
    public void lerAudioVisuais() {
        System.out.println("Carregando audiovisuais...");

        Path path = Paths
        .get("./data/dados.csv");
        
        try(BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            
            String line = null;
            int itensCarregados = 0;
            int itensNaoCarregados = 0;
            String[] console;

            AudioVisual a;

            while ((line = reader.readLine()) != null) {
                console = line.split(";");

                //verifica se o quarto campo é um número
                if(console[2].equals("1")) {
                    a = new BluRay(console[0], Double.parseDouble(console[1]), Integer.parseInt(console[3]));
                    System.out.println("BluRay criado com sucesso!");
                    audiovisuais.add(a);
                    itensCarregados++;
                }
                else if(console[2].equals("2")){
                    a = new Game(console[0], Double.parseDouble(console[1]), console[3]);
                    System.out.println("Game criado com sucesso!");
                    audiovisuais.add(a);
                    itensCarregados++;
                } 
                else {
                    System.out.println("Erro ao cadastrar item!");
                    itensNaoCarregados++;
                }
            }

            System.out.println("Quantidade de itens carregados: " + itensCarregados);
            System.out.println("Quantidade de itens não carregados: " + itensNaoCarregados);
            
            try {
                FileWriter writer = new FileWriter("./data/resultado.csv");
                writer.write("1," + itensCarregados);
                writer.close();
                System.out.println("Dados escritos com sucesso no arquivo.");
                
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo.");
                e.printStackTrace();
            }

        }
        catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);

        }
         
    }

    /**
     * @escreverInfomacoesIens
     * escreve algumas informações para cada item carregado com sucesso no sistema, no formato: 2;título;valor do preço final;valor do imposto
     */
    public void escreverInfomacoesIens() {
        System.out.println("Carregando audiovisuais...");

        Path path = Paths
        .get("./data/dados.csv");

        try(BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            
            String line = null;
            int itensCarregados = 0;
            int itensNaoCarregados = 0;
            String[] console;

            AudioVisual a;

            while ((line = reader.readLine()) != null) {

                console = line.split(";");

                if(console.length == 4) {

                    if(console[2].equals("1")) {

                        a = new BluRay(console[0], Double.parseDouble(console[1]), Integer.parseInt(console[3]));
                        System.out.println("BluRay criado com sucesso!");

                    }

                    else if(console[2].equals("2")) {

                        a = new Game(console[0], Double.parseDouble(console[1]), console[3]);
                        System.out.println("Game criado com sucesso!");

                    }

                    try {
                        FileWriter writer = new FileWriter("./data/resultado.csv", true);
                        
                        writer.write("2," + a.getTitulo() + ";" + a.calculaPrecoVenda() + ";" + a.calculaImposto());
                        writer.close();
                        System.out.println("Dados escritos com sucesso no arquivo.");
                        
                    } catch (IOException e) {
                        System.out.println("Erro ao escrever no arquivo.");
                        e.printStackTrace();
                    }
                        
                } else {
                    System.out.println("Erro ao cadastrar item!");
                    itensNaoCarregados++;
                }

            }

            System.out.println("Quantidade de itens carregados: " + itensCarregados);
            System.out.println("Quantidade de itens não carregados: " + itensNaoCarregados);

        }
        catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);

        }
    }

}
