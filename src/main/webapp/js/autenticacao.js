
//Criando uma Classe (Objeto)

Autenticacao = function(){

    //Criando um Método dentro da Classe Autenticacao
    this.validarCampos = function (){
    
        elementoUsuario = document.getElementById("txtUsuario");
        usuario = elementoUsuario.value;
    
        elementoSenha = document.getElementById("txtSenha");
        senha = elementoSenha.value;
    
        if (usuario != "" && senha != "") {
        	window.open("cadastro-usuario.html");
        	return true;
        } else {
            document.getElementById("txtMensagem").innerHTML = "<strong>Usuário e Senha devem ser preenchidos !</strong>";
            return false;
        }
    }
}
// Instanciando o Objeto (Classe) Autenticacao

aut = new Autenticacao();
