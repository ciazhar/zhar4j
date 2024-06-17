# Java Style Guide

## Content

- [JPA](#jpa)

## JPA

### Auto Increment Id

Untuk id bertipe data number, better di set auto increment dari database seperti ini

```java

@Id
@GeneratedValue
private Integer id;
```

### Audit

Untuk data yang diperlukan field audit seperti createdAt, updatedAt, createdBy, updatedBy bisa menggunakan Audit. Kemudian add annotation @EntityListeners(AuditingEntityListener.class) di atas class.
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

- single data gunakan optional, kemudian pake isPresent
- multi data gunakan list
- untuk data yang banyak bisa gunakan stream
- untuk async gunakan asunc
- untuk data yang cacheable bisa di cache
- optional parameter
- cte