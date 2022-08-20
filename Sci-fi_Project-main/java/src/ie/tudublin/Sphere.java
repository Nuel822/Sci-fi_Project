package ie.tudublin;
import processing.core.*;

public class Sphere
{
    ScreenUI ui;
    private float x;
    private float y;
    private float width;
    private float height;

    public Sphere(ScreenUI ui, float x, float y, float width, float height)
    {
        this.ui = ui;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void render()
    {
        float w = width;
        float h = height;
        ui.size(600, 600);
        ui.fill(255);
        ui.lights();
        ui.translate(w / 2, h / 2);
        ui.sphere(200);

    }  

    /**
     * @return the ui
     */
    public ScreenUI getUi() {
        return ui;
    }

    /**
     * @param ui the ui to set
     */
    public void setUi(ScreenUI ui) {
        this.ui = ui;
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

}