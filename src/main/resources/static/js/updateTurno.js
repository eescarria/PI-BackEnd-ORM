window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#turno_id').value,
            paciente: {
                id: document.querySelector('#paciente_id').value,
                apellido: document.querySelector('#paciente_apellido').value,
                nombre: document.querySelector('#paciente_nombre').value,
                documento: document.querySelector('#documento').value,
                fechaIngreso: document.querySelector('#fechaIngreso').value,
                domicilio:{
                    id: document.querySelector('#domicilio_id').value,
                    calle: document.querySelector('#calle').value,
                    numero: document.querySelector('#numero').value,
                    localidad: document.querySelector('#localidad').value,
                    provincia: document.querySelector('#provincia').value
                    },
                email: document.querySelector('#email').value
            },
            odontologo: {
                id: document.querySelector('#odontologo_id').value,
                numeroMatricula: document.querySelector('#numeroMatricula').value,
                nombre: document.querySelector('#odontologo_nombre').value,
                apellido: document.querySelector('#odontologo_apellido').value,
            },
            fecha : document.querySelector('#fecha').value
        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/turnos';
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
          const url = '/turnos/id/'+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#paciente_id').value = turno.paciente.id;
              document.querySelector('#paciente_apellido').value = turno.paciente.apellido;
              document.querySelector('#paciente_nombre').value = turno.paciente.nombre;
              document.querySelector('#documento').value = turno.paciente.documento;
              document.querySelector('#fechaIngreso').value = turno.paciente.fechaIngreso;
              document.querySelector('#domicilio_id').value = turno.paciente.domicilio.id;
              document.querySelector('#calle').value = turno.paciente.domicilio.calle;
              document.querySelector('#numero').value = turno.paciente.domicilio.numero;
              document.querySelector('#localidad').value = turno.paciente.domicilio.localidad;
              document.querySelector('#provincia').value = turno.paciente.domicilio.provincia;
              document.querySelector('#email').value = turno.paciente.email;
              document.querySelector('#odontologo_id').value = turno.odontologo.id;
              document.querySelector('#numeroMatricula').value = turno.odontologo.numeroMatricula;
              document.querySelector('#odontologo_nombre').value = turno.odontologo.nombre;
              document.querySelector('#odontologo_apellido').value = turno.odontologo.apellido;
              document.querySelector('#fecha').value = turno.fecha;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }