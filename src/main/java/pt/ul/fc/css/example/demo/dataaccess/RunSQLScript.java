package pt.ul.fc.css.example.demo.dataaccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.util.ResourceUtils;

public class RunSQLScript {

  private RunSQLScript() {}

  public static void runScript(Connection connection, String scriptFilename)
      throws IOException, SQLException {
    File file = ResourceUtils.getFile("classpath:" + scriptFilename);
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String command;
      while ((command = br.readLine()) != null) {
        try (Statement statement = connection.createStatement()) {
          statement.execute(command);
        }
      }
    }
  }
}
