/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntradeapp;

/**
 *
 * @author g33k5qu4d
 */
public class SettingModel {

    private String username;
    private String passsword;
    private String pictureurl;
    private String stylesheet;
    private Boolean sceenshot;
    private Boolean snapShot;
    private Boolean skipformcheck;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public String getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    public Boolean getSceenshot() {
        return sceenshot;
    }

    public void setScreenshot(Boolean sceenshot) {
        this.sceenshot = sceenshot;
    }

    public Boolean getSnapShot() {
        return snapShot;
    }

    public void setSnapShot(Boolean snapShot) {
        this.snapShot = snapShot;
    }

    public Boolean getSkipformcheck() {
        return skipformcheck;
    }

    public void setSkipformcheck(Boolean skipformcheck) {
        this.skipformcheck = skipformcheck;
    }
}
