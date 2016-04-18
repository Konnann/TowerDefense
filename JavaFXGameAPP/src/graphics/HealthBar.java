package graphics;

public class HealthBar {

    private int x = 0;
    private int y = 0;
    private int maxWidth = 0;
    private int maxHeight = 0;
    private int currentWidth = 0;
    private int currentHeight = 0;
    private int maxhealth = 0;

    /*(barHP.Width / barMaxHP.Width) * 100 = (HP / maxHP) * 100 -->
       barHP.Width = ((HP / maxHP) * 100 * barMaxHP.Width) / 100 -->
       barHP.Width = (HP / maxHP) * barMaxHP.Width.
    */

    public HealthBar(int x, int y, int maxWidth, int maxHeight, int maxhealth) {
        this.x = x;
        this.y = y;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.maxhealth = maxhealth;
    }
    public void tick(int currentHealth){


    }

}
