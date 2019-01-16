package com.wechat.model.qrcode;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rongyaowen
 * on 2019/1/16.
 * 二维码获取类中请求参数
 */
public class QrCodeParam {
    @SerializedName("expire_seconds")
    private Integer expireSeconds;
    @SerializedName("action_name")
    private String actionName;
    @SerializedName("action_info")
    private ActionInfo actionInfo;

    /**
     * 二维码信息
     */
    public class ActionInfo {
        private Scene scene;

        /**
         * 场景值
         */
        public class Scene {
            @SerializedName("scene_id")
            private Integer sceneId;
            @SerializedName("scene_str")
            private String sceneStr;

            public Integer getSceneId() {
                return sceneId;
            }

            public void setSceneId(Integer sceneId) {
                this.sceneId = sceneId;
            }

            public String getSceneStr() {
                return sceneStr;
            }

            public void setSceneStr(String sceneStr) {
                this.sceneStr = sceneStr;
            }
        }

        public Scene getScene() {
            return scene;
        }

        public void setScene(Scene scene) {
            this.scene = scene;
        }
    }

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public ActionInfo getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }
}
