public class App {

    public void executa() {
        
        Acervo acervo = new Acervo();

        /**
        * @lerAudioVisuais
        * Lê todos os dados de audiovisuais e escreve a quantidade de itens carregados com sucesso no formato: 1;quantidade de itens carregados
        */
        acervo.lerAudioVisuais();

         /**
         * @Escrever informações dos itens
         * escreve algumas informações para cada item carregado com sucesso no sistema, no formato: 2;título;valor do preço final;valor do imposto
         */
        acervo.escreverInfomacoesItens();

         /**
         * @Escrever quantos games RPG foram cadastrados
         * escreve a quantidade de games da categoria RPG que foram carregados no sistema no formato: 3;quantidade de games RPG
         */
        acervo.escreverRpgsCadastrados();

        /**
         * @Escrever o BluRay com imposto mais próximo da média
         * calcula a média dos valores de imposto de BluRays carregados e escreve qual BluRay possui o valor de imposto mais próximo da média calculada, no formato: 4:média dos valores de impostos de BluRays;título do BluRay com imposto mais próximo da média. Caso não haja BluRays, escreve no formato: 4:Nenhum BluRay
         */



    }
}
