package scoring;

public class Goal {

    public final float topPost;
    public final float bottomPost;
    public final float depth;

    public Goal(float topPost, float bottomPost, float depth) {
        this.topPost = topPost;
        this.bottomPost = bottomPost;
        this.depth = depth;
    }

    public float getTopPost() {
        return topPost;
    }

    public float getBottomPost() {
        return bottomPost;
    }

    public float getDepth() {
        return depth;
    }
}
