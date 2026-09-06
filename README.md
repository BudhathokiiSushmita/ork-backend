# ork-backend
This is an application where recruiters can post jobs and jobseekers can find job. Verification of application goes through series of stages.

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
Admin will create recruiter -- going with this
Recruiter will create hr and director

Recruiter
- Login
- will add their resp company first mandatory- done
- will create HR and director user
- Posts vacancy based on area - done
* - Forwards to HR of the company
*

If no companies have been created by that user, open company form
create company, 


TODOS
~~- On user creation form, Admin has to see only RECRUITER, and recruiter has to see HR & Director only **done**~~
~~- Filter for user table for admin to be able to view user created by them or other **done**~~
~~- Highlight the current nav **done**~~
~~- username in nav bar **done**~~
~~- photos of sector **done**~~
~~- Admin - recruiter form- role should be disabled to choose **done, no need of this because now only recruiter shows**~~
~~- Recruiter - vacancy form - if vacancy is unpaid - make salary range disabled **done**~~
~~- when session is timed out, name and logout sign is still there **done**~~
~~- Token expiration **done**~~
- security redesign after completing the project by making new branch
- Add TRANSACTIONAL


BUSINESS PANEL
- admin > form > edit and delete user (delete if there is another recruiter or they have no connection with any files or customer)
~~- admin > nav > no need of applicant and application on nav bar # .......~~

~~- recruiter > look > recruiter can be associated with only one company so for them when clicked on company, view shouldnt be tabular
  instead should have good view~~
- recruiter > company data editable without affecting other things **EXTRA** .....
- recruiter > form > sector should be added by admin only, recruiter should have a form to request for adding of sector and uniqueness 
  will be checked and added. **EXTRA**
- recruiter > form > vacancy should be editable or not, not sure
- recruiter > list > applicant those who have applied to the jobs posted by this recruiter should only be seen, can see the details 
  of applicant in a modal and job application history with the job #id, status **EXTRA**
- recruiter > form > user edit
- recruiter > application should be filtered with their status
- recruiter > self > user data update
- recruiter > application list > status view is not good
- recruiter > form > recruiter should be able to create only one hr and one director. We can change this for business logic 
  but for now, only one. #
- recruiter > application list > after forwarding the application it should reload the page and update the application list itself. #

- hr > application list or any list > if there are no data for the list, it should show some informative message saying its empty.
- hr > view > there is no way to view the application at all
- hr > user > nav bar > no need of user but applicant who has applied to their company so far with their job history as stated above. .......

- director > application list > if it is approved then no action button should be there and also summary is needed instead of
  view maybe with some comments they passed.

CLIENT PANEL
~~- header > application on header should be seen by them only #~~
- wishlist > need to login and see wishlist on header alongside application > tbd
~~- application > should be able to view the application #~~
~~- jobs > if already applied, "apply now" => "applied"~~
- application apply > validation while applying job of course, and maybe validation and data patch in back and forth needed _this will be at last_


After this, try to deploy it and then security remodel.

[JPQL vs NATIVE QUERY]
|               | **JPQL**                                      | **Native SQL**                     |
| ------------- | --------------------------------------------- | ---------------------------------- |
| **Purpose**   | Query **Java entities** through JPA/Hibernate | Query **database tables directly** |
| Talks to      | Entities                                      | Tables/columns                     |
| `nativeQuery` | `false` (default)                             | `true`                             |
| Best for      | Normal JPA operations, won't                  | Complex or DB-specific SQL         |
                  matter if db changes       
| Example       | `Application`, `a.vacancy.id`                 | `application`, `vacancy_id`        |

| JPQL                                                               | Native SQL                                                         |
| ------------------------------------------------------------------ | ------------------------------------------------------------------ |
| `SELECT a.vacancy.id FROM Application a WHERE a.user.id = :userId` | `SELECT a.vacancy_id FROM application a WHERE a.user_id = :userId` |


**5th Sept**
1. Changed role nav permission > hr can view applicant not user. When you start app, patch will run itself everytime
   but need to remove the 3-5 one.


**AFTER DEPLOY**
1. Add Description and photo to add while adding company.
2. View will have these, now its commented in frontend.
3. Applicant should be able to see company's data too.
