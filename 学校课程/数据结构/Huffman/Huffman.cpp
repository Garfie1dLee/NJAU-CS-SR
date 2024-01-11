#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<limits.h>
#include<math.h>

#define LIST_INIT_SIZE 256 //线性表存储空间的初始分配量

static int count[LIST_INIT_SIZE];  //定义对于每个字符的计数数组

struct HNode {  //哈夫曼堆的实现
	int *Data;         // 存储int元素，即权值
	int Size;          // 当前堆内元素个数
	int Capacity;      // 堆的最大容量
};
typedef struct HNode *Heap;
Heap CreateMinHeap(int MaxSize)  //创建最小堆
{
	Heap H = (Heap)malloc(sizeof(struct HNode));       //分配堆空间
	H->Data = (int *)malloc((MaxSize + 1) * sizeof(int));  //分配堆的元素空间
	H->Size = 0;
	H->Capacity = MaxSize;
	H->Data[0] = -1;  //第一位为哨兵
	return H;
}
bool IsFull(Heap H) //判断堆是否为满
{
	return (H->Size == H->Capacity);
}
bool IsEmpty(Heap H)  //判断堆是否为空
{
	return (H->Size == 0);
}

bool Insert(Heap &H, int X) //排序插入堆
{
	int i;
	if (IsFull(H)) {
		printf("最小堆已满");
		return false;
	}
	i = ++H->Size;  //元素加一
	for (; H->Data[i / 2] > X; i /= 2)  //选择位置插入
		H->Data[i] = H->Data[i / 2];
	H->Data[i] = X;
	return true;
}
int ERROR=-1;
int Deletemin(Heap &H)    //返回并去除最小值
{
	int Parent, Child;
	int MinItem, X;

	if (IsEmpty(H)) {
		printf("最小堆已为空");
		return ERROR;
	}
	MinItem = H->Data[1];  //最小值为1号节点
	X = H->Data[H->Size--];
	for (Parent = 1; Parent * 2 <= H->Size; Parent = Child) {  //排序移动元素
		Child = Parent * 2;
		if ((Child != H->Size) && (H->Data[Child]>H->Data[Child + 1]))
			Child++;
		if (X <= H->Data[Child]) break;
		else
			H->Data[Parent] = H->Data[Child];
	}
	H->Data[Parent] = X;
	return MinItem;
}
void listprint(Heap H)  //print堆
{
    for(int i=1;i<=H->Size;i++)
        printf("%d  ",H->Data[i]);
}


int ReadFile(char filename[])   //读取文件
{
    char buff[1024]; //设置读取缓冲区
    FILE* fp;
    int len;
    int typecount=0;
    if ((fp = fopen(filename,"r")) == NULL)
	{
		perror("fail to read the file");
		exit(1);
	}
    while(fgets(buff,1024,fp))
    {
        len=strlen(buff);
        for(int i=0;i<len;i++)
        {
            if(buff[i]>256) printf("含有非法字符");
            count[buff[i]]++;
        }
    }
    for(int j=0;j<256;j++)   //读取同时计数
            if(count[j]!=0)
            typecount++;
    fclose(fp);
        return typecount;

}
int ReadFile1(char filename[])   //读取文件
{
    char buff[1024]; //设置读取缓冲区
    FILE* fp;
    int len;
    int count1=0;
    if ((fp = fopen(filename,"r")) == NULL)
	{
		perror("fail to read the file");
		exit(1);
	}
    while(fgets(buff,1024,fp))
    {
        len=strlen(buff);
        count1+=len;
    }

    fclose(fp);
        return count1;
}


typedef struct ElemType{  //哈夫曼节点 权值和字符名
    int value;
    char name;
};

typedef struct{       //线性表
    ElemType *elem;
    int length;
    int listsize;
}SqList;

int InitSqList(SqList &L)
{
    L.elem=(ElemType *)malloc(LIST_INIT_SIZE*sizeof(ElemType)); //申请线性表空间
    L.length=0; //当前长度
    L.listsize=LIST_INIT_SIZE; //当前分配量
    return 0;
}

int ListInsert(SqList &L,ElemType e)  //插入元素默认为队尾
{
    L.elem[L.length]=e;
    L.length++;
    return 0;
}


ElemType ListPeek(SqList L)
{
    return L.elem[0];
}

void ListSort(SqList &L)  //线性表排序
{
     for (int i=0;i<L.length-1;i++)
        for (int j=0;j<L.length-i-1;j++)
        {
            if (L.elem[j].value>L.elem[j+1].value) {
                ElemType temp=L.elem[j];
                L.elem[j]=L.elem[j+1];
                L.elem[j+1]=temp;
            }
        }
}

void ListPrint(SqList L)
{
    for(int i=0;i<L.length;i++)
        if(L.elem[i].name=='\n')
        printf("name=换行,value=%d\n",L.elem[i].value);
        else printf("name=%c,value=%d\n",L.elem[i].name,L.elem[i].value);
    printf("------------------------------------------------------------------------------\n");
}

void JoinElemTypeNode(SqList &L)   //线性表添加元素
{
     for(int i=0;i<LIST_INIT_SIZE;i++)
        if(count[i]!=0)
    {
       ElemType newone={count[i],(char)i};
       ListInsert(L,newone);
    }
    ListSort(L);
    ListPrint(L);
}



typedef struct        //哈夫曼 本质为线性表
{
    int value;
    char name;
    int parent, lchild, rchild;
}HTNode,*HuffmanTree;
typedef char * *HuffmanCode;

int min(HuffmanTree ht,int n){   //旧获得权值最小元素，暂不使用
	int i,ret;
	int temp=INT_MAX;
	for(i=1;i<=n;i++)
		if(ht[i].value<temp&&ht[i].parent==0){
			temp=ht[i].value;
			ret=i;
		}
	ht[ret].parent=1;
	return ret;
}
int newSearch(HuffmanTree HT,int n,int e)  //根据权值搜索相应的元素，并把parent设为1防止二次访问，等价。
{
    for(int i=1;i<=n;i++)
		if(HT[i].value==e&&HT[i].parent==0){
			HT[i].parent=1;
			return i;
		}
}
void Select(HuffmanTree HT,int n,int &s1,int &s2){   //暂不使用
	s1=min(HT,n);
	s2=min(HT,n);
}

void HuffmanCoding(HuffmanTree &HT, HuffmanCode &HC, SqList L, int n) //哈夫曼编码，建立哈夫曼树并获得对应哈夫曼编码
{
	int i,s1,s2,m,c,start,f;
	HuffmanTree p;
	char *cd;
	m=2*n-1;
	HT=(HuffmanTree)malloc((m+1)*sizeof(HTNode));  //申请空间 第0个不使用
for(p=HT+1,i=1; i<=n; ++i,++p){  //前n个元素为初始节点
	p->value=L.elem[i-1].value;
	p->name=L.elem[i-1].name;
	p->parent=0;
	p->lchild=0;
	p->rchild=0;
}
for(;i<=m; ++i,++p){  //初始化后n+1个元素
	p->value=0;
	p->name={};
	p->parent=0;
	p->lchild=0;
	p->rchild=0;
}  //对叶子之后的存储单元清零

   Heap H=CreateMinHeap(m);  //创建最小堆
   for(int i=1;i<=n;i++)
    Insert(H,L.elem[i-1].value);  //添加n个元素
    //listprint(H);
    //printf("\n");

for(i=n+1;i<=m; ++i){ //建Huffman树
	//Select(HT, i-1, s1, s2);
	int e1=Deletemin(H);int e2=Deletemin(H);  //获得堆的最小的2个元素
	s1=newSearch(HT,i-1,e1);
	s2=newSearch(HT,i-1,e2);
	//在HT[1…i-1]选择parent为0且weight最小的两个结点，其序号分别为
	//s1和s2
	HT[s1].parent=i; HT[s2].parent=i;
	HT[i].lchild=s1; HT[i].rchild=s2; //s1、s2分别作为i的左右孩子
	HT[i].value=HT[s1].value+ HT[s2].value;
	Insert(H,HT[i].value);  //添加为权值之和的元素进入堆
}


	HC=(HuffmanCode)malloc((n+1)*sizeof(char*));
	//分配n个字符编码的头指针向量
	cd=(char*) malloc(n*sizeof(char)); //分配求编码的工作空间
	cd[n-1]='\0'; //编码结束符（从cd[0]~cd[n-1]为合法空间）
for(i=1;i<=n;++i){ //逐个字符求Huffman编码
	start=n-1; //编码结束符位置
	for(c=i,f=HT[i].parent; f!=0; c=f, f=HT[f].parent)
	//从叶子到根逆向求编码
	if(HT[f].lchild==c) cd[--start]='0';
	else cd[--start]='1';
	HC[i]=(char*)malloc((n-start)*sizeof(char));
	//为第i个字符编码分配空间
	strcpy(HC[i],&cd[start]); //从cd复制编码串到HC
	}
	free(cd); //释放工作空间
}

void showHC(HuffmanCode HC,HuffmanTree HT,int n)
{
    for(int i=1;i<=n;i++)
    {
        printf("%c  ",HT[i].name);
        puts(HC[i]);
    //puts(w);
    }
}
void WriteCode(char filename[],HuffmanCode HC,HuffmanTree HT,int n)
{
    FILE *fp = NULL;
    fp = fopen(filename, "w+");  //写哈夫曼编码文件
    for(int i=1;i<=n;i++)
    {
        char w[30]={HT[i].name};
        strcat(w,HC[i]);
        strcat(w,"\n");
        fputs(w, fp);
    //puts(w);
    }
	fclose(fp);
}

void PrintHuffmanCode(HuffmanTree HT,int n)
{
    for(int i=1;i<=2*n-1;i++)
        printf("%d \n",HT[i].value);
}

void ReadandCompressFile2(char filename[],char ALL[],char OUT[],int n,HuffmanCode HC,HuffmanTree HT,char outfilename[])   //读取并压缩文件
{
    char buff[1024];
    FILE* fp;
    if ((fp = fopen(filename,"r")) == NULL)
	{
		perror("fail to read the file");
		exit(1);
	}
    while(fgets(buff,1024,fp))
    {
        strcat(ALL,buff);   //获得输入文件
    }
    //puts(ALL);  输入文件
    //printf("lengthjhshdhs=%d",strlen(ALL));


    int length=strlen(ALL);
    for(int i=0;i<length;i++)
        for(int j=1;j<=n;j++)
            if(ALL[i]==HT[j].name)  //变换输入文件为哈夫曼编码，10格式
            strcat(OUT,HC[j]);
    fp = fopen(outfilename, "wb+");  //二进制写入
    //puts(OUT); 二进制10文件
    //printf("\nlengthjhshdhs=%d\n",strlen(OUT));
    int totalcount=0;

    //compresss
    for(int i=7;i<strlen(OUT);i+=8){
        int temp=0;
        for (int j=i;i-j<8;j--)  //每八位转为0-255的int型
        temp+=(OUT[j]-48)*(pow(2,(i-j)));
       // if(temp==0)printf("-----------------------------------------------------------------------------------may error");
        //if(temp==26){temp=0;}
        fputc(temp,fp);
       // printf("%d ",temp);
        totalcount++;
    }
    fclose(fp);
    //printf("\ntotalcount%d\n",totalcount);
}
typedef struct HFCode{  //哈夫曼编码
    char name;
    char c[1024];
};
void del_char(char a[],char c)    //删除指定字符
{
    int i,j;
    for(i=0,j=0; *(a+i)!='\0'; i++)
    {
        if(*(a+i)==c)
             continue;
         else
         {
             *(a+j)=*(a+i);
             j++;
         }
     }
     *(a+j)='\0';
 }
void Getmessage(char filename[],HFCode IN[])  //从哈夫曼编码文件中获得哈夫曼编码 HFCOde
{
    char buff[1024];
    FILE* fp;
    if ((fp = fopen(filename,"r")) == NULL)
	{
		perror("fail to read the file");
		exit(1);
	}
	int count1=1;
	IN[0].name='a';
	strcat(IN[0].c,"a");
	while(fgets(buff,1024,fp))
    {
        if(buff[0]=='\n')   //根据换行进行不同的读取
        {
            IN[count1].name='\n';
             fgets(buff,1024,fp);
             strtok(buff, "\n");
             strcpy(IN[count1].c,buff);
             count1++;
        }
        else
        {
        del_char(buff,'\n');
        IN[count1].name=buff[0];
        strcpy(IN[count1].c,buff+1);
        count1++;
        }
    }

    fclose(fp);
}


void fun(char*s)  //字符串的倒置
{
   int i,j=0,n;
   char temp;
   n=strlen(s);
    for(i=0;i<n/2;i++){
        temp = s[i];
        s[i] = s[n-1-i];
        s[n-1-i] = temp;
    }
}

void substring(char a[],char b[],int start,int last)  //字符串的截取函数
{
    int i;
    for(i=0;i<last-start;i++)
    {
        b[i]=a[start+i];
    }
    b[++i]='\0';
}

int DeCompressFile(char filename[],HFCode USE[],char outfile[])  //从哈夫曼编码文件与压缩文件解压为原文件
{
    FILE* fp;
    if ((fp = fopen(filename,"rb")) == NULL)  //二进制读取，规避Int 26终止符号
	{
		perror("fail to read the file");
		exit(1);
	}
    char laststr[50000]={};
    int e;
    while((e=fgetc(fp))!=EOF)
    {
            //printf("%d  ",e);输出读入int字符
            char str[50]={};
            int temp=0;
            //if(e==0)
           //    temp=26;
           // else
               temp=e;
            for(int j=0;j<8;j++)  //获得每个int代表的8位二进制编码
            {
                int compare=abs(temp%2);
                if(compare==1)
                    strcat(str,"1");
                else
                    strcat(str,"0");
                temp=temp/2;
            }
            fun(str);  //倒置编码为正确。
            strcat(laststr,str);
    }
    //puts("---------------------------------------------------------");
    //puts(laststr);输出编码文件10格式

    int uselen;
    fp = fopen(outfile, "w+"); //写出解压缩文件
    for(int i=0;USE[i].name!=NULL;i++)
        uselen=i;
    //printf("%d\n",uselen); 输出长度
    //printf("%d",strlen(laststr)); 还原字符串长度

    int supercountl=0;
    for(int i=0;i<strlen(laststr);i++)
      for(int j=i;j<strlen(laststr);j++){
           char strin[50000]={};substring(laststr,strin,i,j);
           for(int k=1;k<=uselen;k++)
            if(strcmp(strin,USE[k].c)==0)
            {
                i=j;fputc(USE[k].name,fp);supercountl++;
                break;
            }
            }

    fclose(fp);
    return supercountl;




}
int main()
{
    printf("输入1 进行文件压缩\n输入2 进行文件解压缩\n");
    char ch=getchar();
    char filename2[]="huffmancode.txt";
    char outfilename[]="code.dat";
    char filename[]="source.txt";
    if(ch=='1')
    {
    printf("请确认文件source.txt在同一目录内，如果无反应则文件内可能包含非ASCII字符，确认后输入回车\n");
    getchar();
    getchar();
    int typecount=ReadFile(filename);  //读取输入文件
    printf("共%d种字符\n",typecount);
    SqList L;
    InitSqList(L);
    JoinElemTypeNode(L);
    HuffmanCode HC;
    HuffmanTree HT;

    HuffmanCoding(HT,HC,L,typecount);  //哈夫曼编码
    printf("哈夫曼编码为\n");
    showHC(HC,HT,typecount);

    WriteCode(filename2,HC,HT,typecount);  //写哈夫曼文件

    char OUT[500000]="\0";
    char ALL[500000]="\0";

    ReadandCompressFile2(filename,ALL,OUT,typecount,HC,HT,outfilename); //写压缩文件

    //puts(OUT);
    //puts(ALL);
    printf("压缩完成!!!  压缩文件为code.dat, 文件长度为%d个字符,压缩率为%.2f%%  \n",strlen(OUT)/8,100*strlen(OUT)/8.0/strlen(ALL));
    puts("输入回车退出或输入2进入解压缩模式");
    ch=getchar();
    }
    if(ch=='2')
    {
    printf("请输入解压缩文件名称,保证哈夫曼编码文件huffmancode.txt与输出文件code.dat在同一目录内\n");
    char orfile[30];
    scanf("%s",orfile);
    HFCode *Co=(HFCode*)malloc((256)*sizeof(HFCode));
    Getmessage(filename2,Co); //首先获得哈夫曼编码
    //for(int i=1;i<=typecount;i++)
     //  printf("name=%c,len=%d,code=%s \n",Co[i].name,strlen(Co[i].c),(Co[i].c));
   //for(int i=0;i<strlen(Co[0].c);i++)
        //printf("%c",Co[0].c[0]);
    int count1=ReadFile1(filename);
    int count2=DeCompressFile(outfilename,Co,orfile);  //还原原文件
    printf("解压缩完成 完整率为%.2f\n",count2*100.0/count1);
    getchar();
    getchar();
    }
    return 0;
}
