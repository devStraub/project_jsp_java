<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="template/pageTemplate.jsp"%>

<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/dataTables.bootstrap5.min.css">

    <script>
        $(document).ready(function() {
            $('#dataTable').DataTable({
                "language": {
                    "emptyTable": "Nenhum projeto encontrado",
                    "zeroRecords": "Nenhum projeto correspondente encontrado",
                    "infoEmpty": "Mostrando 0 a 0 de 0 projetos",
                    "sSearch": "Pesquisar projetos:",
                    "sLengthMenu": "Mostrar _MENU_ projetos por página",
                    "sInfo": "Mostrando _START_ a _END_ de _TOTAL_ projetos"
                }
            });

        });
    </script>
</head>

<main>
    <div class="card">
        <div class="card-header d-flex justify-content-between">
            <h5>Lista de Projetos</h5>
            <button type="button" class="btn btn-primary" onclick="window.location='${pageContext.request.contextPath}/${view.moduleName}/edit/0'"><i class="bi bi-plus"></i></button>
        </div>
        <div class="card-body">
            <table id="dataTable" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th align="center">Código</th>
                        <th align="center">Nome</th>
                        <th align="center">Início</th>
                        <th align="center">Previsão</th>
                        <th align="center">Fim</th>
                        <th align="center">Descrição</th>
                        <th align="center">Status</th>
                        <th align="center">Orçamento</th>
                        <th align="center">Risco</th>
                        <th align="center">Gerente</th>
                        <th align="center">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${entityList}">
                        <tr>
                            <td align="right">${item.id}</td>
                            <td>${item.nome}</td>
                            <td align="center">${utils.calendarTableFormat(item.dataInicio)}</td>
                            <td align="center">${utils.calendarTableFormat(item.dataPrevisaoFim)}</td>
                            <td align="center">${utils.calendarTableFormat(item.dataFim)}</td>
                            <td>${item.descricao}</td>
                            <td align="center">${view.getStatusLabel(item.status)}</td>
                            <td align="right">R$ ${item.orcamento != null ? item.orcamento : '0.00'}</td>
                            <td align="center">${view.getRiscoLabel(item.risco)}</td>
                            <td>${item.gerente.nome}</td>
                            <td>
                                <div style="display: flex;">
                                    <button id="editar" type="button" class="btn btn-warning m-1" onclick="window.location='${pageContext.request.contextPath}/${view.moduleName}/edit/${item.id}'">
                                        <i class="bi bi-pencil"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>
