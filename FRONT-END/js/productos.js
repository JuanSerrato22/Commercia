const API_URL = 'http://localhost:8085/api/productos';
const categoriaSelect = document.getElementById('categoria');
const tbody = document.getElementById('productos-tbody');
const form = document.getElementById('producto-form');
const formularioProducto = document.getElementById('formulario-producto');

function cargarProductos() {
    fetch('http://localhost:8085/api/productos')
        .then(res => res.json())
        .then(data => {
            tbody.innerHTML = '';
            data.forEach(p => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${p.id}</td>
                    <td>${p.nombre}</td>
                    <td>${p.descripcion}</td>
                    <td>$${p.precio}</td>
                    <td>${p.categoria?.nombre || '-'}</td>
                    <td>
                        <button class="btn-icon" onclick="editarProducto(${p.id})">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button class="btn-icon" onclick="eliminarProducto(${p.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        });
}

function cargarCategorias() {
    return fetch('http://localhost:8085/api/categorias')
        .then(res => res.json())
        .then(data => {
            categoriaSelect.innerHTML = '';
            data.forEach(cat => {
                const option = document.createElement('option');
                option.value = cat.id;
                option.textContent = cat.nombre;
                categoriaSelect.appendChild(option);
            });
        });
}

function mostrarFormulario() {
    document.querySelector('.formulario').style.display = 'block';
    document.getElementById('producto-id').value = '';
    cargarCategorias();
}

function cancelarFormulario() {
    document.querySelector('.formulario').style.display = 'none';
}

form.addEventListener('submit', function (e) {
    e.preventDefault();
    const id = document.getElementById('producto-id').value;
    const producto = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value,
        precio: parseFloat(document.getElementById('precio').value),
        categoria: { id: parseInt(categoriaSelect.value) }
    };

    const method = id ? 'PUT' : 'POST';
    const url = id ? `http://localhost:8085/api/productos/${id}` : API_URL;

    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(producto)
    })
        .then(() => {
            cargarProductos();
            formularioProducto.style.display = 'none';
        });
});

function editarProducto(id) {
    fetch(`http://localhost:8085/api/productos/${id}`)
        .then(res => res.json())
        .then(p => {
            document.getElementById('producto-id').value = p.id;
            document.getElementById('nombre').value = p.nombre;
            document.getElementById('descripcion').value = p.descripcion;
            document.getElementById('precio').value = p.precio;
            formularioProducto.style.display = 'block';
            cargarCategorias().then(() => {
                categoriaSelect.value = p.categoria || '';
            });
        });
}

function eliminarProducto(id) {
    if (confirm('¿Seguro que deseas eliminar este producto?')) {
        fetch(`http://localhost:8085/api/productos/${id}`, { method: 'DELETE' })
            .then(() => cargarProductos());
    }
}

cargarProductos();



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