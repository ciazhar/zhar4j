# Spring Boot Style Guide

> Style Guide ini ada sebagai pedoman dalam ngoding Spring Boot Java.

## Content

- [JPA](#jpa)
    - [Auto Increment Id](#auto-increment-id)
    - [Audit](#audit)
    - [Single Data Query](#single-data-query)
    - [Multi Data Query](#multi-data-query)
        - [List](#list)
        - [Stream](#stream)
        - [Async](#async)
    - [Optional Parameter](#optional-parameter)
    - Transactional
    - Cacheable

## JPA

### Auto Increment Id

Untuk membuat id bertipe data number yang auto increment, kita bisa menggunakan `@GeneratedValue`

```java

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
```

Kemudian pada migration perlu membuat sequence

```sql
CREATE SEQUENCE role_seq START 1;
CREATE TABLE roles
(
    id BIGINT DEFAULT nextval('role_seq') PRIMARY KEY,
```

### Audit

Spring memiliki fitur untuk mengisi otomatis field field audit seperti createdAt, updatedAt, createdBy dan updatedBy.
Kita hanya perlu menambahkan annotation @EntityListeners di @Entity class yang dikehendaki dan @EnableJpaAuditing di
configuration file. Field createdAt & updatedAt akan langsung di isi oleh spring.

```java

@EntityListeners(AuditingEntityListener.class)
public class User {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @ManyToOne
    @CreatedBy
    private User createdBy;

    @ManyToOne
    @LastModifiedBy
    private User lastModifiedBy;

}
```

Kemudian untuk createdBy dan updatedBy kita perlu membuat Bean auditorProvider yang returning AuditorAware<User>. Data
user ini sendiri bisa kita ambil dari SecurityContext, session dll sesuai kebutuhan.

```java

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@RequiredArgsConstructor
public class JpaConfig {

    private final UserRepository userRepository;

    @Bean
    public AuditorAware<User> auditorProvider() {
        // Assuming you have some way to provide the current user
        // You can fetch the current user from SecurityContext, session, etc.
        return () -> userRepository.findById(1);
    }
}
```

### Single data query

Untuk query single data biasakan untuk return optional, kemudian check apakah data exist menggunakan `isPresent`

```java
Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
```

### Multi data query

Ada 3 cara untuk returning multi data yaitu:

#### List

List biasanya digunakan untuk returning data yang terbatas seperti untuk pagination.

```java

@Repository
public interface MyRepository extends JpaRepository<MyEntity, Long> {
    List<MyEntity> findBySomeProperty(String property);
}
```

### Stream

Stream biasanya digunakan untuk returning data besar.

```java

@Repository
public interface MyRepository extends JpaRepository<MyEntity, Long> {
    Stream<MyEntity> findBySomeProperty(String property);
}
```

Stream bisa juga digunakan untuk untuk mengubahn data on the fly.

```java

@Service
public class MyService {
    @Autowired
    private MyRepository myRepository;

    public List<MyEntity> processEntities() {
        List<MyEntity> entities = myRepository.findAll();
        return entities.stream()
                .filter(entity -> entity.isActive())
                .collect(Collectors.toList());
    }
}

```

### Async

Async biasanya digunakan untuk mengambil data di background dengan membuat thread baru. Untuk mengaktifkanya kita perlu
menambahkan `@EnableAsync` di configuration file.

- Deklarasi async

```java

@Repository
public interface MyRepository extends JpaRepository<MyEntity, Long> {
    CompletableFuture<MyEntity> findBySomeProperty(String property);
}
```

### Optional parameter

Untuk optional parameter kita bisa menggukaan pendekatan seperti berikut

```java

@Query("SELECT e FROM Employee e WHERE (:firstName IS NULL OR e.firstName = :firstName) AND (:lastName IS NULL OR e.lastName = :lastName)")
List<Employee> findByOptionalParams(@Param("firstName") String firstName, @Param("lastName") String lastName);
```

### Projection

Untuk membuat projection di spring data kita perlu membuat interface projection terlebih dulu

```java
public interface EmployeeDetailsProjection {
    String getId();

    String getFirstName();

    String getLastName();

    String getCreatedBy();

    String getLastModifiedBy();
}
```

Kemudian kita perlu membuat repositorynya, saya lebih suka menggunakan raw query agar mudah di debug

```java
String query = """
            WITH EmployeeCTE AS (SELECT e.id, e.first_name, e.last_name, u1.username AS created_by, u2.username AS last_modified_by
                                FROM employees e
                                         LEFT JOIN users u1 ON e.created_by_id = u1.id
                                         LEFT JOIN users u2 ON e.last_modified_by_id = u2.id
                                WHERE e.age > :age)
            SELECT e.id, e.first_name, e.last_name, e.created_by, e.last_modified_by
            FROM EmployeeCTE e
        """;

@Query(value = query, nativeQuery = true)
Collection<EmployeeDetailsProjection> findAllProjectedBy(@Param("age") int age);
```