UsuarioService = function(){
    
   // Métodos CRUD 
   //Create
   this.salvaUsuario = function(usuario,cb){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                cb ();
            }
        };
        parametro = "usucontroller?";
        parametro += "&nome=";
        parametro += usuario.nome;
        parametro += "&senha="
        parametro += usuario.senha;
        xhttp.open("POST", parametro, true);
        xhttp.send();

   }

   //Retrieve
   this.obtemUsuarios = function(cb){

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                vetor = [];
                vetor = JSON.parse(this.responseText);
                cb (vetor);
            }
        };
        xhttp.open("GET", "usucontroller", true);
        xhttp.send();
   }

   this.obtemUsuarioPorId = function(id, cb){
        

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
            	cb (JSON.parse(this.responseText));
                
            }
        };
        parametro = "usucontroller?id=";
        parametro += id;
        xhttp.open("GET", parametro, true);
        xhttp.send();
   }

   //Update
   this.atualizaUsuario = function(id,usuario,cb){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                cb ();
            }
        };
        parametro = "usucontroller?id=";
        parametro += id;
        parametro += "&nome=";
        parametro += usuario.nome;
        parametro += "&senha="
        parametro += usuario.senha;
        xhttp.open("PUT", parametro, true);
        xhttp.send();

   }
   //Delete
   this.excluiUsuario = function(id,cb){
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                cb ();
            }
        };
        parametro = "usucontroller?id=";
        parametro += id;
        xhttp.open("DELETE", parametro, true);
        xhttp.send();

      
   }
}