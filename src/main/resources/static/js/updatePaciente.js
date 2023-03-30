window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        let pacienteId = document.querySelector('#paciente_id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#paciente_id').value,
            apellido: document.querySelector('#paciente_apellido').value,
            nombre: document.querySelector('#paciente_nombre').value,
            documento: document.querySelector('#documento').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio:{
                id: document.querySelector('#domicilio_id').value,
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                ciudad: document.querySelector('#ciudad').value,
                departamento: document.querySelector('#departamento').value
            },
            email: document.querySelector('#email').value

        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/pacientes';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
    function findBy(id) {
          const url = '/pacientes/id'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let paciente = data;
              console.log(paciente)
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#paciente_apellido').value = paciente.apellido;
              document.querySelector('#paciente_nombre').value = paciente.nombre;
              document.querySelector('#documento').value = paciente.documento;
              document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
              document.querySelector('#domicilio_id').value = paciente.domicilio.id;
              document.querySelector('#calle').value = paciente.domicilio.calle;
              document.querySelector('#numero').value = paciente.domicilio.numero;
              document.querySelector('#ciudad').value = paciente.domicilio.ciudad;
              document.querySelector('#departamento').value = paciente.domicilio.departamento;
              document.querySelector('#email').value = paciente.email;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }