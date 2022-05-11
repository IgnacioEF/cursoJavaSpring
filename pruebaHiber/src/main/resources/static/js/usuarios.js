// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
    $('#usuarioS').DataTable();

actualizarEmailDelUsuario();

});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

async function cargarUsuarios(){

  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders(),
  });
  const usuarios = await request.json();

    let listadoHtml = '';
    for(usuario of usuarios){
        let borrar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';

        let telefono = usuario.telefono == null ? '-' : usuario.telefono;

        let usuarioHtml = '<tr> <td>'+usuario.id+'</td> <td>'+usuario.nombre+' '
            +usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+telefono
            +'</td> <td> '+ borrar + '</td></tr>';
        listadoHtml += usuarioHtml;
    }

  console.log(usuarios);


  document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

}


function getHeaders(){
    return{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization' : localStorage.token
    }
}

async function eliminarUsuario(id){

if(!confirm('¿seguro que desea eliminar el usuario?')){
    return;
}

  const request = await fetch('api/usuarios/' + id, {
    method: 'DELETE',
    headers: getHeaders(),
  });


  location.reload()

}
