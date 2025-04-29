document.addEventListener('DOMContentLoaded', function () {
    const API_BASE = 'http://localhost:8085/api/pedidos';


    cargarClientes();
    cargarProductos();
    cargarPedidos();

    // Manejo del formulario
    document.getElementById('pedido-form').addEventListener('submit', function (e) {
        e.preventDefault();

        const id = document.getElementById('pedido-id').value;
        const clienteId = document.getElementById('cliente').value;
        const productoId = document.getElementById('producto').value;
        const direccionEnvio = document.getElementById('direccionEnvio').value;
        const estado = document.getElementById('estado').value;

        const pedido = { cliente: { id: clienteId }, producto: { id: productoId }, direccionEnvio, estado };

        if (id) {
            // Actualizar pedido
            fetch(`http://localhost:8085/api/pedidos/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(pedido)
            })
            .then(() => {
                cargarPedidos();
                cancelarFormulario();
            });
        } else {
            // Crear pedido
            fetch('http://localhost:8085/api/pedidos', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(pedido)
            })
            .then(() => {
                cargarPedidos();
                cancelarFormulario();
            });
        }
    });
});

function cargarClientes() {
    fetch('http://localhost:8085/api/clientes')
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById('cliente');
            data.forEach(cliente => {
                const option = document.createElement('option');
                option.value = cliente.id;
                option.textContent = `${cliente.nombre} ${cliente.apellido}`;
                select.appendChild(option);
            });
        });
}

function cargarProductos() {
    fetch('http://localhost:8085/api/productos')
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById('producto');
            data.forEach(producto => {
                const option = document.createElement('option');
                option.value = producto.id;
                option.textContent = `${producto.nombre}`;
                select.appendChild(option);
            });
        });
}

function cargarPedidos() {
    fetch('http://localhost:8085/api/pedidos')
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById('pedidos-tbody');
            tbody.innerHTML = '';
            data.forEach(pedido => {
                // Comprobamos que los datos existan antes de acceder a ellos
                const clienteNombre = pedido.cliente ? `${pedido.cliente.nombre} ${pedido.cliente.apellido}` : 'Desconocido';
                const productoNombre = pedido.producto ? pedido.producto.nombre : 'Desconocido';
                const productoPrecio = pedido.producto ? pedido.producto.precio : 'No disponible';

                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${pedido.id}</td>
                    <td>${clienteNombre}</td>
                    <td>${productoNombre}</td>
                    <td>${productoPrecio}</td>
                    <td>${pedido.estado}</td>
                    <td>
                        <button class="btn-icon" onclick="editarPedido(${pedido.id}, '${pedido.cliente ? pedido.cliente.id : ''}', '${pedido.producto ? pedido.producto.id : ''}', '${pedido.direccionEnvio}', '${pedido.estado}')">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button class="btn-icon" onclick="eliminarPedido(${pedido.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(error => console.error('Error al cargar pedidos:', error));
}

function mostrarFormulario() {
    document.querySelector('.formulario').style.display = 'block';
    document.getElementById('pedido-id').value = '';
    document.getElementById('cliente').value = '';
    document.getElementById('producto').value = '';
    document.getElementById('monto').value = '';
    document.getElementById('direccionEnvio').value = '';
    document.getElementById('estado').value = 'PENDIENTE';
}

function cancelarFormulario() {
    document.querySelector('.formulario').style.display = 'none';
}

function editarPedido(id, clienteId, productoId, direccionEnvio, estado) {
    mostrarFormulario();
    document.getElementById('pedido-id').value = id;
    document.getElementById('cliente').value = clienteId;
    document.getElementById('producto').value = productoId;
    document.getElementById('direccionEnvio').value = direccionEnvio;
    document.getElementById('estado').value = estado;
}

function eliminarPedido(id) {
    if (confirm('¿Estás seguro de que quieres eliminar este pedido?')) {
        fetch(`http://localhost:8085/api/pedidos/${id}`, {
            method: 'DELETE'
        }).then(() => cargarPedidos());
    }
}

function mostrarMonto() {
    const productoId = document.getElementById('producto').value;
    if (productoId) {
        fetch(`http://localhost:8085/api/productos/${productoId}`)
            .then(res => res.json())
            .then(producto => {
                document.getElementById('monto').value = producto.precio;
            });
    } else {
        document.getElementById('monto').value = '';
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