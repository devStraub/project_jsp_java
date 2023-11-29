package com.cgpm.api.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.cgpm.api.base.BaseEntity;
import com.cgpm.api.model.dto.ProjetoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author michel.pech
 */

@Entity
@Table(name = "projeto")
public class Projeto extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -4250923798041679221L;
	
	private String nome;
	private Calendar dataInicio;
	private Calendar dataPrevisaoFim;
	private Calendar dataFim;
	private String descricao;
	private String status;
	private BigDecimal orcamento;
	private String risco;
	private Pessoa gerente;
	
	@Autowired
	private ModelMapper modelMapper;	
	
	public Projeto() {}

	public Projeto(Long id) {
		this.setId(id);
	}
	
	public Projeto(ProjetoDTO dto) {
		modelMapper.map(dto, this);
	}	
	
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_inicio")
	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_previsao_fim")
	public Calendar getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(Calendar dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "data_fim")
	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	
	@Column(name = "descricao")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "orcamento")
	public BigDecimal getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}
	
	@Column(name = "risco")
	public String getRisco() {
		return risco;
	}

	public void setRisco(String risco) {
		this.risco = risco;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idgerente")	
	public Pessoa getGerente() {
		return gerente;
	}

	public void setGerente(Pessoa gerente) {
		this.gerente = gerente;
	}
			
	
}
