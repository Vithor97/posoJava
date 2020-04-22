package br.com.bradesco.posoTeatro.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.bradesco.posoTeatro.posoUtil.DataUtil;
import br.com.bradesco.posoTeatro.posoUtil.Validacoes;


public class Funcionario {
	
	private int codigo;
	private String nome;
	private String cpf;
	private Cargo cargo = new Cargo();
	private Date dataNascimento;
	private String telefone;
	private String email;
	private String senha;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Cargo getCargo() {
		return cargo;
	}
	
	public void setCargo (Cargo cargo) {
		this.cargo = cargo;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDataNascimentoString() {
		if(getDataNascimento() != null) {
			Format f = new SimpleDateFormat("dd/MM/yyyy");
			return f.format(getDataNascimento());
		}
		return "";
	}

	public String getTelefoneFormatado() {
		if (!getTelefone().equals(null)) {
			return getTelefone().replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
		}
		return "";
	}

	public String getCpfFormatado() {
		if(!getCpf().equals(null)) {
			return getCpf().replace(".", "").replace("-", "");
		}
		return "";
	}

	public boolean cpfValido() {
		if (!getCpf().equals(null)) {
			return new Validacoes().cpfValido(getCpf().replace(".", "").replace("-", ""));
		}
		return false;
	}
	
	public boolean emailValido() {
		if(!getEmail().equals(null)) {
			return new Validacoes().validarEmail(getEmail());
		}
		return false;	}

	public boolean nascimentoValido() {
		if(getDataNascimento() != null) {
			return new DataUtil().validarIdade(18, getDataNascimento());
		}
		return false;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
