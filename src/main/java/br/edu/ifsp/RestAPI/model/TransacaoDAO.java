package br.edu.ifsp.RestAPI.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TransacaoDAO {
	
	private DataSource dataSource;

	public TransacaoDAO() throws NamingException {
		InitialContext ctx = new InitialContext();
		dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/Transacao");
	}
	
	public List<Transacao> listar() throws SQLException {
		List<Transacao> tarefas = new ArrayList<>();
		String sql = "SELECT * FROM Transacao_info";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Transacao t = new Transacao(
						rs.getInt("id"), 
						rs.getString("descricao"), 
						rs.getDouble("valor"),
						rs.getBoolean("tipo"),
						rs.getString("categoria"),
						rs.getString("data_transacao")
						);
				tarefas.add(t);
			}
		}
		return tarefas;
	}
	
	public void inserir(Transacao transacao) throws SQLException {
		String sql = "INSERT INTO Transacao_info (descricao, valor, tipo, categoria, data_transacao) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, transacao.getDescricaoTransacao());
			stmt.setDouble(2, transacao.getValor());
			stmt.setBoolean(3, transacao.isTipoTransacao());
			stmt.setString(4, transacao.getCategoriaTransacao());
			stmt.setDate(5, transacao.getDataTransacao());
			stmt.executeUpdate();
		}
	}
	
	public boolean atualizar(int id, Transacao transacao) throws SQLException {
		String sql = "UPDATE Transacao_info SET descricao = ?, valor = ?, tipo = ?, categoria = ? WHERE id = ?";
		int rows;
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, transacao.getDescricaoTransacao());
			stmt.setDouble(2, transacao.getValor());
			stmt.setBoolean(3, transacao.isTipoTransacao());
			stmt.setString(4, transacao.getCategoriaTransacao());
			stmt.setInt(5, id);
			rows = stmt.executeUpdate();
		}
		return rows > 0;
	}

	public boolean deletar(int id) throws SQLException {
		String sql = "DELETE FROM Transacao_info WHERE id = ?";
		int rows;
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			rows = stmt.executeUpdate();
		}
		return rows > 0;
	}
	
	public Transacao buscarPorId(int id) throws Exception {
		String sql = "SELECT * FROM Transacao_info WHERE id = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Transacao transacao = new Transacao();
				transacao.setId(rs.getInt("id"));
				transacao.setDescricaoTransacao("descricao");
				transacao.setValor(rs.getDouble("valor"));
				transacao.setTipoTransacao(rs.getBoolean("tipo"));
				transacao.setCategoriaTransacao("categoria");
				transacao.setDataTransacao(rs.getDate("data_transacao"));
				return transacao;
			}
			return null;
		}
	}

	public List<Transacao> buscarPendentes() throws Exception {
		List<Transacao> tarefas = new ArrayList<>();
		String sql = "SELECT * FROM tarefas WHERE concluida = false";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				Transacao transacao = new Transacao();
				transacao.setId(rs.getInt("id"));
				transacao.setTitulo(rs.getString("titulo"));
				transacao.setDescricao(rs.getString("descricao"));
				transacao.setConcluida(rs.getBoolean("concluida"));
				tarefas.add(transacao);
			}
		}
		return tarefas;
	}
}
