This is your new Play 2.1 application
=====================================

Sample Play app to show how you can annotate your routes to perform role based routing. For example the following routes defines the roles that is required in to invoke the action in the documentation section (#admin or #anonymous):

```scala

#admin
GET    /admin-foo                   controllers.Application.adminService

#anonymous
GET    /user-foo                    controllers.Application.userService
```

Now using simple Play filter you can easily use the information to implement role based routing:

```scala
object Filters extends Results {
  val checkRole = Filter { (next, rh) =>
    val nextAction: Option[RequestHeader => Result] = for {
      comments <- rh.tags.get(play.api.Routes.ROUTE_COMMENTS)
      role <- rh.headers.get("role") if role == comments
    } yield next
    nextAction.map(_.apply(rh))
      .getOrElse(Unauthorized("Not authorized to access this action. Make sure you have 'role' header"))
  }  
}

object Global extends WithFilters(Filters.checkRole)
```