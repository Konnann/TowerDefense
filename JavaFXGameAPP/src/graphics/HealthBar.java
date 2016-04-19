package graphics;

import java.awt.*;

public class HealthBar {

    private int x = 0;
    private int y = 0;
    private int maxWidth = 0;
    private double currentWidth = 0;
    private int height = 0;
    private int maxHealth = 0;
    private double currentHealth = 0;
    private Rectangle emptyHealthBar;

    /*(barHP.Width / barMaxHP.Width) * 100 = (HP / maxHP) * 100 -->
       barHP.Width = ((HP / maxHP) * 100 * barMaxHP.Width) / 100 -->
       barHP.Width = (HP / maxHP) * barMaxHP.Width.
    */

    public HealthBar(int x, int y, int maxWidth, int height, int maxHealth) {
        this.x = x;
        this.y = y;
        this.maxWidth = maxWidth;
        this.height = height;
        this.maxHealth = maxHealth;


    }
    public void tick(int x, int y, double currentHealth){
            this.x = x;
            this.y = y;
            this.currentHealth = currentHealth;
            this.currentWidth = (this.currentHealth / this.maxHealth) * this.maxWidth;

    }
    public void render(Graphics graphics){

        //Empty health bar
        graphics.setColor(Color.gray);
        graphics.fillRect(this.x, this.y, this.maxWidth, this.height);

        //current health, changes color to red if you're at one third of health
        if(currentWidth >= maxWidth / 3 ) {
            graphics.setColor(new Color(50, 180, 50));
            graphics.fillRect(this.x, this.y, (int) this.currentWidth, this.height);
        }else  if(currentWidth < maxWidth / 3 && currentWidth >= 0){
            graphics.setColor(new Color(190,0,0));
            graphics.fillRect(this.x, this.y, (int) this.currentWidth, this.height);
        }
        //border
        graphics.setColor(new Color(0, 0, 0));
        graphics.drawRect(this.x, this.y, this.maxWidth, this.height);

    }

}
