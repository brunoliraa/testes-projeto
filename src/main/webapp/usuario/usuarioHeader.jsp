<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="f" uri="" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: wernnevon
  Date: 11/12/2019
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
<%--    <meta charset="UTF-16BE"/>--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
    <title>Rende Fácil</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="./materialize/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
    <link href="./materialize/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>
<%--<f:view contentType="text/html; charset=ISO-8859-1">--%>
<body>
<nav class="green darken-3">
    <div class="nav-wrapper container">
        <ul class="right hide-on-med-and-down">
            <li>
                <a href="#usuario" id="button-user" class="dropdown-trigger white-text">${sessionScope.login.nome} <i class="material-icons left">person</i></a>
            </li>
            <li><a><form  action="Controller?command=UserNegociacaoCommand" method="post"><button type="action" name="action" id="button-ativos" class="btn-flat white-text">Ativos</button><i class="material-icons left">multiline_chart</i></form></a>
            </li>
            <li>
                <a><form  action="Controller?command=HistoricoNegociacoesCommand" id="button-historico" method="post"><button type="action" name="action" class="btn-flat white-text">Histórico</button><i class="material-icons left">reorder</i></form></a>
            </li>
        </ul>
        <form  action="Controller?command=UserCommand" method="post"><button type="action" name="action" id="button-index" class="btn-flat white-text" style="font-size: 200%">Rende Fácil</button></form>
    </div>
</nav>
<ul id='usuario' class="dropdown-content green lighten-5">
    <li>
        <form  action="Controller?command=UserDadosCommand" method="post"><button type="action" name="action" id="button-dados" class="btn-flat black-text">Dados <i class="material-icons left">storage</i></button></form>
    </li>
    <li><a class="black-text modal-trigger" id="button-carteira" href="#carteira">Carteira <i class="material-icons left">account_balance_wallet</i></a></li>

    <li>
        <form action="Controller?command=LogoutCommand" method="post"><button type="action" id="button-logout" name="action" class="black-text">Logout <i class="material-icons left">exit_to_app</i></button> </form>
    </li>
</ul>
<div style="width: 35%; height: 60%" id="carteira" class="modal green lighten-5">
    <div class="modal-content">
        <div class="green darken-3">
            <label style="font-size: 32pt;" class="white-text">Carteira:</label>
        </div>
        <table>
            <tr>
                <td><b class="text-darken-3 green-text"> Saldo Atual</b></td>
            </tr>
            <tr>
                <td name="carteira">
                    <fmt:setLocale value="pt-BR"/>
                    <fmt:formatNumber type="currency">${sessionScope.login.carteira.valorCaixa}</fmt:formatNumber></td>
            </tr>
        </table>
    </div>
    <div class="modal-footer green lighten-5">
        <a href="#!" id="button-fechar" class="modal-close btn-flat text-darken-3 green-text">Fechar</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="./materialize/js/materialize.js"></script>
<script src="./materialize/js/init.js"></script>
</body>
</html>
