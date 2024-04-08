<h1>Proyecto Universitario con JDBC y MySQL</h1>

<h2>Descripción</h2>
<p>Este proyecto universitario se basa en la reserva de vuelos y cuartos de hotel dependiendo del lugar, utilizando JDBC (Java Database Connectivity) junto con MySQL. Se implementa el patrón de diseño Modelo-Vista-Controlador (MVC) para una estructura organizada y modular. Además, se incluye una interfaz gráfica en Java para facilitar la interacción del usuario, permitiendo realizar reservas y cancelaciones, mostrando el precio del viaje y el hospedaje. Todo el manejo de la base de datos se realiza utilizando la metodología DAO (Data Access Object).</p>

<h2>Características Principales</h2>
<ul>
  <li>Conexión a una base de datos MySQL utilizando JDBC.</li>
  <li>Implementación del patrón MVC para separar la lógica de negocio, la interfaz y el acceso a datos.</li>
  <li>Interfaz gráfica en Java para interactuar con el usuario y gestionar reservas y cancelaciones de vuelos y cuartos de hotel.</li>
  <li>Uso de la metodología DAO para acceder y manipular los datos en la base de datos.</li>
</ul>

<h2>Tecnologías Utilizadas</h2>
<ul>
  <li>Java</li>
  <li>JDBC (Java Database Connectivity)</li>
  <li>MySQL</li>
</ul>

<h2>Estructura del Proyecto</h2>
<p>La estructura del proyecto sigue la organización MVC:</p>
<pre>
src/
├── main/
│   ├── java/
│   │   ├── controller/          # Controladores (lógica de negocio)
│   │   ├── model/               # Modelos de datos
│   │   ├── view/                # Vistas (interfaz gráfica)
│   │   ├── dao/                 # DAO (Data Access Object)
│   │   └── Main.java            # Clase principal de la aplicación
</pre>

<h2>Uso</h2>
<p>Para utilizar la aplicación con la interfaz gráfica:</p>
<ol>
  <li>Ejecuta el archivo <code>Main.java</code> para iniciar la aplicación.</li>
  <li>Utiliza la interfaz gráfica para realizar reservas y cancelaciones de vuelos y cuartos de hotel, y ver el precio del viaje y el hospedaje.</li>
</ol>

<h2>Contribuciones</h2>
<p>¡Las contribuciones son bienvenidas! Si tienes sugerencias para mejorar la aplicación o implementar nuevas funcionalidades, no dudes en enviar un pull request.</p>

</body>
