<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="template/pageTemplate.jsp"%>

<head>
	<script>	
	    $(document).ready(function() {
	        // Esconder o botão de deletar se a condição não for atendida
	        if (${entity.id == null}) {
	            $("#restaurar").hide();
	            $("#remover").hide();
	        }
	        
	        // Associar a função save ao evento de clique do botão
	        $("#salvar").on("click", function() {            	        	
	        	$.ajax({
	                type: "POST",
	                url: "${pageContext.request.contextPath}/${view.moduleName}/edit",
	                data: $("#editForm").serialize(),
	                success: function(response) {
	                	window.location.href = "${pageContext.request.contextPath}/projeto/list";
	                	alert('Registro salvo com sucesso.')
	                },
	                error: function(error) {
	                    console.error(error);
	                }
	            });
	        });   
	        
	        // Associar a função remover ao evento de clique do botão
	        $("#remover").on("click", function() {	
	        	if ("${entity.status}" === "INICIADO" || 
	        		"${entity.status}" === "EM_ANDAMENTO" ||
	        		"${entity.status}" === "ENCERRADO"
	        	){
	        		alert('Status do Projeto não permite exclusão')
	        		return
	        	}
	        	
	            $.ajax({
	                type: "POST",
	                url: "${pageContext.request.contextPath}/${view.moduleName}/remove",
	                data: $("#editForm").serialize(),
	                success: function(response) {
	                    window.location.href = "${pageContext.request.contextPath}/projeto/list";
	                },
	                error: function(error) {
	                    
	                }
	            });
	        }); 	        
	    });
	</script>	
</head>

<main>
	<div class="card">
		<div class="card-header d-flex justify-content-between">
			<h5>Projeto #${isEdit ? entity.id : 'Novo'}</h5>
		</div>
		<div class="card-body">
			<div class="container">
				<form id="editForm">
					<div class="row align-items-center">
					  
					  <div class="col">
						  <div class="col-auto">
						    <label for="gerente" class="col-form-label">Gerente</label>
						  </div>
						  <div class="col-auto">
						    <div class="dropdown">
						    	<div class="d-flex">
									<select id="gerente" name="gerente" class="form-select" aria-label="Default select example">
										<option value="${entity.gerente != null ? entity.gerente.id : null}" selected>${entity.gerente != null ? entity.gerente.nome : 'Selecione'}</option>
									  	<c:forEach var="item" items="${funcionarioList}">
									  		<option value="${item.id}">${item.nome}</option>
									  	</c:forEach>										
									</select>							    							    							    
						    	</div>				  		  
							</div>
						  </div>
					  </div>
					  
					  <div class="col">
						  <div class="col-auto">
						    <label for="nome" class="col-form-label">Nome</label>
						  </div>
						  <div class="col-auto">
						    <input id="nome" name="nome" type="text" value="${entity.nome}">
						  </div>
					  </div>
					  
					  <div class="col">
						  <div class="col-auto">
						    <label for="dataInicio" class="col-form-label">Data Início</label>
						  </div>
						  <div class="col-auto">
						    <input id="dataInicio" name="dataInicio" type="date" value="${utils.calendarFormat(entity.dataInicio)}">
						  </div>
					  </div>
					  
					  <div class="col">
						  <div class="col-auto">
						    <label for="dataPrevisaoFim" class="col-form-label">Data Previsão Fim</label>
						  </div>
						  <div class="col-auto">
						    <input id="dataPrevisaoFim" name="dataPrevisaoFim" type="date" value="${utils.calendarFormat(entity.dataPrevisaoFim)}">
						  </div>
					  </div>					  
					  				  
					</div>	
					
					<div class="row align-items-center">
					  
					  <div class="col">
						  <div class="col-auto">
						    <label for="dataFim" class="col-form-label">Data Fim</label>
						  </div>
						  <div class="col-auto">
						    <input id="dataFim" name="dataFim" type="date" value="${utils.calendarFormat(entity.dataFim)}">
						  </div>
					  </div>
					  
					  <div class="col">
						  <div class="col-auto">
						    <label for="status" class="col-form-label">Status</label>
						  </div>
						  <div class="col-auto">
						    <div class="dropdown">
						    	<div class="d-flex">							  
									<select id="status" name="status" class="form-select" aria-label="Default select example">
										<option value="${entity.status != null ? entity.status : null}" selected>${entity.status != null ? view.getStatusLabel(entity.status) : 'Selecione'}</option>
									  	<c:forEach var="item" items="${statusList}">
									  		<option value="${item}">${item.label}</option>
									  	</c:forEach>										
									</select>								  
						    	</div>				  		  
							</div>
						  </div>
					  </div>
					  
					  <div class="col">
						  <div class="col-auto">
						    <label for="orcamento" class="col-form-label">Orçamento</label>
						  </div>
						  <div class="col-auto">
						    <input id="orcamento" name="orcamento" type="number" value="${entity.orcamento}">
						  </div>
					  </div>	
					  
					  <div class="col">
						  <div class="col-auto">
						    <label for="risco" class="col-form-label">Risco</label>
						  </div>
						  <div class="col-auto">
						    <div class="dropdown">
						    	<div class="d-flex">							  
									<select id="risco" name="risco" class="form-select" aria-label="Default select example">
										<option value="${entity.risco != null ? entity.risco : null}" selected>${entity.risco != null ? view.getRiscoLabel(entity.risco) : 'Selecione'}</option>
									  	<c:forEach var="item" items="${riscoList}">
									  		<option value="${item}">${item.label}</option>
									  	</c:forEach>										
									</select>								  
						    	</div>				  		  
							</div>
						  </div>
					  </div>					  				  
					  							  							  					  					  				  
					</div>								
					
					<div class="row align-items-center">
						<div class="col">
							<div class="col-auto">
						    	<label for="descricao" class="col-form-label">Descrição</label>
						  	</div>
						  	<div class="col"> 
						    	<input id="descricao" name="descricao" class="w-100" type="text" value="${entity.descricao}">
						  	</div>
						</div>					
					</div>	
					
					<div class="row align-items-center mt-5">
						<div class="btn-group" role="group" >
						  <button id="voltar" type="button" class="btn btn-primary" onclick="window.location='${pageContext.request.contextPath}/${view.moduleName}/list'">
						  	<i class="bi bi-arrow-left-short"></i>
						  </button>
						  <button id="salvar" type="button" class="btn btn-success">
						  	<i class="bi bi-folder-symlink"></i>
						  </button>
						  <button id="restaurar" type="button" class="btn btn-warning" onclick="window.location='${pageContext.request.contextPath}/${view.moduleName}/edit/${entity.id}'">
						  	<i class="bi bi-arrow-clockwise"></i>
						  </button>
						  <button id="remover" type="button" class="btn btn-danger">
						  	<i class="bi bi-trash"></i>
						  </button>					  
						</div>						
					</div>
				</form>
			</div>
		</div>
	</div>	
</main>