UsuarioController = function(){
           
    this.usuarioService = new UsuarioService();

    // Métodos DOM

    this.modoEditar = false;
    this.indiceEditar = 0;

    this.iniciaTela = function(){
        this.modoEditar = false;
        document.getElementById("txtUsuario").value = "";
        document.getElementById("txtSenha").value = "";
        document.getElementById("txtMensagem").innerHTML = "";
        this.listarUsuarios();

    }
    this.listarUsuarios = function(){
        let todosUsuarios = this.usuarioService.obtemUsuarios();
        this.montarTabelaUsuarios(todosUsuarios);
    }
    this.montarTabelaUsuarios = function(todosUsuarios){
        if (todosUsuarios.length == 0) {
            document.getElementById("txtMensagem").innerHTML = "<strong>Adicione Usuários</strong>";
            document.getElementById("tabelaUsuarios").innerHTML = "";
        } else {
            document.getElementById("txtMensagem").innerHTML = "";
            let texto = "<tr><th>Nome</th><th>Senha</th><th></th><th></th></tr>"; // th deixa em negrito
            for (let i = 0; i < todosUsuarios.length; i++) {
                texto += "<tr>";
                texto += "<td>" + todosUsuarios[i].nome + "</td>";
                texto += "<td>" + todosUsuarios[i].senha + "</td>";
                texto += '<td><input type="button" value="Excluir" onclick="controlador.aoCliecarExcluir(' +i+ ')"></td>';
                texto += '<td><input type="button" value="Editar" onclick="controlador.aoClicarEditar(' +i+ ')"></td>';
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
            if (!this.modoEditar) {
                this.usuarioService.salvaUsuario(objetoUsuarioSenha);
            } else {
                this.usuarioService.atualizaUsuario(this.indiceEditar,objetoUsuarioSenha);
                this.modoEditar = false;        
            }
            this.iniciaTela();
        }
    }
    
    this.aoCliecarExcluir = function(indice){
        this.usuarioService.excluiUsuario(indice);
        this.iniciaTela();
    }
    this.aoClicarEditar = function(indice){
        let objetoUsuarioSenha = this.usuarioService.obtemUsuarioPorId(indice);
        document.getElementById("txtUsuario").value = objetoUsuarioSenha.nome;
        document.getElementById("txtSenha").value = objetoUsuarioSenha.senha;
        this.modoEditar = true;
        this.indiceEditar = indice;
    }
    this.aoClicarCancelar = function(){
        this.iniciaTela();
    } 
}