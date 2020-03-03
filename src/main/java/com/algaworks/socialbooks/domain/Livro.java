package com.algaworks.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Representa um Livro")
@Entity
public class Livro {

	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "ID de um livro", example = "1")
	private Long id;
	
	@NotEmpty(message = "O campo nome deve ser preenchido")
	@ApiModelProperty( example = "Uberlândia")
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo Data de publicação deve ser preenchido")
	@ApiModelProperty(value = "Data de publicação do Livro")
	private Date publicacao;
	
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O campo Editora deve ser preenchido")
	@ApiModelProperty(example = "AlgaWorks")
	private String editora;
	
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O campo resumo deve ser preenchido")
	@Size(max = 1500, message = "Não deve conter mais que 1500 caracteres")
	@ApiModelProperty(value = "Resumo de um livro")
	private String resumo;
	
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany( mappedBy = "livro")
	private List<Comentario>comentarios;
	
	@ManyToOne
	@JoinColumn(name = "Autor_Id")
	@JsonInclude(Include.NON_NULL)
	private Autor autor;
	
	
	public Livro() {
		
	}
	
	public Livro(String nome) {
		this.nome=nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getPublicacao() {
		return publicacao;
	}
	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
