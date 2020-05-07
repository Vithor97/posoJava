package br.com.bradesco.posoTeatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import br.com.bradesco.posoTeatro.model.Evento;
import br.com.bradesco.posoTeatro.model.Funcionario;
import br.com.bradesco.posoTeatro.model.Pessoa;
import br.com.bradesco.posoTeatro.model.TipoEvento;

public class EventoDao extends Evento {

	public String cadastrar(Evento evento) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			//busca func atraves do cpf
			PreparedStatement smt0 = conn.prepareStatement("select * from funcionario where cpf_func = '"
					+ evento.getFuncionario().getCpf().replace(".", "").replace("-", "") + "'");
			ResultSet rs0 = smt0.executeQuery();
			if (rs0.next()) {
				evento.getFuncionario().setCodigo(Integer.parseInt(rs0.getString("cod_func")));
			}
			rs0.close();
			smt0.close();
			//busca empresa atraves do cnpj
			PreparedStatement smt1 = conn.prepareStatement("select * from empresa_responsavel where cnpj_empresa = '"
					+ evento.getEmpresaResponsavel().getCnpj().replace(".", "").replace("-", "").replace("/", "") + "'");
			ResultSet rs1 = smt1.executeQuery();
			if (rs1.next()) {
				evento.getEmpresaResponsavel().setCodigo(Integer.parseInt(rs1.getString("cod_empresa")));
			}
			rs1.close();
			smt1.close();
			//busca tipo evento atraves do nome
			PreparedStatement smt2 = conn.prepareStatement("select * from tipo_evento where nome_tipo = '"
					+ evento.getTipoEvento().getNome()+ "'");
			ResultSet rsTipo = smt2.executeQuery();
			if (rsTipo.next()) {
				evento.getTipoEvento().setCodigo(Integer.parseInt(rsTipo.getString("cod_tipo")));
			}
			rsTipo.close();
			smt2.close();
			//busca genero atraves do nome
			PreparedStatement smt3 = conn.prepareStatement("select * from genero where nome_genero = '"
					+ evento.getGenero().getNome()+ "'");
			ResultSet rsGenr = smt3.executeQuery();
			if (rsGenr.next()) {
				evento.getGenero().setCodigo(Integer.parseInt(rsGenr.getString("cod_genero")));
			}
			rsGenr.close();
			smt3.close();
			//insere
			PreparedStatement smt4 = conn.prepareStatement("insert into evento values (?, ?, ?, ?, ?, default)");

			smt4.setInt(1, evento.getEmpresaResponsavel().getCodigo());
			smt4.setInt(2, evento.getTipoEvento().getCodigo());
			smt4.setInt(3, evento.getGenero().getCodigo());
			smt4.setInt(4, evento.getFuncionario().getCodigo());
			smt4.setString(5, evento.getDescricao());


			smt4.executeUpdate();
			smt4.close();
			//busca o cod do evento que foi cadastrado para ser usado no cad pessoas evento
			PreparedStatement smt5 = conn.prepareStatement("select top 1 * FROM evento order by cod_evento desc");
			ResultSet rs2 = smt5.executeQuery();
			if (rs2.next()) {
				evento.setCodigo(Integer.parseInt(rs2.getString("cod_evento")));
			}
			rs2.close();
			smt5.close();
			
			conn.close();

			return "Evento cadastrado.";

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro.";
		}
	}

	public String voltarCnpj(Evento evento) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement smt0;
		try {
			smt0 = conn.prepareStatement("select * from empresa_responsavel where cnpj_empresa = '"
					+ evento.getEmpresaResponsavel().getCnpj().replace(".", "").replace("-", "").replace("/", "") + "'");
			ResultSet rs0 = smt0.executeQuery();
			if (rs0.next()) {
				evento.getEmpresaResponsavel().setNome(rs0.getString("nomef_empresa"));
			}else {
				evento.getEmpresaResponsavel().setNome("CNPJ nao encontrado na base de dados.");
			}
			rs0.close();
			smt0.close();
			conn.close();
			return "CNPJ encontrado.";
		} catch (SQLException e) {
			e.printStackTrace();
			return "CNPJ nao encontrado.";
		}
	}
	
	public ArrayList<TipoEvento> listarTipos() {
		ArrayList<TipoEvento> tiposEvento = new ArrayList<TipoEvento>();
		
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select * from tipo_evento");
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				TipoEvento tipoEvento = new TipoEvento();
				tipoEvento.setCodigo(rs.getInt("cod_tipo"));
				tipoEvento.setNome(rs.getString("nome_tipo"));
				tiposEvento.add(tipoEvento);
			}
		rs.close();
		smt.close();
		conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tiposEvento;
	}

	public String voltarCpf(Evento evento) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement smt0;
		try {
			smt0 = conn.prepareStatement("select * from funcionario where cpf_func = '"
					+ evento.getFuncionario().getCpf().replace(".", "").replace("-", "").replace("/", "") + "'");
			ResultSet rs0 = smt0.executeQuery();
			if (rs0.next()) {
				evento.getFuncionario().setNome(rs0.getString("nome_func"));
			}else {
				evento.getFuncionario().setNome("CPF nao encontrado na base de dados.");
			}
			rs0.close();
			smt0.close();
			conn.close();
			return "CPF encontrado.";
		} catch (SQLException e) {
			e.printStackTrace();
			return "CPF nao encontrado.";
		}
	}
	
	public String voltarPessoaEvento(Evento evento) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement smt0;
		try {
			smt0 = conn.prepareStatement("select * from pessoa where cpf_pessoa = '"
					+ evento.getPessoa().getCpf().replace(".", "").replace("-", "").replace("/", "") + "'");
			ResultSet rs0 = smt0.executeQuery();
			if (rs0.next()) {
				evento.getPessoa().setNome(rs0.getString("nome_pessoa"));
				evento.getListaCpfPessoa().add(evento.getPessoa().getCpf().replace(".", "").replace("-", "").replace("/", ""));
			}else {
				evento.getPessoa().setNome("*");
			}
			rs0.close();
			smt0.close();
			conn.close();
			return "CPF encontrado.";
		} catch (SQLException e) {
			e.printStackTrace();
			return "CPF nao encontrado.";
		}
	}
	
	public String cadastrarPessoaEvento(Evento evento) {
		try {
			PreparedStatement smt0;
			Connection conn = new ConnectionFactory().getConnection();
			for (int i = 0; i < evento.getListaCpfPessoa().size(); i++) {
				String cpfPessoa = evento.getListaCpfPessoa().get(i);
				smt0 = conn.prepareStatement("select * from pessoa where cpf_pessoa = '"+cpfPessoa+"'");
				ResultSet rs0 = smt0.executeQuery();
				if (rs0.next()) {
					evento.getPessoa().setCodigo(Integer.parseInt(rs0.getString("cod_pessoa")));
				}
					smt0.close();
					PreparedStatement smt1 = conn.prepareStatement("insert into pessoa_evento values (?, ?)");

					smt1.setInt(1, evento.getPessoa().getCodigo());
					smt1.setInt(2, evento.getCodigo());
					smt1.executeUpdate();
					smt1.close();	
			}
			
			conn.close();

			return "Evento cadastrado.";

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro no cadastro de Elenco";
		}
	}
	
	
	public ArrayList<Evento> listar(String nome) {

        ArrayList<Evento> eventos = new ArrayList<Evento>();
        try {
            Connection conn = new ConnectionFactory().getConnection();

            FacesContext context = FacesContext.getCurrentInstance();
            Funcionario funcionario = (Funcionario) context.getExternalContext().getSessionMap().get("funcionarioLogado");
            String validacao = "where ";

            if (funcionario.getCargo().getCodigo() == 2) {
                if (!nome.trim().isEmpty()) {
                    validacao += "desc_evento like '" + nome + "%' and ";
                }
                validacao += " cod_func = " + funcionario.getCodigo() + " and ";
            } else if (!nome.trim().isEmpty()) {
                validacao += "desc_evento like '" + nome + "%' and ";
            }
            
            validacao += " situacao_evento = 1";

            PreparedStatement smt = conn.prepareStatement("select cod_evento, desc_evento from evento " + validacao);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Evento evento = new Evento();
                evento.setCodigo(rs.getInt("cod_evento"));
                evento.setDescricao(rs.getString("desc_evento"));
                eventos.add(evento);
            }
            rs.close();
            smt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventos;
    }

	public ArrayList<Evento> listar(Funcionario funcionario) {
	
	    ArrayList<Evento> eventos = new ArrayList<Evento>();
	    try {
	        Connection conn = new ConnectionFactory().getConnection();
	        PreparedStatement smt = conn.prepareStatement("select * from Evento where situacao_evento = 1 and cod_func = " + funcionario.getCodigo());
	        ResultSet rs = smt.executeQuery();
	        while (rs.next()) {
	            Evento evento = new Evento();
	            evento.setCodigo(rs.getInt("cod_evento"));
	            evento.setDescricao(rs.getString("desc_evento"));
	            eventos.add(evento);
	        }
	        rs.close();
	        smt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	    return eventos;
	}
	
	public ArrayList<Pessoa> listarPessoasDoEvento(Evento evento) {

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select b.* from pessoa_evento a inner join pessoa b on a.cod_pessoa = b.cod_pessoa where a.cod_evento = " + evento.getCodigo());
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setCodigo(rs.getInt("cod_pessoa"));
				pessoa.setNome(rs.getString("nome_pessoa"));
				String cpf = rs.getString("cpf_pessoa");
				pessoa.setCpf(cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9,11));
				pessoas.add(pessoa);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pessoas;
	}
	
	public Evento consultar(Evento evento) {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("select * from evento where cod_evento = " + evento.getCodigo());
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				evento.setDescricao(rs.getString("desc_evento"));
				evento.getFuncionario().setCodigo(rs.getInt("cod_func"));
				evento.setFuncionario(new FuncionarioDao().consultarFuncionario(evento.getFuncionario()));
				evento.getEmpresaResponsavel().setCodigo(rs.getInt("cod_empresa"));
				evento.setEmpresaResponsavel(new EmpresaDao().consultar(evento.getEmpresaResponsavel()));
				evento.getGenero().setCodigo(rs.getInt("cod_genero"));
				evento.getTipoEvento().setCodigo(rs.getInt("cod_tipo"));
				evento.setGenero(new GeneroDao().consultar(evento.getGenero()));
				evento.setTipoEvento(new TipoEventoDao().consultar(evento.getTipoEvento()));
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return evento;
	}
	
	public int alterar(Evento eventoAltera) {
		int rs = 0;
		
		try {
			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement("Update evento set desc_evento = ?, cod_tipo = ?, cod_genero = ?, cod_empresa = ?, cod_func = ? where cod_evento = ?");
			
			smt.setString(1, eventoAltera.getDescricao());
			smt.setInt(2, eventoAltera.getTipoEvento().getCodigo());
			smt.setInt(3, eventoAltera.getGenero().getCodigo());
			smt.setInt(4, eventoAltera.getEmpresaResponsavel().getCodigo());
			smt.setInt(5, eventoAltera.getFuncionario().getCodigo());
			smt.setInt(6, eventoAltera.getCodigo());

			rs = smt.executeUpdate();
			
			
			System.out.println("rows affected " + rs);
			
		} catch (Exception e) {
			System.out.println("dentro do catch: " + e.getMessage());
		}
		
		return rs;				
	}
	
	Connection conn = new ConnectionFactory().getConnection();
	
	public int excluirEvento(Evento evento) {
		
		int rowAffected = 0;
		
		try {
			Connection conn = new ConnectionFactory().getConnection();

			PreparedStatement smt = conn.prepareStatement("update evento set situacao_evento = ? where cod_evento = ?");
			
			smt.setInt(1, 0);
			smt.setInt(2, evento.getCodigo());

			rowAffected = smt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		
			return rowAffected;
		
	}
	
	public ArrayList<Evento> listarEventosPessoa(Pessoa pessoa) {

		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String query = ("SELECT e.cod_evento as codigo, e.desc_evento as descricao, g.nome_genero as genero, t.nome_tipo as tipo "
						+ "FROM Pessoa_Evento a, Pessoa p, Evento e, Genero g, Tipo_Evento t "
						+ "where a.cod_pessoa = p.cod_pessoa "
						+ "and e.cod_evento = a.cod_evento "
						+ "and g.cod_genero = e.cod_genero "
						+ "and t.cod_tipo = e.cod_tipo "
						+ "and a.cod_pessoa = " + pessoa.getCodigo() 
						+ "union select c.cod_evento as codigo, c.desc_evento as descricao, e.nome_genero as genero, d.nome_tipo as tipo "
						+ "from Ingresso a inner join Sessao b on a.cod_sessao = b.cod_sessao "
						+ "inner join Evento c on b.cod_evento = c.cod_evento "
						+ "inner join Tipo_Evento d on c.cod_tipo = d.cod_tipo "
						+ "inner join Genero e on c.cod_genero = e.cod_genero " 
						+ "where a.cod_pessoa = " + pessoa.getCodigo());

		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement smt = conn.prepareStatement(query);
			ResultSet rs = smt.executeQuery();
			while (rs.next()) {
				Evento evento = new Evento();
				evento.setCodigo(rs.getInt("codigo"));
				evento.setDescricao(rs.getString("descricao"));
				evento.getGenero().setNome(rs.getString("genero"));
				evento.getTipoEvento().setNome(rs.getString("tipo"));
				eventos.add(evento);
			}
			rs.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventos;
	}
}