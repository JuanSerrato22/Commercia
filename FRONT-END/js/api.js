const apiUrl = 'http://localhost:8085/api'; // Cambia esta URL por la URL de tu API

// Función para obtener los productos desde la API
function obtenerProductos() {
    fetch(`${apiUrl}/productos`) // Endpoint de la API para obtener productos
        .then(response => response.json())
        .then(productos => {
            // Llamar a la función para mostrar los productos en la página
            mostrarProductos(productos);
        })
        .catch(error => console.error('Error al obtener los productos:', error));
}

// Función para mostrar los productos en la página
function mostrarProductos(productos) {
    const listaProductos = document.querySelector('.product-list');  // El contenedor donde se van a mostrar los productos
    listaProductos.innerHTML = ''; // Limpiar la lista antes de agregar los productos

    productos.forEach(producto => {
        const div = document.createElement('div');
        div.classList.add('product-item');
        div.innerHTML = `
            <img src="${producto.imagen || 'https://via.placeholder.com/150'}" alt="${producto.nombre}">
            <div class="product-info">
                <h4>${producto.nombre}</h4>
                <p>${producto.descripcion}</p>
            </div>
            <div class="product-price">$${producto.precio}</div>
        `;
        listaProductos.appendChild(div);
    });
}

// Función para obtener los detalles de un producto específico por ID (opcional)
function obtenerProductoPorId(productId) {
    fetch(`${apiUrl}/productos/${productId}`)
        .then(response => response.json())
        .then(producto => {
            // Aquí puedes agregar el código para mostrar los detalles de un solo producto
            console.log(producto);
        })
        .catch(error => console.error('Error al obtener el producto:', error));
}

// Función para obtener las categorías (opcional, solo si la necesitas)
function obtenerCategorias() {
    fetch(`${apiUrl}/categorias`) // Endpoint de la API para obtener categorías
        .then(response => response.json())
        .then(categorias => {
            mostrarCategorias(categorias);
        })
        .catch(error => console.error('Error al obtener las categorías:', error));
}

// Función para mostrar las categorías en la página (opcional)
function mostrarCategorias(categorias) {
    const listaCategorias = document.querySelector('.category-list');
    listaCategorias.innerHTML = ''; // Limpiar la lista antes de agregar las categorías

    categorias.forEach(categoria => {
        const div = document.createElement('div');
        div.classList.add('category-item');
        div.innerHTML = `
            <h4>${categoria.nombre}</h4>
            <p>${categoria.descripcion}</p>
        `;
        listaCategorias.appendChild(div);
    });
}

// Función para agregar un producto al carrito (opcional, si es necesario)
function agregarAlCarrito(productoId, cantidad) {
    fetch(`${apiUrl}/carrito`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            productoId: productoId,
            cantidad: cantidad
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log('Producto agregado al carrito:', data);
        })
        .catch(error => console.error('Error al agregar al carrito:', error));
}

// Llamar la función para obtener los productos cuando la página esté lista
document.addEventListener("DOMContentLoaded", function () {
    // Verifica si estamos en la página de productos
    if (window.location.pathname.includes("productos.html")) {
        obtenerProductos();  // Llamar la función para cargar los productos
    }

    // Opcional: si también quieres cargar las categorías, lo harías de esta manera:
    if (window.location.pathname.includes("categorias.html")) {
        obtenerCategorias(); // Cargar las categorías si estamos en la página de categorías
    }
});
