package com.example.mirash.toshkintest;

import android.view.Display;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class MainClass extends BaseGameActivity {

    private Camera mCamera;
    private Scene mMainScene;
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TextureRegion mBackgroundTexture;
    private TextureRegion mPlayerTexture;
    private Sprite player;
    int cameraWidth;
    int cameraHeight;
    int PlayerHeight;
    TiledTextureRegion _TiledSprite_TR;
    AnimatedSprite _AnimSprite;
    @Override
    public Engine onLoadEngine() {
        final Display display = getWindowManager().getDefaultDisplay();
        cameraWidth = display.getWidth();
        cameraHeight = display.getHeight();

        mCamera = new Camera(0, 0, cameraWidth, cameraHeight);

        return new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.LANDSCAPE,
                new RatioResolutionPolicy(cameraWidth, cameraHeight), mCamera));


    }

    @Override
    public void onLoadResources() {
        mBitmapTextureAtlas = new BitmapTextureAtlas(4096, 4096,
               TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");


  //      mPlayerTexture = BitmapTextureAtlasTextureRegionFactory
      //          .createFromAsset(this.mBitmapTextureAtlas, this, "panda.png",
       //                 cameraWidth + 100, 0);

        PlayerHeight = cameraHeight/8;


        mBackgroundTexture = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(this.mBitmapTextureAtlas, this, "fon.png",
                        0, 0);
        mBackgroundTexture.setHeight(cameraHeight);
        mBackgroundTexture.setWidth(cameraWidth);
        _TiledSprite_TR =
                BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                        this.mBitmapTextureAtlas, this, "animpanda.png", 3000,3000, 1, 2);

        _AnimSprite = new AnimatedSprite(100, 100, _TiledSprite_TR);
        _AnimSprite.animate(300);
        _AnimSprite.setHeight(PlayerHeight);
        _AnimSprite.setWidth(PlayerHeight);
     //   _TiledSprite_TR.setWidth(PlayerHeight);
        mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
    }

    @Override
    public Scene onLoadScene() {
        mEngine.registerUpdateHandler(new FPSLogger());

        mMainScene = new Scene();
      //  player = new Sprite(0, cameraHeight  - PlayerHeight, mPlayerTexture);

        final int centerX = (cameraWidth -
                mBackgroundTexture.getWidth()) / 2; final int centerY = (cameraHeight -
                mBackgroundTexture.getHeight()) / 2;
        SpriteBackground bg = new SpriteBackground(new Sprite(centerX, centerY, mBackgroundTexture));

        mMainScene.setBackground(bg);
        _AnimSprite = new AnimatedSprite(100, 100, _TiledSprite_TR);
        _AnimSprite.animate(300);
        _AnimSprite.setHeight(PlayerHeight);
        _AnimSprite.setWidth(PlayerHeight);
   //     mMainScene.attachChild(player);
        mMainScene.attachChild(_AnimSprite);
        return mMainScene;
    }

    @Override
    public void onLoadComplete() {

    }
    public class Bolb{}
