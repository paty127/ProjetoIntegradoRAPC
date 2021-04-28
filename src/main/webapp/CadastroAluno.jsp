<%-- 
    Document   : CadastroAluno
    Created on : 22 de abr. de 2021, 11:14:11
    Author     : Carlos Pavão
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

</head>
<style>
    *{
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    text-decoration: none;
    font-family: sans-serif;
    font-size: 14px;
}

body{
    width: 100%;
    min-height: 100vh;
    background: url("Imagens/fundo.jpg") center/cover;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
}
    h1 {
        font: bold;
        color: rgb(16, 87, 87);
    }
    .campo {
        background: rgb(255, 255, 255);
    }

    .textoCaixa {
        font-family: Roboto, sans-serif;
        -moz-osx-font-smoothing: grayscale;
        -webkit-font-smoothing: antialiased;
        font-size: 22;
        letter-spacing: normal;
        text-decoration: inherit;
        text-transform: inherit;
        color: #000000;
        font: bold;

    }

    .container {

        width: 100vw;
        height: 100vh;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center
    }

    .box {
        width: 300px;
        height: 300px;

    }
    .rodape{
        font-size: xx-large;
        font: bold;
        color: black;
    }
    .formulario{
        align-items: center;
        text-align: center;
    }

    .group{
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-direction:column;
    width: 70%;
    padding: 1.3rem 0;
    
    }
    .cadastro{
        background-color: rgb(155, 156, 156);
    }
    
    .getSexo{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    padding: 1.3rem 0;
    }
    .sexo{
    display: flex;
    width: 100%;
    justify-content: space-evenly;
    align-items: center;
    padding: 1rem;
    }
</style>
<body class="fundo">
    <jsp:forward page="/AlunoController?action=listUser" />
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
                <div class="group">
                    <h1 class="mdl-cell--12-col-desktop mdl-cell--8-col-tablet mdl-cell--6-col ">Cadastro Aluno</h1>
                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--12-col-desktop mdl-cell--6-col mdl-cell--8-col-tablet cadastro">
                            <form class="formulario " action="${request.contextPath}/exemplo-web" method="post">
                                <input type="hidden" name="id" value="123" />
                                <div>
                                    <label>Nome:</label>
                                    <input type="text" name="nome" placeholder="Preencha o nome completo" />
                                </div>
                                <div>
                                    <label>Data Nascimento:</label>
                                    <input type="date" name="dataNascimento" />
                                </div>
                                <div>
                                    <label>Pai</label>
                                    <input type="text" name="NomePai"/>
                                </div>
                                <div>
                                    <label>Mãe:</label>
                                    <input type="text" name="NomeMae"/>
                                </div>
                                <div>
                                    <label>E-mail:</label>
                                    <input type="email" name="email" placeholder="e-mail do responsável" />
                                </div>
                                <div>
                                    <label>Celular:</label>
                                    <input type="text" name="CelularAluno" placeholder="(11)99999-9999" />
                                </div>
                                <div>
                                    <label>Celular do Pai:</label>
                                    <input type="text" name="celularPai" placeholder="(11)99999-9999" />
                                </div>
                                <div>
                                    <label>Celular da Mãe:</label>
                                    <input type="text" name="celularMae" placeholder="(11)99999-9999" />
                                </div>
                                <div class="getsexo">
                                    <fieldset class="sexo">
                                        <legend class="">Gênero</legend>
                                        <div>
                                            <input   type="radio" name="genero" value="0" id="generoF" />
                                            <label  for="generoF">Feminino</label>
                                        </div>
                                        <div>
                                            <input  type="radio" name="genero" value="1" id="generoM" />
                                            <label  for="generoF">Masculino</label>
                                        </div>
                                    </fieldset>
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


