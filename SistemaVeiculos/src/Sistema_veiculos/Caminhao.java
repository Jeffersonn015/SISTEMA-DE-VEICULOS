package Sistema_veiculos;

import dao.VeiculoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe Caminhao: Representa um caminhão, que é um tipo específico de veículo
public class Caminhao extends Veiculo {
    private int eixos; // quantidade de eixos do caminhão
    private int pesoBruto; // peso bruto do caminhão

    // Construtor que recebe todos os parâmetros necessários para criar um caminhão
    public Caminhao(String modelo, int anodeFabricacao, String montadora, String cor, double kilometragem, int eixos, int pesoBruto) {
        super(modelo, anodeFabricacao, montadora, cor, kilometragem); // Chama o construtor da superclasse Veiculo
        setEixos(eixos); // Setando a quantidade de eixos do caminhão
        setPesoBruto(pesoBruto); // Setando o peso bruto do caminhão
    }

    // Métodos Getter e Setter com validações para os atributos específicos de Caminhão

    public int getEixos() {
        return eixos; // Retorna a quantidade de eixos do caminhão
    }

    public void setEixos(int eixos) {
        // Valida se a quantidade de eixos é maior que zero
        if (eixos <= 0) { 
            throw new IllegalArgumentException("A quantidade de eixos deve ser maior que zero."); // Se inválido, lança erro
        }
        this.eixos = eixos; // Se válido, define a quantidade de eixos
    }

    public int getPesoBruto() {
        return pesoBruto; // Retorna o peso bruto do caminhão
    }

    public void setPesoBruto(int pesoBruto) {
        // Valida se o peso bruto é maior que zero
        if (pesoBruto <= 0) { 
            throw new IllegalArgumentException("O peso bruto deve ser maior que zero."); // Se inválido, lança erro
        }
        this.pesoBruto = pesoBruto; // Se válido, define o peso bruto
    }

    // Sobrescreve o método descricao() da superclasse Veiculo para adicionar informações específicas do Caminhão
    @Override
    public String descricao() {
        return super.descricao() + "\nEixos: " + eixos + "\nPeso Bruto: " + pesoBruto; // Chama a descrição da superclasse e adiciona as informações específicas
    }

    // Método para inserir os dados do caminhão no banco de dados
    public void inserirNoBanco() {
        VeiculoDAO dao = new VeiculoDAO(); // Criação de uma instância do DAO para interagir com o banco de dados
        // Definição da query SQL para inserção dos dados do caminhão
        String sqlCaminhao = String.format("INSERT INTO Caminhoes (modelo, anodeFabricacao, montadora, cor, kilometragem, eixos, pesoBruto) " +
                                           "VALUES (?, ?, ?, ?, ?, ?, ?);");

        try (Connection conn = dao.getConnection(); // Obtém uma conexão com o banco de dados
             PreparedStatement stmtCaminhao = conn.prepareStatement(sqlCaminhao)) { // Prepara a declaração SQL

            // Define os valores para os parâmetros da query SQL
            stmtCaminhao.setString(1, getModelo()); // Passa o modelo do caminhão
            stmtCaminhao.setInt(2, getAnoDeFabricacao()); // Passa o ano de fabricação do caminhão
            stmtCaminhao.setString(3, getMontadora()); // Passa a montadora do caminhão
            stmtCaminhao.setString(4, getCor()); // Passa a cor do caminhão
            stmtCaminhao.setDouble(5, getKilometragem()); // Passa a quilometragem do caminhão
            stmtCaminhao.setInt(6, eixos); // Passa a quantidade de eixos do caminhão
            stmtCaminhao.setInt(7, pesoBruto); // Passa o peso bruto do caminhão

            // Executa a query de inserção no banco de dados
            stmtCaminhao.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe detalhes do erro caso haja uma falha ao inserir no banco de dados
            System.out.println("Erro ao inserir dados: " + e.getMessage()); // Mensagem de erro mais amigável
        }
    }
}
