<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="Scripts/exercicios.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-light_blue.min.css" />
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
    <link rel="stylesheet" href="CSS/Cadastro Aluno.css" type="text/css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

</head>
<body class="fundo">
    <script>
        $(funcion(){
            $('input[name=data_de_nascimento').datapicker();
        });
    </script>
    <div class="mdl-layout mdl-js-layout">
        <header class="mdl-layout__header">
            <div class="mdl-layout__header-row">
                <span class="mdl-layout-title "><img src="Imagens/logo.png" alt="some text" width=120 height=60><span class="rodape">CyberSchool</span></span>
                <div class="mdl-layout-spacer mdl-color--accent"></div>
                <div class="mdl-textfield  mdl-js-textfield mdl-textfield--expandable">
                    <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
                        <i class="material-icons">search</i>
                    </label>
                    <div class="mdl-textfield__expandable-holder">
                        <input class="mdl-textfield__input" type="text" id="search">
                        <label class="mdl-textfield__label " for="search">Enter your query...</label>
                    </div>
                </div>
                <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
                    <i class="material-icons ">more_vert</i>
                </button>
                <ul class="mdl-menu  mdl-color-text--black mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
                    <li class="mdl-menu__item">About</li>
                    <li class="mdl-menu__item">Contato</li>
                    <li class="mdl-menu__item">Legal information</li>
                </ul>
                <label class="mdl-button mdl-js-button mdl-button--icon " for="login">
                    <i class="material-icons">login</i>
                
            </div>
        </header>
        <div class="demo-drawer mdl-layout__drawer mdl-color--blue-100 mdl-color-text--black">
            <header class="demo-drawer-header">
                <img src="Imagens/user.png" class="demo-avatar">
                <div class="demo-avatar-dropdown mdl-typography--text-center">
                    <span>hello@example.com</span>
                </div>
            </header>
            <nav class="demo-navigation mdl-navigation mdl-color--blue-300">
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">home</i>Home</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">description</i>Relatorios</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">delete</i>Trash</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">report</i>Spam</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">forum</i>Forums</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">flag</i>Updates</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">local_offer</i>Promos</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">shopping_cart</i>Purchases</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">people</i>Social</a>
                <div class="mdl-layout-spacer"></div>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--white material-icons"
                        role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
            </nav>
        </div>

        <div class="mdl-layout__content">
            <!-- Todo conteudo do Site. -->
            <main>
                <div class="mdl-cell--8-col-desktop mdl-cell--6-col-tablet mdl-cell--4-col">
                    <div class="area">
                        <h1>Cadastro Aluno</h1>
                        <div class="mdl-grid">
                            <div class="mdl-cell mdl-cell--12-col-desktop mdl-cell--6-col mdl-cell--8-col-tablet cadastro">
                                <form class="formulario" method="POST" action="AlunoController" name="frmAddAluno">
                                    User ID : <input type="text" readonly="readonly" name="codAluno"
                                                        value="<c:out value="${aluno.codAluno}" />"/> <br /><br />
                                    <div>
                                        Nome:<input type="text" name="nome"
                                                    value="<c:out value="${aluno.nome}" />"/> <br /><br />        
                                    </div>
                                    <div>
                                        Data Nascimento:<input type="text" placeholder="dd/MM/yyyy" name="data_de_nascimento" data-date-format="dd/MM/yyyy"
                                                value="<fmt:formatDate pattern="dd/MM/yyyy" value="${user.data_de_nascimento}" />" /> <br /><br />
                                    </div>
                                    <div>
                                        E-mail:<input type="text" name="email"
                                                    value="<c:out value="${aluno.email}" />" /> <br /><br />
                                    </div>
                                    <div>
                                        Nome do Pai:<input type="text" name="pai"
                                                    value="<c:out value="${aluno.nomePai}" />" /> <br /><br />
                                    </div>
                                    <div>
                                        Nome do mae<input type="text" name="mae"
                                                    value="<c:out value="${aluno.nomeMae}" />" /> <br /><br />
                                    </div>
                                    <div>
                                        Nome do Celular: <input type="text" name="celular"
                                                    value="<c:out value="${aluno.celular}" />" /> <br /><br />
                                    </div>
                                    <div>
                                        Celular do Pai: <input type="text" name="celularPai"
                                                    value="<c:out value="${aluno.telefone_pai}" />" /> <br /><br />
                                    </div>
                                    <div>
                                        Celular da Mãe: <input type="text" name="celularMae"
                                                    value="<c:out value="${aluno.telefone_mae}" />" /><br /><br />
                                    </div>
                                    <div>
                                        Genero: <input type="text" name="sexo"
                                                    value="<c:out value="${aluno.sexo}" />" /><br /><br />
                                    </div>
                                    <input type="submit" value="Submit" />

                            </div>
                        </div>
                    </div>
                </div>
            
            </main>
        </div>

        <footer class="mdl-mini-footer mdl-color--accent ">
            <!-- mdl-mini-footer--left-section = alinhado a esquerda -->
            <div class="mdl-mini-footer--left-section">
                <div class="mdl-logo"><img src="Imagens/logo.png" alt="some text" width=100 height=50></div>
                <ul class="mdl-mini-footer--link-list">
                    <li class="mdl-mini-footer--social-btn">
                        <a href="https://twitter.com" class="social-btn social-btn__twitter" role="button" title="Twitter">
                            <img class="mdl-mega-footer--social-btn" src="Imagens/Twitter.png">
                        </a>
                    </li>
                    <li class="mdl-mini-footer--social-btn">
                        <a href="https://github.com" class="social-btn social-btn__github" role="button" title="GitHub">
                            <img class="mdl-mega-footer--social-btn" src="Imagens/git.png">
                        </a>
                    </li>
                    <li class="mdl-mini-footer--social-btn ">
                        <a href="https://www.facebook.com" class="social-btn social-btn__facebook" role="button" title="Facebook">
                            <img class="mdl-mega-footer--social-btn" src="Imagens/face.jpg">
                        </a>
                    </li>
                </ul>
            </div>

            <div class=" mdl-mini-footer--center">
               <span class="rodape"> Desenvolvido por RAPC</span>
            </div>
        </footer>
</body>

</html>
