const API_URL = 'http://localhost:8085/api';

// Función para obtener el número de productos, clientes y pedidos
function cargarEstadisticas() {
    // Obtener el número de productos
    fetch(`http://localhost:8085/api/productos`)
        .then(res => res.json())
        .then(data => {
            const count = Array.isArray(data) ? data.length : 0;  // Contar los productos si es una lista
            document.getElementById('productos-count').textContent = count;
        })
        .catch(err => console.error('Error al obtener productos:', err));

    // Obtener el número de clientes
    fetch(`http://localhost:8085/api/clientes`)
        .then(res => res.json())
        .then(data => {
            const count = Array.isArray(data) ? data.length : 0;  // Contar los clientes si es una lista
            document.getElementById('clientes-count').textContent = count;
        })
        .catch(err => console.error('Error al obtener clientes:', err));

    // Obtener el número de pedidos
    fetch(`http://localhost:8085/api/pedidos`)
        .then(res => res.json())
        .then(data => {
            const count = Array.isArray(data) ? data.length : 0;  // Contar los pedidos si es una lista
            document.getElementById('pedidos-count').textContent = count;
        })
        .catch(err => console.error('Error al obtener pedidos:', err));
}

// Función para obtener los pedidos recientes
function cargarPedidosRecientes() {
    fetch(`http://localhost:8085/api/pedidos?limit=5`)
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById('pedidos-recientes-tbody');
            tbody.innerHTML = '';  // Limpiar el tbody
            data.forEach(pedido => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${pedido.id}</td>
                    <td>${pedido.cliente ? `${pedido.cliente.nombre} ${pedido.cliente.apellido}` : 'Desconocido'}</td>
                    <td>${new Date(pedido.fecha).toLocaleDateString()}</td>
                    <td>$${pedido.total || 0}</td>
                    <td>${pedido.estado || 'Pendiente'}</td>
                    <td><button>Ver</button></td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(err => console.error('Error al obtener pedidos recientes:', err));
}

// Función para obtener productos más vendidos
function cargarProductosMasVendidos() {
    fetch(`http://localhost:8085/api/productos`)
        .then(res => res.json())
        .then(data => {
            const container = document.querySelector('.top-products .card-body');
            container.innerHTML = '';  // Limpiar el contenedor
            if (Array.isArray(data) && data.length > 0) {
                data.forEach(producto => {
                    const div = document.createElement('div');
                    div.classList.add('product-item');
                    div.innerHTML = `
                        <p>${producto.nombre}</p>
                        <p><strong>$${producto.precio}</strong></p>
                    `;
                    container.appendChild(div);
                });
            } else {
                container.innerHTML = '<p>No hay productos disponibles</p>';
            }
        })
        .catch(err => console.error('Error al obtener productos más vendidos:', err));
}

// Llamar a las funciones al cargar la página
window.onload = function () {
    cargarEstadisticas();
    cargarPedidosRecientes();
    cargarProductosMasVendidos();
};
