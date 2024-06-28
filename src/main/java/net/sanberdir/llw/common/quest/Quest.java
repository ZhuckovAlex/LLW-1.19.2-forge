package net.sanberdir.llw.common.quest;
public class Quest {
    private String id;
    private String texture;
    private String parentId;
    private String name;
    private String description;
    private Position position;
    private boolean active;

    public String getId() {
        return id;
    }

    public String getTexture() {
        return texture;
    }

    public String getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void toggleActive() {
        this.active = !this.active;
    }

    public static class Position {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}