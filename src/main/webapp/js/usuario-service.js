UsuarioService = function(){
    //Tabela Usuários (Banco de Dados)
    this.vetorUsuarios = [];
       this.vetorUsuarios[0] = {nome: "José", senha: "123"};
       this.vetorUsuarios[1] = {nome: "Jao", senha: "456"};
       this.vetorUsuarios[2] = {nome: "Maria", senha: "789"};
   // Métodos CRUD 
   //Create
   this.salvaUsuario = function(objetoUsuario){
       this.vetorUsuarios.push(objetoUsuario);
   }
   //Retrieve
   this.obtemUsuarios = function(){
       return this.vetorUsuarios;
   }
   this.obtemUsuarioPorId = function(indice){
       return this.vetorUsuarios[indice];
   }
   //Update
   this.atualizaUsuario = function(indice,objetoUsuarioSenha){
       this.vetorUsuarios[indice] = objetoUsuarioSenha;
   }
   //Delete
   this.excluiUsuario = function(indice){
       this.vetorUsuarios.splice(indice, 1);
   }
}