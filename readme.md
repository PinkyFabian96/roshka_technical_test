Task Management API
Esta es una API REST construida con Spring Boot para la gestión de tareas y subtareas. El sistema incluye autenticación basada en JWT de usuarios en memoria y soporte para paginación de resultados.

🚀 Requisitos Previos
Antes de comenzar, asegúrate de tener instalado:

Java 17 o superior.

Maven 3.8+.

PostgreSQL instalado y configurado en tu maquina local.

🛠️ Instalación y Configuración
1. Clonar el repositorio
2. Configurar variables de entorno:
   El proyecto incluye un archivo .env.example. Debes renombrarlo y completar tus credenciales.
3. Crear la base de datos con el nombre que definiste en el enviroment


📖 Documentación de la API (Ejemplos JSON)

### Endpoints Principales

##### Tareas (Tasks)
* POST /tasks → Crear una nueva tarea.
* GET /tasks → Listar todas las tareas (Soporta paginación y filtros).
* GET /tasks/:id → Obtener el detalle de una tarea por ID.
* PUT /tasks/:id → Actualizar una tarea existente.
* DELETE /tasks/:id → Eliminar una tarea.

##### Subtareas (Subtasks)
* POST /tasks/:id/subtasks → Crear una subtarea asociada a una tarea específica.
* GET /tasks/:id/subtasks → Listar todas las subtareas de una tarea.
* GET /tasks/:id/subtasks/:subtaskId → Obtener una subtarea por su ID.
* PUT /tasks/:id/subtasks/:subtaskId → Actualizar una subtarea.
* DELETE /tasks/:id/subtasks/:subtaskId → Eliminar una subtarea.

1. #### Autenticación Endpoint: POST /login
* POST /login → Para iniciar sesion
* GET /profile → Para obtener datos el usuario logeado

### Documentacion de Swagger

[Swagger Link](http://localhost:8080/swagger-ui/index.html)

Request (LoginRequestDTO):
`{
    "username": "admin",
    "password": "admin123"
}`

Response (LoginResponseDTO):
`{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "username": "admin",
    "role": "ADMIN"
}`


2. ##### Tareas (Tasks) Endpoint: POST /tasks

Request (TaskRequestDTO):
`{
    "title": "Finalizar reporte mensual",
    "description": "Redactar el informe de ventas del mes de marzo",
    "category": "Trabajo",
    "status": "PENDING"
}`

Response (TaskResponseDTO):
`{
    "id": 1,
    "title": "Finalizar reporte mensual",
    "description": "Redactar el informe de ventas del mes de marzo",
    "category": "Trabajo",
    "status": "PENDING",
    "createdAt": "2026-03-27T19:00:00",
    "updatedAt": "2026-03-27T19:00:00"
}`

3. ##### Subtareas (Subtasks) Endpoint: POST /tasks/{taskId}/subtasks

Request (SubtaskRequestDTO):
`{
    "title": "Recopilar datos de Excel",
    "description": "Extraer las tablas de la hoja de cálculo",
    "status": "IN_PROGRESS"
}`

4. ##### Listados Paginados Endpoint: GET /tasks?page=0

Response (PagedResponseDTO):
`{
    "data": [
    { "id": 1, "title": "Tarea 1", "...": "..." }
    ],
    "meta": {
        "total": 50,
        "page": 0,
        "totalPages": 5
    }
}`
