package Sistema_veiculos;

import dao.VeiculoDAO;

// Classe Skates
public class Skate {
    private String modelo;     // modelo do skate
    private String marca;      // marca do skate
    private String cor;        // cor do skate
    private String tipoRodas;  // tipo das rodas do skate

    // Construtor da classe Skates
    public Skate(String modelo, String marca, String cor, String tipoRodas) {
        setModelo(modelo);        // setando o modelo
        setMarca(marca);          // setando a marca
        setCor(cor);              // setando a cor
        setTipoRodas(tipoRodas);  // setando o tipo das rodas
    }

    // Getters e Setters com validações
    public String getModelo() {
        return modelo; // devolve o modelo
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) { // checando se o modelo é válido
            throw new IllegalArgumentException("O modelo não pode ser nulo ou vazio."); // se não for válido, lança erro
        }
        this.modelo = modelo; // se for válido, setamos
    }

    public String getMarca() {
        return marca; // devolve a marca
    }

    public void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) { // checando se a marca é válida
            throw new IllegalArgumentException("A marca não pode ser nula ou vazia."); // se não for válida, lança erro
        }
        this.marca = marca; // se for válida, setamos
    }

    public String getCor() {
        return cor; // devolve a cor
    }

    public void setCor(String cor) {
        if (cor == null || cor.trim().isEmpty()) { // checando se a cor é válida
            throw new IllegalArgumentException("A cor não pode ser nula ou vazia."); // se não for válida, mostra erro
        }
        this.cor = cor; // se for válida, setamos
    }

    public String getTipoRodas() {
        return tipoRodas; // devolve o tipo das rodas
    }

    public void setTipoRodas(String tipoRodas) {
        if (tipoRodas == null || tipoRodas.trim().isEmpty()) { // checando se o tipo das rodas é válido
            throw new IllegalArgumentException("O tipo das rodas não pode ser nulo ou vazio."); // se não for válida, lança erro
        }
        this.tipoRodas = tipoRodas; // se for válida, setamos
    }

    public String descricao() {
        return "\nModelo: " + modelo + "\nMarca: " + marca + "\nCor: " + cor + "\nTipo das Rodas: " + tipoRodas; // retorna a descrição do skate
    }

    // Método para inserir o skate no banco de dados
 // Método para inserir o skate no banco de dados usando VeiculoDAO
    public void inserirNoBanco() {
        VeiculoDAO dao = new VeiculoDAO();
        String sql = String.format("INSERT INTO Skates (modelo, marca, cor, tipoRodas) VALUES ('%s', '%s', '%s', '%s');",
                                   modelo, marca, cor, tipoRodas);
        dao.inserirVeiculo(sql);
    }
}
