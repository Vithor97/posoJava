package br.com.bradesco.posoTeatro.model;

import java.util.ArrayList;
import java.util.List;

public class Evento {

	private int codigo;
	private Funcionario funcionario;
	private EmpresaResponsavel empresaResponsavel;
	private Pessoa pessoa;
	private String descricao;
	private TipoEvento tipoEvento;
	private Genero genero;
	private int situacaoEvento;
	

	private List<String> pessoaEvento = new ArrayList<String>();
	private List<String> listaCpfPessoa = new ArrayList<String>();
	
	

	public Evento() {
		setFuncionario(new Funcionario());
		setEmpresaResponsavel(new EmpresaResponsavel());
		setPessoa(new Pessoa());
		setTipoEvento(new TipoEvento());
		setGenero(new Genero());
	}

	public int getSituacaoEvento() {
		return situacaoEvento;
	}

	public void setSituacaoEvento(int situacaoEvento) {
		this.situacaoEvento = situacaoEvento;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public EmpresaResponsavel getEmpresaResponsavel() {
		return empresaResponsavel;
	}

	public void setEmpresaResponsavel(EmpresaResponsavel empresaResponsavel) {
		this.empresaResponsavel = empresaResponsavel;
	}

	public List<String> getPessoaEvento() {
		return pessoaEvento;
	}

	public void setPessoaEvento(List<String> pessoaEvento) {
		this.pessoaEvento = pessoaEvento;
	}

	public List<String> getListaCpfPessoa() {
		return listaCpfPessoa;
	}

	public void setListaCpfPessoa(List<String> listaCpfPessoa) {
		this.listaCpfPessoa = listaCpfPessoa;
	}

	
	
	
	
}