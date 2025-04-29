document.addEventListener('DOMContentLoaded', function () {
    const API_BASE = 'http://localhost:8085/api/categorias';

    cargarCategorias();

    document.getElementById('categoria-form').addEventListener('submit', function (e) {
        e.preventDefault();

        const id = document.getElementById('categoria-id').value;
        const nombre = document.getElementById('nombre').value;
        const descripcion = document.getElementById('descripcion').value;

        const categoria = { nombre, descripcion };

        if (id) {
            // Actualizar categoría
            fetch(`http://localhost:8085/api/categorias/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(categoria)
            })
            .then(() => {
                cargarCategorias();
                cancelarFormulario();
            });
        } else {
            // Crear categoría
            fetch('http://localhost:8085/api/categorias', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(categoria)
            })
            .then(() => {
                cargarCategorias();
                cancelarFormulario();
            });
        }
    });
});

function cargarCategorias() {
    fetch('http://localhost:8085/api/categorias')
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById('categorias-tbody');
            tbody.innerHTML = '';
            data.forEach(categoria => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${categoria.id}</td>
                    <td>${categoria.nombre}</td>
                    <td>${categoria.descripcion}</td>
                    <td>
                        <button class="btn-icon" onclick="editarCategoria(${categoria.id}, '${categoria.nombre}', '${categoria.descripcion}')">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button class="btn-icon" onclick="eliminarCategoria(${categoria.id})">
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
    document.getElementById('categoria-id').value = '';
    document.getElementById('nombre').value = '';
    document.getElementById('descripcion').value = '';
}

function cancelarFormulario() {
    document.querySelector('.formulario').style.display = 'none';
}

function editarCategoria(id, nombre, descripcion) {
    mostrarFormulario();
    document.getElementById('categoria-id').value = id;
    document.getElementById('nombre').value = nombre;
    document.getElementById('descripcion').value = descripcion;
}

function eliminarCategoria(id) {
    if (confirm('¿Estás seguro de que quieres eliminar esta categoría?')) {
        fetch(`http://localhost:8085/api/categorias/${id}`, {
            method: 'DELETE'
        }).then(() => cargarCategorias());
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
