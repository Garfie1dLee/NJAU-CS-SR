/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
  ******************************************************************************
  ** This notice applies to any and all portions of this file
  * that are not between comment pairs USER CODE BEGIN and
  * USER CODE END. Other portions of this file, whether 
  * inserted by the user or by software development tools
  * are owned by their respective copyright owners.
  *
  * COPYRIGHT(c) 2020 STMicroelectronics
  *
  * Redistribution and use in source and binary forms, with or without modification,
  * are permitted provided that the following conditions are met:
  *   1. Redistributions of source code must retain the above copyright notice,
  *      this list of conditions and the following disclaimer.
  *   2. Redistributions in binary form must reproduce the above copyright notice,
  *      this list of conditions and the following disclaimer in the documentation
  *      and/or other materials provided with the distribution.
  *   3. Neither the name of STMicroelectronics nor the names of its contributors
  *      may be used to endorse or promote products derived from this software
  *      without specific prior written permission.
  *
  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
  * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
  * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
  * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
  * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
  * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
  * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
  * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  *
  ******************************************************************************
  */
/* USER CODE END Header */

/* Includes ------------------------------------------------------------------*/
#include "main.h"
#include "gpio.h"
/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#include "sys.h"
#include "lcd.h"
#include "spi.h"
#include "GUI.h"

/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */

/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/
extern SPI_HandleTypeDef hspi1;

/* USER CODE BEGIN PV */

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
static void MX_SPI1_Init(void);
void HAL_GPIO_EXTI_Callback(uint16_t GPIO_Pin);
void showtable();
void cleartable();
int judge();
void show(int x, int y);
//-------------added-----------------
int trans_x(int x,int y);
int trans_y(int x,int y);
void confirm_wuzi();
void up_wuzi();
void down_wuzi();
void left_wuzi();
void right_wuzi();
void showtable_wuzi();
void resetnow();
void confirm_liuzi();
void up_liuzi();
void down_liuzi();
void left_liuzi();
void right_liuzi();

int trans_x_2(int x,int y );
int trans_y_2(int x,int y);
void cleartable_liuzi();
int judge_liuzi();
void showtable_liuzi();
//
void a5_showtable();
void a5_cleartable();
int a5_judge();
void a5_confirm();
void a5_up();
void a5_left();
void a5_right();
void a5_down();

int f_a5[20][20] = {0};
int k = 16;  //ju li
//
void a21_printcard(int x1,int y1,int value);
void a21_printnumber(int x1,int y1,int value1,int value2);
void a21_printnumber1(int x1,int y1,int x);
void a21_calnumberandtake(int t1,int t2);
void a21_calnumberandtakeplayer(int t1);
void a21_printvalue();
void a21_clearplayer(int value);
void a21_printplayer(int value);
void a21_showtable();
void a21_right();

int k3;
int k4;
int count=0;
int f_21[52]={0};
int used[52]={0};
int dealer=0;
int player=0;
int suiji=12;

int playerAflag=0;
int dealerAflag=0;
int playerAusedflag=0;

int times=0;



//
//-------------added-----------------
/* USER CODE BEGIN PFP */
int startx = 120, starty = 168;
int cntb = 0;
int cntw = 0;
int f[8][8] = {0};   // black is 1   white is 2  ,modified
int flag = 1;//black is 1, white is 0
int k_wuzi = 16;
int game=0;
int k_liuzi=32;
unsigned char s[] = "0123456789";
//-------------added-----------------
int ifEnd =0;  //if game end

int select[2];
int selected=0;
int ifEnd_2=0;
//-------------added-----------------
/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */

/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */

  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */

  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_SPI1_Init();
  /* USER CODE BEGIN 2 */

  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
	LCD_Init();
	//Show_Str(0,16,BLACK,0XFFFF,(u8 *)"666666",16,1);
	
	//Show_Str(90,32,BLACK,0XFFFF,(u8 *)"Designed By GY",16,1);
	
	//select  which  game  
	Show_Str(32,70+32,BLACK,0XFFFF,(u8 *)"select which game",16,1);
	Show_Str(32,70+32*2,BLACK,0XFFFF,(u8 *)"press  UP to play game 1",16,1);
	Show_Str(32,70+32*3,BLACK,0XFFFF,(u8 *)"press DOWN to play game 2",16,1);
	Show_Str(32,70+32*4,BLACK,0XFFFF,(u8 *)"press LEFT to play game 3",16,1);
	Show_Str(32,70+32*5,BLACK,0XFFFF,(u8 *)"press RIGHT to play game 4",16,1);
	
	
	
	//
	

  while (1)
  {
    /* USER CODE END WHILE */
		times++;
		delay_ms(100);
    /* USER CODE BEGIN 3 */
  }
  /* USER CODE END 3 */
}

/**
  * @brief System Clock Configuration
  * @retval None
  */
void HAL_GPIO_EXTI_Callback(uint16_t GPIO_Pin)
{
	/***CONFIRM***/
	if(GPIO_Pin == GPIO_PIN_0)
	{
		if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_0)){
					if(game==1)
					{
						confirm_wuzi();
					}
					else if(game==2){
						confirm_liuzi();
					}
					else if(game==3){
						a5_confirm();
			
					}
					else if(game==4){
						
			
					}
		
		}

	}
	/***UP***/
	if(GPIO_Pin == GPIO_PIN_4)
	{
		if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_4)){
			if(game==0){
					game=1;
					Show_Str(32,32,BLACK,0XFFFF,(u8 *)"ºÚ°×Æå",16,1);
					Show_Str(32,70+32,WHITE,0XFFFF,(u8 *)"select which game",16,1);
					Show_Str(32,70+32*2,WHITE,0XFFFF,(u8 *)"press  UP to play game 1",16,1);
					Show_Str(32,70+32*3,WHITE,0XFFFF,(u8 *)"press DOWN to play game 2",16,1);
					Show_Str(32,70+32*4,WHITE,0XFFFF,(u8 *)"press LEFT to play game 3",16,1);
					Show_Str(32,70+32*5,WHITE,0XFFFF,(u8 *)"press RIGHT to play game 4",16,1);
					
					showtable_wuzi();

					Show_Str(70,70,BLACK,0XFFFF,(u8 *)"Black go",16,1);
					Show_Str(70,260,BLACK,0XFFFF,(u8 *)"ºÚÆå : °×Æå",16,1);
	
			}
			else if(game==1){
				up_wuzi();
			
			}
			else if(game==2){
				up_liuzi();
			
			}
			else if(game==3){
				a5_up();
			
			}
			else if(game==4){
				suiji=times;
			
			}

		
		}
		
		
	}
	/***LEFT***/
	if(GPIO_Pin == GPIO_PIN_5)
	{
		if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_5)){
			if(game==0){
					game=3;
					Show_Str(32,32,BLACK,0XFFFF,(u8 *)"GOBANG",16,1);
	        Show_Str(90,32,BLACK,0XFFFF,(u8 *)"BLACK GO",16,1);
					Show_Str(32,70+32,WHITE,0XFFFF,(u8 *)"select which game",16,1);
					Show_Str(32,70+32*2,WHITE,0XFFFF,(u8 *)"press  UP to play game 1",16,1);
					Show_Str(32,70+32*3,WHITE,0XFFFF,(u8 *)"press DOWN to play game 2",16,1);
					Show_Str(32,70+32*4,WHITE,0XFFFF,(u8 *)"press LEFT to play game 3",16,1);
					Show_Str(32,70+32*5,WHITE,0XFFFF,(u8 *)"press RIGHT to play game 4",16,1);
					
					a5_showtable();//mmmm

	
			}
			else if(game==1){
				left_wuzi();
			
			}
			else if(game==2){
				left_liuzi();
			
			}
			else if(game==3){
				a5_left();
			
			}
			else if(game==4){
				a21_calnumberandtakeplayer(player);
			
			}
		
		}
		

	}
	/***DOWN***/
	if(GPIO_Pin == GPIO_PIN_6)
	{
		
		if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_6)){
			if(game==0){
					game=2;
					Show_Str(32,32,BLACK,0XFFFF,(u8 *)"ºÚ°×Æå",16,1);
					Show_Str(32,70+32,WHITE,0XFFFF,(u8 *)"select which game",16,1);
					Show_Str(32,70+32*2,WHITE,0XFFFF,(u8 *)"press  UP to play game 1",16,1);
					Show_Str(32,70+32*3,WHITE,0XFFFF,(u8 *)"press DOWN to play game 2",16,1);
					Show_Str(32,70+32*4,WHITE,0XFFFF,(u8 *)"press LEFT to play game 3",16,1);
					Show_Str(32,70+32*5,WHITE,0XFFFF,(u8 *)"press RIGHT to play game 4",16,1);
					
					showtable_liuzi();//

					Show_Str(70,70,BLACK,0XFFFF,(u8 *)"Black go",16,1);
					Show_Str(70,260,BLACK,0XFFFF,(u8 *)"ºÚÆå : °×Æå",16,1);
	
			}
			else if(game==1){
				down_wuzi();
			
			}
			else if(game==2){
				down_liuzi();
			
			}
			else if(game==3){
				a5_down();
			
			}
			else if(game==4){
				
			
			}
	
		}
	
	}
	/***RIGHT***/
	if(GPIO_Pin == GPIO_PIN_8)
	{
		
		if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_8)){
			if(game==0){
					game=4;
					Show_Str(32,70+32,WHITE,0XFFFF,(u8 *)"select which game",16,1);
					Show_Str(32,70+32*2,WHITE,0XFFFF,(u8 *)"press  UP to play game 1",16,1);
					Show_Str(32,70+32*3,WHITE,0XFFFF,(u8 *)"press DOWN to play game 2",16,1);
					Show_Str(32,70+32*4,WHITE,0XFFFF,(u8 *)"press LEFT to play game 3",16,1);
					Show_Str(32,70+32*5,WHITE,0XFFFF,(u8 *)"press RIGHT to play game 4",16,1);
				
				Show_Str(32,32,BLACK,0XFFFF,(u8 *)"BLACK JACK",16,1);
	      Show_Str(150,32,BLACK,0XFFFF,(u8 *)"PLAYER GO",16,1);
					suiji=times*times+2*times;
					a21_showtable();//mmmm

					
	
			}
			else if(game==1){
				right_wuzi();
			
			}
			else if(game==2){
				right_liuzi();
			
			}
			else if(game==3){
				a5_right();
			
			}
			else if(game==4){
				 a21_right();
			
			}
		
		}
	

	}
	if(GPIO_Pin == GPIO_PIN_2)
	{
		
		if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_2)){
			if(game==0){
					
	
			}
			else if(game==1){
			  resetnow();
			
			}
			else if(game==2){
				resetnow();
			
			}
			else if(game==3){
				resetnow();
			
			}
			else if(game==4){
				resetnow();
			
			}
	
		}
	
	}
}
void resetnow()
{
	LCD_Clear(WHITE);
	game=0;
	Show_Str(32,70+32,BLACK,0XFFFF,(u8 *)"select which game",16,1);
	Show_Str(32,70+32*2,BLACK,0XFFFF,(u8 *)"press  UP to play game 1",16,1);
	Show_Str(32,70+32*3,BLACK,0XFFFF,(u8 *)"press DOWN to play game 2",16,1);
	Show_Str(32,70+32*4,BLACK,0XFFFF,(u8 *)"press LEFT to play game 3",16,1);
	Show_Str(32,70+32*5,BLACK,0XFFFF,(u8 *)"press RIGHT to play game 4",16,1);
	k3=0;
  k4=0;
  count=0;
	for(int i=0;i<52;i++)
  {f_21[i]=0;
  used[i]=0;}
  dealer=0;
  player=0;

  playerAflag=0;
  dealerAflag=0;
  playerAusedflag=0;
  startx = 120, starty = 168;
  cntb = 0;
  cntw = 0;
	for(int i=0;i<8;i++)
	for(int j=0;j<8;j++)
  f[i][j] = 0;   // black is 1   white is 2  ,modified
  flag = 1;//black is 1, white is 0
  k_wuzi = 16;

  k_liuzi=32;
//-------------added-----------------
  ifEnd =0;  //if game end

  select[0]=0;
	select[1]=0;
  selected=0;
  ifEnd_2=0;
}
void cleartable_wuzi()
{
	gui_circle(startx, starty, WHITE, 6, 0);

}
///---------------- added ---------------------
int trans_x(int x,int y){
	
	return (x-56)/16 ;
}
int trans_y(int x,int y){
	return (y-104)/16 ;     //56 104  
}
///---------------- added ---------------------

int judge_wuzi()
{


int coutb=1,coutw=1,i=0,j=0;
int x=trans_x(startx,starty),y=trans_y(startx,starty);
int now = flag==1 ? 2 : 1;  //to check black or white
//horizon
for(i=x+1;i<8;i++){
	if(f[i][y]!=now)
		break;
	else{
	  now==1 ? coutb++ : coutw++ ; 
	}
}

for(i=x-1;i>=0;i--){
	if(f[i][y]!=now)
		break;
	else{
	  now==1 ? coutb++ : coutw++ ; 
	}
}

if(now==1&&coutb>=4){
	
	  Show_Str(70,240,BLACK,0XFFFF,(u8 *)"BLACK Wins!",16,1);
	  ifEnd=1;
}
else if(now==2&&coutw>=4){
Show_Str(70,240,BLACK,0XFFFF,(u8 *)"WHITE Wins!",16,1);
	ifEnd=1;
}

//vertical
if(ifEnd==0){
	for(i=y+1;i<8;i++){
	if(f[x][i]!=now)
		break;
	else{
	  now==1 ? coutb++ : coutw++ ; 
	}
}

for(i=y-1;i>=0;i--){
	if(f[x][i]!=now)
		break;
	else{
	  now==1 ? coutb++ : coutw++ ; 
	}
}

if(now==1&&coutb>=4){
	
	  Show_Str(70,240,BLACK,0XFFFF,(u8 *)"BLACK Wins!",16,1);
	ifEnd=1;
}
else if(now==2&&coutw>=4){
Show_Str(70,240,BLACK,0XFFFF,(u8 *)"WHITE Wins!",16,1);
	ifEnd=1;
}

}
//left-top and right-bottom 
if(ifEnd==0){
	for(i=x+1,j=y+1;i<=7&&j<=7;i++,j++){
		if(f[i][j]!=now)
		 break;
	  else{
	   now==1 ? coutb++ : coutw++ ; 
	}
}
for(i=x-1,j=y-1;i>=0&&j>=0;i--,j--){
		if(f[i][j]!=now)
		 break;
	  else{
	   now==1 ? coutb++ : coutw++ ; 
	}
}
if(now==1&&coutb>=4){
	
	  Show_Str(70,240,BLACK,0XFFFF,(u8 *)"BLACK Wins!",16,1);
	ifEnd=1;
}
else if(now==2&&coutw>=4){
Show_Str(70,240,BLACK,0XFFFF,(u8 *)"WHITE Wins!",16,1);
	ifEnd=1;
}

}
//left-bottom and right-top 
if(ifEnd==0){
	for(i=x-1,j=y+1;i>=0&&j<=7;i--,j++){
		if(f[i][j]!=now)
		 break;
	  else{
	   now==1 ? coutb++ : coutw++ ; 
	}
}
for(i=x+1,j=y-1;i<=7&&j>=0;i++,j--){
		if(f[i][j]!=now)
		 break;
	  else{
	   now==1 ? coutb++ : coutw++ ; 
	}
}
if(now==1&&coutb>=4){
	
	  Show_Str(70,240,BLACK,0XFFFF,(u8 *)"BLACK Wins!",16,1);
	ifEnd=1;
}
else if(now==2&&coutw>=4){
Show_Str(70,240,BLACK,0XFFFF,(u8 *)"WHITE Wins!",16,1);
	ifEnd=1;
}


}

    return 1;

}

void show(int x, int y)
{
	if (x == 0)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"0",16,1);
	}
	else if (x == 1)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"1",16,1);
	}
	else if (x == 2)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"2",16,1);
	}
	else if (x == 3)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"3",16,1);
	}
	else if (x == 4)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"4",16,1);
	}
	else if (x == 5)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"5",16,1);
	}
	else if (x == 6)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"6",16,1);
	}
	else if (x == 7)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"7",16,1);
	}
	else if (x == 8)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"8",16,1);
	}
	else if (x == 9)
	{
		Show_Str(86 + y,280,BLACK,0XFFFF,(u8 *)"9",16,1);
	}
}

void showtable_wuzi()
{

	cntb = 0;
	cntw = 0;

	int i = 0, j = 0;
	for (i = 0; i < 8; i++)
	{
		for (j = 0; j < 8; j++)
		{
			f[i][j] = 0;
		}
	}
	LCD_DrawLine(47, 95, 47, 225);
	LCD_DrawLine(47, 95, 177, 95);
	LCD_DrawLine(177, 95, 177, 225);
	LCD_DrawLine(47, 225, 177, 225);
	for (i = 96; i < 240; i += 16)
	{
		LCD_DrawLine(48, i, 176, i);
	}
	for (i = 48; i < 192; i += 16)
	{
		LCD_DrawLine(i, 96, i, 224);
	}

	gui_circle(120, 168, RED, 6, 0);  //modified
	flag = 1;     //the  next is black
}

//
void confirm_wuzi(){
		if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_0)&&ifEnd==0 && game!=0)//m
		{
		
			if (flag == 1)
			{//black

				int x_will = trans_x(startx,starty),y_will = trans_y(startx,starty);
				
				if(f[x_will][y_will]==0){
					if(y_will<7){ //need to check
					  if(f[x_will][y_will+1]!=0){  // can
						  gui_circle(startx, starty, BLACK, 5, 1);
						  f[x_will][y_will]=1;
					  	flag = 0;
							cntb++;
							Show_Str(70,70,WHITE,0XFFFF,(u8 *)"Black go",16,1);
							Show_Str(70,70,WHITE,0XFFFF,(u8 *)"White go",16,1);
							Show_Str(70,70,BLACK,0XFFFF,(u8 *)"White go",16,1);
							judge_wuzi();
					  }
						else{
						  Show_Str(70,240,BLACK,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
							Show_Str(70,240,WHITE,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
						}
				  }
					else{// do not need to check
							gui_circle(startx, starty, BLACK, 5, 1);
						  f[x_will][y_will]=1;
					  	flag = 0;
						  cntb++;
						  Show_Str(70,70,WHITE,0XFFFF,(u8 *)"Black go",16,1);
						  Show_Str(70,70,WHITE,0XFFFF,(u8 *)"White go",16,1);
						  Show_Str(70,70,BLACK,0XFFFF,(u8 *)"White go",16,1);
							judge_wuzi();
					}
				}
				else {
				  Show_Str(70,240,BLACK,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
					Show_Str(70,240,WHITE,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
				  
				}

			}
			else
			{//white

				int x_will = trans_x(startx,starty),y_will = trans_y(startx,starty);
				
				if(f[x_will][y_will]==0){
					if(y_will<7){ //need to check
					  if(f[x_will][y_will+1]!=0){  // can
						  gui_circle(startx, starty, BLACK, 5, 0);
						  f[x_will][y_will]=2;
					  	flag = 1;
							cntw++;
							Show_Str(70,70,WHITE,0XFFFF,(u8 *)"Black go",16,1);
							Show_Str(70,70,WHITE,0XFFFF,(u8 *)"White go",16,1);
							Show_Str(70,70,BLACK,0XFFFF,(u8 *)"Black go",16,1);
							judge_wuzi();
					  }
						else{
						  Show_Str(70,240,BLACK,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
							Show_Str(70,240,WHITE,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
						}
				  }
					else{// do not need to check
							gui_circle(startx, starty, BLACK, 5, 0);
						  f[x_will][y_will]=2;
					  	flag = 1;
						  cntw++;
							Show_Str(70,70,WHITE,0XFFFF,(u8 *)"Black go",16,1);
							Show_Str(70,70,WHITE,0XFFFF,(u8 *)"White go",16,1);
							Show_Str(70,70,BLACK,0XFFFF,(u8 *)"Black go",16,1);
							judge_wuzi();
					}
				}
				else {
				  Show_Str(70,240,BLACK,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
				  Show_Str(70,240,WHITE,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
				}
				
				
			}
		}

}


void up_wuzi(){

					if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_4)&& trans_y(startx,starty)>=1 &&ifEnd==0)//m
					{
						cleartable_wuzi();
						starty = starty - k_wuzi;
						gui_circle(startx, starty, RED, 6, 0);
					}	

}


void down_wuzi(){

					if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_6)&& trans_y(startx,starty)<=6 &&ifEnd==0)//m
					{
						cleartable_wuzi();
						starty = starty + k_wuzi;
						gui_circle(startx, starty, RED, 6, 0);
					}	

}

void left_wuzi(){

					if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_5)&& trans_x(startx,starty)>=1 &&ifEnd==0)//m
					{
						cleartable_wuzi();
						startx = startx - k_wuzi;
						gui_circle(startx, starty, RED, 6, 0);
					}

}

void right_wuzi(){

					if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_8)&& trans_x(startx,starty)<=6 &&ifEnd==0)//m
					{
						cleartable_wuzi();
						startx = startx + k_wuzi;
						gui_circle(startx, starty, RED, 6, 0);
					}	



}


//

//
void confirm_liuzi(){
	
			if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_0) && ifEnd_2==0 )//m
		{
			if(selected==1){
				  int x= trans_x_2(startx,starty),y=trans_y_2(startx,starty);
					if (flag == 1)
					{//black

						if(f[x][y] == 0 && ( abs(x-select[0])+abs(y-select[1]) )==1 ){

							gui_circle(select[1]*32+64, select[0]*32+112, WHITE,  10, 1);//delete the before
							gui_circle(startx, starty, BLACK,  10, flag);//draw the next
							flag=0;
							selected=0;
							
							f[x][y]=1;
							f[select[0]][select[1]]=0;
							judge_liuzi();
							Show_Str(70,70,WHITE,0XFFFF,(u8 *)"Black go",16,1);
							Show_Str(70,70,WHITE,0XFFFF,(u8 *)"White go",16,1);
							Show_Str(70,70,BLACK,0XFFFF,(u8 *)"White go",16,1);
							
							
						}
						else if(f[x][y] == 1){//maybe wanna re-select the black
							select[0]=trans_x_2(startx,starty);
							select[1]=trans_y_2(startx,starty);	
							
						}
						else{
							Show_Str(70,240,BLACK,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
							Show_Str(70,240,WHITE,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
						}
					}
					else
					{//white

							if(f[x][y] == 0 && ( abs(x-select[0])+abs(y-select[1]) )==1 ){
								gui_circle(select[1]*32+64, select[0]*32+112, WHITE,  10, 0);//delete the before
								gui_circle(startx, starty, BLACK,  10, flag);//draw the next
								flag=1;
								selected=0;
								
								f[x][y]=2;
							  f[select[0]][select[1]]=0;
								judge_liuzi();
								Show_Str(70,70,WHITE,0XFFFF,(u8 *)"Black go",16,1);
								Show_Str(70,70,WHITE,0XFFFF,(u8 *)"White go",16,1);
								Show_Str(70,70,BLACK,0XFFFF,(u8 *)"Black go",16,1);
							
							}
							else if(f[x][y] == 2){//maybe wanna re-select the white
							select[0]=trans_x_2(startx,starty);
							select[1]=trans_y_2(startx,starty);	
							
							}
							else{
								Show_Str(70,240,BLACK,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
								Show_Str(70,240,WHITE,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
							}
					}
					
			
			}
			else{
				if( f[trans_x_2(startx,starty)][trans_y_2(startx,starty)] == 1 && flag==1 ){
					selected=1;
					select[0]=trans_x_2(startx,starty);
					select[1]=trans_y_2(startx,starty);
				}
				else if( f[trans_x_2(startx,starty)][trans_y_2(startx,starty)] == 2 && flag==0 ){
				
					selected=1;
					select[0]=trans_x_2(startx,starty);
					select[1]=trans_y_2(startx,starty);	
				}
			
			}
		}
	

}
void up_liuzi(){

					if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_4) && starty>=112+1*32 && ifEnd_2==0)//m starty>=112+1*32  (trans_x_2(startx,starty))>=1
					{
						cleartable_liuzi();
						starty = starty - k_liuzi;
						gui_circle(startx, starty, RED, 12, 0);//m
					}


}
void down_liuzi(){

						
				if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_6)&& (trans_x_2(startx,starty))<=2 && ifEnd_2==0)//m
			{
				cleartable_liuzi();
				starty = starty + k_liuzi;
				gui_circle(startx, starty, RED,  12, 0);//m
			}	

}
void left_liuzi(){
	

				if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_5)&& (trans_y_2(startx,starty))>=1 && ifEnd_2==0)//m
			{
				cleartable_liuzi();
				startx = startx - k_liuzi;
				gui_circle(startx, starty, RED,  12, 0);//m
			}


}
void right_liuzi(){

				if(0 == HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_8)&& (trans_y_2(startx,starty))<=2 && ifEnd_2==0)//m
			{
				cleartable_liuzi();
				startx = startx + k_liuzi;
				gui_circle(startx, starty, RED,  12, 0);//m
			}
		

	


}
void showtable_liuzi()
{
	startx=128;starty=144;
	cntb = 6;
	cntw = 6;
	int i = 0, j = 0;
	for (i = 0; i < 4; i++)//m
	{
		for (j = 0; j < 4; j++)//m
		{
			f[i][j] = 0;
		}
	}  
	//boarder
	LCD_DrawLine(47, 95, 47, 225);  //down
	LCD_DrawLine(47, 95, 177, 95);  //right
	LCD_DrawLine(177, 95, 177, 225);//
	LCD_DrawLine(47, 225, 177, 225);
	//content
	for (i = 96; i < 240; i += 32)//m
	{
		LCD_DrawLine(48, i, 176, i);
	}
	for (i = 48; i < 192; i += 32)//m
	{
		LCD_DrawLine(i, 96, i, 224);
	}

	//----------gy-----------------
	for(i=0;i<4;i++){
		gui_circle(64+i*32, 112, BLACK, 10, 0);
		f[0][i]=2;
	}
	gui_circle(64, 112+32, BLACK, 10, 0);
	gui_circle(64+3*32, 112+32, BLACK, 10, 0);
	f[1][0]=2;f[1][3]=2;
	gui_circle(64, 112+32+32, BLACK, 10, 1);
	gui_circle(64+3*32, 112+32+32, BLACK, 10, 1);
	f[2][0]=1;f[2][3]=1;
	for(i=0;i<4;i++){
		gui_circle(64+i*32, 208, BLACK, 10, 1);
		f[3][i]=1;
	}
	gui_circle(64+2*32, 112+32, RED, 12, 0);
	flag = 1;
	//----------gy-----------------
}
int trans_x_2(int x,int y){
//	64 112 -> 0 0
//  64+32*2  112+32 -> 1 2
	return (y-112)/32; 

}

int trans_y_2(int x,int y){
  return (x-64)/32; 	
}
void cleartable_liuzi()
{
	gui_circle(startx, starty, WHITE, 12, 0);//m

}
int judge_liuzi()
{

	int i = 0, j = 0;

	int isin = 0;
	int eaten = 0;
	int x=trans_x_2(startx,starty);//position x
	int y=trans_y_2(startx,starty);//position y
	
	int want_color = flag==1 ? 2 : 1;
	int enemy = want_color ==1 ? 2 : 1;

	
	//in the middle ,look the vertical
	if(x==1 && f[x-1][y]==want_color && f[x+1][y]==enemy && f[x+2][y]!=enemy){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle(y*32+64, (x+1)*32+112, WHITE,  10, 1);
			cntb--;
			f[x+1][y]=0;

		}
		else
		{
			gui_circle(y*32+64, (x+1)*32+112, WHITE,  10, 0);
			cntw--;
			f[x+1][y]=0;
		}
		
	}
	if(eaten==0)//not eat any chess,then continue to judge 
	if(x==2 && f[x+1][y]==want_color && f[x-1][y]==enemy && f[x-2][y]!=enemy){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle(y*32+64, (x-1)*32+112, WHITE,  10, 1);
			cntb--;
			f[x-1][y]=0;
		}
		else
		{
			gui_circle(y*32+64, (x-1)*32+112, WHITE,  10, 0);
			cntw--;
			f[x-1][y]=0;
		}
		
	}
	//in the middle ,look the horizon
	if(eaten==0)//not eat any chess,then continue to judge 
	if(y==1 && f[x][y-1]==want_color && f[x][y+1]==enemy && f[x][y+2]!=enemy){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y+1)*32+64, x*32+112, WHITE,  10, 1);
			cntb--;
			f[x][y+1]=0;
		}
		else
		{
			gui_circle((y+1)*32+64, x*32+112, WHITE,  10, 0);
			cntw--;
			f[x][y+1]=0;
		}
	}
	if(eaten==0)//not eat any chess,then continue to judge 
	if(y==2 && f[x][y+1]==want_color && f[x][y-1]==enemy && f[x][y-2]!=enemy){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y-1)*32+64, x*32+112, WHITE,  10, 1);
			cntb--;
			f[x][y-1]=0;
		}
		else
		{
			gui_circle((y-1)*32+64, x*32+112, WHITE,  10, 0);
			cntw--;
			f[x][y-1]=0;
		}
	}
	//in the eage , look the vertical
	if(eaten==0)//not eat any chess,then continue to judge 
	if(x==0 && f[x+1][y]==want_color && f[x+2][y]==enemy && f[x+3][y]!=enemy){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y)*32+64, (x+2)*32+112, WHITE,  10, 1);
			cntb--;
			f[x+2][y]=0;
		}
		else
		{
			gui_circle((y)*32+64, (x+2)*32+112, WHITE,  10, 0);
			cntw--;
			f[x+2][y]=0;
		}
	}
	if(eaten==0)//not eat any chess,then continue to judge 
	if(x==1 && f[x+1][y]==want_color && f[x+2][y]==enemy ){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y)*32+64, (x+2)*32+112, WHITE,  10, 1);
			cntb--;
			f[x+2][y]=0;
		}
		else
		{
			gui_circle((y)*32+64, (x+2)*32+112, WHITE,  10, 0);
			cntw--;
			f[x+2][y]=0;
		}
	}
	if(eaten==0)//not eat any chess,then continue to judge 
	if(x==2 && f[x-1][y]==want_color && f[x-2][y]==enemy ){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y)*32+64, (x-2)*32+112, WHITE,  10, 1);
			cntb--;
			f[x-2][y]=0;
		}
		else
		{
			gui_circle((y)*32+64, (x-2)*32+112, WHITE,  10, 0);
			cntw--;
			f[x-2][y]=0;
		}
	}
	if(eaten==0)//not eat any chess,then continue to judge 
	if(x==3 && f[x-1][y]==want_color && f[x-2][y]==enemy && f[x-3][y]!=enemy){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y)*32+64, (x-2)*32+112, WHITE,  10, 1);
			cntb--;
			f[x-2][y]=0;
		}
		else
		{
			gui_circle((y)*32+64, (x-2)*32+112, WHITE,  10, 0);
			cntw--;
			f[x-2][y]=0;
		}
	}
	//in the eage , look the horizon
	if(eaten==0)//not eat any chess,then continue to judge 
	if(y==0 && f[x][y+1]==want_color && f[x][y+2]==enemy && f[x][y+3]!=enemy){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y+2)*32+64, (x)*32+112, WHITE,  10, 1);
			cntb--;
			f[x][y+2]=0;
		}
		else
		{
			gui_circle((y+2)*32+64, (x)*32+112, WHITE,  10, 0);
			cntw--;
			f[x][y+2]=0;
		}
	}
	if(eaten==0)//not eat any chess,then continue to judge 
	if(y==1 && f[x][y+1]==want_color && f[x][y+2]==enemy ){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y+2)*32+64, (x)*32+112, WHITE,  10, 1);
			cntb--;
			f[x][y+2]=0;
		}
		else
		{
			gui_circle((y+2)*32+64, (x)*32+112, WHITE,  10, 0);
			cntw--;
			f[x][y+2]=0;
		}
	}
	if(eaten==0)//not eat any chess,then continue to judge 
	if(y==2 && f[x][y-1]==want_color && f[x][y-2]==enemy ){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y-2)*32+64, (x)*32+112, WHITE,  10, 1);
			cntb--;
			f[x][y-2]=0;
		}
		else
		{
			gui_circle((y-2)*32+64, (x)*32+112, WHITE,  10, 0);
			cntw--;
			f[x][y-2]=0;
		}
	}
	if(eaten==0)//not eat any chess,then continue to judge 
	if(y==3 && f[x][y-1]==want_color && f[x][y-2]==enemy && f[x][y-3]!=enemy){
		eaten=1;
		if(enemy==1)   //2  0
		{
			gui_circle((y-2)*32+64, (x)*32+112, WHITE,  10, 1);
			cntb--;
			f[x][y-2]=0;
		}
		else
		{
			gui_circle((y-2)*32+64, (x)*32+112, WHITE,  10, 0);
			cntw--;
			f[x][y-2]=0;
		}
	}
	
  if(cntw<=1){
		Show_Str(70,240,BLACK,0XFFFF,(u8 *)"BLACK Wins!",16,1);
		ifEnd_2=1;
		
	}
	else if(cntb<=1){
		Show_Str(70,240,BLACK,0XFFFF,(u8 *)"WHITE Wins!",16,1);
		ifEnd_2=1;
	}
	
	return isin;
}

//


void a5_confirm(){
	
if (flag == 1)
			{//black
				if (a5_judge() == 1 )
				{
					flag = 0;
					Show_Str(90,32,WHITE,0XFFFF,(u8 *)"BLACK GO",16,1);
					Show_Str(90,32,WHITE,0XFFFF,(u8 *)"WHITE GO",16,1);
					Show_Str(90,32,BLACK,0XFFFF,(u8 *)"WHITE GO",16,1);
				}
				else if (a5_judge() == 0)
				{
					
					Show_Str(70,240,BLACK,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
					Show_Str(70,240,WHITE,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
				}
			}
			else
			{//white
				if (a5_judge() == 1 )
				{
					
					flag = 1;
					Show_Str(90,32,WHITE,0XFFFF,(u8 *)"BLACK GO",16,1);
					Show_Str(90,32,WHITE,0XFFFF,(u8 *)"WHITE GO",16,1);
					Show_Str(90,32,BLACK,0XFFFF,(u8 *)"BLACK GO",16,1);
				}
				else if (a5_judge() == 0)
				{
					
					Show_Str(70,240,BLACK,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
					Show_Str(70,240,WHITE,0XFFFF,(u8 *)"ILLEGAL STEP!",16,1);
				}
			}
		}

void a5_up()
{
	    a5_cleartable();
			starty = starty - k;
			gui_circle(startx, starty, RED, 6, 0);
}
void a5_left()
{
	    a5_cleartable();
			startx = startx - k;
			gui_circle(startx, starty, RED, 6, 0);
}
void a5_down()
{
	    a5_cleartable();
			starty = starty + k;
			gui_circle(startx, starty, RED, 6, 0);
}
void a5_right()
{
	    a5_cleartable();
			startx = startx + k;
			gui_circle(startx, starty, RED, 6, 0);
}

void a5_cleartable()
{
	gui_circle(startx, starty, WHITE, 6, 0);
	LCD_DrawPoint(startx+6,starty);
	LCD_DrawPoint(startx-6,starty);
	LCD_DrawPoint(startx,starty+6);
	LCD_DrawPoint(startx,starty-6);
}

int a5_judge()
{
	int ifflag=0;
	int winflag=0;
	if(f_a5[(startx-32)/16][(starty-32)/16]==0)
	{ifflag=1;
		if(flag==1)
		{
			gui_circle(startx, starty, BLACK, 5, 1);
			f_a5[(startx-32)/16][(starty-32)/16]=1;
			flag=0;
		}
		else
		{
			gui_circle(startx, starty, BLACK, 5, 0);
			f_a5[(startx-32)/16][(starty-32)/16]=2;
			flag=1;
		}
		for(int i=0;i<11;i++)
		for(int j=0;j<11;j++)
		if(f_a5[i][j]==0)
			continue;
		else if(
			(f_a5[i][j]==f_a5[i][j+1]&&f_a5[i][j]==f_a5[i][j+2]&&f_a5[i][j]==f_a5[i][j+3]&&f_a5[i][j]==f_a5[i][j+4])||
		(f_a5[i][j]==f_a5[i+1][j]&&f_a5[i][j]==f_a5[i+2][j]&&f_a5[i][j]==f_a5[i+3][j]&&f_a5[i][j]==f_a5[i+4][j])||
		(f_a5[i][j]==f_a5[i+1][j+1]&&f_a5[i][j]==f_a5[i+2][j+2]&&f_a5[i][j]==f_a5[i+3][j+3]&&f_a5[i][j]==f_a5[i+4][j+4])||
		(i>=4&&(f_a5[i][j]==f_a5[i-1][j+1]&&f_a5[i][j]==f_a5[i-2][j+2]&&f_a5[i][j]==f_a5[i-3][j+3]&&f_a5[i][j]==f_a5[i-4][j+4]))
			)
		{
		winflag=1;
		}
		if(winflag==1&&ifflag==1)
		{if(flag==0)
				Show_Str(70,240,BLACK,0XFFFF,(u8 *)"BLACK WIN!",16,1);
			else
				Show_Str(70,240,BLACK,0XFFFF,(u8 *)"WHITE WIN!",16,1);
		}
		
	}
	return ifflag;
}



void a5_showtable()
{
	startx = 112, starty = 160;
	int i = 0, j = 0;
	for (i = 0; i < 20; i++)
	{
		for (j = 0; j < 20; j++)
		{
			f_a5[i][j] = 0;
		}
	}
  LCD_DrawLine(31, 79, 193, 79);
	LCD_DrawLine(31, 241,193, 241);
	LCD_DrawLine(31, 79, 31, 241);
	LCD_DrawLine(193,79, 193,241);
	for (i = 80; i < 256; i += 16)
	{
		LCD_DrawLine(32, i, 192, i);
	}
	for (i = 32; i < 208; i += 16)
	{
		LCD_DrawLine(i, 80, i, 240);
	}
	gui_circle(112, 160, RED, 6, 0);
	flag = 1;
}

//

void a21_right()
{
	int k1=rand()%53;
	while(used[k1]==1)
	{k1=rand()%53;}
	used[k1]=1;
	int k2=rand()%53;
	while(used[k2]==1)
	{k2=rand()%53;}
	used[k2]=1;
  a21_printcard(45,110,k1);
	a21_printcard(45,130,k2);
	a21_calnumberandtake(k1,k2);
}


void a21_calnumberandtake(int t1,int t2)
{
	int value1;
	int value2;
	int k5;
	int k6;
	if(f_21[t1]==1)
		value1=11;
	else if(f_21[t1]>=10)
		value1=10;
	else
		value1=f_21[t1];
	if(f_21[t2]==1)
		value2=11;
	else if(f_21[t2]>=10)
		value2=10;
	else
		value2=f_21[t2];
	
	int value=value1+value2;
	if(value1==11&&value2==11)
		value=12;
	if(value>=17)
		dealer=value;
	if(value<17)
	{
		k5=rand()%53;
	while(used[k5]==1)
	{k5=rand()%53;}
	used[k5]=1;
	a21_printcard(125,110,k5);
	int value3=f_21[k5];
	if(value3>=10)value3=10;
	if(f_21[k5]==1)
	{if(value+11<=21)
			value=value+11;}
	else value=value+value3;
	if(value<17)
	{
		k6=rand()%53;
	while(used[k6]==1)
	{k6=rand()%53;}
	used[k6]=1;
	a21_printcard(125,130,k6);
	
	int value4=f_21[k6];
	if(value4>=10)value4=10;
	if(f_21[k6]==1)
	{if(value+11<=21)
			value=value+11;}
	else value=value+value4;
	}
}dealer=value;
	
  Show_Str(140,270,BLACK,0XFFFF,(u8 *)"SCORE",16,1);
	a21_printnumber1(180,270,dealer);
	if(dealer>21)
	{Show_Str(30,285,WHITE,0XFFFF,(u8 *)"CONTINUE?",16,1);
	Show_Str(30,285,BLACK,0XFFFF,(u8 *)"YOUWIN!",16,1);}
	else if(dealer>player)
	{
		Show_Str(30,285,WHITE,0XFFFF,(u8 *)"CONTINUE?",16,1);
	Show_Str(30,285,BLACK,0XFFFF,(u8 *)"YOULOSE!",16,1);}
	else if(dealer==player)
	{Show_Str(30,285,WHITE,0XFFFF,(u8 *)"CONTINUE?",16,1);
	Show_Str(30,285,BLACK,0XFFFF,(u8 *)"EVEN!",16,1);}
	else if(dealer<player)
		{Show_Str(30,285,WHITE,0XFFFF,(u8 *)"CONTINUE?",16,1);
	Show_Str(30,285,BLACK,0XFFFF,(u8 *)"YOUWIN!",16,1);}
}


void a21_calnumberandtakeplayer(int t1)
{
	int k9;
	int value=t1;
	k9=rand()%53;
	while(used[k9]==1)
	{k9=rand()%53;}
	used[k9]=1;
	if(count==0)
	{
	a21_printcard(125,210,k9);
		count=1;
	}
	else
	{
	a21_printcard(125,230,k9);
	}
	int value3=f_21[k9];
	if(value3>=10)value3=10;
	if(f_21[k9]==1)
	{if(value+11<=21)
			value=value+11;}
	else value=value+value3;
	if(value>21&&playerAflag==1&&playerAusedflag==0)
	{value=value-10;playerAusedflag=1;}
	a21_clearplayer(player);
	player=value;
	a21_printplayer(player);
	
	if(player>21)
	{Show_Str(30,285,WHITE,0XFFFF,(u8 *)"CONTINUE?",16,1);
	Show_Str(30,285,BLACK,0XFFFF,(u8 *)"YOULOSE!",16,1);}
	
		
}

void a21_printcard(int x1,int y1,int value)
{
	if(value/13==0)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"SPADE",16,1);
	else if(value/13==1)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"HEART",16,1);
	else if(value/13==2)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"CLUB",16,1);
	else if(value/13==3)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"DIAMOND",16,1);
	
	
	if(f_21[value]==1)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"A",16,1);
	else if(f_21[value]==2)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"2",16,1);
		else if(f_21[value]==3)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"3",16,1);
			else if(f_21[value]==4)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"4",16,1);
				else if(f_21[value]==5)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"5",16,1);
					else if(f_21[value]==6)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"6",16,1);
						else if(f_21[value]==7)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"7",16,1);
							else if(f_21[value]==8)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"8",16,1);
								else if(f_21[value]==9)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"9",16,1);
									else if(f_21[value]==10)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"10",16,1);
										else if(f_21[value]==11)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"J",16,1);
										else if(f_21[value]==12)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"Q",16,1);
										else if(f_21[value]==13)
		Show_Str(x1+60,y1,BLACK,0XFFFF,(u8 *)"K",16,1);
}	

void a21_clearplayer(int value)
{
	int x1=70;
	int y1=270;
	if(value==1)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"1",16,1);
	else if(value==2)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"2",16,1);
	else if(value==3)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"3",16,1);
	else if(value==4)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"4",16,1);
	else if(value==5)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"5",16,1);
	else if(value==6)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"6",16,1);
	else if(value==7)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"7",16,1);
	else if(value==8)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"8",16,1);
	else if(value==9)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"9",16,1);
	else if(value==10)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"10",16,1);
	else if(value==11)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"11",16,1);
	else if(value==12)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"12",16,1);
	else if(value==13)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"13",16,1);
	else if(value==14)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"14",16,1);
	else if(value==15)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"15",16,1);
	else if(value==16)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"16",16,1);
	else if(value==17)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"17",16,1);
	else if(value==18)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"18",16,1);
	else if(value==19)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"19",16,1);
	else if(value==20)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"20",16,1);
	else if(value==21)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"21",16,1);
	else if(value==22)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"22",16,1);
	else if(value==23)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"23",16,1);
	else if(value==24)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"24",16,1);
	else if(value==25)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"25",16,1);
	else if(value==26)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"26",16,1);
	else if(value==27)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"27",16,1);
	else if(value==28)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"28",16,1);
	else if(value==29)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"29",16,1);
	else if(value==30)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"30",16,1);
	else if(value==31)
		Show_Str(x1,y1,WHITE,0XFFFF,(u8 *)"31",16,1);
}

void a21_printplayer(int value)
{
	int x1=70;
	int y1=270;
	
	if(value==1)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"1",16,1);
	else if(value==2)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"2",16,1);
	else if(value==3)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"3",16,1);
	else if(value==4)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"4",16,1);
	else if(value==5)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"5",16,1);
	else if(value==6)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"6",16,1);
	else if(value==7)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"7",16,1);
	else if(value==8)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"8",16,1);
	else if(value==9)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"9",16,1);
	else if(value==10)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"10",16,1);
	else if(value==11)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"11",16,1);
	else if(value==12)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"12",16,1);
	else if(value==13)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"13",16,1);
	else if(value==14)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"14",16,1);
	else if(value==15)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"15",16,1);
	else if(value==16)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"16",16,1);
	else if(value==17)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"17",16,1);
	else if(value==18)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"18",16,1);
	else if(value==19)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"19",16,1);
	else if(value==20)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"20",16,1);
	else if(value==21)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"21",16,1);
	else if(value==22)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"22",16,1);
	else if(value==23)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"23",16,1);
	else if(value==24)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"24",16,1);
	else if(value==25)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"25",16,1);
	else if(value==26)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"26",16,1);
	else if(value==27)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"27",16,1);
	else if(value==28)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"28",16,1);
	else if(value==29)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"29",16,1);
	else if(value==30)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"30",16,1);
	else if(value==31)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"31",16,1);
}


void a21_printnumber(int x1,int y1,int x,int y)
{
	
	int value1=f_21[x];
	int value2=f_21[y];
	if(value1>=10)value1=10;
	if(value2>=10)value2=10;
	int mode=1;
	if(value1==1||value2==1)
		mode=2;
	int value;
	if(mode==1)
	{
		value=value1+value2;
	}
	if(mode==2)
	{
		if(value1==1&&value2==1)
			value=12;
		if(value1==1)
		{value=value2+11;playerAflag++;}
		if(value2==1)
		{value=value1+11;playerAflag++;}
	}
	player=value;
	if(value==1)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"1",16,1);
	else if(value==2)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"2",16,1);
	else if(value==3)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"3",16,1);
	else if(value==4)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"4",16,1);
	else if(value==5)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"5",16,1);
	else if(value==6)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"6",16,1);
	else if(value==7)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"7",16,1);
	else if(value==8)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"8",16,1);
	else if(value==9)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"9",16,1);
	else if(value==10)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"10",16,1);
	else if(value==11)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"11",16,1);
	else if(value==12)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"12",16,1);
	else if(value==13)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"13",16,1);
	else if(value==14)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"14",16,1);
	else if(value==15)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"15",16,1);
	else if(value==16)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"16",16,1);
	else if(value==17)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"17",16,1);
	else if(value==18)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"18",16,1);
	else if(value==19)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"19",16,1);
	else if(value==20)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"20",16,1);
	else if(value==21)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"21",16,1);
	else if(value==22)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"22",16,1);
	else if(value==23)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"23",16,1);
	else if(value==24)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"24",16,1);
	else if(value==25)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"25",16,1);
	else if(value==26)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"26",16,1);
	else if(value==27)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"27",16,1);
	else if(value==28)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"28",16,1);
	else if(value==29)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"29",16,1);
	else if(value==30)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"30",16,1);
	else if(value==31)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"31",16,1);
}

void a21_printnumber1(int x1,int y1,int x)
{
	
	
	int value=x;
	if(value==1)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"1",16,1);
	else if(value==2)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"2",16,1);
	else if(value==3)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"3",16,1);
	else if(value==4)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"4",16,1);
	else if(value==5)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"5",16,1);
	else if(value==6)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"6",16,1);
	else if(value==7)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"7",16,1);
	else if(value==8)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"8",16,1);
	else if(value==9)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"9",16,1);
	else if(value==10)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"10",16,1);
	else if(value==11)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"11",16,1);
	else if(value==12)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"12",16,1);
	else if(value==13)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"13",16,1);
	else if(value==14)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"14",16,1);
	else if(value==15)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"15",16,1);
	else if(value==16)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"16",16,1);
	else if(value==17)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"17",16,1);
	else if(value==18)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"18",16,1);
	else if(value==19)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"19",16,1);
	else if(value==20)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"20",16,1);
	else if(value==21)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"21",16,1);
	else if(value==22)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"22",16,1);
	else if(value==23)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"23",16,1);
	else if(value==24)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"24",16,1);
	else if(value==25)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"25",16,1);
	else if(value==26)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"26",16,1);
	else if(value==27)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"27",16,1);
	else if(value==28)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"28",16,1);
	else if(value==29)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"29",16,1);
	else if(value==30)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"30",16,1);
	else if(value==31)
		Show_Str(x1,y1,BLACK,0XFFFF,(u8 *)"31",16,1);
}


void a21_showtable()
{
	int i = 0, j = 0;
	for(i=0;i<4;i++)
	for(j=0;j<13;j++)
	{f_21[i*13+j]=j+1; used[i*13+j]=0;}

  LCD_DrawLine(30, 80, 210,80);
	LCD_DrawLine(30, 80, 30, 160);
	LCD_DrawLine(30, 160, 210, 160);
	LCD_DrawLine(210,80, 210,160);
	Show_Str(45,90,BLACK,0XFFFF,(u8 *)"DEALER!",16,1);
	srand(suiji);
  
	
	int newx=30;
	int newy=180;
	LCD_DrawLine(30, 180, 210,180);
	LCD_DrawLine(30, 180, 30, 260);
	LCD_DrawLine(30, 260, 210, 260);
	LCD_DrawLine(210,180, 210,260);
	
	
	k3=rand()%53;
	while(used[k3]==1)
	{k3=rand()%53;}
	used[k3]=1;
	k4=rand()%53;
	while(used[k4]==1)
	{k4=rand()%53;}
	used[k4]=1;
	Show_Str(45,190,BLACK,0XFFFF,(u8 *)"PLAYER!",16,1);
	a21_printcard(45,210,k3);
	a21_printcard(45,230,k4);
	Show_Str(30,270,BLACK,0XFFFF,(u8 *)"SCORE",16,1);
	a21_printnumber(70,270,k3,k4);
	
	Show_Str(30,285,BLACK,0XFFFF,(u8 *)"CONTINUE?",16,1);
	
}

//

void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};

  /**Initializes the CPU, AHB and APB busses clocks 
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_HSE;
  RCC_OscInitStruct.HSEState = RCC_HSE_ON;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_NONE;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }
  /**Initializes the CPU, AHB and APB busses clocks 
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1|RCC_CLOCKTYPE_PCLK2;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_HSE;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV1;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV1;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV1;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_0) != HAL_OK)
  {
    Error_Handler();
  }
}

/**
  * @brief SPI1 Initialization Function
  * @param None
  * @retval None
  */
static void MX_SPI1_Init(void)
{

  /* USER CODE BEGIN SPI1_Init 0 */

  /* USER CODE END SPI1_Init 0 */

  /* USER CODE BEGIN SPI1_Init 1 */

  /* USER CODE END SPI1_Init 1 */
  /* SPI1 parameter configuration*/
  hspi1.Instance = SPI1;
  hspi1.Init.Mode = SPI_MODE_MASTER;
  hspi1.Init.Direction = SPI_DIRECTION_2LINES;
  hspi1.Init.DataSize = SPI_DATASIZE_8BIT;
  hspi1.Init.CLKPolarity = SPI_POLARITY_LOW;
  hspi1.Init.CLKPhase = SPI_PHASE_1EDGE;
  hspi1.Init.NSS = SPI_NSS_SOFT;
  hspi1.Init.BaudRatePrescaler = SPI_BAUDRATEPRESCALER_2;
  hspi1.Init.FirstBit = SPI_FIRSTBIT_MSB;
  hspi1.Init.TIMode = SPI_TIMODE_DISABLE;
  hspi1.Init.CRCCalculation = SPI_CRCCALCULATION_DISABLE;
  hspi1.Init.CRCPolynomial = 10;
  if (HAL_SPI_Init(&hspi1) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN SPI1_Init 2 */

  /* USER CODE END SPI1_Init 2 */

}

/**
  * @brief GPIO Initialization Function
  * @param None
  * @retval None
  */
static void MX_GPIO_Init(void)
{
  GPIO_InitTypeDef GPIO_InitStruct = {0};

  /* GPIO Ports Clock Enable */
  __HAL_RCC_GPIOB_CLK_ENABLE();
  __HAL_RCC_GPIOD_CLK_ENABLE();
  __HAL_RCC_GPIOC_CLK_ENABLE();

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOB, GPIO_PIN_15|GPIO_PIN_14|GPIO_PIN_10|GPIO_PIN_13 
                          |GPIO_PIN_11|GPIO_PIN_12, GPIO_PIN_RESET);

  /*Configure GPIO pins : PC1 PC0 PC8 PC6  PC2
                           PC4 PC5 */
  GPIO_InitStruct.Pin = GPIO_PIN_1|GPIO_PIN_0|GPIO_PIN_8|GPIO_PIN_6 | GPIO_PIN_2
                          |GPIO_PIN_4|GPIO_PIN_5;
  GPIO_InitStruct.Mode = GPIO_MODE_IT_FALLING;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(GPIOC, &GPIO_InitStruct);

  /*Configure GPIO pins : PB15 PB14 PB10 PB13 
                           PB11 PB12 */
  GPIO_InitStruct.Pin = GPIO_PIN_15|GPIO_PIN_14|GPIO_PIN_10|GPIO_PIN_13 
                          |GPIO_PIN_11|GPIO_PIN_12;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOB, &GPIO_InitStruct);

  /* EXTI interrupt init*/
  HAL_NVIC_SetPriority(EXTI0_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI0_IRQn);

  HAL_NVIC_SetPriority(EXTI1_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI1_IRQn);

  HAL_NVIC_SetPriority(EXTI4_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI4_IRQn);

  HAL_NVIC_SetPriority(EXTI9_5_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI9_5_IRQn);

}

/* USER CODE BEGIN 4 */

/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */

  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(uint8_t *file, uint32_t line)
{ 
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     tex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
