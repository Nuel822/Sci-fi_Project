package ie.tudublin;

import processing.core.*;

public class Star extends Sprite {
    public int mph = 20;
    private int maxSpeed = 250;

    public Star(ScreenUI ui) {

        super(ui, ui.random((ui.width / 2) - 1, (ui.width / 2) + 1),
                ui.random((ui.height / 2) - 1, (ui.height / 2) + 1), 0, 20);

        if (pos.x < ui.width / 2) {
            forward.x = -1;
            forward.y = ui.random(-1, 1);
        }

        if (pos.x > ui.width / 2) {
            forward.x = 1;
            forward.y = ui.random(-1, 1);
        }

        if (pos.x == ui.width / 2) {
            forward.x = 0;
            forward.y = ui.random((float) -1, (float) 1);
        }

        forward.normalize();

    }

    @Override
    public void update() {
        // pos =+ forward * speed;
        pos.add(PVector.mult(forward, speed));
        rotation += 0.01f;

        if (ui.keyPressed) {
            if (ui.key == 'L' || ui.key == 'l' && mph <= maxSpeed) {
                this.speed += 10;
                mph += 10;
            }

            else if (ui.key == 'J' || ui.key == 'j' && mph > 20) {
                this.speed -= 5;
                mph -= 5;
            }
        }

        if (pos.x < 0) {
            pos.x = ui.width / 2 - 1;
            pos.y = ui.height / 2;
        }

        if (pos.x > ui.width) {
            pos.x = ui.width / 2 + 1;
            pos.y = ui.height / 2;
        }

        if (pos.y < 0) {
            pos.x = ui.width / 2;
            pos.y = ui.height / 2 - 1;

        }
        if (pos.y > ui.height) {
            pos.x = ui.width / 2;
            pos.y = ui.height / 2 + 1;
        }
    }

    public void drawText() {
        ui.fill(33, 157, 150);
        ui.textSize(30);
        ui.text("Current Speed", ui.width - 300, ui.height * 0.78f);
        ui.textSize(100);
        ui.text(mph + " MPH", ui.width - 300, ui.height * 0.85f);

        if (this.speed == 250) {

            ui.fill(33, 157, 150);
            ui.text("MAX SPEED!", ui.width - 100, ui.height * 0.9f);
        }
    }

    int size = 1;

    @Override
    public void render() {
        ui.noStroke();
        ui.pushMatrix();
        ui.translate(pos.x, pos.y);
        ui.rotate(rotation);
        ui.fill(33, 157, 150);
        ui.ellipse(-size / 2, -size / 2, 5, 5);
        ui.popMatrix();
        drawText();
    }
}