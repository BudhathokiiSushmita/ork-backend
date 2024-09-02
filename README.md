# ork-backend
This is an application where recruiters can post jobs and jobseekers can find job. Verification of application goes through series of statges.

# Notes
SecurityConfig
UserRepository
CustomUser


* [JPA]: It provides a framework for object-relational mapping (ORM) and data persistence,
* allowing developers to work with databases using Java objects.
*
* Advantages:
* 1. Object-Relational Mapping (ORM): JPA maps Java objects to database
* tables, allowing you to interact with the database using Java objects rather than SQL queries.
*
* 2. Criteria API: JPA includes a Criteria API for building type-safe queries programmatically.
*
* 3. First and Second Level Caching
*
* 4. Allowing you to manage transactions declaratively using annotations like @Transactional.
* This simplifies transaction management and ensures consistency.
*
* 5. JPA provides annotations for defining and managing relationships between entities,
* such as @OneToOne, @OneToMany, @ManyToOne, and @ManyToMany
*
* 6. JPA is a standard specification in Java, which means you can use different JPA implementations
* (like Hibernate, EclipseLink, and OpenJPA) interchangeably
*
* [Hibernate]: It is a default JPA implementation. It is a ORM.

* [AbstractPersistable]: is a base class provided by Spring Data JPA that helps simplify
* the creation of entity classes. It implements the Persistable interface,
* which defines methods for dealing with entity persistence.
* It provides method like isNew(), getId().
* You can use AbstractPersistable when you want to reduce boilerplate code in
* your entity classes, especially when your entities use Long as the ID type
* and you don't need any custom ID management logic.
*
* CONS: This generates _seq table, to stop that we need to put primary key as IDENTITY
* but it is Sequenece by default. Cannot change it, so it is ok to use it if we are not
* creating table using hibernate. Can use Liquibase instead.
*
* [Serializable]: We implement Serializable to convert JPA entity to byte stream.
* You can certainly persist data to a database without using Serializable. However,
* if your use case involves serialization (like writing objects to files, sending
* them over a network, or storing them in sessions for replication), you will need Serializable.

* TYPES: There are many ways to implement Spring Security - SecurityFilterChain, WebSecurityConfigurerAdapter,
* Method based, @Preauthorize, @RolesAllowed, @Secured. Depends on the project's need, we choose these.
*
* METHODS: SecurityFilterChain is latest in Spring 5.0. Even inside this, we have different ways to handle request
* to endpoint. CSRF is disabled to CSRF token is not there for when it is not a web browser. authorizeRequests
* is to handle access using in the deprecated class, but there is authorizeHttpRequests which is new since
* Spring 5.0. It allows to add custom filter, lambda expression and is used inside SFC.
*
* MECHANISM: For the spring security, we have filter -> authentication manager
* -> authentication providers -> UserDetailsService

# BUSINESS LOGIC

* Admin
* - Will Add companies
* - Will add area, admin can add this
*
* Recruiter
* - Signup based on company
* - Login
* - Posts vacancy based on area
* - Forwards to HR of the company
* - will create HR and Director user
*
* HR
* - Verifies if the job seeker is eligible (just by looking at)
* -  Forwards to Director of the company
*
* Director
* - Approves and sets time for interview
*
* Applicant
* - Signup
* - Login
* - Sees multiple companies before login, this is PUBLIC
* - Can apply to the hiring post
* - Sees the process throughout
*
* Same login page, dashboard and navigation is based on role's nav permissions


New idea
Admin will create recruiter
Recruiter will create hr and director
