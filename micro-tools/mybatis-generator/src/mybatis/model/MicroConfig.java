package model;

/**
 * generated at 2023-10-08 21:19:58
 */
public class MicroConfig {
    /**
     */
    private Long id;

    /**
     * 配置标识
     */
    private String key1;

    /**
     * 配置值
     */
    private String value1;

    /**
     * 应用: common special
     */
    private String application;

    /**
     * 环境: template
     */
    private String profile;

    /**
     * 分支: master
     */
    private String label;

    /**
     * 业务分类: common domain notice ...
     */
    private String configtype;

    /**
     * 描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1 == null ? null : key1.trim();
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1 == null ? null : value1.trim();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getConfigtype() {
        return configtype;
    }

    public void setConfigtype(String configtype) {
        this.configtype = configtype == null ? null : configtype.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}