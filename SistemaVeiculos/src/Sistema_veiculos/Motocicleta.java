package Sistema_veiculos;

import dao.VeiculoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe Motocicleta - Representa uma motocicleta que herda de Veiculo
public class Motocicleta extends Veiculo {
    private int cilindradas; // Cilindrada da motocicleta (capacidade do motor)
    private int torque; // Torque da motocicleta (força gerada pelo motor)

    // Construtor que recebe todos os parâmetros necessários
    // Este construtor inicializa a motocicleta com todos os parâmetros fornecidos
    public Motocicleta(String modelo, int anodeFabricacao, String montadora, String cor, double kilometragem, int cilindradas, int torque) {
        super(modelo, anodeFabricacao, montadora, cor, kilometragem); // Chama o construtor da classe mãe (Veiculo) para inicializar atributos comuns
        setCilindradas(cilindradas); // Inicializa a cilindrada da motocicleta
        setTorque(torque); // Inicializa o torque da motocicleta
    }

    // Métodos Getters e Setters com validações

    // Getter para a cilindrada
    public int getCilindradas() {
        return cilindradas; // Retorna a cilindrada da motocicleta
    }

    // Setter para a cilindrada
    public void setCilindradas(int cilindradas) {
        if (cilindradas <= 0) { // Verifica se a cilindrada é válida (deve ser maior que zero)
            throw new IllegalArgumentException("As cilindradas devem ser maiores que zero."); // Lança exceção se inválido
        }
        this.cilindradas = cilindradas; // Atribui o valor se válido
    }

    // Getter para o torque
    public int getTorque() {
        return torque; // Retorna o torque da motocicleta
    }

    // Setter para o torque
    public void setTorque(int torque) {
        if (torque <= 0) { // Verifica se o torque é válido (deve ser maior que zero)
            throw new IllegalArgumentException("O torque deve ser maior que zero."); // Lança exceção se inválido
        }
        this.torque = torque; // Atribui o valor se válido
    }

    // Método de descrição
    // Este método chama a descrição da classe pai (Veiculo) e adiciona informações específicas da motocicleta
    @Override
    public String descricao() {
        return super.descricao() + "\nCilindradas: " + cilindradas + "\nTorque: " + torque; // Retorna a descrição completa com detalhes
    }

    // Método para inserir a motocicleta no banco de dados
    // Usando o DAO para interação com o banco de dados e realizar a inserção
    public void inserirNoBanco() {
        VeiculoDAO dao = new VeiculoDAO(); // Cria uma instância do DAO para comunicação com o banco de dados
        String sqlMotocicleta = String.format("INSERT INTO Motocicletas (modelo, anodeFabricacao, montadora, cor, kilometragem, cilindradas, torque) " +
                                              "VALUES (?, ?, ?, ?, ?, ?, ?);"); // SQL para inserir a motocicleta

        try (Connection conn = dao.getConnection(); // Obtém uma conexão com o banco de dados
             PreparedStatement stmtMotocicleta = conn.prepareStatement(sqlMotocicleta)) { // Prepara o statement SQL

            // Define os valores dos parâmetros da query com base nos atributos da motocicleta
            stmtMotocicleta.setString(1, getModelo());
            stmtMotocicleta.setInt(2, getAnoDeFabricacao());
            stmtMotocicleta.setString(3, getMontadora());
            stmtMotocicleta.setString(4, getCor());
            stmtMotocicleta.setDouble(5, getKilometragem());
            stmtMotocicleta.setInt(6, cilindradas);
            stmtMotocicleta.setInt(7, torque);

            // Executa a inserção na tabela Motocicletas
            stmtMotocicleta.executeUpdate();
        } catch (SQLException e) { // Trata exceções SQL
            e.printStackTrace();
            System.out.println("Erro ao inserir dados: " + e.getMessage()); // Exibe erro se ocorrer uma exceção
        }
    }
}
