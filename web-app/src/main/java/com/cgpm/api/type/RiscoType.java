package com.cgpm.api.type;

public enum RiscoType {
	
	BAIXO_RISCO("Baixo Risco"),
	MEDIO_RISCO("Médio Risco"),
	ALTO_RISCO("Alto Risco");
	
	private String label;
	
	RiscoType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}		
	
}
