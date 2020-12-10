UsuarioController = function(){
           
    this.usuarioService = new UsuarioService();

    // Métodos DOM

    this.modoEditar = false;
    this.idEditar = 0;

    this.iniciaTela = function(){
        this.modoEditar = false;
        document.getElementById("txtUsuario").value = "";
        document.getElementById("txtSenha").value = "";
        document.getElementById("txtMensagem").innerHTML = "";
        this.listarUsuarios();

    }
    this.listarUsuarios = function(){
        var self = this; // para ser referenciado dentro de outra function
        todosUsuarios = this.usuarioService.obtemUsuarios(function (todosUsuarios) {
                                    self.montarTabelaUsuarios(todosUsuarios);
                                    });
    }

    this.montarTabelaUsuarios = function(todosUsuarios){
        if (todosUsuarios.length == 0) {
            document.getElementById("txtMensagem").innerHTML = "<strong>Adicione Usuários</strong>";
            document.getElementById("tabelaUsuarios").innerHTML = "";
        } else {
            document.getElementById("txtMensagem").innerHTML = "";
            let texto = "<tr><th>Id</th><th>Nome</th><th>Senha</th><th></th><th></th></tr>"; // th deixa em negrito
            for (let i = 0; i < todosUsuarios.length; i++) {
                texto += "<tr>";
                texto += "<td>" + todosUsuarios[i].id + "</td>";
                texto += "<td>" + todosUsuarios[i].nome + "</td>";
                texto += "<td>" + todosUsuarios[i].senha + "</td>";
                texto += '<td><input type="button" value="Excluir" onclick="controlador.aoCliecarExcluir(' +todosUsuarios[i].id+ ')"></td>';
                texto += '<td><input type="button" value="Editar" onclick="controlador.aoClicarEditar(' +todosUsuarios[i].id+ ')"></td>';
                texto += "</tr>";
            }
            document.getElementById("tabelaUsuarios").innerHTML = texto;
        }
    }
    this.obtemUsuarioSenha = function(){
        let nomeSalvar = document.getElementById("txtUsuario").value;
        let senhaSalvar = document.getElementById("txtSenha").value;
        let objetoUsuario = {nome:nomeSalvar,senha:senhaSalvar};
        return objetoUsuario;
    }
    this.testaUsuarioSenha = function(objetoUsuario){
        if (objetoUsuario.nome == "" || objetoUsuario.senha == "") {
            document.getElementById("txtMensagem").innerHTML = "<strong>Campos Usuário e Senha precisam ser preenchidos !</stgrong>";
            return false;
        } else {
            return true;
        }
    }

    this.aoClicarSalvar = function(){
        let objetoUsuarioSenha = this.obtemUsuarioSenha();
        let usuarioSenhaValidos = this.testaUsuarioSenha(objetoUsuarioSenha);
        if (usuarioSenhaValidos){
            var self = this; // para ser referenciado dentro de outra function
            if (!this.modoEditar) {
                this.usuarioService.salvaUsuario(objetoUsuarioSenha,function (){
                                    self.iniciaTela();
                                    window.alert("Inclusão efetuada com sucesso!");})
            } else {
                this.usuarioService.atualizaUsuario(this.idEditar,objetoUsuarioSenha,function (){
                                    self.iniciaTela();
                					window.alert("Alteração efetuada com sucesso!");})
                this.modoEditar = false;
            }
        }
    }
    
    this.aoCliecarExcluir = function(id){
    
    	if (window.confirm("Deseja realmente excluir?")){
	    	var self = this;
	        this.usuarioService.excluiUsuario(id,function (){
	            self.iniciaTela();
	            window.alert("Exclusão efetuada com sucesso!");})
		}
    }
    this.aoClicarEditar = function(id){
        this.usuarioService.obtemUsuarioPorId(id, function (usuario){
            document.getElementById("txtUsuario").value = usuario.nome;
            document.getElementById("txtSenha").value = usuario.senha;

        });
        
        this.modoEditar = true;
        this.idEditar = id;
    }
    this.aoClicarCancelar = function(){
        this.iniciaTela();
    } 
}