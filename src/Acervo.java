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

    /**
    * @lerAudioVisuais
    * Lê todos os dados de audiovisuais e escreve a quantidade de itens carregados com sucesso no formato: 1;quantidade de itens carregados
    */
    public void lerAudioVisuais() {

        try(BufferedReader reader = Files.newBufferedReader(pathRd, Charset.defaultCharset())) {
            
            String line = null;
            String[] console;
            int itensCarregados = 0;

            AudioVisual a;

            while ((line = reader.readLine()) != null) {
                console = line.split(";");
            
                switch (console[2]) {
                    case "1":
                        a = new BluRay(console[0], Double.parseDouble(console[1]), Integer.parseInt(console[3]));
                        audiovisuais.add(a);
                        itensCarregados++;
                        break;
                    case "2":
                        a = new Game(console[0], Double.parseDouble(console[1]), console[3]);
                        audiovisuais.add(a);
                        itensCarregados++;
                        break;
                    default:
                        break;
                }
            }
            
            try {
                FileWriter writer = new FileWriter(pathWr.toString());
                
                writer.write("1;" + itensCarregados + "\n");
                writer.close();
                
            } catch (IOException e) {
                System.err.format("Erro de E/S: %s%n", e);
                e.printStackTrace();
            }

        }
        catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);

        }
         
    }

    /**
     * @escreverInfomacoesItens
     * escreve algumas informações para cada item carregado com sucesso no sistema, no formato: 2;título;valor do preço final;valor do imposto
     */
    public void escreverInformacoesItens() {

        /*
        try(BufferedReader reader = Files.newBufferedReader(pathRd, Charset.defaultCharset())) {
            
            String line = null;
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
                            
                        } catch (IOException e) {
                            System.out.println("Erro ao escrever no arquivo.");
                            e.printStackTrace();
                        }
                    }

                    else if(console[2].equals("2")) {

                        a = new Game(console[0], Double.parseDouble(console[1]), console[3]);

                        try {

                            FileWriter writer = new FileWriter(pathWr.toFile(), true);

                            DecimalFormat decimalFormat = new DecimalFormat("#.00");

                            String precoVendaFormatado = decimalFormat.format(a.calculaPrecoVenda());
                            String impostoFormatado = decimalFormat.format(a.calculaImposto());
                            
                            writer.write("2;" + a.getTitulo() + ";" + precoVendaFormatado.replace(",", ".") + ";" + impostoFormatado.replace(",", ".") + "\n");
                            writer.close();
                            
                        } catch (IOException e) {

                            System.out.println("Erro ao escrever no arquivo.");
                            e.printStackTrace();

                        }
                    }
                }
            }
        }
        catch (IOException e) {

            System.err.format("Erro de E/S: %s%n", e);

        }
        */
        if(audiovisuais.size() > 0) {

            AudioVisual a = null;
           
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            for(int i=0; i < audiovisuais.size(); i++) {

                a = audiovisuais.get(i);
                
                try {

                    FileWriter writer = new FileWriter(pathWr.toFile(), true);
                    String precoVendaFormatado = decimalFormat.format(a.calculaPrecoVenda());
                    String impostoFormatado = decimalFormat.format(a.calculaImposto());
                                            writer.write("2;" + a.getTitulo() + ";" + precoVendaFormatado.replace(",", ".") + ";" + impostoFormatado.replace(",", ".") + "\n");
                    writer.close();
                        
                } catch (IOException e) {

                    System.out.println("Erro ao escrever no arquivo.");
                    e.printStackTrace();
                }
            }                
        }
    }
        

    /**
    * @Escrever quantos games RPG foram cadastrados
    * escreve a quantidade de games da categoria RPG que foram carregados no sistema no formato: 3;quantidade de games RPG
    */
    public void escreverRpgsCadastrados() {

        try {

            FileWriter writer = new FileWriter(pathWr.toFile(), true);
            
            final int[] rpgsCadastrados = {0};
            
            audiovisuais.forEach(a -> {
                if(a instanceof Game) {
                    
                    Game g = (Game) a;
                    if(g.getCategoria().equals("RPG")) {
                        rpgsCadastrados[0]++;
                    }
                }
            });

            writer.write("3;" + rpgsCadastrados[0] + "\n");
            writer.close();
            
        } catch (IOException e) {

            System.out.println("Erro ao escrever no arquivo.");
            e.printStackTrace();

        }
    }

    /**
    * @Escrever o BluRay com imposto mais próximo da média
    * calcula a média dos valores de imposto de BluRays carregados e escreve qual BluRay possui o valor de imposto mais próximo da média calculada, no formato: 4:média dos valores de impostos de BluRays;título do BluRay com imposto mais próximo da média. Caso não haja BluRays, escreve no formato: 4:Nenhum BluRay
    */
    public void escreverMediaBlurayProximo() {
       
        int qtdBluray = 0;
        double mediaBluray = 0;

        for(int i=0; i<audiovisuais.size(); i++) {

            if(audiovisuais.get(i) instanceof BluRay) {

                BluRay b = (BluRay) audiovisuais.get(i);
                mediaBluray += b.calculaImposto();
                qtdBluray++;

            }
        }

        if(qtdBluray > 0) {

            BluRay bluraySemelhante = null;

            //Definição da média
            mediaBluray = mediaBluray/qtdBluray;
            
            //Encontrar qual BluRay está mais próximo da média
            for(int i=0; i<audiovisuais.size(); i++) {

                if(audiovisuais.get(i) instanceof BluRay) {

                    BluRay b = (BluRay) audiovisuais.get(i);

                    if(bluraySemelhante == null) {

                        bluraySemelhante = b;
                    
                    } else {
                    
                        if(Math.abs(b.calculaImposto() - mediaBluray) < Math.abs(bluraySemelhante.calculaImposto() - mediaBluray)) {
                            
                            bluraySemelhante = b;

                        }
                    }
                }
            }
    
            try {

                FileWriter writer = new FileWriter(pathWr.toFile(), true);

                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                
                String mediaBlurayFormatado = decimalFormat.format(mediaBluray);
                mediaBlurayFormatado = mediaBlurayFormatado.replace(",", ".");
    
                writer.write("4;" + mediaBlurayFormatado + ";" + bluraySemelhante.getTitulo());
                writer.close();
                
            } catch (IOException e) {

                System.out.println("Erro ao escrever no arquivo.");
                e.printStackTrace();

            }
            
        } else {

            try {

                FileWriter writer = new FileWriter(pathWr.toFile(), true);
                writer.write("4:Nenhum BluRay");
                writer.close();

            } catch (IOException e) {

                System.out.println("Erro ao escrever no arquivo.");
                e.printStackTrace();

            }
        }
    }     
}
