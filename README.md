# spring-data-jpa-udemy
Ejercicios y notas del curso de Udemy: [Spring Data JPA with Hibernate](https://www.udemy.com/course/spring-data-jpa-using-hibernate/)

## Generadores
Estrategias para generar IDs:
1. AUTO: Checa con la BD, que tipos de generadores soporta.
2. IDENTITY: Auto increment
3. SEQUENCE: Crea una secuencia en la BD y usa el valor actual. (No soportado por MySQL)
4. TABLE: Crea una tabla adicional a la tabla que representa el entity.

De la siguiente forma se configura la generación del ID de un entity:
```java
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
```

Para provedores de BD que no soportan secuencias, se puede usar una tabla para generar los IDs:
```java
    // allocationSize, es el tamaño en que se va incrementando el valor para el ID
	// Debe existir una tabla con nombre "table_generator_name" y con 2 columnas: una columna para el nombre del generador (generator_name) y una columna para el valor actual del generador de IDs (generator_value)
	@Id
	@TableGenerator(name = "generator_name", table = "table_generator_name", pkColumnName = "generator_name", valueColumnName = "generator_value", allocationSize=2)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="generator_name")
	private Integer id;
```

Podemos crear nuestros propios generadores de IDs:
```java
	// generatorClass must be the fully qualified name of the custom class that generates the id. 
	// The class that generates the IDs must implement IdentifierGenerator, from Hibernate.
	@Id
	@GenericGenerator(name = "generator_name", strategy = "generatorClass")
	@GeneratedValue(generator = "generator_name")
	private String id;
```

## Métodos para búsquedas
- Las palabras clave soportadas, para generar consultas de búsqueda se pueden consultar en esta sección de la documentación: [Supported query method predicate keywords and modifiers](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.predicate)


## Paginado y ordenamiento.
- Para paginar y ordenar el resultado de los métodos de búsqueda, los repositorios deben extender de `PagingAndSortingRepository`
- Los componentes principales para paginado son: la interfaz `Pageable` y su implementación `PageRequest`. La clase `PageRequest.java` tiene métodos fábrica con los que podemos crear instancias, indicando el número de página y el tamaño de cada página.
- Los componentes principales para ordenamiento son: `Sort`, `Order` y `Direction`. La clase `Sort.java` tiene métodos fábrica, con los que podemos crear instancias indicando la dirección y las propiedades por las que queremos ordenar.
- La clase `Order.java` guarda un dirección de ordenamiento (ASC o DESC) y una o multiples propiedades por las cuales  deseamos se ordene el resultado. Esta clase nos permite ordernar por multiples propiedades y que cada propiedad tenga su propia dirección de ordenamiento.
- Uno de los métodos fábrica de `PageRequest.java` nos permite indicar las propiedades y dirección de ordenamiento para ordenar los elementos de cada página.
- Para que el resultado de los métodos personalizados se pueda paginar/ordenar, hay que agregarles un parámetro de tipo `Pageable.java`
 ```java
@Repository
public ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	List<Product> findByPriceLessThan(BigDecimal price, Pageable pageable);
}
```


## JPQL y consultas SQL nativas
- Para hacer una consulta usando *JPQL*, solamente hay que anotar el método, de un repositorio, con `@Query`:
```java
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
  // Since we want to retrieve all the columns we do not have to start our query
  // select.
  @Query("from Student")
  List<Student> findAllStudents();
  // A query that retrieve some columns
  @Query("select st.firstName from Student st")
  List<Object[]> findAllNames();
}
```
- Es posible que la anotación `@Query` contenga una sentencia para modificar renglones en una tabla, siempre y cuando se agregue tambiénn la anotacion `@Modifying` al método.
- Para usar consultas nativas, usamos la misma anotación `@Query`, pero adicionalmente tenes que agregar el atributo`nativeQuery = true`, que por defecto es `falso
 
## Mapeo de Herencia.
- Las bases de datos no soportan herencia. Sin embargo, hay 3 estrategias para mapear jerarquias de clases a tablas:
1. SINGLE_TABLE. Todos los campos, de todas las tablas en la jerarquia, están en la misma tabla. Para lo que se tiene un campo descriminador.
	Para usar esta estrategia se requiere:
	1. Anotar la clase base con:
	```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name = "columnName", discriminatorType = DiscriminatorType.STRING)
	```
	2. Anotar las clases hijas con:
	```java
@DiscriminatorValue("value")
	```
	3. Crear un repositorio para la clase base.
1. TABLE_PER_CLASS
2. JOINED. Cada clase en la jerarquia tiene su tabla correspondiente. La clase padre tiene los campos que son comunes. Cada tabla almacena la minima información posible. Las claves/tablas se relacionan por llave primaria/llave foranea. En la clase base se usa la anotación: `@Inheritance(strategy = InheritanceType.JOINED)` y las sub classes se deben de anotar con: `@PrimaryKeyJoinColumn(name = "id")`

## Mapear componentes
- Se hace cuando queremos mapear una relación `has a` en una sola tabla.
- La tabla compuesta debe tener un campo del tipo del componente, anotado con `@Embedded`
- El componente se debe anotar con `@Embeddable`
```java
@Entity
class Employee {
	@Embedded
	private Address address;
}

@Entity
@Embeddable
class Address {
}
```

## Mapeo de Asociaciones
Las relaciones entre tablas se pueden mapear a el modelo de clases con las siguientes anotaciones:
1. OnteToOne
2. ManyToMany
3. OneToMany
4. ManyToOne
- El mapeo se puede conifgurar de dos formas: unidireccional y bidireccional
- Para guardar los datos de la tabla padre e hijo se debe agregar el atributo `cascade` en la anotación.
- **Cascading** es el proceso de propagar las operaciones no-select (delete, update, insert) de la clase principal de la asociacion, a la clase hija de la asociacion.
- Para que se carge la informacion de la tabla hija se debe asignar un valor al atributo `fetch` en la anotacion de la asociacion.
```java
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
```

### Many to Many
- Para mapear las asociaciones de muchos a muchos, en donde hay 3 tablas involucradas, se usan las siguientes anotaciones en la entidad que represent al padre en la asociación:
```java
@Entity  
public class Programmer {  
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "programmers_projects",  
      joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"),  
      inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")) 
  private Set<Project> projects;
}
```
- El campo involucrado en la asociación, en la entidad hija se anota con:
```java
@ManyToMany(mappedBy = "projects")  
private Set<Programmer> programmers;
```
### One to One
- En las asociaciones uno a uno existen 2 tipos:
	1. Primary Key - Cuando todos los elementos de la tabla padre tienen un elemento correspondiente en la tabla hija. La PK de la tabla padre es también la PK en la tabla hija.
	2. Foreign Key - Cuando algunos elementos de la table padre no tienen nigun elemento correspondiente en la tabla hija. Por ejemplo: Person -> Driver Liscense. La PK de la tabla padre es una FK en la tabla hija.
- La entidad hija se anota con:
```java
@OneToOne
@JoinColumn(name = "column_name")
```
- La entidad padre se anota con:
```java
@OneToOne(mappedBy = "fieldNAmeInChildEntity")
```

### Caching
- Guardar las la información leida de la BD, convertida en objetos, en memoria, para mejorar el desempeño de la aplicación.
- Hibernate, que es una implementación de JPA, soporta 2 niveles de cache:
	1. Primer nivel: a nivel de la sesión de hibernate. Por default
	2. Segundo nivel: a nivel de la fábrica de sesiones (*SessionFactory*). El cache está compartido por todas las sesiones (de hibernate), existentens en nuestra aplicación. Se deben usar provedores de cache, por ejemplo: *ehcache*

### Transacciones
- Una transacción es una sección atómica de operaciones, en donde todas se ejecutan o ninguna se ejecuta.
- Las transacciones tiene las propiedades ACID (Atomicity, Consistency, Isolation, Durability )
	- Atomicidad.
	- Consistencia, quiere decir que la BD debe quedar en un estado consistente antes de comenzar la transacción y después de que finaliza la transacción.
	- Aislamiento (Isolation): Si hay muchas transacciones al mismo tiempo, cada una de ellas debe trabajar independientemente y no afectar a las demás.
	- Durabilidad: Una vez que lo cambios en la BD son confirmados (*committed*), deben permanecer en la BD.

- To retrieve images/audio from database you have to annotate the field with:
```java
@Lob
private byte[] data;
```

- Estos son los pasos para modelar claves compuestas:
	- Crear una clase que represente la clave compuesta. Esta clase debe implementar `Serializable`
	- Anotar la entidad, con `@IdClass` y declarar nuevamente cada uno de los campos de la llave compuesta. Y anotar cada campo con `@Id`
	- El repositorio deberá indicar que el tipo para el id es la clase que representa la clave compuesta

- Una segunda forma para mapear claves compuestas es:
	- Anotar la clase que representa la tabla compuesta con `@Embeddable`
	- Agregar un campo a la entidad, de el tipo de la clase que representa la clave compuesta, y anotar el campo con `@EmbeddedId`
