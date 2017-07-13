package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.shiro.codec.Md5Hash;
import org.apache.shiro.codec.SimpleHash;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField text_pwd;
    @FXML
    private TextField text_pwd_show;
    @FXML
    private void action(ActionEvent e){
        String saltSource = "abcdefg";

        String hashAlgorithmName = "MD5";//加密方式
        Object salt = new Md5Hash(saltSource);//盐值
        int hashIterations = 1024;//加密次数

        //加密后的密码
        Object result = new SimpleHash(hashAlgorithmName, text_pwd.getText().toString(), salt, hashIterations);
        text_pwd_show.setText(result.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        btn2.setOnAction(new EventHandler<ActionEvent>(){
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("here");
//            }
//        });
    }
}
