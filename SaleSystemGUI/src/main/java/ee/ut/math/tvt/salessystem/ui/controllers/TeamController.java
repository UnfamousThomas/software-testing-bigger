package ee.ut.math.tvt.salessystem.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeamController implements Initializable {

    @FXML
    private ImageView imageView;
    private static final Logger log = LogManager.getLogger(TeamController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InputStream inputStream = getClass().getResourceAsStream(resources.getString("logoPath"));
        if(inputStream == null) {
            log.error("No image found in stream.");
        } else {
            imageView.setImage(new Image(inputStream));
        }
        log.info("Team controller started");
    }
}
