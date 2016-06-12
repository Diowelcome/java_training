package ru.stesting.jtraining.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stesting.jtraining.mantis.model.MailMessage;
import ru.stesting.jtraining.mantis.model.UserData;
import ru.stesting.jtraining.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by DBorisov on 12.06.2016.
 */
public class ChangeUserPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangeUserPassword() throws IOException, MessagingException {
    String newPassword = "secret";
    // Считываем существующих пользователей из базы (за исключением администратора)
    Users users = app.db().users();
    // Проверяем, что существует хотя бы один пользователь
    if (users.size() > 0) {
    // Выбираем пользователя
      UserData selectedUser = users.iterator().next();
    // Запускаем браузер под администратором  и выполняем необходимые действия в пользовательском интерфейсе
      app.webSession().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
      app.goTo().manageUsers();
      app.users().changePassword(selectedUser);
    // Получаем письмо
      List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
      String confirmationLink = findConfirmationLink(mailMessages, selectedUser.getEmail());
    // Смена пароля по полученной ссылке
      app.registration().finish(confirmationLink, newPassword);
    // Проверка входа с новым паролем по http
      assertTrue(app.newSession().login(selectedUser.getUsername(), newPassword));

    } else {
      throw new Error("Для данного теста необходимо вручную создать хотя бы одного пользователя");
    }
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
