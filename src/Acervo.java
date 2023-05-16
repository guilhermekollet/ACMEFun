import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Acervo {
    ArrayList<AudioVisual> audiovisuais = new ArrayList<AudioVisual>();
    Path pathRd = Paths.get("./ACMEFun/data/dados.csv");
    Path pathWr = Paths.get("./ACMEFun/data/resultado.csv");

    public Acervo() {
        System.out.println("Criando acervo...");
    }

    /**
    * @lerAudioVisuais
    * Lê todos os dados de audiovisuais e escreve a quantidade de itens carregados com sucesso no formato: 1;quantidade de itens carregados
    */
    public void lerAudioVisuais() {
        try(BufferedReader reader = Files.newBufferedReader(pathRd, Charset.defaultCharset())) {
            
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
                FileWriter writer = new FileWriter(pathWr.toString());
                writer.write("1;" + itensCarregados + "\n");
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

        try(BufferedReader reader = Files.newBufferedReader(pathRd, Charset.defaultCharset())) {
            
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

                        try {
                            FileWriter writer = new FileWriter(pathWr.toFile(), true);
                            DecimalFormat decimalFormat = new DecimalFormat("#.00");
                            String precoVendaFormatado = decimalFormat.format(a.calculaPrecoVenda());
                            String impostoFormatado = decimalFormat.format(a.calculaImposto());
                            
                            writer.write("2;" + a.getTitulo() + ";" + precoVendaFormatado.replace(",", ".") + ";" + impostoFormatado.replace(",", ".") + "\n");
                            writer.close();
                            System.out.println("Dados escritos com sucesso no arquivo.");
                            itensCarregados++;
                            
                        } catch (IOException e) {
                            System.out.println("Erro ao escrever no arquivo.");
                            e.printStackTrace();
                        }
                    }

                    else if(console[2].equals("2")) {

                        a = new Game(console[0], Double.parseDouble(console[1]), console[3]);
                        System.out.println("Game criado com sucesso!");

                        try {
                            FileWriter writer = new FileWriter(pathWr.toFile(), true);

                            DecimalFormat decimalFormat = new DecimalFormat("#.00");
                            String precoVendaFormatado = decimalFormat.format(a.calculaPrecoVenda());
                            String impostoFormatado = decimalFormat.format(a.calculaImposto());
                            
                            writer.write("2;" + a.getTitulo() + ";" + precoVendaFormatado.replace(",", ".") + ";" + impostoFormatado.replace(",", ".") + "\n");
                            writer.close();
                            System.out.println("Dados escritos com sucesso no arquivo.");
                            itensCarregados++;
                            
                        } catch (IOException e) {
                            System.out.println("Erro ao escrever no arquivo.");
                            e.printStackTrace();
                        }
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
