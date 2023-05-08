import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
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

    /*
    * @Ler audiovisuais
    * Lê todos os dados de audiovisuais e escreve a quantidade de itens carregados com sucesso no formato: 1;quantidade de itens carregados
    */
    public void lerAudioVisuais() {
        System.out.println("Carregando audiovisuais...");
        
        //teste
        AudioVisual a1 = new Game("GTA V", 100.0, "ACAO");
        AudioVisual a2 = new BluRay("O Poderoso Chefão", 50.0, 180);

        System.out.println(
            "Preço de venda do game: " + a1.calculaPrecoVenda() + "\n" +
            "Preço de venda do blu-ray: " + a2.calculaPrecoVenda() + "\n" +
            "Imposto do game: " + a1.calculaImposto() + "\n" +
            "Imposto do blu-ray: " + a2.calculaImposto() + "\n"
        );

        Path path = Paths
        .get("D:\\Development\\Java Learn\\TP2\\App\\data\\dados.csv");
        
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
                FileWriter writer = new FileWriter("D:\\Development\\Java Learn\\TP2\\App\\data\\resultado.csv");
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
}
