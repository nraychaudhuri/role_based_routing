package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def adminService = Action {
    Ok(views.html.index("Your new application is ready."))

  }

  def userService = Action {
    Ok(views.html.index("Your new application is ready."))

  }

}