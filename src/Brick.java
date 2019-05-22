public class Brick implements AbstractBrick {

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
