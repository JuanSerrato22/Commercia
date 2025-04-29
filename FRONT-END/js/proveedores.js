document.addEventListener('DOMContentLoaded', function () {
    const API_BASE = 'http://localhost:8085/api/proveedores';

    cargarProveedores();

    document.getElementById('proveedor-form').addEventListener('submit', function (e) {
        e.preventDefault();

        const id = document.getElementById('proveedor-id').value;
        const nit = document.getElementById('nit').value;        
        const nombre = document.getElementById('nombre').value;
        const telefono = document.getElementById('telefono').value;
        const email = document.getElementById('email').value;
        const direccion = document.getElementById('direccion').value;

        if (!nit || !nombre || !telefono || !email) {
            alert("Todos los campos son obligatorios.");
            return;
        }

                // Validación de formato de email
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("Por favor ingresa un email válido.");
            return;
        }


        const proveedor = { nit, nombre, telefono, email, direccion };

        if (id) {
            // Actualizar proveedor
            fetch(`http://localhost:8085/api/proveedores/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(proveedor)
            })
            .then(() => {
                cargarProveedores();
                cancelarFormulario();
            });
        } else {
            // Crear proveedor
            fetch('http://localhost:8085/api/proveedores', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(proveedor)
            })
            .then(response => {
                if (response.status === 409) {
                    alert("Ya existe un proveedor con ese NIT o email.");
                } else if(response.ok) {
                    mostrarMensajeExito('Proveedor creado exitosamente.');
                    cargarProveedores();
                    cancelarFormulario();
                } else {
                    alert("Hubo un error al crear el proveedor. Intenta nuevamente.");
                }
            });
        }
    });
});

function mostrarMensajeExito(texto) {
    const mensaje = document.getElementById('mensaje-exito');
    mensaje.textContent = texto;
    mensaje.style.display = 'block';

    setTimeout(() => {
        mensaje.style.display = 'none';
    }, 3000);
}

function cargarProveedores() {
    fetch('http://localhost:8085/api/proveedores')
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById('proveedores-tbody');
            tbody.innerHTML = '';
            data.forEach(proveedor => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${proveedor.id}</td>
                    <td>${proveedor.nit}</td>
                    <td>${proveedor.nombre}</td>
                    <td>${proveedor.email}</td>
                    <td>${proveedor.telefono}</td>
                    <td>${proveedor.direccion || ''}</td>
                    <td>
                        <button class="btn-icon" onclick="editarProveedor(${proveedor.id}, '${proveedor.nombre}', '${proveedor.email}', '${proveedor.telefono}', '${proveedor.direccion || ''}')">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button class="btn-icon" onclick="eliminarProveedor(${proveedor.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        });
}

function mostrarFormulario() {
    document.querySelector('.formulario').style.display = 'block';
    document.getElementById('proveedor-id').value = '';
    document.getElementById('nombre').value = '';
    document.getElementById('telefono').value = '';
    document.getElementById('email').value = '';
    document.getElementById('direccion').value = '';
}

function cancelarFormulario() {
    document.querySelector('.formulario').style.display = 'none';
}

function editarProveedor(id, nombre, email, telefono, direccion) {
    mostrarFormulario();
    document.getElementById('proveedor-id').value = id;
    document.getElementById('nombre').value = nombre;
    document.getElementById('telefono').value = telefono;
    document.getElementById('email').value = email;
    document.getElementById('direccion').value = direccion;
}

function eliminarProveedor(id) {
    if (confirm('¿Estás seguro de que quieres eliminar este proveedor?')) {
        fetch(`http://localhost:8085/api/proveedores/${id}`, {
            method: 'DELETE'
        }).then(() => {
            mostrarMensajeExito('Proveedor eliminado exitosamente.');
            cargarProveedores();
        });
    }
}



// Obtener el formulario y el botón de cerrar
const formulario = document.getElementById('formulario');
const closeBtn = document.getElementById('closeBtn');

// Función para cerrar el formulario
function cerrarFormulario() {
  formulario.style.display = 'none';
}

// Cerrar con ESC
document.addEventListener('keydown', (event) => {
  if (event.key === 'Escape') {
    cerrarFormulario();
  }
});

// Cerrar con la X
closeBtn.addEventListener('click', cerrarFormulario);