#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<limits.h>
#include<math.h>

#define LIST_INIT_SIZE 256 //���Ա�洢�ռ�ĳ�ʼ������

static int count[LIST_INIT_SIZE];  //�������ÿ���ַ��ļ�������

struct HNode {  //�������ѵ�ʵ��
	int *Data;         // �洢intԪ�أ���Ȩֵ
	int Size;          // ��ǰ����Ԫ�ظ���
	int Capacity;      // �ѵ��������
};
typedef struct HNode *Heap;
Heap CreateMinHeap(int MaxSize)  //������С��
{
	Heap H = (Heap)malloc(sizeof(struct HNode));       //����ѿռ�
	H->Data = (int *)malloc((MaxSize + 1) * sizeof(int));  //����ѵ�Ԫ�ؿռ�
	H->Size = 0;
	H->Capacity = MaxSize;
	H->Data[0] = -1;  //��һλΪ�ڱ�
	return H;
}
bool IsFull(Heap H) //�ж϶��Ƿ�Ϊ��
{
	return (H->Size == H->Capacity);
}
bool IsEmpty(Heap H)  //�ж϶��Ƿ�Ϊ��
{
	return (H->Size == 0);
}

bool Insert(Heap &H, int X) //��������
{
	int i;
	if (IsFull(H)) {
		printf("��С������");
		return false;
	}
	i = ++H->Size;  //Ԫ�ؼ�һ
	for (; H->Data[i / 2] > X; i /= 2)  //ѡ��λ�ò���
		H->Data[i] = H->Data[i / 2];
	H->Data[i] = X;
	return true;
}
int ERROR=-1;
int Deletemin(Heap &H)    //���ز�ȥ����Сֵ
{
	int Parent, Child;
	int MinItem, X;

	if (IsEmpty(H)) {
		printf("��С����Ϊ��");
		return ERROR;
	}
	MinItem = H->Data[1];  //��СֵΪ1�Žڵ�
	X = H->Data[H->Size--];
	for (Parent = 1; Parent * 2 <= H->Size; Parent = Child) {  //�����ƶ�Ԫ��
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
void listprint(Heap H)  //print��
{
    for(int i=1;i<=H->Size;i++)
        printf("%d  ",H->Data[i]);
}


int ReadFile(char filename[])   //��ȡ�ļ�
{
    char buff[1024]; //���ö�ȡ������
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
            if(buff[i]>256) printf("���зǷ��ַ�");
            count[buff[i]]++;
        }
    }
    for(int j=0;j<256;j++)   //��ȡͬʱ����
            if(count[j]!=0)
            typecount++;
    fclose(fp);
        return typecount;

}
int ReadFile1(char filename[])   //��ȡ�ļ�
{
    char buff[1024]; //���ö�ȡ������
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


typedef struct ElemType{  //�������ڵ� Ȩֵ���ַ���
    int value;
    char name;
};

typedef struct{       //���Ա�
    ElemType *elem;
    int length;
    int listsize;
}SqList;

int InitSqList(SqList &L)
{
    L.elem=(ElemType *)malloc(LIST_INIT_SIZE*sizeof(ElemType)); //�������Ա�ռ�
    L.length=0; //��ǰ����
    L.listsize=LIST_INIT_SIZE; //��ǰ������
    return 0;
}

int ListInsert(SqList &L,ElemType e)  //����Ԫ��Ĭ��Ϊ��β
{
    L.elem[L.length]=e;
    L.length++;
    return 0;
}


ElemType ListPeek(SqList L)
{
    return L.elem[0];
}

void ListSort(SqList &L)  //���Ա�����
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
        printf("name=����,value=%d\n",L.elem[i].value);
        else printf("name=%c,value=%d\n",L.elem[i].name,L.elem[i].value);
    printf("------------------------------------------------------------------------------\n");
}

void JoinElemTypeNode(SqList &L)   //���Ա����Ԫ��
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



typedef struct        //������ ����Ϊ���Ա�
{
    int value;
    char name;
    int parent, lchild, rchild;
}HTNode,*HuffmanTree;
typedef char * *HuffmanCode;

int min(HuffmanTree ht,int n){   //�ɻ��Ȩֵ��СԪ�أ��ݲ�ʹ��
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
int newSearch(HuffmanTree HT,int n,int e)  //����Ȩֵ������Ӧ��Ԫ�أ�����parent��Ϊ1��ֹ���η��ʣ��ȼۡ�
{
    for(int i=1;i<=n;i++)
		if(HT[i].value==e&&HT[i].parent==0){
			HT[i].parent=1;
			return i;
		}
}
void Select(HuffmanTree HT,int n,int &s1,int &s2){   //�ݲ�ʹ��
	s1=min(HT,n);
	s2=min(HT,n);
}

void HuffmanCoding(HuffmanTree &HT, HuffmanCode &HC, SqList L, int n) //���������룬����������������ö�Ӧ����������
{
	int i,s1,s2,m,c,start,f;
	HuffmanTree p;
	char *cd;
	m=2*n-1;
	HT=(HuffmanTree)malloc((m+1)*sizeof(HTNode));  //����ռ� ��0����ʹ��
for(p=HT+1,i=1; i<=n; ++i,++p){  //ǰn��Ԫ��Ϊ��ʼ�ڵ�
	p->value=L.elem[i-1].value;
	p->name=L.elem[i-1].name;
	p->parent=0;
	p->lchild=0;
	p->rchild=0;
}
for(;i<=m; ++i,++p){  //��ʼ����n+1��Ԫ��
	p->value=0;
	p->name={};
	p->parent=0;
	p->lchild=0;
	p->rchild=0;
}  //��Ҷ��֮��Ĵ洢��Ԫ����

   Heap H=CreateMinHeap(m);  //������С��
   for(int i=1;i<=n;i++)
    Insert(H,L.elem[i-1].value);  //���n��Ԫ��
    //listprint(H);
    //printf("\n");

for(i=n+1;i<=m; ++i){ //��Huffman��
	//Select(HT, i-1, s1, s2);
	int e1=Deletemin(H);int e2=Deletemin(H);  //��öѵ���С��2��Ԫ��
	s1=newSearch(HT,i-1,e1);
	s2=newSearch(HT,i-1,e2);
	//��HT[1��i-1]ѡ��parentΪ0��weight��С��������㣬����ŷֱ�Ϊ
	//s1��s2
	HT[s1].parent=i; HT[s2].parent=i;
	HT[i].lchild=s1; HT[i].rchild=s2; //s1��s2�ֱ���Ϊi�����Һ���
	HT[i].value=HT[s1].value+ HT[s2].value;
	Insert(H,HT[i].value);  //���ΪȨֵ֮�͵�Ԫ�ؽ����
}


	HC=(HuffmanCode)malloc((n+1)*sizeof(char*));
	//����n���ַ������ͷָ������
	cd=(char*) malloc(n*sizeof(char)); //���������Ĺ����ռ�
	cd[n-1]='\0'; //�������������cd[0]~cd[n-1]Ϊ�Ϸ��ռ䣩
for(i=1;i<=n;++i){ //����ַ���Huffman����
	start=n-1; //���������λ��
	for(c=i,f=HT[i].parent; f!=0; c=f, f=HT[f].parent)
	//��Ҷ�ӵ������������
	if(HT[f].lchild==c) cd[--start]='0';
	else cd[--start]='1';
	HC[i]=(char*)malloc((n-start)*sizeof(char));
	//Ϊ��i���ַ��������ռ�
	strcpy(HC[i],&cd[start]); //��cd���Ʊ��봮��HC
	}
	free(cd); //�ͷŹ����ռ�
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
    fp = fopen(filename, "w+");  //д�����������ļ�
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

void ReadandCompressFile2(char filename[],char ALL[],char OUT[],int n,HuffmanCode HC,HuffmanTree HT,char outfilename[])   //��ȡ��ѹ���ļ�
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
        strcat(ALL,buff);   //��������ļ�
    }
    //puts(ALL);  �����ļ�
    //printf("lengthjhshdhs=%d",strlen(ALL));


    int length=strlen(ALL);
    for(int i=0;i<length;i++)
        for(int j=1;j<=n;j++)
            if(ALL[i]==HT[j].name)  //�任�����ļ�Ϊ���������룬10��ʽ
            strcat(OUT,HC[j]);
    fp = fopen(outfilename, "wb+");  //������д��
    //puts(OUT); ������10�ļ�
    //printf("\nlengthjhshdhs=%d\n",strlen(OUT));
    int totalcount=0;

    //compresss
    for(int i=7;i<strlen(OUT);i+=8){
        int temp=0;
        for (int j=i;i-j<8;j--)  //ÿ��λתΪ0-255��int��
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
typedef struct HFCode{  //����������
    char name;
    char c[1024];
};
void del_char(char a[],char c)    //ɾ��ָ���ַ�
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
void Getmessage(char filename[],HFCode IN[])  //�ӹ����������ļ��л�ù��������� HFCOde
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
        if(buff[0]=='\n')   //���ݻ��н��в�ͬ�Ķ�ȡ
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


void fun(char*s)  //�ַ����ĵ���
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

void substring(char a[],char b[],int start,int last)  //�ַ����Ľ�ȡ����
{
    int i;
    for(i=0;i<last-start;i++)
    {
        b[i]=a[start+i];
    }
    b[++i]='\0';
}

int DeCompressFile(char filename[],HFCode USE[],char outfile[])  //�ӹ����������ļ���ѹ���ļ���ѹΪԭ�ļ�
{
    FILE* fp;
    if ((fp = fopen(filename,"rb")) == NULL)  //�����ƶ�ȡ�����Int 26��ֹ����
	{
		perror("fail to read the file");
		exit(1);
	}
    char laststr[50000]={};
    int e;
    while((e=fgetc(fp))!=EOF)
    {
            //printf("%d  ",e);�������int�ַ�
            char str[50]={};
            int temp=0;
            //if(e==0)
           //    temp=26;
           // else
               temp=e;
            for(int j=0;j<8;j++)  //���ÿ��int�����8λ�����Ʊ���
            {
                int compare=abs(temp%2);
                if(compare==1)
                    strcat(str,"1");
                else
                    strcat(str,"0");
                temp=temp/2;
            }
            fun(str);  //���ñ���Ϊ��ȷ��
            strcat(laststr,str);
    }
    //puts("---------------------------------------------------------");
    //puts(laststr);��������ļ�10��ʽ

    int uselen;
    fp = fopen(outfile, "w+"); //д����ѹ���ļ�
    for(int i=0;USE[i].name!=NULL;i++)
        uselen=i;
    //printf("%d\n",uselen); �������
    //printf("%d",strlen(laststr)); ��ԭ�ַ�������

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
    printf("����1 �����ļ�ѹ��\n����2 �����ļ���ѹ��\n");
    char ch=getchar();
    char filename2[]="huffmancode.txt";
    char outfilename[]="code.dat";
    char filename[]="source.txt";
    if(ch=='1')
    {
    printf("��ȷ���ļ�source.txt��ͬһĿ¼�ڣ�����޷�Ӧ���ļ��ڿ��ܰ�����ASCII�ַ���ȷ�Ϻ�����س�\n");
    getchar();
    getchar();
    int typecount=ReadFile(filename);  //��ȡ�����ļ�
    printf("��%d���ַ�\n",typecount);
    SqList L;
    InitSqList(L);
    JoinElemTypeNode(L);
    HuffmanCode HC;
    HuffmanTree HT;

    HuffmanCoding(HT,HC,L,typecount);  //����������
    printf("����������Ϊ\n");
    showHC(HC,HT,typecount);

    WriteCode(filename2,HC,HT,typecount);  //д�������ļ�

    char OUT[500000]="\0";
    char ALL[500000]="\0";

    ReadandCompressFile2(filename,ALL,OUT,typecount,HC,HT,outfilename); //дѹ���ļ�

    //puts(OUT);
    //puts(ALL);
    printf("ѹ�����!!!  ѹ���ļ�Ϊcode.dat, �ļ�����Ϊ%d���ַ�,ѹ����Ϊ%.2f%%  \n",strlen(OUT)/8,100*strlen(OUT)/8.0/strlen(ALL));
    puts("����س��˳�������2�����ѹ��ģʽ");
    ch=getchar();
    }
    if(ch=='2')
    {
    printf("�������ѹ���ļ�����,��֤�����������ļ�huffmancode.txt������ļ�code.dat��ͬһĿ¼��\n");
    char orfile[30];
    scanf("%s",orfile);
    HFCode *Co=(HFCode*)malloc((256)*sizeof(HFCode));
    Getmessage(filename2,Co); //���Ȼ�ù���������
    //for(int i=1;i<=typecount;i++)
     //  printf("name=%c,len=%d,code=%s \n",Co[i].name,strlen(Co[i].c),(Co[i].c));
   //for(int i=0;i<strlen(Co[0].c);i++)
        //printf("%c",Co[0].c[0]);
    int count1=ReadFile1(filename);
    int count2=DeCompressFile(outfilename,Co,orfile);  //��ԭԭ�ļ�
    printf("��ѹ����� ������Ϊ%.2f\n",count2*100.0/count1);
    getchar();
    getchar();
    }
    return 0;
}
