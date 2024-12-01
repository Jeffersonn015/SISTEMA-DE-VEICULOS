package Sistema_veiculos;

public class Veiculo {
    private String modelo; // Modelo do veículo
    private int anodeFabricacao; // Ano de fabricação do veículo
    private String montadora; // Montadora do veículo
    private String cor; // Cor do veículo
    private double kilometragem; // Kilometragem do veículo

    // Construtor da classe Veiculo
    // Este construtor recebe os parâmetros necessários para inicializar os atributos do veículo
    public Veiculo(String modelo, int anodeFabricacao, String montadora, String cor, double kilometragem) {
        this.modelo = modelo; // Inicializa o modelo do veículo
        this.anodeFabricacao = anodeFabricacao; // Inicializa o ano de fabricação
        this.montadora = montadora; // Inicializa a montadora
        this.cor = cor; // Inicializa a cor do veículo
        this.kilometragem = kilometragem; // Inicializa a quilometragem
    }

    // Métodos Getters: são usados para acessar os valores dos atributos privados da classe

    public String getModelo() {
        return modelo; // Retorna o modelo do veículo
    }

    public int getAnoDeFabricacao() {
        return anodeFabricacao; // Retorna o ano de fabricação do veículo
    }

    public String getMontadora() {
        return montadora; // Retorna a montadora do veículo
    }

    public String getCor() {
        return cor; // Retorna a cor do veículo
    }

    public double getKilometragem() {
        return kilometragem; // Retorna a quilometragem do veículo
    }

    // Método de descrição: Retorna uma descrição formatada do veículo
    // Utiliza os valores dos atributos para montar uma string representando as informações do veículo
    public String descricao() {
        return String.format("Modelo: %s\nAno de Fabricação: %d\nMontadora: %s\nCor: %s\nKilometragem: %.1f",
                             modelo, anodeFabricacao, montadora, cor, kilometragem); // Retorna os dados formatados
    }
}
