document.addEventListener('DOMContentLoaded', function () {
    // Cargar productos y proveedores al inicio
    cargarProductos();
    cargarProveedores();

    // Manejar la sumisión del formulario
    document.getElementById('relacion-form').addEventListener('submit', function (e) {
        e.preventDefault();

        const productoId = document.getElementById('producto-select').value;
        const proveedorId = document.getElementById('proveedor-select').value;

        // Verificar que los valores no estén vacíos
        if (!productoId || !proveedorId) {
            alert("Por favor, seleccione un producto y un proveedor.");
            return;
        }

        // Crear la relación
        const nuevaRelacion = { productoId, proveedorId };

        // Enviar la relación al backend
        fetch('http://localhost:8085/api/productosproveedores', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevaRelacion)
        })
        .then(response => {
            if (response.ok) {
                alert("Relación creada exitosamente.");
                cargarRelaciones(); // Recargar la lista de relaciones
                ocultarFormulario(); // Ocultar el formulario
            } else {
                alert("Hubo un error al crear la relación.");
            }
        });
    });

    // Mostrar el formulario para agregar nueva relación
    function mostrarFormulario() {
        document.querySelector('.formulario').style.display = 'block';
    }

    // Ocultar el formulario
    function ocultarFormulario() {
        document.querySelector('.formulario').style.display = 'none';
    }

    // Cargar productos desde el backend
    function cargarProductos() {
        fetch('http://localhost:8085/api/productos')
            .then(res => res.json())
            .then(data => {
                const selectProducto = document.getElementById('producto-select');
                selectProducto.innerHTML = ''; // Limpiar opciones anteriores

                data.forEach(producto => {
                    const option = document.createElement('option');
                    option.value = producto.id;
                    option.textContent = producto.nombre; // Asumiendo que el producto tiene un campo "nombre"
                    selectProducto.appendChild(option);
                });
            });
    }

    // Cargar proveedores desde el backend
    function cargarProveedores() {
        fetch('http://localhost:8085/api/proveedores')
            .then(res => res.json())
            .then(data => {
                const selectProveedor = document.getElementById('proveedor-select');
                selectProveedor.innerHTML = ''; // Limpiar opciones anteriores

                data.forEach(proveedor => {
                    const option = document.createElement('option');
                    option.value = proveedor.id;
                    option.textContent = proveedor.nombre; // Asumiendo que el proveedor tiene un campo "nombre"
                    selectProveedor.appendChild(option);
                });
            });
    }

    // Cargar las relaciones existentes y mostrarlas en la tabla
    function cargarRelaciones() {
        fetch('http://localhost:8085/api/productosproveedores')
            .then(res => res.json())
            .then(data => {
                const tbody = document.getElementById('relaciones-tbody');
                tbody.innerHTML = ''; // Limpiar la tabla

                data.forEach(relacion => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${relacion.productoId}</td>
                        <td>${relacion.proveedorId}</td>
                        <td>
                            <button class="btn-icon" onclick="eliminarRelacion(${relacion.productoId}, ${relacion.proveedorId})">
                                <i class="fas fa-trash"></i>
                            </button>
                        </td>
                    `;
                    tbody.appendChild(row);
                });
            });
    }

    // Eliminar relación
    function eliminarRelacion(productoId, proveedorId) {
        if (confirm('¿Estás seguro de que quieres eliminar esta relación?')) {
            fetch(`http://localhost:8085/api/productosproveedores/${productoId}/${proveedorId}`, {
                method: 'DELETE'
            })
            .then(() => {
                alert("Relación eliminada.");
                cargarRelaciones(); // Recargar la lista de relaciones
            });
        }
    }

    // Inicializar la carga de relaciones
    cargarRelaciones();
});



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