public class SideProtect extends BrickDecorator {

    SideProtect(AbstractBrick brick) {
        super(brick);
    }

    @Override
    public int getSideProtected() {
        return 0;
    }

    @Override
    public void draw() {

    }

    @Override
    public Bonus destroy() {
        return null;
    }
}
