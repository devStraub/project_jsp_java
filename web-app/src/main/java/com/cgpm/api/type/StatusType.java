package com.cgpm.api.type;

public enum StatusType {
	
	EM_ANALISE("Em Análise"),
	ANALISE_REALIZADA("Análise Realizada"),
	ANALISE_APROVADA("Análise Aprovada"),
	INICIADO("Iniciado"),
	PLANEJADO("Planejado"),
	EM_ANDAMENTO("Em Andamento"),
	ENCERRADO("Encerrado"),
	CANCELADO("Cancelado");
	
	private String label;
	
	StatusType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
		
}
