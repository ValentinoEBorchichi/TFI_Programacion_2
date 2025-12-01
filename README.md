# TFI-Programacion-2
# Sistema de Gestión de Concesionaria “El Pepe”
> Trabajo Final Integrador – Programación II (Java)

Este proyecto implementa un **sistema de escritorio por consola** para la gestión integral de una concesionaria de autos.  
Permite administrar **clientes, vehículos, ventas y mantenimientos**, utilizando los principales conceptos vistos en Programación 2:

- Programación Orientada a Objetos (POO)
- Herencia y polimorfismo
- Interfaces e interfaces funcionales (con expresión *lambda*)
- Colecciones (`ArrayList`)
- Estructuras enlazadas propias (lista enlazada simple)
- Manejo de archivos de texto (persistencia)
- Manejo básico de excepciones y validaciones de entrada

---

## 1. Objetivo del proyecto

El objetivo principal es simular el funcionamiento de una concesionaria real, permitiendo:

- Registrar y administrar **clientes**.
- Cargar y gestionar **autos disponibles para la venta**.
- Registrar **ventas** y mantener un **historial persistente**.
- Registrar **mantenimientos** asociados a los vehículos.
- Persistir la información en **archivos de texto** para no perder los datos entre ejecuciones.

Al mismo tiempo, sirve como demostración práctica de los contenidos de Programación II, integrando POO, colecciones, archivos y diseño modular.

---

## 2. Tecnologías y herramientas utilizadas

- **Lenguaje:** Java (versión 8+)
- **Entorno de ejecución:** Consola (línea de comandos) o IDE (IntelliJ, NetBeans, Eclipse, VS Code con extensión Java).
- **Persistencia:** Archivos de texto (`.txt`) con formato CSV simple.
- **Colecciones:**  
  - `ArrayList` para listas de autos, clientes y detalles de ventas.  
  - Lista enlazada simple propia para el historial de ventas.
- **Paquete principal:** `concesionaria`

---

## 3. Estructura del proyecto

Todas las clases se encuentran en el paquete:

package concesionaria;

### 3.1. Clases de dominio (modelo)

- **`Persona`**  
  Clase base que representa a una persona con los datos:
  - `DNI`, `Nombre`, `Apellido`, `Direccion`, `Telefono`, `Email`.
  Provee el constructor y getters para reutilizar estos datos en otras clases.

- **`Cliente` extends `Persona` implements `Comparable<Cliente>`**  
  Especializa a `Persona` para representar a un cliente de la concesionaria.  
  - Mantiene una **lista estática** de clientes (`ArrayList<Cliente>`).
  - Implementa `equals` y `hashCode` comparando por **DNI**.
  - Implementa `Comparable` para **ordenar clientes por apellido**.
  - Métodos principales (estáticos):
    - `opcionesCliente(Scanner)` → menú específico de clientes.
    - Altas, bajas, modificaciones y listado de clientes.
    - Búsqueda por DNI.
    - Persistencia:
      - `guardarClientesEnArchivo("clientes.txt")`
      - `cargarClientesDesdeArchivo("clientes.txt")`

- **`Vehiculo` (abstracta)**  
  Clase abstracta que modela un vehículo genérico:
  - Atributos: `patente`, `marca`, `modelo`, `anio`, `estado`, `precio`.
  - Encapsulamiento mediante getters/setters.
  - `equals` y `hashCode` redefinidos para comparar vehículos por **patente**.
  - Sirve como base para especializar distintos tipos de vehículos.

- **`Auto` extends `Vehiculo`**  
  Representa un automóvil:
  - Atributos adicionales: `cantidadPuertas`, `tipo` (por ejemplo, sedán, hatchback, etc.).
  - Constructor que recibe todos los datos del vehículo más los campos propios.
  - `toString()` sobreescrito para mostrar toda la información del auto.

- **`Venta`**  
  Representa una venta realizada:
  - Atributos:
    - `Cliente cliente`
    - `ArrayList<DetalleVenta> detalles`
    - `double totalFinal`
  - En el constructor se llama a un método interno que **calcula el total** sumando los subtotales de cada detalle.
  - Métodos:
    - `getCliente()`
    - `getDetalles()`
    - `getTotalFinal()`
    - `toString()` → muestra un resumen amigable de la venta.

- **`DetalleVenta`**  
  Representa la relación entre un **vehículo** y una **cantidad** dentro de una venta:
  - Atributos: `Vehiculo vehiculo`, `int cantidad`, `double subtotal`.
  - El subtotal se calcula como `precio * cantidad`.
  - Métodos:
    - `getVehiculo()`, `getCantidad()`, `getSubtotal()`
    - `getSubtotalNeto()` (alias para el subtotal).
    - `toString()` descriptivo con marca, modelo, cantidad y subtotal.

- **`Mantenimiento`**  
  Modela un mantenimiento realizado sobre un vehículo:
  - Atributos: `idMantenimiento`, `descripcion`, `fecha`, `costo`, `Vehiculo vehiculo`.
  - Método `calcularCostoTotal()` (por ahora devuelve el costo base).
  - `toString()` detallado.
  - Persistencia:
    - `guardarMantenimientosEnArchivo("mantenimientos.txt")`
    - `cargarMantenimientosDesdeArchivo("mantenimientos.txt")`  
      Al cargar, si se encuentra el vehículo asociado, se marca su estado como **"En mantenimiento"** y se agrega a `Concesionaria.listaMantenimientos`.

- **`HistorialVentas` implements `Iterable<Venta>`**  
  Implementa un **historial de ventas con una lista enlazada simple propia**:
  - Clase interna `Node` con `Venta venta` y `Node next`.
  - Atributos:
    - `Node head`, `Node tail`, `int size`.
  - Métodos principales:
    - `registrarVenta(Venta v)` → agrega una venta al final de la lista.
    - `eliminarVenta(Venta v)` → recorre la lista y elimina la venta indicada.
    - `mostrarHistorial()` → recorre la lista e imprime las ventas numeradas.
    - `size()`, `estaVacio()`.
    - Implementación de `iterator()` para poder usar **for-each** sobre `HistorialVentas`.
  - Persistencia de ventas:
    - `guardarVentasEnArchivo("ventas.txt")`  
      Escribe por cada venta:

      DNI_CLIENTE,PATENTE_AUTO,TOTAL_FINAL

    - `cargarVentasDesdeArchivo("ventas.txt")`  
      - Lee cada línea, separa por coma.
      - Busca el cliente por DNI en `Cliente.getListaClientes()`.
      - Busca el vehículo por patente en `Concesionaria.listaAutos`.
      - Reconstruye una `Venta` con un `DetalleVenta` y la agrega al historial.

---

### 3.2. Clases de infraestructura y menús

- **`Concesionaria`**  
  Clase que centraliza las colecciones principales:
  - `public static ArrayList<Auto> listaAutos`
  - `public static ArrayList<Mantenimiento> listaMantenimientos`
  - `public static HistorialVentas historialVentas`
  - Métodos de persistencia de autos:
    - `guardarAutosEnArchivo("autos.txt")`  
      Guarda cada auto en formato:

      patente,marca,modelo,anio,estado,precio,cantidadPuertas,tipo

    - `cargarAutosDesdeArchivo("autos.txt")`  
      Reconstruye objetos `Auto` a partir del archivo.

- **`MenuAutos`**  
  Maneja el **submenú de gestión de autos**:
  - Define una **interfaz funcional**:

    @FunctionalInterface
    public interface FiltroAuto {
        boolean filtrar(Auto a);
    }

  - `mostrarMenu(Scanner sc)` muestra opciones:
    1. Agregar auto  
    2. Listar autos  
    3. Listado de autos en mantenimiento  
    4. Buscar auto por patente  
    5. Eliminar auto  
    6. Filtrar autos por marca (usando una **expresión lambda** sobre `FiltroAuto`)  
    7. Volver al menú principal
  - Demuestra el uso de:
    - Colecciones (`ArrayList`)
    - Expresiones **lambda** para aplicar filtros sobre la lista de autos.
    - Persistencia tras cada alta/baja.

- **`MenuVentas`**  
  Maneja la lógica para **registrar ventas**:
  - Verifica que existan autos disponibles.
  - Pide un cliente (por DNI) y un auto (por patente).
  - Crea un `DetalleVenta` y una `Venta`.
  - Registra la venta en `Concesionaria.historialVentas`.
  - Actualiza archivos:
    - Guarda la venta en `ventas.txt`.
    - Elimina el auto vendido de `listaAutos` y actualiza `autos.txt`.
  - Muestra el **total final** de la venta.

- **`MenuMantenimiento`**  
  Administra los mantenimientos:
  - Opciones:
    1. Agregar mantenimiento  
    2. Listar mantenimientos  
    3. Eliminar mantenimiento  
    4. Volver
  - Al agregar:
    - Pide ID, descripción, fecha, costo y patente del vehículo.
    - Marca el vehículo en estado **"En mantenimiento"**.
    - Registra el mantenimiento en `Concesionaria.listaMantenimientos`.
    - Guarda cambios en `mantenimientos.txt` y `autos.txt`.
  - Al eliminar un mantenimiento:
    - Restaura el estado del vehículo a **"Disponible"**.
    - Actualiza la lista y los archivos.

- **`Main`**  
  Punto de entrada del programa:

  public class Main {
      public static void main(String[] args) {
          Menus.mostrarMenuPrincipal();
          System.out.println("Programa finalizado.");
      }
  }

  Contiene la clase interna `Menus` con el **menú principal**:

  --- Concesionaria El Pepe ---
  1. Opciones de cliente
  2. Opciones de autos
  3. Opciones de ventas
  4. Opciones de mantenimiento
  5. Ver historial de ventas
  6. Salir

  Al iniciar, se cargan los datos desde:
  - `clientes.txt`
  - `autos.txt`
  - `mantenimientos.txt`
  - `ventas.txt`

---

## 4. Flujo de uso del sistema

1. **Inicio del programa**
   - Se cargan automáticamente los clientes, autos, mantenimientos y ventas desde sus archivos correspondientes (si existen).
   - Se muestra el menú principal.

2. **Gestión de clientes**
   - Permite:
     - Listar clientes registrados.
     - Buscar cliente por DNI.
     - Agregar un nuevo cliente.
     - Modificar datos de un cliente existente.
     - Eliminar un cliente.
   - Cada cambio se persiste en `clientes.txt`.

3. **Gestión de autos**
   - Permite:
     - Cargar nuevos autos como “Disponibles”.
     - Listarlos en pantalla.
     - Consultar autos actualmente en mantenimiento.
     - Buscar por patente.
     - Eliminar autos.
     - Filtrar autos por marca usando una expresión lambda sobre la interfaz funcional `FiltroAuto`.
   - Al finalizar, se guardan los cambios en `autos.txt`.

4. **Registro de ventas**
   - Se selecciona un cliente y un auto disponible.
   - Se genera una venta con su detalle y total.
   - Se actualiza:
     - `historialVentas` (lista enlazada).
     - Archivo `ventas.txt`.
     - Se elimina el auto vendido de `listaAutos` y se actualiza `autos.txt`.

5. **Gestión de mantenimientos**
   - Se registran mantenimientos asociados a vehículos.
   - El estado del vehículo cambia a **"En mantenimiento"**.
   - Al eliminar un mantenimiento, el vehículo vuelve a estado **"Disponible"**.
   - Persistencia en `mantenimientos.txt` y actualización de `autos.txt`.

6. **Historial de ventas**
   - Opción 5 del menú principal.
   - Recorre la lista enlazada (`HistorialVentas`) e imprime todas las ventas registradas.

---

## 5. Persistencia de datos

El sistema no utiliza base de datos, sino **archivos de texto** en formato CSV simple.  
Esto permite:

- **Mantener la información entre ejecuciones**.
- Visualizar y editar (con cuidado) los archivos desde cualquier editor de texto.

Archivos utilizados:

- `clientes.txt`
- `autos.txt`
- `mantenimientos.txt`
- `ventas.txt`

Cada clase relacionada conoce cómo **guardar** y **cargar** sus datos (responsabilidad repartida):

- `Cliente` se encarga de sus propios archivos de clientes.
- `Concesionaria` maneja el archivo de autos.
- `Mantenimiento` maneja sus archivos, pero usando `Concesionaria.listaMantenimientos`.
- `HistorialVentas` guarda y carga las ventas.

---

## 6. Cómo compilar y ejecutar

1. Asegurarse de tener **JDK 8 o superior** instalado.
2. Ubicarse en el directorio raíz del proyecto, donde está la carpeta `concesionaria/`.
3. Compilar todas las clases:

   javac concesionaria/*.java

4. Ejecutar el programa:

   java concesionaria.Main

5. Verificar que los archivos:
   - `clientes.txt`
   - `autos.txt`
   - `mantenimientos.txt`
   - `ventas.txt`  
   estén en el **mismo directorio de trabajo**, o dejar que el programa los genere al guardar por primera vez.

---

## 7. Contenidos de Programación II aplicados

Este TFI integra de forma práctica varios contenidos de la materia:

- **POO**
  - Herencia: `Persona` → `Cliente`, `Vehiculo` → `Auto`.
  - Polimorfismo: tratamiento general de vehículos.
  - Encapsulamiento mediante atributos privados y getters/setters.
- **Interfaces**
  - `Comparable<Cliente>` para poder ordenar clientes por apellido.
  - `Iterable<Venta>` para poder recorrer el historial de ventas con for-each.
  - Interfaz funcional `FiltroAuto` para filtros con expresiones lambda.
- **Colecciones**
  - `ArrayList` para listas dinámicas de autos, clientes y detalles de venta.
- **Estructuras propias**
  - Implementación manual de una **lista enlazada simple** para el historial de ventas (`HistorialVentas` + clase interna `Node`).
- **Archivos**
  - Lectura y escritura con `BufferedReader` y `BufferedWriter`.
  - Manejo básico de excepciones de entrada/salida (`try/catch`).
- **Manejo de errores de entrada**
  - Validaciones al leer opciones de menú y datos desde `Scanner`.

---

## 8. Posibles mejoras futuras

Algunas mejoras que podrían incorporarse a futuro:

- Manejo de más tipos de vehículos (por ejemplo, `Camioneta` extendiendo `Vehiculo`).
- Manejo de múltiples detalles por venta (varios autos en una misma factura).
- Validaciones más robustas de entrada (controlar excepciones `NumberFormatException` de forma más detallada).
- Reportes estadísticos:
  - Total vendido por cliente.
  - Autos más vendidos.
  - Costos totales de mantenimiento.
- Interfaz gráfica (JavaFX o Swing) o migración a una API REST.

---

Este README describe el proyecto desde el punto de vista técnico y también académico, mostrando claramente **qué problema resuelve**, **cómo está diseñado** y **qué contenidos de Programación II se aplican**.  


