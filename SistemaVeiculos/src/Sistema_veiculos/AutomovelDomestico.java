package Sistema_veiculos;

import dao.VeiculoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Subclasse Automóvel Doméstico, que herda de Veiculo
public class AutomovelDomestico extends Veiculo {
    private int quantidadePassageiros; // Atributo que define a quantidade de passageiros no carro
    private String tipoFreio; // Atributo que define o tipo de freio do carro
    private String airbag; // Atributo que define o tipo de airbag do carro

    // Construtor que inicializa todos os parâmetros necessários para o Automóvel Doméstico
    public AutomovelDomestico(String modelo, int anodeFabricacao, String montadora, String cor, double kilometragem, int quantidadePassageiros, String tipoFreio, String airbag) {
        super(modelo, anodeFabricacao, montadora, cor, kilometragem); // Chama o construtor da superclasse Veiculo
        setQuantidadePassageiros(quantidadePassageiros); // Inicializa a quantidade de passageiros
        setTipoFreio(tipoFreio); // Inicializa o tipo de freio
        setAirbag(airbag); // Inicializa o tipo de airbag
    }

    // Getters e Setters com validações

    public int getQuantidadePassageiros() {
        return quantidadePassageiros; // Retorna a quantidade de passageiros
    }

    public void setQuantidadePassageiros(int quantidadePassageiros) {
        if (quantidadePassageiros <= 0) { // Valida se a quantidade de passageiros é válida
            throw new IllegalArgumentException("A quantidade de passageiros deve ser maior que zero."); // Lança exceção se inválido
        }
        this.quantidadePassageiros = quantidadePassageiros; // Define a quantidade de passageiros
    }

    public String getTipoFreio() {
        return tipoFreio; // Retorna o tipo de freio
    }

    public void setTipoFreio(String tipoFreio) {
        if (tipoFreio == null || tipoFreio.isEmpty()) { // Valida se o tipo de freio não é vazio
            throw new IllegalArgumentException("O tipo de freio não pode ser vazio."); // Lança exceção se vazio
        }
        this.tipoFreio = tipoFreio; // Define o tipo de freio
    }

    public String getAirbag() {
        return airbag; // Retorna o tipo de airbag
    }

    public void setAirbag(String airbag) {
        if (airbag == null || airbag.isEmpty()) { // Valida se o tipo de airbag não é vazio
            throw new IllegalArgumentException("O airbag não pode ser vazio."); // Lança exceção se vazio
        }
        this.airbag = airbag; // Define o tipo de airbag
    }

    // Método que retorna a descrição completa do automóvel doméstico, incluindo os dados da superclasse e os específicos
    @Override
    public String descricao() {
        return super.descricao() + "\nQuantidade de Passageiros: " + quantidadePassageiros +
                "\nTipo de Freio: " + tipoFreio + "\nAirbag: " + airbag; // Chama a descrição da superclasse e adiciona detalhes específicos
    }

    // Método para inserir o automóvel doméstico no banco de dados
    public void inserirNoBanco() {
        VeiculoDAO dao = new VeiculoDAO(); // Instancia o DAO (Data Access Object)
        
        // Comando SQL para inserir dados na tabela Veiculos
        String sqlVeiculo = "INSERT INTO Veiculos (modelo, anodeFabricacao, montadora, cor, kilometragem) VALUES (?, ?, ?, ?, ?)";
        
        // Comando SQL para inserir dados específicos do Automóvel Doméstico na tabela AutomovelDomestico
        String sqlAutomovel = "INSERT INTO AutomovelDomestico (idVeiculo, quantidadePassageiros, tipoFreio, airbag) VALUES (?, ?, ?, ?)";

        // Conexão com o banco de dados e execução dos comandos
        try (Connection conn = dao.getConnection(); // Obtém a conexão com o banco de dados
             PreparedStatement stmtVeiculo = conn.prepareStatement(sqlVeiculo, PreparedStatement.RETURN_GENERATED_KEYS); // Preparar o SQL para inserir veículo
             PreparedStatement stmtAutomovel = conn.prepareStatement(sqlAutomovel)) { // Preparar o SQL para inserir automóvel doméstico

            // Inserir dados na tabela Veiculos
            stmtVeiculo.setString(1, getModelo()); // Define o modelo do veículo
            stmtVeiculo.setInt(2, getAnoDeFabricacao()); // Define o ano de fabricação
            stmtVeiculo.setString(3, getMontadora()); // Define a montadora
            stmtVeiculo.setString(4, getCor()); // Define a cor
            stmtVeiculo.setDouble(5, getKilometragem()); // Define a quilometragem
            stmtVeiculo.executeUpdate(); // Executa a inserção dos dados na tabela Veiculos

            // Obtém o idVeiculo gerado automaticamente
            ResultSet generatedKeys = stmtVeiculo.getGeneratedKeys(); // Recupera a chave gerada pela tabela de veículos
            if (generatedKeys.next()) {
                int idVeiculo = generatedKeys.getInt(1); // Obtém o id do veículo

                // Inserir dados na tabela AutomovelDomestico
                stmtAutomovel.setInt(1, idVeiculo); // Define o idVeiculo
                stmtAutomovel.setInt(2, quantidadePassageiros); // Define a quantidade de passageiros
                stmtAutomovel.setString(3, tipoFreio); // Define o tipo de freio
                stmtAutomovel.setString(4, airbag); // Define o tipo de airbag
                stmtAutomovel.executeUpdate(); // Executa a inserção dos dados específicos do automóvel doméstico
            } else {
                throw new SQLException("Falha ao obter o ID do veículo."); // Lança exceção se não conseguir obter o ID do veículo
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime o stack trace em caso de erro
            System.out.println("Erro ao inserir dados: " + e.getMessage()); // Exibe a mensagem de erro
        }
    }
}
