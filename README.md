# Parcial1_arep_IvanTorres

Usted debe construir un "Almacenamiento llave-Valor (key, value) distribuido". La solución consta de un servidor backend que responde a solicitudes HTTP GET de la Facade, un servidor Facade que responde a solicitudes HTTP GET del cliente , y un cliente Html+JS que envía los comandos y muestra las respuestas. La api permite almacenar tuplas llave-valor (k,v), ambas de tipo string en el backend, y recuperar el valor dada una llave. 

# API mínima para “Almacenamiento llave-valor” (GET/POST)

- Cliente → Fachada
````
GET /setkv?key={key}&value={value}´
````
Descripción: Crea o reemplaza el valor asociado a una llave.

Respuestas:

200 OK – reemplazado  o creado.
400 Bad Request – faltan key o value o no son string.

````
GET /getkv?key={key}
````
Descripción: Obtiene el valor de una llave.

- Fachada → Backend
````
GET /setkv?key={key}&value={value}
````
Descripción: Crea o reemplaza el valor asociado a una llave.
````
GET /getkv?key={key}
````
Descripción: Obtiene el valor de una llave.



## Consideraciones mínimas

**Arquitectura**
 
- La aplicación tendrá tres componentes distribuidos: Una fachada de servicios, un servicio de backend, y un cliente web (html +js).
- Los servicios de la fachada y del backend deben estar desplegados en máquinas virtuales java  diferentes.
- El cliente es un cliente web que usa html y js. Se descarga desde un servicio en la fachada (Puede entregar el cliente directamente desde un método no es necesario que lo lea desde el disco).
- La comunicación se hace usando http y las respuestas de los servicios son en formato JSON.
- Los llamados al servicio de fachada desde el cliente deben ser asíncronos usando el mínimo JS prosible. No actualice la página en cada llamado, solo el resultado.
-Los retornos deben estar  en formato JSON o TEXTO.
- El diseño de los servicios WEB debe tener en cuenta buenas prácticas de diseño OO.

### Puerto que vamos a utilizar
````
Puerto: 36000
````

## Para comenzar
Estas instrucciones te permitirán obtener una copia del proyecto y ejecutarlo en tu máquina local para desarrollo y pruebas.  

### Estructura del proyecto

La estructura de directorios del proyecto es la siguiente:


```
.
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── escuelaing
    │   │           └── edu
    │   │               └── co
    │   │                   └── tallerconocimientos1
    |   |                      |__HttpServer.java
    │   └── public
    │       └── resources
    │           └── index.html
    └── pom.xml
```


### Prerequisites
Debes tener instalado en tu equipo:  

- [Java 17 o superior](https://adoptium.net/)  
- [Apache Maven](https://maven.apache.org/) 

Verifica las versiones:
- $ java -version
- $ mvn -version


### Instalación
Clona este repositorio en tu máquina:
```
git clone https://github.com/Ttowers-09/Parcial1_arep_IvanTorres.git
```
Accedemos a la carpeta:
```
cd Parcial1_arep_IvanTorres
```


## Ejecución (Con Maven)
Estando dentro de la carpeta Parcial1_arep_IvanTorres ejecutamos el siguiente  comando:
```
mvn clean install
```
Con esto aseguramos que la compilación se realice desde cero eliminando cualquier archivo de compilación anterior.
luego utilizamos:
```
mvn compile
```
Para ejecutar el código fuente del proyecto.
luego utilizamos:
```
mvn exec:java
```
Para ejecutar la clase principal con método main.

### Finalizar la ejecución

En la consola ejecutamos la combinación de teclas: 

```
Windows: Ctrl + C
Mac: Comando + C
```

## Construido con:

- **Java Standard Library** - El proyecto está construido con clases de I/O y java.net de la librería estándar de Java.

- **Maven** - Se utiliza como herramienta de gestión de dependencias y compilación.

## Versionamiento

Usamos Git y GitHub para realizar el versionamiento del proyecto.

## Authors

* **Ivan Santiago Forero Torres** - *Trabajo inicial* - gitHub User: [Ttowers-09]


## Licencia

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
