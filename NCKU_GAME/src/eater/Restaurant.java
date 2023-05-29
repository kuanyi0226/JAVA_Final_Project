package eater;
import java.awt.*;

public class Restaurant {
	
	Image img;
	
	int x;
	int y;
	int width;
	int height;
	int speed;
	
	int direction = 1;
	int type;
	int count;
	public void paintSelf(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}
	
	public Rectangle getRec() {
		return new Rectangle(x, y, width, height);
	}

}

//in 100
//紅樓
class Rest_l extends Restaurant{
	Rest_l(){
		this.x = -45;
		this.y = (int)(Math.random()*700+100);
		this.width = 45;
		this.height = 69;
		this.speed = 10;
		this.type = 1;
		this.count = 1;
		this.img = GameImage.rest1;
	}
	
}
//上樂炒飯
class Rest_r extends Restaurant{
	Rest_r(){
		this.x = 1400;
		this.direction = -1;
		this.y = (int)(Math.random()*700+100);
		this.width = 45;
		this.height = 69;
		this.speed = 10;
		this.type = 1;
		this.count = 1;
		this.img = GameImage.rest2;
	}
	
}
//活力  東馬
class Rest_3 extends Restaurant{
	Rest_3(){
		this.x = -45;
		this.y = (int)(Math.random()*700+100);
		this.width = 45;
		this.height = 69;
		this.speed = 10;
		this.type = 1;
		this.count = 1;
		this.img = GameImage.rest3;
	}
	
}

//老夫子
class Rest_4 extends Restaurant{
	Rest_4(){
		this.x = 1400;
		this.direction = -1;
		this.y = (int)(Math.random()*700+100);
		this.width = 45;
		this.height = 69;
		this.speed = 10;
		this.type = 1;
		this.count = 1;
		this.img = GameImage.rest4;
	}
	
}



//100~200


//南洋杉
class Rest_5 extends Rest_l{
	Rest_5(){
		this.x = -100;
		this.y = (int)(Math.random()*700+100);
		this.width = 100;
		this.height = 100;
		this.speed = 5;
		
		this.type = 2;
		this.count = 2;
		this.img = GameImage.rest5;
	}
	
}
//簡單
class Rest_6 extends Rest_r{
	Rest_6(){
		this.width = 100;
		this.height = 100;
		this.type = 2;
		this.count = 2;
		this.img = GameImage.rest6;
	}
	
}
//辣絕
class Rest_7 extends Rest_l{
	Rest_7(){
		this.x = -100;
		this.y = (int)(Math.random()*700+100);
		this.width = 100;
		this.height = 100;
		this.speed = 5;
		
		this.type = 2;
		this.count = 2;
		this.img = GameImage.rest7;
	}
	
}

//花語
class Rest_8 extends Rest_r{
	Rest_8(){
		this.width = 100;
		this.height = 100;
		this.type = 2;
		this.count = 2;
		this.img = GameImage.rest8;
	}
	
}



//200 up

//井選
class Rest_9 extends Rest_l{
	Rest_9(){
		this.x = -300;
		this.width = 150;
		this.height = 150;
		this.speed = 3;
		this.type = 3;
		this.count = 3;
		this.img = GameImage.rest9;
	}
	//too big
	public Rectangle getRec() {
		return new Rectangle(x + 40,y + 30, width - 80, height - 60);
	}
	
}

//日曜日
class Rest_10 extends Rest_r{
	Rest_10(){
		this.x = 1400;
		this.direction = -1;
		this.width = 150;
		this.height = 150;
		this.speed = 3;
		this.type = 3;
		this.count = 3;
		this.img = GameImage.rest10;
	}
	
	public Rectangle getRec() {
		return new Rectangle(x + 40,y + 30, width - 80, height - 60);
	}
	
}

//石火山
class Rest_11 extends Rest_l{
	Rest_11(){
		this.x = -300;
		this.width = 150;
		this.height = 150;
		this.speed = 3;
		this.type = 3;
		this.count = 3;
		this.img = GameImage.rest11;
	}
	//too big
	public Rectangle getRec() {
		return new Rectangle(x + 40,y + 30, width - 80, height - 60);
	}
	
}

//八峰庭
class Rest_12 extends Rest_r{
	Rest_12(){
		this.x = 1400;
		this.direction = -1;
		this.width = 150;
		this.height = 150;
		this.speed = 3;
		this.type = 3;
		this.count = 3;
		this.img = GameImage.rest12;
	}
	
	public Rectangle getRec() {
		return new Rectangle(x + 40,y + 30, width - 80, height - 60);
	}
	
}

//super mode 

class Rest_13 extends Rest_l{
	Rest_13(){
		this.x = -300;
		this.width = 250;
		this.height = 250;
		this.speed = 20;
		this.type = 3;
		this.count = 5;
		this.img = GameImage.rest13;
	}
	//too big
	public Rectangle getRec() {
		return new Rectangle(x + 40,y + 30, width - 80, height - 60);
	}
	
}

class Rest_14 extends Rest_l{
	Rest_14(){
		this.x = -300;
		this.width = 250;
		this.height = 250;
		this.speed = 20;
		this.type = 3;
		this.count = 5;
		this.img = GameImage.rest14;
	}
	//too big
	public Rectangle getRec() {
		return new Rectangle(x + 40,y + 30, width - 80, height - 60);
	}
	
}

class Rest_15 extends Rest_r{
	Rest_15(){
		this.x = 1400;
		this.direction = -1;
		this.width = 250;
		this.height = 250;
		this.count = 5;
		this.speed = 20;
		this.type = 3;
		this.img = GameImage.rest15;
	}
	
	public Rectangle getRec() {
		return new Rectangle(x + 40,y + 30, width - 80, height - 60);
	}
	
}

class Rest_16 extends Rest_r{
	Rest_16(){
		this.x = 1400;
		this.direction = -1;
		this.width = 250;
		this.height = 250;
		this.count = 5;
		this.speed = 20;
		this.type = 3;
		this.img = GameImage.rest16;
	}
	
	public Rectangle getRec() {
		return new Rectangle(x + 40,y + 30, width - 80, height - 60);
	}
	
}























