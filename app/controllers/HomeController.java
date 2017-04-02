package controllers;

import com.google.inject.Inject;
import models.TableDetails;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.PrestoService;
import views.html.home;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
  Form<TableDetails> formData = Form.form(TableDetails.class);
  @Inject() PrestoService prestoService;

  /**
   * An action that renders an HTML page with a welcome message.
   * The configuration in the <code>routes</code> file means that
   * this method will be called when the application receives a
   * <code>GET</code> request with a path of <code>/</code>.
   */
  public Result index() {
    return ok(home.render(formData));
  }

  public Result getData() {
    Form<TableDetails> formData = Form.form(TableDetails.class).bindFromRequest();
    if (formData.hasErrors()) {
      return badRequest();
    } else {
      TableDetails tableDetails = formData.get();
      String result =
          prestoService.getData(tableDetails.getDatabaseName(), tableDetails.getTableName());
      return ok(result);
    }
  }
}

