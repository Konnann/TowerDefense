package gameObjects.PlayerAssets;

import entities.ProjectileEntity;
import game.Game;
import game.MouseInput;
import game.MouseMoving;
import gameObjects.Enemy.Enemy;
import gameObjects.Projectile.Arrow;
import graphics.Assets;
import graphics.SpawnEnemies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class Crossbow extends PlayerAssets {

    private BufferedImage crossbowSprite;

    private int crossbowWidth;
    private int crossbowHeight;

    private int x;
    private int y;
    private int yPos;

    private Rectangle boundingBox;
    private ArrayList<ProjectileEntity> projectiles;

    private Timer timer;
    private  ActionListener actionListener;

    public static boolean isDragged;
    private boolean isShooting;
    private Enemy[][] enemies;
    public boolean isActive;


    //Create the crossbow
    public Crossbow() {
        this.isActive = true;
        this.crossbowWidth = 164;
        this.crossbowHeight = 158;

        this.x = -crossbowWidth / 2;
        this.y = 260;
        this.yPos = 260;

        this.actionListener = e -> isShooting = true;
        this.timer = new Timer(500, actionListener);
        timer.start();

        this.boundingBox = new Rectangle (this.x, this.y, this.crossbowWidth, this.crossbowHeight);
        crossbowSprite = Assets.crossbow;

        this.projectiles = new ArrayList<ProjectileEntity>();
        this.isShooting = false;

    }

    public void tick(Enemy[][] enemies) {

        if(!isActive) {
            return;
        }

        //update enemyList
        this.enemies = enemies;
        this.yPos = this.calcY((int)MouseMoving.y - 300);

        this.boundingBox.setBounds(this.x, this.y, this.crossbowWidth, this.crossbowHeight);
        if(isShooting && isDragged || isShooting && MouseInput.mousePressed){
            shoot();
            isShooting = false;
            isDragged = false;
        }

        for (int i = 0; i < projectiles.size() ; i++) {
            projectiles.get(i).tick(this.enemies);
            //check if target is hit
            if(projectiles.get(i).getTargetIsHit()){
                projectiles.remove(i);
            }
        }




    }

    public void render(Graphics g) {
        if(!isActive) {
            return;
        }
        Graphics2D gr = (Graphics2D) g;

        gr.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        int cx = crossbowSprite.getWidth() / 2 - 20;
        int cy = crossbowSprite.getHeight() / 2;
        AffineTransform oldAT = gr.getTransform();
        gr.translate(cx + this.x, cy + this.y);
        gr.translate(-cx, -cy);


        for (ProjectileEntity projectile : projectiles) {
            projectile.render(g);
        }

      //  this.arrow.render(g, this.yPos);

        gr.drawImage(crossbowSprite, 0, this.yPos, null);
        gr.setTransform(oldAT);


    }

    private int calcY(int y){
        int maxTopRange = Game.getHeight() -  575;
        int ret = y;
        if(y < 0 && Math.abs(y) > maxTopRange){
            ret = -maxTopRange;
        }else if(y > 0 && Math.abs(y) > maxTopRange){
            ret = 170;
        }

        return ret;
    }
    private void shoot(){
        projectiles.add(new Arrow(this.yPos + 60));

    }
    public double getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public int getYPos(){
        return this.yPos;
    }

    public void setShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }




}
