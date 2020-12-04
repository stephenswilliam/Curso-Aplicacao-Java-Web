
//Criando uma Classe (Objeto)

Autenticacao = function(){}

//Criando um Método que pertence a Classe Autenticacao
Autenticacao.prototype.validarCampos = function (){
    
    elementoUsuario = document.getElementById("txtUsuario");
    usuario = elementoUsuario.value;
    

    elementoSenha = document.getElementById("txtSenha");
    senha = elementoSenha.value;
    
    if (usuario != "" && senha != "") {
        return true;
    } else {
        window.alert("Usuario e Senha devem ser preenchidos !");
        return false;
    }
}

// Instanciando o Objeto (Classe) Autenticacao

aut = new Autenticacao();
