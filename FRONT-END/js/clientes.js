
document.addEventListener('DOMContentLoaded', function () {
    const API_BASE = 'http://localhost:8085/api/clientes';

    cargarClientes();

    document.getElementById('cliente-form').addEventListener('submit', function (e) {
        e.preventDefault();

        const id = document.getElementById('cliente-id').value;
        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const email = document.getElementById('email').value;
        const telefono = document.getElementById('telefono').value;
        const direccion = document.getElementById('direccion').value;

        const cliente = { nombre, apellido, email, telefono, direccion };

        if (id) {
            // Actualizar cliente
            fetch(`http://localhost:8085/api/clientes/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(cliente)
            })
            .then(() => {
                cargarClientes();
                cancelarFormulario();
            });
        } else {
            // Crear cliente
            fetch('http://localhost:8085/api/clientes', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(cliente)
            })
            .then(response => {
                if (response.status === 409) {
                    alert("Ya existe un cliente con ese email.");
                }
                cargarClientes();
                cancelarFormulario();
            });
        }
    });
});

function cargarClientes() {
    fetch('http://localhost:8085/api/clientes')
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById('clientes-tbody');
            tbody.innerHTML = '';
            data.forEach(cliente => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${cliente.id}</td>
                    <td>${cliente.nombre} ${cliente.apellido}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefono}</td>
                    <td>
                        <button class="btn-icon" onclick="editarCliente(${cliente.id}, '${cliente.nombre}', '${cliente.apellido}', '${cliente.email}', '${cliente.telefono}', '${cliente.direccion || ''}')">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button class="btn-icon" onclick="eliminarCliente(${cliente.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        });
}

function mostrarFormulario() {
    document.getElementById('formulario-cliente').style.display = 'block';
    document.getElementById('cliente-id').value = '';
    document.getElementById('nombre').value = '';
    document.getElementById('email').value = '';
    document.getElementById('telefono').value = '';
    document.getElementById('direccion').value = '';
}

function cancelarFormulario() {
    document.getElementById('formulario-cliente').style.display = 'none';
}

function editarCliente(id, nombre, apellido, email, telefono, direccion) {
    mostrarFormulario();
    document.getElementById('cliente-id').value = id;
    document.getElementById('nombre').value = nombre;
    document.getElementById('apellido').value = apellido; 
    document.getElementById('email').value = email;
    document.getElementById('telefono').value = telefono;
    document.getElementById('direccion').value = direccion;
}

function eliminarCliente(id) {
    if (confirm('¿Estás seguro de que quieres eliminar este cliente?')) {
        fetch(`http://localhost:8085/api/clientes/${id}`, {
            method: 'DELETE'
        }).then(() => cargarClientes());
    }
}