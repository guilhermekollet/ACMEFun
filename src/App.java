import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//leitura dados.csv
//escrita resultados.csv
public class App {

    /*
    * @Ler audiovisuais
    * Lê todos os dados de audiovisuais e escreve a quantidade de itens carregados com sucesso no formato: 1;quantidade de itens carregados
    */
    public boolean leAudioVisuais() {
        
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
    }

    public void executa() {
        
        leAudioVisuais();

         /*
         * @Escrever informações dos itens
         * escreve algumas informações para cada item carregado com sucesso no sistema, no formato: 2;título;valor do preço final;valor do imposto
         */

         /*
         * @Escrever quantos games RPG foram cadastrados
         * escreve a quantidade de games da categoria RPG que foram carregados no sistema no formato: 3;quantidade de games RPG
         */

        /*
         * @Escrever o BluRay com imposto mais próximo da média
         * calcula a média dos valores de imposto de BluRays carregados e escreve qual BluRay possui o valor de imposto mais próximo da média calculada, no formato: 4:média dos valores de impostos de BluRays;título do BluRay com imposto mais próximo da média. Caso não haja BluRays, escreve no formato: 4:Nenhum BluRay
         */



    }
}
