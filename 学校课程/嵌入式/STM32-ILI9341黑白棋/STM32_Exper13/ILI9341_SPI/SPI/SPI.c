#include "spi.h"

SPI_HandleTypeDef hspi1;

void  SPIv_WriteData(uint8_t Data)
{
  HAL_SPI_Transmit(&hspi1, &Data, 1, 1000);
	/*
	unsigned char i=0;
	for(i=8;i>0;i--)
	{
	  if(Data&0x80)	
			SPI_MOSI_SET; //Êä³öÊý¾Ý
    else
			SPI_MOSI_CLR;
	   
      SPI_SCLK_CLR;       
      SPI_SCLK_SET;
      Data<<=1; 
	}
	*/
}

