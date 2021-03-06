package beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Pessoa;
import modelo.PessoaFisica;
import modelo.PessoaJuridica;
import modelo.Sexo;

@ManagedBean
@SessionScoped
public class CadastroPessoasBean {
	private Pessoa pessoaSelecionada;
	private Collection<Pessoa> lista;
	private String tipoNovaPessoa;

	
	public CadastroPessoasBean() {
		pessoaSelecionada = new PessoaFisica();
		lista = new ArrayList<Pessoa>();
	
	
		for(int x=0; x<10; x++) {
			Pessoa p = (x%2==0) ? new PessoaFisica() : new PessoaJuridica();
			p.setNome(String.format("Fulano %02d", x));
			p.setEmail(String.format("Fulano%02d@teste.com", x));
			p.setNome(String.format("9999.8888%02d", x));
			
			lista.add(p);
		}
	}
	
	public void criar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if(tipoNovaPessoa == null) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Você deve especificar o tipo", ""));
			return;
		}
		if(tipoNovaPessoa.equals("PF")) {
			pessoaSelecionada = new PessoaFisica();
		}else if(tipoNovaPessoa.equals("PJ")) {
			pessoaSelecionada = new PessoaJuridica();
		}
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa criada com sucesso", ""));
	}
	
	public void salvar() {
		if(!lista.contains(pessoaSelecionada )) {
			lista.add(pessoaSelecionada);
		}
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso!", ""));
	}
	
	public String cancelar() {
		pessoaSelecionada = null;
		tipoNovaPessoa = null;
		return "primeiro.jsf";
	}
	
	public void excluir() {
		lista.remove(pessoaSelecionada);
		pessoaSelecionada = null;
		FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage(FacesMessage.SEVERITY_INFO,"Pessoa excluida com sucesso!","" ));
	}
	
	
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}
	public Collection<Pessoa> getLista() {
		return lista;
	}
	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	public void setLista(Collection<Pessoa> lista) {
		this.lista = lista;
	}
	

	public String getTipoNovaPessoa() {
		return tipoNovaPessoa;
	}

	public void setTipoNovaPessoa(String tipoNovaPessoa) {
		this.tipoNovaPessoa = tipoNovaPessoa;
	}
	
	// Métodos de atributos inexistentes 
	public boolean isPessoaFisicaSelecionada() {
		return pessoaSelecionada instanceof PessoaFisica;
	}
	
	public boolean isPessoaJuridicaSelecionada() {
		return pessoaSelecionada instanceof PessoaJuridica;
	}
	

	public Sexo[] getSexos() {
		return Sexo.values();
	}
	
	
}
