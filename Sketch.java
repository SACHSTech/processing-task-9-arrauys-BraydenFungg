import processing.core.PApplet;

public class Sketch extends PApplet {
	
  float[] SnowY = new float[25];
  float[] SnowX = new float[25];
  float fltSnowSpeed = 1;
  int intLives = 3;

  float fltPlayerX = 200;
  float fltPlayerY = 350;
  boolean[] isHidden = new boolean[25];
  int intPoints = 0;

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(100, 100, 200);
    for (int i = 0; i < SnowX.length; i++){
      SnowX[i] = random(width);
      SnowY[i] = random(height);
    }
  }

  public void draw() {
    // Set background color
    background(100, 100, 200);
    
    // Draw Player
    lives();
    fill(0, 0, 128);
    stroke(0);
    ellipse(fltPlayerX, fltPlayerY, 20, 20);
       
    
    for (int i = 0; i < SnowY.length; i++){
      if (isHidden[i] == false){
        fill(255);
        noStroke();
        ellipse(SnowX[i], SnowY[i], 30, 30);
        SnowY[i] = SnowY[i] + fltSnowSpeed;
      }
      
      if (intPoints == 25){
        textSize(50);
        text("You Win!", 100, 200); 
        fill(200); 
      }
      
      if (SnowY[i] > height){
        SnowY[i] = 0;
      }
      
      if (dist(SnowX[i], SnowY[i], fltPlayerX, fltPlayerY) < 20){
        isHidden[i] = true;
        SnowX[i] = 0;
        SnowY[i] = 0;
        intLives = intLives - 1;
        intPoints = intPoints + 1;
      }

    }
    // Press Key for Snow Speed
    if (keyPressed){
      if (keyCode == UP && fltSnowSpeed > 1){ 
        fltSnowSpeed = (float)(fltSnowSpeed - 0.05);
      }
      else if (keyCode == DOWN){
        fltSnowSpeed = (float)(fltSnowSpeed + 0.05);
      }
    }
    // Press Key for Player Movement
    if (keyPressed) {
      if (key == 'w') {
        fltPlayerY-= 1;
      } 
      else if (key == 'a') {
        fltPlayerX-= 1;
      } 
      else if(key == 's'){
        fltPlayerY+= 1;
      }
      else if(key == 'd'){
        fltPlayerX+= 1;
        }
      } 
    

    if (fltPlayerX >= 400){
      fltPlayerX = fltPlayerX - 5;
    }
    else if (fltPlayerX <= 0){
      fltPlayerX = fltPlayerX + 5;
    }
    if (fltPlayerY >= 400){
      fltPlayerY = fltPlayerY - 5;
    }
    else if (fltPlayerY <= 0){
      fltPlayerY = fltPlayerY + 5;
    }
  }
  
  public void mousePressed() {
    for (int i = 0; i < SnowY.length; i++){
      if (dist(SnowX[i], SnowY[i], mouseX, mouseY) < 20){
        isHidden[i] = true;
        SnowX[i] = 0;
        SnowY[i] = 0;
        intPoints = intPoints + 1;
      }
    }
  }

  public void lives() {
    if(intLives == 3){
      fill(240, 0, 24);
      rect(280, 10, 30, 30);
      rect(320, 10, 30, 30);
      rect(360, 10, 30, 30);
    
    }else if(intLives == 2){
      fill(240, 100, 100);
      rect(320, 10, 30, 30);
      rect(360, 10, 30, 30);
    
    }else if(intLives == 1){
      fill(240, 200, 200);
      rect(360, 10, 30, 30);

    }if (intLives <= 0){
      background(255);  
    }
  }
}