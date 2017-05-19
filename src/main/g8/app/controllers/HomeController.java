package controllers;

import javax.inject.Inject;

import models.DataSourceDetails;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.PrestoService;
import views.html.home;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
  private Form<DataSourceDetails> formData;
  private final PrestoService prestoService;

  @Inject public HomeController(PrestoService prestoService, FormFactory formFactory) {
    this.prestoService = prestoService;
    this.formData = formFactory.form(DataSourceDetails.class);
  }

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
    Form<DataSourceDetails> formData = Form.form(DataSourceDetails.class).bindFromRequest();
    if (formData.hasErrors()) {
      return badRequest();
    } else {
      DataSourceDetails dataSourceDetails = formData.get();
      String result = prestoService.getData(dataSourceDetails.getCatalogName().toLowerCase(), dataSourceDetails.getDatabaseName().toLowerCase(),
          dataSourceDetails.getTableName().toLowerCase());
      return ok(result);
    }
  }
}

