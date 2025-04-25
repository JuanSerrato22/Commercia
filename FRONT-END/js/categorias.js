document.addEventListener('DOMContentLoaded', function () {
    cargarCategorias();

    document.getElementById('categoriaForm').addEventListener('submit', function (e) {
        e.preventDefault();
        const id = document.getElementById('categoriaId').value;
        const nombre = document.getElementById('nombre').value;

        const categoria = { nombre };

        if (id) {
            // Actualizar
            fetch(`/api/categorias/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(categoria)
            })
            .then(response => {
                if (response.ok) {
                    cargarCategorias();
                    cancelarFormulario();
                    alert("Categoría actualizada correctamente.");
                } else {
                    alert("Hubo un error al actualizar la categoría.");
                }
            })
            .catch(error => {
                console.error("Error al actualizar la categoría:", error);
                alert("Error de red, no se pudo actualizar.");
            });
        } else {
            // Crear
            fetch('/api/categorias', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(categoria)
            })
            .then(response => {
                if (response.status === 409) {
                    alert("Ya existe una categoría con ese nombre.");
                } else if (response.ok) {
                    cargarCategorias();
                    cancelarFormulario();
                    alert("Categoría creada correctamente.");
                } else {
                    alert("Hubo un error al crear la categoría.");
                }
            })
            .catch(error => {
                console.error("Error al crear la categoría:", error);
                alert("Error de red, no se pudo crear.");
            });
        }
    });
});

function cargarCategorias() {
    fetch('/api/categorias')
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById('tabla-categorias');
            tbody.innerHTML = '';
            data.forEach(cat => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${cat.id}</td>
                    <td>${cat.nombre}</td>
                    <td>
                        <button class="btn-icon" onclick="editarCategoria(${cat.id}, '${cat.nombre}')">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button class="btn-icon" onclick="eliminarCategoria(${cat.id})">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error al cargar las categorías:", error);
            alert("Error al cargar las categorías.");
        });
}

function mostrarFormulario() {
    document.getElementById('formularioCategoria').style.display = 'block';
    document.getElementById('titulo-formulario').textContent = 'Nueva Categoría';
    document.getElementById('categoriaId').value = '';
    document.getElementById('nombre').value = '';
}

function cancelarFormulario() {
    document.getElementById('formularioCategoria').style.display = 'none';
}

function editarCategoria(id, nombre) {
    mostrarFormulario();
    document.getElementById('categoriaId').value = id;
    document.getElementById('nombre').value = nombre;
    document.getElementById('titulo-formulario').textContent = 'Editar Categoría';
}

function eliminarCategoria(id) {
    if (confirm('¿Estás seguro de que quieres eliminar esta categoría?')) {
        fetch(`/api/categorias/${id}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    cargarCategorias();
                    alert("Categoría eliminada correctamente.");
                } else {
                    alert("Hubo un error al eliminar la categoría.");
                }
            })
            .catch(error => {
                console.error("Error al eliminar la categoría:", error);
                alert("Error de red, no se pudo eliminar.");
            });
    }
}
