# Servidor Web en Java coon un MiniFrameWork

Este proyecto es un servidor web simple implementado en Java sin utilizar frameworks externos. Permite servir archivos estáticos (HTML, CSS, JS, imágenes) y proporciona una API REST para interacciones básicas, configurada mediante expresiones lambda.

## Características
- **Registrar endpoints REST**: usando métodos como `get()` para definir servicios.
- **Extraer parámetros de consulta**: a través de la clase `HttpRequest`.
- **Servir archivos estáticos**: configurando el directorio de archivos con `staticfiles()`.

En este ejemplo, se utiliza el prefijo **/App** para los endpoints REST. Por ejemplo:
- `http://localhost:8080/App/hello?name=Pedro` responderá con un saludo.
- `http://localhost:8080/App/pi` responderá con el valor de PI.
  
Las solicitudes que no comiencen con **/App** se tratarán como peticiones de archivos estáticos.

## Comenzando

Sigue estas instrucciones para configurar y ejecutar el proyecto en tu máquina local para desarrollo y pruebas.

### Prerrequisitos

Asegúrate de tener instalado lo siguiente:

- Java 8 o superior
- Apache Maven
- Git para clonar el repositorio
- Una terminal o línea de comandos

### Instalación

1. Clona este repositorio y navega al directorio del proyecto:

   ```bash
   git clone https://github.com/Koket987/AREPTALLER1.git
   cd AREPTALLER1
   ```

2. Compila y construye el proyecto con Maven:

   ```bash
   mvn clean install
   ```

3. Ejecuta el servidor:

   ```bash
   mvn exec:java -Dexec.mainClass="co.edu.eci.arep.HttpServer"
   ```

## Pruebas

### Pruebas Unitarias

Este proyecto incluye pruebas unitarias con JUnit 5. Para ejecutarlas, usa:

```bash
mvn test
```

Las pruebas verifican, entre otros casos, que:
- **GET** `http://localhost:8080/App/hello?name=Santiago` devuelve **Hello Santiago**.
- **GET** `http://localhost:8080/App/pi` devuelve **3.141592653589793**.
- Los archivos estáticos se sirven correctamente.

### Pruebas de extremo a extremo

Puedes probar el servidor abriendo un navegador y accediendo a:

```bash
http://localhost:8080
```

#### API REST

Utiliza `curl` desde tu terminal favorita para probar los endpoints de la API:

- **GET** (Saludo):  
  ```bash
  curl -X GET "http://localhost:8080/App/hello?name=Santiago"
  ```
  Respuesta esperada:
  ```
  Hello Santiago
  ```

- **GET** (Valor de PI):  
  ```bash
  curl -X GET "http://localhost:8080/App/pi"
  ```
  Respuesta esperada:
  ```
  3.141592653589793
  ```

#### Archivos Estáticos

Accede a los archivos incluidos desde cualquier navegador, por ejemplo:

- `http://localhost:8080/App/index.html`
- `http://localhost:8080/styles.css`
- `http://localhost:8080/script.js`
- `http://localhost:8080/images/example1.png`

## Diseño del Sistema

El servidor sigue un diseño modular que permite la extensión de funcionalidades:

- **Manejo de solicitudes HTTP**: Soporta métodos `GET` y `POST`, diferenciando entre peticiones REST (prefijo `/App`) y solicitudes de archivos estáticos.
- **Manejo de archivos estáticos**: Permite servir HTML, CSS, JavaScript e imágenes desde un directorio configurado.
- **Endpoints REST**: Permite registrar servicios REST de forma sencilla mediante expresiones lambda.

El código se encuentra estructurado en las siguientes clases:
- `HttpRequest.java`: Procesa y parsea la petición HTTP.
- `HttpResponse.java`: Permite gestionar la respuesta HTTP.
- `HttpServer.java`: Implementa el framework, con métodos para registrar endpoints (`get()`, `post()`), configurar archivos estáticos (`staticfiles()`) y arrancar el servidor (`start()`).
- `ExampleApp.java`: Ejemplo de aplicación que utiliza el framework.

## Despliegue

Para uso en producción, considera ejecutar el servidor como un proceso en segundo plano o configurar un servicio systemd:

```bash
nohup mvn exec:java -Dexec.mainClass="co.edu.eci.arep.ExampleApp" &
```

## Construido Con

- **Java** - Lenguaje principal utilizado.
- **Maven** - Para la gestión de dependencias y automatización.
- **Biblioteca estándar de Java** - Para manejo de red y archivos.
- **JUnit** - Para pruebas automatizadas.

## Contribuciones

Siéntete libre de hacer fork y enviar pull requests para mejorar el proyecto. Las contribuciones son bienvenidas.

## Versionado

Este proyecto sigue [SemVer](http://semver.org/) para la gestión de versiones. Consulta los [tags en este repositorio](https://github.com/Koket987/AREPTALLER1/tags) para versiones disponibles.

## Autor

* **Santiago** - *Trabajo inicial* - [GitHub Personal](https://github.com/koket987)

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos

- Inspiración de implementaciones de servidores web minimalistas.
- Comunidad de código abierto por las mejores prácticas.

