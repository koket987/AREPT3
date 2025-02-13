# Servidor Web en Java

Este proyecto es un servidor web simple implementado en Java sin utilizar frameworks externos. Permite servir archivos estáticos (HTML, CSS, JS, imágenes) y proporciona una API REST para interacciones básicas, configurada mediante anotaciones y expresiones lambda.

## Características
- **Registrar endpoints REST**: usando anotaciones como `@GetMapping`.
- **Extraer parámetros de consulta**: mediante `@RequestParam`.
- **Autodescubrimiento de controladores**: Carga automáticamente clases anotadas con `@RestController`.
- **Soporte para JSON**: Las respuestas se devuelven en formato JSON.
- **Servir archivos estáticos**: Configura el directorio de archivos con `staticfiles()`.

## Prerrequisitos
Asegúrate de tener instalado:
- **Java 8 o superior**
- **Apache Maven**
- **Git**

## Instalación
### Clonar y Construir el Proyecto
```bash
git clone https://github.com/Koket987/AREPTALLER1.git
cd AREPTALLER1
mvn clean install
```

## Cómo Ejecutar
### Compilar el Proyecto
Utiliza Maven, Gradle o tu IDE preferido para compilar las clases.

### Ejecutar el Servidor
Para cargar el controlador desde la línea de comandos, ejecuta:
```bash
java -cp target/classes co.edu.eci.arep.HttpServer co.edu.eci.arep.GreetingController
```
Esto registra el `GreetingController` y mapea el método con `@GetMapping("/greeting")`.

### Probar el Servicio REST
Abre en el navegador, usa PostMan o utiliza `curl` la siguiente URL:
```bash
http://localhost:35000/App/rests/greeting?name=Juan
```
La respuesta será similar a:
```json
{"message": "Hola Juan"}
```

### Servir Archivos Estáticos
Si tienes un archivo `index.html` en `src/main/resources/www`, podrás acceder a él vía:
```bash
http://localhost:35000/App/index.html
```

## Diseño del Sistema
- **Manejo de solicitudes HTTP**: Soporta métodos `GET` y `POST`, diferenciando entre peticiones REST (prefijo `/App`) y solicitudes de archivos estáticos.
- **Manejo de archivos estáticos**: Permite servir HTML, CSS, JavaScript e imágenes desde un directorio configurado.
- **Endpoints REST**: Permite registrar servicios REST de forma sencilla mediante anotaciones.

Las principales clases del código son:
- `HttpRequest.java`: Procesa y parsea la petición HTTP.
- `HttpResponse.java`: Permite gestionar la respuesta HTTP.
- `HttpServer.java`: Implementa el framework y maneja la configuración.
- `ExampleApp.java`: Ejemplo de aplicación que utiliza el framework.

## Pruebas
### Pruebas Unitarias
Ejecuta las pruebas con:
```bash
mvn test
```

Verifican que:
- **GET** `http://localhost:35000/App/rests/greeting?name=Santiago` devuelve `{"message": "Hola Santiago"}`.
- **GET** `http://localhost:35000/App/pi` devuelve `3.141592653589793`.
- Los archivos estáticos se sirven correctamente.

### Pruebas de Extremo a Extremo
Prueba el servidor accediendo a:
```bash
http://localhost:35000
```

#### API REST
Con `curl`:
```bash
curl -X GET "http://localhost:35000/App/rests/greeting?name=Santiago"
```
Respuesta esperada:
```json
{"message": "Hola Santiago"}
```

#### Archivos Estáticos
Accede desde un navegador a:
```bash
http://localhost:35000/App/index.html
```

## Despliegue
Para ejecutar el servidor en segundo plano:
```bash
nohup mvn exec:java -Dexec.mainClass="co.edu.eci.arep.HttpServer" &
```

## Construido Con
- **Java** - Lenguaje principal utilizado.
- **Maven** - Para la gestión de dependencias.
- **JUnit** - Para pruebas automatizadas.

## Contribuciones
Siéntete libre de hacer fork y enviar pull requests para mejorar el proyecto.

## Versionado
Este proyecto sigue [SemVer](http://semver.org/). Consulta los [tags en este repositorio](https://github.com/Koket987/AREPTALLER1/tags) para versiones disponibles.

## Autor
* **Santiago** - *Trabajo inicial* - [GitHub Personal](https://github.com/koket987)

## Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## Agradecimientos
- Inspiración de implementaciones de servidores web minimalistas.
- Comunidad de código abierto por las mejores prácticas.

