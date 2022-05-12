package com.example.demo4;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class FXMLDocumentController {

    @FXML
    private MediaView mediaView;
    @FXML
    MediaPlayer player;

    @FXML
    private Slider volume;

    @FXML
    private Slider time;

    @FXML
    private ImageView stop;

    @FXML
    private ImageView play;

    @FXML
    private ImageView pause;

    @FXML
    private Slider Volume;

    @FXML
    public void initialize() {
        String video = getClass().getResource("james.mp4").toExternalForm();
        Media media = new Media(video);
       player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);

        volume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                player.setVolume(volume.getValue() *0.01);
            }
        });

        //set Time Duration
        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {
                time.setValue(newValue.toSeconds());
            }
        });
        time.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(time.getValue()));
            }
        });

        time.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player.seek(Duration.seconds(time.getValue()));
            }
        });

        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total=media.getDuration();
                time.setMax(total.toSeconds());
            }
        });

    }


    @FXML
    void playVideo(MouseEvent event) {
        player.play();
    }

    @FXML
    void stopVideo(MouseEvent event) {
        player.stop();
    }


    @FXML
    void pauseVideo(MouseEvent event) {
        player.pause();
    }

    @FXML
    void exit(MouseEvent event) {
        Platform.exit();
    }

}

