package top.zerotop.global.enums;

/**
 * Created by:zerotop  date:2019/10/30
 */
public enum UserRoleEnum {
    ADMIN("Admin", 0);

    UserRoleEnum(String roleType, int level) {
        this.roleType = roleType;
        this.level = level;
    }

    private String roleType;
    private int level;

    public String getRoleType() {
        return roleType;
    }

    public int getLevel() {
        return level;
    }
}
