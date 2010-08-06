package com.bayninestudios.eldania;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

public class Landscape {

	private DrawModel tile;
	private DrawModel dirtTile;
	private DrawModel grassTile;
	private DrawModel waterTile;
	private TileMap map;
	public boolean useTextures;

	public Landscape(Context context)
	{
		tile = new DrawModel(context, R.xml.tile);
		dirtTile = new DrawModel(context, R.xml.tile);
		grassTile = new DrawModel(context, R.xml.tile);
		waterTile = new DrawModel(context, R.xml.tile);
		map = new TileMap(context, R.xml.map);
		useTextures = false;
	}

	public void loadTextures(GL10 gl, Context context)
	{
		dirtTile.loadTexture(gl, context, R.drawable.dirt12);
		grassTile.loadTexture(gl, context, R.drawable.grass_sm);
		waterTile.loadTexture(gl, context, R.drawable.water);
	}

	public void draw(GL10 gl, float charX, float charY)
	{
		for (int y = -2; y < 4; y++)
		{
			for (int x = -4; x < 5; x++)
			{
				int tileX = x+(int)charX;
				int tileY = y+(int)charY;
				int tileType = map.getTile(tileX, tileY);
				if (tileType == 0)
				{
					gl.glColor4f(0f, 0f, .7f, 1f);
					if (useTextures)
						waterTile.draw(gl, tileX, tileY, 0f);
					else tile.draw(gl, tileX, tileY, 0f);
				}
				else if (tileType == 1)
				{
					gl.glColor4f(0f, 0.5f, 0f, 1f);
					if (useTextures)
						grassTile.draw(gl, tileX, tileY, 0f);
					else tile.draw(gl, tileX, tileY, 0f);
				}
				else if (tileType == 2)
				{
					gl.glColor4f(0.5f, 0.25f, 0.25f, 1f);
					if (useTextures)
						dirtTile.draw(gl, tileX, tileY, 0f);
					else tile.draw(gl, tileX, tileY, 0f);
				}
			}
		}
	}

	public boolean checkPassable(float x, float y)
	{
		return (map.checkPassible((int)x,(int)y));
	}
}
