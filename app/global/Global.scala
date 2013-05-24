
package global 

import play.api._
import play.api.mvc._


object Filters extends Results {
  val checkRole = Filter { (next, rh) =>
    val nextAction: Option[RequestHeader => Result] = for {
      comments <- rh.tags.get(play.api.Routes.ROUTE_COMMENTS)
      role <- rh.headers.get("role") if role == comments
    } yield next
    nextAction.map(_.apply(rh))
      .getOrElse(Unauthorized("Not authorized to access this action. Make sure you have 'role' header with admin or anonymous"))
  }  
}

object Global extends WithFilters(Filters.checkRole)

