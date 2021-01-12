package controllers

import javax.inject._
import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import play.api.mvc.Results._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
                                cc: ControllerComponents,
                                assets: Assets
                              ) extends AbstractController(cc) {

  /**
   * プロダクション時のみ
   * @return
   */
  def index() = assets.at(path = "/public", file = "vue-dist/index.html")

  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("picture").map { file =>
      import java.io.File
      val filename = file.filename
      val contentType = file.contentType
      file.ref.moveFileTo(new File("/tmp", filename))
      Ok(Json.toJson(Map( "status" -> "200", "response" -> "Image uploaded." )))
    }.getOrElse {
      Ok(Json.toJson(Map( "status" -> "400", "response" -> "Error occurred.")))
    }
  }
}
