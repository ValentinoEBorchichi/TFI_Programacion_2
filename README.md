# TFI-Programacion-2
# Sistema de Gestión de Concesionaria “El Pepe”
> Trabajo Final Integrador – Programación II (Java)

Este proyecto implementa un sistema por consola para gestionar una concesionaria de autos.  
Permite administrar clientes, vehículos, ventas y mantenimientos, aplicando los conceptos principales de Programación 2:

- Programación Orientada a Objetos (POO)
- Herencia y polimorfismo
- Interfaces e interfaces funcionales (lambda)
- Colecciones (ArrayList)
- Lista enlazada propia para historial de ventas
- Manejo de archivos de texto para persistencia

---

## 1. Objetivo del proyecto

El objetivo es simular el funcionamiento básico de una concesionaria:

- Registrar y administrar clientes.
- Cargar y gestionar autos disponibles para la venta.
- Registrar ventas y consultar el historial.
- Registrar mantenimientos de vehículos.
- Guardar todo en archivos de texto para no perder la información.

---

## 2. Tecnologías y herramientas utilizadas

- Lenguaje: Java 
- Ejecución: Consola o IDE (VS Code)
- Persistencia: Archivos .txt con formato tipo CSV
- Colecciones: ArrayList y lista enlazada simple propia
- Paquete principal: concesionaria

---

## 3. Estructura general del proyecto

Todas las clases están en el paquete:

package concesionaria;

### 3.1. Clases de dominio (modelo)

- Persona  
  Representa datos generales de una persona: DNI, nombre, apellido, dirección, teléfono, email.  
  Se usa como base para otras clases.

- Cliente (extiende Persona)  
  Representa a los clientes de la concesionaria.  
  Maneja una lista estática de clientes y permite:
  - Alta, baja, modificación y listado
  - Búsqueda por DNI
  - Guardar y cargar desde el archivo clientes.txt

- Vehiculo   
  Modelo general de un vehículo: patente, marca, modelo, año, estado, precio.  
  Sirve como clase base para tipos concretos.

- Auto (extiende Vehiculo)  
  Vehículo tipo auto, con cantidad de puertas y tipo (por ejemplo, sedán, hatchback, etc.).  
  Sobrescribe toString para mostrar todos los datos del auto.

- Venta y DetalleVenta  
  Venta: guarda el cliente, los detalles y el total de la operación.  
  DetalleVenta: relaciona un vehículo con una cantidad y su subtotal.

- Mantenimiento  
  Registra mantenimientos realizados sobre vehículos (descripción, fecha, costo, vehículo).  
  Se guarda y carga desde mantenimientos.txt y actualiza el estado del vehículo a “En mantenimiento” o “Disponible”.

- HistorialVentas  
  Implementa una lista enlazada simple de ventas.  
  Permite registrar, eliminar y mostrar el historial, y guardar o cargar las ventas desde ventas.txt.

---

### 3.2. Clases de infraestructura y menús

- Concesionaria  
  Centraliza las colecciones principales:
  - Lista de autos
  - Lista de mantenimientos
  - Historial de ventas  
  También se encarga de guardar y cargar los autos desde autos.txt.

- MenuAutos  
  Menú para gestionar autos:
  - Agregar, listar, buscar y eliminar autos
  - Ver autos en mantenimiento
  - Filtrar autos por marca utilizando una interfaz funcional y una expresión lambda

- MenuMantenimiento  
  Menú para:
  - Agregar, listar y eliminar mantenimientos
  - Cambiar el estado del vehículo según corresponda (en mantenimiento / disponible)

- Main  
  Punto de entrada del sistema.  
  Muestra el menú principal con las opciones:


  --- Concesionaria El Pepe ---
  1. Opciones de cliente
  2. Opciones de autos
  3. Opciones de mantenimiento
  4. Registrar nueva venta
  5. Ver historial de ventas
  6. Salir

  Al iniciar, intenta cargar datos desde:
  clientes.txt, autos.txt, mantenimientos.txt, ventas.txt.

---

## 4. Flujo simple de uso

1. Se ejecuta el programa y aparece el menú principal.  
2. Desde el menú se puede:
   - Gestionar clientes (altas, bajas, modificaciones y búsqueda).
   - Gestionar autos (cargar, listar, buscar, filtrar y eliminar).
   - Registrar ventas (seleccionando cliente y auto).
   - Registrar y administrar mantenimientos.
   - Ver el historial de ventas registrado en la lista enlazada.
3. Cada operación importante actualiza sus archivos de texto correspondientes.

---

## 5. Contenidos de Programación II aplicados

- POO: herencia (Persona → Cliente, Vehiculo → Auto), polimorfismo y encapsulamiento.  
- Interfaces: uso de interfaces como Comparable, Iterable y una interfaz funcional para filtros de autos.  
- Colecciones: uso de ArrayList para manejar listas dinámicas.  
- Estructuras propias: implementación de una lista enlazada simple para el historial de ventas.  
- Archivos: lectura y escritura de datos en .txt para persistir la información del sistema.
