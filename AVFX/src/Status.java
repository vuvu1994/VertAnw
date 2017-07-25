import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Created by Denni on 21.07.2017.
 */
public class Status {

    public static void make(String text){

            new Thread(() -> {
                try
                {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GuiElemente.getStatus().setVisible(true);
                            GuiElemente.getStatus().setText(text);
                        }
                    });
                    Thread.sleep(1500);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GuiElemente.getStatus().setVisible(false);
                        }
                    });
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }).start();
        };

    public static void makeLong(String text){

        new Thread(() -> {
            try
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GuiElemente.getStatus().setVisible(true);
                        GuiElemente.getStatus().setText(text);
                    }
                });
                Thread.sleep(8000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GuiElemente.getStatus().setVisible(false);
                    }
                });
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }).start();
    };

    }

