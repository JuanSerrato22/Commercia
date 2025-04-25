const API_URL = 'http://localhost:8085/api/pedidos';
const clienteSelect = document.getElementById('cliente');
const productoSelect = document.getElementById('producto');
const precioInput = document.getElementById('precio'); // Para el precio del producto
const tbody = document.getElementById('pedidos-tbody');
const form = document.getElementById('pedido-form');
const formularioPedido = document.getElementById('formulario-pedido');


function cargarPedidos() {
    fetch('http://localhost:8085/api/pedidos')
        .then(res => res.json())
        .then(data => {
            tbody.innerHTML = '';
            data.forEach(pedido => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${pedido.id}</td>
                    <td>${pedido.cliente.nombre} ${pedido.cliente.apellido}</td>
                    <td>${pedido.fecha}</td>
                    <td>${pedido.direccionEnvio}</td>
                    <td>
                        <button onclick="editarPedido(${pedido.id})">Editar</button>
                        <button onclick="eliminarPedido(${pedido.id})">Eliminar</button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        });
}


function cargarClientes() {
    return fetch('http://localhost:8085/api/clientes')
        .then(res => res.json())
        .then(data => {
            clienteSelect.innerHTML = '';
            data.forEach(cat => {
                const option = document.createElement('option');
                option.value = cat.id, cat.nombre, cat.apellido;
                option.textContent = cat.apellido;
                clienteSelect.appendChild(option);
            });
        });
}

function cargarProductos() {
    return fetch('http://localhost:8085/api/productos')
        .then(res => res.json())
        .then(data => {
            productoSelect.innerHTML = '';
            data.forEach(producto => {
                const option = document.createElement('option');
                option.value = producto.id;
                option.textContent = `${producto.nombre} - $${producto.precio}`;
                productoSelect.appendChild(option);
            });
        });
}

function mostrarFormulario() {
    form.reset();
    document.getElementById('pedido-id').value = '';
    precioInput.value = ''; // Resetear el precio cuando se muestra el formulario
    formularioPedido.style.display = 'block';
    cargarClientes();
    cargarProductos();
}

productoSelect.addEventListener('change', function () {
    const productoId = productoSelect.value;
    if (productoId) {
        fetch(`http://localhost:8085/api/productos/${productoId}`)
            .then(res => res.json())
            .then(producto => {
                precioInput.value = producto.precio; // Mostrar el precio del producto seleccionado
            });
    }
});



form.addEventListener('submit', function (e) {
    e.preventDefault();
    const id = document.getElementById('pedido-id').value;
    const pedido = {
        cliente: { id: parseInt(clienteSelect.value) },
        detalles: [{
            productoId: parseInt(productoSelect.value),
            cantidad: 1,
            precioUnitario: parseFloat(precioInput.value)
        }],

    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `http://localhost:8085/api/pedidos/${id}` : API_URL;

    fetch('http://localhost:8085/api/pedidos', {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(pedido)
    })
        .then(() => {
            cargarPedidos();
            formularioPedido.style.display = 'none';
        });
});

function editarPedido(id) {
    fetch(`http://localhost:8085/api/pedidos/${id}`)
        .then(res => res.json())
        .then(pedido => {
            document.getElementById('pedido-id').value = pedido.id;
            clienteSelect.value = pedido.cliente.id;
            document.getElementById('direccionEnvio').value = pedido.direccionEnvio;
            formularioPedido.style.display = 'block';
            cargarProductos().then(() => {
                productoSelect.value = pedido.detalles[0]?.productoId || '';
                precioInput.value = pedido.detalles[0]?.precioUnitario || ''; // Establecer el precio cuando se edita
            });
        });
}

function eliminarPedido(id) {
    if (confirm('¿Seguro que deseas eliminar este pedido?')) {
        fetch(`http://localhost:8085/api/pedidos/${id}`, { method: 'DELETE' })
            .then(() => cargarPedidos());
    }
}

cargarPedidos();
